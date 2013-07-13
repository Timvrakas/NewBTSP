/*    */ package net.minecraft.launcher.process;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.launcher.OperatingSystem;
/*    */ 
/*    */ public class JavaProcessLauncher
/*    */ {
/*    */   private final String jvmPath;
/*    */   private final List<String> commands;
/*    */   private File directory;
/*    */ 
/*    */   public JavaProcessLauncher(String jvmPath, String[] commands)
/*    */   {
/* 17 */     if (jvmPath == null) jvmPath = OperatingSystem.getCurrentPlatform().getJavaDir();
/* 18 */     this.jvmPath = jvmPath;
/* 19 */     this.commands = new ArrayList(commands.length);
/* 20 */     addCommands(commands);
/*    */   }
/*    */ 
/*    */   public JavaProcess start() throws IOException {
/* 24 */     List full = getFullCommands();
/* 25 */     return new JavaProcess(full, new ProcessBuilder(full).directory(this.directory).redirectErrorStream(true).start());
/*    */   }
/*    */ 
/*    */   public List<String> getFullCommands() {
/* 29 */     List result = new ArrayList(this.commands);
/* 30 */     result.add(0, getJavaPath());
/* 31 */     return result;
/*    */   }
/*    */ 
/*    */   public List<String> getCommands() {
/* 35 */     return this.commands;
/*    */   }
/*    */ 
/*    */   public void addCommands(String[] commands) {
/* 39 */     this.commands.addAll(Arrays.asList(commands));
/*    */   }
/*    */ 
/*    */   public void addSplitCommands(String commands) {
/* 43 */     addCommands(commands.split(" "));
/*    */   }
/*    */ 
/*    */   public JavaProcessLauncher directory(File directory) {
/* 47 */     this.directory = directory;
/*    */ 
/* 49 */     return this;
/*    */   }
/*    */ 
/*    */   public File getDirectory() {
/* 53 */     return this.directory;
/*    */   }
/*    */ 
/*    */   protected String getJavaPath() {
/* 57 */     return this.jvmPath;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 62 */     return "JavaProcessLauncher[commands=" + this.commands + ", java=" + this.jvmPath + "]";
/*    */   }
/*    */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.process.JavaProcessLauncher
 * JD-Core Version:    0.6.2
 */