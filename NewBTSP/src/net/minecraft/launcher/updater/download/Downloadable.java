/*     */ package net.minecraft.launcher.updater.download;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.math.BigInteger;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.Proxy;
/*     */ import java.net.URL;
/*     */ import java.security.DigestInputStream;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ 
/*     */ public class Downloadable
/*     */ {
			private static int read;
/*     */   private final URL url;
/*     */   private final File target;
/*     */   private final boolean forceDownload;
/*     */   private int numAttempts;
/*  17 */   private long expectedSize = 0L;
			private Proxy proxy;
/*     */ 
/*     */   public Downloadable(URL remoteFile, File localFile, boolean forceDownload) {
/*  20 */    
/*  21 */     this.url = remoteFile;
/*  22 */     this.target = localFile;
/*  23 */     this.forceDownload = forceDownload;
/*  24 *
/*     */   }
/*     */ 

/*     */ 
/*     */   public long getExpectedSize() {
/*  32 */     return this.expectedSize;
/*     */   }
/*     */ 
/*     */   public void setExpectedSize(long expectedSize) {
/*  36 */     this.expectedSize = expectedSize;
/*     */   }
/*     */ 
/*     */   public String download() throws IOException{
			  String localMd5 = null;
/*  42 */     this.numAttempts += 1;
/*     */ 
/*  44 */     if ((this.target.getParentFile() != null) && (!this.target.getParentFile().isDirectory())) 
/*  45 */       this.target.getParentFile().mkdirs();
/*     */     
/*  47 */     if ((!this.forceDownload) && (this.target.isFile())) //if not force download and the file exists, consider for skipping
/*  48 */       localMd5 = getMD5(this.target);
/*     */     
/*  51 */     if ((this.target.isFile()) && (!this.target.canWrite())) //if exists and cannot write then permissions are incorect
/*  52 */       throw new RuntimeException("Do not have write permissions for " + this.target + " - aborting!");
/*     */     
			  int status = -1;

/*     */  try{
/*  56 */       HttpURLConnection connection = makeConnection(localMd5);
/*  57 */       status = connection.getResponseCode();
/*     */ 
/*  59 */       if (status == 304)
/*  60 */         return "Used own copy as it matched etag";
/*  61 */       
/*     */ 
/*  68 */         InputStream inputStream = (connection.getInputStream());
/*  69 */         FileOutputStream outputStream = new FileOutputStream(this.target);
/*  70 */         String md5 = copyAndDigest(inputStream, outputStream);
/*  71 */         String etag = getEtag(connection);
/*     */ 
/*  73 */       if (etag.contains("-"))
/*  75 */           return "Didn't have etag so assuming our copy is good";

				if (etag.equalsIgnoreCase(md5))
/*  78 */           return "Downloaded successfully and etag matched";
				else
					return "E-tag did not match downloaded MD5 (ETag was "+etag+", downloaded "+md5+")";
				
				}catch (IOException e) {	
/*  82 */       if (this.target.isFile()) {
/*  83 */         return "Couldn't connect to server (responded with " + status + ") but have local file, assuming it's good";
				} 
/*     */     	}catch (NoSuchAlgorithmException e){
/*  94 */       throw new RuntimeException("Missing Digest.MD5", e);
 
				}
				return "Not Sure what Happened there? Conected and not conected?";
				}
/*     */ 
/*     */   protected HttpURLConnection makeConnection(String localMd5) throws IOException {
/*  99 */     HttpURLConnection connection = (HttpURLConnection)this.url.openConnection();
/* 102 */     connection.setUseCaches(false);
/* 103 */     connection.setDefaultUseCaches(false);
/* 104 */     connection.setRequestProperty("Cache-Control", "no-store,max-age=0,no-cache");
/* 105 */     connection.setRequestProperty("Expires", "0");
/* 106 */     connection.setRequestProperty("Pragma", "no-cache");
/* 107 */     if (localMd5 != null) connection.setRequestProperty("If-None-Match", localMd5);
/* 109 */     connection.connect();
/* 111 */     return connection;
/*     */   }
/*     */ 
/*     */   public URL getUrl() {
/* 115 */     return this.url;
/*     */   }
/*     */ 
/*     */   public File getTarget() {
/* 119 */     return this.target;
/*     */   }
/*     */ 
/*     */   public boolean shouldIgnoreLocal() {
/* 123 */     return this.forceDownload;
/*     */   }
/*     */ 
/*     */   public int getNumAttempts() {
/* 127 */     return this.numAttempts;
/*     */   }
/*     */ 
/*     */   public static String getMD5(File file) {
/* 135 */     DigestInputStream stream = null;
/*     */     try
/*     */     {
/* 138 */       stream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
/* 139 */       byte[] buffer = new byte[65536];
/*     */ 
/* 141 */       read = stream.read(buffer);
/* 142 */       while (read >= 1)
/* 143 */         read = stream.read(buffer);
/*     */     }
/*     */     catch (Exception ignored)
/*     */     {
/*     */       
/* 146 */       return null;
/*     */     } finally {
/* 148 */       closeSilently(stream);
/*     */     }
/*     */ 
/* 151 */     return String.format("%1$032x", new Object[] { new BigInteger(1, stream.getMessageDigest().digest()) });
/*     */   }
/*     */ 
/*     */   public static void closeSilently(Closeable closeable) {
/* 155 */     if (closeable != null)
/*     */       try {
/* 157 */         closeable.close();
/*     */       }
/*     */       catch (IOException localIOException) {
/*     */       }
/*     */   }
/*     */ 
/*     */   public static String copyAndDigest(InputStream inputStream, OutputStream outputStream) throws IOException, NoSuchAlgorithmException {
/* 164 */     MessageDigest digest = MessageDigest.getInstance("MD5");
/* 165 */     byte[] buffer = new byte[65536];
/*     */     try
/*     */     {
/* 168 */       int read = inputStream.read(buffer);
/* 169 */       while (read >= 1) {
/* 170 */         digest.update(buffer, 0, read);
/* 171 */         outputStream.write(buffer, 0, read);
/* 172 */         read = inputStream.read(buffer);
/*     */       }
/*     */     } finally {
/* 175 */       closeSilently(inputStream);
/* 176 */       closeSilently(outputStream);
/*     */     }
/*     */ 
/* 179 */     return String.format("%1$032x", new Object[] { new BigInteger(1, digest.digest()) });
/*     */   }
/*     */ 
/*     */   public static String getEtag(HttpURLConnection connection) {
/* 183 */     return getEtag(connection.getHeaderField("ETag"));
/*     */   }
/*     */ 
/*     */   public static String getEtag(String etag) {
/* 187 */     if (etag == null)
/* 188 */       etag = "-";
/* 189 */     else if ((etag.startsWith("\"")) && (etag.endsWith("\"")))
/*     */     {
/* 191 */       etag = etag.substring(1, etag.length() - 1);
/*     */     }
/*     */ 
/* 194 */     return etag;
/*     */   }
/*     */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.updater.download.Downloadable
 * JD-Core Version:    0.6.2
 */