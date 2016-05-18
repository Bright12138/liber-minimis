package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabBookMenu extends TabPane
{
	private final Tab[] tabs = new Tab[]
			{
					new Tab(LanguageMap.translate("main.viewbook.all.tab")),
					new Tab(LanguageMap.translate("main.viewbook.damaged.tab")),
					new Tab(LanguageMap.translate("main.viewbook.due.tab"))
			};
	
	public TabBookMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(120.0);
		
		for(Tab t : tabs)
			t.setClosable(false);
		
		this.getTabs().addAll(tabs);
	}
}