package com.uraniumingot.liberminimis.database;

import java.sql.Timestamp;

import com.uraniumingot.liberminimis.util.DBUtil;

public class History 
{
	private final int ID;
	private final Timestamp date;
	private final int bookID;
	private String cachedBookName;
	private final int userID;
	private String cachedUserName;
	private final EnumHistoryType historyType;
	
	public History(int ID, Timestamp date, int bookID, int userID, EnumHistoryType historyType)
	{
		this.ID = ID;
		this.date = date;
		this.bookID = bookID;
		this.cachedBookName = DBUtil.getBookNameFromID(bookID);
		this.userID = userID;
		this.cachedUserName = DBUtil.getBookNameFromID(userID);
		this.historyType = historyType;
	}

	public int getID() 
	{
		return ID;
	}
	
	public Timestamp getDate()
	{
		return date;
	}
	
	public int getBookID()
	{
		return bookID;
	}
	
	public String getCachedBookName()
	{
		return cachedBookName;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public String getCachedUserName()
	{
		return cachedUserName;
	}
	
	public EnumHistoryType getHistoryType()
	{
		return historyType;
	}
}
