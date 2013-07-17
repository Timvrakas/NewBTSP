package net.minecraft.launcher.updater.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import net.minecraft.launcher.Http;

public class GetVersions {
	static Gson gson = new Gson();
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException{
		
			Map<String,List<String>> result = null;
			URL url = new URL("http://files.minecraftforge.net/minecraftforge/json2");
			JsonReader reader = new JsonReader(new InputStreamReader(url.openStream(), "UTF-8"));
	        JsonParser parser = new JsonParser();
	        reader.beginObject();
	        System.out.println(reader.nextName());
	        System.out.println(reader.nextString());
	        System.out.println(reader.nextName());
	  
	        Type typeOfHashMap = new TypeToken<Map<String,Map<Integer,Map<String,Object>>>>() { }.getType();
	        Map<String,Map<Integer,Map<String,String>>> mcvers = gson.fromJson(parser.parse(reader),typeOfHashMap);
			if(true){
				for(Entry<String, Map<Integer, Map<String, String>>> mcversEntry : mcvers.entrySet()){
					Map<Integer, Map<String, String>> mcver = mcvers.get(mcversEntry.getKey());
					result.put(mcversEntry.getKey(),null);
						for(Entry<Integer, Map<String, String>> entry : mcver.entrySet()){
							System.out.println(entry.getValue().get("version")+"-"+mcversEntry.getKey());
							result.get(mcversEntry.getKey()).add(entry.getValue().get("version"));
					}
				}
			}
		
		return;
		
	}
	
	
}
