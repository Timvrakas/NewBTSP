/*    */ package net.btsp.profile;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public enum ReleaseType
/*    */ {
/*  7 */   SNAPSHOT("snapshot"), RELEASE("release");
/*    */ 
/*    */   private static final Map<String, ReleaseType> lookup;
/*    */   private final String name;
/*    */ 
/*    */   private ReleaseType(String name)
/*    */   {
/* 19 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */ 
/*    */   public static ReleaseType getByName(String name) {
/* 27 */     return (ReleaseType)lookup.get(name);
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/*  9 */     lookup = new HashMap<String, ReleaseType>();
/*    */ 
/* 13 */     for (ReleaseType type : values())
/* 14 */       lookup.put(type.getName(), type);
/*    */   }
/*    */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.versions.ReleaseType
 * JD-Core Version:    0.6.2
 */