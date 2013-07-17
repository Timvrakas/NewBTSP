package net.btsp;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

import net.minecraft.launcher.Http;
import net.minecraft.launcher.authentication.server.InvalidateRequest;
import net.minecraft.launcher.authentication.server.RefreshRequest;
import net.minecraft.launcher.authentication.server.RefreshResponse;
import net.minecraft.launcher.authentication.server.Response;
import net.minecraft.launcher.authentication.server.AuthenticationRequest;
import net.minecraft.launcher.authentication.server.AuthenticationResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class Auth {
	private final Gson gson = new Gson();
	private String accessToken = null;
	private static UUID uuid = null;
	private static final String BASE_URL = "https://authserver.mojang.com/";
	public boolean loggedIn = false;
	
	public void login(String name, String pass){
		
		System.out.println("Logging in with username & password");
		
		     AuthenticationRequest request = new AuthenticationRequest(name, pass, uuid.toString());
		    System.out.println(uuid.toString());
		     try {
				AuthenticationResponse response = makeRequest(new URL(BASE_URL+"authenticate"), request, AuthenticationResponse.class);
				System.out.println("Logged In with Credentials and recived: "+response.getAccessToken());
				accessToken = response.getAccessToken();
				loggedIn = true;
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
	}
	
	public void refresh(){
		    System.out.println("Logging in with access token");
		   try {
		      RefreshRequest request = new RefreshRequest(accessToken, uuid.toString());
			  RefreshResponse response = (RefreshResponse)makeRequest(new URL(BASE_URL+"refresh"), request, RefreshResponse.class);
		      if (!response.getClientToken().equals(uuid.toString()))
		    	  throw new Exception("Server requested we change our client token. Don't know how to handle this!");
		      this.accessToken = response.getAccessToken();
		      loggedIn = true;
		      System.out.println("Refreshed Token and recived: "+response.getAccessToken());
		} catch (Exception e) {
				e.printStackTrace();
			} 
		  }
	
	public void logout(){
		System.out.println("Logging out");
			  try {
				  InvalidateRequest request = new InvalidateRequest(accessToken, uuid.toString());
				makeRequest(new URL(BASE_URL+"invalidate"), request, Response.class);
				loggedIn = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void loadFromStorage(Properties prop){
		String uuidString = prop.getProperty("UUID");
		if(uuidString != null)
			uuid = UUID.fromString(uuidString);
		else
			uuid = UUID.randomUUID();
		accessToken = prop.getProperty("Token");
		
	}
	
 	protected <T extends Response> T makeRequest(URL url, Object input, Class<T> classOfT) throws Exception {
		     try {
		       String jsonResult = Http.performPost(url, this.gson.toJson(input),"application/json", true);
		       T result = this.gson.fromJson(jsonResult, classOfT);
		
		      if (result == null) return null;
		 
		       if (StringUtils.isNotBlank(result.getError())) {
		          throw new Exception(result.getErrorMessage());
		       }
		       return result;
		     } catch (IOException e) {
		       throw new Exception("Cannot contact authentication server", e);
		     }
		  }
	
 	
 	
}
