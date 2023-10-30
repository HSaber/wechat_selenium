package com.gy.testcase;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

public class SpeedTracking{
	
	public static void main(String args[]) throws Exception{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String baseUrl = "https://app.jingsocial.com/";
		driver.get(baseUrl);
		Cookie cookie = new Cookie("PHPSESSID", "nlnrfau26rel7a0e0p26ldefp7");
		driver.manage().addCookie(cookie);
		
		//Date start=new Date();
		//driver.get(baseUrl + "/Manager/default/index/category/dashboard");
		//Date end=new Date();
		//long loadtime=end.getTime() - start.getTime();
		//System.out.println("darshboard的页面加载时间是：" + loadtime +"ms");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.get(baseUrl + "/Manager/default/index/category/dashboard");
		long endtime= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime=endtime-starttime;
		System.out.println("Darshboard的加载时间是：" + loadtime +"ms");
		Thread.sleep(3000);
		 
		driver.get(baseUrl + "/Manager/ecommerce/index/category/ecommerce_analytics");
		long endtime1= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime1= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime1=endtime1-starttime1;
		System.out.println("ecommerce的加载时间是：" + loadtime1 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/followerAnalyse/network/category/follower_network");
		long endtime2= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime2= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime2=endtime2-starttime2;
		System.out.println("follower network的加载时间是：" + loadtime2 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/wechatCustomer/view/category/followers/id/18520/postData/a%3A4%3A%7Bs%3A9%3A%22date_from%22%3Bs%3A10%3A%222015-10-26%22%3Bs%3A7%3A%22date_to%22%3Bs%3A10%3A%222015-11-25%22%3Bs%3A7%3A%22request%22%3Bs%3A6%3A%22update%22%3Bs%3A9%3A%22info_page%22%3Bi%3A1%3B%7D");
		long endtime3= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime3= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime3=endtime3-starttime3;
		System.out.println("follower profile的加载时间是：" + loadtime3 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/followerAnalyse/all/category/follower_overview");
		long endtime4= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime4= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime4=endtime4-starttime4;
		System.out.println("follower overview的加载时间是：" + loadtime4 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/dataStatistics/messagePostAnalyticsDetail/id/686/part/1/category/post_analytic");
		long endtime5= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime5= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime5=endtime5-starttime5;
		System.out.println("messaged post more analytics2的加载时间是：" + loadtime5 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/dataStatistics/postAnalyticsDetail/id/2762/part/1/category/post_analytic");
		long endtime6= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime6= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime6=endtime6-starttime6;
		System.out.println("published post more analytics2的加载时间是：" + loadtime6 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/dataStatistics/messagePostAnalytics/category/message_post_analytics");
		long endtime7= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime7= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime7=endtime7-starttime7;
		System.out.println("messaged post analytics overview的加载时间是：" + loadtime7 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/wechatCustomer/admin/category/followers");
		long endtime8= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime8= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime8=endtime8-starttime8;
		System.out.println("follower list的加载时间是：" + loadtime8 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/res/admin/category/manage_posts");
		long endtime9= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime9= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime9=endtime9-starttime9;
		System.out.println("manage post的加载时间是：" + loadtime9 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/userActionRecord/admin/category/follower_actions");
		long endtime10= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime10= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime10=endtime10-starttime10;
		System.out.println("follower action的加载时间是：" + loadtime10 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/dataStatistics/postAnalyticsDetail/id/2762/category/post_analytic");
		long endtime11= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime11= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime11=endtime11-starttime11;
		System.out.println("published post more analytics1的加载时间是：" + loadtime11 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/dataStatistics/messagePostAnalyticsDetail/id/686/category/post_analytic");
		long endtime12= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime12= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime12=endtime12-starttime12;
		System.out.println("messaged post more analytics1的加载时间是：" + loadtime12 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/dataStatistics/postAnalytics/category/post_analytics");
		long endtime13= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime13= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime13=endtime13-starttime13;
		System.out.println("published post analytics overview的加载时间是：" + loadtime13 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/message/list/category/message_inbox");
		long endtime14= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime14= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime14=endtime14-starttime14;
		System.out.println("message inbox的加载时间是：" + loadtime14 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/tags/admin/category/tags");
		long endtime15= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime15= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime15=endtime15-starttime15;
		System.out.println("tags的加载时间是：" + loadtime15 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/dataStatistics/followerAttribution/category/follower_growth");
		long endtime16= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime16= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime16=endtime16-starttime16;
		System.out.println("follower attribution的加载时间是：" + loadtime16 +"ms");
		Thread.sleep(3000);
		
		driver.get(baseUrl + "/Manager/groups/message/category/schedule_post");
		long endtime17= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime17= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime17=endtime17-starttime17;
		System.out.println("schedule post的加载时间是：" + loadtime17 +"ms");
		Thread.sleep(3000);
		
		//driver.manage().deleteCookieNamed("PHPSESSID");
		driver.manage().deleteAllCookies();
		//driver.manage().deleteAllCookies();
		
		driver.get("http://app.jingsocial.com/artview/detail?openid=oqKzat61tixVci2ihxkOOj46bKqU&wid=42&ord=1&rid=2950");
		long endtime18= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime18= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime18=endtime18-starttime18;
		System.out.println("front post detail page的加载时间是：" + loadtime18 +"ms");
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		//driver.manage().deleteCookie(cookie);
		//driver.get(baseUrl);
		//driver.manage().deleteAllCookies();
		//driver.get("http://app.jingsocial.com/artview/detail?openid=oqKzat61tixVci2ihxkOOj46bKqU&wid=42&ord=1&rid=2950");
		//long endtime181= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		//long starttime181= (long)js.executeScript("return window.performance.timing.navigationStart");
		//long loadtime181=endtime181-starttime181;
		//System.out.println("front post detail page的加载时间是：" + loadtime181 +"ms");
		//driver.manage().deleteAllCookies();
		driver.get("http://app.jingsocial.com/artview/index/v/1?wxid=oqKzat61tixVci2ihxkOOj46bKqU&wid=42&rid=2950#mp.weixin.qq.com");
		long endtime19= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime19= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime19=endtime19-starttime19;
		System.out.println("front post teaser page的加载时间是：" + loadtime19 +"ms");
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		
		driver.get("http://app.jingsocial.com/artview/history?openid=oqKzat61tixVci2ihxkOOj46bKqU&wid=42&ord=&rid=");
		long endtime20= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime20= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime20=endtime20-starttime20;
		System.out.println("front post history page的加载时间是：" + loadtime20 +"ms");
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		
		driver.get("http://app.jingsocial.com/htmlFivePage/index?openid=oqKzat61tixVci2ihxkOOj46bKqU&wid=1@42&ord=&rid=");
		long endtime21= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime21= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime21=endtime21-starttime21;
		System.out.println("front h5 post page的加载时间是：" + loadtime21 +"ms");
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		
		driver.get("http://app.jingsocial.com/rotate/index?openid=oqKzat61tixVci2ihxkOOj46bKqU&wid=@52@42&ord=0&rid=0");
		long endtime22= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime22= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime22=endtime22-starttime22;
		System.out.println("wheel of fortune page的加载时间是：" + loadtime22 +"ms");
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		
		driver.get("http://app.jingsocial.com/campaign/index?openid=oqKzat61tixVci2ihxkOOj46bKqU&wid=5@42&ord=&rid=");
		long endtime23= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime23= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime23=endtime23-starttime23;
		System.out.println("coupon promotion page的加载时间是：" + loadtime23 +"ms");
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		
		driver.get("http://app.jingsocial.com/formEvent/index?openid=&wid=@42&event_id=72&open_id=oqKzat61tixVci2ihxkOOj46bKqU");
		long endtime24= (long)js.executeScript("return window.performance.timing.loadEventEnd");
		long starttime24= (long)js.executeScript("return window.performance.timing.navigationStart");
		long loadtime24=endtime24-starttime24;
		System.out.println("formevent page的加载时间是：" + loadtime24 +"ms");
		
		driver.quit();
}
}