package com.frm.utils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.frm.constants.AppConstants;

public class WebDriverUtil 
{
	private static WebDriver webDriver = null;
	private static WebDriverWait wait = null;
	
	public static void setup(ITestContext context)
	{
		String chromeVersion = context.getCurrentXmlTest().getParameter("chrome.version");

		if(chromeVersion == null)
		{
			chromeVersion = "103";
		}
		
		System.setProperty(AppConstants.CHROME_DRIVER, AppConstants.CHROME_DRIVER_PATH.replace("XX", chromeVersion));  
	}
	
	public static WebDriver launch()
	{
		webDriver = new ChromeDriver();
		webDriver.get(AppConstants.APP_URL);
		webDriver.manage().window().maximize();
		
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(webDriver, 10000);
		
		return webDriver;
	}
	
	public static void cleanUpWebDriver() 
	{
		if (webDriver != null) 
		{
			try
			{
				webDriver.quit();
			}catch (Exception e)
			{
				e.printStackTrace();
			}
			
			webDriver = null;
		}
	}

	public static void clickIfVisible(By element) 
	{
		if(isElementVisible(element))
		{
			webDriver.findElement(element).click();;
		}
	}
	
	public static void clickIfExists(By element) 
	{
		if(isElementPresent(element))
		{
			webDriver.findElement(element).click();
		}
	}
	
	public static void click(By element) 
	{
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		webDriver.findElement(element).click();
	}

	public static void enterText(By element, String text, boolean isClear, boolean isCntlA) 
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		if(isClear)
		{
			webDriver.findElement(element).clear();
		}
		
		if(isCntlA)
		{
			webDriver.findElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
		}
		else
		{
			webDriver.findElement(element).sendKeys(text);
		}
	}
	
	public static void enterText(By element, String text) 
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		webDriver.findElement(element).sendKeys(text);
	}

	public static void selectDropDown(By element, String option)
	{
		Select dropDown = new Select(webDriver.findElement(element));
		dropDown.selectByVisibleText(option);
	}
	
	public static void selectMultiLvlDropDown(By element, String option)
	{
		Select dropDown1 = new Select(webDriver.findElement(element));
		List<WebElement> optionsList = dropDown1.getOptions();
		
		for(WebElement optionElement : optionsList)
		{
			if(option.equals(optionElement.getText()))
			{
				optionElement.click();
				break;
			}
		}
	}

	public static String getLabel(By element) 
	{
		return webDriver.findElement(element).getText();
	}
	
	public static boolean isElementPresent(By element) 
	{
		List<WebElement> elementList = webDriver.findElements(element);

		if(elementList.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isElementVisible(By element)
	{
	    return webDriver.findElement(element).isDisplayed();
	}

	public static void clickALink(By element)
	{
		List<WebElement> elementList = webDriver.findElements(element);

		for(WebElement element1 : elementList)
		{
			if(element1.isDisplayed())
			{
				element1.click();
				break;
			}
		}
	}

	public static void vijethTest(String searchString, String searchRegion) throws InterruptedException {
		//webDriver.get("https://www.gumtree.com.au/");
		//webDriver.manage().window().maximize();
		//Thread.sleep(3000);

		webDriver.findElement(By.id("search-query")).sendKeys(searchString);
		Thread.sleep(3000);

		webDriver.findElement(By.id("search-area")).sendKeys(searchRegion);
		Thread.sleep(3000);

		webDriver.findElement(By.id("categoryId-wrp")).click();
		Thread.sleep(3000);
		WebElement category = webDriver.findElement(
				By.xpath("//div[@id='categoryId-wrpwrapper']//li//div[@id='categoryId-wrp-option-20045']"));
		hoverAndClick(webDriver, category);
		Thread.sleep(3000);

		webDriver.findElement(By.id("srch-radius-wrp")).click();
		Thread.sleep(3000);
		WebElement radius = webDriver.findElement(
				By.xpath("//div[@id='srch-radius-wrpwrapper']//li//div[@id='srch-radius-wrp-option-20']"));
		hoverAndClick(webDriver, radius);
		Thread.sleep(3000);

		webDriver.findElement(By.className("header__search-button")).click();
		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("window.scrollBy(0,1800)");

		List<WebElement> searchResult = webDriver.findElements(By.xpath(
				"//section[@class='search-results-page__user-ad-collection']//div[@class='user-ad-collection-new-design']//div[@class='user-ad-collection-new-design__wrapper--row']//a"));
		Random rand = new Random();
		int randomElement = rand.nextInt(searchResult.size() - 1);
		WebElement randomSelect = searchResult.get(randomElement);
		hoverAndClick(webDriver, randomSelect);

		Thread.sleep(3000);
		String breadCrumbsAdId = webDriver
				.findElement(
						By.xpath("//div[@id='breadcrumbs__desktop-sentinel']//span[@class='breadcrumbs__summary']"))
				.getText();

		if (breadCrumbsAdId.split(" ")[2].isBlank()) {
			System.out.println("Breadcrumbs not present");
		} else if (isNumeric(breadCrumbsAdId.split(" ")[2])) {
			System.out.println("Breadcrumbs AD ID present and is a Numeric Value");
		}

		/***********************************************************************************/

		List<WebElement> similarAds = webDriver.findElements(By.xpath(
				"//div[@class='panel-body vip-similar-ads__slider-container']//ul[@class='slider__item-wrapper']//li"));
		if (similarAds.size() > 0) {
			System.out.println("Similar Ads present");
		} else {
			System.out.println("Similar Ads not present");
		}
	}

	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			Long d = Long.parseLong(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private static void hoverAndClick(WebDriver driver, WebElement WebElement) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(WebElement);
		Thread.sleep(3000);
		action.click().build().perform();

	}
}
