/*    */ package net.minecraft.launcher.process;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class ProcessMonitorThread extends Thread
/*    */ {
/*    */   private final JavaProcess process;
/*    */ 
/*    */   public ProcessMonitorThread(JavaProcess process)
/*    */   {
/* 15 */     this.process = process;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 20 */     InputStreamReader reader = new InputStreamReader(this.process.getRawProcess().getInputStream());
/* 21 */     BufferedReader buf = new BufferedReader(reader);
/* 22 */     String line = null;
/*    */ 
/* 24 */     while (this.process.isRunning()) {
/*    */       try {
/* 26 */         while ((line = buf.readLine()) != null) {
/* 27 */           System.out.println("Client> " + line);
/*    */         }
/*    */       } catch (IOException ex) {
/* 31 */         Logger.getLogger(ProcessMonitorThread.class.getName()).log(Level.SEVERE, null, ex);
/*    */       } finally {
/*    */         try {
/* 34 */           buf.close();
/*    */         } catch (IOException ex) {
/* 36 */           Logger.getLogger(ProcessMonitorThread.class.getName()).log(Level.SEVERE, null, ex);
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 41 */     JavaProcessRunnable onExit = this.process.getExitRunnable();
/*    */ 
/* 43 */     if (onExit != null)
/* 44 */       onExit.onJavaProcessEnded(this.process);
/*    */   }
/*    */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.process.ProcessMonitorThread
 * JD-Core Version:    0.6.2
 */