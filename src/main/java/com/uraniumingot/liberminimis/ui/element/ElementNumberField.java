package com.uraniumingot.liberminimis.ui.element;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class ElementNumberField extends TextField
{
	public ElementNumberField(String args)
	{
		super(args);
		this.setAlignment(Pos.CENTER);
	}
	
	public ElementNumberField()
	{
		super();
		this.setAlignment(Pos.CENTER);
	}
	
	public static void setTextLimit(TextField field, final int maxLength)
	{
		
		field.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue)
			{
				if(field.getText().length() > maxLength)
				{
					field.setText(field.getText().substring(0, maxLength));
				}
			}
		});
	}
	
	@Override 
	public void replaceText(int start, int end, String text) 
	{
		if (text.matches("[0-9]*")) 
		{
			super.replaceText(start, end, text);
		}
	}

	@Override 
	public void replaceSelection(String text) 
	{
		if (text.matches("[0-9]*")) 
		{
			super.replaceSelection(text);
		}
	}
}
