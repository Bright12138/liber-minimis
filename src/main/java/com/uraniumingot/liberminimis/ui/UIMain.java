package com.uraniumingot.liberminimis.ui;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.ui.element.TabBARMenu;
import com.uraniumingot.liberminimis.ui.element.TabBookMenu;
import com.uraniumingot.liberminimis.ui.element.TabHistoryMenu;
import com.uraniumingot.liberminimis.ui.element.TabLeftMenu;
import com.uraniumingot.liberminimis.ui.element.TabMiscMenu;
import com.uraniumingot.liberminimis.ui.element.TabUserMenu;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UIMain extends Stage
{
	private BorderPane borderpane;
	
	public UIMain()
	{
		this.setTitle(LanguageMap.translate("main.title"));
		
		this.setOnCloseRequest(new EventHandler<WindowEvent>()
		{

			@Override
			public void handle(WindowEvent event) 
			{
				LiberMinimis.markForShutdown();
			}
		});
		
		borderpane = new BorderPane();
		
		borderpane.setLeft(new TabLeftMenu(this));
		
		switchBARMenu();
		this.setMinHeight(768);
		this.setMinWidth(1024);
		this.setScene(new Scene(borderpane, 1024, 768));
		this.show();
	}
	
	public void switchBARMenu()
	{
		borderpane.setCenter(new TabBARMenu());
	}
	
	public void switchUserMenu()
	{
		borderpane.setCenter(new TabUserMenu());
	}
	
	public void switchBookMenu()
	{
		borderpane.setCenter(new TabBookMenu());
	}
	
	public void switchHistoryMenu()
	{
		borderpane.setCenter(new TabHistoryMenu());
	}
	
	public void switchOtherMenu()
	{
		borderpane.setCenter(new TabMiscMenu());
	}
}
