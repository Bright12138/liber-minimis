package com.uraniumingot.liberminimis.ui.node;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class NodeUserMenu extends TabPane
{
	private final Tab[] tabs = new Tab[]
			{
					new Tab(LanguageMap.translate("main.viewuser.all.tab")),
					new Tab(LanguageMap.translate("main.viewuser.withbook.tab")),
					new Tab(LanguageMap.translate("main.viewuser.duebook.tab"))
			};
	
	public NodeUserMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(120.0);
		
		for (Tab t : tabs)
			t.setClosable(false);
		
		this.getTabs().addAll(tabs);
	}
	
}
