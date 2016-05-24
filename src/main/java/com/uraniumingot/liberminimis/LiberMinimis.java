package com.uraniumingot.liberminimis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uraniumingot.liberminimis.configuration.ConfigHandler;
import com.uraniumingot.liberminimis.configuration.ConfigUpdateThread;
import com.uraniumingot.liberminimis.database.SQLDatabase;
import com.uraniumingot.liberminimis.lib.Reference;
import com.uraniumingot.liberminimis.ui.UILogin;
import com.uraniumingot.liberminimis.ui.UIRegister;
import com.uraniumingot.liberminimis.util.EncryptUtil;
import com.uraniumingot.liberminimis.util.SystemUtil;

import javafx.application.Application;
import javafx.stage.Stage;

public class LiberMinimis extends Application
{
	public static final Logger log = LogManager.getLogger(Reference.NAME);
	private static final ConfigUpdateThread cut = new ConfigUpdateThread();
	
	public static void main(String[] args)
	{
		Thread.currentThread().setName("Liber Minimis Main Thread");
		SystemUtil.initDataFolder();
		ConfigHandler.init();
		SQLDatabase.init();
		cut.setName("Configuration Update Thread");
		cut.start();
		launch();
	}
	
	public static void markForShutdown()
	{
		cut.markStop();
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
