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
					new Tab(LanguageMap.translate("main.borrowreturn.return.tab")),
					new Tab(LanguageMap.translate("main.borrowreturn.renew.tab")),
					new Tab(LanguageMap.translate("main.borrowreturn.reportdue.tab"))
			};
	
	public TabBARMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(120.0);
		
		for (Tab t : tabs)
			t.setClosable(false);
		
		tabs[0].setContent(new ElementBorrowTab());
		tabs[1].setContent(new ElementReturnTab());
		tabs[2].setContent(new ElementRenewTab());
		tabs[3].setContent(new ElementReportDamagedTab());
		
		this.getTabs().addAll(tabs);
	}
	
}
