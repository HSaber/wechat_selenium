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

public class MenuPersonalHC {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(MenuPersonalHC.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
		driver.close();
	}
	
	@Test(dependsOnGroups={"MenuGY"},groups="MenuHC")
	public void test() throws InterruptedException,Exception {
		methods.navigation(driver, "Menus", By.className("btn"));
		try {	
			driver.findElement(By.linkText("Create a Menu")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("menu_name")))
					break;
			}
			System.out.println("Open create personalize menu page successfully!");
		} catch (Exception e) {
			System.out.println("Fail to open create personalize menu page !");
			logger.error("Fail to open create personalize menu page !");
		}

	
		String menuDate = methods.timeDate2();
		String title = methods.timeDate();
		new Select(driver.findElement(By.id("match_rule"))).selectByVisibleText("HC2+PersonalMenu");
		//标签title
		driver.findElement(By.id("menu_name")).sendKeys("H.+Candy");
		driver.findElement(By.id("maincd1")).click();
		driver.findElement(By.id("cdmcinpo")).clear();
		driver.findElement(By.id("cdmcinpo")).sendKeys("Img-" + menuDate);
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send a Picture");
		
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
		// tag add
		StyleMethods.AddTag(driver, "HCImag"+title);
//---------------------menu2------------------------------------
		driver.findElement(By.id("maincd2")).click();
		driver.findElement(By.id("cdmcinpo")).click();
		driver.findElement(By.id("cdmcinpo")).clear();
		driver.findElement(By.id("cdmcinpo")).sendKeys("LinkS-" + menuDate);
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Show Sub Menu");
		driver.findElement(By.id("xsejcdopt")).click();
		while(true){
			if(methods.isElementPresent(driver,By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")))
			break;
		 }
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).sendKeys("官方网站");
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).sendKeys("History");
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).sendKeys("HC客服");
	
		//点数查询
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[3]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Customer Service");
		// tag add
		StyleMethods.AddTag(driver, "HC客服"+title);
			
		//History
		 String baseUrl=methods.baseUrl;
	     baseUrl=baseUrl.substring(6,baseUrl.length());
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[2]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		driver.findElement(By.name("event_url")).clear();
		driver.findElement(By.name("event_url")).sendKeys("http:"+baseUrl+"artview/history/wid/"+methods.mid);
		// tag add
		StyleMethods.AddTag(driver, "HCHistory"+title);
		
		//官方网站
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[2]/div[1]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		driver.findElement(By.name("event_url")).clear();
		driver.findElement(By.name("event_url")).sendKeys("http://app.jingsocial.com/openid/website/APPID/wx68198b58536ab209");
		// tag add
		StyleMethods.AddTag(driver, "HC官方网站"+title);
		

	
//----------------menu3-------------------------
		String[] SegmentArr={"AB+Message","FollowerTagName+MenuClick","FollowProfileCustomFieldName+ActionDate","PostReadAmount+PostReadPostName"};
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
		driver.findElement(By.id("maincd3")).click();
        driver.findElement(By.id("cdmcinpo")).clear();
        driver.findElement(By.id("cdmcinpo")).sendKeys("Dynamic-"+menuDate);
        new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Dynamic Menu");
        new Select(driver.findElement(By.id("message_type"))).selectByVisibleText("Send a Text Message");
		driver.findElement(By.className("dynamic_wb")).sendKeys("From Personalize Menu \n"
				+ "{{nickname}} clicked Dynamic Menu\n"
				+"<a href=\"http://jingsocial.com/\">JingSocial WebSite </a>{{time}}好\n"
				+"--Default Message");
		
		for(int i=1;i<=SegmentArr.length;){
			
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[1]/select"))).selectByVisibleText(SegmentArr[i-1]);
			if(i==2){
				new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Picture");
				((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"img\")[2].setAttribute(\"src\",\""+picUrl+"\");");
				String str= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[2].getAttribute(\"src\");");
				while(str.isEmpty())
				{
					str= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[1].getAttribute(\"src\");");
				if(str.contains("upload/images/"))
					break;
				}
				
			}
			new Select(driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/select"))).selectByVisibleText("Send a Text Message");
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).clear();
			driver.findElement(By.xpath(".//*[@id='segmentation_lists']/div["+i+"]/div[2]/textarea")).sendKeys("From Personalize Menu \n"
				+ "And you belong HC2+PersonalMenu Segmentation \n"
				+ "{{nickname}} clicked message type menu\n"
				+ "<a href=\"http://jingsocial.com/\">JingSocial WebSite </a>{{time}}好\n"
				+"---你属于"+SegmentArr[i-1]);
			
		    if(!driver.findElement(By.id("segmentation_lists")).getText().contains("Segment "+(++i))){
		    	driver.findElement(By.className("add_segmentation")).click();
		    }	
		}
	
		StyleMethods.AddTag(driver, "HCDynaMenu"+title);
        driver.findElement(By.id("saveOrder")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.id("personal-menu-grid")))
				 break;
		 }
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
				  AssertJUnit.assertTrue(driver.findElement(By.id("personal-menu-grid")).findElement(By.tagName("table")).getText().contains("HC2+PersonalMenu"));
			}
				else
				{
                AssertJUnit.assertTrue(driver.findElement(By.id("personal-menu-grid")).findElement(By.tagName("table")).getText().contains("HC2+PersonalMenu"));
				}
      System.out.println("HC2+PersonalMenu 创建成功!");
	}

}
