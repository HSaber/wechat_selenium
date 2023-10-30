package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class MessageTriggerPMI extends TestBase{
	private static Logger logger = Logger.getLogger(MessageTriggerPMI.class);
	//@Test(groups="messageTriggerPMI")
	@Test(groups="messageTriggerPMI",dependsOnGroups="segmentation48",alwaysRun=true)
  public void messageTriggerPMI() throws Exception {
	
	  navigation("Triggers");
    //创建conditional为48小时，触发次数大于一次的multi trigger:  reply post& message &image
    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/a[2]/button")).click();
    //必填项校验
    driver.findElement(By.name("yt0")).click();
    try {
      AssertJUnit.assertEquals("Action to Take cannot be blank.", driver.findElement(By.cssSelector("div.errorSummary > ul > li")).getText());
    } catch (Error e) {
     
    	logger.error("必填项校验失败，没有显示Action to Take cannot be blank");
     Assert.fail("必填项校验失败，没有显示Action to Take cannot be blank");
    }
    try {
      AssertJUnit.assertEquals("Triggers cannot be blank.", driver.findElement(By.xpath("//form[@id='triggers-form']/div/ul/li[3]")).getText());
    } catch (Error e) {
      
    	logger.error("必填项校验失败，没有显示Triggers cannot be blank.");
     Assert.fail("必填项校验失败，没有显示Triggers cannot be blank.");
    }
    try {
      AssertJUnit.assertEquals("Title cannot be blank.", driver.findElement(By.cssSelector("div.errorMessage")).getText());
    } catch (Error e) {
     
    	logger.error("必填项校验失败，没有显示Title cannot be blank.");
      Assert.fail("必填项校验失败，没有显示Title cannot be blank.");
    }
    try {
      AssertJUnit.assertEquals("Action to Take cannot be blank.", driver.findElement(By.cssSelector("div.row.action_type > div.errorMessage")).getText());
    } catch (Error e) {
     
    	logger.error("必填项校验失败，没有显示Action to Take cannot be blank.");
      Assert.fail("必填项校验失败，没有显示Action to Take cannot be blank.");
    }
    //开始创建trigger
    driver.findElement(By.id("Triggers_category")).clear();
    driver.findElement(By.id("Triggers_category")).sendKeys("48hour&multi2");
    driver.findElement(By.id("Triggers_title")).clear();
    driver.findElement(By.id("Triggers_title")).sendKeys("MessageTriggerPMI");
    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Sent Message");
    driver.findElement(By.id("one_message")).click();
    new Select(driver.findElement(By.id("matching_type"))).selectByVisibleText("Full Matching");
    driver.findElement(By.id("event_message")).clear();
    driver.findElement(By.id("event_message")).sendKeys("MTP");
    
    driver.findElement(By.cssSelector("button.add_new_rule")).click();
    new Select(driver.findElement(By.xpath("(//select[@id='matching_type'])[2]"))).selectByVisibleText("Fuzzy Matching");
    driver.findElement(By.xpath("(//input[@id='event_message'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@id='event_message'])[2]")).sendKeys("MTP");
    driver.findElement(By.cssSelector("button.add_new_rule")).click();
    driver.findElement(By.xpath("(//input[@id='event_message'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@id='event_message'])[3]")).sendKeys("MTP2");
    
    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("selenium48hour");
    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Post");
    new Select(driver.findElement(By.id("action_post"))).selectByVisibleText("reply'post多图文“测试” selenium");
    driver.findElement(By.id("delay_radio_1")).click();
    new Select(driver.findElement(By.id("delay_schedule_seconds"))).selectByVisibleText("1 Second");//暂时改成1s，等bug改好了再恢复成5 Seconds
    
    driver.findElement(By.id("add_action")).click();
    new Select(driver.findElement(By.name("Triggers[extra_action][2]"))).selectByVisibleText("Send a Message");
    driver.findElement(By.xpath("(//textarea[@id='action_message'])[2]")).clear();
    driver.findElement(By.xpath("(//textarea[@id='action_message'])[2]")).sendKeys("selenium: {{nickname}},{{time}}好，you are in 48 hout list &send MTP trigger message & take 9s");
    new Select(driver.findElement(By.xpath("(//select[@id='delay_schedule_seconds'])[2]"))).selectByVisibleText("4 Seconds");
    
    driver.findElement(By.id("add_action")).click();
    new Select(driver.findElement(By.name("Triggers[extra_action][3]"))).selectByVisibleText("Send a Image");
    driver.findElement(By.xpath("(//input[@name='files[]'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@name='files[]'])[3]")).sendKeys(file+"/com/material/2.jpg");
    //Thread.sleep(4000);
    String src = null;
	src= driver.findElement(By.xpath("//div[@id='actions']/div[3]/div[1]/div[6]/div/img")).getAttribute("src");
	System.out.println("src的值是："+ src);
	while(src==null||src.equals("")){
		 src= driver.findElement(By.xpath("//div[@id='actions']/div[3]/div[1]/div[6]/div/img")).getAttribute("src");
		 System.out.println("image未上传完成,继续等待中！");
	}
   /* try {
        System.out.println(driver.findElement(By.xpath("//div[@id='actions']/div[3]/div/div[6]/div/img")).getAttribute("src"));
    	assertNotEquals(null,driver.findElement(By.xpath("//div[@id='actions']/div[3]/div/div[6]/div/img")).getAttribute("src"));
      } catch (Error e) {
        verificationErrors.append(e.toString());
        System.out.println("trigger中图片没有上传成功");
      }*/
    
    driver.findElement(By.name("yt0")).click();
    Thread.sleep(5000);
    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
    if(category.equals("48hour&multi2")){
    	System.out.println("第一行就是48hour&multi2，不需要搜素查询");
    }else{
    	driver.findElement(By.name("Triggers[category]")).clear();
	    driver.findElement(By.name("Triggers[category]")).sendKeys("48hour&multi2");
	    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
	   
	    while(true){
	    	boolean b = true;
	    	try{
	    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    	}catch(org.openqa.selenium.StaleElementReferenceException e){
	    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
	    		 b=false;
	    	}
	    	if(category.equals("48hour&multi2")&&b)
	    		break;
	    }
    }
    try {
      AssertJUnit.assertEquals("48hour&multi2", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
    } catch (Error e) {
      
    	logger.error("trigger创建后table中category显示错误");
     Assert.fail("trigger创建后table中category显示错误");
    }
    try {
      AssertJUnit.assertEquals("MessageTriggerPMI", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText());
    } catch (Error e) {
      
    	logger.error("trigger创建后table中title显示错误");
     Assert.fail("trigger创建后table中title显示错误");
    }
    try {
        AssertJUnit.assertEquals("selenium48hour", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText());
      } catch (Error e) {
        
      	logger.error("trigger创建后table中segmentation显示错误");
        Assert.fail("trigger创建后table中segmentation显示错误");
      }
    try {
      AssertJUnit.assertEquals("Sent Message", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[5]")).getText());
    } catch (Error e) {
      
    	logger.error("trigger创建后table中action type显示错误");
      Assert.fail("trigger创建后table中action type显示错误");
    }
    try {
        AssertJUnit.assertEquals("Post,Messages,Image", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[6]")).getText());
      } catch (Error e) {
        
    	  logger.error("trigger创建后table中触发操作显示错误");
        Assert.fail("trigger创建后table中触发操作显示错误");
      }
    try {
        AssertJUnit.assertEquals("reply'post多图文“测试” selenium", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[7]")).getText());
      } catch (Error e) {
       
    	  logger.error("trigger创建后table中第一个触发操作显示错误");
        Assert.fail("trigger创建后table中第一个触发操作显示错误");
      }
    /*try {
        AssertJUnit.assertEquals("0 day + 0 hour + 0 minute + 1 second", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[8]")).getText());
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
