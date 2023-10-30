package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ActionRecordTest {
	 WebDriver driver;

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test
	public void test() throws InterruptedException,Exception {
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElement(By.linkText("Analytics"))).click().perform();
		builder.moveToElement(driver.findElement(By.linkText("Follower Analytics"))).click().perform();
		try{
			driver.findElement(By.linkText("Follower Actions Log")).click();
			while(true){
				if(methods.isElementPresent(driver,By.name("UserActionRecord[openid]")))
					break;
			}
			AssertJUnit.assertEquals("Follower Actions Log",driver.findElement(By.className("page_title")).getText());
			System.out.println("Open action record successfully!");	
			}catch(Exception e){
			System.out.println("Fail to action record page !");	
			}
		driver.findElement(By.name("UserActionRecord[openid]")).sendKeys("H.'");
		driver.findElement(By.name("UserActionRecord[openid]")).sendKeys(Keys.ENTER);		
		Thread.sleep(4000);
		AssertJUnit.assertTrue(driver.findElement(By.xpath(".//*[@id='menu-record-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText().contains("H.'"));
        
        AssertJUnit.assertTrue(driver.findElement(By.tagName("table")).getText().contains("Got Coupon"));
        //Viewed Html Five Page
        System.out.println("卡券领取成功!");
        
        AssertJUnit.assertTrue(driver.findElement(By.tagName("table")).getText().contains("Viewed Html Five Page"));
        System.out.println("打开Html5成功!");
        
        
        AssertJUnit.assertTrue(driver.findElement(By.tagName("table")).getText().contains("Custom Data Event"));
        AssertJUnit.assertTrue(driver.findElement(By.tagName("table")).getText().contains("Html5_"+methods.timeDate()));
        System.out.println("JS tracking 正常!");
        
        new Select(driver.findElement(By.name("UserActionRecord[type]"))).selectByVisibleText("Clicked Menu");
        Thread.sleep(5000);
        AssertJUnit.assertTrue(driver.findElement(By.tagName("table")).getText().contains("Formevent"));
        System.out.println("menu action 正常!");
        
        new Select(driver.findElement(By.name("UserActionRecord[type]"))).selectByIndex(0);
        Thread.sleep(5000);
        AssertJUnit.assertTrue(driver.findElement(By.tagName("table")).getText().contains("Followed"));
        AssertJUnit.assertTrue(driver.findElement(By.tagName("table")).getText().contains("Scan QR Code"));
        AssertJUnit.assertTrue(driver.findElement(By.tagName("table")).getText().contains("Unsubscribe"));
        System.out.println("取关加扫描二维码关注 正常！");
	}

}
