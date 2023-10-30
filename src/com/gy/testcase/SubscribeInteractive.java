package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SubscribeInteractive extends TestBase{
	
	
	  @Test
	  public void subscribeInteractive() throws Exception {
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		
		String subscribe= "<xml>"
								+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
								+ "<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[event]]></MsgType>"
								+ "<Event><![CDATA[subscribe]]></Event>"
					    + "</xml>";
		
		String requesturl = baseUrl+"/wechat/index/id/"+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
			
		String[] keyreply=  Post(requesturl,subscribe);
		/*String time=currentTime();
		System.out.println(time);
		System.out.println(keyreply[0]);
		Document doc = DocumentHelper.parseText(keyreply[0]);
		Element root = doc.getRootElement();
		String message = root.elementText("Content");
		System.out.println(message);   	
		try{
	   				assertTrue(message.contains("Welcome message:rainbow"));
	   				//System.out.println("default message触发返回的值实际是： " + message);
	   				assertTrue(message.contains("你住在瑞典斯德哥尔摩，你是女生，"));
	   				
	   				assertTrue(message.contains("你是，你说中文"+time));
	   				System.out.println("welcome message触发message 成功 ！！！");
	   				
		}catch(Error e){
			   			verificationErrors.append(e.toString());
			   			System.out.println("welcome message触发message失败！！！");
		}*/
			
		
		    
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
		      
		      System.out.println("重新关注后action log里关注记录中nickname显示错误！！");
		      Assert.fail("重新关注后action log里关注记录中nickname显示错误！！");
		    }
		    try {
		      AssertJUnit.assertEquals("Followed", driver.findElement(By.xpath("//div[@id='menu-record-grid']/div/table/tbody/tr/td[2]")).getText());
		    } catch (Error e) {
		      
		      System.out.println("重新关注后action log里关注记录中action type显示错误！！");
		      Assert.fail("重新关注后action log里关注记录中action type显示错误！！");
		    }
		    try {
		      AssertJUnit.assertEquals("subscribe", driver.findElement(By.xpath("//div[@id='menu-record-grid']/div/table/tbody/tr/td[3]")).getText());
		    } catch (Error e) {
		     
		      System.out.println("重新关注后action log里关注记录中keyword显示错误！！");
		     Assert.fail("重新关注后action log里关注记录中keyword显示错误！！");
		    }
		    try {
		      AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='menu-record-grid']/div/table/tbody/tr/td[4]")).getText().contains(time));
		    } catch (Error e) {
		     
		      System.out.println("关注后action log里关注记录中time显示错误！！");
		      Assert.fail("关注后action log里关注记录中time显示错误！！");
		    }
}
	
}

