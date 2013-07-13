/*    */ package net.minecraft.launcher.process;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class JavaProcess
/*    */ {
/*    */   private static final int MAX_SYSOUT_LINES = 5;
/*    */   private final List<String> commands;
/*    */   private final Process process;
/* 10 */   private final LimitedCapacityList<String> sysOutLines = new LimitedCapacityList(String.class, 5);
/*    */   private JavaProcessRunnable onExit;
/* 12 */   private ProcessMonitorThread monitor = new ProcessMonitorThread(this);
/*    */ 
/*    */   public JavaProcess(List<String> commands, Process process) {
/* 15 */     this.commands = commands;
/* 16 */     this.process = process;
/*    */ 
/* 18 */     this.monitor.start();
/*    */   }
/*    */ 
/*    */   public Process getRawProcess() {
/* 22 */     return this.process;
/*    */   }
/*    */ 
/*    */   public List<String> getStartupCommands() {
/* 26 */     return this.commands;
/*    */   }
/*    */ 
/*    */   public String getStartupCommand() {
/* 30 */     return this.process.toString();
/*    */   }
/*    */ 
/*    */   public LimitedCapacityList<String> getSysOutLines() {
/* 34 */     return this.sysOutLines;
/*    */   }
/*    */ 
/*    */   public boolean isRunning() {
/*    */     try {
/* 39 */       this.process.exitValue();
/*    */     } catch (IllegalThreadStateException ex) {
/* 41 */       return true;
/*    */     }
/*    */ 
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   public void setExitRunnable(JavaProcessRunnable runnable) {
/* 48 */     this.onExit = runnable;
/*    */   }
/*    */ 
/*    */   public void safeSetExitRunnable(JavaProcessRunnable runnable) {
/* 52 */     setExitRunnable(runnable);
/*    */ 
/* 54 */     if ((!isRunning()) && 
/* 55 */       (runnable != null))
/* 56 */       runnable.onJavaProcessEnded(this);
/*    */   }
/*    */ 
/*    */   public JavaProcessRunnable getExitRunnable()
/*    */   {
/* 62 */     return this.onExit;
/*    */   }
/*    */ 
/*    */   public int getExitCode() {
/*    */     try {
/* 67 */       return this.process.exitValue();
/*    */     } catch (IllegalThreadStateException ex) {
/* 69 */       ex.fillInStackTrace();
/* 70 */       throw ex;
/*    */     }
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 76 */     return "JavaProcess[commands=" + this.commands + ", isRunning=" + isRunning() + "]";
/*    */   }
/*    */ 
/*    */   public void stop() {
/* 80 */     this.process.destroy();
/*    */   }
/*    */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.process.JavaProcess
 * JD-Core Version:    0.6.2
 */