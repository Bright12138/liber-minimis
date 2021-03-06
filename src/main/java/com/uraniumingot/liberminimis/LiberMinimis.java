package com.uraniumingot.liberminimis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uraniumingot.liberminimis.configuration.ConfigHandler;
import com.uraniumingot.liberminimis.database.SQLDatabase;
import com.uraniumingot.liberminimis.lib.Reference;
import com.uraniumingot.liberminimis.lib.Settings;
import com.uraniumingot.liberminimis.login.LoginTimerThread;
import com.uraniumingot.liberminimis.ui.UILogin;
import com.uraniumingot.liberminimis.ui.UIRegister;
import com.uraniumingot.liberminimis.util.EncryptUtil;
import com.uraniumingot.liberminimis.util.SystemUtil;

import javafx.application.Application;
import javafx.stage.Stage;

public class LiberMinimis extends Application
{
	public static final Logger log = LogManager.getLogger(Reference.NAME);
	private static final LoginTimerThread timerThread = new LoginTimerThread();
	
	public static void main(String[] args)
	{
		Thread.currentThread().setName("Liber Minimis Main Thread");
		SystemUtil.initDataFolder();
		ConfigHandler.init();
		Settings.init();
		timerThread.start();
		SQLDatabase.init();
		launch();
	}
	
	@Override
	public void start(Stage primary)
	{
		log.info("Application Started");
		if(EncryptUtil.hasPassword())
			new UILogin();
		else
			new UIRegister();
	}
	
	public static LoginTimerThread getLoginTimer()
	{
		return timerThread;
	}
}
