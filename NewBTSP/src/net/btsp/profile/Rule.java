/*    */ package net.btsp.profile;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import net.minecraft.launcher.OperatingSystem;
/*    */ 
/*    */ public class Rule
/*    */ {
/* 42 */   private Action action = Action.ALLOW;
/*    */   private OSRestriction os;
/*    */ 
/*    */   public Action getAppliedAction()
/*    */   {
/* 46 */     if ((this.os != null) && (!this.os.isCurrentOperatingSystem())) return null;
/*    */ 
/* 48 */     return this.action;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 53 */     return "Rule{action=" + this.action + ", os=" + this.os + '}';
/*    */   }
/*    */ 
/*    */   public static enum Action
/*    */   {
/* 10 */     ALLOW, 
/* 11 */     DISALLOW;
/*    */   }
/*    */   public class OSRestriction {
/*    */     private OperatingSystem name;
/*    */     private String version;
/*    */ 
/*    */     public OSRestriction() {  } 
/* 19 */     public boolean isCurrentOperatingSystem() { if ((this.name != null) && (this.name != OperatingSystem.getCurrentPlatform())) return false;
/*    */ 
/* 21 */       if (this.version != null)
/*    */         try {
/* 23 */           Pattern pattern = Pattern.compile(this.version);
/* 24 */           Matcher matcher = pattern.matcher(System.getProperty("os.version"));
/* 25 */           if (!matcher.matches()) return false;
/*    */         }
/*    */         catch (Throwable localThrowable)
/*    */         {
/*    */         }
/* 30 */       return true;
/*    */     }
/*    */ 
/*    */     public String toString()
/*    */     {
/* 35 */       return "OSRestriction{name=" + this.name + ", version='" + this.version + '\'' + '}';
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.versions.Rule
 * JD-Core Version:    0.6.2
 */