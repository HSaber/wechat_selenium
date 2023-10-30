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

public class SegmentationVerificationTag {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SegmentationVerificationTag.class);
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
	
			//Old Tag Count >1-------------------------------------------------------------------------------------------------
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Tag");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("Name");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).selectByVisibleText("Any Action Trigger");
			String tagID=new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).getFirstSelectedOption().getAttribute("value");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[5]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[6]"))).selectByVisibleText("greater than");
			driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys("0");
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
		
			//通过mysql拿到tag的id
			Connection conns; // 获取连接
			PreparedStatement pst; // 执行Sql语句(Statement)
			ResultSet rs; // 获取返回结果
		
			String tagFollower = null;
			String sqlTagFollower="SELECT count(DISTINCT(openid)) as TagFollower FROM `wechat_customer_tag` "
			 		+ "where mid="+methods.mid+" AND tag_id="+tagID+" And status=1 AND openid in (SELECT openid FROM wechat_customer WHERE mid="+methods.mid+" and subscribe=1);";
	
			try {
					conns =  MySQLConnection.devconn();
				    pst = conns.prepareStatement(sqlTagFollower);
					rs = pst.executeQuery(sqlTagFollower);// 执行sql语句
					System.out.println("---------------------------------------");		
					while (rs.next()) {
					  System.out.println(rs.getString("TagFollower"));
					  tagFollower=rs.getString("TagFollower");
					  }	
					conns.close();
					pst.close();
					rs.close();
					 } catch (SQLException e) {
					     System.out.println("数据查询失败");
					     e.printStackTrace();
					 }
			try{
				AssertJUnit.assertEquals(summary_total, tagFollower);
			}catch(AssertionError e){
				logger.error("Old Tag amount segmentation 查询人数不一致，mysql result:"+tagFollower+"  Segmentation result:"+summary_total);
				e.printStackTrace();
			}
			methods func=new methods();
			SearchRequestBuilder responsebuilder =func.esConnect("wechat_customer");
	      
			String queryString="{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
			        + "{\"has_child\":{\"type\":\"wechat_customer_tag\","
			        + "\"query\":{\"filtered\":{\"filter\":"
			        + "{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},"
			        + "{\"term\":{\"tag_id_integer\":"+tagID+"}},"
			        		+ "{\"term\":{\"status\":1}}]"
			        		+ "}}}}}}]}}}},"
			        +"\"aggs\":{\"group_openid\":{\"terms\":{\"field\":\"openid\"}}}}"; 
			   System.out.println(queryString);
		        //Sample Query - JSONObject
		        //We convert the raw query string to JSONObject to avoid query parser error in Elasticsearch
				JSONObject queryStringObject = new JSONObject(queryString);
		        //Elasticsearch Response
				SearchResponse response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
				SearchHits hits = response.getHits();
				String tagAmountJson=hits.getTotalHits()+"";
				System.out.println("查询的记录数="+hits.getTotalHits());
			try{
				AssertJUnit.assertEquals(summary_total, tagAmountJson);
			}catch(AssertionError e){
				logger.error("Old Tag amount segmentation 查询人数不一致，Json result:"+tagAmountJson+"  Segmentation result:"+summary_total);
				e.printStackTrace();
			}	 
			
			//new tag Count >0-------------------------------------------------------------------------------------------------
			methods.navigation(driver, "Tags", By.name("Tags[name]"));
			
			String tagNum,newtag;
			for(int i=1;;i++)
			{
				tagNum=driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr["+i+"]/td[5]/a")).getText();
				if(!tagNum.equals("0"))
				{
					newtag=driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr["+i+"]/td[4]")).getText();
					break;
				}
			}
			
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
			
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Tag");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("Name");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).selectByVisibleText(newtag);
			String newtagID=new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[4]"))).getFirstSelectedOption().getAttribute("value");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[5]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[6]"))).selectByVisibleText("greater than");
			driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys("0");
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
		
			//通过mysql拿到tag的id

			String newtagFollower = null;
			String sqlnewTagFollower="SELECT count(DISTINCT(openid)) as TagFollower FROM `wechat_customer_tag` "
			 		+ "where mid="+methods.mid+" AND tag_id="+newtagID+" And status=1 AND openid in (SELECT openid FROM wechat_customer WHERE mid="+methods.mid+" and subscribe=1)";	
			try {
					conns =  MySQLConnection.devconn();				
					pst = conns.prepareStatement(sqlnewTagFollower);
					rs = pst.executeQuery(sqlnewTagFollower);// 执行sql语句
					System.out.println("---------------------------------------");		
					while (rs.next()) {
					  System.out.println(rs.getString("TagFollower"));
					  newtagFollower=rs.getString("TagFollower");
					  }	
					conns.close();
					pst.close();
					rs.close();
					 } catch (SQLException e) {
					     System.out.println("数据查询失败");
					     e.printStackTrace();
					 }
			try{
				AssertJUnit.assertEquals(summary_total, newtagFollower);
			}catch(AssertionError e){
				logger.error("New Tag amount segmentation 查询人数不一致，mysql result:"+newtagFollower+"  Segmentation result:"+summary_total);
				e.printStackTrace();
			}
			queryString="{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
			        + "{\"has_child\":{\"type\":\"wechat_customer_tag\","
			        + "\"query\":{\"filtered\":{\"filter\":"
			        + "{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},"
			        + "{\"term\":{\"tag_id_integer\":"+newtagID+"}},"
			        		+ "{\"term\":{\"status\":1}}]"
			        		+ "}}}}}}]}}}},"
			        +"\"aggs\":{\"group_openid\":{\"terms\":{\"field\":\"openid\"}}}}"; 
			   System.out.println(queryString);
		        //Sample Query - JSONObject
		        //We convert the raw query string to JSONObject to avoid query parser error in Elasticsearch
		        queryStringObject = new JSONObject(queryString);

		        //Elasticsearch Response
		        response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
				hits = response.getHits();
				String newtagAmountJson=hits.getTotalHits()+"";
				System.out.println("查询的记录数="+hits.getTotalHits());
			try{
				AssertJUnit.assertEquals(summary_total, newtagAmountJson);
			}catch(AssertionError e){
				logger.error("New Tag amount segmentation 查询人数不一致，Json result:"+newtagAmountJson+"  Segmentation result:"+summary_total);
				e.printStackTrace();
			}	 
		
		}
}
