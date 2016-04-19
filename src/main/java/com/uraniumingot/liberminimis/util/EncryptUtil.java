package com.uraniumingot.liberminimis.util;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.uraniumingot.liberminimis.lib.Reference;

public class EncryptUtil 
{
	private static final BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	private static String hashedPassword = readPasswordFromFile();
	
	private static String getHashedPassword(String password)
	{
		return passwordEncryptor.encryptPassword(password);
	}
	
	private static String readPasswordFromFile()
	{
		return (String) IOUtil.readFile(Reference.PASSWORD_FILE, null);
	}
	
	public static boolean hasPassword()
	{
		return hashedPassword != null;
	}
	
	public static void setPassword(String originalPassword, String newPassword)
	{
		if(isPassword(originalPassword))
		{
			hashedPassword = getHashedPassword(newPassword);
			IOUtil.writeFile(Reference.PASSWORD_FILE, hashedPassword);
		}
	}
	
	public static boolean isPassword(String password)
	{
		if (hasPassword())
			return passwordEncryptor.checkPassword(password, hashedPassword);
		return true;
	}
	
}
