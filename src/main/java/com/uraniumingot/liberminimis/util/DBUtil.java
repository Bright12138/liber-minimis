package com.uraniumingot.liberminimis.util;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.database.EnumBookState;
import com.uraniumingot.liberminimis.database.EnumHistoryType;
import com.uraniumingot.liberminimis.database.SQLDatabase;
import com.uraniumingot.liberminimis.lib.Settings;
import com.uraniumingot.liberminimis.ui.UIDropDownMessage;
import com.uraniumingot.liberminimis.ui.UIMessage;

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
			"BookID INT NOT NULL",
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
			"BookID INT NOT NULL UNIQUE",
			"UserID INT NOT NULL",
			"IsDamaged BIT NOT NULL"
		);
	}
	
	public static void addUser(String name, String comments)
	{
		try 
		{
			SQLDatabase.getInstance().insert("Users", "NULL", String.format("'%s'", filterString(name)), "DATETIME('NOW')", String.format("'%s'", filterString(comments)), "1");
		}
		catch (SQLException e) 
		{
			LiberMinimis.log.error(String.format("Failed to add user: %s to database!", name), e);
		}
		catch (InvalidParameterException e)
		{
			new UIMessage("message.invalidpar.txt");
		}
	}
	
	public static void setUserActive(int UserID, boolean allowBorrow)
	{
		try
		{
				SQLDatabase.getInstance().update("Users", "AllowBorrow", allowBorrow ? "1" : "0", "UserID", fromInt(UserID));
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
			SQLDatabase.getInstance().insert("Books", "NULL", String.format("'%s'", filterString(name)), "DATETIME('NOW')", String.format("'%s'", filterString(comments)),fromInt(EnumBookState.Available.ordinal()));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error(String.format("Failed to add book: %s to database!", name), e);
		}
		catch (InvalidParameterException e)
		{
			new UIMessage("message.invalidpar.txt");
		}
	}
	
	public static void addBookHold(String name, String comments)
	{
		try
		{
			SQLDatabase.getInstance().insert("Books", "NULL", String.format("'%s'", filterString(comments)), "DATETIME('NOW')", String.format("'%s'", filterString(comments)),fromInt(EnumBookState.OnHold.ordinal()));
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error(String.format("Failed to add book: %s to database!", name), e);
		}
		catch (InvalidParameterException e)
		{
			new UIMessage("message.invalidpar.txt");
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
			SQLDatabase.getInstance().insert("OnLend", "DATETIME('NOW')", calculateReturnDate(),fromInt(BookID),fromInt(UserID), "0");
			
			setBookState(BookID, EnumBookState.OnLend.ordinal());
			
			addHistory(BookID, UserID, EnumHistoryType.Borrow.ordinal());
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
			
			setBookState(BookID, EnumBookState.Available.ordinal());
			
			addHistory(BookID, getUserIDInLend(BookID), isDue(BookID) ? EnumHistoryType.ReturnDue.ordinal() : EnumHistoryType.Return.ordinal());

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
			
			addHistory(BookID, getUserIDInLend(BookID), EnumHistoryType.Renew.ordinal());
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
		return String.valueOf(intString);
	}
	
	public static String filterString(String toFilter) throws InvalidParameterException
	{
		if(toFilter.indexOf("'") == -1)
			return toFilter;
		else
			throw new InvalidParameterException(String.format("Invalid parameter '%s'!", toFilter));
	}
	
	public static String getUserNameFromID(int ID)
	{
		String name = "";
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("Users", "Name", String.format("ID = %s", ID));
			if(rs.next())
				name = rs.getString("Name");
		} 
		catch (SQLException e) 
		{
			LiberMinimis.log.error("Failed to cache username!", e);;
		}
		return name;
	}
	
	public static int getIDFromUserName(String name, boolean ignoreExpired)
	{
		int id = -1;
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("Users", "ID", String.format(ignoreExpired ? "Name = %s AND AllowBorrow = 1" : "Name = %s", filterString(name)));
			if(rs.next())
				id = rs.getInt("ID");
			if(rs.next())
			{
				ArrayList<String> ids = new ArrayList<String>();
				ids.add(String.valueOf(id));
				do 
					ids.add(String.valueOf(rs.getInt("ID")));
				while(rs.next());
				UIDropDownMessage choose = new UIDropDownMessage("dropdown.pickid.txt", ids);
				return choose.getResult();
			}
		}
		catch(InvalidParameterException e)
		{
			new UIMessage("message.invalidpar.txt");
		}
		catch(SQLException e)
		{
			return -1;
		}
		return id;
	}
	
	public static String getBookNameFromID(int ID)
	{
		String name = "";
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("Books", "Name", String.format("ID = %s", ID));
			if(rs.next())
				name = rs.getString("Name");
		}
		catch (SQLException e)
		{
			LiberMinimis.log.error("Failed to cache bookname!", e);
		}
		return name;
	}
	
	public static int getIDFromBookName(String name, boolean onlyAvaliable)
	{
		int id = -1;
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("Books", "ID", String.format(onlyAvaliable ? "Name = %s AND BookState <> 0" : "Name = %s", filterString(name)));
			if(rs.next())
				id = rs.getInt("ID");
			if(rs.next())
			{
				ArrayList<String> ids = new ArrayList<String>();
				ids.add(String.valueOf(id));
				do 
					ids.add(String.valueOf(rs.getInt("ID")));
				while(rs.next());
				UIDropDownMessage choose = new UIDropDownMessage("dropdown.pickid.txt", ids);
				return choose.getResult();
			}
		}
		catch(InvalidParameterException e)
		{
			new UIMessage("message.invalidpar.txt");
		}
		catch(SQLException e)
		{
			return -1;
		}
		return id;
	}
	
	public static boolean inLendList(int bookID, boolean includeDamaged)
	{
		boolean hasID = false;
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("OnLend", "1", includeDamaged ? String.format("BookID = %s", bookID) : String.format("BookID = %s,  AND IsDamaged = 0", bookID));
			if(rs.next())
				hasID = rs.getBoolean("ID");
		}
		catch (SQLException e)
		{
			LiberMinimis.log.error("Failed to check whether the book is in lend list!", e);
		}
		return hasID;
	}
	
	public static boolean existsAsBookID(int ID, boolean onlyAvaliable)
	{
		boolean hasName = false;
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("Books", "1", String.format(onlyAvaliable ? "ID = %s AND Bookstate = 1" : "ID = %s", ID));
			hasName = rs.next();
		}
		catch (SQLException e)
		{
			LiberMinimis.log.error("Failed to check whether the book exists!", e);
		}
		return hasName;
	}
	
	public static boolean existsAsUserID(int ID, boolean ignoreExpired)
	{
		boolean hasName = false;
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("Users", "1", String.format(ignoreExpired ? "ID = %s AND AllowBorrow = 1" : "ID = %s", ID));
			hasName = rs.next();
		}
		catch (SQLException e)
		{
			LiberMinimis.log.error("Failed to check whether the user exists!", e);
		}
		return hasName;
	}
	
	public static boolean isDue(int bookID)
	{
		Timestamp now = new Timestamp(new Date().getTime());
		Timestamp rd = null;;
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("OnLend", "ReturnDate", String.format("BookID = %s AND IsDamaged = 0", bookID));
			if(rs.next())
				rd = rs.getTimestamp("ReturnDate");
		}
		catch (SQLException e)
		{
			LiberMinimis.log.error("Failed to get return time!", e);
		}
		if(now.after(rd))
			return true;
		return false;
	}
	
	public static int getValidBookID(String anything, boolean onlyAvaliable)
	{
		int bookID = -1;
		if(anything.equals(""))
			return bookID;
		try
		{
			bookID = Integer.parseInt(anything);
			if(existsAsBookID(bookID, onlyAvaliable))
				return bookID;
			else
				bookID = -1;
		}
		catch(Exception e)
		{
			bookID = getIDFromBookName(anything, onlyAvaliable);
		}
		return bookID;
	}
	
	public static int getUserIDInLend(int bookID)
	{
		int userID = -1;
		try
		{
			ResultSet rs = SQLDatabase.getInstance().getTableWithCondition("OnLend", "UserID", String.format("BookID = %s", bookID));
			if(rs.next())
				userID = rs.getInt("UserID");
		}
		catch(SQLException e)
		{
			LiberMinimis.log.error("Failed to get user id from lend list!", e);
		}
		return userID;
	}
	
	public static int getValidUserID(String anything, boolean ignoreExpired)
	{
		int userID = -1;
		if(anything.equals(""))
			return userID;
		try
		{
			userID = Integer.parseInt(anything);
			if(existsAsUserID(userID, ignoreExpired))
				return userID;
			else
				userID = -1;
		}
		catch(Exception e)
		{
			userID = getIDFromBookName(anything, ignoreExpired);
		}
		return userID;
	}
}
