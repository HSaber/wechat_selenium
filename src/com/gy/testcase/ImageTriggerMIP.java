package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ImageTriggerMIP extends TestBase{
	private static Logger logger = Logger.getLogger(ImageTriggerMIP.class);
	 @Test(groups="imageTriggerMIP")
	  public void imageTriggerMIP() throws Exception {
		
		 navigation("Triggers");
	    
	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/a[2]/button")).click();
	    
	    //开始创建trigger
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("no conditional image trigger");
	    driver.findElement(By.id("Triggers_title")).clear();
	    driver.findElement(By.id("Triggers_title")).sendKeys("ImageTriggerMIP");
	    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
	    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Send Image");
	    
	    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Message");
	    driver.findElement(By.id("action_message")).clear();
	    driver.findElement(By.id("action_message")).sendKeys("selenium: {{nickname}},{{time}}好，你发送了image触发了这条消息，5s后将收到图片(Trigger send image)和neweditor多图文");
	    
	    driver.findElement(By.id("add_action")).click();
	    new Select(driver.findElement(By.name("Triggers[extra_action][2]"))).selectByVisibleText("Send a Image");
	    driver.findElement(By.xpath("(//input[@name='files[]'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@name='files[]'])[2]")).sendKeys(file+"/com/material/Trigger8.jpg");
	    //Thread.sleep(4000);
	    String src = null;
		src= driver.findElement(By.xpath("//div[@id='actions']/div[2]/div[1]/div[6]/div/img")).getAttribute("src");
		System.out.println("src的值是："+ src);
		while(src==null||src.equals("")){
			 src= driver.findElement(By.xpath("//div[@id='actions']/div[2]/div[1]/div[6]/div/img")).getAttribute("src");
			 System.out.println("image未上传完成,继续等待中！");
		}
		new Select(driver.findElement(By.xpath("(//select[@id='delay_schedule_seconds'])[2]"))).selectByVisibleText("5 Seconds");
		
		
	    driver.findElement(By.id("add_action")).click();
	    new Select(driver.findElement(By.xpath("(.//*[@id='Triggers_action'])[3]"))).selectByVisibleText("Send a Post");
	    new Select(driver.findElement(By.xpath("(.//*[@id='action_post'])[3]"))).selectByVisibleText("new editor多图文'selenium");
	    
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    //driver.findElement(By.xpath("(//input[@type='text'])[9]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    //driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("image trigger"+ time+Keys.ENTER);
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("image trigger"+ time);
	    driver.findElement(By.id("Triggers_is_once")).click();
	    Thread.sleep(1000);
	    
	    //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.name("yt0"))); 
	    //driver.findElement(By.xpath(".//*[@id='triggers-form']/div[19]/input[1]")).click();
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category.equals("no conditional image trigger")){
	    	System.out.println("第一行就是no conditional image trigger，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("no conditional image trigger");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		   
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category.equals("no conditional image trigger")&&b)
		    		break;
		    }
	    }
	    try {
	      AssertJUnit.assertEquals("no conditional image trigger", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
	    } catch (Error e) {
	    
	    	 logger.error("trigger创建后table中category显示错误");
	      Assert.fail("trigger创建后table中category显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("ImageTriggerMIP", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText());
	    } catch (Error e) {
	     
	    	 logger.error("trigger创建后table中title显示错误");
	      Assert.fail("trigger创建后table中title显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("Send Image", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[5]")).getText());
	    } catch (Error e) {
	      
	    	 logger.error("trigger创建后table中action type显示错误");
	      Assert.fail("trigger创建后table中action type显示错误");
	    }
	    try {
	        AssertJUnit.assertEquals("Messages,Image,Post", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[6]")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("trigger创建后table中触发操作显示错误");
	        Assert.fail("trigger创建后table中触发操作显示错误");
	      }
	    try {
	        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[7]")).getText().contains("你发送了image触发了这条消息，5s后将收到图片(Trigger send image)和neweditor多图文"));
	      } catch (Error e) {
	       
	    	  logger.error("trigger创建后table中第一个触发操作显示错误");
	        Assert.fail("trigger创建后table中第一个触发操作显示错误");
	      }
	   /* try {
	        AssertJUnit.assertEquals("", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[8]")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("trigger创建后table中触发延时显示错误");
	       Assert.fail("trigger创建后table中触发延时显示错误");
	      }*/
	    try {
	        AssertJUnit.assertEquals("No", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[9]")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("trigger创建后table中是否触发一次显示错误");
	        Assert.fail("trigger创建后table中是否触发一次显示错误");
	      }
	   
	   try {
	      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[10]/img")).getAttribute("src"));
	    } catch (Error e) {
	     
	    	 logger.error("trigger创建后table中触发status显示错误");
	      Assert.fail("trigger创建后table中触发status显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("0", driver.findElement(By.linkText("0")).getText());
	    } catch (Error e) {
	     
	    	 logger.error("trigger创建后table中触发次数显示错误");
	      Assert.fail("trigger创建后table中触发次数显示错误");
	    }
	    
	    
	  }
}
