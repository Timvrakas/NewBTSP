/*    */ package net.minecraft.launcher.authentication.server;
/*    */ 

/*    */ 
/*    */ public class RefreshRequest
/*    */ {
/*    */   @SuppressWarnings("unused")
			private String clientToken;
/*    */   @SuppressWarnings("unused")
			private String accessToken;

/*    */ 

/*    */ 
/*    */   public RefreshRequest(String accessToken, String clientToken) {
/* 15 */     this.accessToken = accessToken;
/* 9 */     this.clientToken = clientToken;
/*    */   }
/*    */ }

