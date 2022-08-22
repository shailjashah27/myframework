package com.frm.web.pageobjects;

import org.openqa.selenium.By;

public class RepaymentCalculatorPageObjects 
{
	public static By repaymentsCalculatorLink = By.xpath("//h6[contains(text(),'Repayment calculator')]");
	public static By amountTextBox = By.id("amount");
	public static By yearsTextBox = By.id("term");
	public static By repaymentTypeDropDown = By.id("interestOnly");
	public static By productDropDown = By.id("productId");
	public static By calculateButton = By.id("submit");
	public static By totalRepaymentLbl = By.xpath("//span[@data-tid='total-repayment']");
	public static By totalInterestLbl = By.xpath("//span[@data-tid='total-interest']");
	
	public static By customRateTextBox = By.id("customRate");
	public static By useProductListLink = By.xpath("//a[contains(text(),'input interest rate') and @id='useProductList']");
	

}
