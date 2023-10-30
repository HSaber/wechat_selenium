package com.gy.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import static org.testng.Assert.*;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DefaultVideoInteractive extends TestBase{
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		 
	  }
	@Test
		public void defaultVideoInteractive() throws Exception {
		System.out.println("开始执行DefaultVideoInteractive");
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"默认图片消息"};
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
				   	Iterator it = root.elementIterator("Video");
				   	if(it.hasNext()){
					   	
				   		while(it.hasNext()){
					   		Element image = (Element) it.next(); 
					   		String mediaid = image.elementText("MediaId");
					   		System.out.println("default video得到的MediaId是"+mediaid);
					   		try{
					   				assertNotNull(mediaid);
					   				System.out.println("defaultvideo触发video 成功 ！！！");
					   				
					   		}catch(Error e){
					   			
					   			System.out.println("defaultvideo触发video失败！！！");
					   			fail("defaultvideo触发video失败！！！");
					   		}
					   	}
				   	
				   	}else{
				   		System.out.println("Default video触发失败,xml没找到video");
				   		Assert.fail("Default video触发失败,xml没找到video");
				   	}
				   	
				}else{
					System.out.println("default video触发失败，未返回响应");
					fail("default video触发失败，未返回响应");
				}
		    }
		}
	
	}
}
