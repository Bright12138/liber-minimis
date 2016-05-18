package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

//BAR stands for borrow and return
public class TabBARMenu extends TabPane
{
	private final Tab[] tabs = new Tab[]
			{
					new Tab(LanguageMap.translate("main.borrowreturn.borrow.tab")),
					new Tab(LanguageMap.translate("main.borrowreturn.return.tab"))
			};
	
	public TabBARMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(120.0);
		
		for (Tab t : tabs)
			t.setClosable(false);
		
		tabs[0].setContent(new NodeBorrowMenu());
		tabs[1].setContent(new NodeReturnMenu());
		
		this.getTabs().addAll(tabs);
	}
	
}
