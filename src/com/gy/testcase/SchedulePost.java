package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
public class SchedulePost extends TestBase{
	
	@Test
	  public void schedulepost() throws Exception {
		
		
		String time=delayTableTime();
		String delay=delay();
		System.out.println(time);
		navigation("Schedule a Post");
	    //下拉框的使用
	    new Select(driver.findElement(By.name("sendType"))).selectByVisibleText("Segmentation");
	    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("selenium_A_Group(50%)");
	    new Select(driver.findElement(By.id("res_id"))).selectByVisibleText("single'post单图文“测试” selenium");
	    try {
	        AssertJUnit.assertEquals("单图文'title“测试” selenium", driver.findElement(By.linkText("单图文'title“测试” selenium")).getText());
	      } catch (Error e) {
	       
	        System.out.println("segmentation send post选择post后，post preview没有正常显示");
	       Assert.fail("segmentation send post选择post后，post preview没有正常显示");
	      }
	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div[3]/div[1]/div/ul/li[2]/label")).click();
	    driver.findElement(By.id("post_date")).clear();
	    driver.findElement(By.id("post_date")).sendKeys(delay);
	    driver.findElement(By.id("post_date")).sendKeys(Keys.ENTER);
	    Thread.sleep(2000);
	    driver.findElement(By.id("group_message_send")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("button.group_message_submit")).click();
	    Thread.sleep(2000);
	    
	    navigation("Segmentation");
		 driver.findElement(By.name("DynamicRules[name]")).clear();
		 driver.findElement(By.name("DynamicRules[name]")).sendKeys("selenium_A_Group(50%)");
		 driver.findElement(By.name("DynamicRules[note]")).sendKeys(Keys.ENTER);
		 Thread.sleep(2000);
		 
		 driver.findElement(By.cssSelector("img[alt=\"Update\"]")).click();
		 Thread.sleep(10000);
		 int a = catchNum("of "," results",driver.findElement(By.cssSelector("div.summary")).getText());
		 System.out.println("A group里获取的数据是：" + a);  
	    
		 navigation("Scheduled Posts List");
	   
	    for(int i=1,j=2;i<j;i++){
	    	if(i==11){
	    		System.out.println("在schedule post list中没找到这条segmentation(A group) send post记录，可能有问题，请检查");
	    		Assert.fail("在schedule post list中没找到这条segmentation(A group) send post记录，可能有问题，请检查");
	    		
	    		
	    	}else if(driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td")).getText().contains(time)){
			   
			    try {
			    	AssertJUnit.assertEquals("Text & Picture", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[2]")).getText());
			    } catch (Error e) {
			      
			      System.out.println("定时发送的segmentation send post在schedule post list里内容类型错误");
			      Assert.fail("定时发送的segmentation send post在schedule post list里内容类型错误");
			    }
			    try {
			    	
			    	AssertJUnit.assertEquals("single'post单图文“测试” selenium", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[3]/a")).getText());
			    } catch (Error e) {
			      
			      System.out.println("定时发送的segmentation send post在schedule post list里post name错误");
			      Assert.fail("定时发送的segmentation send post在schedule post list里post name错误");
			    }
			    try {
			    	
			    	AssertJUnit.assertEquals("Segmentation Message", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[4]")).getText());
			    } catch (Error e) {
			     
			      System.out.println("定时发送的segmentation send post在schedule post list里发送类型错误");
			     Assert.fail("定时发送的segmentation send post在schedule post list里发送类型错误");
			    }
			    try {
			    	
			    	AssertJUnit.assertEquals(a + "", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[5]")).getText());
			    } catch (Error e) {
			     
			      System.out.println("定时发送的segmentation send post在schedule post list里发送人数错误");
			      Assert.fail("定时发送的segmentation send post在schedule post list里发送人数错误");
			    }
			    try {
			    	
			    	AssertJUnit.assertEquals("Pending", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[6]")).getText());
			    } catch (Error e) {
			     
			      System.out.println("定时发送的segmentation send post在schedule post list里发送状态错误");
			     Assert.fail("定时发送的segmentation send post在schedule post list里发送状态错误");
			    }
			    try {
			        AssertJUnit.assertEquals("seleniumtag", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[7]")).getText());
			      } catch (Error e) {
			       
			        System.out.println("定时发送的segmentation post在schedule post list里post tag显示错误");
			       Assert.fail("定时发送的segmentation post在schedule post list里post tag显示错误");
			      }
			      try {
			        AssertJUnit.assertEquals("selenium_A_Group(50%)", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[8]")).getText());
			      } catch (Error e) {
			       
			        System.out.println("定时发送的segmentation post在schedule post list里segmentation name显示错误");
			        Assert.fail("定时发送的segmentation post在schedule post list里segmentation name显示错误");
			      }
		   
	    	}else{
			   		System.out.println("table时间不匹配，table获取的是"+driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td")).getText());
			   		System.out.println("实际时间是：" + time);
			   		j++;
			   		System.out.println(j);
		   }
	   }
	    
	  }
}
