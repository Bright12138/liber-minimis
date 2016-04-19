package com.uraniumingot.liberminimis.util;

public class SystemUtil 
{
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	public static int getJavaComparableVersion()
	{
		int jvfd = JAVA_VERSION.indexOf(".") + 1;
		int jvsd = JAVA_VERSION.substring(jvfd).indexOf(".");
		return Integer.parseInt(JAVA_VERSION.substring(jvfd).substring(0, jvsd));
	} 
}
