package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class DirectFollowTriggerIMP extends TestBase{
	private static Logger logger = Logger.getLogger(DirectFollowTriggerIMP.class);
	//@Test(groups="directFollowTriggerIMP")
	 @Test(groups="directFollowTriggerIMP",dependsOnGroups="anyFollowTriggerQRcodeFollow",alwaysRun=true)
	  public void directFollowTriggerIMP() throws Exception {
	    
		 navigation("Triggers");
	    
	    //创建direct follow no condition触发multi trigger(image/message/post)
	   driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/a[2]/button")).click();
	    
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("direct follow no condition");
	    driver.findElement(By.id("Triggers_title")).clear();
	    driver.findElement(By.id("Triggers_title")).sendKeys("sele direct follow");
	    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
	    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Follow");
	    driver.findElement(By.id("direct_follow")).click();
	    
	    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Image");
	    driver.findElement(By.name("files[]")).clear();
	    driver.findElement(By.name("files[]")).sendKeys(file+"/com/material/16.jpg");
	    //Thread.sleep(4000);
	    String src = null;
		src= driver.findElement(By.xpath("//div[@id='actions']/div[1]/div[1]/div[6]/div/img")).getAttribute("src");
		System.out.println("src的值是："+ src);
		while(src==null||src.equals("")){
			 src= driver.findElement(By.xpath("//div[@id='actions']/div[1]/div[1]/div[6]/div/img")).getAttribute("src");
			 System.out.println("image未上传完成,继续等待中！");
		}
		
	    driver.findElement(By.id("add_action")).click();
	    new Select(driver.findElement(By.name("Triggers[extra_action][2]"))).selectByVisibleText("Send a Message");
	    driver.findElement(By.xpath("(//textarea[@id='action_message'])[2]")).clear();
	    driver.findElement(By.xpath("(//textarea[@id='action_message'])[2]")).sendKeys("{{nickname}},你是direct follow,将会立即触发一张\"李易峰靠墙\"的图片，\n这条信息距离图片\n发送 5s..............................\n<a href=\"http://www.baidu.com\">trigger 链接检查</a>\n再过3s，你会收到一篇seleniumnormal 多图文，注意检查！！！");
	    new Select(driver.findElement(By.cssSelector("span.delay_schedule > #delay_schedule_seconds"))).selectByVisibleText("5 Seconds");
	    
	    driver.findElement(By.id("add_action")).click();
	    new Select(driver.findElement(By.name("Triggers[extra_action][3]"))).selectByVisibleText("Send a Post");
	    new Select(driver.findElement(By.xpath("(//select[@id='action_post'])[3]"))).selectByVisibleText("multi'post多图文“测试” selenium");
	    new Select(driver.findElement(By.xpath("(//select[@id='delay_schedule_seconds'])[3]"))).selectByVisibleText("3 Seconds");
	    
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    //driver.findElement(By.xpath(".//*[@id='triggers-form']/div[18]/div[1]/label")).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[12]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("direct_follow_trigger"+ time+Keys.ENTER);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category.equals("direct follow no condition")){
	    	System.out.println("第一行就是direct follow no condition，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("direct follow no condition");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		   
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category.equals("direct follow no condition")&&b)
		    		break;
		    }
	    }
	    try {
	      AssertJUnit.assertEquals("direct follow no condition", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	    	logger.error("sele direct follow创建失败！！category名称错误，请检查！！");
	    	Assert.fail("sele direct follow创建失败！！category名称错误，请检查！！");
	      
	    }
	    try {
	      AssertJUnit.assertEquals("sele direct follow", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[3]")).getText());
	    } catch (Error e) {
	    	logger.error("sele direct follow创建失败！！title名称错误，请检查！！");
	    	Assert.fail("sele direct follow创建失败！！title名称错误，请检查！！");
	    }
	    try {
		      AssertJUnit.assertEquals("", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[4]")).getText());
		    } catch (Error e) {
		    	logger.error("sele direct follow创建失败！！trigger segmentation错误，请检查！！");
		    	Assert.fail("sele direct follow创建失败！！trigger segmentation错误，请检查！！");
		    }
	    try {
	      AssertJUnit.assertEquals("Follow", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[5]")).getText());
	    } catch (Error e) {
	    	logger.error("sele direct follow创建失败！！trigger 类型名称错误，请检查！！");
	    	Assert.fail("sele direct follow创建失败！！trigger 类型名称错误，请检查！！");
	    }
	    try {
	      AssertJUnit.assertEquals("Image,Messages,Post", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[6]")).getText());
	    } catch (Error e) {
	    	logger.error("sele direct follow创建失败！！触发内容 名称错误，请检查！！");
	    	Assert.fail("sele direct follow创建失败！！触发内容 名称错误，请检查！！");
	    }
	    try {
	      assertNotEquals(null, driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[7]/img")).getAttribute("src"));
	    } catch (Error e) {
	    	logger.error("sele direct follow创建失败！！图片没显示，请检查！！");
	    	Assert.fail("sele direct follow创建失败！！图片没显示，请检查！！");
	    }
	   /* try {
	      AssertJUnit.assertEquals("", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[8]")).getText());
	    } catch (Error e) {
	    	logger.error("sele direct follow创建失败！！延时没显示，请检查！！");
	    	Assert.fail("sele direct follow创建失败！！延时没显示，请检查！！");
	    }*/
	    try {
	      AssertJUnit.assertEquals("0", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[8]/a")).getText());
	    } catch (Error e) {
	    	logger.error("sele direct follow创建失败！！触发次数不为0，请检查！！");
	    	Assert.fail("sele direct follow创建失败！！触发次数不为0，请检查！！");
	    }
	    try {
	      AssertJUnit.assertEquals("No", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[9]")).getText());
	    } catch (Error e) {
	    	logger.error("sele direct follow创建失败！！是否触发一次应该显示no，请检查！！");
	    	Assert.fail("sele direct follow创建失败！！是否触发一次应该显示no，请检查！！");
	    }
	    try {
	        AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[10]/img")).getAttribute("src"));
	      } catch (Error e) {
	    	  logger.error("sele direct follow创建失败！！应该是active状态，请检查！！");
	    	  Assert.fail("sele direct follow创建失败！！应该是active状态，请检查！！");
	      }
	    
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    try {
	      AssertJUnit.assertEquals("direct_follow_trigger"+time, driver.findElement(By.cssSelector("li.tagItem")).getText());
	    } catch (Error e) {
	    	logger.error("direct follow trigger创建tag失败，请检查！！！");
	      Assert.fail("direct follow trigger创建tag失败，请检查！！！");
	    }
	  }
}
