package com.gy.testcase;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;

import java.util.Iterator;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class DefaultMessagePost extends TestBase{
	private static Logger logger = Logger.getLogger(DefaultMessagePost.class);
	@Test(dependsOnGroups="defaultTextInteractive",alwaysRun=true)
	public void post() throws Exception {
		
		System.out.println("开始执行DefaultMessagePost");
		navigation("Default Message");
	  //default message类型是send post 
	    String status = driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src");
	    System.out.println(status);
	    if(status.equals(baseUrl + "images/switch-button_off.png")){
	    	System.out.println("初始状态为关，进行更改");
	    	driver.findElement(By.cssSelector(".switchStatus")).click();
	    	//Thread.sleep(3000);
	    }else if(status.equals(baseUrl + "images/switch-button_on.png")){
	    	System.out.println("该default message的初始状态为open");
	    }
	    driver.findElement(By.id("DefaultMessages_replyType")).click();
		new Select(driver.findElement(By.id("DefaultMessages_resId"))).selectByVisibleText("single'post单图文“测试” selenium");
		Thread.sleep(2000);
		try {
	        AssertJUnit.assertEquals("单图文'title“测试” selenium", driver.findElement(By.linkText("单图文'title“测试” selenium")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("default message 选择post后，post preview显示不正常");
	        Assert.fail("default message 选择post后，post preview显示不正常");
	      }
		driver.findElement(By.id("expire_type_0")).click();
	    driver.findElement(By.name("yt0")).click();
		Thread.sleep(2000);
	  //校验是否保存
		navigation("Default Message");
		Thread.sleep(2000);
		try {
		        AssertJUnit.assertEquals("单图文'title“测试” selenium", driver.findElement(By.linkText("单图文'title“测试” selenium")).getText());
		      } catch (Error e) {
		       
		    	  logger.error("default message post类型没有保存成功");
		        Assert.fail("default message post类型没有保存成功");
		      }
		 try {
		    	System.out.println(driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		    	AssertJUnit.assertEquals(baseUrl + "images/switch-button_on.png", driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
		      } catch (Error e) {
		    	  logger.error("default message状态更改失败");
		    	  Assert.fail("default message状态更改失败");
		        
		      }
	    }
	
	@Test(dependsOnMethods="post",groups={"defaultMessagePostInteractive"})
	public void defaultMessagePostInteractive() throws Exception {
	System.out.println("开始执行DefaultMessagePostInteractive");
	//接口部分
	//String[] info= {"appid","secret","mid","WechatID","openid"};
	String[] attr=info();
	String[] arr=getSignature(attr[2],attr[0],attr[1]);
	String[] msg={"默认图文消息"};
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
		System.out.println("Defaultpost得到的响应"+keyreply[0]);
		if(keyreply[0]==null){
			logger.warn("Defaultpost，响应为null，请在手机上手工测试！");
		}else{
	    	
	    		System.out.println(keyreply[0]);
				Document doc = DocumentHelper.parseText(keyreply[0]);
			   	Element root = doc.getRootElement();
			   	Iterator it = root.elementIterator("Articles");
			   		if(it.hasNext()){
					   	while(it.hasNext()){
					   		Element item = (Element) it.next(); 
					   		Iterator it1 = item.elementIterator("item");
					   		String titlename = "";
					   		if(it1.hasNext()){
						   		while(it1.hasNext()){
						   			//System.out.println("输出title");
						   			Element title=(Element)it1.next();
						   			System.out.println("获取title字节名称："+ title.elementText("Title"));
						   			titlename = title.elementText("Title");
						   			
						   		}
					   		}else{
					   			logger.error("default post触发失败，xml没找到title");
						   		Assert.fail("default post触发失败,xml没找到title");
					   		}	
					   		System.out.println("default post得到的post标题分别是"+titlename);
					   		try{
					   				assertEquals("单图文'title“测试” selenium",titlename);
					   				System.out.println("defaultpost触发post 成功 ！！！");
					   				
					   		}catch(Error e){
					   			
					   			logger.error("defaultpost触发post失败！！！");
					   			Assert.fail("defaultpost触发post失败！！！");
					   		}
					   	}
			   		}else{
			   			logger.error("default post触发失败,xml没找到article");
				   		Assert.fail("default post触发失败,xml没找到article");
			   		}   	
			
		}
		
	}

}
}
