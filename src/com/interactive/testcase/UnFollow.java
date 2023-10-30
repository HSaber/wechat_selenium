package com.interactive.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.security.NoSuchAlgorithmException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;

import com.hu.testcase.methods;

public class UnFollow extends methods{

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}
	@Parameters({"openIdNum","openIdMax"})
	@Test
	public void test(int openIdNum,int openIdMax  ) throws NoSuchAlgorithmException, InterruptedException, JSONException, DocumentException {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		int i=methods.getAccountOrder(methods.baseUrl)[2];
		int j=openIdNum;
		int t=openIdMax;
		System.out.println(openIdNum+" "+openIdMax);
         while(j<=1){
		String  testStr1= "<xml>"
				+ "<ToUserName><![CDATA["+HttpClient.WechatIDArr[p][q]+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+HttpClient.openidSArr[i][j]+"]]></FromUserName>"
				+ "<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[unsubscribe]]></Event>"
				+ "</xml>";
		
		String[] arr=HttpClient.getSignature(methods.mid,HttpClient.appidArr[p][q],HttpClient.secretArr[p][q]);
		String requesturl =HttpClient.RequestUrl(HttpClient.InterurlArr[p][q], methods.mid, arr[2], arr[0], arr[1]);
		String[] resultArr1=HttpClient.Post(requesturl,testStr1);
		String str=resultArr1[0];
		System.out.println(str);
		j++;
		Thread.sleep(1000);
        }

				
	}

}
