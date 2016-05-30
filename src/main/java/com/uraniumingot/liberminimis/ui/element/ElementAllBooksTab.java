package com.uraniumingot.liberminimis.ui.element;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.database.SQLDatabase;
import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.search.SearchType;
import com.uraniumingot.liberminimis.util.DBUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ElementAllBooksTab extends VBox implements ITableViewTab
{
	private final TableView<ObservableList<String>> table = new TableView<ObservableList<String>>();
	private final TextField nameField = new TextField();
	private final TextField commentField = new TextField();
	private final Button addBookButton = new Button(LanguageMap.translate("addbook.btn"));
	
	private final ElementSearchBar searchBar = new ElementSearchBar(SearchType.TypeBookID, SearchType.TypeBookName, SearchType.TypeBookComment, SearchType.TypeBookAddedTime)
			{
		@Override
		public void onSearch(String text, SearchType type)
		{
			onSearch(text, type);
		}
			};
	
	public ElementAllBooksTab()
	{
		nameField.setPromptText(LanguageMap.translate("addbook.name"));
		commentField.setPromptText(LanguageMap.translate("addbook.comment"));
		commentField.setMinWidth(585);
		
		addBookButton.setOnAction(getAddBookEvent());
		
//		delcol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
//		
//		Callback<TableColumn<ObservableList<String>, String>, TableCell<ObservableList<String>, String>> cellFactory = new Callback<TableColumn<ObservableList<String>, String>, TableCell<ObservableList<String>, String>>()
//		{
//
//			@Override
//			public TableCell<ObservableList<String>, String> call(TableColumn<ObservableList<String>, String> arg) 
//			{
//				return new TableCell<ObservableList<String>, String>()
//				{
//					Button optbtn = new Button(LanguageMap.translate("opt.btn"));
//					
//					@Override
//					public void updateItem(String item, boolean empty)
//					{
//						super.updateItem(item, empty);
//						if(empty)
//						{
//							setGraphic(null);
//							setText(null);
//						}
//						else
//						{
//							optbtn.setOnAction( new EventHandler<ActionEvent>()
//							{
//								@Override
//								public void handle(ActionEvent event)
//								{
//									
//								}
//							});
//							setGraphic(optbtn);
//							setText(null);
//						}
//					}
//				};
//			}
//			
//		};
//		
//		delcol.setCellFactory(cellFactory);
//		
//		idcol.setEditable(false);
//		addedcol.setEditable(false);
		
		table.setMinHeight(620);
		table.setEditable(true);
		initColumns(getBooksTable());
		
		//HBox bottom
		HBox hbb = new HBox();
		hbb.setSpacing(3);
		hbb.getChildren().addAll(nameField, commentField, addBookButton);
		
		this.setSpacing(9);
		this.setPadding(new Insets(9));
		
		updateTable(getBooksTable());
		
		this.getChildren().addAll(searchBar, table, hbb);
	}
	
	private void handleAddBook()
	{
		String name = nameField.getText();
		String comment = commentField.getText();
		if(!name.equals(""))
		{
			DBUtil.addBook(name, comment);
			nameField.setText("");
			commentField.setText("");
			updateTable(getBooksTable());
		}
	}
	
	public EventHandler<ActionEvent> getAddBookEvent()
	{
		return new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				handleAddBook();
			}
		};
	}
	

	private ResultSet getBooksTable()
	{
		ResultSet rs = null;
		try
		{
			rs= SQLDatabase.getInstance().getTable("Books");
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error("Failed to get books table from database!", e);
		}
		return rs;
	}
	
	
	//TODO Allow data editing, add another button column for editing
	@Override
	public void initColumns(ResultSet rs)
	{
		if(rs == null)
		{
			LiberMinimis.log.error("The books table returned a null result set!");
			return;
		}
		
		try
		{
			TableColumn<ObservableList<String>, String>[] cols = new TableColumn[rs.getMetaData().getColumnCount()];
			for(int i = 0; i < rs.getMetaData().getColumnCount(); i++)
			{
				final int j = i;
				cols[i] = new TableColumn<ObservableList<String>, String>(LanguageMap.translate(String.format("column.%s.%s", "books", rs.getMetaData().getColumnName(i+1).toLowerCase())));
				cols[i].setCellValueFactory(new Callback<CellDataFeatures<ObservableList<String> ,String>,ObservableValue<String>>()
				{                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList<String>, String> param) 
                    {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
				});
			}
			cols[0].setEditable(false);
			cols[2].setEditable(false);
			table.getColumns().addAll(cols);
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error("Failed to create columns from result set!", e);
		}
	}
	
	@Override
	public void updateTable(ResultSet rs) 
	{
		if(rs == null)
		{
			LiberMinimis.log.error("The books table returned a null result set!");
			return;
		}
		
		ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
		
		try 
		{
			while(rs.next())
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
				{
					row.add(rs.getString(i));
				}
				data.add(row);
			}
		} 
		catch (SQLException e) 
		{
			LiberMinimis.log.error("Failed to add data all books from database!", e);
		}
		table.setItems(data);
	}

	//TODO change null to search querry
	@Override
	public void onSearch(String text, SearchType type)
	{
		updateTable(null);
	}
	
	
}
