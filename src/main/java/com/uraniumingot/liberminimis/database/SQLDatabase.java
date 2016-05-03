package com.uraniumingot.liberminimis.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public static SQLDatabase getInstance()
	{
		return instance;
	}
	
	public void insert(String table, String values) throws SQLException
	{
		statement.executeUpdate(String.format("INSERT INTO %s VALUES (%s)", table, values));
	}
	
	public void createTable(String name, String dataTypes) throws SQLException
	{
		statement.executeUpdate(String.format("CREATE TABLE %s (%s)", name, dataTypes));
	}
	
	public void dropTable(String name) throws SQLException
	{
		statement.executeUpdate(String.format("DROP TABLE %s", name));
	}
	
}
