package com.gy.testcase;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class KeywordPost extends TestBase{
	private static Logger logger = Logger.getLogger(KeywordPost.class);
   @Test(groups="keywordPost")
   public void keywordPost() throws Exception {
	 //  System.out.println("keywordpost"+ getDriver());
	//login();
	   navigation("Keyword Auto-Reply");
    //======================创建keyword======================
    //验证必填项（post必须选）
    driver.findElement(By.cssSelector("a.btn > button")).click();
    driver.findElement(By.name("yt0")).click();
   try{
	   AssertJUnit.assertEquals("Material was not selected!",closeAlertAndGetItsText());
    }catch(Error e){
    	 
    	logger.error("keyword中post必选校验失败");
         Assert.fail("keyword中post必选校验失败");
    }
   //name必须填
    new Select(driver.findElement(By.id("KeyWord_resId"))).selectByVisibleText("multi'post多图文“测试” selenium");
    Thread.sleep(1000);
    //验证post preview
    try {
        AssertJUnit.assertEquals("标题title'1“测试” selenium", driver.findElement(By.linkText("标题title'1“测试” selenium")).getText());
      } catch (Error e) {
        
    	  logger.error("keyword选择post以后，post preview没显示");
        Assert.fail("keyword选择post以后，post preview没显示");
      }
      try {
        AssertJUnit.assertEquals("标题title'4“测试”", driver.findElement(By.linkText("标题title'4“测试”")).getText());
      } catch (Error e) {
        
    	  logger.error("keyword选择post以后，post preview显示不正常");
        Assert.fail("keyword选择post以后，post preview显示不正常");
      }
    driver.findElement(By.name("yt0")).click();
    Thread.sleep(2000);
    
    //验证其他文本框必填项
    try {
      AssertJUnit.assertEquals("Name cannot be blank.", driver.findElement(By.xpath("//form[@id='key-word-form']/div/ul/li")).getText());
    } catch (Error e) {
      
    	logger.error("keyword name必填项校验失败");
      Assert.fail("keyword name必填项校验失败");
    }
    try {
        AssertJUnit.assertEquals("Key Word cannot be blank.", driver.findElement(By.xpath("//form[@id='key-word-form']/div/ul/li[2]")).getText());
      } catch (Error e) {
        
    	  logger.error("keyword中 keyword必填项校验失败"); 
        Assert.fail("keyword中 keyword必填项校验失败");
      }
     
    //输入keyword
    driver.findElement(By.cssSelector("input.keyword")).click();
    driver.findElement(By.cssSelector("input.keyword")).clear();
    driver.findElement(By.cssSelector("input.keyword")).sendKeys("seleniumpost");
    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
    driver.findElement(By.cssSelector("button.add_new_rule")).click();
    //driver.findElement(By.cssSelector("div.span-6 > button.delete_rule")).click();
    driver.findElement(By.xpath(".//*[@id='key-word-form']/div[7]/div[2]/button")).click();
   //输入name 
    driver.findElement(By.id("KeyWord_name")).clear();
    driver.findElement(By.id("KeyWord_name")).sendKeys("seleniumtest");
    driver.findElement(By.id("KeyWord_internal_name")).clear();
    driver.findElement(By.id("KeyWord_internal_name")).sendKeys("selenium");
   //添加tag
    driver.findElement(By.id("tags")).click();
    driver.findElement(By.xpath("//ul[@id='tag_handler']/li/input")).click();
    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("selenium");
    driver.findElement(By.id("tags")).click();
    driver.findElement(By.id("tags")).click();
    Thread.sleep(1000);
    new Select(driver.findElement(By.id("KeyWord_resId"))).selectByVisibleText("multi'post多图文“测试” selenium");
    Thread.sleep(2000);
    driver.findElement(By.name("yt0")).click();
    driver.findElement(By.name("KeyWord[keyWord]")).clear();
    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys("seleniumpost");
    new Select(driver.findElement(By.name("KeyWord[status]"))).selectByVisibleText("Active");
    String search = "";
    boolean b = true;
    /*while(!search.equals("seleniumpost")){
    	search = driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[4]")).getText();
    	System.out.println("请等待，未搜索完成！！");
    }*/
    while(true){
    	b=true;
    	try{
    		search = driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[4]")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    		
    		if(search.equals("seleniumpost")&&b)
    			break;
    }
    //验证是否创建成功 
    
    try {
        AssertJUnit.assertEquals("seleniumpost",search);
      } catch (Error e) {
        
      	logger.error("keyword internal name table中显示不正确");
        Assert.fail("keyword internal name table中显示不正确");
      }
    
    while(true){
    	b=true;
    	try{
    		search = driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[3]")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    		
    		if(b)
    			break;
    }
    try {
      AssertJUnit.assertEquals("seleniumtest", search);
    } catch (Error e) {
   
    	logger.error("keyword name table中显示不正确");
      Assert.fail("keyword name table中显示不正确");
    }
   
    
    while(true){
    	b=true;
    	try{
    		search = driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[5]")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    		
    		if(b)
    			break;
    }
    
    try {
      AssertJUnit.assertEquals("Post", search);
    } catch (Error e) {
     
    	logger.error("keyword type table中显示不正确");
      Assert.fail("keyword type table中显示不正确");
    }
    
    while(true){
    	b=true;
    	try{
    		search = driver.findElement(By.linkText("multi'post多图文“测试” selenium")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    		
    		if(b)
    			break;
    }
    try {
      AssertJUnit.assertEquals("multi'post多图文“测试” selenium", search);
    } catch (Error e) {
     
    	logger.error("keyword table中显示post名称错误");
      Assert.fail("keyword table中显示post名称错误");
    }
    String category = null;
    while(true){
    	 b = true;
    	try{
    		 category = driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[8]/img")).getAttribute("src");
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
    		 b=false;
    	}
    	if(category.equals(baseUrl+"images/switch-button_on.png")&&b)
    		break;
    }
    try {
      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", category);
    } catch (Error e) {
      
    	logger.error("keyword table中status显示不正确");
      Assert.fail("keyword table中status显示不正确");
    }
    
    while(true){
    	b=true;
    	try{
    		search = driver.findElement(By.linkText("0")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    		
    		if(b)
    			break;
    }
    try {
      AssertJUnit.assertEquals("0", search);
    } catch (Error e) {
      
    	logger.error("keyword table中触发次数显示不正确");
      Assert.fail("keyword table中触发次数显示不正确");
    }
    
   
    while(true){
    	 b = true;
    	try{
    		driver.findElement(By.xpath(".//*[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[9]/a")).click();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
    		 b=false;
    	}
    	if(b)
    		break;
    }
    
    //driver.findElement(By.xpath(".//*[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[9]/a")).click();
    Thread.sleep(2000);
    try {
      AssertJUnit.assertEquals("selenium", driver.findElement(By.cssSelector("li.tagItem")).getText());
    } catch (Error e) {
    	logger.error("keyword tag没加上，请检查！！");
    	Assert.fail("keyword tag没加上，请检查！！");
    }
   // System.out.println("keywordpost结束"+ getDriver());
   }
}
