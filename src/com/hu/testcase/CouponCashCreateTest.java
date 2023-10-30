package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class CouponCashCreateTest {
	 WebDriver driver;
	Logger logger = Logger.getLogger("log.text");

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(groups="coupon")
	public void test() throws InterruptedException,Exception {
		
		String titleD=methods.timeDate();
		String titleC=methods.getStringRandom(9);

		 try {
             LoggerUtil.setLogingProperties(logger);           
         } catch (SecurityException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
		 Actions builder=new Actions(driver);
		 builder.moveToElement(driver.findElement(By.linkText("Engagement"))).click().perform();
		 Thread.sleep(1000);
		try{ 
			    driver.findElement(By.linkText("Coupon Cards")).click();
				Thread.sleep(3000);
			//	System.out.println("open coupon list page successfully!--create");
				logger.log(Level.INFO, "open coupon list page successfully!--create");
		}
		catch(Exception e){
			   System.out.println("fail to open coupon page!--create");
			   logger.log(Level.INFO, "fail to open coupon page!--create");
		}
		
				driver.findElement(By.id("js_add_card")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//input[@id='checkbox2'])[2]")).click();
				driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("Cards_internal_name")).sendKeys("CashCoupon"+titleD);
				driver.findElement(By.linkText("#55BD47")).click();
			    driver.findElement(By.linkText("#509fc9")).click();
			  //加tag
			    driver.findElement(By.id("tags")).click();
			    driver.findElement(By.cssSelector("li.tagInput")).click();		   
			    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
			    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("CashCouponCard_"+titleD);
			   //模拟键盘enter键 
			    try {
					Robot r=new Robot();
					r.keyPress(KeyEvent.VK_ENTER);
				} catch (AWTException e) {
					e.printStackTrace();
				}
			   
			    Thread.sleep(2000);
			    driver.findElement(By.id("Cards_sub_title")).sendKeys("Subtitle"+titleC);             
			    driver.findElement(By.xpath(".//*[@id='js_begin_time_container']/select")).click();
			    driver.findElement(By.xpath(".//*[@id='js_begin_time_container']/div/div[3]/div[3]/div[1]/div[3]")).click();
			    Thread.sleep(1000);
			    driver.findElement(By.xpath(".//*[@id='js_begin_time_container']/div/div[3]/div[3]/ul[2]/li[12]")).click();	
			    Thread.sleep(1000);
			    driver.findElement(By.id("Cards_reduce_cost")).clear();
			    driver.findElement(By.id("Cards_reduce_cost")).sendKeys("10");
			    driver.findElement(By.id("Cards_title")).sendKeys(titleC);
			    driver.findElement(By.id("Cards_least_cost")).clear();
			    driver.findElement(By.id("Cards_least_cost")).sendKeys("100");
                Thread.sleep(2000);
			    
				//Pin Stamp Set//		
			    //Actions action =new Actions(driver);
				WebElement pin=driver.findElement(By.id("js_destroy_type_preview"));		
			   // action.moveToElement(pin).build().perform();
			    
			    Actions action = new Actions(driver); 
			    //action.clickAndHold();// 鼠标悬停在当前位置，既点击并且不释放
			    action.moveToElement(pin).click().perform();// 鼠标悬停在 onElement 元素的位置
			    
			//	Thread.sleep(5000);
			//	driver.findElement(By.xpath("//div[@id='js_preview_area']/div/div/div[2]/div[3]/a/i")).click();				
				Thread.sleep(2000);											
						 
				driver.findElement(By.xpath("//div[@id='js_destroy_type']/div/div[2]/label/i")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("Cards_notice")).clear();
				driver.findElement(By.id("Cards_notice")).sendKeys("usage notice");			     
				Thread.sleep(1000);
				driver.findElement(By.id("Cards_quantity")).clear();
				driver.findElement(By.id("Cards_quantity")).sendKeys("10");
				Thread.sleep(3000);
				//action.release();

				//Default Coupons Details
				WebElement detail=driver.findElement(By.xpath(".//*[@id='js_preview_area']/div/div/div[3]/ul/li[1]/div[1]"));
				//WebElement detail=driver. findElement(By.xpath(".//*[@id='js_preview_area']/div/div/div[3]/ul/li[1]/div[1]/div/p"));								
				action.moveToElement(detail).click().perform();
				//Thread.sleep(4000);
			//    driver.findElement(By.cssSelector("li.msg_card_section.js_preview > div.msg_card_mask > a.js_edit_icon.edit_oper")).click();
			    		//.xpath(".//*[@id='js_preview_area']/div/div/div[3]/ul/li[1]/div[2]/a/i")).click();
			    Thread.sleep(2000);    
			    driver.findElement(By.id("Cards_description")).clear();
			    driver.findElement(By.id("Cards_description")).sendKeys("instructions");
			    driver.findElement(By.id("Cards_service_phone")).clear();
			    driver.findElement(By.id("Cards_service_phone")).sendKeys("customer service phone");
			    Thread.sleep(2000);
			  //  action.release();
			    
				//Apply to Stores//			    
			    ///div/p
				WebElement stores=driver.findElement(By.xpath(".//*[@id='js_preview_area']/div/div/div[3]/ul/li[2]/div[1]"));	
				action.moveToElement(stores).click().perform();
				//Thread.sleep(4000);
			//	driver.findElement(By.xpath(".//*[@id='js_preview_area']/div/div/div[3]/ul/li[2]/div[2]/a/i")).click();	
				Thread.sleep(2000); 
				//suitable store
				driver.findElement(By.xpath(".//*[@id='js_edit_area']/div[5]/div/div/div[1]/div/div/div[3]/label/i")).click();
				Thread.sleep(1000);
				//coustom URL
				driver.findElement(By.xpath(".//*[@id='js_edit_area']/div[5]/div/strong/div/div[1]/label[2]/i")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("Cards_custom_url_name")).sendKeys("UrlC");
				driver.findElement(By.id("Cards_custom_url")).sendKeys("http://www.baidu.com");
				driver.findElement(By.id("Cards_custom_url_sub_title")).sendKeys("NoteC");						
				//Promotion URL
				driver.findElement(By.xpath(".//*[@id='js_edit_area']/div[5]/div/strong/div/div[2]/label[2]/i")).click();
				driver.findElement(By.id("Cards_promotion_url_name")).sendKeys("UrlP");
				driver.findElement(By.id("Cards_promotion_url")).sendKeys("http://www.baidu.com");
				driver.findElement(By.id("Cards_promotion_url_sub_title")).sendKeys("NoteP");
				Thread.sleep(2000);
				action.release();			    

				//submit--------
				driver.findElement(By.xpath(".//*[@id='js_submit']/button")).click();
				Thread.sleep(5000);
				String NewCoupon=driver.findElement(By.xpath(".//*[@id='cards-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText();
				try{
					
					AssertJUnit.assertEquals("CashCoupon"+titleD,NewCoupon);
					System.out.println("create "+NewCoupon+" successfully!");
					logger.log(Level.INFO, "create "+NewCoupon+" successfully!");
				}
				catch (Exception e){
					System.out.println("fail to create "+NewCoupon+"!");
					logger.log(Level.INFO, "fail to create "+NewCoupon+"!");
				}
			
			
}

}

