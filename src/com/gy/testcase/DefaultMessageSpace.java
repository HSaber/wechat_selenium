package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class DefaultMessageSpace extends TestBase{
	private static Logger logger = Logger.getLogger(DefaultMessageSpace.class);
	 @Test(dependsOnGroups="defaultVideoInteractive",alwaysRun=true)
	  public void defaultMessageSpace() throws Exception {
		
		System.out.println("开始执行DefaultMessageSpace");
		navigation("Default Message");
	    String status = driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src");
	    System.out.println(status);
	    if(status.equals(baseUrl + "images/switch-button_on.png")){
	    	System.out.println("初始状态为开，进行更改");
	    	driver.findElement(By.cssSelector(".switchStatus")).click();
	    	//Thread.sleep(3000);
	    }else if(status.equals(baseUrl + "images/switch-button_off.png")){
	    	System.out.println("该default message的初始状态为off");
	    }
	    //default message类型是send message
	    driver.findElement(By.xpath("(//input[@id='DefaultMessages_replyType'])[2]")).click();
	   
	    WebElement editor = driver.findElement(By.xpath("//div[@id='text']/div"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("$('div.js_editorArea').text('   ');", editor);
	   
	    driver.findElement(By.id("expire_type_0")).click();
	    
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    //校验是否保存
	    navigation("Default Message");
	    Thread.sleep(2000);
	    try {
	      assertEquals("   ", driver.findElement(By.cssSelector("div.js_editorArea")).getText());
	    } catch (Error e) {
	      
	    	logger.error("default message保存失败");
	      Assert.fail("default message保存失败");
	    }
	    try {
	    	System.out.println(driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
	    	assertEquals(baseUrl + "images/switch-button_off.png", driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
	      } catch (Error e) {
	    	  logger.error("default message状态更改失败");
	    	  Assert.fail("default message状态更改失败");
	        
	      }
	   }
	 
	 @Test(dependsOnMethods="defaultMessageSpace")
		public void defaultSpaceInteractive() throws Exception {
		System.out.println("开始执行DefaultSpaceInteractive");
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"默认文本消息"};
		for(int j=0;j<msg.length;j++){
			
			String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
							+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
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
					
				logger.error("失败，defaultspace不能返回数据！！！");
					Assert.fail("失败，defaultspace不能返回数据！！！");
			}
				   
			
				   	
		}
	}
}
