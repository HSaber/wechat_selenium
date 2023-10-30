package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class ImageTriggerMIPInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(ImageTriggerMIPInteractive.class);
	@Test(groups="imageTriggerMIPInteractive",dependsOnGroups="imageTriggerMIP")
	  public void imageTriggerMIPInteractive() throws Exception {
		
		navigation("Triggers");
	    Thread.sleep(2000);
	    String category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category1.equals("no conditional image trigger")){
	    	System.out.println("第一行就是no conditional image trigger，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("no conditional image trigger");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category1.equals("no conditional image trigger")&&b)
		    		break;
		    }
	    }
	    
	    int a = Integer.parseInt(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).getText());
        System.out.println("接口post操作前，ImageTriggerMIPInteractive当前总触发次数是"+a+"次");
        int c =0;
        if(a!=0){
	        driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).click();
		    Thread.sleep(3000);
		    String s1=driver.findElement(By.cssSelector("div.summary")).getText();
		    int num21 = catchNum("of "," results.",s1);
		    
		    for(int k=1;k<num21+1;k++){
		    	if(isElementPresent(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[1]/a"))){
				    	String nickname = driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[1]/a")).getText();
				    	System.out.println(nickname);
				    	if(nickname.equals("rainbow")){
				    		c++;
				    		
				    	}
		    		}
		    }
        }
        System.out.println("接口post前，rainbow触发ImageTriggerMIPInteractive的次数是:"+c);
		//接口部分
		//String[] info= {"appid","secret","mid","WechatID","openid"};
		String[] attr=info();
		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		
		String image = "<xml>"
				+"<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
				+"<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
				+ "<CreateTime>"+arr[0]+"</CreateTime>"
				+ "<MsgType><![CDATA[image]]></MsgType>"
				+ "<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/nZfLicOOIrOJiaEFjYcichkHibdTq8uZeFxocic2p18VW0rPricIrNsvLURISiavPaUzJiaIL1UfXoK5yVLDhwbTmrMntQ/0]]></PicUrl>"
				+ "<MsgId>6288948680105300799</MsgId>"
				+"<MediaId><![CDATA[GrnnfHKBVdoJsV-0grKmCY-3SkPTXZeab4P1or7BMbptWqL6FJuC_mnVXkIvhF6m]]></MediaId>"
				+ "</xml>";
		
		String requesturl=requestURL();
		String[] keyreply=  Post(requesturl,image);
		Thread.sleep(3000);
		String[] keyreply2=  Post(requesturl,image);
		Thread.sleep(3000);
		for(int i=0;i<keyreply.length;i++){
		    	
		    	if(keyreply[i]!=null){
				    System.out.println(keyreply[i]);
				    
				    //暂时拿不到返回的xml数据
					
				    /*Document doc = DocumentHelper.parseText(keyreply[i]);
				   	Element root = doc.getRootElement();
				   	Iterator it = root.elementIterator("Articles");
				   	while(it.hasNext()){
				   		Element item = (Element) it.next(); 
				   		Iterator it1 = item.elementIterator("item");
				   		String[] titlename= new String[4];
				   		int a=0;
				   		while(it1.hasNext()){
				   			System.out.println("输出title");
				   			Element title=(Element)it1.next();
				   			System.out.println("获取title字节名称："+ title.elementText("Title"));
				   			titlename[a]=title.elementText("Title");
				   			a++;
				   		}
				   		System.out.println("得到的4个标题分别是"+titlename[0]+"  "+titlename[1]+"  "+titlename[2]+"  "+titlename[3]);
				   		try{
				   				assertEquals("标题title'1“测试” selenium",titlename[0]);
				   				assertEquals("标题title'2“测试”",titlename[1]);
				   				assertEquals("标题title'3“测试”",titlename[2]);
				   				assertEquals("标题title'4“测试”",titlename[3]);
				   		}catch(Error e){
				   			verificationErrors.append(e.toString());
				   			System.out.println("keyword触发post失败！！！");
				   		}*/
				   	}
				   	
				   
		    	
		    }
		
		Thread.sleep(3000);
		navigation("Triggers");
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category.equals("no conditional image trigger")){
	    	System.out.println("第一行就是no conditional image trigger，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("no conditional image trigger");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category.equals("no conditional image trigger")&&b)
		    		break;
		    }
	    }
	    
	    int num=0;
	    try {
	      num = Integer.parseInt(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).getText());
	        System.out.println("接口post操作后，ImageTriggerMIPInteractive当前总触发次数是"+num+"次");
	    	AssertJUnit.assertTrue(num>=a+2);
	      } catch (Error e) {
	        //verificationErrors.append(e.toString());
	    	  if(num==a+1){
	    		  logger.error("ImageTriggerMIPInteractive只触发了一次，少触发了一次，请检查！！！");
	    		  Assert.fail("ImageTriggerMIPInteractive只触发了一次，少触发了一次，请检查！！！");
	        }else{
	        	 logger.error("ImageTriggerMIPInteractive触发失败，请检查！！！");
	    		  Assert.fail("ImageTriggerMIPInteractive触发失败，请检查！！！");
	        }
	      }
	    
	    driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).click();
	    Thread.sleep(3000);
	    String s=driver.findElement(By.cssSelector("div.summary")).getText();
	    int num2 = catchNum("of "," results.",s);
	    int r =0;
	    for(int k=1;k<num2+1;k++){
	    	if(isElementPresent(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[1]/a"))){
			    	String nickname = driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+k+"]/td[1]/a")).getText();
			    	System.out.println(nickname);
			    	if(nickname.equals("rainbow")){
			    		r++;
			    		
			    	}
	    		}
	    }
	    System.out.println("rainbow触发image trigger的次数是:"+r);
	    try {
	      AssertJUnit.assertEquals(c+2,r);
	    } catch (Error e) {
	    	if(r==c+1){
	    		logger.error("rainbow触发ImageTriggerMIPInteractive只成功了一次，还少一次，请检查！！");
	    		Assert.fail("rainbow触发ImageTriggerMIPInteractive只成功了一次，还少一次，请检查！！");
	    	}else if(r<c+1){
	    		logger.error("rainbow触发ImageTriggerMIPInteractive失败，请检查！！");
	    		Assert.fail("rainbow触发ImageTriggerMIPInteractive失败，请检查！！");
	    	}else{
	    		logger.error("rainbow触发ImageTriggerMIPInteractive超过2次，请检查！！");
	    		Assert.fail("rainbow触发ImageTriggerMIPInteractive超过2次，请检查！！");
	    	}
	    	//verificationErrors.append(e.toString());
	    }
		}
}
