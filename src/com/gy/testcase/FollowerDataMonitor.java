package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class FollowerDataMonitor extends TestBase{
	private static Logger logger = Logger.getLogger(FollowerDataMonitor.class);
	 @Test
	 public void followerDataMonitor() throws Exception {
		 //筛选日期，将日期定位到前一天
		 	driver.findElement(By.name("select")).click();
		 	int n1=0;
		 	if(isElementPresent(By.cssSelector("div.drp-calendar.drp-calendar-end > ul.drp-days > li.drp-day.drp-day-selected"))){
		 		String l1 = driver.findElement(By.cssSelector("div.drp-calendar.drp-calendar-end > ul.drp-days > li.drp-day.drp-day-selected")).getText();
		 		if(l1.equals("1")){
		 				driver.findElement(By.cssSelector("div.drp-calendar.drp-calendar-end > div.drp-month-picker > div.drp-arrow")).click();
		 				driver.findElement(By.xpath("(//form[@id='dateContainer']/div/div/div[3]/div[3]/ul[2]/li)[last()]")).click();
		 		}else{
			 		 n1=Integer.parseInt(l1)-1;
			 		 driver.findElement(By.xpath("//form[@id='dateContainer']/div/div/div[3]/div[3]/ul[2]/li[text()='"+n1+"']")).click();
		 		}
		 	}else if(isElementPresent(By.cssSelector("div.drp-calendar.drp-calendar-end > ul.drp-days > li.drp-day.drp-day-selected.drp-day-last-in-row"))){
		 		String l1 = driver.findElement(By.cssSelector("div.drp-calendar.drp-calendar-end > ul.drp-days > li.drp-day.drp-day-selected.drp-day-last-in-row")).getText();
		 		if(l1.equals("1")){
	 				driver.findElement(By.cssSelector("div.drp-calendar.drp-calendar-end > div.drp-month-picker > div.drp-arrow")).click();
	 				driver.findElement(By.xpath("(//form[@id='dateContainer']/div/div/div[3]/div[3]/ul[2]/li)[last()]")).click();
		 		}else{
			 		 n1=Integer.parseInt(l1)-1;
			 		 driver.findElement(By.xpath("//form[@id='dateContainer']/div/div/div[3]/div[3]/ul[2]/li[text()='"+n1+"']")).click();
		 		}
		 	}else{
		 		logger.error("日历筛选器有问题，没有时间被选中，请检查！！！");
		 		Assert.fail("日历筛选器有问题，没有时间被选中，请检查！！！");
		 	}
		 	
		 	if(n1==0){
		 		driver.findElement(By.xpath("//form[@id='dateContainer']/div/div/div[3]/div/ul[2]/li[text()='"+n1+"']")).click();
		 	}else{
			 	driver.findElement(By.cssSelector("div.drp-arrow.drp-arrow-right")).click();
			 	driver.findElement(By.xpath("//form[@id='dateContainer']/div/div/div[3]/div/ul[2]/li[text()='"+n1+"']")).click();
		 	}
		 	
		 	String day = driver.findElement(By.cssSelector("div.drp-calendar-date")).getText();
		 	driver.findElement(By.cssSelector("div.switchbutton")).click();
		 	
		 //获取dashboard上的数据
		 	String s = driver.findElement(By.cssSelector("div.count_number")).getText();
		 	System.out.println(s);
		 	int newFollower=0;
		 	try{
		 		newFollower = catchNum(""," vs",s);
		 	}catch(java.lang.NumberFormatException e){
		 		newFollower=0;
		 	}
		 	logger.info("dashboard"+ day +"当天获取的newfollower数据是："+newFollower);
		 	
		 	String s1 = driver.findElement(By.xpath("//div[2]/div[2]/div[2]/div[2]")).getText();
		 	int totalFollower=0;
		 	try{
		 		totalFollower = catchNum(""," vs",s1);
		 	}catch(java.lang.NumberFormatException e){
		 		totalFollower=0;
		 	}
		 	logger.info("dashboard"+ day +"当天获取的totalFollower数据是："+totalFollower);
		 	
		 	String s2 = driver.findElement(By.xpath("//div[2]/div[3]/div[2]")).getText();
		 	int unFollower=0;
		 	try{
		 		unFollower = catchNum(""," vs",s2);
		 	}catch(java.lang.NumberFormatException e){
		 		unFollower=0;
		 	}
		 	logger.info("dashboard"+ day +"当天获取的unFollower数据是："+unFollower);
		 	
		 //获取follower growth上的数据
		 	driver.findElement(By.linkText("Analytics")).click();
		 	driver.findElement(By.linkText("Follower Analytics")).click();
		 	driver.findElement(By.linkText("Follower Growth Analytics")).click();
		 	
		 	String time = driver.findElement(By.cssSelector("td")).getText();
		 	int newgrowth = Integer.parseInt(driver.findElement(By.xpath("//div[@id='wechat-customer-grid']/div/table/tbody/tr/td[2]")).getText());
		 	logger.info("该账号在follower growth页面"+time+"这一天获取的newFollower的值是："+newgrowth);
		 	int ungrowth = Integer.parseInt(driver.findElement(By.xpath("//div[@id='wechat-customer-grid']/div/table/tbody/tr/td[3]")).getText());
		 	logger.info("该账号在follower growth页面"+time+"这一天获取的unFollower的值是："+ungrowth);
		 	int totalgrowth = Integer.parseInt(driver.findElement(By.xpath("//div[@id='wechat-customer-grid']/div/table/tbody/tr/td[5]")).getText());
		 	logger.info("该账号在follower growth页面"+time+"这一天获取的totalFollower的值是："+totalgrowth);
		 
		 //把dashboard上的值和follower growth页面上的值做对比
		 	
		    try {
		    	Assert.assertEquals(newFollower, newgrowth);
		    } catch (Error e) {
		    	int difference=Math.abs(newFollower-newgrowth);
		    	logger.error(time+"这一天dashboard上newfollower的数据和follower growth上的数据不一样，相差"+ difference +"请检查");
		    	Assert.fail(time+"这一天dashboard上newfollower的数据和follower growth上的数据不一样，相差"+ difference +"请检查");
		    	//这里还缺代码，需要确定逻辑误差多大的时候报错
		    }
		    try {
		    	Assert.assertEquals(totalFollower, totalgrowth);
		    } catch (Error e) {
		    	int difference=Math.abs(totalFollower-totalgrowth);
		    	logger.error(time+"这一天dashboard上totalFollower的数据和follower growth上的数据不一样，相差"+ difference +"请检查");
		    	Assert.fail(time+"这一天dashboard上totalFollower的数据和follower growth上的数据不一样，相差"+ difference +"请检查");
		    	//这里还缺代码，需要确定逻辑误差多大的时候报错
		    }
		    try {
		    	Assert.assertEquals(unFollower, ungrowth);
		    } catch (Error e) {
		    	int difference=Math.abs(unFollower-ungrowth);
		    	logger.error(time+"这一天dashboard上unFollower的数据和follower growth上的数据不一样，相差"+ difference +"请检查");
		    	Assert.fail(time+"这一天dashboard上unFollower的数据和follower growth上的数据不一样，相差"+ difference +"请检查");
		    	//这里还缺代码，需要确定逻辑误差多大的时候报错
		    }
		   
	 }
}
