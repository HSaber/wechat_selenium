package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class AnyFollowTrigger extends TestBase{
	private static Logger logger = Logger.getLogger(AnyFollowTrigger.class);
	//@Test
	@Test(groups="anyFollowTrigger",dependsOnGroups="segmentationTag")
	  public void anyFollowTrigger() throws Exception {
	 
		 navigation("Triggers");
	    
	    
	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/a[2]/button")).click();
	    
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("Any follow in condition");
	    driver.findElement(By.id("Triggers_title")).clear();
	    driver.findElement(By.id("Triggers_title")).sendKeys("selenium any follow");
	    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
	    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Follow");
	    driver.findElement(By.id("any_follow")).click();
	    
	    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("测试组tag");
	    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Message");
	    driver.findElement(By.id("action_message")).clear();
	    driver.findElement(By.id("action_message")).sendKeys("这条消息又any follow触发2s后收到，下面同时会收到selenium单图文post，过5s会收到大树图片\n{{nickname}}\n<a href=\"https://www.baidu.com\">百度链接</a>");
	    driver.findElement(By.id("delay_radio_1")).click();
	    new Select(driver.findElement(By.id("delay_schedule_seconds"))).selectByVisibleText("2 Seconds");
	    
	    driver.findElement(By.id("add_action")).click();
	    new Select(driver.findElement(By.name("Triggers[extra_action][2]"))).selectByVisibleText("Send a Post");
	    new Select(driver.findElement(By.xpath("(//select[@id='action_post'])[2]"))).selectByVisibleText("single'post单图文“测试” selenium");
	    
	    driver.findElement(By.id("add_action")).click();
	    new Select(driver.findElement(By.name("Triggers[extra_action][3]"))).selectByVisibleText("Send a Image");
	    
	    driver.findElement(By.xpath("(//input[@name='files[]'])[3]")).clear();
	    driver.findElement(By.xpath("(//input[@name='files[]'])[3]")).sendKeys(file+"/com/material/15.jpg");
	    Thread.sleep(3000);
	    AssertJUnit.assertEquals("Unsupported file size!", closeAlertAndGetItsText());
	    driver.findElement(By.xpath("(//input[@name='files[]'])[3]")).clear();
	    driver.findElement(By.xpath("(//input[@name='files[]'])[3]")).sendKeys(file+"/com/material/12.jpg");
	   // Thread.sleep(4000);
	    String src = null;
		src= driver.findElement(By.xpath("//div[@id='actions']/div[3]/div[1]/div[6]/div/img")).getAttribute("src");
		System.out.println("src的值是："+ src);
		while(src==null||src.equals("")){
			 src= driver.findElement(By.xpath("//div[@id='actions']/div[3]/div[1]/div[6]/div/img")).getAttribute("src");
			 System.out.println("image未上传完成,继续等待中！");
		}
	    new Select(driver.findElement(By.xpath("(//select[@id='delay_schedule_seconds'])[3]"))).selectByVisibleText("5 Seconds");
	    
	    String time=currentTime();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		 WebElement target = driver.findElement(By.id("tags"));
		 js.executeScript("arguments[0].scrollIntoView();", target);
	    driver.findElement(By.id("tags")).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[12]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("any follow"+ time+Keys.ENTER);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category.equals("Any follow in condition")){
	    	System.out.println("第一行就是Any follow in condition，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("Any follow in condition");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		   
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category.equals("Any follow in condition")&&b)
		    		break;
		    }
	    }
	    
	    try {
	      AssertJUnit.assertEquals("Any follow in condition", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	    	logger.error("Any follow in condition创建失败！！category名称错误，请检查！！");
	    	Assert.fail("Any follow in condition创建失败！！category名称错误，请检查！！");
	      
	    }
	    try {
	      AssertJUnit.assertEquals("selenium any follow", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[3]")).getText());
	    } catch (Error e) {
	    	logger.error("Any follow in condition创建失败！！title名称错误，请检查！！");
	    	Assert.fail("Any follow in condition创建失败！！title名称错误，请检查！！");
	    }
	    try {
		      AssertJUnit.assertEquals("测试组tag", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[4]")).getText());
		    } catch (Error e) {
		    	logger.error("Any follow in condition创建失败！！trigger segmentation错误，请检查！！");
		    	Assert.fail("Any follow in condition创建失败！！trigger segmentation错误，请检查！！");
		    }
	    try {
	      AssertJUnit.assertEquals("Follow", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[5]")).getText());
	    } catch (Error e) {
	    	logger.error("Any follow in condition创建失败！！trigger 类型名称错误，请检查！！");
	    	Assert.fail("Any follow in condition创建失败！！trigger 类型名称错误，请检查！！");
	    }
	    try {
	      AssertJUnit.assertEquals("Messages,Post,Image", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[6]")).getText());
	    } catch (Error e) {
	    	logger.error("Any follow in condition创建失败！！触发内容 名称错误，请检查！！");
	    	Assert.fail("Any follow in condition创建失败！！触发内容 名称错误，请检查！！");
	    }
	    try {
	        AssertJUnit.assertEquals("这条消息又any follow触发2s后收到，下面同时会收到selenium单图文post，过5s会收到大树图片 {{nickname}} 百度链接", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[7]")).getText());	   
	    	
	    }catch (Error e) {
	    	logger.error("Any follow in condition创建失败！！text没显示，请检查！！");
	    	Assert.fail("Any follow in condition创建失败！！text没显示，请检查！！");
	    }
	   /* try {
	      AssertJUnit.assertEquals("0 day + 0 hour + 0 minute + 2 seconds", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[8]")).getText());
	    } catch (Error e) {
	    	logger.error("Any follow in condition创建失败！！延时没显示，请检查！！");
	    	Assert.fail("Any follow in condition创建失败！！延时没显示，请检查！！");
	    }*/
	    try {
	      AssertJUnit.assertEquals("0", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[8]/a")).getText());
	    } catch (Error e) {
	    	logger.error("Any follow in condition创建失败！！触发次数不为0，请检查！！");
	    	Assert.fail("Any follow in condition创建失败！！触发次数不为0，请检查！！");
	    }
	    try {
	      AssertJUnit.assertEquals("No", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[9]")).getText());
	    } catch (Error e) {
	    	logger.error("Any follow in condition创建失败！！是否触发一次应该显示no，请检查！！");
	    	Assert.fail("Any follow in condition创建失败！！是否触发一次应该显示no，请检查！！");
	    }
	    try {
	        AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[10]/img")).getAttribute("src"));
	      } catch (Error e) {
	    	  logger.error("Any follow in condition创建失败！！应该是active状态，请检查！！");
	    	  Assert.fail("Any follow in condition创建失败！！应该是active状态，请检查！！");
	      }
	    
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    try {
	      AssertJUnit.assertEquals("any follow"+time, driver.findElement(By.cssSelector("li.tagItem")).getText());
	    } catch (Error e) {
	    	logger.error("Any follow in condition创建tag失败，请检查！！！");
	      Assert.fail("Any follow in condition创建tag失败，请检查！！！");
	    }
	  }
}
