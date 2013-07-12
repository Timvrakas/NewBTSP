/*    */ package net.minecraft.launcher.authentication.server;
/*    */ 
/*    */ public class AuthenticationRequest
/*    */ {
/*    */   
/*    */   @SuppressWarnings("unused")
			private String username;
/*    */   @SuppressWarnings("unused")
			private String password;
/*    */   @SuppressWarnings("unused")
			private String clientToken;
/*    */ 
/*    */   public AuthenticationRequest(String name, String password, String uuid)
/*    */   {
/* 11 */     this.username = name;
/* 12 */     this.clientToken = uuid;
/* 13 */     this.password = password;
/*    */   }
/*    */ }

