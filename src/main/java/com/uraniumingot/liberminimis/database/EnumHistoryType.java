package com.uraniumingot.liberminimis.database;

import com.uraniumingot.liberminimis.lang.LanguageMap;

public enum EnumHistoryType 
{
	Borrow,
	Return,
	Renew,
	LostOrDamaged,
	ReturnDue,
	// Used for the filter in the user interface
	All;
	
	@Override
	public String toString()
	{
		return LanguageMap.translate(String.format("historytype.%s.name", super.toString().toLowerCase()));
	}
}
