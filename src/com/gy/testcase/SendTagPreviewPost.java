package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SendTagPreviewPost extends TestBase{
	private static Logger logger = Logger.getLogger(SendTagPreviewPost.class);
	//@Test
	 @Test(groups="sendTagPreview",dependsOnGroups="sendpreview",alwaysRun=true)
	  public void sendTagPreview() throws Exception {
		
	    String name[] = {"rainbow","H.'","Candy"};
	    for(int i=0;i<name.length;i++)
	    {
		navigation("Followers");
	    driver.findElement(By.name("WechatCustomer[nickname]")).clear();
	    driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(name[i]);
	    driver.findElement(By.name("WechatCustomer[action_count]")).sendKeys(Keys.ENTER);
	    Thread.sleep(4000);
	    if(name[i].contains("rainbow")){
	    driver.findElement(By.linkText(name[i])).click();
	    }else{
	    	driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[1]/td[1]/a[2]")).click();
	    }
	   
	    driver.findElement(By.linkText("add")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@type='text']")).click();
	    driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("测试组tag3");
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.ENTER);
	    
	    driver.findElement(By.id("profileEdit")).click();
	    
	    driver.findElement(By.cssSelector("button.savehandle.btn")).click();
	    }
	   
	    navigation("Segmentation");
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
	    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("Profile");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"))).selectByVisibleText("Tag");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[3]"))).selectByVisibleText("Name");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]"))).selectByVisibleText("测试组tag3");
	    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("greater than");
	    driver.findElement(By.cssSelector("input.values.amount")).clear();
	    driver.findElement(By.cssSelector("input.values.amount")).sendKeys("0");
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(3000);
	    int a = catchNum("of "," results",driver.findElement(By.cssSelector("div.summary")).getText());
	    System.out.println("被打上测试组tag3的人的个数是：" + a);
	   
	    navigation("Schedule a Post");
	    driver.findElement(By.cssSelector("a.btnZi > button")).click();
	    new Select(driver.findElement(By.id("preview-tag"))).selectByVisibleText("测试组tag3");
	    new Select(driver.findElement(By.id("res_id"))).selectByVisibleText("new editor多图文'selenium");
	    try {
	      AssertJUnit.assertEquals("new'editor selenium多图文--4", driver.findElement(By.linkText("new'editor selenium多图文--4")).getText());
	    } catch (Error e) {
	     
	    	logger.error("用tag发送preview，选择post后post preview显示不正常");
	     Assert.fail("用tag发送preview，选择post后post preview显示不正常");
	    }
	    driver.findElement(By.id("group_message_send")).click();
	    try {
	      AssertJUnit.assertEquals("This post will be sent to " + a +" followers", driver.findElement(By.xpath("//div[5]/p")).getText());
	    } catch (Error e) {
	      //verificationErrors.append(e.toString());
	    	AssertJUnit.fail("用tag发送preview，发送前发送人数显示错误");
	    	logger.error("用tag发送preview，发送前发送人数显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("Materials: new editor多图文'selenium", driver.findElement(By.xpath("//div[5]/p[2]")).getText());
	    } catch (Error e) {
	     
	    	logger.error("用tag发送preview，发送前发送post名称显示错误");
	      Assert.fail("用tag发送preview，发送前发送post名称显示错误");
	    }
	    driver.findElement(By.cssSelector("button.group_message_submit.btn")).click();
	    Thread.sleep(5000);
	    try {
	        AssertJUnit.assertEquals("Post Sending Queue", driver.findElement(By.cssSelector("h1.page_title")).getText());
	      } catch (Error e) {
	       
	    	  logger.error("send tag preview发送后没有跳到send post list页面");
	    	  Assert.fail("send tag preview发送后没有跳到send post list页面");
	      }
	   
	  }
}
