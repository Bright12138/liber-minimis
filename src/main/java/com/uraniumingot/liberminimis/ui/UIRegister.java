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
import javafx.stage.WindowEvent;

public class UIRegister extends Stage
{
	private final Label register = new Label(LanguageMap.translate("register.txt"));
	private final PasswordField pwfield = new PasswordField();
	private final PasswordField pwrfield = new PasswordField();
	private final Button registerbtn = new Button(LanguageMap.translate("register.btn"));
	
	public UIRegister()
	{
		LiberMinimis.log.info("Starting a register screen");
		this.setTitle(LanguageMap.translate("login.title"));
		
		this.setOnCloseRequest(new EventHandler<WindowEvent>()
		{

			@Override
			public void handle(WindowEvent event) 
			{
				LiberMinimis.markForShutdown();
			}
		});
		
		HBox[] hb = new HBox[]
				{
						new HBox(),
						new HBox(),
						new HBox(),
						new HBox()
				};
		
		
		Label password = new Label(LanguageMap.translate("password.txt"));
		Label password2 = new Label(LanguageMap.translate("passwordretype.txt"));

		VBox vb = new VBox();
		
		for(HBox hbox : hb)
		{
			hbox.setPadding(new Insets(9));
			hbox.setAlignment(Pos.CENTER);
		}
		
		pwfield.setOnAction(getRegisterEvent());
		pwrfield.setOnAction(getRegisterEvent());
		registerbtn.setOnAction(getRegisterEvent());
		
		hb[0].getChildren().add(register);
		hb[1].getChildren().addAll(password, pwfield);
		hb[2].getChildren().addAll(password2, pwrfield);
		hb[3].getChildren().add(registerbtn);
		
		vb.getChildren().addAll(hb);
		
		this.setScene(new Scene(vb, 250, 160));
		this.setResizable(false);
		this.show();
	}
	
	public void hideStage()
	{
		this.hide();
	}
	
	private EventHandler<ActionEvent> getRegisterEvent()
	{
		return new EventHandler<ActionEvent>()
				{
			@Override
			public void handle(ActionEvent event)
			{
				String pw = pwfield.getText();
				String pwr = pwrfield.getText();
				if (pw.equals(pwr) && pw != null && !pw.equals(""))
				{
					EncryptUtil.setPassword(null, pw);
					LiberMinimis.log.info("Starting main application");
					hideStage();
					new UIMain();
				}
				else if (pw.equals("") || pw == null)
				{
					register.setText(LanguageMap.translate("register.enterpassword"));
					register.setTextFill(Color.RED);
					pwfield.setText("");
					pwrfield.setText("");
				}
				else
				{
					register.setText(LanguageMap.translate("register.wrongpassword"));
					register.setTextFill(Color.RED);
					pwfield.setText("");
					pwrfield.setText("");
				}
			}
				};
	}
}
