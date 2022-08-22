package com.frm.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.frm.constants.AppConstants;

public class TextFileParser {

	public static void main(String[] args) throws Exception 
	{
		String fileName = AppConstants.TEST_DATA_FOLDER_PATH + "q1.test_data";

		TextFileParser textFileParser = new TextFileParser();
		Map<String, Map<String, String>> resultMap = textFileParser.parse(fileName);
		CSVUtils.writeCSV(AppConstants.RESULTS_FOLDER_PATH + AppConstants.CSV_RESULT_NAME, resultMap);
		ExcelUtils.writeExcel(AppConstants.RESULTS_FOLDER_PATH + AppConstants.EXCEL_RESULT_NAME, resultMap);
	}

	public Map<String, Map<String, String>> parse(String fileName) throws FileNotFoundException 
	{
		Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String,String>>();

		File file = new File(fileName);

		if(!file.exists())
		{
			throw new FileNotFoundException();
		}

		System.out.println(file.getAbsolutePath());
		
		Scanner fileScanner = new Scanner(file);

		try
		{
			String transactionSeparatorStr = AppConstants.TRANSACTION_SEPARATOR;
			String recordStartStr = AppConstants.RECORD_START_STR;
			
			Pattern transactionSeparatorPattern = Pattern.compile(transactionSeparatorStr);
			Pattern recordStartPattern = Pattern.compile(recordStartStr);

			//Stack<String> headerStack = new Stack<String>();
			int transactionCount = 0;
			
			while(fileScanner.hasNextLine())
			{
				String line = fileScanner.nextLine();
				line = line.trim();
				
				line = StringUtils.replaceSZRecord(line, AppConstants.SZ_RECORD_STR, "");
				
				Matcher transactionSeparatorMatcher = transactionSeparatorPattern.matcher(line);
				Matcher recordStartMatcher = recordStartPattern.matcher(line);

				if (transactionSeparatorMatcher.find()) 
				{
					transactionCount++;
					resultMap.put("SZ" + transactionCount, null);
				}
				
				if (!recordStartMatcher.find()) 
				{
					//String transactionVal = headerStack.pop();
					
					String[] keysArr = StringUtils.splitLine(line);
					
					Map<String, String> keysMap = resultMap.get("SZ" + transactionCount);
					
					if(keysMap == null)
					{
						keysMap = new HashMap<String, String>();
					}
					
					keysMap.put(keysArr[0], keysArr[1]);
					resultMap.put("SZ" + transactionCount, keysMap);
				}
			}
			
			System.out.println(resultMap);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			fileScanner.close();
		}
		
		return resultMap;
	}
}