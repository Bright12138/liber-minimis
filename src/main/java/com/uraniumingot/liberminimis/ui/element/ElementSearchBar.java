package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.search.SearchType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public abstract class ElementSearchBar extends HBox
{
	public final TextField searchField = new TextField();
	public final ComboBox<SearchType> searchType = new ComboBox<SearchType>();
	public final Button searchButton = new Button(LanguageMap.translate("search.btn"));
	
	public ElementSearchBar(SearchType... searchTypes)
	{
		searchField.setPromptText(LanguageMap.translate("search.txt"));
		setMinWidth();
		searchType.getItems().addAll(searchTypes);
		searchType.setPromptText(LanguageMap.translate("searchtype.info"));
		
		searchButton.setOnAction(getSearchEvent());
		searchField.setOnAction(getSearchEvent());
		
		this.setSpacing(3);
		addToBar();
	}
	
	protected void addToBar()
	{
		this.getChildren().addAll(searchField, searchType, searchButton);
	}
	
	protected void setMinWidth()
	{
		searchField.setMinWidth(665);
	}
	
	public abstract void onSearch(String text, SearchType type);
	
	public void resize()
	{
		searchField.setMinWidth(this.getWidth() - searchType.getWidth() - searchButton.getWidth());
	}
	
	private EventHandler<ActionEvent> getSearchEvent()
	{
		return new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				if(searchType.getValue() != null)
					onSearch(searchField.getText(), searchType.getValue());
			}
		};
	}
}
