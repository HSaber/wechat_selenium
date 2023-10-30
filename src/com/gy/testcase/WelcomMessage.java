package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;


public class WelcomMessage extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomMessage.class);
  @Test(groups="welcomeMessage")
  public void welcomeMessage() throws Exception {
    
	  navigation("Welcome Message");
    //创建welcom message
    driver.findElement(By.cssSelector("a.btn > button")).click();
    
    driver.findElement(By.xpath("(//input[@id='ytFirstAttention_typ'])[2]")).click();
    driver.findElement(By.name("yt0")).click();
    Thread.sleep(2000);
    try{
    	AssertJUnit.assertEquals("Text can't be empty!", closeAlertAndGetItsText());
    
    }catch(Error e){
    	
    	logger.error("welcome message text类型必填项校验失败");
    	Assert.fail("welcome message text类型必填项校验失败");
    }
    Thread.sleep(2000);
    try {
        AssertJUnit.assertEquals("You can also input600words", driver.findElement(By.cssSelector("p.editor_tip.js_editorTip")).getText());
      } catch (Error e) {
       
    	  logger.error("welcome message text类型的editor框里没有可输入600字的提示");
      Assert.fail("welcome message text类型的editor框里没有可输入600字的提示");
      }
    String createtime = currentTime();
    driver.findElement(By.id("FirstAttention_content")).clear();
    driver.findElement(By.id("FirstAttention_content")).sendKeys("Welcome message:{{nickname}}，name:{{name}}{{time}}好！\n 你住在{{country}}{{province}}{{city}}，你是{{gender}}生，\n 你是{{role}}，你说{{language}}"+ createtime);
    Thread.sleep(2000);
    try {
        AssertJUnit.assertEquals("You can also input454words", driver.findElement(By.cssSelector("p.editor_tip.js_editorTip")).getText());
      } catch (Error e) {
      
    	  logger.error("welcome message text类型的editor框里实时显示还可以输入多少字不正确");
       Assert.fail("welcome message text类型的editor框里实时显示还可以输入多少字不正确");
      }
    new Select(driver.findElement(By.id("FirstAttention_status"))).selectByVisibleText("Active");
    driver.findElement(By.name("yt0")).click();
    //验证更新是否成功
    try {
        AssertJUnit.assertEquals("Text", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
      } catch (Error e) {
        
    	  logger.error("新创建的welcome message创建失败或者table中type显示错误");
        Assert.fail("新创建的welcome message创建失败或者table中type显示错误");
      }
    try {
      AssertJUnit.assertEquals("Welcome message:{{nickname}}，name:{{name}}{{time}}好！ 你住在{{country}}{{province}}{{city}}，你是{{gender}}生， 你是{{role}}，你说{{language}}"+ createtime, driver.findElement(By.xpath("//div[@id='first-attention-grid']/div/table/tbody/tr/td[4]")).getText());
    } catch (Error e) {
      
    	logger.error("新创建的welcome message创建失败或者table中text content显示错误");
      Assert.fail("新创建的welcome message创建失败或者table中text content显示错误");
    }
    try {
    	String a = driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src");
    	System.out.println(a);
        AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", a);
      } catch (Error e) {
      
    	  logger.error("新创建的welcome message状态未开启，应该是active状态");
       Assert.fail("新创建的welcome message状态未开启，应该是active状态");
      }
    
    try {
    	String b = driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[2]/td[8]/img")).getAttribute("src");
    	System.out.println(b);
        AssertJUnit.assertEquals(baseUrl+"images/switch-button_off.png", b);
      } catch (Error e) {
      
    	  logger.error("状态错误，创建新的active的welcome message后，旧的message应该自动变为inactive");
       Assert.fail("状态错误，创建新的active的welcome message后，旧的message应该自动变为inactive");
      }
  }
}
  
