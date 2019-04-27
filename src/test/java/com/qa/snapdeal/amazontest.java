package com.qa.snapdeal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class amazontest {
	WebDriver driver;
	Actions a;
	@BeforeTest
	public void initialization()
	{
		System.setProperty("webdriver.chromedriver", "C:\\Users\\jeevi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		 driver = new ChromeDriver();
	//	driver.get("https://www.amazon.com");
		//driver.manage().window().maximize();
	}
	@Test
	public void amazontc1() throws InterruptedException
	{
		
	//	a = new Actions(driver);
//		a.moveToElement(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))).click().keyDown(Keys.SHIFT).sendKeys("Legogirls").doubleClick().build().perform();
	//	a.moveToElement(driver.findElement(By.xpath("//input[@value='Go']"))).click().build().perform();
	//	Thread.sleep(8000);
	//	a.moveToElement(driver.findElement(By.xpath("//span[contains(@class,'a-size-small a-color-link')]"))).click().build().perform();

	}
	@Test
	public void amazontc2() throws InterruptedException
	{
		 driver.navigate().to("https://www.google.com");
		 Reporter.log("google search completed" );
		 driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Amazon");
		 //get the list of elements from the searchbx auto suggestion.
		 Thread.sleep(8000);
		 List<WebElement> l = driver.findElements(By.xpath("//ul[@role='listbox']//li//div[@class='sbtc']"));
		 for(int i =0;i<l.size();i++)
		 {
			 System.out.println("text  " + l.get(i) );
		 }
		 
		 l.get(4).click();
		 Reporter.log("Product search list clicked" );
	}

	
}
