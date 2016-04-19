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

public class UIRegister extends Stage
{
	private Label register = new Label(LanguageMap.translate("register.txt"));
	private PasswordField pwfield = new PasswordField();
	private PasswordField pwrfield = new PasswordField();
	private Button registerbtn = new Button(LanguageMap.translate("register.btn"));
	
	public UIRegister()
	{
		this.setTitle(LanguageMap.translate("login.title"));
		
		HBox hbtop = new HBox();
		HBox hbmiddletop = new HBox();
		HBox hbmiddlebottom = new HBox();
		HBox hbbottom = new HBox();
		Label password = new Label(LanguageMap.translate("password.txt"));
		Label password2 = new Label(LanguageMap.translate("passwordretype.txt"));

		VBox vb = new VBox();
		
		hbtop.setPadding(new Insets(9));
		hbmiddletop.setPadding(new Insets(9));
		hbmiddlebottom.setPadding(new Insets(9));
		hbbottom.setPadding(new Insets(9));

		pwfield.setOnAction(getRegisterEvent());
		pwrfield.setOnAction(getRegisterEvent());
		registerbtn.setOnAction(getRegisterEvent());
		
		hbtop.getChildren().add(register);
		hbtop.setAlignment(Pos.CENTER);
		hbmiddletop.getChildren().addAll(password, pwfield);
		hbmiddletop.setAlignment(Pos.CENTER);
		hbmiddlebottom.getChildren().addAll(password2, pwrfield);
		hbmiddlebottom.setAlignment(Pos.CENTER);
		hbbottom.getChildren().add(registerbtn);
		hbbottom.setAlignment(Pos.CENTER);
		
		vb.getChildren().addAll(hbtop, hbmiddletop, hbmiddlebottom, hbbottom);
		
		this.setScene(new Scene(vb, 250, 160));
		this.setResizable(false);
		this.show();
	}
	
	public void hideStage()
	{
		this.hide();
	}
	
	public EventHandler<ActionEvent> getRegisterEvent()
	{
		return new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				String pw = pwfield.getText();
				String pwr = pwrfield.getText();
				LiberMinimis.log.info(pw);
				LiberMinimis.log.info(pwr);
				if (pw.equals(pwr) && pw != null && pw != "")
				{
					EncryptUtil.setPassword(null, pw);
					//TODO Next stage
				}
				else if (pw == "" || pw == null)
				{
					register.setText(LanguageMap.translate("register.enterpassword"));
					register.setTextFill(Color.RED);
				}
				else
				{
					register.setText(LanguageMap.translate("register.wrongpassword"));
					register.setTextFill(Color.RED);
				}
			}
		};
	}
}
