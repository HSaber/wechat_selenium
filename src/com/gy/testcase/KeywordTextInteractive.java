package com.gy.testcase;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

public class KeywordTextInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(KeywordTextInteractive.class);
	@Test(dependsOnGroups="keywordText")
	  public void keywordTextInteractive() throws Exception {
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"seleniumtext","guoyan"};
		for(int j=0;j<msg.length;j++){
			String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
							+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
							+"<CreateTime>"+arr[0]+"</CreateTime>"
							+ "<MsgType><![CDATA[text]]></MsgType>"
							+ "<Content><![CDATA["+msg[j]+"]]></Content>"
							+"<MsgId>6278109033050373709</MsgId></xml>";
			
			//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
			String requesturl=requestURL();
			String[] keyreply=  Post(requesturl,keyword);
			String time=currentTime();
		    for(int i=0;i<keyreply.length;i++){
		    	
		    	if(keyreply[i]!=null){
				    System.out.println(keyreply[i]);
					Document doc = DocumentHelper.parseText(keyreply[i]);
				   	Element root = doc.getRootElement();
				   	String content = root.elementText("Content");
				   	System.out.println(content);
				   	try{
				   			AssertJUnit.assertTrue(content.contains("/微笑由Keyword触发：rainbow，name:"));
				   			AssertJUnit.assertTrue(content.contains("好！你住在瑞典斯德哥尔摩，你是女生，你是，你说中文，<a href=\"https://www.baidu.com\">百度链接</a>"+time));
				   			//assertEquals("/微笑rainbow，name:晚上好！你住在瑞典斯德哥尔摩，你是女生，你是，你说中文2016/04/25_16:02:03",content);
				   			System.out.println("接口测试：keyword text 回复成功");
				   	}catch(Error e){
				   		
				   		logger.error("keywordreply触发回复错误");
				   		Assert.fail("keywordreply触发回复错误");
				   	}
		    	}
		    }
		}
	//后台验证触发数据	
		
		navigation("Keyword Auto-Reply");
	    Thread.sleep(2000);
	    driver.navigate().refresh();
	    Thread.sleep(2000);
	    driver.findElement(By.name("KeyWord[keyWord]")).clear();
	    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys("seleniumtext,guoyan");
	    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys(Keys.ENTER);
	    Thread.sleep(8000);
	    try {
	        int num = Integer.parseInt(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[7]/a")).getText());
	        System.out.println(num);
	    	AssertJUnit.assertTrue(num>=2);
	      } catch (Error e) {
	        
	    	  logger.error("keyword text接口测试触发过后触发次数显示错误");
	        Assert.fail("keyword text接口测试触发过后触发次数显示错误");
	      }
	    driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[7]/a")).click();
	    Thread.sleep(3000);
	    String s=driver.findElement(By.cssSelector("div.summary")).getText();
	    int num2 = catchNum("of "," results.",s);
	    for(int k=1;k<num2+1;k++){
	    	if(isElementPresent(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[1]/a"))){
			    	String nickname = driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[1]/a")).getText();
			    	System.out.println(nickname);
			    	if(nickname.contains("rainbow")){
			    		
			    		try{
			    			System.out.println("keyword text接口测试rainbow触发显示次数是："+ driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[2]/a")).getText());	
			    			AssertJUnit.assertEquals("2",driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[2]/a")).getText());
			    			k=num2+1;
			    			System.out.println(k);
			    		}catch(Error e){
			    			
			    			logger.error("keyword text接口测试rainbow触发过后触发次数显示错误");
			    	        Assert.fail("keyword text接口测试rainbow触发过后触发次数显示错误");
			    		}
			    	}
	    		}
	    }
  }	
}
