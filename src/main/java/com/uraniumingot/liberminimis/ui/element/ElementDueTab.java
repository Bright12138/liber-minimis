package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.search.SearchType;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ElementDueTab extends VBox
{
	private final TableView<String> table = new TableView<String>();
	private final ElementSearchBar searchBar = new ElementSearchBar(SearchType.TypeBookID, SearchType.TypeBookName, SearchType.TypeUserID, SearchType.TypeUserName, SearchType.TypeLendAddedTime, SearchType.TypeLendReturnTime)
	{
		@Override
		public void onSearch(String text, SearchType type)
		{
			
		}
	};
	
	public ElementDueTab()
	{
		TableColumn<String, String> uNameCol = new TableColumn<String, String>(LanguageMap.translate("users.name"));
		TableColumn<String, String> uIdCol = new TableColumn<String, String>(LanguageMap.translate("users.id"));
		TableColumn<String, String> bNameCol = new TableColumn<String, String>(LanguageMap.translate("books.name"));
		TableColumn<String, String> bIdCol = new TableColumn<String, String>(LanguageMap.translate("books.id"));
		TableColumn<String, String> addedCol = new TableColumn<String, String>(LanguageMap.translate("lend.addeddate"));
		TableColumn<String, String> returnCol = new TableColumn<String, String>(LanguageMap.translate("lend.returndate"));
		
		table.getColumns().add(uNameCol);
		table.getColumns().add(uIdCol);
		table.getColumns().add(bNameCol);
		table.getColumns().add(bIdCol);
		table.getColumns().add(addedCol);
		table.getColumns().add(returnCol);
		
		table.setEditable(false);
		table.setMinHeight(650);
		
		this.setSpacing(9);
		this.setPadding(new Insets(9));
		
		this.getChildren().addAll(searchBar, table);
		
	}
}
