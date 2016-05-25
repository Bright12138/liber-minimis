package com.uraniumingot.liberminimis.lib;

import com.uraniumingot.liberminimis.configuration.ConfigHandler;

public class Settings 
{
	public static boolean NEED_PASSWORD_ON_ACTION;
	public static int MINUTES_UNTIL_NEXT_PASSWORD;
	public static int LEND_DAY_COUNT;
	public static int RENEW_DAY_COUNT;
	
	public static void init()
	{
		NEED_PASSWORD_ON_ACTION = ConfigHandler.getBoolean("need_password_on_action", true);
		MINUTES_UNTIL_NEXT_PASSWORD = ConfigHandler.getInt("minutes_until_next_password", 5);
		LEND_DAY_COUNT = ConfigHandler.getInt("lend_day_count", 7);
		RENEW_DAY_COUNT = ConfigHandler.getInt("renew_day_count", 7);
	}
}
