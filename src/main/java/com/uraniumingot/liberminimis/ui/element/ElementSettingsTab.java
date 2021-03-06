package com.uraniumingot.liberminimis.ui.element;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.configuration.ConfigHandler;
import com.uraniumingot.liberminimis.lang.LanguageMap;
import com.uraniumingot.liberminimis.lib.Settings;
import com.uraniumingot.liberminimis.util.AuthUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ElementSettingsTab extends VBox
{
	private final Label settings = new Label(LanguageMap.translate("settings.info"));
	private final Label settingsh = new Label(LanguageMap.translate("settings.hint"));
	private final Label npa = new Label(LanguageMap.translate("settings.needpasswordaction.info"));
	private final Label npat = new Label(LanguageMap.translate("settings.needpasswordactiontime.info"));
	private final Label lenddaycount = new Label(LanguageMap.translate("settings.lenddaycount.info"));
	private final Label renewdaycount = new Label(LanguageMap.translate("settings.renewdaycount.info"));
	private final Button applybtn = new Button(LanguageMap.translate("settings.apply.btn"));
	private final ToggleButton needPasswordOnActionBtn = new ToggleButton(LanguageMap.translate("settings.needpasswordonaction.yes.toggle"));
	private final ElementNumberField munpField = new ElementNumberField();
	private final ElementNumberField lendField = new ElementNumberField();
	private final ElementNumberField renewField = new ElementNumberField();
	
	public ElementSettingsTab()
	{
		ElementNumberField.setTextLimit(munpField, 2);
		ElementNumberField.setTextLimit(lendField, 2);
		ElementNumberField.setTextLimit(renewField, 2);
		
		munpField.setText(String.valueOf(Settings.MINUTES_UNTIL_NEXT_PASSWORD));
		lendField.setText(String.valueOf(Settings.LEND_DAY_COUNT));
		renewField.setText(String.valueOf(Settings.RENEW_DAY_COUNT));
		needPasswordOnActionBtn.setSelected(Settings.NEED_PASSWORD_ON_ACTION);
		needPasswordOnActionBtn.setText(LanguageMap.translate(needPasswordOnActionBtn.isSelected() ? "settings.needpasswordonaction.yes.toggle" : "settings.needpasswordonaction.no.toggle"));
		
		needPasswordOnActionBtn.setMinWidth(75);
		munpField.setMaxWidth(30);
		lendField.setMaxWidth(30);
		renewField.setMaxWidth(30);
		
		HBox[] hbs = new HBox[]
		{
			new HBox(),
			new HBox(),
			new HBox(),
			new HBox(),
			new HBox(),
			new HBox(),
			new HBox()
		};
		
		for(HBox hb : hbs)
		{
			hb.setSpacing(3);
			hb.setPadding(new Insets(5));
			hb.setAlignment(Pos.CENTER);
		}
		
		needPasswordOnActionBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				needPasswordOnActionBtn.setText(LanguageMap.translate(needPasswordOnActionBtn.isSelected() ? "settings.needpasswordonaction.yes.toggle" : "settings.needpasswordonaction.no.toggle"));
			}
			
		});
		
		applybtn.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event) 
			{
				handleApply();
			}
		});
		
		hbs[0].getChildren().add(settings);
		hbs[1].getChildren().add(settingsh);
		hbs[2].getChildren().addAll(npa, needPasswordOnActionBtn);
		hbs[3].getChildren().addAll(npat, munpField);
		hbs[4].getChildren().addAll(lenddaycount, lendField);
		hbs[5].getChildren().addAll(renewdaycount, renewField);
		hbs[6].getChildren().add(applybtn);
		
		this.setPadding(new Insets(9));
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(hbs);
	}
	
	private void handleApply()
	{
		if(!AuthUtil.authUserCompleted())
			return;
		
		LiberMinimis.log.info("Applying config updates");
		
		boolean needPasswordOnActionValue = needPasswordOnActionBtn.isSelected();
		int munpValue = Integer.parseInt(munpField.getText());
		int lendValue = Integer.parseInt(lendField.getText());
		int renewValue = Integer.parseInt(renewField.getText());
		
		if(Settings.NEED_PASSWORD_ON_ACTION != needPasswordOnActionValue)
			ConfigHandler.setBoolean("need_password_on_action", needPasswordOnActionValue);
		if(Settings.MINUTES_UNTIL_NEXT_PASSWORD != munpValue)
			ConfigHandler.setInt("minutes_until_next_password", munpValue);
		if(Settings.LEND_DAY_COUNT != lendValue)
			ConfigHandler.setInt("lend_day_count", lendValue);
		if(Settings.RENEW_DAY_COUNT != renewValue)
			ConfigHandler.setInt("renew_day_count", renewValue);
	}

}
