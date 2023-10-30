package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;//下拉框方法调用


public class Send48Post extends TestBase{
	private static Logger logger = Logger.getLogger(Send48Post.class);
	//@Test
	@Test(groups="send48Post",dependsOnGroups="newEditorSinglePost")
	public void send48Post() throws Exception {
	
	
		navigation("Schedule a Post");
    //直接跳到schedule post页面选择下拉框中固定post以48小时立即发送
    
    new Select(driver.findElement(By.name("sendType"))).selectByVisibleText("48 Hour List");
    Thread.sleep(2000);
    //new Select(driver.findElement(By.id("res_id"))).selectByVisibleText("single'post单图文“测试” selenium");
    new Select(driver.findElement(By.id("res_id"))).selectByVisibleText("new editor单图文'selenium");
    Thread.sleep(2000);
	try {
      //AssertJUnit.assertEquals("单图文'title“测试” selenium", driver.findElement(By.linkText("单图文'title“测试” selenium")).getText());
      AssertJUnit.assertEquals("new'editor selenium单图文", driver.findElement(By.linkText("new'editor selenium单图文")).getText());
    } catch (Error e) {
      
    	logger.error("48 hour list send post选择post后post preview校验失败");
      Assert.fail("48 hour list send post选择post后post preview校验失败");
    }
    driver.findElement(By.id("group_message_send")).click();
    Thread.sleep(3000);
    String total = driver.findElement(By.xpath("//div[6]/p")).getText();
    String num = catchNum("Total:","",total) + "";
    System.out.println("显示即将发送的人数是：" + num);
   
    driver.findElement(By.cssSelector("button.group_message_submit")).click();
   
    Thread.sleep(8000);
    String time = tableTime();
    System.out.println("实际获取的当前时间是： "+time);
  
    try {
        AssertJUnit.assertEquals("48 Posts List", driver.findElement(By.cssSelector("h1.page_title")).getText());
      } catch (Error e) {
    	  logger.error("48小时立即发送post发送后没有跳转到48 posts list页面");
    	Assert.fail("48小时立即发送post发送后没有跳转到48 posts list页面");
      }
   
    try {
        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[1]")).getText().contains(time));
      } catch (Error e) {
    	  logger.error("48小时立即发送post发送后48 posts list页面发送创建时间不对");
    	 Assert.fail("48小时立即发送post发送后48 posts list页面发送创建时间不对");
      }
      try {
        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[2]")).getText().contains(time));
      } catch (Error e) {
    	  logger.error("48小时立即发送post发送后48 posts list页面发送时间不对");
    	  Assert.fail("48小时立即发送post发送后48 posts list页面发送时间不对");
      }
      try {
        AssertJUnit.assertEquals("Text & Picture", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[3]")).getText());
      } catch (Error e) {
    	  logger.error("48小时立即发送post发送后48 posts list页面 显示发送类型不正确");
        Assert.fail("48小时立即发送post发送后48 posts list页面 显示发送类型不正确"); 
      }
      try {
        AssertJUnit.assertEquals("new editor单图文'selenium", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[4]/a")).getText());
      } catch (Error e) {
    	  logger.error("48小时立即发送post发送后48 posts list页面 显示post name不正确");
    	  Assert.fail("48小时立即发送post发送后48 posts list页面 显示post name不正确");
      }
      try {
        AssertJUnit.assertEquals("", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[5]")).getText());
      } catch (Error e) {
    	  logger.error("48小时立即发送post发送后48 posts list页面 显示发送类型不正确");
    	  Assert.fail("48小时立即发送post发送后48 posts list页面 显示发送类型不正确");
      }
      try {
    	  System.out.println(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText());
    	  AssertJUnit.assertEquals(num, driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText());
      } catch (Error e) {
    	  logger.error("48小时立即发送post发送后48 posts list页面 显示计划发送人数不正确");
    	  Assert.fail("48小时立即发送post发送后48 posts list页面 显示计划发送人数不正确");
      }
    
      try {
        AssertJUnit.assertEquals("Sent", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[8]")).getText());
      } catch (Error e) {
    	  logger.error("48小时立即发送post发送后48 posts list页面 显示发送状态不正确，可能发送失败！！");
    	  Assert.fail("48小时立即发送post发送后48 posts list页面 显示发送状态不正确，可能发送失败！！");
      }
    
     
  }
}

