package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;



import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PollCreateTest {

	boolean acceptNextAlert = true;
	 WebDriver driver;
	public boolean flag = false;
	public static String Osname;
	 private static Logger logger = Logger.getLogger(PollCreateTest.class);

		@BeforeClass
		public  void setUpBeforeClass() throws Exception {
			driver= new ChromeDriver();
			methods.account_login(driver);
		}

		@AfterClass
		public  void tearDownAfterClass() throws Exception {
				driver.close();
		}

	@Test(groups="poll")
	public void test() throws InterruptedException,Exception {
		
		String PollTitle = methods.timeDate();
		// 去掉最后三个
		long l = System.currentTimeMillis();
		Date d = new Date(l);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String StartDate = sdf.format(d);

		String EndDate = StartDate + 1;
		methods.navigation(driver, "Poll",By.cssSelector("a.btn > button"));
		 
		try{
		driver.findElement(By.cssSelector("a.btn > button")).click();
		while(true){
			if(methods.isElementPresent(driver,By.id("Poll_poll_title")))
				break;
		}
		System.out.println("Open create poll page successfully!");
		}catch(Exception e){
			System.out.println("Fail to open create poll page!");
			logger.error("Fail to open create poll page!");
		}

		driver.findElement(By.id("Poll_poll_title")).clear();
		driver.findElement(By.id("Poll_poll_title")).sendKeys("中学生上网情况问卷调查"+PollTitle);
		driver.findElement(By.id("Poll_qualification_1")).click();
		driver.findElement(By.xpath(".//*[@id='Poll_start_date']")).sendKeys(StartDate);
		driver.findElement(By.xpath(".//*[@id='Poll_end_date']")).sendKeys(EndDate);

		driver.findElement(By.id("PollQuestion_question_title")).clear();
		driver.findElement(By.id("PollQuestion_question_title")).sendKeys("你目前所读的年级？");
		driver.findElement(By.name("PollQuestion[0][option][0][title]")).clear();
		driver.findElement(By.name("PollQuestion[0][option][0][title]")).sendKeys("初一");
		driver.findElement(By.name("PollQuestion[0][option][1][title]")).clear();
		driver.findElement(By.name("PollQuestion[0][option][1][title]")).sendKeys("初二");
		driver.findElement(By.linkText("Add Option")).click();
		driver.findElement(By.name("PollQuestion[0][option][2][title]")).clear();
		driver.findElement(By.name("PollQuestion[0][option][2][title]")).sendKeys("初三");
		driver.findElement(By.linkText("Add Option")).click();
		driver.findElement(By.name("PollQuestion[0][option][3][title]")).clear();
		driver.findElement(By.name("PollQuestion[0][option][3][title]")).sendKeys("高一");
		driver.findElement(By.linkText("Add Option")).click();
		driver.findElement(By.name("PollQuestion[0][option][4][title]")).clear();
		driver.findElement(By.name("PollQuestion[0][option][4][title]")).sendKeys("其他");

		driver.findElement(By.id("js_add_question")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("PollQuestion[1][question_title]")).clear();
		driver.findElement(By.name("PollQuestion[1][question_title]")).sendKeys("你的性别？");
		driver.findElement(By.name("PollQuestion[1][option][0][title]")).clear();
		driver.findElement(By.name("PollQuestion[1][option][0][title]")).sendKeys("男");

		// jQuery('.up_files').show();
		((JavascriptExecutor) driver).executeScript("jQuery('.up_files').show()");
		// String rootPath=getClass().getResource("/").getFile().toString();
		String parentPath = getClass().getResource("../../").getFile().toString();

		String parentPath1 = parentPath + "/material/21.JPG";
		String parentPath2 = parentPath + "/material/20.JPG";

		File f1 = new File(parentPath1);
		File f2 = new File(parentPath2);

		driver.findElement(By.xpath(".//*[@id='question_1']/div[2]/div[1]/div[2]/input[2]")).sendKeys(f1.toString());
		while(true){
			if(driver.findElement(By.xpath(".//*[@id='question_1']/div[2]/div[1]/div[2]/img")).getAttribute("src").contains("vote"))
				break;
			System.out.println(this.getClass().getSimpleName()+" Image 上传中");
		}
		driver.findElement(By.name("PollQuestion[1][option][1][title]")).clear();
		driver.findElement(By.name("PollQuestion[1][option][1][title]")).sendKeys("女");
		driver.findElement(By.xpath(".//*[@id='question_1']/div[2]/div[2]/div[2]/input[2]")).sendKeys(f2.toString());
		while(true){
			if(driver.findElement(By.xpath(".//*[@id='question_1']/div[2]/div[2]/div[2]/img")).getAttribute("src").contains("vote"))
				break;
			System.out.println(this.getClass().getSimpleName()+" Image 上传中");
		}

		driver.findElement(By.id("js_add_question")).click();
		driver.findElement(By.name("PollQuestion[2][question_title]")).clear();
		driver.findElement(By.name("PollQuestion[2][question_title]")).sendKeys("你平时是否有上网的经历？");
		driver.findElement(By.name("PollQuestion[2][option][0][title]")).clear();
		driver.findElement(By.name("PollQuestion[2][option][0][title]")).sendKeys("是");
		driver.findElement(By.name("PollQuestion[2][option][1][title]")).clear();
		driver.findElement(By.name("PollQuestion[2][option][1][title]")).sendKeys("否");

		driver.findElement(By.id("js_add_question")).click();
		driver.findElement(By.name("PollQuestion[3][question_title]")).clear();
		driver.findElement(By.name("PollQuestion[3][question_title]")).sendKeys("你一般使用哪种方式上网？");
		driver.findElement(By.cssSelector("#question_3 > div.type > #PollQuestion_type > #PollQuestion_type_1")).click();
		driver.findElement(By.name("PollQuestion[3][option][0][title]")).clear();
		driver.findElement(By.name("PollQuestion[3][option][0][title]")).sendKeys("电脑上网");
		driver.findElement(By.name("PollQuestion[3][option][1][title]")).clear();
		driver.findElement(By.name("PollQuestion[3][option][1][title]")).sendKeys("手机上网");

		driver.findElement(By.id("js_add_question")).click();
		driver.findElement(By.name("PollQuestion[4][question_title]")).clear();
		driver.findElement(By.name("PollQuestion[4][question_title]")).sendKeys("你平时什么时间上网？");
		driver.findElement(By.name("PollQuestion[4][option][0][title]")).clear();
		driver.findElement(By.name("PollQuestion[4][option][0][title]")).sendKeys("课余时间");
		driver.findElement(By.name("PollQuestion[4][option][1][title]")).clear();
		driver.findElement(By.name("PollQuestion[4][option][1][title]")).sendKeys("有时逃课");
		driver.findElement(By.cssSelector("div.option.option4 > a.js_add_item")).click();
		driver.findElement(By.name("PollQuestion[4][option][2][title]")).clear();
		driver.findElement(By.name("PollQuestion[4][option][2][title]")).sendKeys("经常逃课");


		driver.findElement(By.id("js_add_question")).click();
		driver.findElement(By.name("PollQuestion[5][question_title]")).clear();
		driver.findElement(By.name("PollQuestion[5][question_title]")).sendKeys("你花费在这些事情上的时间比例是？");
		driver.findElement(By.cssSelector("#question_5 > div.type > #PollQuestion_type > #PollQuestion_type_2")).click();



		// tag add
		StyleMethods.AddTag(driver, "中学生上网情况问卷调查" + PollTitle);
        driver.findElement(By.id("PollQuestion_question_title")).click();
		driver.findElement(By.name("yt0")).click();
		while(true){
			if(methods.isElementPresent(driver,By.id("poll-grid")))
				break;
		}
		AssertJUnit.assertTrue(driver.findElement(By.tagName("body")).getText().contains("中学生上网情况问卷调查" + PollTitle));		
		System.out.println("Create 中学生上网情况问卷调查" + PollTitle + " successfully!");
	}

}
