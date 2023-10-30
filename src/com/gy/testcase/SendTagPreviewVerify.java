package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class SendTagPreviewVerify extends TestBase{
	private static Logger logger = Logger.getLogger(SendTagPreviewVerify.class);
	//@Test
	 @Test(dependsOnGroups="sendTagPreview")
	 
	  public void sendTagPreviewVerify() throws Exception {
		
		 navigation("Segmentation");
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
		    
		    navigation("Sent Posts List");
		    for(int j=1;j<a+1;j++){
				    
				    try {
				      assertEquals("Text & Picture", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr["+j+"]/td[2]")).getText());
				    } catch (Error e) {
				      
				    	logger.error("send tag preview在send post list table中内容类型错误");
				      Assert.fail("send tag preview在send post list table中内容类型错误");
				    }
				    try {
				      assertEquals("new editor多图文'selenium", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr["+j+"]/td[3]/a")).getText());
				    } catch (Error e) {
				     
				    	logger.error("send tag preview在send post list table中post名称错误");
				     Assert.fail("send tag preview在send post list table中post名称错误");
				    }
				    try {
				      assertEquals("Preview", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr["+j+"]/td[4]")).getText());
				    } catch (Error e) {
				     
				    	logger.error("send tag preview在send post list table中发送类型错误");
				      Assert.fail("send tag preview在send post list table中发送类型错误");
				    }
				    try {
				      assertEquals("1", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr["+j+"]/td[5]")).getText());
				    } catch (Error e) {
				      
				    	logger.error("send tag preview在send post list table中发送人数错误");
				     Assert.fail("send tag preview在send post list table中发送人数错误");
				    }
				    try {
				      assertEquals("Sent", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr["+j+"]/td[6]")).getText());
				    } catch (Error e) {
				     
				    	logger.error("send tag preview在send post list table中内容状态错误");
				      Assert.fail("send tag preview在send post list table中内容状态错误");
				    }
				    
		    }
	 }
}
