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

public class SegmentationVerificationAction {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SegmentationVerificationAction.class);
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
			//Action amount >1-------------------------------------------------------------------------------------------------
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Actions");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Amount");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("greater than");
			driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys("1000");
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
			String queryString="{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
				        + "{\"has_child\":{\"type\":\"followers_actions_date_index\","
				        + "\"query\":{\"filtered\":{\"filter\":"
				        + "{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},"
				        + "{\"range\":{\"total_number\":{\"gte\":"+1000+"}}}]}}}}}}]}}}},"
				        +"\"aggs\":{\"group_openid\":{\"terms\":{\"field\":\"openid\"}}}}";
			System.out.println(queryString);
				        //Sample Query - JSONObject
				        //We convert the raw query string to JSONObject to avoid query parser error in Elasticsearch
		    methods func=new methods();
			SearchRequestBuilder responsebuilder =func.esConnect("wechat_customer");
			JSONObject    queryStringObject = new JSONObject(queryString);
			SearchResponse	response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
			SearchHits	hits = response.getHits();
						String actionAmount=hits.getTotalHits()+"";
						System.out.println("查询的记录数="+hits.getTotalHits());
					try{
						AssertJUnit.assertEquals(summary_total, actionAmount);
					}catch(AssertionError e){
						logger.error("Action amount segmentation 查询人数不一致，Json result:"+actionAmount+"  Segmentation result:"+summary_total);
						e.printStackTrace();
					}
			
		
			
		}
}
