package com.uraniumingot.liberminimis.ui;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIMain extends Stage
{
	private BorderPane borderpane;
	
	public UIMain()
	{
		this.setTitle(LanguageMap.translate("main.title"));
		
		borderpane = new BorderPane();
		
		VBox leftmenu = new VBox();
		HBox topmenu = new HBox();
		
		leftmenu.setPadding(new Insets(9));
		topmenu.setPadding(new Insets(9));
		
		borderpane.setLeft(leftmenu);
		
		
		this.setScene(new Scene(borderpane, 800, 600));
		this.show();
	}
}
