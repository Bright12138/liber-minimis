package com.uraniumingot.liberminimis.ui;

import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.ui.node.NodeBARMenu;
import com.uraniumingot.liberminimis.ui.node.NodeBookMenu;
import com.uraniumingot.liberminimis.ui.node.NodeHistoryMenu;
import com.uraniumingot.liberminimis.ui.node.NodeLeftMenu;
import com.uraniumingot.liberminimis.ui.node.NodeOtherMenu;
import com.uraniumingot.liberminimis.ui.node.NodeUserMenu;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIMain extends Stage
{
	private BorderPane borderpane;
	
	public UIMain()
	{
		this.setTitle(LanguageMap.translate("main.title"));
		
		borderpane = new BorderPane();
		
		borderpane.setLeft(new NodeLeftMenu(this));
		
		switchBARMenu();
		this.setMinHeight(768);
		this.setMinWidth(1024);
		this.setScene(new Scene(borderpane, 1024, 768));
		this.show();
	}
	
	public void switchBARMenu()
	{
		borderpane.setCenter(new NodeBARMenu());
	}
	
	public void switchUserMenu()
	{
		borderpane.setCenter(new NodeUserMenu());
	}
	
	public void switchBookMenu()
	{
		borderpane.setCenter(new NodeBookMenu());
	}
	
	public void switchHistoryMenu()
	{
		borderpane.setCenter(new NodeHistoryMenu());
	}
	
	public void switchOtherMenu()
	{
		borderpane.setCenter(new NodeOtherMenu());
	}
}
