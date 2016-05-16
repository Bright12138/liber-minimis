package com.uraniumingot.liberminimis.util;

import com.uraniumingot.liberminimis.lib.Reference;

public class SystemUtil 
{
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	public static void initDataFolder()
	{
		if(!Reference.DATA_DIR.exists())
			Reference.DATA_DIR.mkdirs();
	}
	
	public static int getJavaComparableVersion()
	{
		int jvfd = JAVA_VERSION.indexOf(".") + 1;
		int jvsd = JAVA_VERSION.substring(jvfd).indexOf(".");
		return Integer.parseInt(JAVA_VERSION.substring(jvfd).substring(0, jvsd));
	} 
}
