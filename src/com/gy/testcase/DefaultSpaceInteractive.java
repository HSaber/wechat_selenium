package com.gy.testcase;

import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DefaultSpaceInteractive extends TestBase{
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		 
	  }
	@Test
		public void defaultSpaceInteractive() throws Exception {
		System.out.println("开始执行DefaultSpaceInteractive");
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"默认文本消息"};
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
			String time=currentTime();
			System.out.println(keyreply[0]);
		
			/*Document doc = DocumentHelper.parseText(keyreply[0]);
			Element root = doc.getRootElement();
			String message = root.elementText("Content");*/
							   	
			try{
					assertNull(keyreply[0]);	
					   				
			}catch(Error e){
					
					System.out.println("失败，defaultspace不能返回数据！！！");
					Assert.fail("失败，defaultspace不能返回数据！！！");
			}
				   
			
				   	
		}
	}
}

//default message是空格时返回的xml数据，应该不返回数据
/*<xml>
<ToUserName><![CDATA[oqKzat61tixVci2ihxkOOj46bKqU]]></ToUserName>
<FromUserName><![CDATA[jingsocial_test]]></FromUserName>
<CreateTime>1464252483</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[]]></Content>
<FuncFlag>0</FuncFlag>
</xml>*/