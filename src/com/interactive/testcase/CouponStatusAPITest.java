package com.interactive.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.hu.testcase.methods;

public class CouponStatusAPITest {
	  private static String baseUrl;
	   WebDriver driver;
	  private static StringBuffer verificationErrors = new StringBuffer();



	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver = new ChromeDriver();
	    baseUrl = "https://mp.weixin.qq.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      Assert.fail(verificationErrorString);
	    }
	}

	@Test
	public void test() throws JSONException, InterruptedException {
		
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		driver.manage().window().maximize();
	    driver.get(baseUrl + "/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN");
	    Thread.sleep(2000);
/*	    driver.findElement(By.id("account")).clear();
	    driver.findElement(By.id("account")).sendKeys("weixin@achang.com");

	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("Admin123");
	    Thread.sleep(1000);*/
	    
	    driver.findElement(By.id("account")).clear();
	    driver.findElement(By.id("account")).sendKeys(HttpClient.AccountArr[p][q]);

	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys(HttpClient.PassArr[p][q]);
	    Thread.sleep(1000);
	    driver.findElement(By.id("loginBt")).click();

	    Thread.sleep(3000);
	    driver.findElement(By.linkText("卡券功能")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("优惠券")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("详情")).click();
	    Thread.sleep(3000);

		
	    String CouponID=driver.findElement(By.xpath(".//*[@id='js_detail_container']/div[1]/div[2]/ul/li[2]/div")).getText();
	    String CouponTitle=driver.findElement(By.xpath(".//*[@id='js_showinfo']/div/div/div[2]/div[1]/ul/li[4]/div")).getText();
	    System.out.println(p+"  "+q +"  "+HttpClient.appidArr[p][q]+"   "+HttpClient.secretArr[p][q]);
		String access_token = HttpClient.getAccessToken(HttpClient.appidArr[p][q],HttpClient.secretArr[p][q]);
		String getUrl="https://api.weixin.qq.com/card/get?access_token="+access_token;
        System.out.println(getUrl);
		String cardJson="{\"card_id\":\""+CouponID+"\"}";
        System.out.println(cardJson);
		String result[] = HttpClient.Post(getUrl, cardJson);
		String resultStr="["+result[0]+"]";
        System.out.println(resultStr);

		JSONArray resultArray=new JSONArray(resultStr);
		JSONObject resultObj=resultArray.optJSONObject(0);
		String errmsg =resultObj.getString("errmsg");
	
		AssertJUnit.assertEquals("ok",errmsg);
		System.out.println(CouponTitle+"coupon 接口数据返回正常!");

		JSONObject card = resultObj.getJSONObject("card");

		String cash = "["+card.getString("cash")+"]";
		JSONArray infoArr = new JSONArray(cash);
		JSONObject baseinfoObj=infoArr.optJSONObject(0);	
		JSONObject base_info = baseinfoObj.getJSONObject("base_info");
		
		String status=base_info.getString("status");
		if(status.equals("CARD_STATUS_VERIFY_OK")){
			System.out.println(CouponTitle+"卡券通过审核");
		}else if(status.equals("CARD_STATUS_NOT_VERIFY")){
			System.out.println(CouponTitle+"卡券待审核");
		}else if(status.equals("CARD_STATUS_VERIFY_FAIL")){
			System.out.println(CouponTitle+"卡券审核失败");
		}else if(status.equals("CARD_STATUS_USER_DELETE")){
			System.out.println(CouponTitle+"卡券被商户删除");
		}else if(status.equals("CARD_STATUS_DISPATCH")){
			System.out.println(CouponTitle+"该卡券已在公众平台投放过的卡券");
		}else
		{
			System.out.println(CouponTitle+"卡券状态未知!");
		}
      
		
		
		
/*		JSONObject cashObj = cashArr.optJSONObject(0);
		System.out.println(cashObj);
		JSONObject cash = resultObj.getJSONObject("cash");
		String base_info = card.getString("base_info");
		JSONArray baseArr = new JSONArray(cash);
		JSONObject base = resultObj.getJSONObject("base_info");

		String status = base.getString("status");
		
		System.out.println(status);*/
		String testStr= "<xml>"
				+ "<ToUserName><![CDATA["+HttpClient.WechatIDArr[p][q]+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+HttpClient.openidArr[p][q]+"]]></FromUserName>"
				+ "<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[card_pass_check]]></Event>"
				+ "<CardId><![CDATA[]]></CardId>"
				+ "</xml>";
	}

}
