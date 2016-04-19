package com.uraniumingot.liberminimis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uraniumingot.liberminimis.lib.Reference;
import com.uraniumingot.liberminimis.ui.UILogin;
import com.uraniumingot.liberminimis.ui.UIRegister;
import com.uraniumingot.liberminimis.util.EncryptUtil;

import javafx.application.Application;
import javafx.stage.Stage;

public class LiberMinimis extends Application
{
	public static final Logger log = LogManager.getLogger(Reference.NAME);
	
	
	public static void main(String[] args)
	{
		launch();
	}
	
	@Override
	public void start(Stage primary)
	{
		if(EncryptUtil.hasPassword())
			new UILogin();
		else
			new UIRegister();
	}
}
