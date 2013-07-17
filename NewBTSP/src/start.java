import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.btsp.Auth;
import net.btsp.ResourcesDownload;
import net.minecraft.launcher.updater.download.DownloadJob;
import net.minecraft.launcher.updater.download.Downloadable;
import net.minecraft.launcher.updater.download.GetVersions;


public class start {

	static Auth auth = new Auth();
	public static void main(String args[]) throws MalformedURLException, IOException, InterruptedException{
		//auth.login("ben1234neb", "ben5x62fb");
		//auth.refresh();
		
		
		//JavaProcessLauncher j = new JavaProcessLauncher(null, args);
		//.directory(new File("/Users/timv/desktop/test/"));
		//JavaProcess jp = j.start();
		
		Long time = System.currentTimeMillis();
		Map<String, List<String>> m = GetVersions.getVersions(new File("/Users/timv/Downloads/json2"));
		System.out.println(System.currentTimeMillis()-time);
		
		/*
		Set<Downloadable> files = ResourcesDownload.getResourceFiles(new File("/users/timv/desktop"));
				DownloadJob j = new DownloadJob("Name", false, files);
		ThreadPoolExecutor t = new ThreadPoolExecutor(8, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		j.startDownloading(t);
		for ( ; ; ) {
			Thread.sleep(1000);
			System.out.println(j.getProgress());
			if(j.isComplete()){
				System.out.println("done");
				t.shutdown();
				break;
			}
		}
		*/
	}
	
	
	
}
