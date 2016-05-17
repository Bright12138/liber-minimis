package com.uraniumingot.liberminimis.ui;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIMessage extends Stage
{
	private final Button confirmbtn = new Button(LanguageMap.translate("ok.btn"));
	
	public UIMessage(String unlocalizedMessage)
	{
		LiberMinimis.log.info("Showing a  message screen");
		this.setTitle(LanguageMap.translate("message.title"));
		
		confirmbtn.setOnAction(getHideEvent());
		
		VBox vb = new VBox();
		Label message = new Label(LanguageMap.translate(unlocalizedMessage));
		
		vb.getChildren().addAll(message, confirmbtn);
		vb.setPadding(new Insets(9));
		vb.setAlignment(Pos.CENTER);
		
		this.setScene(new Scene(vb, 250, 80));
		this.setResizable(false);
		this.show();
	}
	
	public void hideMessage()
	{
		this.hide();
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
}
