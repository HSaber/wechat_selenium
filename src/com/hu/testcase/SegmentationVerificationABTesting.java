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

public class SegmentationVerificationABTesting {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SegmentationVerificationABTesting.class);
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
			
		//ABTesting Date -------------------------------------------------------------------------------------------------
		String lastid="0,1,2,3,4,5";
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("AB Testing");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("lastid");
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys(lastid);

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
		
		String ABFollower = null;
		String sql="SELECT count(*) as followerCount FROM `wechat_customer` where mid="+methods.mid+" and subscribe=1 and  SUBSTRING(id, -1) in ("+lastid+");";	
		System.out.println(sql);
				try {
				Connection conns =  MySQLConnection.devconn();
				PreparedStatement pst = conns.prepareStatement(sql);
				ResultSet rs = pst.executeQuery(sql);// 执行sql语句
				System.out.println("---------------------------------------");		
				while (rs.next()) {
				  System.out.println(rs.getString("followerCount"));
				  ABFollower=rs.getString("followerCount");
				  }	
				conns.close();
				pst.close();
				rs.close();
				 } catch (SQLException e) {
				     System.out.println("数据查询失败");
				     e.printStackTrace();
				 }
		try{
			AssertJUnit.assertEquals(summary_total, ABFollower);
		}catch(AssertionError e){
			logger.error("ABTesting segmentation 查询人数不一致，mysql result:"+ABFollower+"  Segmentation result:"+summary_total);
			e.printStackTrace();
		}
		methods func=new methods();
		SearchRequestBuilder responsebuilder =func.esConnect("wechat_customer");
        String queryString="{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
		        + "{\"bool\":{\"should\":[{\"terms\":{\"id_string\":["+lastid+"]}}]}}]}}}}}";
        System.out.println(queryString);
        //Sample Query - JSONObject
        //We convert the raw query string to JSONObject to avoid query parser error in Elasticsearch
        JSONObject queryStringObject = new JSONObject(queryString);

        //Elasticsearch Response
        SearchResponse response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
        SearchHits hits = response.getHits();
		String ABJson=hits.getTotalHits()+"";
		System.out.println("wechat_customer 查询的记录数="+hits.getTotalHits());
		
		try{
			AssertJUnit.assertEquals(ABJson, ABFollower);
		}catch(AssertionError e){
			logger.error("AB segmentation 查询人数不一致，ES-wechat_customer Json result:"+ABJson+"  mysql result:"+ABFollower);
			e.printStackTrace();
		}
		
		
		/*		//Menu Title -------------------------------------------------------------------------------------------------
		driver.navigate().refresh();
		String menuTitle="Message";
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Menu");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Menu Title");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("equals");
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).selectByVisibleText(menuTitle);
		
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
	
		
		 MenuFollower = null;
		 sql="SELECT count(DISTINCT(a.openid)) as followerCount FROM `user_action_record` a "
		+"RIGHT JOIN(SELECT openid from wechat_customer where mid ="+methods.mid+" and subscribe=1 ) b on a.openid=b.openid "
		+ "where a.wid="+methods.mid+" and a.type='menu' and a.keyword='"+menuTitle+"'";		
		 System.out.println(sql);
		try {
				Connection conns =  MySQLConnection.devconn();
				PreparedStatement pst = conns.prepareStatement(sql);
				ResultSet rs = pst.executeQuery(sql);// 执行sql语句
				System.out.println("---------------------------------------");		
				while (rs.next()) {
				  System.out.println(rs.getString("followerCount"));
				  MenuFollower=rs.getString("followerCount");
				  }	
				conns.close();
				pst.close();
				rs.close();
				 } catch (SQLException e) {
				     System.out.println("数据查询失败");
				     e.printStackTrace();
				 }
		try{
			AssertJUnit.assertEquals(summary_total, MenuFollower);
		}catch(AssertionError e){
			logger.error("MenuTitle segmentation 查询人数不一致，mysql result:"+MenuFollower+"  Segmentation result:"+summary_total);
			e.printStackTrace();
		}
	

         queryString="{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
        + "{\"has_child\":{\"type\":\"user_action_record_menu\","
        + "\"query\":{\"filtered\":{\"filter\":"
        + "{\"bool\":{\"must\":[{\"term\":{\"wid\":"+methods.mid+"}},"
        + "{\"term\":{\"type\":\"menu\"}},"
        + "{\"term\":{\"keyword\":\""+menuTitle+"\"}}]}}}}}}]}}}}}";
        System.out.println(queryString);
        //Sample Query - JSONObject
        //We convert the raw query string to JSONObject to avoid query parser error in Elasticsearch
         queryStringObject = new JSONObject(queryString);
        //Elasticsearch Response
         response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
		 hits = response.getHits();
		 MenuOtherJson=hits.getTotalHits()+"";
		System.out.println("user_action_record_menu 查询的记录数="+hits.getTotalHits());
		
		queryString="{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
			        + "{\"has_child\":{\"type\":\"user_action_record\","
			        + "\"query\":{\"filtered\":{\"filter\":"
			        + "{\"bool\":{\"must\":[{\"term\":{\"wid\":"+methods.mid+"}},"
			        + "{\"term\":{\"type\":\"menu\"}},"
			        + "{\"term\":{\"keyword\":\""+menuTitle+"\"}}]}}}}}}]}}}}}";
		System.out.println(queryString);
			        //Sample Query - JSONObject
			        //We convert the raw query string to JSONObject to avoid query parser error in Elasticsearch
		queryStringObject = new JSONObject(queryString);
        response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
		hits = response.getHits();
		MenuJson=hits.getTotalHits()+"";
		System.out.println("user_action_record 查询的记录数="+hits.getTotalHits());
		try{
			AssertJUnit.assertEquals(MenuJson, MenuOtherJson);
		}catch(AssertionError e){
			logger.error("MenuTitle segmentation 查询人数不一致，user_action_record Json result:"+MenuJson+"  user_action_record_menu result:"+MenuOtherJson);
			e.printStackTrace();
		}					*/
		}
}
