package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import static org.testng.Assert.*;

import org.junit.*;
import org.openqa.selenium.*;

public class DefaultImageOffInteractive extends TestBase{
	 @Test
	  public void defaultImageOffInteractive() throws Exception {
		  
		  	System.out.println("开始执行DefaultImageOffInteractive");
		    driver.findElement(By.linkText("Messaging")).click();
		    driver.findElement(By.linkText("Auto Responders")).click();
		    driver.findElement(By.linkText("Default Message")).click();
		    String status = driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src");
		    System.out.println(status);
		    if(status.equals(baseUrl + "images/switch-button_on.png")){
		    	System.out.println("初始状态为开，进行更改");
		    	driver.findElement(By.cssSelector(".switchStatus")).click();
		    	//Thread.sleep(2000);
		    }else if(status.equals(baseUrl + "images/switch-button_off.png")){
		    	System.out.println("该default message的初始状态为off");
		    }
		    driver.findElement(By.name("yt0")).click();
		    Thread.sleep(2000);
		    //校验是否保存
		    driver.findElement(By.linkText("Messaging")).click();
		    driver.findElement(By.linkText("Auto Responders")).click();
		    driver.findElement(By.linkText("Default Message")).click();
		    Thread.sleep(2000);
		    try {
		    	System.out.println(driver.findElement(By.cssSelector("#pic_upload > img")).getAttribute("src"));
		    	assertNotEquals(null, driver.findElement(By.cssSelector("#pic_upload > img")).getAttribute("src"));
		      } catch (Error e) {
		        
		        System.out.println("default message图片上传失败");
		        Assert.fail("default message图片上传失败");
		      }
		    try {
		    	System.out.println(driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		    	AssertJUnit.assertEquals(baseUrl + "images/switch-button_off.png", driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		      } catch (Error e) {
		    	  System.out.println("default message状态更改失败");
		    	  Assert.fail("default message状态更改失败");
		        
		      }
		    
		    
		  //接口部分
			//String[] info= {"appid","secret","mid","WechatID","openid"};
			String[] attr=info();
			String[] arr=getSignature(attr[2],attr[0],attr[1]);
			String[] msg={"默认文本消息"};
			for(int j=0;j<msg.length;j++){
				
				String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
								+"<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[text]]></MsgType>"
								+ "<Content><![CDATA["+msg[j]+"]]></Content>"
								+"<MsgId>6278109033050373709</MsgId></xml>";
				
				//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
				String requesturl=requestURL();
				String[] keyreply=  Post(requesturl,keyword);
				String time=currentTime();
				System.out.println(keyreply[0]);
			
				/*Document doc = DocumentHelper.parseText(keyreply[0]);
				Element root = doc.getRootElement();
				String message = root.elementText("Content");*/
								   	
				try{
						assertNull(keyreply[0]);	
						   				
				}catch(Error e){
						
						System.out.println("失败，defaultspace不能返回数据！！！");
						Assert.fail("失败，defaultspace不能返回数据！！！");
				}
			}
	 }
}
