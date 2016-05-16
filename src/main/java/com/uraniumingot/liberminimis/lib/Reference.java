package com.uraniumingot.liberminimis.lib;

import java.io.File;

public class Reference 
{
	
	public static final String NAME = "Liber Minimis";
	public static final String VERSION = "@VERSION@";
	public static final String DATA_FOLDER_SUFFIX = "Data";
	public static final String TRANSLATION_FILE_LOCATION = "/translate" + FileSuffix.LANG;
	public static final String TRANSLATION_FILE_ENCODING = "UTF-8";
	public static final String FILE_SEP = System.getProperty("file.separator");
	public static final File DATA_DIR = new File(System.getProperty("user.dir") + FILE_SEP + Reference.DATA_FOLDER_SUFFIX);
	private static final String PASSWORD_FILE_LOCATION = DATA_DIR.getAbsolutePath() + FILE_SEP + "PasswordCache" + FileSuffix.BOOK_ASSIST_DATABASE;
	public static final File PASSWORD_FILE = new File(PASSWORD_FILE_LOCATION);
}
