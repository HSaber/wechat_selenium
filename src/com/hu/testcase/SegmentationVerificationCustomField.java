package com.hu.testcase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.interactive.testcase.MySQLConnection;

public class SegmentationVerificationCustomField {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SegmentationVerificationCustomField.class);
		@BeforeClass
		public  void setUpBeforeClass() throws Exception {
			driver= new ChromeDriver();
			methods.account_login(driver);
		}

		@AfterClass
		public  void tearDownAfterClass() throws Exception {
				driver.close();
		}

		@Test
		public void test() throws InterruptedException,Exception{	
			
		methods.navigation(driver, "Segmentation", By.className("btn"));
		
		try{
			driver.findElement(By.className("btn")).click();
			while(true)
			 {
				Thread.sleep(500);
				if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
					break;
			 }
			AssertJUnit.assertEquals("Create a Segment",driver.findElement(By.className("page_title")).getText());
			System.out.println("Open create segmentation page successfully!");
		}catch(Exception e){
			 System.out.println("Fail to open create segmentation page!");
			 logger.error("Fail to open create segmentation page!");
		}
			
			//Custom field have value -------------------------------------------------------------------------------------------------
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Custom Field");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("Name");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).selectByVisibleText("Estimate Phone Number");
			String attribute_id=new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).getFirstSelectedOption().getAttribute("value");
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[6]"))).selectByVisibleText("Have Value");

			driver.findElement(By.linkText("Preview")).click();
			while(true)
			{
				Thread.sleep(500);
				if(methods.isElementPresent(driver, By.className("summary")))
					if(!driver.findElement(By.className("summary")).getText().isEmpty())
					break;
				System.out.println("segmentation 结果筛选中...");
			}
			String summary=driver.findElement(By.className("summary")).getText();
			String summary_total=methods.Getnum(summary)[2];
			System.out.println(summary+"   "+summary_total+"=================================================================");	
		
			
			String CustomFieldFollower = null;
			String sql="SELECT count(DISTINCT(openid)) as followerCount FROM `follower_eav_attribute_text` where  attribute_id="+attribute_id+""
					+ " and openid in (SELECT openid FROM wechat_customer where mid="+methods.mid+" and subscribe=1);";						
			try {
					Connection conns =  MySQLConnection.devconn();
					PreparedStatement pst = conns.prepareStatement(sql);
					ResultSet rs = pst.executeQuery(sql);// 执行sql语句
					System.out.println("---------------------------------------");		
					while (rs.next()) {
					  System.out.println(rs.getString("followerCount"));
					  CustomFieldFollower=rs.getString("followerCount");
					  }	
					conns.close();
					pst.close();
					rs.close();
					 } catch (SQLException e) {
					     System.out.println("数据查询失败");
					     e.printStackTrace();
					 }
			try{
				AssertJUnit.assertEquals(summary_total, CustomFieldFollower);
			}catch(AssertionError e){
				logger.error("Custom Field have value segmentation 查询人数不一致，mysql result:"+CustomFieldFollower+"  Segmentation result:"+summary_total);
				e.printStackTrace();
			}
		
			//Custom field value fuzzy-------------------------------------------------------------------------------------------------
			driver.navigate().refresh();
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Custom Field");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("Name");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).selectByVisibleText("Estimate Phone Number");
			 attribute_id=new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).getFirstSelectedOption().getAttribute("value");
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[6]"))).selectByVisibleText("Fuzzy Matching");
			driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys("13");
			
			driver.findElement(By.linkText("Preview")).click();
			while(true)
			{
				Thread.sleep(500);
				if(methods.isElementPresent(driver, By.className("summary")))
					if(!driver.findElement(By.className("summary")).getText().isEmpty())
					break;
				System.out.println("segmentation 结果筛选中...");
			}
			summary=driver.findElement(By.className("summary")).getText();
			summary_total=methods.Getnum(summary)[2];
			System.out.println(summary+"   "+summary_total+"=================================================================");	
		

			sql="SELECT count(DISTINCT(openid)) as followerCount FROM `follower_eav_attribute_text` where  attribute_id="+attribute_id+""
					+ "and `value` like '%13%' and openid in (SELECT openid FROM wechat_customer where mid="+methods.mid+" and subscribe=1);";						
			try {
				  Connection conns =  MySQLConnection.devconn();
				  PreparedStatement pst = conns.prepareStatement(sql);
				  ResultSet rs = pst.executeQuery(sql);// 执行sql语句
					System.out.println("---------------------------------------");		
					while (rs.next()) {
					  System.out.println(rs.getString("followerCount"));
					  CustomFieldFollower=rs.getString("followerCount");
					  }	
					conns.close();
					pst.close();
					rs.close();
					 } catch (SQLException e) {
					     System.out.println("数据查询失败");
					     e.printStackTrace();
					 }
			try{
				AssertJUnit.assertEquals(summary_total, CustomFieldFollower);
			}catch(AssertionError e){
				logger.error("Custom Field value fuzzy 查询人数不一致，mysql result:"+CustomFieldFollower+"  Segmentation result:"+summary_total);
				e.printStackTrace();
			}
			
		}
}
