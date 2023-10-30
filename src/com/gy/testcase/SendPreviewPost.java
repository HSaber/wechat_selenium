package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;

public class SendPreviewPost extends TestBase{
	private static Logger logger = Logger.getLogger(SendPreviewPost.class);
	//@Test
	@Test(groups="sendpreview",dependsOnGroups="addMultiPostSummary")
	public void sendpreview() throws Exception {
		
		navigation("Manage Posts");
		boolean search = isElementPresent(By.xpath("//div[@id='js_search_title']/span/input"));
		if(!search){
			driver.findElement(By.id("post_filter_search")).click();
		    driver.findElement(By.xpath("//div[@id='js_search_title']/span/input")).clear();
		    driver.findElement(By.xpath("//div[@id='js_search_title']/span/input")).sendKeys("new editor多图文'selenium");
		    driver.findElement(By.cssSelector("i.icon16_common.search_gray")).click();
		    Thread.sleep(10000);
		    }
		    driver.findElement(By.xpath("//div[@id='main']/div/div/div/div[2]/ul/li/a/span")).click();
		    //Thread.sleep(3000);
		    boolean status = isElementPresent(By.id("J_prevNews"));
		    while(status==false){
		    	status = isElementPresent(By.id("J_prevNews"));
		    	System.out.println("正在跳转进post update页面，请等待！！");
		    }
		   // driver.findElement(By.linkText("Switch to the Older Version")).click();
		   // Thread.sleep(3000);
		    driver.findElement(By.id("J_prevNews")).click();
		    //Thread.sleep(5000);
		    boolean status1 = isElementPresent(By.xpath("//div[@id='page']/div[2]/div[2]/div[1]/button[3]"));
		    while(status1==false){
		    	status1 = isElementPresent(By.xpath("//div[@id='page']/div[2]/div[2]/div[1]/button[3]"));
		    	System.out.println("正在跳转进post preview页面，请等待！！");
		    }
		    driver.switchTo().frame(0);
		    boolean status2 = isElementPresent(By.linkText("new'editor selenium多图文--1"));
		    while(status2==false){
		    	status2 = isElementPresent(By.linkText("new'editor selenium多图文--1"));
		    	System.out.println("post 预览还没加载出来，请等待！！");
		    }
		    try {
		      AssertJUnit.assertEquals("new'editor selenium多图文--1", driver.findElement(By.linkText("new'editor selenium多图文--1")).getText());
		    } catch (Error e) {
		      
		    	logger.error("从post update页面跳到preview post页面后图文预览显示错误");
		      Assert.fail("从post update页面跳到preview post页面后图文预览显示错误");
		    }
		    try {
		      AssertJUnit.assertEquals("new'editor selenium多图文--4", driver.findElement(By.linkText("new'editor selenium多图文--4")).getText());
		    } catch (Error e) {
		     
		    	logger.error("从post update页面跳到preview post页面后图文预览显示错误");
		      Assert.fail("从post update页面跳到preview post页面后图文预览显示错误");
		    }
		    driver.switchTo().defaultContent();
		    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[1]/button[2]")).click();
		    Thread.sleep(3000);
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("jQuery('input').css('visibility','visible');");
		    driver.findElement(By.cssSelector("div.preview-wechat-id.pullDown > #preview-method")).click();
		    driver.findElement(By.id("preview-wechat-id")).clear();
		    driver.findElement(By.id("preview-wechat-id")).sendKeys("Rainbowgy121");
		    try {
		      AssertJUnit.assertEquals("new'editor selenium多图文--1", driver.findElement(By.linkText("new'editor selenium多图文--1")).getText());
		    } catch (Error e) {
		     
		    	logger.error("从post update页面跳到send preview页面后图文没有自动选中");
		     Assert.fail("从post update页面跳到send preview页面后图文没有自动选中");
		    }
		    try {
		      AssertJUnit.assertEquals("new'editor selenium多图文--4", driver.findElement(By.linkText("new'editor selenium多图文--4")).getText());
		    } catch (Error e) {
		   
		    	logger.error("从post update页面跳到send preview页面后图文没有自动选中");
		     Assert.fail("从post update页面跳到send preview页面后图文没有自动选中");
		    }
		    driver.findElement(By.id("group_message_send")).click();
		    //Thread.sleep(3000);
		    boolean status3 = isElementPresent(By.xpath("//div[5]/p"));
		    while(status3==false){
		    	status3 = isElementPresent(By.xpath("//div[5]/p"));
		    	System.out.println("post 发送preview,正在加载弹框中，请等待！！");
		    }
		    try {
		      AssertJUnit.assertEquals("This post will be sent to 1 followers", driver.findElement(By.xpath("//div[5]/p")).getText());
		    } catch (Error e) {
		      
		    	logger.error("send preview弹出的框中发送人数显示错误");
		      Assert.fail("send preview弹出的框中发送人数显示错误");
		    }
		    try {
		      AssertJUnit.assertEquals("Materials: new editor多图文'selenium", driver.findElement(By.xpath("//div[5]/p[2]")).getText());
		    } catch (Error e) {
		     
		    	logger.error("send preview弹出的框中发送内容显示错误");
		     Assert.fail("send preview弹出的框中发送内容显示错误");
		    }
		    driver.findElement(By.cssSelector("button.group_message_submit.btn")).click();
		    Thread.sleep(5000);
		    try {
		      AssertJUnit.assertEquals("Post Sending Queue", driver.findElement(By.cssSelector("h1.page_title")).getText());
		    } catch (Error e) {
		      
		    	logger.error("send preview发送后没有跳转到send post list页面");
		     Assert.fail("send preview发送后没有跳转到send post list页面");
		    }
		    Thread.sleep(5000);
		    navigation("Sent Posts List");
		    Thread.sleep(3000);
		    try {
		      AssertJUnit.assertEquals("new editor多图文'selenium", driver.findElement(By.linkText("new editor多图文'selenium")).getText());
		    } catch (Error e) {
		     
		    	logger.error("send preview发送失败或者table中post name显示错误");
		      Assert.fail("send preview发送失败或者table中post name显示错误");
		    }
		    try {
		      AssertJUnit.assertEquals("Preview", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[4]")).getText());
		    } catch (Error e) {
		      
		    	logger.error("send preview发送失败或者table中post send type显示错误");
		     Assert.fail("send preview发送失败或者table中post send type显示错误");
		    }
		    try {
		      AssertJUnit.assertEquals("1", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[5]")).getText());
		    } catch (Error e) {
		     
		    	logger.error("send preview发送失败或者table中post发送人数显示错误");
		      Assert.fail("send preview发送失败或者table中post发送人数显示错误");
		    }
		    try {
		      AssertJUnit.assertEquals("Sent", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[6]")).getText());
		    } catch (Error e) {
		    
		    	logger.error("send preview发送失败或者table中post send status显示错误");
		    Assert.fail("send preview发送失败或者table中post send status显示错误");
		    }
		    
	  }
}

