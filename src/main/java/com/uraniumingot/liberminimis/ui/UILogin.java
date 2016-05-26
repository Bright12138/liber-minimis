package com.uraniumingot.liberminimis.ui;


import com.uraniumingot.liberminimis.LiberMinimis;
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
	private final Label login = new Label(LanguageMap.translate("login.txt"));
	private final Button loginbtn = new Button(LanguageMap.translate("login.btn"));
	private final PasswordField pwfield = new PasswordField();
	private boolean wasSuccessful = false;
	
	public UILogin()
	{
		LiberMinimis.log.info("Starting a login screen");
		this.setTitle(LanguageMap.translate("login.title"));
		
		HBox[] hb = new HBox[]
				{
						new HBox(),
						new HBox(),
						new HBox()
				};
		
		Label password = new Label(LanguageMap.translate("password.txt"));

		VBox vb = new VBox();
		
		for(HBox hbox: hb)
		{
			hbox.setPadding(new Insets(9));
			hbox.setAlignment(Pos.CENTER);
		}
		
		loginbtn.setOnAction(getLoginEvent());
		pwfield.setOnAction(getLoginEvent());
		
		hb[0].getChildren().add(login);
		hb[1].getChildren().addAll(password, pwfield);
		hb[2].getChildren().add(loginbtn);
		
		vb.getChildren().addAll(hb);
		
		this.setScene(new Scene(vb, 250, 120));
		this.setResizable(false);
		this.show();
	}
	
	public UILogin(boolean inSession)
	{
		this.setTitle(LanguageMap.translate("login.title"));
		
		HBox[] hb = new HBox[]
				{
						new HBox(),
						new HBox(),
						new HBox()
				};
		
		Label password = new Label(LanguageMap.translate("password.txt"));

		VBox vb = new VBox();
		
		for(HBox hbox: hb)
		{
			hbox.setPadding(new Insets(9));
			hbox.setAlignment(Pos.CENTER);
		}
		
		loginbtn.setOnAction(getActionLoginEvent());
		pwfield.setOnAction(getActionLoginEvent());
		
		hb[0].getChildren().add(login);
		hb[1].getChildren().addAll(password, pwfield);
		hb[2].getChildren().add(loginbtn);
		
		vb.getChildren().addAll(hb);
		
		this.setScene(new Scene(vb, 250, 120));
		this.setResizable(false);
		this.showAndWait();
	}
	
	public void hideStage()
	{
		this.hide();
	}
	
	private EventHandler<ActionEvent> getLoginEvent()
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
					pwfield.setText("");
				}
				else
				{
					LiberMinimis.log.info("Starting main application");
					hideStage();
					new UIMain();
				}
			}
				};
		
	}
	
	public boolean getLoginStatus()
	{
		return wasSuccessful;
	}
	
	private EventHandler<ActionEvent> getActionLoginEvent()
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
					pwfield.setText("");
				}
				else
				{
					LiberMinimis.log.info("Starting main application");
					wasSuccessful = true;
					hideStage();
				}
			}
		};
	}
}
