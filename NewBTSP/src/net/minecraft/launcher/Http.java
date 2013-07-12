/*     */ package net.minecraft.launcher;
/*     */ 
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import org.apache.commons.io.Charsets;
/*     */ public class Http
/*     */ {
            //private static Proxy proxy;
            
            
/*     */   public static String buildQuery(Map<String, Object> query)
/*     */   {
/*  14 */     StringBuilder builder = new StringBuilder();
/*     */ 	
/*  16 */     for (Map.Entry entry : query.entrySet()) {
/*  17 */       if (builder.length() > 0) {
/*  18 */         builder.append('&');
/*     */       }
/*     */       try
/*     */       {
/*  22 */         builder.append(URLEncoder.encode((String)entry.getKey(), "UTF-8"));
/*     */       } catch (UnsupportedEncodingException e) {
/*  24 */         printStackTrace("Unexpected exception building query", e);
/*     */       }
/*     */ 
/*  27 */       if (entry.getValue() != null) {
/*  28 */         builder.append('=');
/*     */         try {
/*  30 */           builder.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
/*     */         } catch (UnsupportedEncodingException e) {
/*  32 */           printStackTrace("Unexpected exception building query", e);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  37 */     return builder.toString();
/*     */   }

			private static void printStackTrace(String string,
		UnsupportedEncodingException e) {
	// TODO Auto-generated method stub
	
}
/*     */ 
/*     */   public static String performPost(URL url, Map<String, Object> query) throws IOException {
/*  41 */     return performPost(url, buildQuery(query), "application/x-www-form-urlencoded", false);
/*     */   }
/*     */ 
/*     */   public static String performPost(URL url, String parameters, String contentType, boolean returnErrorPage) throws IOException {
/*  45 */     HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/*  46 */     byte[] paramAsBytes = parameters.getBytes(Charsets.UTF_8);
/*     */ 
/*  48 */     connection.setConnectTimeout(15000);
/*  49 */     connection.setReadTimeout(15000);
/*  50 */     connection.setRequestMethod("POST");
/*  51 */     connection.setRequestProperty("Content-Type", contentType + "; charset=utf-8");
/*     */ 
/*  53 */     connection.setRequestProperty("Content-Length", "" + paramAsBytes.length);
/*  54 */     connection.setRequestProperty("Content-Language", "en-US");
/*     */ 
/*  56 */     connection.setUseCaches(false);
/*  57 */     connection.setDoInput(true);
/*  58 */     connection.setDoOutput(true);
/*     */ 
/*  61 */     DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
/*  62 */     writer.write(paramAsBytes);
/*  63 */     writer.flush();
/*  64 */     writer.close();
			BufferedReader reader;
/*     */     try
/*     */     {
/*  70 */       reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*     */       
/*     */       
/*  72 */       if (returnErrorPage) {
/*  73 */         InputStream stream = connection.getErrorStream();
/*     */ 
/*  75 */         if (stream != null)
/*  76 */           reader = new BufferedReader(new InputStreamReader(stream));
/*     */         else
/*  78 */           throw e;
/*     */       }
/*     */       else {
/*  81 */         throw e;
/*     */       }
/*     */     }
/*     */     
/*  86 */     StringBuilder response = new StringBuilder();
/*     */     String line;
/*  88 */     while ((line = reader.readLine()) != null) {
/*  89 */       response.append(line);
/*  90 */       response.append('\r');
/*     */     }
/*     */ 
/*  93 */     reader.close();
/*  94 */     return response.toString();
/*     */   }
/*     */ 
/*     */   public static String performGet(URL url) throws IOException {
/*  98 */     HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/*  99 */     connection.setRequestMethod("GET");
/*     */ 
/* 102 */     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */ 
/* 104 */     StringBuilder response = new StringBuilder();
/*     */     String line;
/* 106 */     while ((line = reader.readLine()) != null) {
/* 107 */       response.append(line);
/* 108 */       response.append('\r');
/*     */     }
/*     */ 
/* 111 */     reader.close();
/* 112 */     return response.toString();
/*     */   }
/*     */ 
/*     */   public static URL concatenateURL(URL url, String args) throws MalformedURLException {
/* 116 */     if ((url.getQuery() != null) && (url.getQuery().length() > 0)) {
/* 117 */       return new URL(url.getProtocol(), url.getHost(), url.getFile() + "?" + args);
/*     */     }
/* 119 */     return new URL(url.getProtocol(), url.getHost(), url.getFile() + "&" + args);
/*     */   }
/*     */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.Http
 * JD-Core Version:    0.6.2
 */