package com.uraniumingot.liberminimis.ui.node;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

//BAR stands for borrow and return
public class NodeBARMenu extends TabPane
{
	private final Tab[] tabs = new Tab[]
			{
					new Tab(LanguageMap.translate("main.borrowreturn.borrow.tab")),
					new Tab(LanguageMap.translate("main.borrowreturn.return.tab"))
			};
	
	public NodeBARMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(120.0);
		
		for (Tab t : tabs)
			t.setClosable(false);
		
		this.getTabs().addAll(tabs);
	}
	
}
