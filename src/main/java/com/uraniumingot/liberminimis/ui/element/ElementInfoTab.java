package com.uraniumingot.liberminimis.ui.element;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.lang.LanguageMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ElementInfoTab extends VBox
{
	private final Label infoLabel = new Label(LanguageMap.translate("about.info"));
	private final Text aboutText = new Text(LanguageMap.translate("about.detail"));
	private final Label depLabel = new Label(LanguageMap.translate("about.dep.info"));
	private final Hyperlink licenseLink = new Hyperlink();
	
	public ElementInfoTab()
	{
		licenseLink.setText("GNU GENERAL PUBLIC LICENSE v3");
		licenseLink.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try 
				{
					java.awt.Desktop.getDesktop().browse(new URI("https://github.com/Py-Tek/liber-minimis/blob/master/LICENSE"));
				} 
				catch (IOException | URISyntaxException e) 
				{
					LiberMinimis.log.error("Failed to open Url!", e);
				}
			}
		});
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll
		(
			infoLabel,
			aboutText,
			licenseLink,
			depLabel,
			addDep("Apache Log4j 2", "http://logging.apache.org/log4j/2.x/", "http://logging.apache.org/log4j/2.x/license.html"),
			addDep("Jxls", "http://jxls.sourceforge.net/","http://jxls.sourceforge.net/license.html"),
			addDep("Jasypt", "http://www.jasypt.org/", "http://www.jasypt.org/license.html"),
			addDep("SQLite JDBC", "https://github.com/xerial/sqlite-jdbc", "https://github.com/xerial/sqlite-jdbc/blob/master/LICENSE")
		);
	}
	
	private HBox addDep(String label, String homepageUrl, String licenseUrl)
	{
		HBox hb = new HBox();
		Hyperlink homepage = new Hyperlink();
		Hyperlink license = new Hyperlink();
		Label nameLabel = new Label(label);
		
		homepage.setText(LanguageMap.translate("about.homepage"));
		license.setText(LanguageMap.translate("about.license"));
		
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(nameLabel, homepage, license);
		
		homepage.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				try 
				{
					java.awt.Desktop.getDesktop().browse(new URI(homepageUrl));
				} 
				catch (Exception e1)
				{
					LiberMinimis.log.error("Failed to load Url!", e);
				}
			}
		});
		
		license.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				try 
				{
					java.awt.Desktop.getDesktop().browse(new URI(licenseUrl));
				} 
				catch (Exception e2)
				{
					LiberMinimis.log.error("Failed to load Url!", e);
				}
			}
		});
		
		hb.setPadding(new Insets(9));
		hb.setSpacing(3);
		return hb;
	}
}
