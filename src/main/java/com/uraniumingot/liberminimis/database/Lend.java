package com.uraniumingot.liberminimis.database;

import java.sql.Timestamp;

import com.uraniumingot.liberminimis.util.DBUtil;

public class Lend 
{
	private final Timestamp lendDate;
	private Timestamp dueDate;
	private final int bookID;
	private String cachedBookName;
	private final int userID;
	private final String cachedUserName;
	
	public Lend(Timestamp lendDate, Timestamp dueDate, int bookID, int userID)
	{
		this.lendDate = lendDate;
		this.dueDate = dueDate;
		this.bookID = bookID;
		this.cachedBookName = DBUtil.getBookNameFromID(bookID);
		this.userID = userID;
		this.cachedUserName = DBUtil.getUserNameFromID(userID);
	}
	
	public Timestamp getLendDate()
	{
		return this.lendDate;
	}
	
	public Timestamp getDueDate()
	{
		return this.dueDate;
	}
	
	public void setDueDate(Timestamp dueDate)
	{
		//TODO Probably change this to extend due date?
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
}
