package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.search.SearchType;

import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ElementOnLendMenu extends VBox
{
	private final TableView<String> table = new TableView<String>();
	private final ElementSearchBar searchBar = new ElementSearchBar(SearchType.TypeBookID, SearchType.TypeBookName, SearchType.TypeUserID, SearchType.TypeUserName, SearchType.TypeLendAddedTime, SearchType.TypeLendReturnTime)
	{
		@Override
		public void onSearch(String text, SearchType type)
		{
			
		}
	};
	
	public ElementOnLendMenu()
	{
		
	}
	
	
	
}
