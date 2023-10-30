package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class WelcomeVideoInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomeVideoInteractive.class);
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		  
	  }
	  
	  @Test(dependsOnGroups="welcomeVideo",groups="welcomeVideoInteractive")
		public void welcomeVideoInteractive() throws Exception {
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
		Document doc = DocumentHelper.parseText(welcomereply[0]);
		Element root = doc.getRootElement();
	   	Iterator it = root.elementIterator("Video");
	   	if(it.hasNext()){
		   		
	   		while(it.hasNext()){
	   			Element item = (Element) it.next(); 
	   			String mediaid = item.elementText("MediaId");
		   		System.out.println("welcome Video得到的MediaId是"+mediaid);
		   		try{
		   				assertNotNull(mediaid);
		   				System.out.println("welcome Video触发Video 成功 ！！！");
		   				
		   		}catch(Error e){
		   			
		   			logger.error("welcome Video触发Video失败！！！");
		   			Assert.fail("welcome Video触发Video失败！！！");
		   		}
		   	}
	   	
	   	}else{
	   		logger.error("welcome Video触发失败,xml没找到Video");
	   		Assert.fail("welcome Video触发失败,xml没找到Video");
	   	}
	   	
	   		
	   	}
}
