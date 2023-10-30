package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class WelcomePostInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomePostInteractive.class);
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		  
	  }
	@Test(dependsOnGroups="welcomePost")
		public void welcomePostInteractive() throws Exception {
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
		if(welcomereply[0]==null){
			logger.info("welcompost拿不到响应，请在手机上手动测试！！");
		}else{
			Document doc = DocumentHelper.parseText(welcomereply[0]);
			Element root = doc.getRootElement();
			Iterator it = root.elementIterator("Articles");
			if(it.hasNext()){
		   		
	   		while(it.hasNext()){
	   			Element item = (Element) it.next(); 
		   		Iterator it1 = item.elementIterator("item");
		   		String[] titlename= new String[4];
		   		int a=0;
		   		if(it1.hasNext()){
		   			while(it1.hasNext()){
					   			System.out.println("输出title");
					   			Element title=(Element)it1.next();
					   			System.out.println("获取title字节名称："+ title.elementText("Title"));
					   			titlename[a]=title.elementText("Title");
					   			a++;
		   			}
		   		}else{
		   			logger.error("welcome post触发失败，xml没找到title");
			   		Assert.fail("welcome post触发失败,xml没找到title");
		   		}
		   		System.out.println("得到的4个标题分别是"+titlename[0]+"  "+titlename[1]+"  "+titlename[2]+"  "+titlename[3]);
		   		try{
		   				assertEquals("标题title'1“测试” selenium",titlename[0]);
		   				assertEquals("标题title'2“测试”",titlename[1]);
		   				assertEquals("标题title'3“测试”",titlename[2]);
		   				assertEquals("标题title'4“测试”",titlename[3]);
		   		}catch(Error e){
		   			
		   			logger.error("welcome post触发post失败！！！");
		   			Assert.fail("welcome post触发post失败！！！");
		   		}
	   		}	
		   		
	   	}else{
	   		logger.error("welcome post触发失败,xml没找到article");
	   		Assert.fail("welcome post触发失败,xml没找到article");
	   		
	   	}
		}
	}
}
