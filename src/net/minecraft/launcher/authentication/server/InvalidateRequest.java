/*   */ package net.minecraft.launcher.authentication.server;
/*   */ 
/*   */ public class InvalidateRequest
/*   */ {
/*   */   @SuppressWarnings("unused")
			private String accessToken;
/*   */   @SuppressWarnings("unused")
			private String clientToken;
/*   */ 
/*   */   public InvalidateRequest(String accessToken, String clientToken)
/*   */   {
/* 8 */     this.accessToken = accessToken;
/* 9 */     this.clientToken = clientToken;
/*   */   }
/*   */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.authentication.yggdrasil.InvalidateRequest
 * JD-Core Version:    0.6.2
 */