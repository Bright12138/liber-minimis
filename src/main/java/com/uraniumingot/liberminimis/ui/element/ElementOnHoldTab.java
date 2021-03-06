package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.search.SearchType;
import com.uraniumingot.liberminimis.util.DBUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ElementOnHoldTab extends VBox
{
	private final TableView<String> table = new TableView<String>();
	private final TextField nameField = new TextField();
	private final TextField commentField = new TextField();
	private final Button addUserButton = new Button(LanguageMap.translate("addbook.btn"));
	private final ElementSearchBar searchBar = new ElementSearchBar(SearchType.TypeBookID, SearchType.TypeBookName, SearchType.TypeBookComment, SearchType.TypeBookAddedTime)
			{
		@Override
		public void onSearch(String text, SearchType type)
		{
			
		}
			};
	
	public ElementOnHoldTab()
	{
		nameField.setPromptText(LanguageMap.translate("addbook.name"));
		commentField.setPromptText(LanguageMap.translate("addbook.comment"));
		commentField.setMinWidth(585);
		
		addUserButton.setOnAction(getAddUserEvent());
		
		TableColumn<String, String> idcol = new TableColumn<>(LanguageMap.translate("books.id"));
		TableColumn<String, String> namecol = new TableColumn<>(LanguageMap.translate("books.name"));
		TableColumn<String, String> addedcol = new TableColumn<>(LanguageMap.translate("books.added"));
		TableColumn<String, String> commentcol = new TableColumn<>(LanguageMap.translate("books.comment"));
		TableColumn<String, String> delcol = new TableColumn<>(LanguageMap.translate("book.onhold.remove"));
		
		delcol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		
		Callback<TableColumn<String, String>, TableCell<String, String>> cellFactory = new Callback<TableColumn<String, String>, TableCell<String, String>>()
		{

			@Override
			public TableCell<String, String> call(TableColumn<String, String> arg) 
			{
				return new TableCell<String, String>()
				{
					Button optbtn = new Button(LanguageMap.translate("book.onhold.btn"));
					
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
							optbtn.setOnAction( new EventHandler<ActionEvent>()
							{
								@Override
								public void handle(ActionEvent event)
								{
									
								}
							});
							setGraphic(optbtn);
							setText(null);
						}
					}
				};
			}
			
		};
		
		delcol.setCellFactory(cellFactory);
		
		table.setEditable(true);
		idcol.setEditable(false);
		addedcol.setEditable(false);
		table.setMinHeight(620);
		
		table.getColumns().add(idcol);
		table.getColumns().add(namecol);
		table.getColumns().add(addedcol);
		table.getColumns().add(commentcol);
		table.getColumns().add(delcol);
		
		this.setSpacing(9);
		this.setPadding(new Insets(9));
		
		this.getChildren().addAll(searchBar, table);
	}
	
	private void handleAddUser()
	{
		String name = nameField.getText();
		String comment = commentField.getText();
		if(!name.equals(""))
		{
			DBUtil.addUser(name, comment);
			nameField.setText("");
			commentField.setText("");
		}
	}
	
	public EventHandler<ActionEvent> getAddUserEvent()
	{
		return new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				handleAddUser();
			}
		};
	}
}
