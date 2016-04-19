package com.uraniumingot.liberminimis.database;

public class Database 
{
	private final String hashedPassword;
	
	public Database(String hashedPassword)
	{
		this.hashedPassword = hashedPassword;
	}

	public String getHashedPassword() 
	{
		return hashedPassword;
	}
}
