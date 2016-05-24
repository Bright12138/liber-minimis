package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;

public class TabMiscMenu extends TabPane
{
	private final Tab[] tabs = new Tab[]
			{
					new Tab(LanguageMap.translate("main.other.setting.tab")),
					new Tab(LanguageMap.translate("main.other.export.tab")),
					new Tab(LanguageMap.translate("main.other.changepassword.tab")),
					new Tab(LanguageMap.translate("main.other.info.tab"))
			};
	
	public TabMiscMenu()
	{
		super();
		
		this.setTabMinHeight(50.0);
		this.setTabMinWidth(120.0);
		
		for(Tab t : tabs)
			t.setClosable(false);
//		tabs[0].setContent();
		tabs[1].setContent(new Text("This feature is yet to be implemented"));
		tabs[2].setContent(new ElementChangePasswordTab());
		tabs[3].setContent(new ElementInfoTab());
		
		this.getTabs().addAll(tabs);
	}
}
