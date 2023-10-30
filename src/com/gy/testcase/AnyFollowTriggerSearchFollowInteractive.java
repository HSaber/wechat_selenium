package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class AnyFollowTriggerSearchFollowInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(AnyFollowTriggerSearchFollowInteractive.class);
	//@Test
	@Test(groups="anyFollowTriggerSearchFollowInteractive",dependsOnGroups="anyFollowTrigger")
	  public void anyFollowTriggerSearchFollowInteractive() throws Exception {
		navigation("Triggers");
	    String category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category1.equals("Any follow in condition")){
	    	System.out.println("第一行就是Any follow in condition，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("Any follow in condition");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category1.equals("Any follow in condition")&&b)
		    		break;
		    }
	    }
	    
	    int a = Integer.parseInt(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).getText());
        System.out.println("接口post操作前，AnyFollowTriggerSearchFollowInteractive当前总触发次数是"+a+"次");
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
        System.out.println("接口post前，rainbow触发AnyFollowTriggerSearchFollowInteractive的次数是:"+c);
		
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
					
				Post(requesturl,unsubscribe);
				Thread.sleep(2000);
				Post(requesturl,subscribe);
				Thread.sleep(10000);
				
			
				navigation("Triggers");
				    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
				    if(category.equals("Any follow in condition")){
				    	System.out.println("第一行就是Any follow in condition，不需要搜素查询");
				    }else{
				    	driver.findElement(By.name("Triggers[category]")).clear();
					    driver.findElement(By.name("Triggers[category]")).sendKeys("Any follow in condition");
					    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
					    while(true){
					    	boolean b = true;
					    	try{
					    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
					    	}catch(org.openqa.selenium.StaleElementReferenceException e){
					    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
					    		 b=false;
					    	}
					    	if(category.equals("Any follow in condition")&&b)
					    		break;
					    }
				    }
				   
				    try {
				        int num = Integer.parseInt(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr/td[8]/a")).getText());
				        System.out.println("接口post操作后，AnyFollowTriggerSearchFollowInteractive当前总触发次数是"+num+"次");
				    	AssertJUnit.assertTrue(num>=a+1);
				      } catch (Error e) {
				        //verificationErrors.append(e.toString());
				    	  logger.error("AnyFollowTriggerSearchFollowInteractive触发失败，请检查！");
				        Assert.fail("AnyFollowTriggerSearchFollowInteractive触发失败，请检查！");
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
				    System.out.println("rainbow触发Any follow in condition的次数是:"+r);
				    try {
				      AssertJUnit.assertEquals(c+1,r);
				    } catch (Error e) {
				    	if(r<c+1){
				    		logger.error("AnyFollowTriggerSearchFollowInteractive触发失败，请检查！");
				    		Assert.fail("AnyFollowTriggerSearchFollowInteractive触发失败，请检查！");
				    	}else{
				    		logger.error("rainbow触发AnyFollowTriggerSearchFollowInteractive超过一次，请检查！");
				    		Assert.fail("rainbow触发AnyFollowTriggerSearchFollowInteractive超过一次，请检查！");
				    	}
				    	//verificationErrors.append(e.toString());
				    }
	}
}
