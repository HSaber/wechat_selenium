package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class KeywordImageInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(KeywordImageInteractive.class);
	@Test(dependsOnGroups="keywordImage",groups="keywordImageInteractive")
	  public void keywordImageInteractive() throws Exception {
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		String[] msg={"seleniumimage"};
		for(int j=0;j<msg.length;j++){
			String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
							+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
							+"<CreateTime>"+arr[0]+"</CreateTime>"
							+ "<MsgType><![CDATA[text]]></MsgType>"
							+ "<Content><![CDATA["+msg[j]+"]]></Content>"
							+"<MsgId>6278109033050373709</MsgId></xml>";
			
			//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
			String requesturl=requestURL();
			System.out.println(requesturl);
			String[] keyreply=  Post(requesturl,keyword);
			for(int i=0;i<keyreply.length;i++){
		    	
		    	if(keyreply[i]!=null){
				    System.out.println(keyreply[i]);
					Document doc = DocumentHelper.parseText(keyreply[i]);
				   	Element root = doc.getRootElement();
				   	Iterator it = root.elementIterator("Image");
				   	
				   	if(it.hasNext()){
				   		
				   		while(it.hasNext()){
				   			Element item = (Element) it.next(); 
				   			String mediaid = item.elementText("MediaId");
					   		System.out.println("keyword image得到的MediaId是"+mediaid);
					   		try{
					   				assertNotNull(mediaid);
					   				System.out.println("keyword image触发image 成功 ！！！");
					   				
					   		}catch(Error e){
					   			
					   			logger.error("keyword image触发keyword image失败！！！");
					   			Assert.fail("keyword image触发keyword image失败！！！");
					   		}
					   	}
				   	
				   	}else{
				   		logger.error("keyword image触发失败,xml没找到image");
				   		Assert.fail("keyword image触发失败,xml没找到image");
				   		}
				   	}
		    	}
		}
		
		//后台验证触发数据	
				
				navigation("Keyword Auto-Reply");
			    Thread.sleep(2000);
			    driver.findElement(By.name("KeyWord[keyWord]")).clear();
			    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys("seleniumimage");
			    new Select(driver.findElement(By.name("KeyWord[replyType]"))).selectByVisibleText("Image");
			   // driver.findElement(By.name("KeyWord[keyWord]")).sendKeys(Keys.ENTER);
			    Thread.sleep(8000);
			    try {
			        int num = Integer.parseInt(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[7]/a")).getText());
			        System.out.println(num);
			    	assertTrue(num>=1);
			      } catch (Error e) {
			        
			    	  logger.error("keyword image接口测试触发过后触发次数显示错误");
			        Assert.fail("keyword image接口测试触发过后触发次数显示错误");
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
					    			System.out.println("keyword Image接口测试rainbow触发显示次数是："+ driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[2]/a")).getText());	
					    			assertEquals("1",driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[2]/a")).getText());
					    			k=num2+1;
					    			System.out.println(k);
					    		}catch(Error e){
					    			
					    			logger.error("keyword Image接口测试rainbow触发过后触发次数显示错误");
					    	       Assert.fail("keyword Image接口测试rainbow触发过后触发次数显示错误");
					    		}
					    	}
			    		}
			    }
	}
}
