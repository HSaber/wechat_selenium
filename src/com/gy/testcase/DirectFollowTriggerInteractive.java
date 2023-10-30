package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DirectFollowTriggerInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(DirectFollowTriggerInteractive.class);
	//@Test
	@Test(groups="directFollowTriggerInteractive",dependsOnGroups="directFollowTriggerIMP")
	  public void directFollowTriggerInteractive() throws Exception {
		navigation("Triggers");
	    String category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category1.equals("direct follow no condition")){
	    	System.out.println("第一行就是direct follow no condition，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("direct follow no condition");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category1.equals("direct follow no condition")&&b)
		    		break;
		    }
	    }
	    
	    int a = Integer.parseInt(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).getText());
        System.out.println("接口post操作前，DirectFollowTriggerInteractive当前总触发次数是"+a+"次");
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
	    System.out.println("接口post前，rainbow触发DirectFollowTriggerInteractive的次数是:"+c);
		//接口部分
				//String[] info= {"appid","secret","mid","WechatID","openid"};
				String[] attr=info();
				String[] arr=getSignature(attr[2],attr[0],attr[1]);
				
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
				Thread.sleep(10000);
				String[] postsub=  Post(requesturl,subscribe);
				Thread.sleep(10000);
				Post(requesturl,unsubscribe);
				Thread.sleep(10000);
				Post(requesturl,subscribe);
				Thread.sleep(10000);
				
					//暂时拿不到xml数据
				    for(int i=0;i<postsub.length;i++){
				    	
				    	if(postsub[i]!=null){
						    System.out.println(postsub[i]);
							
				    	}
				    }
				   
				    
				    Thread.sleep(3000);
				    navigation("Triggers");
				    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
				    if(category.equals("direct follow no condition")){
				    	System.out.println("第一行就是direct follow no condition，不需要搜素查询");
				    }else{
				    	driver.findElement(By.name("Triggers[category]")).clear();
					    driver.findElement(By.name("Triggers[category]")).sendKeys("direct follow no condition");
					    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
					    while(true){
					    	boolean b = true;
					    	try{
					    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
					    	}catch(org.openqa.selenium.StaleElementReferenceException e){
					    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
					    		 b=false;
					    	}
					    	if(category.equals("direct follow no condition")&&b)
					    		break;
					    }
				    }
				    int num=0;
				    try {
				        num = Integer.parseInt(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).getText());
				        System.out.println("DirectFollowTriggerInteractive当前触发次数是"+num+"次");
				    	AssertJUnit.assertTrue(num>=a+2);
				      } catch (Error e) {
				        //verificationErrors.append(e.toString());
				    	  if(num==a+1){
				    		  logger.error("DirectFollowTriggerInteractive只触发了一次，少一次，请检查！");
				    		  Assert.fail("DirectFollowTriggerInteractive只触发了一次，少一次，请检查！");
				        }else{
				        		logger.error("DirectFollowTriggerInteractive一次也没触发，请检查！");
				    		  Assert.fail("DirectFollowTriggerInteractive一次也没触发，请检查！");
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
				    System.out.println("rainbow触发direct follow trigger的次数是:"+r);
				    try {
				      AssertJUnit.assertEquals(c+2,r);
				    } catch (Error e) {
				    	if(r==c+1){
				    		logger.error("DirectFollowTriggerInteractive rainbow只触发了一次，少一次，请检查！");
				    		Assert.fail("DirectFollowTriggerInteractive rainbow只触发了一次，少一次，请检查！");
				    	}else{
				    		logger.error("DirectFollowTriggerInteractive rainbow触发超过2次，请检查！");
				    		Assert.fail("DirectFollowTriggerInteractive rainbow触发超过2次，请检查！");
				    	}
				    	//verificationErrors.append(e.toString());
				    }
	}
}
