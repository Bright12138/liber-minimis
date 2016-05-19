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

public class NodeBorrowMenu extends VBox
{
	private final Label infolabel = new Label(LanguageMap.translate("main.borrowreturn.borrow.txt"));
	private final Label booklabel = new Label(LanguageMap.translate("main.borrowreturn.bookid"));
	private final Label userlabel = new Label(LanguageMap.translate("main.borrowreturn.userid"));
	private final TextField bookfield = new TextField();
	private final TextField userfield = new TextField();
	private final Button confirmbtn = new Button(LanguageMap.translate("ok.btn"));
	
	public NodeBorrowMenu()
	{
		
		this.setAlignment(Pos.CENTER);
		HBox hboxs[] = new HBox[]
				{
						new HBox(),
						new HBox(),
						new HBox(),
						new HBox()
				};
		
		for(HBox hb : hboxs)
			hb.setAlignment(Pos.CENTER);
		
		confirmbtn.setOnAction(getBorrowEvent());
		
		this.setSpacing(20.0);
		this.setPadding(new Insets(20));
		this.setPrefWidth(200);
		
		hboxs[0].getChildren().add(infolabel);
		hboxs[1].getChildren().addAll(booklabel,bookfield);
		hboxs[2].getChildren().addAll(userlabel,userfield);
		hboxs[3].getChildren().add(confirmbtn);
		
		this.getChildren().addAll(hboxs);
	}
	
	private EventHandler<ActionEvent> getBorrowEvent()
	{
		return new EventHandler<ActionEvent>()
				{
			@Override
			public void handle(ActionEvent event)
			{
				handleBorrowEvent();
			}
				};
	}
	
	private void handleBorrowEvent()
	{
		String bft = bookfield.getText();
		String uft = userfield.getText();
		
		int book = DBUtil.getValidBookID(bft);
		int user = DBUtil.getValidUserID(uft);
		
		if(book != -1 && user != -1)//Successful
		{
			bookfield.setText("");
			userfield.setText("");
			DBUtil.addOnLend(book, user);
			infolabel.setTextFill(Color.GREEN);
			infolabel.setText(LanguageMap.translate("success.txt"));
		}
		else if(book == -1 && user == -1)
		{
			bookfield.setText("");
			userfield.setText("");
			infolabel.setTextFill(Color.RED);
			infolabel.setText(LanguageMap.translate("failed.bookanduser.txt"));
		}
		else if(book == -1)
		{
			bookfield.setText("");
			infolabel.setTextFill(Color.RED);
			infolabel.setText(LanguageMap.translate("failed.book.txt"));
		}
		else if(user == -1)
		{
			userfield.setText("");
			infolabel.setTextFill(Color.RED);
			infolabel.setText(LanguageMap.translate("failed.user.txt"));
		}
	}
}
