package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PollParti {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(PollParti.class);
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
		
		 String timeStr=methods.timeDate();
		 Actions builder=new Actions(driver);
		 builder.moveToElement(driver.findElement(By.linkText("Smart Posts"))).click().perform();
		 builder.moveToElement(driver.findElement(By.linkText("Post Lists"))).click().perform();
		try{
			driver.findElement(By.linkText("Sent Posts List")).click();
			while(true){
				if(methods.isElementPresent(driver, By.id("post-send-queue-grid")))
					break;
			}
			System.out.println("Open follower list page successfully!");
		}
		catch (Exception e){
			System.out.println("fail to open follower list page!");
			logger.error("fail to open follower list page!");
		}
		new Select(driver.findElement(By.name("GroupMessageRecord[content_type]"))).selectByVisibleText("Text & Picture");
//		new Select(driver.findElement(By.name("GroupMessageRecord[message_type]"))).selectByVisibleText("All Followers");
		Thread.sleep(5000);
		String postLink=null,postTitle=null;
		for (int i=1;i<=10;i++)
		{
			postTitle=driver.findElement(By.xpath(".//*[@id='group-message-record-grid']/div[1]/table/tbody/tr["+i+"]/td[3]/a")).getText();
			if(postTitle.equals("multi'post多图文“测试” selenium")){
				postLink=driver.findElement(By.xpath(".//*[@id='group-message-record-grid']/div[1]/table/tbody/tr["+i+"]/td[3]/a")).getAttribute("href");
				break;
				}
		}
		System.out.println(postLink);
		String[] postId=methods.Getnum(postLink);
        String baseUrl=methods.baseUrl;
        baseUrl=baseUrl.substring(6,baseUrl.length());
        int m;
        if(baseUrl.contains("dev")){
        	if(methods.mid.equals("33"))
        	m=0;
        	else
        	m=1;
        }else if(baseUrl.contains("staging")){
        	if(methods.mid.equals("59"))
        	m=2;
        	else if(methods.mid.equals("68"))
        	m=3;
        	else
        	m=4;
        }else {
        	if(methods.mid.equals("42"))
        	m=5;
        	else
        	m=6;
        }
        	
		String PollLink="Http:"+baseUrl+"/artview/detail?wid="+methods.mid+"&rid="+postId[0]+"&ord=0&openid="+methods.pollopenid[m];
		System.out.println(PollLink);
		driver.get(PollLink);
		while(true){
			if(methods.isElementPresent(driver, By.id("activity-name")))
				break;
		}
        if(!methods.isElementPresent(driver, By.xpath(".//*[@id='vote-form']/input[3]"))){
        	if(1==1)
        		System.out.println("已经参加过该poll，不能重复提交");
        	else
        		System.out.println("该poll已过期！");
        }
		
		driver.findElement(By.xpath(".//*[@id='vote-form']/div[1]/ul/li/div[2]/input")).click();//初二
		driver.findElement(By.xpath(".//*[@id='vote-form']/div[2]/ul/li/div[2]/input")).click();//女
		driver.findElement(By.xpath(".//*[@id='vote-form']/div[3]/ul/li/div[2]/input")).click();//否
		driver.findElement(By.xpath(".//*[@id='vote-form']/div[4]/ul/li/div[1]/input")).click();//电脑上网
		driver.findElement(By.xpath(".//*[@id='vote-form']/div[4]/ul/li/div[2]/input")).click();//手机上网
		driver.findElement(By.xpath(".//*[@id='vote-form']/div[5]/ul/li/div[2]/input")).click();//有时逃课
		driver.findElement(By.xpath(".//*[@id='vote-form']/div[6]/ul/li/p/textarea")).sendKeys("比例“9:1”\n"+timeStr);

		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='vote-form']/input[3]")).click();
		while(true){
			if(methods.isElementPresent(driver, By.xpath(".//*[@id='vote-form']/div[1]/ul/li/div[1]/div/i")))
				break;
		}
	    AssertJUnit.assertTrue(driver.findElement(By.xpath(".//*[@id='vote-form']/div[1]/ul/li/div[2]/label")).getText().contains("已选"));
	 //   System.out.println(methods.pollopenid[m]+"参加poll成功！");
	}

}
