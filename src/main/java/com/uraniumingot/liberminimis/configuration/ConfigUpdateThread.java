package com.uraniumingot.liberminimis.configuration;

import java.util.concurrent.TimeUnit;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lib.Settings;

public class ConfigUpdateThread extends Thread
{	
	public ConfigUpdateThread()
	{
		super();
		this.setDaemon(true);
		this.setName("Configuration Update Thread");
	}
	
	@Override
	public void run() 
	{
		LiberMinimis.log.info("Starting config update thread");
		try 
		{
			while(true)
			{
				if(ConfigHandler.shouldUpdate())
				{
					Settings.init();
					ConfigHandler.updateToFile();
				}
				
				TimeUnit.MILLISECONDS.sleep(500);
			}
		} 
		catch (InterruptedException e) {}
	}

}
