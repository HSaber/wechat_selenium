package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MenuSettingTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(MenuSettingTest.class);
	 
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	/**
	 * @throws InterruptedException
	 */
	@Test//(dependsOnGroups={"UpdateSurvey","Formevent","Html5"},groups="MenuSetting")
	public void test() throws InterruptedException,Exception {
		String menuDate = methods.timeDate2();
		String title = methods.timeDate();
		title="2017-06-09 18:24";
		methods.navigation(driver, "Menu", By.id("maincd1"));
		Thread.sleep(3000);
	    if(methods.isElementPresent(driver, By.cssSelector(".blockUI.blockMsg.blockPage")))
    	driver.findElement(By.xpath("html/body/div[5]/div[4]/a")).click();
        Thread.sleep(1000);
		driver.findElement(By.id("maincd1")).click();
		driver.findElement(By.id("cdmcinpo")).click();
		driver.findElement(By.id("cdmcinpo")).clear();
		driver.findElement(By.id("cdmcinpo")).sendKeys("MainMenu1-" + menuDate);
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Show Sub Menu");
		driver.findElement(By.id("xsejcdopt")).click();
		while(true){
			if(methods.isElementPresent(driver,By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")))
			break;
		 }
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).sendKeys("Formevent" + title);
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).sendKeys("Survey" + title);
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).sendKeys("WeChat" + title);
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).sendKeys("WeChatURL" + title);
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).sendKeys("PostUrl");

		String posturl;
        if(methods.baseUrl.contains("dev")){
        	if(methods.mid.equals("33")){
        		posturl="http://dev.jingsocial.com/artview/index/v/0/rid/928/wid/33";
        	}else{
        	posturl="http://dev.jingsocial.com/artview/index/v/0/rid/928/wid/33";
        	}
        }else if(methods.baseUrl.contains("staging")){
        	if(methods.mid.equals("59")){
        		posturl="http://staging.jingsocial.com/artview/index/v/0/rid/958/wid/59";
        	}else if(methods.mid.equals("68")){
        		posturl="http://staging.jingsocial.com/artview/index/v/3/rid/1080/wid/68";
        	}else{
        	posturl="http://staging.jingsocial.com/artview/index/v/3/rid/1080/wid/68";
        	}
        }else if(methods.mid.equals("42")){
        	posturl="http://app.jingsocial.com/artview/index/v/0/rid/3093/wid/42";
        }else if(methods.mid.equals("61")){
             posturl="http://app.jingsocial.com/artview/index/v/0/rid/9307/wid/61";
        }else
        {
        	posturl="http://shareinstance1.jingsocial.com/artview/index/v/2/rid/21416/wid/1000000";
        }
    	driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[1]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		driver.findElement(By.name("event_url")).click();
		driver.findElement(By.name("event_url")).clear();
		driver.findElement(By.name("event_url")).sendKeys(methods.UrlLink[0]);
		driver.findElement(By.name("event_url")).click();
		// tag add
		StyleMethods.AddTag(driver, "FormEvent_"+title);
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[1]")).click();
		Actions builder=new Actions(driver);
		 builder.moveToElement(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[2]"))).click().perform();
		
	//	driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[2]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send a Survey");
		while(true){
			if(methods.isElementPresent(driver,By.name("rad_tw")))
			break;
		 }
		// "个人信息调查问卷"+SurveyTitle
		new Select(driver.findElement(By.name("rad_tw"))).selectByVisibleText("个人信息调查问卷" + title + " (Active)");
		StyleMethods.AddTag(driver, "Survey"+title);

		

		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[3]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		driver.findElement(By.name("event_url")).click();
		driver.findElement(By.name("event_url")).clear();
		driver.findElement(By.name("event_url")).sendKeys("http://mp.weixin.qq.com/");
		// tag add
		StyleMethods.AddTag(driver, "WeChat_"+title);
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[4]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		driver.findElement(By.name("event_url")).click();
		driver.findElement(By.name("event_url")).clear();
		driver.findElement(By.name("event_url")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA5NDExNDAxMg==&mid=402503006&idx=1&sn=96f7cb154d807671b3dfac20b145d406&scene=21#wechat_redirect");
		StyleMethods.AddTag(driver, "URL LINK Menu");
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[5]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		driver.findElement(By.name("event_url")).click();
		driver.findElement(By.name("event_url")).clear();
		driver.findElement(By.name("event_url")).sendKeys(posturl);
		StyleMethods.AddTag(driver, "PostUrl Menu"+title);
			

	
	
//---------------------menu2------------------------------------
		driver.findElement(By.id("maincd2")).click();
		driver.findElement(By.id("cdmcinpo")).click();
		driver.findElement(By.id("cdmcinpo")).clear();
		driver.findElement(By.id("cdmcinpo")).sendKeys("MainMenu2-" + menuDate);
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Show Sub Menu");
		driver.findElement(By.id("xsejcdopt")).click();
		while(true){
			if(methods.isElementPresent(driver,By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")))
			break;
		 }

		String o2ourl = null;
        if(methods.baseUrl.contains("dev"))
        		o2ourl="http://dev.callback.jingsocial.com/o2o/member/login/id/"+methods.mid;
        else if(methods.baseUrl.contains("staging"))
        	    o2ourl="http://staging.callback.jingsocial.com/o2o/member/login/id/"+methods.mid;
        else 
        	    o2ourl="http://callback.jingsocial.com/o2o/member/login/id/"+methods.mid;
       
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).sendKeys("O2O");
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).sendKeys("MultiPost");
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).sendKeys("CustomerService");
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).sendKeys("Message");
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).sendKeys("Image" + title);

		while(true){
			if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")))
			break;
		 }
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send a Picture");		
		String parentPath = getClass().getResource("../../").getFile().toString();
		String parentPath1 = parentPath + "/material/DefMenuImage.JPG";
		File f1 = new File(parentPath1);
    	//Send a Picture
		driver.findElement(By.name("files[]")).sendKeys(f1.toString());
		while(true){
			if(driver.findElement(By.xpath(".//*[@id='res_pic']/img")).getAttribute("src").contains("/upload/images/"))
				break;
			System.out.println(this.getClass().getSimpleName()+" Image 上传中");
		}
		
		// tag add
		StyleMethods.AddTag(driver, "Image"+title);
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[4]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send a Text Message");	
		driver.findElement(By.className("js_editorArea")).clear();
		driver.findElement(By.linkText("Emotion")).click();
		driver.findElement(By.xpath(".//*[@id='res_wb']/div/div[2]/div[3]/table/tbody/tr[1]/td[1]/div")).click();
		driver.findElement(By.className("js_editorArea")).sendKeys("Menu\n"
				+ "{{nickname}} clicked message type menu\n "
				+ "<a href=\"http://jingsocial.com/\">JingSocial WebSite</a>"
				+"<a href=\""+methods.UrlLink[1]+"\">Html5 Link</a>");
		// tag add
		StyleMethods.AddTag(driver, "Message"+title);
	
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[3]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Customer Service");
		// tag add
		StyleMethods.AddTag(driver, "客服Menu"+title);
			
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[2]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send a Post");                                                                                    
		new Select(driver.findElement(By.cssSelector("#res_tw>select"))).selectByIndex(1);
		// tag add
		StyleMethods.AddTag(driver, "MultiPost Menu"+title);
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[1]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		driver.findElement(By.name("event_url")).click();
		driver.findElement(By.name("event_url")).clear();
		driver.findElement(By.name("event_url")).sendKeys(o2ourl);
		// tag add
		StyleMethods.AddTag(driver, "O2O"+title);
	
//----------------menu3-------------------------
		String[] SegmentArr={"AB+Message","Pollparti+Pollresult","FollowerTagName+MenuClick","FollowProfileCustomFieldName+ActionDate",
	             "PostReadAmount+PostReadPostName","AB+Message","FollowerSource+FollowDate+ActionAmount","AB(1,2,3,4)",
	             "AB(5,6,7,8)","ActionToday+LBS(SH,JS)"};
		String[] picUrlArr={"https://dev.jingsocial.com/upload/images/1481078187.jpg",
				"https://staging.jingsocial.com/upload/images/1473737031.jpg",
				"https://app.jingsocial.com/upload/images/1481077824.jpg"};
		String picUrl;
		if(methods.baseUrl.contains("dev"))
			 picUrl=picUrlArr[0];
		else if(methods.baseUrl.contains("staging"))		
			 picUrl=picUrlArr[1];
		else
			 picUrl=picUrlArr[2];
		
		driver.findElement(By.id("maincd3")).click();
        driver.findElement(By.id("cdmcinpo")).clear();
        driver.findElement(By.id("cdmcinpo")).sendKeys("MainMenu3-"+menuDate);
        new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Dynamic Menu");
		new Select(driver.findElement(By.id("message_type"))).selectByVisibleText("Send a Text Message");
		driver.findElement(By.className("dynamic_wb")).clear();
		driver.findElement(By.className("dynamic_wb")).sendKeys("Dynamic Menu Info\n"
				+ "{{nickname}} {{time}}好\n"
				+ "<a href=\"http://jingsocial.com/\">JingSocial WebSite</a>\n"
				+ "---Default message");

		for(int i=1;i<=SegmentArr.length;){
			if(i==1){
				new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[1]/select"))).selectByVisibleText(SegmentArr[i-1]);
				new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Picture");
				((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"img\")[3].setAttribute(\"src\",\""+picUrl+"\");");
				String str= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[3].getAttribute(\"src\");");
				while(str.isEmpty())
				{
				System.out.println(this.getClass().getSimpleName()+" Image 上传中");
				str= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[3].getAttribute(\"src\");");
				if(str.contains("upload/images/1473737031.jpg"))
					break;
				}
			}else
			{
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[1]/select"))).selectByVisibleText(SegmentArr[i-1]);
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Text Message");
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).clear();
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).sendKeys("Dynamic Menu Info\n"
					+ "<a href=\"http://jingsocial.com/\">JingSocial WebSite </a>\n"
					+ "{{nickname}} {{time}}好\n"
					+ "---你属于"+SegmentArr[i-1]);
			}
			
		    if(!driver.findElement(By.id("segmentation_lists")).getText().contains("Segment "+(++i))){
		    	driver.findElement(By.className("add_segmentation")).click();
		    }	
		}

		StyleMethods.AddTag(driver, "Dynamic Menu"+title);
		
		driver.findElement(By.id("saveOrder")).click();
		while(true){
			Thread.sleep(500);
			if(driver.findElement(By.className("content-top-btn")).getText().contains("Unpublished "))
				break;
		}
		// publish menu
		driver.findElement(By.xpath(".//*[@id='sync']")).click();
		long begin = System.currentTimeMillis();  
		long end,result;
		while(true){
			end = System.currentTimeMillis();   
	        result = (end - begin)/1000;
		if(!methods.isElementPresent(driver,By.className("qp_lodediv")) || (result>=20))
		break;
		 }
		if(result>=20)
		{
         System.out.println("Fail to create all menu successfully!");
         logger.error("Fail to create all menu successfully!");
		}
		else {
		AssertJUnit.assertEquals("MainMenu3-"+menuDate,driver.findElement(By.id("maincd3")).getText());
		 System.out.println("Create  menu successfully! 并且publish 耗时"+result+"秒!");
		}			
	}

}
