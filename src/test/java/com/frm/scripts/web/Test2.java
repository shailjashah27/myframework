package com.frm.scripts.web;

import com.frm.utils.WebDriverUtil;
import com.frm.web.pageactions.RepaymentCalculatorPageActions;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Test2
{
	@BeforeSuite
	public void init(ITestContext context)
	{
			WebDriverUtil.setup(context);
			WebDriverUtil.launch();



	}

	@BeforeTest
	public void beforeTest(ITestContext context, Object[] data)
	{
		
	}

    @Test
   	public void vTest()
   	{
       	RepaymentCalculatorPageActions repaymentCalculatorPageActions = new RepaymentCalculatorPageActions();
       	repaymentCalculatorPageActions.vijethTest();


       	try
       	{
   			Thread.sleep(1000);
   		} catch (InterruptedException e) 
       	{
   			e.printStackTrace();
   		}

   	}


	@AfterSuite(alwaysRun = true)
	public void afterClass() 
	{
		WebDriverUtil.cleanUpWebDriver();
	}

}
