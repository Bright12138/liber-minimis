package com.uraniumingot.liberminimis.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.uraniumingot.liberminimis.LiberMinimis;

public class SQLDatabase 
{
	private static SQLDatabase instance = new SQLDatabase();
	
	private Connection connection;
	private Statement statement;
	
	private SQLDatabase()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Data/library.db");
			statement = connection.createStatement();
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			LiberMinimis.log.fatal("Failed to initialize the SQL database!", e);
			System.exit(0);
		}
	}
	
	public static void init()
	{
		try 
		{
			ArrayList<String> tableNames = instance.getTableNames();
			
			if(!tableNames.contains("History"))
				instance.createTable
				(
						"History",
						"ID int NOT NULL",
						"Date smalldatetime NOT NULL",
						"BookID int NOT NULL",
						"UserID int NOT NULL",
						"Type tinyint NOT NULL",
						"ReturnDate smalldatetime",
						"PRIMARY KEY(ID)"
				);
			if(!tableNames.contains("Books"))
				instance.createTable
				(
						"Books",
						"ID int NOT NULL",
						"Name nvarchar",
						"AddedDate smalldatetime",
						"LastTransaction int",
						"State tinyint NOT NULL",
						"PRIMARY KEY(ID)"
				);
			if(!tableNames.contains("Users"))
				instance.createTable
				(
						"Users",
						"ID int NOT NULL",
						"Name nvarchar NOT NULL",
						"AddedDate smalldatetime",
						"Comments ntext",
						"PRIMARY KEY(ID)"
				);
		} 
		catch (SQLException e) 
		{
			LiberMinimis.log.fatal("Failed to initialize the database!", e);
		}
	}
	
	public static SQLDatabase getInstance()
	{
		return instance;
	}
	
	public void insert(String table, String... values) throws SQLException
	{
		statement.executeUpdate(String.format("INSERT INTO %s VALUES (%s)", table, formatLabels(values)));
	}
	
	public void createTable(String name, String... pars) throws SQLException
	{
		statement.executeUpdate(String.format("CREATE TABLE %s (%s)", name, formatLabels(pars)));
	}
	
	public void dropTable(String name) throws SQLException
	{
		statement.executeUpdate(String.format("DROP TABLE %s", name));
	}
	
	public ResultSet getTable(String table) throws SQLException
	{
		return statement.executeQuery(String.format("SELECT * FROM %s", table));
	}
	
	public ResultSet getTable(String table, String... labels) throws SQLException
	{
		return statement.executeQuery(String.format("SELECT %s FROM %s", formatLabels(labels),table));
	}
	
	public ArrayList<String> getTableNames() throws SQLException
	{
		ArrayList<String> names = new ArrayList<String>();
		ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
		
		while(rs.next())
			names.add(rs.getString("name"));
		
		return names;
	}
	
	private String formatLabels(String... label)
	{
		String formatted = "";
		
		for (int i = 0; i < label.length; i++)
		{
			if(i == label.length - 1)
			{
				formatted = String.format("%s%s", formatted, label[i]);
				return formatted;
			}
			formatted = String.format("%s%s,", formatted, label[i]);
		}
		
		return "";
	}
	
}
