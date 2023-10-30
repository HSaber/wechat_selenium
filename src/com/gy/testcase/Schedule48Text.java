package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;


public class Schedule48Text extends TestBase{
	private static Logger logger = Logger.getLogger(Schedule48Text.class);
	@Test
	public void schedule48Text() throws Exception {
	
		
		navigation("Schedule a Post");
	    new Select(driver.findElement(By.name("sendType"))).selectByVisibleText("48 Hour List");
	    driver.findElement(By.xpath("//div[@id='res_wb']/div[1]/div/ul/li[2]/a")).click();
	    WebElement editor = driver.findElement(By.xpath("//div[@id='res_wb']/div[2]/div[2]/div[1]/div"));
	    
	    String time=delayTableTime();
	    
	    String delay=delay();
	    System.out.println(time);
	  
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("$('div.editArea>div').text('shedule a 48hour text by selenium,延时10分钟，发送时间是："+ delay +"');", editor);
	    editor.sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[@id='res_wb']/div[2]/div[2]/div[2]/div[1]/a")).click();
	    //driver.findElement(By.cssSelector("div.eItem")).click();
	    driver.findElement(By.xpath("//div[@id='res_wb']/div[2]/div[2]/div[2]/div[3]/table/tbody/tr[5]/td[5]/div")).click();
	    
	    driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div[1]/div[3]/div/div/ul/li[2]/label")).click();
	    driver.findElement(By.id("post_date")).clear();
	    driver.findElement(By.id("post_date")).sendKeys(delay);
	    driver.findElement(By.id("post_date")).sendKeys(Keys.ENTER);
	    Thread.sleep(2000);
	    driver.findElement(By.id("group_message_send")).click();
	    Thread.sleep(3000);
	    String total = driver.findElement(By.xpath("//div[6]/p")).getText();
	    String num = catchNum("Total:","",total) + "";
	    System.out.println("显示即将发送的人数是：" + num);
	    driver.findElement(By.cssSelector("button.group_message_submit")).click();
	    Thread.sleep(3000);
	    String createtime = tableTime();
	    try {
	        AssertJUnit.assertEquals("48 Posts List", driver.findElement(By.cssSelector("h1.page_title")).getText());
	      } catch (Error e) {
	    	  logger.error("48小时定时发送text，点击发送后没有跳转到48 posts list页面");
	    	Assert.fail("48小时定时发送text，点击发送后没有跳转到48 posts list页面");
	      }
	   
	    try {
	        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[1]")).getText().contains(createtime));
	      } catch (Error e) {
	    	  logger.error("48小时定时发送text，48 posts list页面发送创建时间不对");
	    	  Assert.fail("48小时定时发送text，48 posts list页面发送创建时间不对");
	      }
	      try {
	        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[2]")).getText().contains(time));
	      } catch (Error e) {
	    	  logger.error("48小时定时发送text，48 posts list页面发送时间不对");
	    	  Assert.fail("48小时定时发送text，48 posts list页面发送时间不对");
	      }
	      try {
	        AssertJUnit.assertEquals("text", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[3]")).getText());
	      } catch (Error e) {
	    	  logger.error("48小时定时发送text，48 posts list页面 显示发送类型不正确");
	       Assert.fail("48小时定时发送text，48 posts list页面 显示发送类型不正确");
	      }
	      try {
	        AssertJUnit.assertEquals("/凋谢shedule a 48hour text by selenium,延时10分钟，发送时间是："+ delay, driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[4]")).getText());
	      } catch (Error e) {
	    	  logger.error("48小时定时发送text，48 posts list页面 显示post name不正确");
	    	  Assert.fail("48小时定时发送text，48 posts list页面 显示post name不正确");
	      }
	      try {
	        AssertJUnit.assertEquals("", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[5]")).getText());
	      } catch (Error e) {
	    	  logger.error("48小时定时发送text，48 posts list页面 显示发送类型不正确");
	    	 Assert.fail("48小时定时发送text，48 posts list页面 显示发送类型不正确");
	      }
	     try {
	    	  System.out.println(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText());
	    	  AssertJUnit.assertEquals(num, driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText());
	      } catch (Error e) {
	    	  logger.error("48小时定时发送text，48 posts list页面 显示计划发送人数不正确");
	    	 Assert.fail("48小时定时发送text，48 posts list页面 显示计划发送人数不正确");
	      }
	     try {
		        AssertJUnit.assertEquals("Pending", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[7]")).getText());
		      } catch (Error e) {
		    	  logger.error("48小时定时发送text，48 posts list页面 显示实际发送人数不正确，应该是0，还未发送！！");
		    	  Assert.fail("48小时定时发送text，48 posts list页面 显示实际发送人数不正确，应该是0，还未发送！！");
		      }
	      
	     try {
	        AssertJUnit.assertEquals("Pending", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[8]")).getText());
	      } catch (Error e) {
	    	  logger.error("48小时定时发送text，48 posts list页面 显示发送状态不正确，应该是pending状态！！");
	    	  Assert.fail("48小时定时发送text，48 posts list页面 显示发送状态不正确，应该是pending状态！！");
	      }
	   
	     navigation("The 48 Hour List");
		Thread.sleep(2000);
		int a = catchNum("of "," results",driver.findElement(By.cssSelector("div.summary")).getText());
		//Thread.sleep(2000);
		 
		 navigation("Scheduled Posts List");
	  
	    for(int i=1,j=2;i<j;i++){
	    	boolean t1 =driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td")).getText().contains(time);
	    	boolean t2 =driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[2]")).getText().equals("text");
	    	if(i==11){
	    		logger.error("在schedule post list中没找到这条发送text记录，可能有问题，请检查");
	    		Assert.fail("在schedule post list中没找到这条发送text记录，可能有问题，请检查");
	    		
	    		
	    	}else if(t1&&t2){
			   
			    try {
			    	AssertJUnit.assertEquals("text", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[2]")).getText());
			    } catch (Error e) {
			     
			    	logger.error("定时发送的48hour text在schedule post list里内容类型错误");
			      Assert.fail("定时发送的48hour text在schedule post list里内容类型错误");
			    }
			    try {
			    	
			    	AssertJUnit.assertEquals("/凋谢shedule a 48hour text by selenium,延时10分钟，发送时间是："+ delay, driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[3]")).getText());
			    } catch (Error e) {
			      
			    	logger.error("定时发送的48hour text在schedule post list里内容错误");
			      Assert.fail("定时发送的48hour text在schedule post list里内容错误");
			    }
			    try {
			    	
			    	AssertJUnit.assertEquals("48 Hours List Message", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[4]")).getText());
			    } catch (Error e) {
			      
			    	logger.error("定时发送的48hour text在schedule post list里发送类型类型错误");
			      Assert.fail("定时发送的48hour text在schedule post list里发送类型类型错误");
			    }
			    try {
			    	
			    	AssertJUnit.assertEquals(a + "", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[5]")).getText());
			    } catch (Error e) {
			      
			    	logger.error("定时发送的48hour text在schedule post list里发送人数错误");
			     Assert.fail("定时发送的48hour text在schedule post list里发送人数错误");
			    }
			    try {
			    	
			    	AssertJUnit.assertEquals("Pending", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[6]")).getText());
			    } catch (Error e) {
			      
			    	logger.error("定时发送的48hour text在schedule post list里发送状态错误");
			      Assert.fail("定时发送的48hour text在schedule post list里发送状态错误");
			    }
		   }else{
			   		System.out.println("table时间不匹配，table获取的是"+driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td")).getText());
			   		System.out.println("实际时间是："+time);
			   		j++;
			   		System.out.println(j);
		   }
	   }
	}
	
}
