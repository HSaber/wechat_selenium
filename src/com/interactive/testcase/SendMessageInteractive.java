package com.interactive.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.security.NoSuchAlgorithmException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hu.testcase.methods;

public class SendMessageInteractive {

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}
	@Parameters({"openIdNum","openIdMax"})
	@Test
	public void test(int openIdNum,int openIdMax) throws NoSuchAlgorithmException, InterruptedException, DocumentException {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		int m=methods.getAccountOrder(methods.baseUrl)[2];
		int n=openIdNum;
		int t=openIdMax;

		String[] arr=HttpClient.getSignature(methods.mid,HttpClient.appidArr[p][q],HttpClient.secretArr[p][q]);
		String msg="test";
		while(n<=t){
		String testStr= "<xml>" +
				"<ToUserName><![CDATA["+HttpClient.WechatIDArr[p][q]+"]]></ToUserName>" +
				"<FromUserName><![CDATA["+HttpClient.openidSArr[m][n]+"]]></FromUserName>"+
				"<CreateTime>"+arr[0]+"</CreateTime>" +
				"<MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA["+msg+"]]></Content>"+
				"<MsgId>6278109033050373709</MsgId>" +
				"</xml>";
		String requesturl =HttpClient.RequestUrl(HttpClient.InterurlArr[p][q], methods.mid, arr[2], arr[0], arr[1]);
		System.out.println(requesturl);
		String[] resultArr=HttpClient.Post(requesturl,testStr);
        String str=resultArr[0];
//        System.out.println(str);
     	//解析xml 
		Document doc = DocumentHelper.parseText(str);
		Element root = doc.getRootElement();
		boolean flag=root.elementText("Content").contains("test");
		AssertJUnit.assertTrue(flag);
		System.out.println(HttpClient.openidSArr[m][n]+"发送message是test成功!");
		n++;
		}
		
		
}
}