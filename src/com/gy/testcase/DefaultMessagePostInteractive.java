package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import static org.testng.Assert.*;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class DefaultMessagePostInteractive extends TestBase{
		//public static void main(String[] args) throws Exception{
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		 
	  }
	@Test
		public void defaultMessagePostInteractive() throws Exception {
		System.out.println("开始执行DefaultMessagePostInteractive");
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"默认图文消息"};
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
			for(int i=0;i<keyreply.length;i++){
		    	
		    	if(keyreply[i]!=null){
				    System.out.println(keyreply[i]);
					Document doc = DocumentHelper.parseText(keyreply[i]);
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
						   			System.out.println("default post触发失败，xml没找到title");
							   		Assert.fail("default post触发失败,xml没找到title");
						   		}	
						   		System.out.println("default post得到的post标题分别是"+titlename);
						   		try{
						   				assertEquals("单图文'title“测试” selenium",titlename);
						   				System.out.println("defaultpost触发post 成功 ！！！");
						   				
						   		}catch(Error e){
						   			
						   			System.out.println("defaultpost触发post失败！！！");
						   			Assert.fail("defaultpost触发post失败！！！");
						   		}
						   	}
				   		}else{
				   			System.out.println("default post触发失败,xml没找到article");
					   		Assert.fail("default post触发失败,xml没找到article");
				   		}   	
				}else{
					System.out.println("default post触发失败，未返回响应");
					fail("default post触发失败，未返回响应");
				}
		    }
		}
	
	}
}
