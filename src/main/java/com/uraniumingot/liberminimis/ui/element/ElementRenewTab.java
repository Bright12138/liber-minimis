package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.lib.Settings;
import com.uraniumingot.liberminimis.util.DBUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ElementRenewTab extends VBox
{
	private final Label infolabel = new Label(LanguageMap.translate("main.borrowreturn.renew.txt"));
	private final Label booklabel = new Label(LanguageMap.translate("main.borrowreturn.bookid"));
	private final TextField bookfield = new TextField();
	private final Button confirmbtn = new Button(LanguageMap.translate("ok.btn"));
	
	public ElementRenewTab()
	{
		
		this.setAlignment(Pos.CENTER);
		HBox hboxs[] = new HBox[]
				{
						new HBox(),
						new HBox(),
						new HBox()
				};
		
		for(HBox hb : hboxs)
			hb.setAlignment(Pos.CENTER);
		
		bookfield.setOnAction(getRenewEvent());
		confirmbtn.setOnAction(getRenewEvent());
		
		this.setSpacing(20.0);
		this.setPadding(new Insets(20));
		this.setPrefWidth(200);
		
		hboxs[0].getChildren().add(infolabel);
		hboxs[1].getChildren().addAll(booklabel,bookfield);
		hboxs[2].getChildren().add(confirmbtn);
		
		
		this.getChildren().addAll(hboxs);
	}
	
	private EventHandler<ActionEvent> getRenewEvent()
	{
		return new EventHandler<ActionEvent>()
				{
			@Override
			public void handle(ActionEvent event)
			{
				handleRenewEvent();
			}
				};
	}
	
	private void handleRenewEvent()
	{
		String bft = bookfield.getText();
		int book = DBUtil.getValidBookID(bft, false);
		
		if(book != -1 && DBUtil.inLendList(book, false))//Successful
		{
			bookfield.setText("");
			DBUtil.updateOnLend(book, Settings.RENEW_DAY_COUNT);
			infolabel.setTextFill(Color.GREEN);
			infolabel.setText(LanguageMap.translate("success.txt"));
		}
		else
		{
			bookfield.setText("");
			infolabel.setTextFill(Color.RED);
			infolabel.setText(LanguageMap.translate("failed.renew.txt"));
		}
	}
}
