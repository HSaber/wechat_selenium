package com.gy.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class WelcomeMessageInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomeMessageInteractive.class);
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		 
	  }
	@Test(dependsOnGroups="welcomeMessage",groups="welcomeMessageInteractive")
		public void welcomeMessageInteractive() throws Exception {
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
		String time=currentTime();
		System.out.println(time);
		System.out.println(welcomereply[0]);
		Document doc = DocumentHelper.parseText(welcomereply[0]);
		Element root = doc.getRootElement();
		String message = root.elementText("Content");
		System.out.println(message);   	
		try{
	   				AssertJUnit.assertTrue(message.contains("Welcome message:rainbowgy"));
	   				//System.out.println("default message触发返回的值实际是： " + message);
	   				AssertJUnit.assertTrue(message.contains("你住在中国，你是女生，"));
	   				
	   				AssertJUnit.assertTrue(message.contains("你是，你说中文"+time));
	   				System.out.println("welcome message触发message 成功 ！！！");
	   				
		}catch(Error e){
			   			
			logger.error("welcome message触发message失败！！！");
			   			Assert.fail("welcome message触发message失败！！！");
		}
	}
}
