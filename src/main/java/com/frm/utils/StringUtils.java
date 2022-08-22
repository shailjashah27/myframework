package com.frm.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.frm.constants.AppConstants;

public class StringUtils 
{
	public static String[] splitLine(String line)
	{
		line = line.trim();
		
		
		Pattern blankPattern = Pattern.compile("\"\\s+\"");
		Matcher blankPatternMatcher = blankPattern.matcher(line);
		
		if (blankPatternMatcher.find()) 
		{
			String bankString = blankPatternMatcher.group();
			int noOfSpaces = bankString.length();
			line = line.replaceAll("\"\\s+\"", org.apache.commons.lang3.StringUtils.repeat(AppConstants.BLANK, noOfSpaces));
		}
		else
		{
			//line = line.replaceAll("\"\\s+\"", AppConstants.BLANK);
			line = line.replaceAll("\"", "");
		}
		
		String[] keysArr = null;
		
		if(line.contains("[") && line.contains("]"))
		{
			keysArr = line.split("\\[");//split by white spaces
			
			if(keysArr.length > 1)
			{
				keysArr[0] = keysArr[0].replaceAll("\\[", "");
				keysArr[0] = keysArr[0].replaceAll("\\]", "");
				keysArr[1] = keysArr[1].replaceAll("\\[", "");
				keysArr[1] = keysArr[1].replaceAll("\\]", "");
				
				keysArr[0] = keysArr[0].trim();
				keysArr[1] = keysArr[1].trim();
			}
		}
		else
		{
			line = line.replaceAll("\\[", "");
			line = line.replaceAll("\\]", "");

			keysArr = line.split("\\s+");//split by white spaces
		}
		
		if(keysArr.length > 1)
		{
			if(keysArr[0].contains(AppConstants.BLANK))
			{
				keysArr[0] = keysArr[0].replaceAll(AppConstants.BLANK, " ");
			}

			if(keysArr[1].contains(AppConstants.BLANK))
			{
				keysArr[1] = keysArr[1].replaceAll(AppConstants.BLANK, " ");
			}
		}

		return keysArr;
	}

	public static String replaceSZRecord(String line, String replaceThis, String replaceTo)
	{
		//line = line.trim();
		line = line.replaceAll(replaceThis, replaceTo);
		return line;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getData(Object[] data) 
	{
		for(Object obj : data)
		{
			if(obj instanceof LinkedHashMap)
			{
				return (LinkedHashMap<String, String>)obj;
			}
		}
		
    	return null;
	}
}
