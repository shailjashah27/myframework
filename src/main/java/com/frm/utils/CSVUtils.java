package com.frm.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.frm.constants.AppConstants;

public class CSVUtils 
{
	private static String DEFAULT_SEPARATOR = AppConstants.DEFAULT_CSV_SEPARATOR;

	public static void writeLine(Writer w, List<String> values) throws IOException
	{
		boolean first = true;

		StringBuilder sb = new StringBuilder();

		for (String value : values) 
		{
			if (!first) 
			{
				sb.append(DEFAULT_SEPARATOR);
			}

			sb.append(value);

			first = false;
		}

		sb.append("\n");
		w.append(sb.toString());
	}


	public static void writeHeaderLine(Writer w, List<String> values) throws IOException 
	{
		writeLine(w, values);
	}

	public static void writeCSV(String csvFileName) throws Exception 
	{
		File csvFile = new File(csvFileName);
		csvFile.createNewFile();
		
		FileWriter writer = new FileWriter(csvFile);

		CSVUtils.writeHeaderLine(writer, Arrays.asList("header a", "header b", "header c", "header d"));

		CSVUtils.writeLine(writer, Arrays.asList("a", "b", "c", "d"));

		//custom separator + quote
		CSVUtils.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"));

		//custom separator + quote
		CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc,c"));

		//double-quotes
		CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"));

		writer.flush();
		writer.close();
	}
	
	public static void writeCSV(String csvFileName, Map<String, Map<String, String>> resultMap) throws Exception 
	{
		File csvFile = new File(csvFileName);
		csvFile.createNewFile();
		
		FileWriter writer = new FileWriter(csvFile);

		CSVUtils.writeHeaderLine(writer, Arrays.asList("Transaction No.", "Key", "Value"));

		Set<String> keyset = resultMap.keySet();
		Iterator<String> keySetItr = keyset.iterator();
		
		while(keySetItr.hasNext())
		{
			String txnNo = keySetItr.next();
			Map<String, String> keyMap = resultMap.get(txnNo);
			
			
			Set<String> keyset2 = keyMap.keySet();
			Iterator<String> keySetItr2 = keyset2.iterator();
			
			while(keySetItr2.hasNext())
			{
				String key = keySetItr2.next();
				String value = keyMap.get(key);
				
				CSVUtils.writeLine(writer, Arrays.asList(txnNo, key, value));
			}
		}
		
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) throws Exception 
	{
		String csvFileName = AppConstants.RESULTS_FOLDER_PATH + AppConstants.CSV_RESULT_NAME;
		CSVUtils.writeCSV(csvFileName);
	}
}
