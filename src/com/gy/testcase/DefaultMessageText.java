package com.gy.testcase;

import static org.testng.Assert.assertNull;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class DefaultMessageText extends TestBase{
	private static Logger logger = Logger.getLogger(DefaultMessageText.class);
  @Test
  public void text() throws Exception {
	  
	
	System.out.println("开始创建default message text(half hour)");
    navigation("Default Message");
    String status = driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src");
    System.out.println(status);
    if(status.equals(baseUrl + "images/switch-button_off.png")){
    	System.out.println("初始状态为关，进行更改");
    	driver.findElement(By.cssSelector(".switchStatus")).click();
    	//Thread.sleep(3000);
    }else if(status.equals(baseUrl + "images/switch-button_on.png")){
    	System.out.println("该default message的初始状态为open");
    }
    //default message类型是send message
    driver.findElement(By.xpath("(//input[@id='DefaultMessages_replyType'])[2]")).click();
    //获取当前时间
   
	String time = currentTime();

    WebElement editor = driver.findElement(By.xpath("//div[@id='text']/div"));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("$('div.js_editorArea').text('来自default message:{{nickname}}，name:{{name}}{{time}}好！你住在{{country}}{{province}}{{city}}，你是{{gender}}生，你是{{role}}，你说{{language}}"+ time +"');", editor);
    editor.sendKeys(Keys.ENTER);
    driver.findElement(By.linkText("Facial Expression")).click();
    driver.findElement(By.cssSelector("i.js_emotion_i")).click();
    
    driver.findElement(By.id("expire_type_1")).click();
    new Select(driver.findElement(By.id("DefaultMessages_expire_time"))).selectByVisibleText("Half Hour");
    
    driver.findElement(By.name("yt0")).click();
    Thread.sleep(2000);
    //校验是否保存
    navigation("Default Message");
    Thread.sleep(2000);
    try {
      AssertJUnit.assertEquals("来自default message:{{nickname}}，name:{{name}}{{time}}好！你住在{{country}}{{province}}{{city}}，你是{{gender}}生，你是{{role}}，你说{{language}}"+ time +"", driver.findElement(By.cssSelector("div.js_editorArea")).getText());
    } catch (Error e) {
      
    	logger.error("default message保存失败");
      Assert.fail("default message保存失败");
    }
    try {
        AssertJUnit.assertEquals("", driver.findElement(By.cssSelector("img[alt=\"mo-微笑\"]")).getText());
      } catch (Error e) {
        
    	  logger.error("default message里的表情保存失败");
        Assert.fail("default message里的表情保存失败");
      }
    try {
    	 System.out.println(driver.findElement(By.xpath("//select[@id='DefaultMessages_expire_time']/option[2]")).getAttribute("selected"));
    	AssertJUnit.assertEquals("true", driver.findElement(By.xpath("//select[@id='DefaultMessages_expire_time']/option[2]")).getAttribute("selected"));
      } catch (Error e) {
        
    	  logger.error("default message里的expire type保存失败");
        Assert.fail("default message里的expire type保存失败");
      }
    try {
    	System.out.println(driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
    	AssertJUnit.assertEquals(baseUrl + "images/switch-button_on.png", driver.findElement(By.cssSelector(".switchStatus")).getAttribute("src"));
      } catch (Error e) {
    	  logger.error("default message状态更改失败");
    	  Assert.fail("default message状态更改失败");
        
      }
  }
  
  @Test(dependsOnMethods="text",groups={"defaultTextInteractive"})
	public void defaultTextInteractive() throws Exception {
	System.out.println("开始执行DefaultTextInteractive");
	//接口部分
	//String[] info= {"appid","secret","mid","WechatID","openid"};
	String[] attr=info();
	String[] arr=getSignature(attr[2],attr[0],attr[1]);
	String[] msg={"默认文本消息","默认文本消息"};
	for(int j=0;j<msg.length;j++){
		
		String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
						+ "<FromUserName><![CDATA["+attr[8]+"]]></FromUserName>"
						+"<CreateTime>"+arr[0]+"</CreateTime>"
						+ "<MsgType><![CDATA[text]]></MsgType>"
						+ "<Content><![CDATA["+msg[j]+"]]></Content>"
						+"<MsgId>6278109033050373709</MsgId></xml>";
		
		//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
		String requesturl=requestURL();
		System.out.println(requesturl);
		String[] keyreply=  Post(requesturl,keyword);
		String time=currentTime();
		System.out.println("DefaultMessageText得到的响应"+keyreply[0]);
		
		if(j==0){
			if(keyreply[0]==null){
				logger.warn("DefaultMessageText触发失败，响应为null");
				//Assert.fail("DefaultMessageText触发失败，响应为null");
			}else{
					Document doc = DocumentHelper.parseText(keyreply[0]);
					Element root = doc.getRootElement();
					String message = root.elementText("Content");
						   	
					try{
								
								AssertJUnit.assertTrue(message.contains("来自default message:rainbow"));
								System.out.println("default message触发返回的值实际是： " + message);
				   				AssertJUnit.assertTrue(message.contains("好！你住在瑞典斯德哥尔摩，你是女生，你是，你说中文"+time));
				   				System.out.println("default message触发message 成功 ！！！");
				   				
					}catch(Error e){
						   			
						logger.error("default message触发message失败！！！");
						   			Assert.fail("default message触发message失败！！！");
					}
			}
			   
		}else if(j==1){
					try{
							assertNull(keyreply[0]);
					}catch(Error e){
			   			
						logger.error("default message设置half hour触发一次message失败！！！返回的xml数据不为null");
			   			Assert.fail("default message设置half hour触发一次message失败！！！返回的xml数据不为null");
					}
		}	   
			   	
	}
	}
}
