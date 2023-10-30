package com.gy.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Schedule48SegmentationReplyPost extends TestBase{
	private static Logger logger = Logger.getLogger(Schedule48SegmentationReplyPost.class);
	//@Test
	@Test(dependsOnGroups="verify48Post")
	public void schedule48SegmentationReplyPost() throws Exception {
		 navigation("Segmentation");
		    driver.findElement(By.cssSelector("a.btn > button")).click();
		    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
		    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("Profile");
		    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"))).selectByVisibleText("Wechat Country");
		    driver.findElement(By.cssSelector("option[value=\"country\"]")).click();
		    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("equals");
		    driver.findElement(By.cssSelector("option[value=\"equals\"]")).click();
		    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]"))).selectByVisibleText("中国");
		    driver.findElement(By.cssSelector("div.insert_row > div.icon")).click();
		    driver.findElement(By.cssSelector("div.property_filter.last > div.property_dropdown.filterable_dropdown > select.rule_types")).click();
		    new Select(driver.findElement(By.cssSelector("div.property_filter.last > div.property_dropdown.filterable_dropdown > select.rule_types"))).selectByVisibleText("48 Hours");
		    driver.findElement(By.cssSelector("div.property_filter.last > div.property_dropdown.filterable_dropdown > select.rule_types > option[value=\"48hours\"]")).click();
		    driver.findElement(By.linkText("Preview")).click();
		   
			String preview=driver.findElement(By.cssSelector("div.summary")).getText();
			System.out.println(preview);
			while(preview.equals("")){
				preview=driver.findElement(By.cssSelector("div.summary")).getText();
				logger.info("segmentation preview没加载完成,继续等待中！");
			}
			logger.info("segmentation preview加载完成后display结果是： "+preview);
			String snum = catchNum("of "," result",preview) + "";
			logger.info("segmentation preview加载完成后显示的人数是：" + snum);
		
		    navigation("Schedule a Post");
		    new Select(driver.findElement(By.name("sendType"))).selectByVisibleText("48 Hour List");
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("jQuery('input.hrs_segmentation').css('visibility','visible');");
		    driver.findElement(By.xpath("(//input[@name='48hrs_segmentation_send_type'])[2]")).click();
		    new Select(driver.findElement(By.id("48hrs_segmentation"))).selectByVisibleText("seleniumchina");
		    Thread.sleep(2000);
		    new Select(driver.findElement(By.id("res_id"))).selectByVisibleText("new editor multi reply post'selenium测试");
		    Thread.sleep(2000);
			try {
		      AssertJUnit.assertEquals("new editor multi reply post'selenium测试--1", driver.findElement(By.linkText("new editor multi reply post'selenium测试--1")).getText());
		    } catch (Error e) {
		      
		    	logger.error("Schedule48SegmentationReplyPost选择post后post preview校验失败");
		      Assert.fail("Schedule48SegmentationReplyPost选择post后post preview校验失败");
		    }
		    
		    String time=delayTableTime();
		    
		    String delay=delay();
		    System.out.println(time);
		  
		    driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div[1]/div[3]/div/div/ul/li[2]/label")).click();
		    driver.findElement(By.id("post_date")).clear();
		    driver.findElement(By.id("post_date")).sendKeys(delay);
		    driver.findElement(By.id("post_date")).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    driver.findElement(By.id("group_message_send")).click();
		    Thread.sleep(3000);
		    String total = driver.findElement(By.xpath("//div[6]/p")).getText();
		    String num = catchNum("Total:","",total) + "";
		    System.out.println("Schedule48SegmentationReplyPost显示即将发送的人数是：" + num);
		    
		    try{
		    	assertEquals(num,snum);
		    }catch(Error e){
		    	logger.error("Schedule48SegmentationReplyPost显示将要发送的人数与segmentaion中查询出的人数不一致，请检查！！！！！");
		    	fail("Schedule48SegmentationReplyPost显示将要发送的人数与segmentaion中查询出的人数不一致，请检查！！！！！");
		    }
		    
		    driver.findElement(By.cssSelector("button.group_message_submit")).click();
		    Thread.sleep(3000);
		    String createtime = tableTime();
		    try {
		        AssertJUnit.assertEquals("48 Posts List", driver.findElement(By.cssSelector("h1.page_title")).getText());
		      } catch (Error e) {
		    	  logger.error("Schedule48SegmentationReplyPost，点击发送后没有跳转到48 posts list页面");
		    	Assert.fail("Schedule48SegmentationReplyPost，点击发送后没有跳转到48 posts list页面");
		      }
		   
		    try {
		        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[1]")).getText().contains(createtime));
		      } catch (Error e) {
		    	  logger.error("Schedule48SegmentationReplyPost，48 posts list页面发送创建时间不对");
		    	  Assert.fail("Schedule48SegmentationReplyPost，48 posts list页面发送创建时间不对");
		      }
		      try {
		        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[2]")).getText().contains(time));
		      } catch (Error e) {
		    	  logger.error("Schedule48SegmentationReplyPost，48 posts list页面发送时间不对");
		    	  Assert.fail("Schedule48SegmentationReplyPost，48 posts list页面发送时间不对");
		      }
		      try {
		        AssertJUnit.assertEquals("Text & Picture", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[3]")).getText());
		      } catch (Error e) {
		    	  logger.error("Schedule48SegmentationReplyPost，48 posts list页面 显示发送类型不正确");
		       Assert.fail("Schedule48SegmentationReplyPost，48 posts list页面 显示发送类型不正确");
		      }
		      try {
		        AssertJUnit.assertEquals("new editor multi reply post'selenium测试", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[4]/a")).getText());
		      } catch (Error e) {
		    	  logger.error("Schedule48SegmentationReplyPost，48 posts list页面 显示post name不正确");
		    	  Assert.fail("Schedule48SegmentationReplyPost，48 posts list页面 显示post name不正确");
		      }
		      
		      try {
		        AssertJUnit.assertEquals("seleniumchina", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[5]")).getText());
		      } catch (Error e) {
		    	  logger.error("Schedule48SegmentationReplyPost，48 posts list页面 显示segmentation不正确");
		    	 Assert.fail("Schedule48SegmentationReplyPost，48 posts list页面 显示segmentation不正确");
		      }
		     try {
		    	  System.out.println(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText());
		    	  AssertJUnit.assertEquals(num, driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText());
		      } catch (Error e) {
		    	  logger.error("Schedule48SegmentationReplyPost，48 posts list页面 显示计划发送人数不正确");
		    	 Assert.fail("Schedule48SegmentationReplyPost，48 posts list页面 显示计划发送人数不正确");
		      }
		     try {
			        AssertJUnit.assertEquals("Pending", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[7]")).getText());
			      } catch (Error e) {
			    	  logger.error("Schedule48SegmentationReplyPost，48 posts list页面 显示实际发送人数不正确，应该是0，还未发送！！");
			    	  Assert.fail("Schedule48SegmentationReplyPost，48 posts list页面 显示实际发送人数不正确，应该是0，还未发送！！");
			      }
		      
		     try {
		        AssertJUnit.assertEquals("Pending", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[8]")).getText());
		      } catch (Error e) {
		    	  logger.error("Schedule48SegmentationReplyPost，48 posts list页面 显示发送状态不正确，应该是pending状态！！");
		    	  Assert.fail("Schedule48SegmentationReplyPost，48 posts list页面 显示发送状态不正确，应该是pending状态！！");
		      }
		   
		   
			 
		    navigation("Scheduled Posts List");
		  
		    for(int i=1,j=2;i<j;i++){
		    	if(i==11){
		    		logger.error("在schedule post list中没找到这条发送Schedule48SegmentationReplyPost记录，可能有问题，请检查");
		    		Assert.fail("在schedule post list中没找到这条发送Schedule48SegmentationReplyPost记录，可能有问题，请检查");
		    		
		    		
		    	}else if(driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td")).getText().contains(time)){
				   
				    try {
				    	AssertJUnit.assertEquals("Text & Picture", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[2]")).getText());
				    } catch (Error e) {
				     
				    	logger.error("Schedule48SegmentationReplyPost在schedule post list里内容类型错误");
				      Assert.fail("Schedule48SegmentationReplyPost在schedule post list里内容类型错误");
				    }
				    try {
				    	
				    	AssertJUnit.assertEquals("new editor multi reply post'selenium测试", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[3]/a")).getText());
				    } catch (Error e) {
				      
				    	logger.error("Schedule48SegmentationReplyPost在schedule post list里内容错误");
				      Assert.fail("Schedule48SegmentationReplyPost在schedule post list里内容错误");
				    }
				    try {
				    	
				    	AssertJUnit.assertEquals("48 Hours List Message", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[4]")).getText());
				    } catch (Error e) {
				      
				    	logger.error("Schedule48SegmentationReplyPost在schedule post list里发送类型类型错误");
				      Assert.fail("Schedule48SegmentationReplyPost在schedule post list里发送类型类型错误");
				    }
				    try {
				    	
				    	AssertJUnit.assertEquals(snum + "", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[5]")).getText());
				    } catch (Error e) {
				      
				    	logger.error("Schedule48SegmentationReplyPost在schedule post list里发送人数错误");
				     Assert.fail("Schedule48SegmentationReplyPost在schedule post list里发送人数错误");
				    }
				    try {
				    	
				    	AssertJUnit.assertEquals("Pending", driver.findElement(By.xpath("//div[@id='post-queue-grid']/div/table/tbody/tr["+i+"]/td[6]")).getText());
				    } catch (Error e) {
				      
				    	logger.error("Schedule48SegmentationReplyPost在schedule post list里发送状态错误");
				      Assert.fail("Schedule48SegmentationReplyPost在schedule post list里发送状态错误");
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
