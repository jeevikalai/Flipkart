package com.qa.flipkart;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
 

public class Flipkart {
	String mainwindow;
	public static WebDriver driver;
	  
  @BeforeTest(alwaysRun=true,description="Intialization")
  public void init() {
		
	  System.setProperty("webdriver.chromedriver", "C:\\Users\\jeevi\\Downloads\\chromedriver_win32\\chromedriver.exe");
	  driver  = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
      driver.navigate().to("https://www.flipkart.com");
	 
	  
	  
  }
  @Test(groups= "login",priority=0,description = "login into flipkart",dataProvider="usernameprovider")
  @Parameters({"username","password"})
  public void login(@Optional("Abc") String username, String password) throws InterruptedException {
	//Element not getting clicked as it is not within Viewport

	  	//Try to use JavascriptExecutor to bring the element within the Viewport
	  
	  WebElement login = driver.findElement(By.partialLinkText("Login"));
 
	  JavascriptExecutor jse2 = (JavascriptExecutor)driver;
	  jse2.executeScript("arguments[0].scrollIntoView()", login); 
 	 
 		driver.findElement(By.xpath("//div[@class='_39M2dM']//input[@type='text']")).sendKeys(username);
		driver.findElement(
				By.xpath("//input[@type='password']"))
				.sendKeys(password);
		driver.findElement(By.xpath("//div[@class='_1avdGP']//button[@type='submit']")).click();
		 Reporter.log("login completed" );
		 
  }
  @Test(groups= "login",priority=1,description = "Search for the product",dataProvider="usernameprovider")
	public void searchAndSelectProduct(String searchkey) throws InterruptedException {
	  
	
	/*
		//  a.moveToElement(driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[2]/div[1]/div/div[2]/div[1]/div/div[1]/div/div[2]/a"))).click().build().perform();
		  
		  try
		  {
			  a.moveToElement(driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"))).click().sendKeys("iphone").build().perform();
		  }
		  catch( StaleElementReferenceException e)
		  {
			  e.printStackTrace();
		  }
		//  a.moveToElement(driver.findElement(By.xpath("//button[@type='submit']"))).click().build().perform();
		//  driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys("iphone");
		  List<WebElement> list = driver.findElements(By.xpath("//ul[@class='col-11-12 _1PBbw8']//li/a/div[2]"));
		  for(int i = 0; i<list.size();i++)
		  {
			  System.out.println("list of suggestions from search box  " +list.get(i).getText());
		  }
		  try
		  {
			  System.out.println("get click element  " +list.get(4));
		  list.get(4).click();
		  }
		  catch(IndexOutOfBoundsException e)
		  {
			  driver.findElement(By.xpath("//button[@type='submit']")).click();
		  }
		//  Thread.sleep(5000);
		  driver.findElement(By.xpath("//button[@type='submit']")).click();

		  try
		  {
			  driver.findElement(By.xpath("//div[contains(@data-id,'MOBEMK62JSRHU85T')]//a[contains(@class,'_31qSD5')]")).click();
	 
		  }
		  catch(NoSuchElementException e)
		  {
				 
		  }*/
	//  Actions a = new Actions(driver);
//Thread.sleep(5000);
	  
	    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

	    
	//	WebDriverWait wait=new WebDriverWait(driver, 50);
		Thread.sleep(5000);
		WebElement searchbox = driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"));
		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		
	//	wait.until(ExpectedConditions.elementToBeClickable(submit));

		  searchbox.sendKeys(searchkey);
		  submit.click();
		  Reporter.log("Product search completed" );
		//div[contains(@class,'bhgxx2 col-12-12')]/div/div
		//div[contains(@class,'_1HmYoV hCUpcT')]/div[2]/child::div[2]
		  //clicks the second element fromm the search results
		  driver.findElement(By.xpath("//div[contains(@class,'_1HmYoV hCUpcT')]/div[2]/child::div[6]")).click();
		  		  
	 	}
  @Test(priority=2,description = "Get the product details from the search results")
  public void tc3() throws InterruptedException
  {
	  String childwindow = null;
	 

	 mainwindow = driver.getWindowHandle();
	 System.out.println("parent window  " +mainwindow);
	  Set<String> g = driver.getWindowHandles();
	  Iterator<String> it = g.iterator();
	  while(it.hasNext()) {
		   childwindow = it.next();
	  }
	  driver.switchTo().window(childwindow);
	  System.out.println("child window " +driver.getWindowHandle());
	  //clicks Add to cart button
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")).click();
	  //remove items from the cart.
	  List<WebElement> remove= driver.findElements(By.xpath("//span[contains(text(),'Remove')]"));
	  System.out.println("no of items in cart " +remove.size());

	  for(int i =0 ; i<remove.size(); i++)
	  {
		  Thread.sleep(5000);

		  remove.get(i).click();
	  }
	  Reporter.log("Product search and  switch to child window  completed" );
  }
  @Test(priority=3,description = "Filtering the results")
  public void tc4() throws InterruptedException
  {
	  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	  System.out.println("parent window  " +mainwindow);
	  driver.switchTo().window(mainwindow);
	  //selects min and max value from dropdown
	  Select s = new Select(driver.findElement(By.xpath("//div[@class='_1qKb_B']//select[@class='fPjUPw']")));
	  s.selectByValue("16000");
	// Thread.sleep(3000);
		WebDriverWait wait=new WebDriverWait(driver, 90);
		WebElement d2 =driver.findElement(By.xpath("//div[@class='_1YoBfV']//select[@class='fPjUPw']"));
		d2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_1YoBfV']//select[@class='fPjUPw']")));
	  s = new Select(d2);
	  s.selectByIndex(1);
	Thread.sleep(3000);
	 //clicks fAssured checkbox
	 WebElement fassured = driver.findElement(By.xpath("//div[@class='_2kFyHg _2uXT1C']//div[@class='_1p7h2j']"));
	// fassured= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_2kFyHg _2uXT1C']//div[@class='_1p7h2j']")));
	 fassured.click();
    Thread.sleep(3000);
	 //clicks Apple Brand check box
	 WebElement apple = driver.findElement(By.xpath("//div[@title='Apple']//div[@class='_1p7h2j']"));
	 
	// apple = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Apple']//div[@class='_1p7h2j']")));
	 apple.click();

    Thread.sleep(3000);
	 WebElement avail = driver.findElement(By.xpath("//div[contains(text(),'Availability')]"));
    //Thread.sleep(3000);
//	 avail =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Availability')]")));
	 avail.click();
	driver.findElement(By.xpath("//div[@title='Exclude Out of Stock']//div[@class='_1p7h2j']")).click();
	Thread.sleep(5000);
	 WebElement sort =  driver.findElement(By.xpath("//div[contains(text(),'Price -- Low to High')]"));
	// sort = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Price -- Low to High')]")));
	 sort.click();
	  WebElement women = driver.findElement(By.xpath("//li[@class='Wbt_B2 _1YVU3_']/span[contains(text(),'Women')]"));
	  women = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='Wbt_B2 _1YVU3_']/span[contains(text(),'Women')]")));
	  women.click();
	  WebElement jewel = driver.findElement(By.xpath("//a[@title='Silver Jewellery']"));
	  jewel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Silver Jewellery']")));
	  jewel.click();
	  Reporter.log("Product filter completed" );
  }
  @Test(groups= "login",priority=6,description = "Logout from the application")
  public void tc5()
  {
	  Actions a = new Actions(driver);
	  a.moveToElement(driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/span/div"))).build().perform();
	  a.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Logout')]"))).click().build().perform();
	  Reporter.log("logout completed" );
  }

  @DataProvider(name="usernameprovider")
 
	public  Object[][] getDataFromDataprovider(Method m)
    {
	        if(m.getName().equalsIgnoreCase("login")){
	        return new Object[][] {
	        	{ "jeevikalai@gmail.com", "Anuska123" },
	                { "username123", "password123" }
	               
	            };}
	        else{
	        return new Object[][] {
	                { "samsung phone" },
	                { "iphone" } 
	            };}       
	    }
  
  @AfterTest
  public void afterTest() {
	 // driver.quit();
  }

}
