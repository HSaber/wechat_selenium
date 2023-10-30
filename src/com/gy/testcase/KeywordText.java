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
  
public class KeywordText extends TestBase{
	private static Logger logger = Logger.getLogger(KeywordText.class);
   @Test(groups="keywordText")
   public void keywordText() throws Exception {
	//System.out.println("keywordtext"+ getDriver());  
	//login();
	   navigation("Keyword Auto-Reply");
    //System.out.println(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[6]")).getText());
    //======================创建keyword======================
    driver.findElement(By.cssSelector("a.btn > button")).click();
    driver.findElement(By.id("KeyWord_category")).clear();
    driver.findElement(By.id("KeyWord_category")).sendKeys("seleniumcategory");
    driver.findElement(By.id("KeyWord_name")).click();
    driver.findElement(By.id("KeyWord_name")).clear();
    driver.findElement(By.id("KeyWord_name")).sendKeys("seleniumtest1");
    driver.findElement(By.id("KeyWord_internal_name")).clear();
    driver.findElement(By.id("KeyWord_internal_name")).sendKeys("selenium1");
    
    driver.findElement(By.xpath("//form[@id='key-word-form']/div[5]/div/input")).clear();
    driver.findElement(By.xpath("//form[@id='key-word-form']/div[5]/div/input")).sendKeys("seleniumtext");
    //new Select(driver.findElement(By.xpath("//form[@id='key-word-form']/div[5]/select"))).selectByVisibleText("Full Matching");
    new Select(driver.findElement(By.xpath("//form[@id='key-word-form']/div[5]/div[2]/select"))).selectByVisibleText("Full Matching");
    
    driver.findElement(By.cssSelector("button.add_new_rule")).click();
    driver.findElement(By.xpath("//form[@id='key-word-form']/div[6]/div[1]/input")).clear();
    driver.findElement(By.xpath("//form[@id='key-word-form']/div[6]/div[1]/input")).sendKeys("guoyan");
    
    driver.findElement(By.cssSelector("button.add_new_rule")).click();
    driver.findElement(By.xpath("//form[@id='key-word-form']/div[7]/div[2]/button")).click();
    
    driver.findElement(By.xpath("(//input[@id='KeyWord_replyType'])[2]")).click();
    
   
	String time=TestBase.currentTime();
	
    WebElement editor = driver.findElement(By.xpath("//div[@id='text']/div"));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("$('div.js_editorArea').text('由Keyword触发：{{nickname}}，name:{{name}}{{time}}好！你住在{{country}}{{province}}{{city}}，你是{{gender}}生，你是{{role}}，你说{{language}}，<a href=\"https://www.baidu.com\">百度链接</a>"+ time +"');", editor);
    editor.sendKeys(Keys.ENTER);
    
    driver.findElement(By.linkText("Facial Expression")).click();
    driver.findElement(By.cssSelector("i.js_emotion_i")).click();
    driver.findElement(By.name("yt0")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("KeyWord[keyWord]")).clear();
    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys("seleniumtext");
    new Select(driver.findElement(By.name("KeyWord[status]"))).selectByVisibleText("Active");
    String search = "";
    boolean b = true;
    /*while(!search.equals("seleniumtext")){
    	search = driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[4]")).getText();
    	System.out.println("请等待，未搜索完成！！");
    }*/
    while(true){
    	b = true;
    	try{
    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[4]")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    	
    	if(search.equals("seleniumtext,guoyan")&&b)
    			break;
    }
    
    try {
        AssertJUnit.assertEquals("seleniumtext,guoyan", search);
      } catch (Error e) {
        
    	  logger.error("keywordText创建后table中keyword显示错误");
        Assert.fail("keywordText创建后table中keyword显示错误");
      }
    
    while(true){
    	b = true;
    	try{
    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    	
    	if(b)
    			break;
    }
    try {
      AssertJUnit.assertEquals("seleniumcategory", search);
    } catch (Error e) {
     
    	logger.error("keywordText创建后table中category显示错误");
     Assert.fail("keywordText创建后table中category显示错误");
    }
    
    while(true){
    	b = true;
    	try{
    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[5]")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    	
    	if(b)
    			break;
    }
    
    try {
    	
    	AssertJUnit.assertEquals("Text", search);
    } catch (Error e) {
      
    	logger.error("keywordText创建后table中type显示错误");
      Assert.fail("keywordText创建后table中type显示错误");
    }
    
    while(true){
    	b = true;
    	try{
    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[6]")).getText();
    	}catch(org.openqa.selenium.StaleElementReferenceException e){
   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
   		 	b=false;
    	}
    	
    	if(b)
    			break;
    }
    try {
    	System.out.println(search);
    	AssertJUnit.assertEquals("/微笑由Keyword触发：{{nickname}}，name:{{name}}{{time}}好！你住在{{country}}{{province}}{{city}}，你是{{gender}}生，你是{{role}}，你说{{language}}，百度链接"+ time +"", search);
      } catch (Error e) {
      
    	  logger.error("keywordText创建后table中触发的text内容显示错误");
        Assert.fail("keywordText创建后table中触发的text内容显示错误");
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
     
    	logger.error("keywordText创建后table中status显示错误");
      Assert.fail("keywordText创建后table中status显示错误");
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
   
   // driver.findElement(By.cssSelector("img[alt=\"Update\"]")).click();
    driver.findElement(By.name("yt1")).click();
    try {
      AssertJUnit.assertEquals("Keyword Auto-Responders", driver.findElement(By.cssSelector("h1.page_title")).getText());
    } catch (Error e) {
     
    	logger.error("keyword不能更新保存");
      Assert.fail("keyword不能更新保存");
    }
    //System.out.println("keywordtext结束"+ getDriver());
  }
}

