package com.uraniumingot.liberminimis.database;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.uraniumingot.liberminimis.LiberMinimis;

public class Book 
{
	private final int ID;
	private String name;
	private final Timestamp addedDate;
	private String comments;
	private EnumBookState bookstate;
	
	public Book(int ID, String name, Timestamp addedDate, String comments, EnumBookState bookstate)
	{
		this.ID = ID;
		this.name = name;
		this.addedDate = addedDate;
		this.comments = comments;
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
			SQLDatabase.getInstance().update("Books", "Name", name, "ID", String.valueOf(ID));
		} 
		catch (SQLException e) 
		{
			LiberMinimis.log.error("Failed to update bookname!", e);
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
			SQLDatabase.getInstance().update("Books", "Comments", comments, "ID", String.valueOf(ID));
		} 
		catch (SQLException e) 
		{
			LiberMinimis.log.error("Failed to update comments!", e);
		}
		this.comments = comments;
	}
	
	public EnumBookState getBookState()
	{
		return this.bookstate;
	}
	
	public void setBookState(EnumBookState bookstate)
	{
		try 
		{
			SQLDatabase.getInstance().update("Books", "Bookstate", String.valueOf(bookstate.ordinal()), "ID", String.valueOf(ID));
		} 
		catch (SQLException e) 
		{
			LiberMinimis.log.error("Failed to update bookstate!", e);
		}
		this.bookstate = bookstate;
	}
}
