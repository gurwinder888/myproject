package GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;


public class WebDriverUtility {

	
	 private WebDriver driver;
	// private WebDriverWait wait;
	 
	public WebDriverUtility(WebDriver driver){
		this.driver = driver;
        //this.wait = new WebDriverWait(driver, 30);
	}
	
	public void maximizeWindow(WebDriver driver)//option 2
	{
		driver.manage().window().maximize(); //browser launched and maximize
	}
	
	
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	public void waitForElementLocatedAt(WebDriver driver ,WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   wait.until(ExpectedConditions.presenceOfElementLocated((By) element));

	}
	
	
	
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}
	
	
	public void waitForElementToBeVisible(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public void waitForElementToBeClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
		
		

	
	
	
	public void waitForElementVisisbleAtPlace(WebDriver driver, String e1) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(e1)));
	}
	
	
	public void handleDropDown(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	
	public void handleDropDown(WebElement element,String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	
	public void handleDropDown(String text,WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	
	public void mouseHoverAction(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	
	public void moveAndClick(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
	}
	
	
	public void rightClickAction(WebDriver driver)
	{
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
	}
	
	
	public void doubleClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
	
	public void dragAndDropAction(WebDriver driver, WebElement srcEle, WebElement dstEle)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(srcEle, dstEle).perform();;
	}
	
	
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	
	public void switchToFrame(WebDriver driver, String NameOrID)
	{
		driver.switchTo().frame(NameOrID);
	}
	
	
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	
	public void scrollDownAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500);", "");
	}
	
	
	public void scrollUpAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-500);", "");
	}
	
	
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	
	public String captureScreenShot(WebDriver driver, String screenshotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE); // temporary location
		File dst = new File("Screenshots/ScreenShot"+screenshotName+".png");
		                                     //Scenario1.png 
		Files.copy(src, dst);
		
		return dst.getAbsolutePath();// used for extent reports
	}
	
	
	public void switchToWindow(WebDriver driver, String partialWinTitle)
	{
		//Step 1: get all the window IDs
		Set<String> allWinIds = driver.getWindowHandles();
		
		//Step 2: Navigate thru each window
		for(String winID:allWinIds)
		{
			//Step 3: switch to each window and capture the title
			String actTitle = driver.switchTo().window(winID).getTitle();
			
			//Step 4: Compare act title with expected partial Title
			if(actTitle.contains(partialWinTitle))
			{
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
