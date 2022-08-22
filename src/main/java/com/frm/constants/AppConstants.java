package com.frm.constants;

import java.util.Hashtable;

import com.frm.utils.PropertiesUtil;

public interface AppConstants 
{
	Hashtable<String, String> CONFIG_PROPERTIES = PropertiesUtil.getPropertiesAsHashtable("./resources/Config.properties");

	String TEST_DATA_FOLDER_PATH = CONFIG_PROPERTIES.get("test_Data_Folder_Path");
	String RESULTS_FOLDER_PATH = CONFIG_PROPERTIES.get("results_Folder_Path");
	String CSV_RESULT_NAME = CONFIG_PROPERTIES.get("CSV_Result_Name");
	String EXCEL_RESULT_NAME = CONFIG_PROPERTIES.get("Excel_Result_Name");
	String DEFAULT_CSV_SEPARATOR = CONFIG_PROPERTIES.get("default_CSV_Separator");
	
	String BLANK = "#BLANK#";
	//search strings
	String SZ_RECORD_STR = CONFIG_PROPERTIES.get("SZ_Record_Str");
	String RECORD_START_STR = CONFIG_PROPERTIES.get("record_Start_Str");
	String TRANSACTION_SEPARATOR = CONFIG_PROPERTIES.get("transaction_Separator");
	//String TEST = CONFIG_PROPERTIES.get("");
	//String TEST = CONFIG_PROPERTIES.get("");
	//String TEST = CONFIG_PROPERTIES.get("");
	
	//Selenium Test
	String CHROME_DRIVER = "webdriver.chrome.driver";
	String CHROME_DRIVER_PATH = CONFIG_PROPERTIES.get("chrome_Driver_Path");
	String APP_URL = CONFIG_PROPERTIES.get("app_url");

}
