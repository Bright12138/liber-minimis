package com.uraniumingot.liberminimis.configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lib.Reference;
import com.uraniumingot.liberminimis.lib.Settings;

public class ConfigHandler 
{
	private static Map<String, String> config;
	private static boolean shouldUpdateFile = false;
	
	private static final ConfigUpdateThread cut = new ConfigUpdateThread();
	
	public static void init()
	{
		config = parseConfig(readConfigFile());
		cut.setName("Configuration Update Thread");
		cut.start();
	}
	
	public static void markStop()
	{
		cut.markStop();
	}
	
	private static ArrayList<String> readConfigFile()
	{
		LiberMinimis.log.info("Trying to load configuration file");
		BufferedReader br;
		ArrayList<String> unprocessed = new ArrayList<String>();
		
		if(!Reference.CONFIG_FILE.getParentFile().exists())
			Reference.CONFIG_FILE.getParentFile().exists();
		
		if(!Reference.CONFIG_FILE.exists())
		{
			markFileUpdate();
			return unprocessed;
		}
		
		try 
		{
			FileInputStream fis = new FileInputStream(Reference.CONFIG_FILE);
			br = new BufferedReader(new InputStreamReader(fis, Reference.CONFIG_FILE_ENCODING));
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
		catch (IOException e)
		{
			LiberMinimis.log.error("Failed to fetch the settings file!", e);
		}
		return unprocessed;
	}
	
	private static Map<String, String> parseConfig(ArrayList<String> unprocessed)
	{
		LiberMinimis.log.info("Settings loaded, parsing");
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
	
	private static void markFileUpdate()
	{
		shouldUpdateFile = true;
		Settings.init();
	}
	
	public static boolean shouldUpdate()
	{
		boolean old = shouldUpdateFile;
		shouldUpdateFile = false;
		return old;
	}
	
	public static void updateToFile()
	{
		LiberMinimis.log.info("Writing configurations to save file");
		
		BufferedWriter bw;
		
		if(!Reference.CONFIG_FILE.getParentFile().exists())
			Reference.CONFIG_FILE.getParentFile().exists();
			
		try
		{
			FileOutputStream fos = new FileOutputStream(Reference.CONFIG_FILE, false);
			bw = new BufferedWriter(new OutputStreamWriter(fos, Reference.CONFIG_FILE_ENCODING));
			
			for (String key : config.keySet())
			{
				String value = config.get(key);
				bw.write(String.format("%s=%s", key, value));
				bw.newLine();
			}
			
			bw.close();
		}
		catch(IOException e)
		{
			LiberMinimis.log.error("Failed to save configuration to file!", e);
		}
	}
	
	public static boolean getBoolean(String key, boolean defaultValue)
	{
		String rawValue = config.get(key);
		boolean value = defaultValue;
		try
		{
			value = Boolean.valueOf(rawValue);
		}
		catch(Exception e)
		{
			config.put(key, String.valueOf(defaultValue));
			markFileUpdate();
		}
		return value;
	}
	
	public static int getInt(String key, int defaultValue)
	{
		String rawValue = config.get(key);
		int value = defaultValue;
		try
		{
			value = Integer.valueOf(rawValue);
		}
		catch(Exception e)
		{
			config.put(key, String.valueOf(defaultValue));
			markFileUpdate();
		}
		return value;
	}
	
	public static long getLong(String key, long defaultValue)
	{
		String rawValue = config.get(key);
		long value = defaultValue;
		try
		{
			value = Long.valueOf(rawValue);
		}
		catch(Exception e)
		{
			config.put(key, String.valueOf(defaultValue));
			markFileUpdate();
		}
		return value;
	}
	
	public static short getShort(String key, short defaultValue)
	{
		String rawValue = config.get(key);
		short value = defaultValue;
		try
		{
			value = Short.valueOf(rawValue);
		}
		catch(Exception e)
		{
			config.put(key, String.valueOf(defaultValue));
			markFileUpdate();
		}
		return value;
	}
	
	public static byte getByte(String key, byte defaultValue)
	{
		String rawValue = config.get(key);
		byte value = defaultValue;
		try
		{
			value = Byte.valueOf(rawValue);
		}
		catch(Exception e)
		{
			config.put(key, String.valueOf(defaultValue));
			markFileUpdate();
		}
		return value;
	}
	
	public static float getFloat(String key, float defaultValue)
	{
		String rawValue = config.get(key);
		float value = defaultValue;
		try
		{
			value = Short.valueOf(rawValue);
		}
		catch(Exception e)
		{
			config.put(key, String.valueOf(defaultValue));
			markFileUpdate();
		}
		return value;
	}
	
	public static String getString(String key, String defaultValue)
	{
		String rawValue = config.get(key);
		if(rawValue == "" || rawValue == null)
		{
			config.put(key, defaultValue);
			markFileUpdate();
			return defaultValue;
		}
		
		return rawValue;
	}
	
	public static void setBoolean(String key, boolean value)
	{
		config.put(key, String.valueOf(value));
		markFileUpdate();
	}
	
	public static void setInt(String key, int value)
	{
		config.put(key, String.valueOf(value));
		markFileUpdate();
	}
	
	public static void setLong(String key, long value)
	{
		config.put(key, String.valueOf(value));
		markFileUpdate();
	}
	
	public static void setShort(String key, short value)
	{
		config.put(key, String.valueOf(value));
		markFileUpdate();
	}
	
	public static void setByte(String key, byte value)
	{
		config.put(key, String.valueOf(value));
		markFileUpdate();
	}
	
	public static void setFloat(String key, float value)
	{
		config.put(key, String.valueOf(value));
		markFileUpdate();
	}
	
	public static void setString(String key, String value)
	{
		config.put(key, String.valueOf(value));
		markFileUpdate();
	}
}
