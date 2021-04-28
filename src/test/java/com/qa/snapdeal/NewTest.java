package com.qa.snapdeal;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTest {
	public static WebDriver driver;
	@Test
  
  public void f() {

	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\jswamynathan\\Downloads\\chromedriver_win32\\chromedriver.exe");
      driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	  driver.get("https://www.cheapoair.com/");
  }
	  @Test(enabled = false)
	  public void af() {
		  driver.findElement(By.linkText("Track Credit/Refund")).click();
	      String MainWindow = driver.getWindowHandle();		
	      Set <String> s1 = driver.getWindowHandles();
	      Iterator<String> i1 = s1.iterator();
	      while(i1.hasNext())
	      {
	      	String childwindow = i1.next();
	      	driver.switchTo().window(childwindow);
			System.out.println("title of the page " + driver.getTitle());
	      }
	  }
}
