package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
		
		this.setSpacing(20.0);
		this.setPadding(new Insets(20));
		this.setPrefWidth(200);
		
		hboxs[0].getChildren().add(infolabel);
		hboxs[1].getChildren().addAll(booklabel,bookfield);
		hboxs[2].getChildren().addAll(userlabel,userfield);
		hboxs[3].getChildren().add(confirmbtn);
		
		this.getChildren().addAll(hboxs);
	}
}
