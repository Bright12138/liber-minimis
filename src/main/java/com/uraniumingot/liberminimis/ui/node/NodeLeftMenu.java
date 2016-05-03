package com.uraniumingot.liberminimis.ui.node;

import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.ui.UIMain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NodeLeftMenu extends VBox
{
	private final Button[] buttons = new Button[]
			{
				new Button(LanguageMap.translate("main.borrowreturn.btn")),
				new Button(LanguageMap.translate("main.viewuser.btn")),
				new Button(LanguageMap.translate("main.viewbook.btn")),
				new Button(LanguageMap.translate("main.history.btn")),
				new Button(LanguageMap.translate("main.other.btn"))
			};
	
	public NodeLeftMenu(UIMain uimain)
	{
		super();
		this.setStyle("-fx-background-color: #DAE6F3;");
		this.setSpacing(20.0);
		this.setPadding(new Insets(20));
		this.setPrefWidth(200);
		
		for(Button btn : buttons)
		{
			btn.setPadding(new Insets(20));
			btn.setMaxWidth(Double.MAX_VALUE);
		}
		
		buttons[0].setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				uimain.switchBARMenu();
				}
		});
		
		buttons[1].setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				uimain.switchUserMenu();;
			}
		});
		
		buttons[2].setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				uimain.switchBookMenu();
			}
		});
		
		buttons[3].setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				uimain.switchHistoryMenu();
			}
		});
		
		buttons[4].setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				uimain.switchOtherMenu();
			}
		});
		
		this.getChildren().addAll(buttons);
	}
}
