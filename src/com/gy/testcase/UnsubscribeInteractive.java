package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class UnsubscribeInteractive extends TestBase{
	
	  
	  @Test
	  public void unsubscribeInteractive() throws Exception {
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		
		String unsubscribe= "<xml>"
									+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
									+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
									+ "<CreateTime>"+arr[0]+"</CreateTime>"
									+ "<MsgType><![CDATA[event]]></MsgType>"
									+ "<Event><![CDATA[unsubscribe]]></Event>"
						  + "</xml>";
		
		String requesturl = baseUrl+"/wechat/index/id/"+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
			
		String[] keyreply=  Post(requesturl,unsubscribe);
			
		    for(int i=0;i<keyreply.length;i++){
		    	if(keyreply[i]!=null){
				    System.out.println("取消关注获取的xml应该是空（冒号后面没有值说明是正确的）："+ keyreply[i]);
		    	}	
		    }
		//单独跑该testcase时用到，testsuite里不需要
	   
	    driver.findElement(By.linkText("Analytics")).click();
	    driver.findElement(By.linkText("Follower Analytics")).click();
	    driver.findElement(By.linkText("Follower Actions Log")).click();
	   
	    Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(d);
		driver.findElement(By.name("UserActionRecord[create_time]")).clear();
		driver.findElement(By.name("UserActionRecord[create_time]")).sendKeys(time);
		
		driver.findElement(By.name("UserActionRecord[openid]")).clear();
	    driver.findElement(By.name("UserActionRecord[openid]")).sendKeys("rainbow");
	    driver.findElement(By.name("UserActionRecord[openid]")).sendKeys(Keys.ENTER);
	    Thread.sleep(8000);
	    
	   
	    try {
	      AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='menu-record-grid']/div/table/tbody/tr/td[1]/a")).getText().contains("rainbow"));
	    } catch (Error e) {
	      
	      System.out.println("取消关注后action log里取消关注记录中nickname显示错误！！");
	      Assert.fail("取消关注后action log里取消关注记录中nickname显示错误！！");
	    }
	    try {
	      AssertJUnit.assertEquals("Unsubscribe", driver.findElement(By.xpath("//div[@id='menu-record-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	     
	      System.out.println("取消关注后action log里取消关注记录中action type显示错误！！");
	      Assert.fail("取消关注后action log里取消关注记录中action type显示错误！！");
	    }
	    try {
	      AssertJUnit.assertEquals("unsubscribe", driver.findElement(By.xpath("//div[@id='menu-record-grid']/div/table/tbody/tr/td[3]")).getText());
	    } catch (Error e) {
	      
	      System.out.println("取消关注后action log里取消关注记录中keyword显示错误！！");
	     Assert.fail("取消关注后action log里取消关注记录中keyword显示错误！！");
	    }
	    try {
	      AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='menu-record-grid']/div/table/tbody/tr/td[4]")).getText().contains(time));
	    } catch (Error e) {
	      
	      System.out.println("取消关注后action log里取消关注记录中time显示错误！！");
	      Assert.fail("取消关注后action log里取消关注记录中time显示错误！！");
	    }
}
	
}
