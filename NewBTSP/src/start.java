import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.btsp.Auth;
import net.minecraft.launcher.Http;


public class start {

	static Auth auth = new Auth();
	public static void main(String args[]) throws MalformedURLException, IOException{
		auth.Login("ben1234neb", "ben5x62fb");
		System.out.println(Http.performGet(new URL("https://s3.amazonaws.com/Minecraft.Download/" + "versions/1.6.2.jar")));
	}
}
