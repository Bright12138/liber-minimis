package com.uraniumingot.liberminimis.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.uraniumingot.liberminimis.LiberMinimis;
import com.uraniumingot.liberminimis.util.DBUtil;

public class SQLDatabase 
{
	private static SQLDatabase instance = new SQLDatabase();
	
	private Connection connection;
	private Statement statement;
	
	private SQLDatabase()
	{
		try
		{
			LiberMinimis.log.info("Initializing SQLDatabase");
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
				DBUtil.createHistoryDB();
			if(!tableNames.contains("OnLend"))
				DBUtil.createOnLendDB();
			if(!tableNames.contains("Books"))
				DBUtil.createBooksDB();
			if(!tableNames.contains("Users"))
				DBUtil.createUsersDB();
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
	
	public void update(String table, String columnChange, String value, String conditionColumn, String conditionValue) throws SQLException
	{
		statement.executeUpdate(String.format("UPDATE %s SET %s = %s WHERE %s = %s", table, columnChange, value, conditionColumn, conditionValue));
	}
	
	public void remove(String table, String column, String value) throws SQLException
	{
		statement.executeUpdate(String.format("DELETE FROM %s WHERE %s = %s", table, column, value));
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
	
	public ResultSet getTableWithCondition(String table, String formattedLabels, String condition) throws SQLException
	{
		return statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s", formattedLabels, table, condition));
	}
	
	public ArrayList<String> getTableNames() throws SQLException
	{
		ArrayList<String> names = new ArrayList<String>();
		ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
		
		while(rs.next())
			names.add(rs.getString("name"));
		
		return names;
	}
	
	public int getMaxID(String table) throws SQLException
	{
		ResultSet rs = statement.executeQuery(String.format("SELECT MAX(ID) AS maxID FROM %s", table));
		rs.next();
		return rs.getInt("maxID");
		//Don't worry, it still returns a 0 if the max id is null
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
