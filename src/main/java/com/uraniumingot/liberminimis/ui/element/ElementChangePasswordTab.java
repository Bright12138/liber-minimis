package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ElementChangePasswordTab extends VBox
{
	private final Label infoLabel = new Label(LanguageMap.translate("changepassword.info"));
	private final Label oldPassLabel = new Label(LanguageMap.translate("changepassword.old.txt"));
	private final Label newPassLabel = new Label(LanguageMap.translate("changepassword.new.txt"));
	private final Label repPassLabel = new Label(LanguageMap.translate("changepassword.rep.txt"));
	private final PasswordField oldPassField = new PasswordField();
	private final PasswordField newPassField = new PasswordField();
	private final PasswordField repPassField = new PasswordField();
	private final Button changeBtn = new Button(LanguageMap.translate("changepassword.btn"));
	
	public ElementChangePasswordTab()
	{
		this.setAlignment(Pos.CENTER);
		
		HBox hboxs[] = new HBox[]
		{
			new HBox(),
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
		
		hboxs[0].getChildren().add(infoLabel);
		hboxs[1].getChildren().addAll(oldPassLabel, oldPassField);
		hboxs[2].getChildren().addAll(newPassLabel, newPassField);
		hboxs[3].getChildren().addAll(repPassLabel, repPassField);
		hboxs[4].getChildren().add(changeBtn);
		
		this.getChildren().addAll(hboxs);
	}
	
}
