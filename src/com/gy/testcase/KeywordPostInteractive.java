package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class KeywordPostInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(KeywordPostInteractive.class);
	@Test(dependsOnGroups="keywordPost")
	  public void keywordPostInteractive() throws Exception {
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"seleniumpost"};
		for(int j=0;j<msg.length;j++){
			String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
							+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
							+"<CreateTime>"+arr[0]+"</CreateTime>"
							+ "<MsgType><![CDATA[text]]></MsgType>"
							+ "<Content><![CDATA["+msg[j]+"]]></Content>"
							+"<MsgId>6278109033050373709</MsgId></xml>";
			System.out.println(keyword);
			//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
			//String requesturl = attr[7]+attr[2]+"?signature="+arr[2]+"&timestamp="+arr[0]+"&nonce="+arr[1];
			String requesturl=requestURL();
			System.out.println(requesturl);
			String[] keyreply=  Post(requesturl,keyword);
			for(int i=0;i<keyreply.length;i++){
		    	
		    	if(keyreply[i]!=null){
				    System.out.println(keyreply[i]);
					Document doc = DocumentHelper.parseText(keyreply[i]);
				   	Element root = doc.getRootElement();
				   	Iterator it = root.elementIterator("Articles");
					if(it.hasNext()){   	
				   		while(it.hasNext()){
					   		Element item = (Element) it.next(); 
					   		Iterator it1 = item.elementIterator("item");
					   		String[] titlename= new String[4];
					   		int a=0;
						   	if(it1.hasNext()){	
					   			while(it1.hasNext()){
						   			System.out.println("输出title");
						   			Element title=(Element)it1.next();
						   			System.out.println("获取title字节名称："+ title.elementText("Title"));
						   			titlename[a]=title.elementText("Title");
						   			a++;
						   		}
						   	}else{
						   		logger.error("keyword post触发失败，xml没找到title");
						   		Assert.fail("keyword post触发失败,xml没找到title");
						   	}	
					   		System.out.println("得到的4个标题分别是"+titlename[0]+"  "+titlename[1]+"  "+titlename[2]+"  "+titlename[3]);
					   		try{
					   				assertEquals("标题title'1“测试” selenium",titlename[0]);
					   				assertEquals("标题title'2“测试”",titlename[1]);
					   				assertEquals("标题title'3“测试”",titlename[2]);
					   				assertEquals("标题title'4“测试”",titlename[3]);
					   		}catch(Error e){
					   			
					   			logger.error("keyword触发post失败！！！");
					   			Assert.fail("keyword触发post失败！！！");
					   		}
					   	}
					}else{
						logger.error("keyword post触发失败,xml没找到article");
				   		Assert.fail("keyword post触发失败,xml没找到article");
					}	
				   
		    	}
		    }
		}
	//后台验证触发数据	
		
		navigation("Keyword Auto-Reply");
	    Thread.sleep(2000);
	    driver.findElement(By.name("KeyWord[keyWord]")).clear();
	    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys("seleniumpost");
	    new Select(driver.findElement(By.name("KeyWord[replyType]"))).selectByVisibleText("Post");
	   // driver.findElement(By.name("KeyWord[keyWord]")).sendKeys(Keys.ENTER);
	    Thread.sleep(8000);
	   
	    
	    try {
	    	int num=0;
	    		while(true){
	    			boolean b = true;
	    			try{
	    				num = Integer.parseInt(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[7]/a")).getText());
	    			}catch(org.openqa.selenium.StaleElementReferenceException e){
	    				 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
			    		 b=false;
	    			}
	    			
	    			if(b)
	    				break;
	    		}
	        
	    	System.out.println(num);
	    	assertTrue(num>=1);
	      } catch (Error e) {
	       
	    	  logger.error("keyword post接口测试触发过后触发次数显示错误");
	        Assert.fail("keyword post接口测试触发过后触发次数显示错误");
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
			    			System.out.println("keyword post接口测试rainbow触发显示次数是："+ driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[2]/a")).getText());	
			    			assertEquals("1",driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[2]/a")).getText());
			    			k=num2+1;
			    			System.out.println(k);
			    		}catch(Error e){
			    			
			    			logger.error("keyword post接口测试rainbow触发过后触发次数显示错误");
			    	        Assert.fail("keyword post接口测试rainbow触发过后触发次数显示错误");
			    		}
			    	}
	    		}
	    }
}	
}
