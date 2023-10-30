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

public class SegmentationVerification48 {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SegmentationVerification48.class);
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
		//48 hour list and 48 hour segmentation-------------------------------------------------------------------------------------------------
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("48 Hours");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"));
		
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
		Date TDate=new Date();
		    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String TodayDate=matter.format(TDate);
	        String TodayDateUnix= String.valueOf(matter.parse(TodayDate).getTime() / 1000);
			
		    Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 48);
		    String hours_48= matter.format(calendar.getTime());
		    String hours_48Unix=String.valueOf(matter.parse(hours_48).getTime()/1000);
		    System.out.println(TodayDate+" "+hours_48+"   "+TodayDateUnix+"  "+hours_48Unix);    
			methods func=new methods();
			SearchRequestBuilder responsebuilder =func.esConnect("wechat_customer");
	        String queryString="{\"query\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
	        + "{\"has_child\":{\"type\":\"interactive_record\","
	        + "\"query\":{\"filtered\":{\"filter\":"
	        + "{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},"
	        + "{\"range\":{\"action_time\":{\"gte\":"+hours_48Unix+",\"lte\":"+TodayDateUnix+"}}}]}}}}}}]}}}";
            System.out.println(queryString);
	        //Sample Query - JSONObject
	        //We convert the raw query string to JSONObject to avoid query parser error in Elasticsearch
	        JSONObject queryStringObject = new JSONObject(queryString);

	        //Elasticsearch Response
	        SearchResponse response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
			SearchHits hits = response.getHits();
			String hour_48=hits.getTotalHits()+"";
			System.out.println("查询的记录数="+hits.getTotalHits());
			for (int i = 0; i < hits.getHits().length; i++) {
				 System.out.println(hits.getHits()[i].getSource().get("openid")+" "+hits.getHits()[i].getSource().get("nickname"));}
			try{
				AssertJUnit.assertEquals(summary_total, hour_48);
			}catch(AssertionError e){
				logger.error("48hour segmentation 查询人数不一致，Json result:"+hour_48+"  Segmentation result:"+summary_total);
				e.printStackTrace();
			}
			     //      System.out.println(hits.getHits()[i].getSourceAsString());
			
		}
}
