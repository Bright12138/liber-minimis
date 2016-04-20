package com.uraniumingot.liberminimis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lib.FileSuffix;

public class IOUtil 
{
	
	public static Object readFile(File file, Object def)
	{
		Object obj = null;
		File bak = new File(file.getAbsolutePath() + FileSuffix.BACKUP);
		if(file.exists())
		{
			try
			{
				obj = readObject(file);
			}
			catch (Exception e1)
			{
				if(bak.exists())
				{
					LiberMinimis.log.warn("An error occured while loading file: " + file.getAbsolutePath() + ", using backup", e1);
					try
					{
						obj = readObject(bak);
						file.delete();
						writeObject(file, obj);
					}
					catch (Exception e2)
					{
						LiberMinimis.log.error("Failed to load the backup file!", e2);
					}
				}
				else
				{
					LiberMinimis.log.error("Failed to load file: " + file.getAbsolutePath(), e1);
				}
			}
		}
		else if(bak.exists())
		{
			LiberMinimis.log.warn("An error occured while loading file: " + file.getAbsolutePath() + ", using backup");
			try
			{
				obj = readObject(bak);
				file.delete();
				writeObject(file, obj);
			}
			catch (Exception e2)
			{
				LiberMinimis.log.error("Failed to load the backup file!", e2);
			}
		}
		if(obj != null)
			return obj;
		return def;
	}
	
	public static void writeFile(File file, Object obj)
	{
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		File bak = new File(file.getAbsolutePath() + FileSuffix.BACKUP);
		try 
		{
			writeObject(file, obj);
		} 
		catch (ClassNotFoundException | IOException e)
		{
			LiberMinimis.log.error("Failed to save data to file!", e);
		}
		try 
		{
			writeObject(bak, obj);
		} 
		catch (ClassNotFoundException | IOException e)
		{
			LiberMinimis.log.error("Failed to save data to backup file!", e);
		}
	}
		
	private static Object readObject(File file) throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}
	
	private static void writeObject(File file, Object obj) throws IOException, ClassNotFoundException
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, false));
		oos.writeObject(obj);
		oos.close();
	}
	
	
}
