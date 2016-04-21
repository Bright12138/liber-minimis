package com.uraniumingot.liberminimis.ui.node;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class NodeOtherMenu extends TabPane
{
	private final Tab[] tabs = new Tab[]
			{
					new Tab(LanguageMap.translate("main.other.setting.tab")),
					new Tab(LanguageMap.translate("main.other.export.tab")),
					new Tab(LanguageMap.translate("main.other.info.tab")),
					new Tab(LanguageMap.translate("main.other.changepassword.tab"))
			};
	
	public NodeOtherMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(120.0);
		
		for(Tab t : tabs)
			t.setClosable(false);
		
		this.getTabs().addAll(tabs);
	}
}
