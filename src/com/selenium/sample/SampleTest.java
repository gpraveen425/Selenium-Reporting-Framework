package com.selenium.sample;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

//import org.junit.*;




import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SampleTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://taylormade.com.au";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  
  
  @Test
  public void Test01() throws Exception {
    driver.get(baseUrl + "/billspages/conversion_table.html");

    
	int i = 0;
	report_may03 obj = new report_may03();    
    
	String input[] = { "1", "2", "3", "4"};
	String output[] = { "0.868423", "2", "3", "4"};
	
	for (i = 0; i <= 3; i++) {
		obj.Addinput(input[i], i);
		obj.Expectedoutput(output[i], i);				
		driver.findElement(By.name("V1")).sendKeys(input[i]);
	    new Select(driver.findElement(By.name("U2"))).selectByVisibleText("Nautical mile");		
		obj.Output(driver.findElement(By.name("V2")).getAttribute("value"), i);
	    driver.findElement(By.name("V1")).clear();
	}   
    
	try {
		obj.Generatereport(3);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    

  }
  
  
  /*
  @Test
  public void Test02() throws Exception {
    driver.get(baseUrl + "/billspages/conversion_table.html");

    
	int i = 0;
	report_may03 obj = new report_may03();    
    
	String input[] = { "1", "2", "3", "4", "5"};
	String output[] = { "0.868423", "2", "3", "4", "5"};
	
	for (i = 0; i <= 4; i++) {
		obj.Addinput(input[i], i);
		obj.Expectedoutput(output[i], i);				
		driver.findElement(By.name("V1")).sendKeys(input[i]);
	    new Select(driver.findElement(By.name("U2"))).selectByVisibleText("Nautical mile");		
		obj.Output(driver.findElement(By.name("V2")).getAttribute("value"), i);
	    driver.findElement(By.name("V1")).clear();
	}   
    
	try {
		obj.Generatereport(4);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
//    assertEquals("0.868423", driver.findElement(By.name("V2")).getAttribute("value"));
  }	
*/
  @AfterClass
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

