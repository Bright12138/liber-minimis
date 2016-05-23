package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.search.SearchType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ElementDamagedTab extends VBox
{
	private final TableView<String> table = new TableView<String>();
	private final ElementSearchBar searchBar = new ElementSearchBar(SearchType.TypeBookID, SearchType.TypeBookName, SearchType.TypeUserID, SearchType.TypeUserName, SearchType.TypeLendAddedTime, SearchType.TypeLendReturnTime)
	{
		@Override
		public void onSearch(String text, SearchType type)
		{
			
		}
	};
	
	public ElementDamagedTab()
	{
		TableColumn<String, String> bNameCol = new TableColumn<String, String>(LanguageMap.translate("books.name"));
		TableColumn<String, String> bIdCol = new TableColumn<String, String>(LanguageMap.translate("books.id"));
		TableColumn<String, String> uNameCol = new TableColumn<String, String>(LanguageMap.translate("users.name"));
		TableColumn<String, String> uIdCol = new TableColumn<String, String>(LanguageMap.translate("users.id"));
		TableColumn<String, String> addedCol = new TableColumn<String, String>(LanguageMap.translate("lend.addeddate"));
		TableColumn<String, String> removeCol = new TableColumn<String, String>(LanguageMap.translate("lend.remove"));
		
		removeCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		
		Callback<TableColumn<String, String>, TableCell<String, String>> cellFactory = new Callback<TableColumn<String, String>, TableCell<String, String>>()
		{

			@Override
			public TableCell<String, String> call(TableColumn<String, String> arg) 
			{
				return new TableCell<String, String>()
				{
					Button delbtn = new Button(LanguageMap.translate("del.btn"));
					
					@Override
					public void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						if(empty)
						{
							setGraphic(null);
							setText(null);
						}
						else
						{
							delbtn.setOnAction( new EventHandler<ActionEvent>()
							{
								@Override
								public void handle(ActionEvent event)
								{
									
								}
							});
							setGraphic(delbtn);
							setText(null);
						}
					}
				};
			}
			
		};
		
		removeCol.setCellFactory(cellFactory);
		
		table.getColumns().add(bNameCol);
		table.getColumns().add(bIdCol);
		table.getColumns().add(uNameCol);
		table.getColumns().add(uIdCol);
		table.getColumns().add(addedCol);
		table.getColumns().add(removeCol);
		
		table.setEditable(false);
		table.setMinHeight(650);
		
		this.setSpacing(9);
		this.setPadding(new Insets(9));
		
		this.getChildren().addAll(searchBar, table);
		
	}
}
