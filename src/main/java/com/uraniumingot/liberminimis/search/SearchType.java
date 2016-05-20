package com.uraniumingot.liberminimis.search;

import com.uraniumingot.liberminimis.lang.LanguageMap;

public class SearchType 
{
	private final String localization;
	private final String typeName;
	
	public static final SearchType TypeUserID = new SearchType("userid", "searchtype.user.id");
	public static final SearchType TypeUserName = new SearchType("username", "searchtype.user.name");
	public static final SearchType TypeUserAddedTime = new SearchType("useraddedtime", "searchtype.user.addedtime");
	public static final SearchType TypeUserComment = new SearchType("usercomment", "searchtype.user.comment");
	public static final SearchType TypeBookID = new SearchType("bookid", "searchtype.book.id");
	public static final SearchType TypeBookName = new SearchType("bookname", "searchtype.book.name");
	public static final SearchType TypeBookAddedTime = new SearchType("bookaddedtime", "searchtype.book.addedtime");
	public static final SearchType TypeBookComment = new SearchType("bookcomment", "searchtype.book.comment");
	public static final SearchType TypeLendAddedTime= new SearchType("lendaddtime", "searchtype.lend.lendtime");
	public static final SearchType TypeLendReturnTime = new SearchType("lendreturntime", "searchtype.lend.returntime");
	
	public SearchType(String typeName, String localization)
	{
		this.typeName = typeName;
		this.localization = localization;
	}
	
	@Override
	public String toString()
	{
		return LanguageMap.translate(localization);
	}
	
	public String getTypeName()
	{
		return typeName;
	}
}
