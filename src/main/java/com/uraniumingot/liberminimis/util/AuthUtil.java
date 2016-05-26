package com.uraniumingot.liberminimis.util;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lib.Settings;
import com.uraniumingot.liberminimis.ui.UILogin;

public class AuthUtil 
{
	public static boolean authUserCompleted()
	{
		if(!Settings.NEED_PASSWORD_ON_ACTION || !LiberMinimis.getLoginTimer().needLogin())
			return true;
		
		UILogin loginScreen = new UILogin(true);
		if(loginScreen.getLoginStatus())
		{
			LiberMinimis.getLoginTimer().disableLogin();
			return true;
		}
		
		return false;
	}

}
