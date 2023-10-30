package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.interactive.testcase.HttpClient;

public class FormEventPartiTest {
	  WebDriver driver;
	  private static Logger logger = Logger.getLogger(FormEventPartiTest.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test(dependsOnGroups="UpFormevent",groups="FormeventParti")
	public void test() throws InterruptedException,Exception {
		
		driver.manage().window().maximize();
		String formurl=methods.UrlLink[0];
		String num=methods.Getnum(formurl)[1];
        String DateStr=methods.timeDate();
        String baseUrl=methods.baseUrl;
        baseUrl=baseUrl.substring(6,baseUrl.length());
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];

        String formUrl="Http:"+baseUrl+"formEvent/index?openid=&wid=@"+methods.mid+"&event_id="+num+"&open_id="+HttpClient.openidArr[p][q];
        logger.info(formUrl);
		driver.get(formUrl);
		
		while(true){
			Thread.sleep(500);
			if(methods.isElementPresent(driver, By.className("rich_media_title")))
				break;
		}
		
		String formeventName=driver.findElement(By.className("rich_media_title")).getText();
		System.out.println(driver.findElement(By.id("winnerTel")).getAttribute("value"));
		if(driver.findElement(By.id("winnerTel")).getAttribute("value").isEmpty())
		{	
		driver.findElement(By.id("winnerTel")).clear();
		driver.findElement(By.id("winnerTel")).sendKeys("1830210"+methods.timeDate2());
		}
		else			
		driver.findElement(By.id("winnerName")).clear();
		driver.findElement(By.id("winnerName")).sendKeys("huhuan"+methods.timeDate2());
		driver.findElement(By.id("friends")).clear();
		driver.findElement(By.id("friends")).sendKeys("0");
		driver.findElement(By.id("comments")).clear();
		driver.findElement(By.id("comments")).sendKeys(DateStr+"Comment \n /微笑  \n Hello There!!!。；？");
		driver.findElement(By.id("save-extra-btn")).click();
		Thread.sleep(2000);
		 if(methods.isAlertPresent(driver)){
          	String AlertText=methods.closeAlertAndGetItsText(driver);
          	if(AlertText.contains("人数已满"))
          	{
          		System.out.println(formeventName+"人数已满 未参加!");
          		logger.info(formeventName+"人数已满 未参加!");
          	}
          	else if(AlertText.contains("不在报名"))
          	{
          		System.out.println(formeventName+"已过期 无法参加!");
          	    logger.info(formeventName+"已过期 无法参加!");
          	}
		 }
          	else{
		AssertJUnit.assertEquals("感谢 您的 预约",driver.findElement(By.xpath("html/body/div[4]/div/p[1]")).getText());
		System.out.println("参加 "+formeventName+"成功!");
		  }

	}

}
