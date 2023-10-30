package com.interactive.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.security.NoSuchAlgorithmException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hu.testcase.methods;

public class HCSpecialMessage {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws NoSuchAlgorithmException, InterruptedException, DocumentException {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		int m=methods.getAccountOrder(methods.baseUrl)[2];
		int n=HttpClient.openIdNum;
		int t=HttpClient.openIdMax;
		String[] arr=HttpClient.getSignature(methods.mid,HttpClient.appidArr[p][q],HttpClient.secretArr[p][q]);
		String msg="HCTestMenu";
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
		String[] resultArr=HttpClient.Post(requesturl,testStr);
		n++;
		}
		
		
}
}