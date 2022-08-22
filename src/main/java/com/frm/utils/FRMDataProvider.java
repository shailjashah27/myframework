package com.frm.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.frm.constants.AppConstants;

public class FRMDataProvider
{
	@DataProvider (name = "data-provider")
	public Object[][] dataProvider(Method method, ITestContext context) throws IOException
	{
		String testDatafilePath = AppConstants.TEST_DATA_FOLDER_PATH + method.getDeclaringClass().getSimpleName() + ".json";
		String parentElement    = method.getName();
		
		Object[][] parsedData = JsonUtils.readJson(testDatafilePath, "$." + parentElement);
		
		return parsedData;
	} 

}
