package com.frm.web.pageactions;

import com.frm.utils.WebDriverUtil;
import com.frm.web.pageobjects.RepaymentCalculatorPageObjects;

public class RepaymentCalculatorPageActions 
{
	public void typeAmountToBorrow(String amount)
	{
		WebDriverUtil.enterText(RepaymentCalculatorPageObjects.amountTextBox, amount, true, true);
	}
	
	public void typeYearsToBorrow(String years)
	{
		WebDriverUtil.enterText(RepaymentCalculatorPageObjects.yearsTextBox, years, true, true);
	}
	
	public void selectRepaymentType(String option)
	{
		WebDriverUtil.selectDropDown(RepaymentCalculatorPageObjects.repaymentTypeDropDown, option);
	}

	public void selectProduct(String option) 
	{
		WebDriverUtil.selectMultiLvlDropDown(RepaymentCalculatorPageObjects.productDropDown, option);
	}

	public void clickCalculate() 
	{
		WebDriverUtil.clickIfVisible(RepaymentCalculatorPageObjects.calculateButton);
	}

	public String readTotalInterestCharged() 
	{
		return WebDriverUtil.getLabel(RepaymentCalculatorPageObjects.totalInterestLbl);
	}

	public String readTotalRepayment() 
	{
		return WebDriverUtil.getLabel(RepaymentCalculatorPageObjects.totalRepaymentLbl);
	}

	public void typeCustomRate(String customRate) 
	{
		WebDriverUtil.enterText(RepaymentCalculatorPageObjects.customRateTextBox, customRate, true, true);
	}
	
	public boolean isCustomRateTextBoxVisible() 
	{
		return WebDriverUtil.isElementVisible(RepaymentCalculatorPageObjects.customRateTextBox);
	}

	public void clickUseProductListLink() 
	{
		WebDriverUtil.clickALink(RepaymentCalculatorPageObjects.useProductListLink);
	}

	public void vijethTest() {

		try {
			WebDriverUtil.vijethTest("tv", "sydney");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
