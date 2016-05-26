package com.uraniumingot.liberminimis.login;

import java.util.concurrent.TimeUnit;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lib.Settings;

public class LoginTimerThread extends Thread
{
	private boolean needLogin = true;
	
	public LoginTimerThread()
	{
		super();
		this.setDaemon(true);
		this.setName("Login Timer Thread");
	}
	
	@Override
	public void run()
	{
		LiberMinimis.log.info("Starting login timer thread");
		try
		{
			int timerMin;
			int timer;
			while(true)
			{
				timerMin = Settings.MINUTES_UNTIL_NEXT_PASSWORD;
				timer = timerMin * 60;
				while(Settings.NEED_PASSWORD_ON_ACTION && needLogin == false)
				{	
					if(timerMin != Settings.MINUTES_UNTIL_NEXT_PASSWORD)
					{
						timerMin = Settings.MINUTES_UNTIL_NEXT_PASSWORD;
						timer = timerMin * 60;
					}
					
					TimeUnit.SECONDS.sleep(1);
					timer--;
					
					if(timer < 1)
					{
						timer = timerMin * 60;
						needLogin = true;
					}
				}
				TimeUnit.SECONDS.sleep(1);
			}
		}
		catch(InterruptedException e){}
	}
	
	
	public boolean needLogin()
	{
		return needLogin;
	}
	
	public void disableLogin()
	{
		needLogin = false;
	}
}
