package com.uraniumingot.liberminimis.ui;


import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.util.EncryptUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UILogin extends Stage
{
	private Label login = new Label(LanguageMap.translate("login.txt"));
	private Button loginbtn = new Button(LanguageMap.translate("login.btn"));
	private PasswordField pwfield = new PasswordField();
	
	public UILogin()
	{

		this.setTitle(LanguageMap.translate("login.title"));
		
		HBox hbtop = new HBox();
		HBox hbmiddle = new HBox();
		HBox hbbottom = new HBox();
		Label password = new Label(LanguageMap.translate("password.txt"));

		VBox vb = new VBox();
		
		hbtop.setPadding(new Insets(9));
		hbmiddle.setPadding(new Insets(9));
		hbbottom.setPadding(new Insets(9));
		
		loginbtn.setOnAction(getLoginEvent());
		pwfield.setOnAction(getLoginEvent());
		
		hbtop.getChildren().add(login);
		hbtop.setAlignment(Pos.CENTER);
		hbmiddle.getChildren().addAll(password, pwfield);
		hbmiddle.setAlignment(Pos.CENTER);
		hbbottom.getChildren().add(loginbtn);
		hbbottom.setAlignment(Pos.CENTER);
		
		vb.getChildren().addAll(hbtop, hbmiddle, hbbottom);
		
		this.setScene(new Scene(vb, 250, 120));
		this.setResizable(false);
		this.show();
	}
	
	public EventHandler<ActionEvent> getLoginEvent()
	{
		return new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				String pw = pwfield.getText();
				if (!EncryptUtil.isPassword(pw))
				{
					login.setText(LanguageMap.translate("login.wrongpassword"));
					login.setTextFill(Color.RED);
				}
			}
		};
	}
}
