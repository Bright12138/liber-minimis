package com.uraniumingot.liberminimis.lang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lib.Reference;

public class LanguageMap
{
	private static Map<String, String> lang = parseLang(readLocalizationFile());
	

	
	private static ArrayList<String> readLocalizationFile()
	{
		LiberMinimis.log.info("Trying to load localization file");
		BufferedReader br;
		ArrayList<String> unprocessed = new ArrayList<String>();
		try 
		{
			
			br = new BufferedReader(new InputStreamReader(LanguageMap.class.getResourceAsStream(Reference.TRANSLATION_FILE_LOCATION), Reference.TRANSLATION_FILE_ENCODING));
			String line;
			do
			{
				line = br.readLine();
				if(line != null && line.substring(0, 1) != "#")
					unprocessed.add(line);
			}
			while(line != null);
			br.close();
		} 
		catch (Exception e)
		{
			LiberMinimis.log.error("Failed to fetch the localized language file!", e);
		}
		return unprocessed;
	}
	
	private static Map<String, String> parseLang(ArrayList<String> unprocessed)
	{
		LiberMinimis.log.info("Localization loaded, parsing");
		Map<String, String> mapping= new HashMap<String, String>();
		for (String line : unprocessed)
		{
			int sep = line.indexOf("=");
			if(sep != -1 && sep != 0 && sep != line.length())
			{
				String key = line.substring(0, sep);
				String local = line.substring(sep+1);
				mapping.put(key, local);
			}
		}
		return mapping;
	}
	
	public static String translate(String unlocalized)
	{
		String localized = lang.get(unlocalized);
		if(localized != null)
			return localized;
		LiberMinimis.log.error("Could not find localization for key: '" + unlocalized + "'. Using unlocalized text");
		return unlocalized;
	}
}
