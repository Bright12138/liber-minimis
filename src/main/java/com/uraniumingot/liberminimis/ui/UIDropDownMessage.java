package com.uraniumingot.liberminimis.ui;

import java.util.ArrayList;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIDropDownMessage extends Stage
{
	private final Button confirmbtn = new Button(LanguageMap.translate("ok.btn"));
	private final Button cancelbtn = new Button(LanguageMap.translate("cancel.btn"));
	private final ComboBox<String> dropdown = new ComboBox<String>();
	private int result = -1;
	
	
	public UIDropDownMessage(String unlocalizedText, ArrayList<String> list)
	{
		LiberMinimis.log.info("Showing a  message screen");
		this.setTitle(LanguageMap.translate("message.title"));
		
		confirmbtn.setOnAction(getChooseEvent());
		cancelbtn.setOnAction(getHideEvent());
		
		VBox vb = new VBox();
		
		HBox hb = new HBox();
		hb.getChildren().addAll(confirmbtn, cancelbtn); 
		
		dropdown.getItems().addAll(list);
		
		
		vb.getChildren().addAll(new Label(LanguageMap.translate(unlocalizedText)),dropdown, hb);
		vb.setPadding(new Insets(9));
		vb.setAlignment(Pos.CENTER);
		
		this.setScene(new Scene(vb, 250, 80));
		this.setResizable(false);
		this.showAndWait();
	}
	
	
	public int getResult()
	{
		return result;
	}
	
	public void setResult(int result)
	{
		this.result = result;
	}
	
	private void hideMessage()
	{
		this.hide();
	}
	
	private void chooseResult()
	{
		this.setResult(Integer.valueOf(dropdown.getValue()));
	}
	
	public String getDropDownText()
	{
		return dropdown.getValue();
	}
	
	private EventHandler<ActionEvent> getHideEvent()
	{
		return new EventHandler<ActionEvent>()
				{
			@Override
			public void handle(ActionEvent event)
			{
				hideMessage();
				
			}
				};
	}
	
	private EventHandler<ActionEvent> getChooseEvent()
	{
		return new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				chooseResult();
			}
		};
	}
	
}
