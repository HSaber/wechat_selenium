package com.gy.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.testng.Assert.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DefaultTextInteractive extends TestBase{
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		 
	  }
	@Test
		public void defaultTextInteractive() throws Exception {
		System.out.println("开始执行DefaultTextInteractive");
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"默认文本消息","默认文本消息"};
		for(int j=0;j<msg.length;j++){
			
			String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
							+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
							+"<CreateTime>"+arr[0]+"</CreateTime>"
							+ "<MsgType><![CDATA[text]]></MsgType>"
							+ "<Content><![CDATA["+msg[j]+"]]></Content>"
							+"<MsgId>6278109033050373709</MsgId></xml>";
			
			//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
			String requesturl=requestURL();
			System.out.println(requesturl);
			String[] keyreply=  Post(requesturl,keyword);
			String time=currentTime();
			System.out.println(keyreply[0]);
			if(j==0){
						Document doc = DocumentHelper.parseText(keyreply[0]);
						Element root = doc.getRootElement();
						String message = root.elementText("Content");
							   	
						try{
									
									AssertJUnit.assertTrue(message.contains("来自default message:rainbow"));
									System.out.println("default message触发返回的值实际是： " + message);
					   				AssertJUnit.assertTrue(message.contains("好！你住在瑞典斯德哥尔摩，你是女生，你是，你说中文"+time));
					   				System.out.println("default message触发message 成功 ！！！");
					   				
						}catch(Error e){
							   			
							   			System.out.println("default message触发message失败！！！");
							   			Assert.fail("default message触发message失败！！！");
						}
				   
			}else if(j==1){
						try{
								assertNull(keyreply[0]);
						}catch(Error e){
				   			
				   			System.out.println("default message设置half hour触发一次message失败！！！返回的xml数据不为null");
				   			Assert.fail("default message设置half hour触发一次message失败！！！返回的xml数据不为null");
						}
			}	   
				   	
		}
		}
	
	}

