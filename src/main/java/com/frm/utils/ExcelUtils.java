package com.frm.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.frm.constants.AppConstants;

public class ExcelUtils 
{
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;

	public ExcelUtils() 
	{
		try 
		{
			workbook = new XSSFWorkbook();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void writeAndCloseWorkbook(String path) throws IOException
	{
		if(workbook != null)
		{
			FileOutputStream fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			workbook = null;
		}
	}
	
	public boolean setCellData(Sheet sheet, int colNum, int rowNum, String data, XSSFWorkbook workbook) 
	{
		try 
		{		
			//int index = workbook.getSheetIndex(sheetName);
			
			//if (index == -1) return false;
			if (colNum == -1) return false;

			//Sheet sheet = workbook.getSheetAt(index);

			//int headerRowNum = 1;
			//row = sheet.getRow(headerRowNum);

			//sheet.autoSizeColumn(colNum);
			Row row = sheet.getRow(rowNum - 1);
			
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			Cell cell = row.getCell(colNum);
			
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);
			sheet.autoSizeColumn(colNum);
			
			//fileOut = new FileOutputStream(path);

			//workbook.write(fileOut);

			//fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Sheet addSheet(String sheetName, XSSFWorkbook workbook) 
	{
		return workbook.createSheet(sheetName);
	}
	
	public boolean isSheetExist(String sheetName) 
	{
		int index = workbook.getSheetIndex(sheetName);

		if (index == -1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public void save(String fileName) 
	{
		try 
		{
			fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	public XSSFSheet getSheet(String sheetName) 
	{
		return workbook.getSheet(sheetName);
	}
	
	public static void main(String args[]) throws IOException
	{
		String excelFileName = AppConstants.RESULTS_FOLDER_PATH + AppConstants.EXCEL_RESULT_NAME;
 
		ExcelUtils excelUtils = new ExcelUtils();
		
		Sheet sheet = excelUtils.addSheet("Transaction_Data", excelUtils.workbook);
		excelUtils.setCellData(sheet, 1, 1, "test_data2", excelUtils.workbook);
		excelUtils.writeAndCloseWorkbook(excelFileName);
	}

	public static void writeExcel(String excelFileName, Map<String, Map<String, String>> resultMap) throws IOException 
	{
		int rowNum = 1;

		ExcelUtils excelUtils = new ExcelUtils();
		Sheet sheet = excelUtils.addSheet("Transaction_Data", excelUtils.workbook);
		excelUtils.writeLine(sheet, Arrays.asList("Transaction No.", "Key", "Value"), rowNum++);

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
				
				excelUtils.writeLine(sheet, Arrays.asList(txnNo, key, value), rowNum++);
			}
		}
		
		excelUtils.writeAndCloseWorkbook(excelFileName);
	}
	
	private void writeLine(Sheet sheet, List<String> columnNameList, int rowNum) 
	{
		int columnNum = 0;
		
		for(String columnName : columnNameList)
		{
			setCellData(sheet, columnNum++, rowNum, columnName, workbook);
		}
	}
}
