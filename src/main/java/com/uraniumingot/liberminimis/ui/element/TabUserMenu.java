package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabUserMenu extends TabPane
{
	private final Tab[] tabs = new Tab[]
			{
					new Tab(LanguageMap.translate("main.viewuser.all.tab")),
					new Tab(LanguageMap.translate("main.viewuser.withbook.tab")),
					new Tab(LanguageMap.translate("main.viewuser.duebook.tab"))
			};
	
	public TabUserMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(120.0);
		
		for (Tab t : tabs)
			t.setClosable(false);
		
		tabs[0].setContent(new ElementViewAllUserMenu());
		
		this.getTabs().addAll(tabs);
	}
	
}
