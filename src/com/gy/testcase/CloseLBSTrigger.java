package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class CloseLBSTrigger extends TestBase{
	private static Logger logger = Logger.getLogger(CloseLBSTrigger.class);
	//@Test
	@Test(groups="closeLBSTrigger",dependsOnGroups="lBSTriggerNoOnceInteractive")
	  public void closeLBSTrigger() throws Exception {
		
		navigation("Triggers");
	    Thread.sleep(2000);
	    
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
  	    if(category.equals("lbstriggerOnceToNoonce")){
  	    	System.out.println("第一行就是lbstriggerOnceToNoonce，不需要搜素查询");
  	    }else{
  	    	driver.findElement(By.name("Triggers[category]")).clear();
  		    driver.findElement(By.name("Triggers[category]")).sendKeys("lbstriggerOnceToNoonce");
  		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
  		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category.equals("lbstriggerOnceToNoonce")&&b)
		    		break;
		    }
  	    }
	    
	    String status = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[10]/img")).getAttribute("src");
		      System.out.println(status);
		      if(status.equals(baseUrl+"images/switch-button_on.png")){
				   driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[10]/img")).click();
				   Thread.sleep(15000);
				   System.out.println("关掉了一个trigger");
		      }else{
			  
		    	  logger.error("这个trigger本来就是关闭的，搜索未完成，请检查！！！");
		    	  Assert.fail("这个trigger本来就是关闭的，搜索未完成，请检查！！！");
		      }
		      
		      String category2 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		  	    if(category2.equals("lbstriggerOnceToNoonce")){
		  	    	System.out.println("第一行就是lbstriggerOnceToNoonce，不需要搜素查询");
		  	    }else{
		  	    	driver.findElement(By.name("Triggers[category]")).clear();
		  		    driver.findElement(By.name("Triggers[category]")).sendKeys("lbstriggerOnceToNoonce");
		  		    driver.findElement(By.name("Triggers[category]")).sendKeys(Keys.ENTER);
		  		  while(true){
				    	boolean b = true;
				    	try{
				    		 category2 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
				    	}catch(org.openqa.selenium.StaleElementReferenceException e){
				    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
				    		 b=false;
				    	}
				    	if(category2.equals("lbstriggerOnceToNoonce")&&b)
				    		break;
				    }
		  	    }
		      
		      try {
			        AssertJUnit.assertEquals("lbstriggerOnceToNoonce", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
			      } catch (Error e) {
			        
			    	  logger.error("trigger category 不是lbstriggerOnceToNoonce， trigger搜索查询没加载好！！请检查！！！！");
			        Assert.fail("trigger category 不是lbstriggerOnceToNoonce， trigger搜索查询没加载好！！请检查！！！！");
			      }
		      
		      try {
		          AssertJUnit.assertEquals("No", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[9]")).getText());
		        } catch (Error e) {
		        	logger.error("trigger is once 显示不是no,trigger搜索查询没加载好！！请检查！！！！");
		          Assert.fail("trigger is once 显示不是no,trigger搜索查询没加载好！！请检查！！！！");
		        }
		        try {
		          AssertJUnit.assertEquals(baseUrl+"images/switch-button_off.png", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[10]/img")).getAttribute("src"));
		        } catch (Error e) {
		        	logger.error("LBS trigger status更新失败，请检查！！！");
		        	Assert.fail("LBS trigger status更新失败,未关闭，请检查！！！");
		        }
		      
		        int a = Integer.parseInt(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).getText());
		        System.out.println("CloseLBSTrigger触发前，该trigger已经被触发"+a+"次");
		        
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
		        System.out.println("接口post前，rainbow触发CloseLBSTrigger的次数是:"+c);
		        //接口部分
		    //String[] info= {"appid","secret","mid","WechatID","openid"};
		  		String[] attr=info();
		  		String[] arr=getSignature(attr[2],attr[0],attr[1]);
		  		
		  		String lbs= "<xml>"
		  							+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
		  							+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
		  							+ "<CreateTime>"+arr[0]+"</CreateTime>"
		  							+ "<MsgType><![CDATA[event]]></MsgType>"
		  							+ "<Event><![CDATA[LOCATION]]></Event>"
		  							+ "<Latitude>31.230553</Latitude>"
		  							+ "<Longitude>121.436096</Longitude>"
		  							+ "<Precision>150.000000</Precision>"
		  				+ "</xml>";
		  		
		  		String unsubscribe= "<xml>"
		  									+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
		  									+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
		  									+ "<CreateTime>"+arr[0]+"</CreateTime>"
		  									+ "<MsgType><![CDATA[event]]></MsgType>"
		  									+ "<Event><![CDATA[unsubscribe]]></Event>"
		  						  + "</xml>";

		  		String subscribe= "<xml>"
		  									+ "<ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
		  									+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
		  									+ "<CreateTime>"+arr[0]+"</CreateTime>"
		  									+ "<MsgType><![CDATA[event]]></MsgType>"
		  									+ "<Event><![CDATA[subscribe]]></Event>"
		  						+ "</xml>";
		  			
		  		//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
		  		String requesturl=requestURL();	
		  			
		  		String[] postunsub=  Post(requesturl,unsubscribe);
		  		Thread.sleep(1000);
		  		String[] postsub=  Post(requesturl,subscribe);
		  		Thread.sleep(3000);
		  		String[] keyreply=  Post(requesturl,lbs);
		  		Thread.sleep(3000);
		  		String[] keyreply2=  Post(requesturl,lbs);
		  		Thread.sleep(3000);
		  			//暂时拿不到xml数据
		  		    for(int i=0;i<keyreply.length;i++){
		  		    	
		  		    	if(keyreply[i]!=null){
		  				    System.out.println(keyreply[i]);
		  					
		  		    	}
		  		    }
		  	
		  		  navigation("Triggers");
		  	    Thread.sleep(2000);
		  	    
		  	    String category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	    if(category1.equals("lbstriggerOnceToNoonce")){
		    	    	System.out.println("第一行就是lbstriggerOnceToNoonce，不需要搜素查询");
		    	    }else{
		    	    	driver.findElement(By.name("Triggers[category]")).clear();
		    		    driver.findElement(By.name("Triggers[category]")).sendKeys("lbstriggerOnceToNoonce");
		    		    driver.findElement(By.name("Triggers[category]")).sendKeys(Keys.ENTER);
		    		   // new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Inactive");
		    		    while(true){
		    		    	boolean b = true;
		    		    	try{
		    		    		 category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		    		 b=false;
		    		    	}
		    		    	if(category1.equals("lbstriggerOnceToNoonce")&&b)
		    		    		break;
		    		    }
		    	    }
		    	    
		  		  try {
		  	        int num = Integer.parseInt(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).getText());
		  	        System.out.println(num);
		  	    	AssertJUnit.assertTrue(num>=a);
		  	      } catch (Error e) {
		  	        //verificationErrors.append(e.toString());
		  	    	logger.error("CloseLBSTrigger未搜索成功，请检查！");
		  	        Assert.fail("CloseLBSTrigger未搜索成功，请检查！");
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
		  	    System.out.println("rainbow触发LBS trigger的次数是:"+r);
		  	    try {
		  	      AssertJUnit.assertEquals(c,r);
		  	    } catch (Error e) {
		  	    	if(r>c){
		  	    		logger.error("该CloseLBSTrigger已经关闭，不应该再触发！！！");
		  	    		Assert.fail("该CloseLBSTrigger已经关闭，不应该再触发！！！");
		  	    	}else{
		  	    		logger.error("CloseLBSTrigger未搜索成功，请检查！");
		  	    		Assert.fail("CloseLBSTrigger未搜索成功，请检查！");
		  	    	}
		  	    	//verificationErrors.append(e.toString());
		  	    }
	}
}
