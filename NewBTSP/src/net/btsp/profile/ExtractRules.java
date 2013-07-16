/*    */ package net.btsp.profile;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ExtractRules
/*    */ {
/*  9 */   private List<String> exclude = new ArrayList<String>();
/*    */ 
/*    */   public ExtractRules() {
/*    */   }
/*    */ 
/*    */   public ExtractRules(String[] exclude) {
/* 15 */     if (exclude != null) Collections.addAll(this.exclude, exclude); 
/*    */   }
/*    */ 
/*    */   public List<String> getExcludes()
/*    */   {
/* 19 */     return this.exclude;
/*    */   }
/*    */ 
/*    */   public boolean shouldExtract(String path) {
/* 23 */     if (this.exclude != null) {
/* 24 */       for (String rule : this.exclude) {
/* 25 */         if (path.startsWith(rule)) return false;
/*    */       }
/*    */     }
/*    */ 
/* 29 */     return true;
/*    */   }
/*    */ }
