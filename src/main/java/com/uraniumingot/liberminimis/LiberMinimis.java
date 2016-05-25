package com.uraniumingot.liberminimis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uraniumingot.liberminimis.configuration.ConfigHandler;
import com.uraniumingot.liberminimis.database.SQLDatabase;
import com.uraniumingot.liberminimis.lib.Reference;
import com.uraniumingot.liberminimis.lib.Settings;
import com.uraniumingot.liberminimis.ui.UILogin;
import com.uraniumingot.liberminimis.ui.UIRegister;
import com.uraniumingot.liberminimis.util.EncryptUtil;
import com.uraniumingot.liberminimis.util.SystemUtil;

import javafx.application.Application;
import javafx.stage.Stage;

public class LiberMinimis extends Application
{
	public static final Logger log = LogManager.getLogger(Reference.NAME);

	
	public static void main(String[] args)
	{
		Thread.currentThread().setName("Liber Minimis Main Thread");
		SystemUtil.initDataFolder();
		ConfigHandler.init();
		Settings.init();
		SQLDatabase.init();
		launch();
	}
	
	public static void markForShutdown()
	{
		ConfigHandler.markStop();
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
}
