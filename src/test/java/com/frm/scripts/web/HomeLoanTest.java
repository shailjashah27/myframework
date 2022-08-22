package com.frm.scripts.web;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.frm.utils.FRMDataProvider;
import com.frm.utils.StringUtils;
import com.frm.utils.WebDriverUtil;
import com.frm.web.pageactions.HomePageActions;
import com.frm.web.pageactions.RepaymentCalculatorPageActions;

public class HomeLoanTest 
{
	@BeforeSuite
	public void init(ITestContext context)
	{
			WebDriverUtil.setup(context);
			WebDriverUtil.launch();
			
			HomePageActions HomePageActions = new HomePageActions();
	    	HomePageActions.clickDissmissButton();
	    	HomePageActions.clickRepaymentsCalculatorLink();
	}

	@BeforeTest
	public void beforeTest(ITestContext context, Object[] data)
	{
		
	}
	
    @Test (dataProvider = "data-provider", dataProviderClass = FRMDataProvider.class, priority=1)
	public void homeLoanRepaymentUsingProductsTest(ITestContext context, Object[] data)
	{
    	Map<String, String> dataMap = StringUtils.getData(data);
    	
    	String amountToBorrow = dataMap.get("AmountToBorrow");	
    	String yearsToBorrow = dataMap.get("YearsToBorrow");	
    	String repaymentType = dataMap.get("RepaymentType");	
    	String product = dataMap.get("Product");				
    	String totalRepayment = dataMap.get("TotalRepayment");	
    	String totalInterest = dataMap.get("TotalInterest");	

    	RepaymentCalculatorPageActions repaymentCalculatorPageActions = new RepaymentCalculatorPageActions();
    	repaymentCalculatorPageActions.typeAmountToBorrow(amountToBorrow);
    	repaymentCalculatorPageActions.typeYearsToBorrow(yearsToBorrow);
    	repaymentCalculatorPageActions.selectRepaymentType(repaymentType);
    	repaymentCalculatorPageActions.selectProduct(product);
    	repaymentCalculatorPageActions.clickCalculate();
    	String totalRepaymentValue = repaymentCalculatorPageActions.readTotalRepayment();
    	String totalInterestChargedValue = repaymentCalculatorPageActions.readTotalInterestCharged();

    	assertEquals(totalRepaymentValue, totalRepayment, "Total Repayment amount doesn't match! Expected Value : " + totalRepayment + " Actual Value : " + totalRepaymentValue);
    	assertEquals(totalInterestChargedValue, totalInterest, "Total Interest Charged doesn't match! Expected Value : " + totalInterest + " Actual Value : " + totalInterestChargedValue);
    	
    	try 
    	{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
    	{
			e.printStackTrace();
		}

	}
    
    @Test (dataProvider = "data-provider", dataProviderClass = FRMDataProvider.class, priority=2)
   	public void homeLoanRepaymentUsingCustomRatesTest(ITestContext context, Object[] data)
   	{
       	Map<String, String> dataMap = StringUtils.getData(data);
       	
       	String amountToBorrow = dataMap.get("AmountToBorrow");	
       	String yearsToBorrow = dataMap.get("YearsToBorrow");	
       	String repaymentType = dataMap.get("RepaymentType");	
       	String customRate = dataMap.get("CustomRate");				
       	String totalRepayment = dataMap.get("TotalRepayment");	
       	String totalInterest = dataMap.get("TotalInterest");	

       	RepaymentCalculatorPageActions repaymentCalculatorPageActions = new RepaymentCalculatorPageActions();
       	repaymentCalculatorPageActions.typeAmountToBorrow(amountToBorrow);
       	repaymentCalculatorPageActions.typeYearsToBorrow(yearsToBorrow);
       	
       	if(!repaymentCalculatorPageActions.isCustomRateTextBoxVisible())
       	{
       		repaymentCalculatorPageActions.clickUseProductListLink();
       	}
       	
       	repaymentCalculatorPageActions.typeCustomRate(customRate);
       	repaymentCalculatorPageActions.clickCalculate();
       	repaymentCalculatorPageActions.selectRepaymentType(repaymentType);
       	String totalRepaymentValue = repaymentCalculatorPageActions.readTotalRepayment();
       	String totalInterestChargedValue = repaymentCalculatorPageActions.readTotalInterestCharged();

       	assertEquals(totalRepaymentValue, totalRepayment, "Total Repayment amount doesn't match! Expected Value : " + totalRepayment + " Actual Value : " + totalRepaymentValue);
       	assertEquals(totalInterestChargedValue, totalInterest, "Total Interest Charged doesn't match! Expected Value : " + totalInterest + " Actual Value : " + totalInterestChargedValue);
       	
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
