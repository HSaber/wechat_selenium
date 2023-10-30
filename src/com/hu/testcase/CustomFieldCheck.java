package com.hu.testcase;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class CustomFieldCheck {
	  WebDriver driver;
	  private String baseUrl;
	  public String FieldTitle;
	  private static Logger logger = Logger.getLogger(CustomFieldCheck.class);
	  
	  @BeforeClass
	  public void beforeClass() throws Exception,MalformedURLException, InterruptedException {
		  driver= new ChromeDriver();
			methods.account_login(driver);
	  }

	  @AfterClass
	  public void afterClass() throws Exception {
		  driver.close();
	  }
	  
  @Test(dependsOnGroups={"SurveyJoin","MenuClick"})
  public void test() throws Exception {
	    FieldTitle=methods.timeDate();
	    methods.navigation(driver, "Custom Field", By.linkText("Create a field"));

		
		//custom data field 数组
	    String[] CustomDFArr={"Name","Telephone","Company","Gender","Hometown","Shopping Rate","Names","Phone Number",
	    		              "消费","Salary","用餐地点","\"lunchtime\""+FieldTitle,FieldTitle+" 的天气",
	    		              "FriendsAccount"+FieldTitle,"Comment"+FieldTitle};
	    String[] FillDataArr={"huhuan"+FieldTitle,"1830210"+FieldTitle,"Achang"+FieldTitle,"女"+FieldTitle,"江苏"+FieldTitle,"7"+FieldTitle,
	    		"huhuan"+methods.timeDate2(),"1830210"+methods.timeDate2(),FieldTitle,FieldTitle+"00","哪里","晚上"+FieldTitle,"阳光明媚",
	    		"0",FieldTitle+"Comment"}; 
	    
	 for(int i=11;i<CustomDFArr.length;i++){
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.linkText("Create a field")))
				break;
		 }
		 driver.findElement(By.name("FollowerEavAttribute[label]")).clear();	 
		 driver.findElement(By.name("FollowerEavAttribute[label]")).sendKeys(CustomDFArr[i]);		
		 driver.findElement(By.name("FollowerEavAttribute[label]")).sendKeys(Keys.ENTER);
		 while(true){
			 Thread.sleep(500);
			 if(methods.isElementPresent(driver, By.xpath(".//*[@id='follower-rating-rules-grid']/div[1]/table/tbody/tr[1]/td[2]")))
				 if(driver.findElement(By.xpath(".//*[@id='follower-rating-rules-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText().contains(CustomDFArr[i]))
				break;
			 System.out.println("Custom field 筛选中....");						 
		 }
		 String summary=driver.findElement(By.className("summary")).getText();
		 String[] resultArr=methods.Getnum(summary);
		 int result = Integer.parseInt(resultArr[2]);
		 if(result>1){
			 for(int j=1;j<=result;j++){
				 String CustomTitle=driver.findElement(By.xpath(".//*[@id='follower-rating-rules-grid']/div[1]/table/tbody/tr["+j+"]/td[2]")).getText();
				 if(CustomTitle.equals(CustomDFArr[i])){
					 try{
						 AssertJUnit.assertFalse(driver.findElement(By.xpath(".//*[@id='follower-rating-rules-grid']/div[1]/table/tbody/tr["+j+"]/td[4]/a")).getText().equals("0"));
						 driver.findElement(By.xpath(".//*[@id='follower-rating-rules-grid']/div[1]/table/tbody/tr["+j+"]/td[4]/a")).click();
						 while(true){
							 Thread.sleep(500);
							 if(methods.isElementPresent(driver, By.id("follower-rating-rules-grid")))
								 break;
							 System.out.println("Custom field 跳转....");
						 }
						 String summary1=driver.findElement(By.className("summary")).getText();
						 String[] resultArr1=methods.Getnum(summary1);
						 int result1 = Integer.parseInt(resultArr1[2]);
							 if(driver.findElement(By.id("follower-rating-rules-grid")).getText().contains(FillDataArr[i]))
							 {
								 System.out.println(CustomDFArr[i]+" 填充的值是："+FillDataArr[i]+".");	
							     driver.navigate().back();
							 }
							 else
							 {
								 System.out.println(CustomDFArr[i]+" 填充失败！！！");
								 logger.error(CustomDFArr[i]+" 填充失败！！！");
								 driver.navigate().back();
							 }
					 }catch(AssertionError e){
						 System.out.println(CustomDFArr[i]+" = "+"0 !");
						 logger.error(CustomDFArr[i]+" = "+"0 !");
						 e.printStackTrace();
					 }
				 }				 
			 }
		 }
		 else if(result==1){
			 String CustomTitle=driver.findElement(By.xpath(".//*[@id='follower-rating-rules-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
			 if(CustomTitle.equals(CustomDFArr[i])){
				 try{
					 AssertJUnit.assertFalse(driver.findElement(By.xpath(".//*[@id='follower-rating-rules-grid']/div[1]/table/tbody/tr[1]/td[4]/a")).getText().equals("0"));
					 driver.findElement(By.xpath(".//*[@id='follower-rating-rules-grid']/div[1]/table/tbody/tr[1]/td[4]/a")).click();
					 while(true){
						 Thread.sleep(500);
						 if(methods.isElementPresent(driver, By.id("follower-rating-rules-grid")))
							 break;
						 System.out.println("Custom field 跳转....");
					 }
					 String summary1=driver.findElement(By.className("summary")).getText();
					 String[] resultArr1=methods.Getnum(summary1);
					 int result1 = Integer.parseInt(resultArr1[2]);
					 try{
						 AssertJUnit.assertTrue(driver.findElement(By.id("follower-rating-rules-grid")).getText().contains(FillDataArr[i]));
						 System.out.println(CustomDFArr[i]+" 填充的值是："+FillDataArr[i]+".");
						 driver.navigate().back();
					 }catch(AssertionError e){
						 System.out.println(CustomDFArr[i]+" 填充失败！！！");
						 logger.error(CustomDFArr[i]+" 填充失败！！！");
						 driver.navigate().back();
						 e.printStackTrace();
					 }						
					 
				 }catch(AssertionError e){
					 System.out.println(CustomDFArr[i]+" = "+"0 !");
					 logger.error(CustomDFArr[i]+" = "+"0 !");
					 e.printStackTrace();
				 }
		 }		 
		 }
	 }
  }
}
