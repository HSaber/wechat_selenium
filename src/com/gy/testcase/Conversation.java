package com.gy.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Conversation extends TestBase{
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		 
	  }
	@Test
		public void conversation() throws Exception {
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"0530","3"};
		
			
			String keyword1= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
							+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
							+"<CreateTime>"+arr[0]+"</CreateTime>"
							+ "<MsgType><![CDATA[text]]></MsgType>"
							+ "<Content><![CDATA["+msg[0]+"]]></Content>"
							+"<MsgId>6278109033050373709</MsgId></xml>";
			
			
			String keyword2= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
					+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
					+"<CreateTime>"+arr[0]+"</CreateTime>"
					+ "<MsgType><![CDATA[text]]></MsgType>"
					+ "<Content><![CDATA["+msg[1]+"]]></Content>"
					+"<MsgId>6278109033050373709</MsgId></xml>";
			
			String requesturl = baseUrl+"/wechat/index/id/"+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
			System.out.println(requesturl);
			String[] keyreply1=  Post(requesturl,keyword1);
			Thread.sleep(3000);
			String[] keyreply2=  Post(requesturl,keyword2);
			String time=currentTime();
			System.out.println(keyreply2[0]);
		
	}
}