import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.btsp.Auth;
import net.btsp.ResourcesDownload;
import net.minecraft.launcher.process.JavaProcess;
import net.minecraft.launcher.process.JavaProcessLauncher;
import net.minecraft.launcher.updater.download.DownloadJob;
import net.minecraft.launcher.updater.download.Downloadable;


public class start {

	static Auth auth = new Auth();
	public static void main(String args[]) throws MalformedURLException, IOException, InterruptedException{
		//auth.login("ben1234neb", "ben5x62fb");
		auth.refresh();
		
		//hi tim
		JavaProcessLauncher j = new JavaProcessLauncher(null, args);
		j.directory(new File("/Users/timv/desktop/test/"));
		JavaProcess jp = j.start();
		
		
		
		
		
		//Set<Downloadable> files = ResourcesDownload.getResourceFiles(new File("/users/timv/desktop"));
				//DownloadJob j = new DownloadJob("Name", false, files);
		//ThreadPoolExecutor t = new ThreadPoolExecutor(8, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		//j.startDownloading(t);
		//for ( ; ; ) {
			//Thread.sleep(1000);
			//System.out.println(j.getProgress());
			//if(j.isComplete()){
				//System.out.println("done");
				//lt.shutdown();
				//break;
			//}
		//}
		//System.out.println(Http.performGet(new URL("https://s3.amazonaws.com/Minecraft.Download/" + "versions/1.6.2.jar")));
	}
	
	
	
}
