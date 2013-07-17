import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
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
		File w = new File("/Users/timv/Downloads/");
		//auth.login("ben1234neb", "ben5x62fb");
		//auth.refresh();
		
		
		//JavaProcessLauncher j = new JavaProcessLauncher(null, args);
		//.directory(new File("/Users/timv/desktop/test/"));
		//JavaProcess jp = j.start();
		
		
		
		
		
		Long time = System.currentTimeMillis();
		Map<String, List<String>> m = GetVersions.getVersions(new File(w,"json2"));
		System.out.print("Time:");
		System.out.println(System.currentTimeMillis()-time);
		List<String> p = m.get("1.6.2");
		String path = "minecraftforge-universal-1.6.2-"+p.get(0)+".jar";
		
		Set<Downloadable> files = new HashSet<Downloadable>();
		files.add(new Downloadable(new URL("http://files.minecraftforge.net/"+path), new File(w,path), true));
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
		
		
		/*Set<Downloadable> files = ResourcesDownload.getResourceFiles(new File("/users/timv/desktop"));
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
