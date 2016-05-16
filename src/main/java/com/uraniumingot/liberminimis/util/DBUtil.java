package com.uraniumingot.liberminimis.util;

import java.sql.SQLException;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.database.EnumBookState;
import com.uraniumingot.liberminimis.database.SQLDatabase;
import com.uraniumingot.liberminimis.lib.Settings;

public class DBUtil 
{

	public static void createUsersDB() throws SQLException
	{
		SQLDatabase.getInstance().createTable
		(
				"Users",
				//The NOT NULL needs to be there, I've checked
				"ID INTEGER PRIMARY KEY NOT NULL",
				"Name NVARCHAR NOT NULL",
				"AddedDate DATETIME",
				"Comments NTEXT",
				"AllowBorrow BIT NOT NULL"
		);
	}
	
	public static void createBooksDB() throws SQLException
	{
		SQLDatabase.getInstance().createTable
		(
				"Books",
				"ID INTEGER PRIMARY KEY NOT NULL",
				"Name NVARCHAR",
				"AddedDate DATETIME",
				"Comments NTEXT",
				"BookState TINYINT NOT NULL"
		);
	}
	
	public static void createHistoryDB() throws SQLException
	{
		SQLDatabase.getInstance().createTable
		(
				"History",
				"ID INTEGER PRIMARY KEY NOT NULL",
				"Date DATETIME NOT NULL",
				"BookID INT NOT NULL UNIQUE",
				"UserID INT NOT NULL",
				"HistoryType TINYINT NOT NULL"
		);
	}
	
	public static void createOnLendDB() throws SQLException
	{
		SQLDatabase.getInstance().createTable
		(
			"OnLend",
			"LendDate DATETIME PRIMARY KEY NOT NULL",
			"ReturnDate DATETIME NOT NULL",
			"BookID INT NOT NULL",
			"UserID INT NOT NULL"
		);
	}
	
	public static void addUser(String name, String comments)
	{
		try 
		{
			SQLDatabase.getInstance().insert("Users", "NULL", String.format("'%s'", name), "DATETIME('NOW')", String.format("'%s'", comments), "1");
		}
		catch (SQLException e) 
		{
			LiberMinimis.log.error(String.format("Failed to add user: %s to database!", name), e);
		}
	}
	
	public static void setUserActive(int UserID, boolean allowBorrow)
	{
		try
		{
			if(allowBorrow)
				SQLDatabase.getInstance().update("Users", "AllowBorrow", "1", "UserID", fromInt(UserID));
			else
				SQLDatabase.getInstance().update("Users", "AllowBorrow", "0", "UserID", fromInt(UserID));
		}
		catch (SQLException e) 
		{
			LiberMinimis.log.error("Failed to change user state!", e);
		}
	}
	
	public static void addBook(String name, String comments)
	{
		try
		{
			SQLDatabase.getInstance().insert("Books", "NULL", String.format("'%s'", name), "DATETIME('NOW')", String.format("'%s'", comments),fromInt(EnumBookState.Available.ordinal()));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error(String.format("Failed to add book: %s to database!", name), e);
		}
	}
	
	public static void addBookHold(String name, String comments)
	{
		try
		{
			SQLDatabase.getInstance().insert("Books", "NULL", String.format("'%s'", name), "DATETIME('NOW')", String.format("'%s'", comments),fromInt(EnumBookState.OnHold.ordinal()));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error(String.format("Failed to add book: %s to database!", name), e);
		}
	}
	
	public static void setBookState(int BookID, int state)
	{
		try
		{
			SQLDatabase.getInstance().insert("Books", "BookState",fromInt(state), "BookID", fromInt(BookID));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error("Failed to set bookstate!", e);
		}
	}
	
	public static void addHistory(int BookID, int UserID, int HistoryType)
	{
		try
		{
			SQLDatabase.getInstance().insert("History", "NULL", "DATETIME('NOW')", fromInt(BookID),fromInt(UserID),fromInt(HistoryType));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error("Failed to add history event to database!", e);
		}
	}
	
	public static void addOnLend(int BookID, int UserID)
	{
		try
		{
			SQLDatabase.getInstance().insert("OnLend", "DATETIME('NOW')", calculateReturnDate(),fromInt(BookID),fromInt(UserID));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error(String.format("Failed to add book with ID: %s to lend list!", BookID), e);
		}
	}
	
	public static void removeOnLend(int BookID)
	{
		try
		{
			SQLDatabase.getInstance().remove("OnLend", "BookID",fromInt(BookID));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error(String.format("Failed to remove book with ID: %s from lend list!", BookID), e);
		}
	}
	
	public static void updateOnLend(int BookID, int extendedDays)
	{
		try
		{
			SQLDatabase.getInstance().update("OnLend", "ReturnDate", String.format("DATETIME(ReturnDate, '+%s Day')", extendedDays), "BookID",fromInt(BookID));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error(String.format("Failed to update book with ID: %s from lend list!", BookID), e);
		}
	}
	
	private static String calculateReturnDate()
	{
		return String.format("DATETIME('NOW', '+%s day', '+4 hour')", Settings.returnPeriodDays);
	}
	
	private static String fromInt(int intString)
	{
		return new Integer(intString).toString();
	}
}
