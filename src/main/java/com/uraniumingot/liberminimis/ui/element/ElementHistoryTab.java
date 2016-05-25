package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.database.EnumHistoryType;
import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.search.SearchType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ElementHistoryTab extends VBox
{
	private final TableView<String> table = new TableView<String>();
	private final ComboBox<EnumHistoryType> historyType = new ComboBox<EnumHistoryType>();
	private final ElementSearchBar searchBar = new ElementSearchBar(SearchType.TypeBookID, SearchType.TypeBookName, SearchType.TypeBookComment, SearchType.TypeBookAddedTime)
			{
		@Override
		public void onSearch(String text, SearchType type)
		{
			
		}
		
		@Override
		protected void addToBar()
		{
			historyType.setPromptText(LanguageMap.translate("historytype.prompt.name"));
			historyType.getItems().addAll(EnumHistoryType.Borrow, EnumHistoryType.Return, EnumHistoryType.Renew, EnumHistoryType.ReturnDue, EnumHistoryType.LostOrDamaged);
			this.getChildren().addAll(searchField, searchType, historyType, searchButton);
		}
		
		@Override
		protected void setMinWidth()
		{
			searchField.setMinWidth(565);
		}
			};
	
	public ElementHistoryTab()
	{
		
		TableColumn<String, String> bidcol = new TableColumn<>(LanguageMap.translate("books.id"));
		TableColumn<String, String> bnamecol = new TableColumn<>(LanguageMap.translate("books.name"));
		TableColumn<String, String> uidcol = new TableColumn<>(LanguageMap.translate("users.id"));
		TableColumn<String, String> unamecol = new TableColumn<>(LanguageMap.translate("users.name"));
		TableColumn<String, String> addedcol = new TableColumn<>(LanguageMap.translate("books.added"));
		TableColumn<String, String> typecol = new TableColumn<>(LanguageMap.translate("history.type"));
		
		table.setEditable(false);
		table.setMinHeight(660);
		
		table.getColumns().add(bidcol);
		table.getColumns().add(bnamecol);
		table.getColumns().add(uidcol);
		table.getColumns().add(unamecol);
		table.getColumns().add(addedcol);
		table.getColumns().add(typecol);
		
		//HBox filter
		HBox hbf = new HBox();
		hbf.setSpacing(3);
		//hbf.getChildren().addAll(nameField, commentField, addUserButton);
		
		this.setSpacing(4);
		this.setPadding(new Insets(9));
		
		this.getChildren().addAll(searchBar, hbf, table);
	}
	
	public EventHandler<ActionEvent> getAddUserEvent()
	{
		return new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				//handleAddUser();
			}
		};
	}
}
