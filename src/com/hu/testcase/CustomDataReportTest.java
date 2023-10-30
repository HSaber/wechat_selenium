package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CustomDataReportTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(CustomDataReportTest.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="Html5Action")
	public void test() throws InterruptedException,Exception {
		String timeStr=methods.timeDate();
		methods.navigation(driver, "Custom Data Report", By.className("btn"));

		driver.findElement(By.className("btn")).click();
		while(true)
		 {
			if(methods.isElementPresent(driver,By.id("CustomDataReport_custom_data_title")))
				break;
		 }
		driver.findElement(By.id("CustomDataReport_custom_data_title")).clear();
		driver.findElement(By.id("CustomDataReport_custom_data_title")).sendKeys("Html5_"+timeStr);		
		driver.findElement(By.id("CustomDataReport_note")).sendKeys("Html5_"+timeStr+"NOTE");
		
		new Select(driver.findElement(By.id("CustomDataReport_module_name"))).selectByVisibleText("Html5_"+timeStr);
		driver.findElement(By.name("yt0")).click();
		//form-event-member-grid
		while(true)
		 {
			if(methods.isElementPresent(driver,By.id("form-event-member-grid")))
				break;
		 }
		//这边应该是导航的问题 查下
		
		methods.navigation(driver, "Custom Data Report", By.name("CustomDataReport[custom_data_title]"));
		try{
			driver.findElement(By.xpath(".//*[@id='dada']/div[2]/div/div/div[2]/ul/li/ul/li[9]/ul/li[1]/a")).click();
			while(true)
			 {
				if(methods.isElementPresent(driver,By.name("CustomDataReport[custom_data_title]")))
					break;
			 }
			AssertJUnit.assertEquals("Manage Custom Data Report",driver.findElement(By.className("page_title")).getText());
			System.out.println("Open custom data report successfully!-twice");	
			}catch(Exception e){
			System.out.println("Fail to open custom data report!-twice");	
			}
		driver.findElement(By.name("CustomDataReport[custom_data_title]")).sendKeys("Html5_"+timeStr);
		
		driver.findElement(By.name("CustomDataReport[custom_data_title]")).sendKeys(Keys.ENTER);

		while(true)
		 {
			if(!methods.isElementPresent(driver,By.xpath(".//*[@id='custom-data-report-grid']/div[1]/table/tbody/tr[2]/td[1]")))
				break;
		 }
		driver.findElement(By.linkText("set now")).click();
		//funnel_form
		while(true)
		 {
			if(methods.isElementPresent(driver,By.id("funnel_form")))
				break;
		 }
		AssertJUnit.assertEquals("Set up Custom Data Funnel",driver.findElement(By.className("page_title")).getText());
		System.out.println("设置custom data report数据！");
		String value=driver.findElement(By.xpath(".//*[@id='funnel_form']/div[1]/div/div[1]/select/option[2]")).getText();
		new Select(driver.findElement(By.className("firstLevel"))).selectByVisibleText(value);
		driver.findElement(By.id("save")).click();
		while(true)
		 {
			if(methods.isElementPresent(driver,By.className("spliter")))
				break;
		 }
		AssertJUnit.assertTrue(driver.findElement(By.className("spliter")).getText().contains("Data Overview"));
		System.out.println("数据预览页面打开成功!");
		 methods.CData[0]=driver.findElement(By.xpath(".//*[@id='form-event-member-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText();
		 methods.CData[1]=driver.findElement(By.xpath(".//*[@id='form-event-member-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		 methods.CData[2]=driver.findElement(By.xpath(".//*[@id='form-event-member-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText();
		
		System.out.println("Html5_"+timeStr+"   查看该HTML5的 action为"+methods.CData[0]+"并且次数是"+methods.CData[1]+"人数是"+methods.CData[2]);
		//截图取证
		String title=methods.FileDate();
        methods.snapshot((TakesScreenshot)driver, title+"CDReport.jpg");
		
	}

}
