package com.gy.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class MessageInbox extends TestBase{
	private static Logger logger = Logger.getLogger(MessageInbox.class);
	 @BeforeMethod
	public void setUp() throws Exception {
		
	 }
	  
	  @AfterMethod
	public void tearDown() throws Exception {
		  
	  }
	
	@Test
	public void messageInbox() throws Exception {
		System.out.println("开始发送不同类型message，测试messageinbox");
	//接口部分
	//String[] info= {"appid","secret","mid","WechatID","openid"};
	String[] attr=info();
	String[] arr=getSignature(attr[2],attr[0],attr[1]);
	

		
		String keyword= "<xml>"
						        + "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
								+"<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[text]]></MsgType>"
								+ "<Content><![CDATA[rainbow收件箱消息]]></Content>"
								+"<MsgId>6278109033050373709</MsgId>"
						+ "</xml>";
		
		String image = "<xml>"
								+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+"<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
								+ "<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[image]]></MsgType>"
								+ "<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/nZfLicOOIrOJiaEFjYcichkHibdTq8uZeFxocic2p18VW0rPricIrNsvLURISiavPaUzJiaIL1UfXoK5yVLDhwbTmrMntQ/0]]></PicUrl>"
								+ "<MsgId>6288948680105300799</MsgId>"
								+"<MediaId><![CDATA[GrnnfHKBVdoJsV-0grKmCY-3SkPTXZeab4P1or7BMbptWqL6FJuC_mnVXkIvhF6m]]></MediaId>"
					+ "</xml>";
		
		String location = "<xml>"
									+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
									+"<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
									+"<CreateTime>"+arr[0]+"</CreateTime>"
									+"<MsgType><![CDATA[location]]></MsgType>"
									+ "<Location_X>31.228785</Location_X>"
									+ "<Location_Y>121.440697</Location_Y>"
									+ "<Scale>16</Scale>"
									+ "<Label><![CDATA[静安区曹家渡三和花园(延平路西)]]></Label>"
									+ "<MsgId>6288963798390183037</MsgId>"
					+ "</xml>";
		
		//dev
		String voicedev = "<xml>"
							+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
							+"<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
							+"<CreateTime>"+arr[0]+"</CreateTime>"
							+ "<MsgType><![CDATA[voice]]></MsgType>"
							+ "<MediaId><![CDATA[IAzo_vIMLIJayWNI_s64Ljv5dQxJ58OGcthXl72QTOgoZnpuocHMbId3vk-T7l1u]]></MediaId>"
							+ "<Format><![CDATA[amr]]></Format>"
							+ "<MsgId>6295642175774392320</MsgId>"
							+ "<Recognition><![CDATA[]]></Recognition>"
					+ "</xml>";
		
		//staging
		String voicestaging = "<xml>"
								+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+"<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
								+"<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[voice]]></MsgType>"
								+ "<MediaId><![CDATA[OYMwwat4gcuQ-1ZQmMERTwkTi-U415XTUGYIHNdo02h6nGuXl_wWbstk4JLjQxPp]]></MediaId>"
								+ "<Format><![CDATA[amr]]></Format>"
								+ "<MsgId>6288965722535531732</MsgId>"
								+ "<Recognition><![CDATA[]]></Recognition>"
				+ "</xml>";
		
		//app
		String voiceapp = "<xml>"
										+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
										+"<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
										+"<CreateTime>"+arr[0]+"</CreateTime>"
										+ "<MsgType><![CDATA[voice]]></MsgType>"
										+ "<MediaId><![CDATA[8SkEk40lB8RZNjxQsveWntkn9kPqr609RLAjfiwMjG_bYUfuWH7fk3P6vYDv2lcG]]></MediaId>"
										+ "<Format><![CDATA[amr]]></Format>"
										+ "<MsgId>6291901646221563811</MsgId>"
										+ "<Recognition><![CDATA[]]></Recognition>"
							+ "</xml>";
		
		//dev
		String shortvideodev = "<xml>"
											+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
											+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
											+ "<CreateTime>"+arr[0]+"</CreateTime>"
											+ "<MsgType><![CDATA[shortvideo]]></MsgType>"
											+ "<MediaId><![CDATA[olIYRcjaWjcseHD5chNpwRWVKdbpTSWgMzwhBKQWxzAtQdmHhHdkLDXMPataM5C8]]></MediaId>"
											+ "<ThumbMediaId><![CDATA[swiswKtJ96-4oQup3FtqWZRHUQFrb0EfGWJXyM6npNqgRrNECw2Mlplk6FULJTNJ]]></ThumbMediaId>"
											+ "<MsgId>6291779987049091530</MsgId>"
								+ "</xml>";
		
		//staging
		String shortvideostaging = "<xml>"
									+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
									+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
									+ "<CreateTime>"+arr[0]+"</CreateTime>"
									+ "<MsgType><![CDATA[shortvideo]]></MsgType>"
									+ "<MediaId><![CDATA[1Ega9KhQvDrCukFzZWM1jVtIwBmCrvlCko6BMyzPmVj56I8D4sEKfsTNki6uZhpB]]></MediaId>"
									+ "<ThumbMediaId><![CDATA[SYJm41g1vPi5C2Xaj51CEMFqi30eRWXReLqTmAcPAiPjICARMn0RqzAzt0q4OWzm]]></ThumbMediaId>"
									+ "<MsgId>6288967337443235105</MsgId>"
						+ "</xml>";
		
		//app
		String shortvideoapp = "<xml>"
											+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
											+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
											+ "<CreateTime>"+arr[0]+"</CreateTime>"
											+ "<MsgType><![CDATA[shortvideo]]></MsgType>"
											+ "<MediaId><![CDATA[bdZHuKhJJF7c3dAMpFppn8-Wdap0Ra3Y8NEGjHZ0LZdT5p4olNRjb-c-0Q6hp2p-]]></MediaId>"
											+ "<ThumbMediaId><![CDATA[zg0gCmrI5zzfc4ReHOH6kw1-YOcr-afF5JbRVCk4iYpRgIPtHFM3ql1ZAr00F4JG]]></ThumbMediaId>"
											+ "<MsgId>6288562244724290440</MsgId>"
								+ "</xml>";
		
				String emoji = "<xml>"
								+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
								+ "<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[text]]></MsgType>"
								+ "<Content><![CDATA[/::<]]></Content>"
								+ "<MsgId>6288968535739110762</MsgId>"
				+ "</xml>";
		
		String special = "<xml>"
								+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
								+ "<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[text]]></MsgType>"
								+ "<Content><![CDATA[【收到不支持的消息类型，暂无法显示】]]></Content>"
								+ "<MsgId>6288968471314601318</MsgId>"
				     + "</xml>";
		
		//dev
		String videodev = "<xml>"
										+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
										+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
										+ "<CreateTime>"+arr[0]+"</CreateTime>"
										+ "<MsgType><![CDATA[video]]></MsgType>"
										+ "<MediaId><![CDATA[4XLNZYFya1bnHxILh1sU2wESXrUnXXvG69dIgYvb0l3HJ7VF1KQ89ue4YBUFhrsa]]></MediaId>"
										+ "<ThumbMediaId><![CDATA[FrNBbgHLBzJKTMH1Mnfy8n5yScBlDQQ7iwkoFFzluN4nvVbAx5qdWh8ZhQtbcBCx]]></ThumbMediaId>"
										+ "<MsgId>6291781902604505763</MsgId>"
						   + "</xml>";
		
		//staging
		String videostaging = "<xml>"
								+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
								+ "<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[video]]></MsgType>"
								+ "<MediaId><![CDATA[VS8vojlIhSZRvyQoyBXdatXFyE4LuWZYQ6eYXGzdSuGSie-gALVLwPEki89aCD20]]></MediaId>"
								+ "<ThumbMediaId><![CDATA[NzBngmZlY1KTKG1eXtg-sB8HSGKJ-h2Ek-6uRJ8P8hfcliIFEyoIs-vMrKPG8IhJ]]></ThumbMediaId>"
								+ "<MsgId>6288559702097161666</MsgId>"
				   + "</xml>";
		
		//app
		String videoapp = "<xml>"
										+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
										+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
										+ "<CreateTime>"+arr[0]+"</CreateTime>"
										+ "<MsgType><![CDATA[video]]></MsgType>"
										+ "<MediaId><![CDATA[YPoaelgIQ0fpayn-VI075MceJItScZG8IE_1TCtFRz3S_qWe0bq0GZVTM-J4DKdf]]></MediaId>"
										+ "<ThumbMediaId><![CDATA[gJf4id8LMzcfwXn_EF3b-_IxYM3lf3FNV94y6JZB124gqGeqiu1vrDPGPGqjNYQR]]></ThumbMediaId>"
										+ "<MsgId>6288562283378996111</MsgId>"
						   + "</xml>";
		
		//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
		String requesturl=requestURL();
		if(baseUrl=="https://dev.jingsocial.com/"){
			String[] keyreply=  Post(requesturl,keyword);
			String[] imagepost=  Post(requesturl,image);
			String[] locationpost=  Post(requesturl,location);
			String[] voicepost=  Post(requesturl,voicedev);
			String[] shortvideopost=  Post(requesturl,shortvideodev);
			String[] emojipost=  Post(requesturl,emoji);
			String[] specialpost=  Post(requesturl,special);
			String[] videopost=  Post(requesturl,videodev);
		}else if(baseUrl=="https://staging.jingsocial.com/"){
			String[] keyreply=  Post(requesturl,keyword);
			String[] imagepost=  Post(requesturl,image);
			String[] locationpost=  Post(requesturl,location);
			String[] voicepost=  Post(requesturl,voicestaging);
			String[] shortvideopost=  Post(requesturl,shortvideostaging);
			String[] emojipost=  Post(requesturl,emoji);
			String[] specialpost=  Post(requesturl,special);
			String[] videopost=  Post(requesturl,videostaging);
		}else if(baseUrl=="https://app.jingsocial.com/"){
			String[] keyreply=  Post(requesturl,keyword);
			String[] imagepost=  Post(requesturl,image);
			String[] locationpost=  Post(requesturl,location);
			String[] voicepost=  Post(requesturl,voiceapp);
			String[] shortvideopost=  Post(requesturl,shortvideoapp);
			String[] emojipost=  Post(requesturl,emoji);
			String[] specialpost=  Post(requesturl,special);
			String[] videopost=  Post(requesturl,videoapp);
		}
		
		/*
		 driver.findElement(By.linkText("Messaging")).click();
		    driver.findElement(By.linkText("Message Inbox")).click();
		    driver.findElement(By.id("start_advanced_search")).click();
		    driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys("rainbow");
		    driver.findElement(By.cssSelector("li.hover > a")).click();
		    driver.findElement(By.name("select")).click();
		    driver.findElement(By.cssSelector("div.drp-arrow.drp-arrow-right")).click();
		    driver.findElement(By.cssSelector("div.drp-arrow.drp-arrow-right")).click();
		    driver.findElement(By.cssSelector("div.drp-arrow.drp-arrow-right")).click();
		    driver.findElement(By.xpath("//div[@id='advanced_search_box']/form/div/div[3]/div/ul[2]/li[6]")).click();
		    driver.findElement(By.cssSelector("i.search_gray.bearchButton")).click();
		    try {
		      assertEquals("【收到不支持的消息类型，暂无法显示】", driver.findElement(By.cssSelector("div.wxMsg")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("/::<", driver.findElement(By.cssSelector("#msgListItem65468 > div.message_content.text > div.wxMsg")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("rainbow收件箱消息", driver.findElement(By.cssSelector("#msgListItem65463 > div.message_content.text > div.wxMsg")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.id("keyword_replay_type")).click();
		    new Select(driver.findElement(By.id("keyword_replay_type"))).selectByVisibleText("Image");
		    driver.findElement(By.cssSelector("i.search_gray.bearchButton")).click();
		    try {
		      assertEquals("", driver.findElement(By.cssSelector("img.wxmImg.Zoomin")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("Displaying 1 - 1 of 1 results from 1 followers.", driver.findElement(By.cssSelector("div.summary")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.id("keyword_replay_type")).click();
		    new Select(driver.findElement(By.id("keyword_replay_type"))).selectByVisibleText("Video");
		    driver.findElement(By.cssSelector("i.search_gray.bearchButton")).click();
		    try {
		      assertEquals("NzBngmZlY1KTKG1eXtg-sB8HSGKJ-h2Ek-6uRJ8P8hfcliIFEyoIs-vMrKPG8IhJ", driver.findElement(By.cssSelector("video")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("Displaying 1 - 1 of 1 results from 1 followers.", driver.findElement(By.cssSelector("div.summary")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.id("keyword_replay_type")).click();
		    new Select(driver.findElement(By.id("keyword_replay_type"))).selectByVisibleText("Voice");
		    driver.findElement(By.cssSelector("i.search_gray.bearchButton")).click();
		    try {
		      assertEquals("Displaying 1 - 1 of 1 results from 1 followers.", driver.findElement(By.cssSelector("div.summary")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("", driver.findElement(By.cssSelector("audio")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    new Select(driver.findElement(By.id("keyword_replay_type"))).selectByVisibleText("ShortVideo");
		    driver.findElement(By.cssSelector("i.search_gray.bearchButton")).click();
		    try {
		      assertEquals("SYJm41g1vPi5C2Xaj51CEMFqi30eRWXReLqTmAcPAiPjICARMn0RqzAzt0q4OWzm", driver.findElement(By.cssSelector("video")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("Displaying 1 - 1 of 1 results from 1 followers.", driver.findElement(By.cssSelector("div.summary")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.linkText("Quick reply")).click();
		    // 回复框不支持直接type,表情的添加也是
		    driver.findElement(By.linkText("Facial Expression")).click();
		    driver.findElement(By.cssSelector("i.js_emotion_i")).click();
		    driver.findElement(By.cssSelector("button.btn.js_reply_OK")).click();
		    driver.findElement(By.id("start_advanced_search")).click();
		    driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys("rainbow");
		    driver.findElement(By.name("select")).click();
		    driver.findElement(By.cssSelector("div.drp-arrow.drp-arrow-right")).click();
		    driver.findElement(By.cssSelector("div.drp-arrow.drp-arrow-right")).click();
		    driver.findElement(By.cssSelector("div.drp-arrow.drp-arrow-right")).click();
		    driver.findElement(By.xpath("//div[@id='advanced_search_box']/form/div/div[3]/div/ul[2]/li[6]")).click();
		    driver.findElement(By.id("keyword_replay_type")).click();
		    new Select(driver.findElement(By.id("keyword_replay_type"))).selectByVisibleText("ShortVideo");
		    driver.findElement(By.cssSelector("i.search_gray.bearchButton")).click();
		    try {
		      assertEquals("●Replied", driver.findElement(By.cssSelector("em.tips")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("29", driver.findElement(By.xpath("//div[@id='dada']/div[2]/div/div/div[2]/ul[7]/li[3]/a/span")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.cssSelector("span.set-all-as-read")).click();
		    try {
		      assertEquals("0", driver.findElement(By.xpath("//div[@id='dada']/div[2]/div/div/div[2]/ul[7]/li[3]/a/span")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.linkText("View Message Analytics >")).click();
		    driver.findElement(By.linkText("Last >>")).click();
		    try {
		      assertEquals("2016-06-03", driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div[2]/table/tbody/tr[16]/td")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("204", driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div[2]/table/tbody/tr[16]/td[2]")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.linkText("Message Inbox")).click();
		    driver.findElement(By.cssSelector("input.frm_checkbox")).click();
		    try {
		      assertEquals("Message Inbox", driver.findElement(By.cssSelector("h1.page_title")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("on", driver.findElement(By.cssSelector("input.frm_checkbox")).getAttribute("value"));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }*/
	}

}
