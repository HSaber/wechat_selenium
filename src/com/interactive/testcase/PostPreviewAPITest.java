package com.interactive.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.json.JSONException;
import com.hu.testcase.methods;

public class PostPreviewAPITest {

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws JSONException, InterruptedException {
		//staging    BsI1T45NyGlbm6vU3mYJpNlsZ-qYgtV2t4lyzFfcbzw
		//dev        DlMkkdwfZfagGKok7fApR4bp0DZXDB1VFpePdJljhtg
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		String access_token = HttpClient.getAccessToken(HttpClient.appidArr[p][q],HttpClient.secretArr[p][q]);
		String previewUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token="+ access_token;
		String postJson="{\"touser\":\""+HttpClient.openidArr[p][q]+"\",\"mpnews\":{\"media_id\":\"DlMkkdwfZfagGKok7fApR4bp0DZXDB1VFpePdJljhtg\"},\"msgtype\":\"mpnews\"}";
		String textJson="{\"touser\":\""+HttpClient.openidArr[p][q]+"\", \"text\":{\"content\":\"Send Preview text ---use interaction\"},\"msgtype\":\"text\"}";
		System.out.println(postJson);
		HttpClient.Post(previewUrl, postJson);
		Thread.sleep(1000);
		HttpClient.Post(previewUrl, textJson);

		//群发接口返回的消息状态
		//https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN
		//String previewUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token="+ access_token;
	}

}
