package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class PostItemsLimit extends TestBase{
	private static Logger logger = Logger.getLogger(PostItemsLimit.class);
	@Test
	public void postItemsVrify() throws Exception {
	//进入create页面
	//oldEditorPage();
		navigation("Manage Posts");
		 
		 driver.findElement(By.id("create-more-posts")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).click();
		 driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).sendKeys("new editor单图文'selenium");
		 driver.findElement(By.linkText("Submit")).click();
   
    //校验post items最多8个
	for(int i=0;i<7;i++)
    driver.findElement(By.xpath(".//*[@id='J_sortable']/section")).click();
    try {
      AssertJUnit.assertEquals("You can join, at most, only eight graphic news!", driver.findElement(By.cssSelector("div.toast-message")).getText());
    } catch (Error e) {
      
    	logger.error("多图文最多8个item校验失败");
      Assert.fail("多图文最多8个item校验失败");
    }
    Thread.sleep(3000);
    
    //删除多余的不要的item
    JavascriptExecutor js = (JavascriptExecutor) driver;
    //js.executeScript("jQuery('div.appmsg-mark').show();");
    js.executeScript("jQuery('.postOperation').css({'opacity':'1','-webkit-transform':'translate3d(0,2px,0)'})");
    
	//Thread.sleep(4000);
    Actions action = new Actions(driver);
    
    for(int j=6;j>0;j--){
    	String a =String.valueOf(j);
    	WebElement delete2 = driver.findElement(By.xpath(".//*[@id='postSetting']/section["+ a +"]/nav/*[name()='svg'][3]"));
		//WebElement delete =driver.findElement(By.xpath("//div[@id='J_sortable']/div["+ a +"]/div[2]/div/a[2]"));
    	//action.moveToElement(driver.findElement(By.xpath("//div[@id='J_sortable']/div["+ a +"]"))).click().perform();
    	//delete.click();
    	action.click(delete2).perform();
    	Thread.sleep(2000);
    	driver.findElement(By.linkText("Delete")).click();
    	Thread.sleep(2000);
    }
    
    WebElement delete = driver.findElement(By.xpath("//div[@id='postSetting']/div[2]/nav/*[name()='svg'][3]"));
	 
	action.click(delete).perform();
	Thread.sleep(2000);
	driver.findElement(By.linkText("Delete")).click();
	Thread.sleep(2000);
	

		boolean t = isElementPresent(By.xpath("//div[@id='postSetting']/div[2]/nav/*[name()='svg'][3]"));
		if(t){
			logger.error("item没有删除完毕！");
		}
		 
		
		
	
		
	
    /*//校验第一个item不能删
    WebElement delete=driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[2]"));
    action.moveToElement(driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]"))).perform();
    delete.click();
    Thread.sleep(1000);
    try {
        AssertJUnit.assertEquals("Unable to delete, multiple graphic need at least 1 message", driver.findElement(By.cssSelector("div.toast-message")).getText());
      } catch (Error e) {
        
    	  logger.error("多图文第一个item不能删校验失败");
        Assert.fail("多图文第一个item不能删校验失败");
      }*/
  }
}