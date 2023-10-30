package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class LBSTriggerOnce extends TestBase{ 
	private static Logger logger = Logger.getLogger(LBSTriggerOnce.class);
	//@Test
	@Test(groups="lbsTriggerOnce",dependsOnGroups="directFollowNotInCondtionInteractive",alwaysRun=true)
  public void lbsTriggerOnce() throws Exception {
	
	  navigation("Triggers");
    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div[1]/a[1]/button")).click();
    //校验必填项
    driver.findElement(By.name("yt0")).click();
    try {
      AssertJUnit.assertEquals("Send Content can't be blank.", driver.findElement(By.cssSelector("div.error-list2.errorMessage2 > ul > li")).getText());
    } catch (Error e) {
     
    	logger.error("LBS trigger必填项校验失败");
      Assert.fail("LBS trigger必填项校验失败");
    }
    driver.findElement(By.id("Triggers_category")).clear();
    driver.findElement(By.id("Triggers_category")).sendKeys("lbstriggerOnce");
    driver.findElement(By.id("Triggers_title")).clear();
    driver.findElement(By.id("Triggers_title")).sendKeys("selbstrigger");
    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
   
    driver.findElement(By.id("action_post")).click();
    new Select(driver.findElement(By.id("action_post"))).selectByVisibleText("single'post单图文“测试” selenium");
    
   
   	String time=currentTime();
   	
    driver.findElement(By.id("tags")).click();
    //driver.findElement(By.xpath(".//*[@id='triggers-form']/div[18]/div[1]/label")).click();
    driver.findElement(By.xpath("//ul[@id='tag_handler']/li/input")).click();
    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("selbstrigger"+time+Keys.ENTER); 
    Thread.sleep(1000);
    driver.findElement(By.name("yt0")).click();
    Thread.sleep(2000);
    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
    if(category.equals("lbstriggerOnce")){
    	System.out.println("第一行就是lbstriggerOnce，不需要搜素查询");
    }else{
    	driver.findElement(By.name("Triggers[category]")).clear();
	    driver.findElement(By.name("Triggers[category]")).sendKeys("lbstriggerOnce");
	    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
	   
	    while(true){
	    	boolean b = true;
	    	try{
	    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    	}catch(org.openqa.selenium.StaleElementReferenceException e){
	    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
	    		 b=false;
	    	}
	    	if(category.equals("lbstriggerOnce")&&b)
	    		break;
	    }
	   
  }
    try {
        AssertJUnit.assertEquals("lbstriggerOnce", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
      } catch (Error e) {
        
    	  logger.error("LBS trigger创建后table中category显示错误");
        Assert.fail("LBS trigger创建后table中category显示错误");
      }
    try {
      AssertJUnit.assertEquals("selbstrigger", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText());
    } catch (Error e) {
     
    	logger.error("LBS trigger创建后table中title显示错误");
     Assert.fail("LBS trigger创建后table中title显示错误");
    }
    try {
      AssertJUnit.assertEquals("LBS", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[5]")).getText());
    } catch (Error e) {
    
    	logger.error("LBS trigger创建后table中type显示错误");
      Assert.fail("LBS trigger创建后table中type显示错误");
    }
    try {
        AssertJUnit.assertEquals("Post", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[6]")).getText());
      } catch (Error e) {
    
    	  logger.error("LBS trigger创建后table中触发内容type显示错误");
       Assert.fail("LBS trigger创建后table中触发内容type显示错误");
      }
    try {
        AssertJUnit.assertEquals("single'post单图文“测试” selenium", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[7]")).getText());
      } catch (Error e) {
        
    	  logger.error("LBS trigger创建后table中触发post名称显示错误");
        Assert.fail("LBS trigger创建后table中触发post名称显示错误");
      }
    try {
        AssertJUnit.assertEquals("0", driver.findElement(By.linkText("0")).getText());
      } catch (Error e) {
        
    	  logger.error("LBS trigger创建后table中触发人数显示错误");
        Assert.fail("LBS trigger创建后table中触发人数显示错误");
      }
    try {
      AssertJUnit.assertEquals("Yes", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[9]")).getText());
    } catch (Error e) {
      
    	logger.error("LBS trigger创建后table中是否触发一次显示错误");
      Assert.fail("LBS trigger创建后table中是否触发一次显示错误");
    }
    try {
      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[10]/img")).getAttribute("src"));
    } catch (Error e) {
      
    	logger.error("LBS trigger创建后table中status显示错误");
      Assert.fail("LBS trigger创建后table中status显示错误");
    }
    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
    Thread.sleep(1000);
    try {
      AssertJUnit.assertEquals("selbstrigger"+time+"", driver.findElement(By.cssSelector("li.tagItem")).getText());
    } catch (Error e) {
     
    	logger.error("LBS trigger创建的tag没有成功保存");
      Assert.fail("LBS trigger创建的tag没有成功保存");
    }
    driver.findElement(By.name("yt1")).click();
    try {
      AssertJUnit.assertEquals("Manage Triggers", driver.findElement(By.cssSelector("h1.page_title")).getText());
    } catch (Error e) {
      
    	logger.error("LBS trigger不能update");
      Assert.fail("LBS trigger不能update");
    }
  }
}

