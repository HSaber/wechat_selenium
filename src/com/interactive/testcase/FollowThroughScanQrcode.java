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

public class FollowThroughScanQrcode  extends methods{

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}
	@Parameters({"openIdNum","openIdMax"})
	@Test
	public void test(int openIdNum,int openIdMax) throws NoSuchAlgorithmException, InterruptedException, JSONException, DocumentException {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		int i=methods.getAccountOrder(methods.baseUrl)[2];
		int j=openIdNum;
		int m,t=openIdMax;
		String[] Post={"gQHB7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzUweEVqaHJsX0VHQmNDeDNpbURfAAIEKA52VwMEAAAAAA==",//dev 33
		               "gQHD8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3RUaTVDLVRsaUxMeG1wOHJSUlRzAAIE69SzVwMEAAAAAA==",//dev 51
		               "gQGy8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0p6dEFsUlBsaEp2OWdJbWJ5UmRCAAIE_aahVwMEAAAAAA==",//staging 59
		               "gQGx7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3BqZ0phVi1rN211WEVmb1gwaFJEAAIE8i17VwMEAAAAAA==",//staging 68
		               "gQFE8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2FVeGlqNVBreUYyeHNTeTc1V0JzAAIErauJVwMEAAAAAA==",//app 42
		               "gQEn8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2MwVjFKclhsbEgzdGdJalU2R255AAIEIXqsVwMEAAAAAA==",//app 61
	                   "gQF28TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyeFl5Mk5xTHpjVjExMDAwMHcwM08AAgSEqidZAwQAAAAA"};
	String[] SceneId={"411","25","202","47","165","19","4"};
	
	if(methods.baseUrl.contains("dev")){
		if(methods.mid.equals("33"))
		m=0;
		else
		m=1;
	}else if(methods.baseUrl.contains("staging")){
		if(methods.mid.equals("59"))
		m=2;
		else
		m=3;
	}else  if(methods.baseUrl.contains("app")){
		if(methods.mid.equals("42"))
		m=4;
		else
		m=5;
	}else{
		m=6;
	}

	while(j<=t){
		String  testStr1= "<xml>"
				+ "<ToUserName><![CDATA["+HttpClient.WechatIDArr[p][q]+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+HttpClient.openidSArr[i][j]+"]]></FromUserName>"
				+ "<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[unsubscribe]]></Event>"
				+ "</xml>";
		
		
		String  testStr2= "<xml>"
				+ "<ToUserName><![CDATA["+HttpClient.WechatIDArr[p][q]+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+HttpClient.openidSArr[i][j]+"]]></FromUserName>"
				+ "<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+"<Event><![CDATA[subscribe]]></Event>"
				+ "<EventKey><![CDATA[qrscene_"+SceneId[m]+"]]></EventKey>"
				+"<Ticket><![CDATA["+Post[m]+"]]></Ticket>"
				+ "</xml>";

		
		String[] arr=HttpClient.getSignature(methods.mid,HttpClient.appidArr[p][q],HttpClient.secretArr[p][q]);
		String requesturl =HttpClient.RequestUrl(HttpClient.InterurlArr[p][q], methods.mid, arr[2], arr[0], arr[1]);
//		System.out.println(testStr1);	 
		String[] resultArr1=HttpClient.Post(requesturl,testStr1);
		String str=resultArr1[0];
		Thread.sleep(2000);
		System.out.println(str);	 
		String[] resultArr2=HttpClient.Post(requesturl,testStr2);
        str=resultArr2[0];
        System.out.println(str);
        Thread.sleep(8000);
      /*  Document doc = DocumentHelper.parseText(str);
		Element root = doc.getRootElement();
		boolean flag=root.elementText("ArticleCount").contains("4");
		assertTrue(flag);
		System.out.println(HttpClient.openidSArr[i][j]+"扫描二维码关注成功!");
		Thread.sleep(1000);*/
		 j++;	
	}
				
	}

}
