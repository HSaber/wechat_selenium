package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.interactive.testcase.HttpClient;

public class Html5ActionTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(Html5ActionTest.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test(dependsOnGroups="Html5",groups="Html5Action")
	public void test() throws InterruptedException,Exception {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		
		String HtmlUrl=methods.UrlLink[1];
		driver.manage().window().maximize();
		String[] num=new String[2];
		num=methods.Getnum(HtmlUrl);
        String baseUrl=methods.baseUrl;
        baseUrl=baseUrl.substring(6,baseUrl.length());
        Actions action = new Actions(driver); 
		driver.get("Http:"+baseUrl+"htmlFivePage/index?openid="+HttpClient.openidArr[p][q]+"&wid="+num[1]+"@"+methods.mid+"&ord=&rid=");
		Thread.sleep(2000);
		 
		   //模拟键盘enter键 
	    try {
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Thread.sleep(2000);
		
	// 鼠标拖拽动作，将 source 元素拖放到 target 元素的位置。
		 driver.findElement(By.id("iSlider-arrow"));

/*		Point initialPositon = drag1.getLocation();
		System.out.println(initialPositon);*/

		//action.clickAndHold(drag1).build().perform();
		 int height=driver.manage().window().getSize().getHeight();
		height=height*5/13;
		 System.out.println(height);
		
		 action.dragAndDropBy(null, 0, -height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, -height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, -height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, -height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, -height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);
		 action.dragAndDropBy(null, 0, height).build().perform();
		 driver.findElement(By.id("iSlider-wrapper")).click();
		 Thread.sleep(2000);		 
		// action.moveByOffset(0,-300);
		 action.release();
		 

	}

}
