package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;
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

public class ElementReturnTab extends VBox
{
	private final Label infolabel = new Label(LanguageMap.translate("main.borrowreturn.return.txt"));
	private final Label booklabel = new Label(LanguageMap.translate("main.borrowreturn.bookid"));
	private final TextField bookfield = new TextField();
	private final Button confirmbtn = new Button(LanguageMap.translate("ok.btn"));
	
	public ElementReturnTab()
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
		
		bookfield.setOnAction(getReturnEvent());
		confirmbtn.setOnAction(getReturnEvent());
		
		this.setSpacing(20.0);
		this.setPadding(new Insets(20));
		this.setPrefWidth(200);
		
		hboxs[0].getChildren().add(infolabel);
		hboxs[1].getChildren().addAll(booklabel,bookfield);
		hboxs[2].getChildren().add(confirmbtn);
		
		
		this.getChildren().addAll(hboxs);
	}
	
	private EventHandler<ActionEvent> getReturnEvent()
	{
		return new EventHandler<ActionEvent>()
				{
			@Override
			public void handle(ActionEvent event)
			{
				handleReturnEvent();
			}
				};
	}
	
	private void handleReturnEvent()
	{
		String bft = bookfield.getText();
		int book = DBUtil.getValidBookID(bft, false);
		
		if(book != -1)//Successful
		{
			bookfield.setText("");
			DBUtil.removeOnLend(book);
			infolabel.setTextFill(Color.GREEN);
			infolabel.setText(LanguageMap.translate("success.txt"));
		}
		else if(book == -1)
		{
			bookfield.setText("");
			infolabel.setTextFill(Color.RED);
			infolabel.setText(LanguageMap.translate("failed.book.txt"));
		}
	}
}
