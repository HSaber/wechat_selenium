package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;

public class WelcomeMessageDefaultInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomeMessageDefaultInteractive.class);
	/* @Before
	  public void setUp() throws Exception {
		
	 }
	  
	  @After
	  public void tearDown() throws Exception {
		  String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
	  }*/
	  
	  @Test(groups="welcomeMessageDefaultInteractive",dependsOnGroups="welcomeVoiceInteractive",alwaysRun=true)
		public void welcomeMessageDefaultInteractive() throws Exception {
		  
		  
		  navigation("Welcome Message");
		    
		    String status = driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src");
		    if(status.equals(baseUrl+"images/switch-button_on.png")){
		    	driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).click();
		    	Thread.sleep(3000);
		    	 try {
			        // System.out.println(driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getText());
			    	assertEquals(baseUrl+"images/switch-button_off.png", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src"));
			    } catch (Error e) {
			    	logger.error("Welcome message没关掉，请检查！！");
			    	Assert.fail("Welcome message没关掉，请检查！！");
			    }
		    }
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		//取消关注
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		
		String unsubscribe= "<xml>"
									+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
									+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
									+ "<CreateTime>"+arr[0]+"</CreateTime>"
									+ "<MsgType><![CDATA[event]]></MsgType>"
									+ "<Event><![CDATA[unsubscribe]]></Event>"
						  + "</xml>";
		
		String subscribe= "<xml>"
									+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
									+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
									+ "<CreateTime>"+arr[0]+"</CreateTime>"
									+ "<MsgType><![CDATA[event]]></MsgType>"
									+ "<Event><![CDATA[subscribe]]></Event>"
						  + "</xml>";
		
		//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
		String requesturl=requestURL();	
		String[] keyreply=  Post(requesturl,unsubscribe);
		Thread.sleep(3000);
		String[] welcomereply=  Post(requesturl,subscribe);
		System.out.println(welcomereply[0]);
		//Document doc = DocumentHelper.parseText(welcomereply[0]);
		//Element root = doc.getRootElement();
		//String message = root.elementText("Content");
		try{
				assertEquals(null,welcomereply[0]);
				
		}catch(Error e){
			logger.error("welcome默认消息触发message失败！！！");	
			Assert.fail("welcome默认消息触发message失败！！！");	
	   			
}
	   	
	   	
	   		
	   	}
}
