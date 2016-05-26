package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.util.EncryptUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
		
		changeBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				handlePasswordChange();
			}
		});
		
		hboxs[0].getChildren().add(infoLabel);
		hboxs[1].getChildren().addAll(oldPassLabel, oldPassField);
		hboxs[2].getChildren().addAll(newPassLabel, newPassField);
		hboxs[3].getChildren().addAll(repPassLabel, repPassField);
		hboxs[4].getChildren().add(changeBtn);
		
		this.getChildren().addAll(hboxs);
	}
	
	private static boolean isRepeat(String pw, String repeated)
	{
		return  !pw.equals("") && pw != null && pw.equals(repeated);
	}
	
	private void handlePasswordChange()
	{
		if(!EncryptUtil.isPassword(oldPassField.getText()))
		{
			oldPassField.setText("");
			infoLabel.setTextFill(Color.RED);
			infoLabel.setText(LanguageMap.translate("changepassword.old.error"));
			return;
		}
		
		if(isRepeat(newPassField.getText(), repPassField.getText()))
		{
			EncryptUtil.setPassword(oldPassField.getText(), newPassField.getText());
			oldPassField.setText("");
			newPassField.setText("");
			repPassField.setText("");
			infoLabel.setTextFill(Color.GREEN);
			infoLabel.setText(LanguageMap.translate("success.txt"));
		}
		else
		{
			oldPassField.setText("");
			newPassField.setText("");
			infoLabel.setTextFill(Color.RED);
			infoLabel.setText(LanguageMap.translate("changepassword.rep.error"));
		}
	}
	
}
