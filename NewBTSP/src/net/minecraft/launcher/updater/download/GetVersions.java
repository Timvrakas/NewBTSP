package net.minecraft.launcher.updater.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class GetVersions {
	
public class Promos {
	public String build;
	public Map <String, Map <String, Object>> files;
		
	}

	static Gson gson = new Gson();
	
	public static Map<String,List<String>> getVersions(File file) throws IOException{
			Map<String,List<String>> result = new HashMap<String, List<String>>();
			InputStream in = new FileInputStream(file);
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
	        JsonParser parser = new JsonParser();
	        reader.beginObject();
	        while(reader.hasNext()){
	        	if(reader.peek()==JsonToken.NAME){
	        		if(reader.nextName().equalsIgnoreCase("mcversion")){
	        			break;
	        		}
	        	}else
	        		reader.skipValue();
	        	}
	        
	        Type typeOfHashMap = new TypeToken<Map<String,Map<Integer,Map<String,Object>>>>() { }.getType();
	        Map<String,Map<Integer,Map<String,String>>> mcvers = gson.fromJson(parser.parse(reader),typeOfHashMap);
			
				for(Entry<String, Map<Integer, Map<String, String>>> mcversEntry : mcvers.entrySet()){
					Map<Integer, Map<String, String>> mcver = mcvers.get(mcversEntry.getKey());
					List<String> builds = new ArrayList<String>();
					
						for(Entry<Integer, Map<String, String>> entry : mcver.entrySet()){
							builds.add(entry.getValue().get("version"));
							}
						Collections.reverse(builds);
						result.put(mcversEntry.getKey(),builds);
						}
				
				while(reader.hasNext()){
		        	if(reader.peek()==JsonToken.NAME){
		        		String s = reader.nextName();
		        		if(s.equalsIgnoreCase("promos")){
		        			break;
		        		}
		        	}else
		        		reader.skipValue();
		        	}
				
				
				typeOfHashMap = new TypeToken<Map<String,Promos>>() { }.getType();
		        Map<String,Promos> promos = gson.fromJson(parser.parse(reader),typeOfHashMap);
				
		        for(Entry<String, Promos> promosEntry : promos.entrySet()){
					if(promosEntry.getKey().startsWith("recommended")){
						Map<String, Map<String, Object>> promo = promosEntry.getValue().files;
						String recomendedbuild = promo.get("src").get("version")+" ("+promosEntry.getKey()+")";
						String mcVersion = (String) promo.get("src").get("mcversion");
						List<String>  list = result.get(mcVersion);
						list.add(0, recomendedbuild);
						result.put(mcVersion, list);
					}
		        }
				return result;		
				}
			
}
