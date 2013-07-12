/*     */ package net.minecraft.launcher.updater.download;
/*     */ 
/*     */ import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ public class DownloadJob
/*     */ {
/*     */   private static final int MAX_ATTEMPTS_PER_FILE = 5;
			private final Queue<Downloadable> remainingFiles = new ConcurrentLinkedQueue<Downloadable>();
			private final List<Downloadable> allFiles = Collections.synchronizedList(new ArrayList<Downloadable>());
			private final List<Downloadable> failures = Collections.synchronizedList(new ArrayList<Downloadable>());
			private final List<Downloadable> successful = Collections.synchronizedList(new ArrayList<Downloadable>());
/*     */   private final String name;
/*     */   private final boolean ignoreFailures;
			private final AtomicInteger remainingThreads = new AtomicInteger();
/*     */   private boolean started;
			private static long sizeTotal = 0L;
			private static long sizeDone = 0L;
/*     */ 
/*     */   public DownloadJob(String name, boolean ignoreFailures, Collection<Downloadable> files)
/*     */   {
/*  26 */     this.name = name;
/*  27 */     this.ignoreFailures = ignoreFailures;
/*  28 */  
/*  29 */     if (files != null) addDownloadables(files); 
/*     */   }
/*     */ 
/*     */   public DownloadJob(String name, boolean ignoreFailures)
/*     */   {
/*  33 */     this(name, ignoreFailures, null);
/*     */   }
/*     */ 
/*     */   public void addDownloadables(Collection<Downloadable> downloadables) {
/*  37 */     if (this.started) throw new IllegalStateException("Cannot add to download job that has already started");
/*     */ 
/*  39 */     this.allFiles.addAll(downloadables);
/*  40 */     this.remainingFiles.addAll(downloadables);
/*     */ 
/*  42 */     for (Downloadable downloadable : downloadables) {
/*  47 */         sizeTotal += downloadable.getExpectedSize();
/*     */       }
/*  49 */
/*     */     }
/*     */   
/*     */ 
/*     */   public void addDownloadables(Downloadable[] downloadables) {
/*  54 */     if (this.started) throw new IllegalStateException("Cannot add to download job that has already started");
/*     */ 
/*  56 */     for (Downloadable downloadable : downloadables) {
/*  57 */       this.allFiles.add(downloadable);
/*  58 */       this.remainingFiles.add(downloadable);
	/*  47 */         sizeTotal += downloadable.getExpectedSize();
	       		
/*     */       
/*  65 */       }
/*     */     }
/*     */   
/*     */ 
/*     */   public void startDownloading(ThreadPoolExecutor executorService) {
/*  70 */     if (this.started) throw new IllegalStateException("Cannot start download job that has already started");
/*  71 */     this.started = true;
/*     */ 
/*  73 */     if (this.allFiles.isEmpty()) 
/*  74 */       System.out.println("Download job '" + this.name + "' skipped as there are no files to download");
/*     */     else {
/*  77 */       int threads = executorService.getMaximumPoolSize();
/*  78 */       this.remainingThreads.set(threads);
/*  79 */       System.out.println("Download job '" + this.name + "' started (" + threads + " threads, " + this.allFiles.size() + " files)");
/*  80 */       for (int i = 0; i < threads; i++)
/*  81 */         executorService.submit(new Runnable()
/*     */         {
/*     */           public void run() {
/*  84 */             DownloadJob.this.popAndDownload();
/*     */           }
/*     */         });
/*     */     }
/*     */   }
/*     */ 
/*     */   private void popAndDownload()
/*     */   {
/*     */     Downloadable downloadable;
/*  94 */     while ((downloadable = (Downloadable)this.remainingFiles.poll()) != null) {
/*  95 */       if (downloadable.getNumAttempts() > MAX_ATTEMPTS_PER_FILE) {
/*  96 */         if (!this.ignoreFailures) this.failures.add(downloadable);
/*  97 */         System.out.println("Gave up trying to download " + downloadable.getUrl() + " for job '" + this.name + "'");
/*     */       }else{
	
/*     */         try {
/* 102 */           String result = downloadable.download();
/* 103 */           
					sizeDone += downloadable.getExpectedSize();
/* 104 */           System.out.println("Finished downloading " + downloadable.getTarget() + " for job '" + this.name + "'" + ": " + result);
/*     */         }catch (Throwable t) {
/* 106 */           System.out.println("Couldn't download " + downloadable.getUrl() + " for job '" + this.name + "'");
					t.printStackTrace();
/* 107 */           this.remainingFiles.add(downloadable);
/*     */         }
					this.successful.add(downloadable);
/*     */       }
/*     */     }
/* 111 */
				
/*     */   }
/*     */ 
/*     */   public boolean shouldIgnoreFailures()
/*     */   {
/* 117 */     return this.ignoreFailures;
/*     */   }
/*     */ 
/*     */   public boolean isStarted() {
/* 121 */     return this.started;
/*     */   }
/*     */ 
/*     */   public boolean isComplete() {
/* 125 */     return (this.started) && (this.remainingFiles.isEmpty());
/*     */   }
/*     */ 
/*     */   public int getFailures() {
/* 129 */     return this.failures.size();
/*     */   }
/*     */ 
/*     */   public int getSuccessful() {
/* 133 */     return this.successful.size();
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 137 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   public int getProgress() {
/* 155 */     long result;
				if(sizeTotal > 0)
/* 156 */     result = (sizeDone * 100) / sizeTotal;
				else
			  result = 100;
/* 157 */     	return (int) result;
/*     */   }
/*     */ }