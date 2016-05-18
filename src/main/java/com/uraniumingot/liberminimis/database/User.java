package com.uraniumingot.liberminimis.database;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.uraniumingot.liberminimis.LiberMinimis;

public class User
{
	private final int ID;
	private String name;
	private final Timestamp addedDate;
	private String comments;
	private boolean allowBorrow;
	
	public User(int ID, String name, Timestamp addedDate, String comments, boolean allowBorrow)
	{
		this.ID = ID;
		this.name = name;
		this.addedDate = addedDate;
		this.comments = comments;
		this.allowBorrow = allowBorrow;
	}

	public int getID() 
	{
		return ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		try 
		{
			SQLDatabase.getInstance().update("Users", "Name", name, "ID", String.valueOf(ID));
		} 
		catch (SQLException e) 
		{
			LiberMinimis.log.error("Failed to update username!", e);
		}
		this.name = name;
	}
	
	public Timestamp getAddedDate()
	{
		return addedDate;
	}
	
	public String getComments()
	{
		return comments;
	}
	
	public void setComments(String comments)
	{
		try 
		{
			SQLDatabase.getInstance().update("Users", "Comments", comments, "ID", String.valueOf(ID));
		} 
		catch (SQLException e) 
		{
			LiberMinimis.log.error("Failed to update comments!", e);
		}
		this.comments = comments;
	}
	
	public boolean getAllowBorrow()
	{
		return allowBorrow;
	}
	
	public void setAllowBorrow(boolean allowBorrow)
	{
		String allowBorrowString = allowBorrow ? "1" : "0";
		try
		{
			SQLDatabase.getInstance().update("Users", "AllowBorrow", allowBorrowString, "ID", String.valueOf(ID));
		}
		catch (SQLException e)
		{
			LiberMinimis.log.error("Failed to toggle allow borrow!", e);
		}
		this.allowBorrow = allowBorrow;
	}
}
