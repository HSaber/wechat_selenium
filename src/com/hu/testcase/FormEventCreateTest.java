package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class FormEventCreateTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(FormEventCreateTest.class);
	public String FormTitle;

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="CustomFEField",groups="Formevent")
	public void test() throws InterruptedException, AWTException,Exception {
		
		 methods.navigation(driver, "Form Events",By.className("btn") );
		 		
		try{
			driver.findElement(By.className("btn")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("FormEvent_inter_name")))
					break;
			}
			System.out.println("Open create formevent page successfully!");
		}catch(Exception e){
			System.out.println("Fail to open create formevent page!");
			logger.error("Fail to open create formevent page!");
		}
	    	String titleC=methods.timeDate();
			driver.findElement(By.id("FormEvent_inter_name")).sendKeys("FormEvent_"+titleC);
			driver.findElement(By.id("FormEvent_title")).sendKeys("活动"+titleC);
			
			driver.findElement(By.id("FormEvent_status")).findElement(By.xpath("//option[@value='1']")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("FormEvent_location")).sendKeys("Location");
			driver.findElement(By.id("FormEvent_participate_times")).clear();
			driver.findElement(By.id("FormEvent_participate_times")).sendKeys("10");


			driver.findElement(By.id("FormEvent_start_date")).click();
			String hour;
			if(methods.timeDate2().substring(0, 2).equals("00"))
			 hour="0";
			else
			 hour=methods.timeDate2().substring(0, 2).replaceAll("0", "");

			methods.Loading(driver, By.xpath("html/body/div[2]/div[2]/div/div[1]/div["+(Integer.parseInt(hour)+1)+""+"]"));
			driver.findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]/div["+(Integer.parseInt(hour)+1)+""+"]")).click();
			String EndDate=methods.funcday(2,0);

			driver.findElement(By.id("FormEvent_end_date")).clear();
			driver.findElement(By.id("FormEvent_end_date")).sendKeys(EndDate);
			Thread.sleep(1000);
			driver.findElement(By.className("page_title")).click();

			//tags
			StyleMethods.AddTag(driver, "FormEvent_"+titleC);
			((JavascriptExecutor)driver).executeScript("CKEDITOR.instances['FormEvent_content'].setData('欢迎参加报名活动 -----create');");
			driver.findElement(By.xpath(".//*[@id='form-event-form']/div[6]/div[1]/label")).click();
			driver.findElement(By.xpath(".//*[@id='cke_29']/span[1]")).click();
			while(true){
				if(methods.isElementPresent(driver, By.className("emotions")))
					break;
			}
			Thread.sleep(1000);
			//Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='form-event-form']/div[8]/div[2]/div/ul/li[1]/i")).click();		    
			Thread.sleep(1000);
/*			driver.findElement(By.xpath(".//*[@id='cke_18']/span[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.linkText("上传")).click();
			Thread.sleep(1000);
			String parentPath = getClass().getResource("../../").getFile().toString();
			parentPath = parentPath + "/material/27.JPG";	
			File f1 = new File(parentPath);
			String num=methods.Getnum(driver.findElement(By.className("cke_dialog_ui_labeled_label")).getAttribute("id"));
			Integer m=Integer.parseInt(num)+1;		
			num=(Integer.parseInt(num)+1).toString();
			//cke_116_label
			((JavascriptExecutor)driver).executeScript("$(\"#cke_"+num+"_label\").attr(\"style\",'display:block')");
			System.out.println(driver.findElement(By.className("cke_dialog_ui_labeled_label")).getAttribute("id")+"    "+m.toString());
			driver.findElement(By.id("cke_"+m.toString()+"_fileInput_input")).sendKeys(f1.toString());
			driver.findElement(By.xpath(".//*[@id='cke_115_fileInput_input']")).sendKeys(f1.toString());
            Thread.sleep(2000);
			driver.findElement(By.linkText("上传到服务器")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//label[text()='宽度']//following::input[@class='cke_dialog_ui_input_text'][1]")).clear();
			driver.findElement(By.xpath("//label[text()='高度']//following::input[@class='cke_dialog_ui_input_text'][1]")).sendKeys("180");
			Thread.sleep(1000);
			driver.findElement(By.linkText("确定")).click();
			Thread.sleep(1000);
           */
			
			//sign up form setting
			driver.findElement(By.id("FormEvent_customer_name_comment")).clear();
			driver.findElement(By.id("FormEvent_customer_name_comment")).sendKeys("请输入您的姓名-name");
			driver.findElement(By.id("customer_name_required")).click();
			driver.findElement(By.id("toEavAttributeusername")).click();
			new Select(driver.findElement(By.id("eavAttributeSelectorusername"))).selectByVisibleText("Names");


			driver.findElement(By.id("FormEvent_telephone_comment")).clear();
			driver.findElement(By.id("FormEvent_telephone_comment")).sendKeys("请输入您的手机号码-telephone");
			driver.findElement(By.id("toEavAttributetelephone")).click();
		    new Select(driver.findElement(By.id("eavAttributeSelectortelephone"))).selectByVisibleText("Phone Number");

		    
			driver.findElement(By.id("FormEvent_friend_count_comment")).clear();
			driver.findElement(By.id("FormEvent_friend_count_comment")).sendKeys("请输入您的朋友数量-friendsaccount");
			driver.findElement(By.id("toEavAttributefriends")).click();
		    new Select(driver.findElement(By.id("eavAttributeSelectorfriends"))).selectByVisibleText("FriendsAccount"+titleC);
			
			driver.findElement(By.id("FormEvent_customer_comment")).clear();
			driver.findElement(By.id("FormEvent_customer_comment")).sendKeys("请输入您的留言-comment");
			driver.findElement(By.id("customer_comment_required")).click();
			driver.findElement(By.id("toEavAttributecomments")).click();
		    new Select(driver.findElement(By.id("eavAttributeSelectorcomments"))).selectByVisibleText("Comment"+titleC);

		    
			driver.findElement(By.id("FormEvent_welcome_message")).clear();
			driver.findElement(By.id("FormEvent_welcome_message")).sendKeys("欢迎"+"\n"+"参加"+"\n"+"活动");
			driver.findElement(By.id("FormEvent_thank_message")).clear();
			driver.findElement(By.id("FormEvent_thank_message")).sendKeys("感谢"+"\n"+"您的"+"\n"+"预约");
			driver.findElement(By.id("FormEvent_out_of_participate_times_message")).clear();
			driver.findElement(By.id("FormEvent_out_of_participate_times_message")).sendKeys("人数已满，"+"\n"+"欢迎下次再来");
			driver.findElement(By.id("FormEvent_already_signed_up_message")).clear();
			driver.findElement(By.id("FormEvent_already_signed_up_message")).sendKeys("您已预约"+"\n"+"无需重复预约");
			driver.findElement(By.id("FormEvent_form_event_closed_message")).clear();
			driver.findElement(By.id("FormEvent_form_event_closed_message")).sendKeys("活动"+"\n"+"已关闭");
			Thread.sleep(1000);
			//confirm-------------
			driver.findElement(By.name("yt0")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("promotion_url")))
					break;
			}
			AssertJUnit.assertEquals("Create Form Event Successful!",driver.findElement(By.className("page_title")).getText().toString());
			System.out.println("Create FormEvent_"+titleC+" successfully!");
			
			methods.UrlLink[0]=driver.findElement(By.id("promotion_url")).getText();
			

	}

}
