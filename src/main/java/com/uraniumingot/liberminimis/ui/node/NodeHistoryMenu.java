package com.uraniumingot.liberminimis.ui.node;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class NodeHistoryMenu extends TabPane
{
	private final Tab[] tabs = new Tab[]
			{
				new Tab(LanguageMap.translate("main.history.user.tab")),
				new Tab(LanguageMap.translate("main.history.alluser.tab")),
				new Tab(LanguageMap.translate("main.history.book.tab")),
				new Tab(LanguageMap.translate("main.history.allbook.tab")),
				new Tab(LanguageMap.translate("main.history.damaged.tab")),
				new Tab(LanguageMap.translate("main.history.alldamaged.tab"))
			};
	
	public NodeHistoryMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(100.0);
		
		for(Tab t : tabs)
			t.setClosable(false);
		
		this.getTabs().addAll(tabs);
	}
}
