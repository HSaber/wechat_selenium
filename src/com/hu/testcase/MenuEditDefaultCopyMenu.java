package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MenuEditDefaultCopyMenu {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(MenuEditDefaultCopyMenu.class);
	 
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	@Test(dependsOnGroups="DefaultMenuCopy",groups="DefaultMenuEdit")
	public void test() throws InterruptedException,Exception {
		
		String timeStr=methods.timeDate();
		methods.navigation(driver, "Menus", By.className("btn"));	
		String menuNum=driver.findElement(By.className("summary")).getText();
		String[] NumArr=methods.Getnum(menuNum);
		int MenuSum=Integer.parseInt(NumArr[2]);	
		
		if(MenuSum>10)
		{
			Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").getElementsByTagName(\"li\").length;");
			driver.findElement(By.xpath(".//*[@id='yw0']/li["+str+"]/a")).click();
			while(true){
				String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-3)+"].className;");
				if(classStr.contains("selected"))
						break;
				}	
		int lastMenu=MenuSum%10;
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+lastMenu+"]//td[8]/a[1]/img")).click();
		while(true){
			if(methods.isElementPresent(driver,By.id("menu_name")))
				break;
		}

		driver.findElement(By.id("menu_name")).sendKeys(timeStr);
		new Select(driver.findElement(By.id("match_rule"))).selectByVisibleText("No Testers");
		driver.findElement(By.id("maincd1")).click();
		while(true){
			if(methods.isElementPresent(driver,By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")))
			break;
		 }
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).sendKeys("PFormevent" + timeStr);
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).sendKeys("PSurvey" + timeStr);
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).sendKeys("PWeChat" + timeStr);
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).sendKeys("PWeChatURL" + timeStr);
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).sendKeys("PPostUrl");
		
		//menu1----------------------------------------------------------------------
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[5]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		StyleMethods.AddTag(driver, "PPostUrl Menu"+timeStr);
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[4]")).click();
		StyleMethods.AddTag(driver, "PURL LINK Menu");
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();

		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[3]")).click();
		StyleMethods.AddTag(driver, "PWeChat_"+timeStr);
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();
	
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[2]")).click();
		StyleMethods.AddTag(driver, "PSurvey"+timeStr);
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();

		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[1]")).click();
		StyleMethods.AddTag(driver, "PFormEvent_"+timeStr);
		//menu2---------------------------------------------------------------------------
		driver.findElement(By.id("maincd2")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).sendKeys("Po2o");
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).sendKeys("PMultiPost");
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).sendKeys("PCustomerService");
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).sendKeys("PMessage");
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).sendKeys("PImage" + timeStr);

		while(true){
			if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")))
			break;
		 }
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();		
		String parentPath = getClass().getResource("../../").getFile().toString();
		String parentPath1 = parentPath + "/material/PerDefMenu.JPG";
		File f1 = new File(parentPath1);
    	//Send a Picture
		driver.findElement(By.name("files[]")).sendKeys(f1.toString());
		while(true){
			if(driver.findElement(By.xpath(".//*[@id='res_pic']/img")).getAttribute("src").contains("/upload/images/"))
				break;
			System.out.println(this.getClass().getSimpleName()+" Image 上传中");
		}
		
		StyleMethods.AddTag(driver, "PImage"+timeStr);

		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[4]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send a Text Message");	
		driver.findElement(By.className("js_editorArea")).clear();
		driver.findElement(By.linkText("Emotion")).click();
		driver.findElement(By.xpath(".//*[@id='res_wb']/div/div[2]/div[3]/table/tbody/tr[1]/td[1]/div")).click();
		driver.findElement(By.className("js_editorArea")).sendKeys("From Personalize Menu \n"
				+ "And you belong No Testers Segmentation \n"
				+ "{{nickname}} clicked message type menu\n"
				+"<a href=\"http://jingsocial.com/\">JingSocial WebSite</a> {{time}}好\n");
		
		StyleMethods.AddTag(driver, "PMessage"+timeStr);
	
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[3]")).click();
		StyleMethods.AddTag(driver, "P客服Menu"+timeStr);
	
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[2]")).click();
		StyleMethods.AddTag(driver, "PMultiPost Menu"+timeStr);
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[1]")).click();
		StyleMethods.AddTag(driver, "Po2o"+timeStr);
		
		//menu3---------------------------------------------------------------------------------------------------------
		String[] SegmentArr={"AB+Message","Pollparti+Pollresult","FollowerTagName+MenuClick","FollowProfileCustomFieldName+ActionDate",
	             "PostReadAmount+PostReadPostName","AB+Message","FollowerSource+FollowDate+ActionAmount"};
		driver.findElement(By.id("maincd3")).click();
        new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Dynamic Menu");
		new Select(driver.findElement(By.id("message_type"))).selectByVisibleText("Send a Text Message");
		driver.findElement(By.className("dynamic_wb")).clear();
		driver.findElement(By.className("dynamic_wb")).sendKeys("From Personalize Menu \n"
				+ "{{nickname}} clicked message type menu\n"
				+"<a href=\"http://jingsocial.com/\">JingSocial WebSite</a>  {{time}}好\n"
				+ "-default message");
		
	
		String[] picUrlArr={"https://dev.jingsocial.com/upload/images/1477982072.jpg",
				"https://staging.jingsocial.com/upload/images/1473408574.jpg",
				"https://app.jingsocial.com/upload/images/1477982152.jpg"};
		String picUrl;
		if(methods.baseUrl.contains("dev"))
			 picUrl=picUrlArr[0];
		else if(methods.baseUrl.contains("staging"))		
			 picUrl=picUrlArr[1];
		else
			 picUrl=picUrlArr[2];
		
		for(int i=0;i<9;i++){
			driver.findElement(By.className("del")).click();
		}
		for(int i=1;i<=SegmentArr.length;){
			if(i==1){
				new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Picture");
				((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"img\")[3].setAttribute(\"src\",\""+picUrl+"\");");
				String imgstr= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[3].getAttribute(\"src\");");
				while(imgstr.isEmpty())
				{
				System.out.println(this.getClass().getSimpleName()+" Image 上传中");
				imgstr= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[3].getAttribute(\"src\");");
				if(imgstr.contains("upload/images/1473737031.jpg"))
					break;
				}
			}else
			{
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[1]/select"))).selectByVisibleText(SegmentArr[i-1]);
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Text Message");
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).clear();
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).sendKeys("From Personalize Menu \n"
					+ "And you belong to No Testers Segmentation \n"
					+ "{{nickname}}  (๑•̀ㅂ•́)و✧\n"
					+"<a href=\"http://jingsocial.com/\">JingSocial WebSite</a> {{time}}好\n"
					+"---你属于"+SegmentArr[i-1]);
			}
			
		    if(!driver.findElement(By.id("segmentation_lists")).getText().contains("Segment "+(++i))){
		    	driver.findElement(By.className("add_segmentation")).click();
		    }	
		}

		StyleMethods.AddTag(driver, "PDynamic Menu"+timeStr);
		driver.findElement(By.id("saveOrder")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.id("personal-menu-grid")))
				 break;
		 }
       System.out.println("Edit Default Copy"+timeStr+" 成功!");
		}
		
		else{
		int lastMenu=MenuSum%10;
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+lastMenu+"]/td[8]/a[1]")).click();
		while(true){
			if(methods.isElementPresent(driver,By.id("menu_name")))
				break;
		}
		driver.findElement(By.id("menu_name")).sendKeys(timeStr);
		new Select(driver.findElement(By.id("match_rule"))).selectByVisibleText("No Testers");
		driver.findElement(By.id("maincd1")).click();
		while(true){
			if(methods.isElementPresent(driver,By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")))
			break;
		 }
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).sendKeys("PFormevent" + timeStr);
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).sendKeys("PSurvey" + timeStr);
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).sendKeys("PWechat" + timeStr);
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).sendKeys("PWeChatURL" + timeStr);
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).sendKeys("PPostUrl");
		
		//menu1----------------------------------------------------------------------
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[5]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		StyleMethods.AddTag(driver, "PPostUrl Menu"+timeStr);
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();

		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[4]")).click();
		StyleMethods.AddTag(driver, "PURL LINK Menu");
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();

		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[3]")).click();
		StyleMethods.AddTag(driver, "PWeChat_"+timeStr);
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();
	
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[2]")).click();
		StyleMethods.AddTag(driver, "PSurvey"+timeStr);
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();

		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[1]")).click();
		StyleMethods.AddTag(driver, "PFormEvent_"+timeStr);
		//menu2---------------------------------------------------------------------------
		driver.findElement(By.id("maincd2")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).sendKeys("Po2o");
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).sendKeys("PMultiPost");
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).sendKeys("PCustomerService");
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).sendKeys("PMessage");
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_5 > input[type=\"text\"]")).sendKeys("PImage" + timeStr);

		while(true){
			if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")))
			break;
		 }
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[5]")).click();		
		String parentPath = getClass().getResource("../../").getFile().toString();
		String parentPath1 = parentPath + "/material/PerDefMenu.JPG";
		File f1 = new File(parentPath1);
    	//Send a Picture
		driver.findElement(By.name("files[]")).sendKeys(f1.toString());
		while(true){
			if(driver.findElement(By.xpath(".//*[@id='res_pic']/img")).getAttribute("src").contains("/upload/images/"))
				break;
			System.out.println(this.getClass().getSimpleName()+" Image 上传中");
		}
		
		StyleMethods.AddTag(driver, "PImage"+timeStr);

		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[4]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send a Text Message");	
		driver.findElement(By.className("js_editorArea")).clear();
		driver.findElement(By.linkText("Emotion")).click();
		driver.findElement(By.xpath(".//*[@id='res_wb']/div/div[2]/div[3]/table/tbody/tr[1]/td[1]/div")).click();
		driver.findElement(By.className("js_editorArea")).sendKeys("From Personalize Menu \n"
				+ "And you belong No Testers Segmentation \n"
				+ "{{nickname}} clicked message type menu\n"
				+"<a href=\"http://jingsocial.com/\">JingSocial WebSite</a> {{time}}好\n");
		
		StyleMethods.AddTag(driver, "PMessage"+timeStr);
	
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[3]")).click();
		StyleMethods.AddTag(driver, "P客服Menu"+timeStr);
	
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[2]")).click();
		StyleMethods.AddTag(driver, "PMultiPost Menu"+timeStr);
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[1]")).click();
		StyleMethods.AddTag(driver, "Po2o"+timeStr);
		
		//menu3---------------------------------------------------------------------------------------------------------
		String[] SegmentArr={"AB+Message","Pollparti+Pollresult","FollowerTagName+MenuClick","FollowProfileCustomFieldName+ActionDate",
	             "PostReadAmount+PostReadPostName","AB+Message","FollowerSource+FollowDate+ActionAmount"};
		driver.findElement(By.id("maincd3")).click();
       new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Dynamic Menu");
		new Select(driver.findElement(By.id("message_type"))).selectByVisibleText("Send a Text Message");
		driver.findElement(By.className("dynamic_wb")).clear();
		driver.findElement(By.className("dynamic_wb")).sendKeys("From Personalize Menu \n"
				+ "{{nickname}} clicked message type menu\n"
				+"<a href=\"http://jingsocial.com/\">JingSocial WebSite</a>  {{time}}好\n"
				+ "-default message");
		
		String[] picUrlArr={"https://dev.jingsocial.com/upload/images/1477982072.jpg",
				"https://staging.jingsocial.com/upload/images/1473408574.jpg",
				"https://app.jingsocial.com/upload/images/1477982152.jpg"};
		String picUrl;
		if(methods.baseUrl.contains("dev"))
			 picUrl=picUrlArr[0];
		else if(methods.baseUrl.contains("staging"))		
			 picUrl=picUrlArr[1];
		else
			 picUrl=picUrlArr[2];
		
		for(int i=0;i<9;i++){
			driver.findElement(By.className("del")).click();
		}
		for(int i=1;i<=SegmentArr.length;){
			if(i==1){
				new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Picture");
				((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"img\")[3].setAttribute(\"src\",\""+picUrl+"\");");
				String imgstr= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[3].getAttribute(\"src\");");
				while(imgstr.isEmpty())
				{
				System.out.println(this.getClass().getSimpleName()+" Image 上传中");
				imgstr= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[3].getAttribute(\"src\");");
				if(imgstr.contains("upload/images/1473737031.jpg"))
					break;
				}
			}else
			{
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[1]/select"))).selectByVisibleText(SegmentArr[i-1]);
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Text Message");
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).clear();
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).sendKeys("From Personalize Menu \n"
					+ "And you belong to No Testers Segmentation \n"
					+ "{{nickname}}  (๑•̀ㅂ•́)و✧\n"
					+"<a href=\"http://jingsocial.com/\">JingSocial WebSite</a> {{time}}好\n"
					+"---你属于"+SegmentArr[i-1]);
			}
			
		    if(!driver.findElement(By.id("segmentation_lists")).getText().contains("Segment "+(++i))){
		    	driver.findElement(By.className("add_segmentation")).click();
		    }	
		}

		StyleMethods.AddTag(driver, "PDynamic Menu"+timeStr);
		driver.findElement(By.id("saveOrder")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.id("personal-menu-grid")))
				 break;
		 }
		 
       System.out.println("Edit Default Copy"+timeStr+" 成功!");
		}
	}

}
