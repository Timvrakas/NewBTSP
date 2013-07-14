/*    */ package net.minecraft.launcher;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.URI;
/*    */ 
/*    */ public enum OperatingSystem
/*    */ {
/*  7 */   LINUX("linux", new String[] { "linux", "unix" }), 
/*  8 */   WINDOWS("windows", new String[] { "win" }), 
/*  9 */   OSX("osx", new String[] { "mac" }), 
/* 10 */   UNKNOWN("unknown", new String[0]);
/*    */ 
/*    */   private final String name;
/*    */   private final String[] aliases;
/*    */ 
/* 16 */   private OperatingSystem(String name, String[] aliases) { this.name = name;
/* 17 */     this.aliases = (aliases == null ? new String[0] : aliases); }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 21 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String[] getAliases() {
/* 25 */     return this.aliases;
/*    */   }
/*    */ 
/*    */   public boolean isSupported() {
/* 29 */     return this != UNKNOWN;
/*    */   }
/*    */ 
/*    */   public String getJavaDir() {
/* 33 */     String separator = System.getProperty("file.separator");
/* 34 */     String path = System.getProperty("java.home") + separator + "bin" + separator;
/*    */ 
/* 36 */     if ((getCurrentPlatform() == WINDOWS) && 
/* 37 */       (new File(path + "javaw.exe").isFile())) {
/* 38 */       return path + "javaw.exe";
/*    */     }
/*    */ 
/* 42 */     return path + "java";
/*    */   }
/*    */ 
/*    */   public static OperatingSystem getCurrentPlatform() {
/* 46 */     String osName = System.getProperty("os.name").toLowerCase();
/*    */ 
/* 48 */     for (OperatingSystem os : values()) {
/* 49 */       for (String alias : os.getAliases()) {
/* 50 */         if (osName.contains(alias)) return os;
/*    */       }
/*    */     }
/*    */ 
/* 54 */     return UNKNOWN;
/*    */   }
/*    */ 
/*    */   public static void openLink(URI link) {
/*    */     try {
/* 59 */       Class<?> desktopClass = Class.forName("java.awt.Desktop");
/* 60 */       Object o = desktopClass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 61 */       desktopClass.getMethod("browse", new Class[] { URI.class }).invoke(o, new Object[] { link });
/*    */     } catch (Throwable e) {
/* 63 */       System.out.println("Failed to open link " + link.toString());
				e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.OperatingSystem
 * JD-Core Version:    0.6.2
 */