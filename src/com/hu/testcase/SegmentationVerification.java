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

public class SegmentationVerification {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SegmentationVerification.class);
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
			     //      System.out.println(hits.getHits()[i].getSourceAsString());}
			
			//Action amount >1-------------------------------------------------------------------------------------------------
			driver.navigate().refresh();
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
			summary=driver.findElement(By.className("summary")).getText();
			summary_total=methods.Getnum(summary)[2];
			System.out.println(summary+"   "+summary_total+"=================================================================");
			queryString="{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
				        + "{\"has_child\":{\"type\":\"followers_actions_date_index\","
				        + "\"query\":{\"filtered\":{\"filter\":"
				        + "{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},"
				        + "{\"range\":{\"total_number\":{\"gte\":"+1000+"}}}]}}}}}}]}}}},"
				        +"\"aggs\":{\"group_openid\":{\"terms\":{\"field\":\"openid\"}}}}";
			            System.out.println(queryString);
				        //Sample Query - JSONObject
				        //We convert the raw query string to JSONObject to avoid query parser error in Elasticsearch
				        queryStringObject = new JSONObject(queryString);

				        //Elasticsearch Response
				        response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
						hits = response.getHits();
						String actionAmount=hits.getTotalHits()+"";
						System.out.println("查询的记录数="+hits.getTotalHits());
					try{
						AssertJUnit.assertEquals(summary_total, actionAmount);
					}catch(AssertionError e){
						logger.error("Action amount segmentation 查询人数不一致，Json result:"+actionAmount+"  Segmentation result:"+summary_total);
						e.printStackTrace();
					}
			
			//Old Tag Count >1-------------------------------------------------------------------------------------------------
			driver.navigate().refresh();
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
			summary=driver.findElement(By.className("summary")).getText();
			summary_total=methods.Getnum(summary)[2];
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
			queryString="{\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"mid\":"+methods.mid+"}},{\"term\":{\"subscribe\":\"1\"}},"
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
		        queryStringObject = new JSONObject(queryString);

		        //Elasticsearch Response
		        response = responsebuilder.setSource(queryStringObject.toString()).execute().actionGet();			
				hits = response.getHits();
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
			
			//Custom field have value -------------------------------------------------------------------------------------------------
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
			summary=driver.findElement(By.className("summary")).getText();
			summary_total=methods.Getnum(summary)[2];
			System.out.println(summary+"   "+summary_total+"=================================================================");	
		
			
			String CustomFieldFollower = null;
			String sql="SELECT count(DISTINCT(openid)) as followerCount FROM `follower_eav_attribute_text` where  attribute_id="+attribute_id+""
					+ " and openid in (SELECT openid FROM wechat_customer where mid="+methods.mid+" and subscribe=1);";						
			try {
					conns =  MySQLConnection.devconn();
				    pst = conns.prepareStatement(sql);
					rs = pst.executeQuery(sql);// 执行sql语句
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
			driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[4]")).sendKeys("13");
			
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
		
			
			
		}
}
