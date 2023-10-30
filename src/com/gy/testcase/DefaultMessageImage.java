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

public class DefaultMessageImage extends TestBase{
	private static Logger logger = Logger.getLogger(DefaultMessageImage.class);
	  @Test(dependsOnGroups="defaultMessagePostInteractive",alwaysRun=true)
	  public void image() throws Exception {
		  
		  	System.out.println("开始执行DefaultMessageImage");
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
		    driver.findElement(By.xpath("(//input[@id='DefaultMessages_replyType'])[3]")).click();
		    driver.findElement(By.name("files[]")).clear();
		   // linux上得到的路径是  /home/gitlab-runner/builds/2254ccf8/0/root/wechat_selenium/wechat/bin/com/material/0.jpg
		   //windows上得到的路径是  H:\guoyan\eclipse\workspace\wechat_selenium\wechat\bin/com/material/0.jpg
		   driver.findElement(By.name("files[]")).sendKeys(file+"/com/material/0.jpg");
		   // Thread.sleep(2000);
		   String src = null;
			src= driver.findElement(By.xpath("//div[@id='pic_upload']/img")).getAttribute("src");
			System.out.println("src的值是："+ src);
			while(src==null||src.equals("")){
				 src= driver.findElement(By.xpath("//div[@id='pic_upload']/img")).getAttribute("src");
				 System.out.println("image未上传完成,继续等待中！");
			}
		    driver.findElement(By.id("expire_type_0")).click();
		    driver.findElement(By.name("yt0")).click();
		    Thread.sleep(2000);
		  //校验是否保存
		    navigation("Default Message");
		    Thread.sleep(2000);
		    try {
		    	System.out.println(driver.findElement(By.cssSelector("#pic_upload > img")).getAttribute("src"));
		    	assertNotEquals(null, driver.findElement(By.cssSelector("#pic_upload > img")).getAttribute("src"));
		      } catch (Error e) {
		        
		    	  logger.error("default message图片上传失败");
		        Assert.fail("default message图片上传失败");
		      }
		    try {
		    	System.out.println(driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		    	AssertJUnit.assertEquals(baseUrl + "images/switch-button_on.png", driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		      } catch (Error e) {
		    	  logger.error("default message状态更改失败");
		    	 Assert.fail("default message状态更改失败");
		        
		      }
		    }
	  
	  @Test(dependsOnMethods="image")
		public void defaultImageInteractive() throws Exception {
		System.out.println("开始执行DefaultImageInteractive");
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"默认图片消息"};
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
			System.out.println("Defaultimage得到的响应"+keyreply[0]);
			if(keyreply[0]==null){
				logger.warn("Defaultimage，响应为null，请手工测试！！");
				
			}else{
		    	
		    	
				    System.out.println(keyreply[0]);
					Document doc = DocumentHelper.parseText(keyreply[0]);
				   	Element root = doc.getRootElement();
				   	Iterator it = root.elementIterator("Image");
				   	if(it.hasNext()){
					   	
				   		while(it.hasNext()){
					   		Element image = (Element) it.next(); 
					   		String mediaid = image.elementText("MediaId");
					   		System.out.println("default image得到的MediaId是"+mediaid);
					   		try{
					   				assertNotNull(mediaid);
					   				System.out.println("defaultimage触发image 成功 ！！！");
					   				
					   		}catch(Error e){
					   			
					   			logger.error("defaultimage触发image失败！！！");
					   			Assert.fail("defaultimage触发image失败！！！");
					   		}
					   	}
				   	
				   	}else{
				   		logger.error("Default image触发失败,xml没找到Image");
				   		Assert.fail("Default image触发失败,xml没找到Image");
				   	}
				   	
			}	
		    
		}
	
	}
	  
	  @Test(dependsOnMethods="defaultImageInteractive",groups={"defaultImageOffInteractive"},alwaysRun=true)
	  public void defaultImageOffInteractive() throws Exception {

		  	System.out.println("开始执行DefaultImageOffInteractive");
		  	navigation("Default Message");
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
		    navigation("Default Message");
		    Thread.sleep(2000);
		    try {
		    	System.out.println(driver.findElement(By.cssSelector("#pic_upload > img")).getAttribute("src"));
		    	assertNotEquals(null, driver.findElement(By.cssSelector("#pic_upload > img")).getAttribute("src"));
		      } catch (Error e) {
		       
		    	  logger.error("default message图片上传失败");
		        Assert.fail("default message图片上传失败");
		      }
		    try {
		    	System.out.println(driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		    	AssertJUnit.assertEquals(baseUrl + "images/switch-button_off.png", driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		      } catch (Error e) {
		    	  logger.error("default message状态更改失败");
		    	  Assert.fail("default message状态更改失败");
		        
		      }
		    
		    
		  //接口部分
			//String[] info= {"appid","secret","mid","WechatID","openid"};
			String[] attr=info();
			String[] arr=getSignature(attr[2],attr[0],attr[1]);
			String[] msg={"默认图片消息"};
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
				String time=currentTime();
				System.out.println(keyreply[0]);
			
				/*Document doc = DocumentHelper.parseText(keyreply[0]);
				Element root = doc.getRootElement();
				String message = root.elementText("Content");*/
								   	
				try{
						assertNull(keyreply[0]);	
						   				
				}catch(Error e){
						
					logger.error("失败，defaultspace不能返回数据！！！");
						Assert.fail("失败，defaultspace不能返回数据！！！");
				}
			}
	 }  
 }
	