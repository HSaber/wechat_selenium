package com.gy.testcase;

import java.util.Iterator;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.*;
import org.openqa.selenium.*;

public class DefaultVoice extends TestBase{
	private static Logger logger = Logger.getLogger(DefaultVoice.class);
	 @Test(dependsOnGroups="defaultImageOffInteractive",alwaysRun=true)
	  public void voice() throws Exception {
		  	
		  	System.out.println("开始执行DefaultVoice");
		  	navigation("Default Message");
		    String status = driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src");
		    System.out.println(status);
		    if(status.equals(baseUrl + "images/switch-button_off.png")){
		    	System.out.println("初始状态为关，进行更改");
		    	driver.findElement(By.cssSelector(".switchStatus")).click();
		    	//Thread.sleep(3000);
		    }else if(status.equals(baseUrl + "images/switch-button_on.png")){
		    	System.out.println("该default message的初始状态为open");
		    }
		    driver.findElement(By.xpath("(//input[@id='DefaultMessages_replyType'])[5]")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("#voice_upload>input")).clear();
		   // linux上得到的路径是  /home/gitlab-runner/builds/2254ccf8/0/root/wechat_selenium/wechat/bin/com/material/0.jpg
		   //windows上得到的路径是  H:\guoyan\eclipse\workspace\wechat_selenium\wechat\bin/com/material/0.jpg
		   driver.findElement(By.cssSelector("#voice_upload>input")).sendKeys(file+"/com/material/welcome.mp3");
		    //Thread.sleep(2000);
		   String src = null;
			src= driver.findElement(By.xpath("//div[@id='voice_upload']/audio")).getAttribute("src");
			System.out.println("src的值是："+ src);
			while(src==null||src.equals("")){
				 src= driver.findElement(By.xpath("//div[@id='voice_upload']/audio")).getAttribute("src");
				 System.out.println("voice未上传完成,继续等待中！");
			}
		    driver.findElement(By.id("expire_type_0")).click();
		    driver.findElement(By.name("yt0")).click();
		    Thread.sleep(2000);
		  //校验是否保存
		    navigation("Default Message");
		    Thread.sleep(2000);
		    try {
		    	System.out.println(driver.findElement(By.cssSelector("#voice_upload > audio")).getAttribute("src"));
		    	assertNotEquals(null, driver.findElement(By.cssSelector("#voice_upload > audio")).getAttribute("src"));
		      } catch (Error e) {
		        
		    	  logger.error("default message语音上传失败");
		        fail("default message语音上传失败");
		      }
		    try {
		    	System.out.println(driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		    	AssertJUnit.assertEquals(baseUrl + "images/switch-button_on.png", driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		      } catch (Error e) {
		    	  logger.error("default message状态更改失败");
		    	  fail("default message状态更改失败");
		        
		      }
		    }
	 
	 @Test(dependsOnMethods="voice",groups={"defaultVoiceInteractive"})
		public void defaultVoiceInteractive() throws Exception {
		System.out.println("开始执行DefaultVoiceInteractive");
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"默认voice消息"};
		for(int j=0;j<msg.length;j++){
			String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
							+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
							+"<CreateTime>"+arr[0]+"</CreateTime>"
							+ "<MsgType><![CDATA[text]]></MsgType>"
							+ "<Content><![CDATA["+msg[j]+"]]></Content>"
							+"<MsgId>6278109033050373709</MsgId></xml>";
			
			//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
			String requesturl=requestURL();
			String[] keyreply=  Post(requesturl,keyword);
			System.out.println("Defaultvoice得到的响应"+keyreply[0]);
			if(keyreply[0]==null){
				logger.warn("Defaultvoice，响应为null,请进行手工测试进一步确认！！");
				
			}else{
		    	
		    	
				    System.out.println(keyreply[0]);
					Document doc = DocumentHelper.parseText(keyreply[0]);
				   	Element root = doc.getRootElement();
				   	Iterator it = root.elementIterator("Voice");
				   	if(it.hasNext()){
					   	
				   		while(it.hasNext()){
					   		Element image = (Element) it.next(); 
					   		String mediaid = image.elementText("MediaId");
					   		System.out.println("default voice得到的MediaId是"+mediaid);
					   		try{
					   				assertNotNull(mediaid);
					   				System.out.println("defaultvoice触发voice 成功 ！！！");
					   				
					   		}catch(Error e){
					   			
					   			logger.error("defaultvoice触发voice失败！！！");
					   			fail("defaultvoice触发voice失败！！！");
					   		}
					   	}
				   	
				   	}else{
				   		logger.error("Default voice触发失败,xml没找到voice");
				   		Assert.fail("Default voice触发失败,xml没找到voice");
				   	}
				   	
			}	
		    
		}
	
	}
}
