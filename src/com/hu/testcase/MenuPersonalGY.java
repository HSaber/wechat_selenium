package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MenuPersonalGY {
	 WebDriver driver ;
	 private static Logger logger = Logger.getLogger(MenuPersonalGY.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	@Test(dependsOnGroups="MenuClean",groups="MenuGY")
	public void test() throws InterruptedException,Exception {
		methods.navigation(driver, "Menus", By.className("btn"));
		try {
			driver.findElement(By.linkText("Create a Menu")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("menu_name")))
					break;
			}
			System.out.println("Open create personalize menu page successfully!");
		} catch (Exception e) {
			System.out.println("Fail to open create personalize menu page !");
			logger.error("Fail to open create personalize menu page !");
		}

    	String o2ourl = null;
        if(methods.baseUrl.contains("dev"))
        		o2ourl="http://dev.callback.jingsocial.com/o2o/member/login/id/"+methods.mid;
        else if(methods.baseUrl.contains("staging"))
        	    o2ourl="http://staging.callback.jingsocial.com/o2o/member/login/id/"+methods.mid;
        else 
        	    o2ourl="http://callback.jingsocial.com/o2o/member/login/id/"+methods.mid;
        
		String menuDate = methods.timeDate2();
		String title = methods.timeDate();
		String[] SegmentArr={"Just+rainbow","FollowerTagName+MenuClick","FollowProfileCustomFieldName+ActionDate"};
		new Select(driver.findElement(By.id("match_rule"))).selectByVisibleText("GY2+PersonalMenu");
		//标签title
		driver.findElement(By.id("menu_name")).sendKeys("rainbow+rainbowgy");
		driver.findElement(By.id("maincd1")).click();
		driver.findElement(By.id("cdmcinpo")).click();
		driver.findElement(By.id("cdmcinpo")).clear();
		driver.findElement(By.id("cdmcinpo")).sendKeys("PersonalMenu" + menuDate);
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Dynamic Menu");
		new Select(driver.findElement(By.id("message_type"))).selectByVisibleText("Send a Text Message");
		driver.findElement(By.className("dynamic_wb")).clear();
		driver.findElement(By.className("dynamic_wb")).sendKeys("From Personalize Menu \n"
				+ "{{nickname}} clicked Dynamic Menu \n"
				+ "<a href=\""+o2ourl+"\">O2O WebSite </a>{{time}}好\n"
				+"---Default message");
        //PerDynamic
		String[] picUrlArr={"https://dev.jingsocial.com/upload/images/1477982072.jpg",
				"https://staging.jingsocial.com/upload/images/1473408574.jpg",
				"https://app.jingsocial.com/upload/images/1477982152.jpg"};
		String picUrl;
		if(methods.baseUrl.contains("dev"))
			 picUrl=picUrlArr[0];
		else if(methods.baseUrl.contains("staging"))		
			 picUrl=picUrlArr[1];
		else
			 picUrl=picUrlArr[2];
		

		for(int i=1;i<=SegmentArr.length;){
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[1]/select"))).selectByVisibleText(SegmentArr[i-1]);
			if(i==1){
				new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Picture");
				((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"img\")[2].setAttribute(\"src\",\""+picUrl+"\");");
				String str= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[2].getAttribute(\"src\");");
				while(str.isEmpty())
				{
				System.out.println(this.getClass().getSimpleName()+" Image 上传中");
				str= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[2].getAttribute(\"src\");");
				if(str.contains("upload/images/"))
					break;
				}
			}else
			{
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Text Message");
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).clear();
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).sendKeys("From Personalize Menu \n"
				+ "{{nickname}} clicked Dynamic Menu Info\n"
			    +"<a href=\""+o2ourl+"\">O2O WebSite </a>{{time}}好\n"
				+"---你属于"+SegmentArr[i-1]);
			}
		    if(!driver.findElement(By.id("segmentation_lists")).getText().contains("Segment "+(++i))){
		    	driver.findElement(By.className("add_segmentation")).click();
		    }	
		}

		StyleMethods.AddTag(driver, "GY2 PerMenu"+title);
		driver.findElement(By.className("segmentation_select")).click();
		driver.findElement(By.id("saveOrder")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.id("personal-menu-grid")))
				 break;
		 }
			String menuNum=driver.findElement(By.className("summary")).getText();
			String[] NumArr=methods.Getnum(menuNum);
			int MenuSum=Integer.parseInt(NumArr[2]);
			if(MenuSum>10)
			{
				Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").getElementsByTagName(\"li\").length;");
				driver.findElement(By.xpath(".//*[@id='yw0']/li["+str+"]/a")).click();
				while(true){
					String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-3)+"].className;");
					if(classStr.contains("selected"))
							break;
					}
				  AssertJUnit.assertTrue(driver.findElement(By.id("personal-menu-grid")).findElement(By.tagName("table")).getText().contains("GY2+PersonalMenu"));
			}
				else
				{
                AssertJUnit.assertTrue(driver.findElement(By.id("personal-menu-grid")).findElement(By.tagName("table")).getText().contains("GY2+PersonalMenu"));
				}
      System.out.println("GY2+PersonalMenu 创建成功!");
	}

}
