package net.btsp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import net.minecraft.launcher.Http;
import net.minecraft.launcher.authentication.server.Response;
import net.minecraft.launcher.authentication.server.AuthenticationRequest;
import net.minecraft.launcher.authentication.server.AuthenticationResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class Auth {
	private final Gson gson = new Gson();
	private String accessToken = null;
	private static UUID uuid = UUID.randomUUID();
	private static final String BASE_URL = "https://authserver.mojang.com/";
	
	public void Login(String name, String pass){
		
		System.out.println("Logging in with username & password");
		
		     AuthenticationRequest request = new AuthenticationRequest(name, pass, uuid.toString());
		    
		     try {
				AuthenticationResponse response = makeRequest(new URL(BASE_URL+"authenticate"), request, AuthenticationResponse.class);
				System.out.println(response.getAccessToken());
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
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
