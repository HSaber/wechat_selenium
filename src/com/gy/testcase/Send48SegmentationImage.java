package com.gy.testcase;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class Send48SegmentationImage extends TestBase{
	private static Logger logger = Logger.getLogger(Send48SegmentationImage.class);
	@Test
	public void send48SegmentationImage() throws Exception {
		//48小时segmentation立即发送image
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
    driver.findElement(By.linkText("Image")).click();
    
    driver.findElement(By.name("files[]")).sendKeys(file+"/com/material/48HrSegmentation.gif");
    
    String src=driver.findElement(By.id("pic_Box")).getAttribute("src");
    System.out.println("图片src是： "+src);
    
    while(src==null){
    	src = driver.findElement(By.id("pic_Box")).getAttribute("src");
    	System.out.println("图片上传未完成，请等待！！");
    }
    driver.findElement(By.id("group_message_send")).click();
    Thread.sleep(3000);
    String total = driver.findElement(By.xpath("//div[6]/p")).getText();
    String num = catchNum("Total:","",total) + "";
    System.out.println("send48SegmentationImage显示即将发送的人数是：" + num);
    try{
    	assertEquals(num,snum);
    }catch(Error e){
    	logger.error("send48SegmentationImage显示将要发送的人数与segmentaion中查询出的人数不一致，请检查！！！！！");
    	fail("send48SegmentationImage显示将要发送的人数与segmentaion中查询出的人数不一致，请检查！！！！！");
    }
   
    driver.findElement(By.cssSelector("button.group_message_submit")).click();
   
    Thread.sleep(8000);
    String time = tableTime();
    System.out.println("实际获取的当前时间是： "+time);
  
    try {
        AssertJUnit.assertEquals("48 Posts List", driver.findElement(By.cssSelector("h1.page_title")).getText());
      } catch (Error e) {
    	  logger.error("send48SegmentationImage发送后没有跳转到48 posts list页面");
    	Assert.fail("send48SegmentationImage发送后没有跳转到48 posts list页面");
      }
   
    try {
        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[1]")).getText().contains(time));
      } catch (Error e) {
    	  logger.error("send48SegmentationImage发送后48 posts list页面发送创建时间不对");
    	 Assert.fail("send48SegmentationImage发送后48 posts list页面发送创建时间不对");
      }
      try {
        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[2]")).getText().contains(time));
      } catch (Error e) {
    	  logger.error("send48SegmentationImage发送后48 posts list页面发送时间不对");
    	  Assert.fail("send48SegmentationImage发送后48 posts list页面发送时间不对");
      }
      try {
        AssertJUnit.assertEquals("Picture", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[3]")).getText());
      } catch (Error e) {
    	  logger.error("send48SegmentationImage发送后48 posts list页面 显示发送类型不正确");
        Assert.fail("send48SegmentationImage发送后48 posts list页面 显示发送类型不正确"); 
      }
      try {
        AssertJUnit.assertNotNull(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[4]/img")).getAttribute("src"));
      } catch (Error e) {
    	  logger.error("48小时segmentation立即发送image发送后image不能正常显示");
    	  Assert.fail("48小时segmentation立即发送image发送后image不能正常显示");
      }
      try {
        AssertJUnit.assertEquals("seleniumchina", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[5]")).getText());
      } catch (Error e) {
    	  logger.error("48小时segmentation立即发送image发送后segmentation显示错误！");
    	  Assert.fail("48小时segmentation立即发送image发送后segmentation显示错误！");
      }
      try {
    	  System.out.println(driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText());
    	  AssertJUnit.assertEquals(num, driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText());
      } catch (Error e) {
    	  logger.error("send48SegmentationImage发送后48 posts list页面 显示计划发送人数不正确");
    	  Assert.fail("send48SegmentationImage发送后48 posts list页面 显示计划发送人数不正确");
      }
    
      try {
        AssertJUnit.assertEquals("Sent", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[8]")).getText());
      } catch (Error e) {
    	  try{
    	        AssertJUnit.assertEquals("Processing", driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[8]")).getText());
  		  
    	  }catch (Error e1) {
    	  logger.error("send48SegmentationImage发送后48 posts list页面 显示发送状态不正确，可能发送失败！！");
    	  Assert.fail("send48SegmentationImage发送后48 posts list页面 显示发送状态不正确，可能发送失败！！");
    	  }
    }
    
     
  }
}
