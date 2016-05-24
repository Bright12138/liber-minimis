package com.uraniumingot.liberminimis.configuration;

import java.util.concurrent.TimeUnit;

import com.uraniumingot.liberminimis.LiberMinimis;

public class ConfigUpdateThread extends Thread
{
	private boolean shouldContinue = true;
	
	public void markStop()
	{
		shouldContinue = false;
	}
	
	@Override
	public void run() 
	{
		LiberMinimis.log.info("Starting config update thread");
		try 
		{
			while(shouldContinue)
			{
				if(ConfigHandler.shouldUpdate())
					ConfigHandler.updateToFile();
				
				TimeUnit.MILLISECONDS.sleep(500);
			}
		} 
		catch (InterruptedException e) {}
	}

}
