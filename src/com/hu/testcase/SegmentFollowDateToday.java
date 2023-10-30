package com.hu.testcase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.interactive.testcase.MySQLConnection;

public class SegmentFollowDateToday {
	 WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	 private static Logger logger = Logger.getLogger(SegmentFollowDateToday.class);
	 
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
	public void test() throws InterruptedException,Exception {
		//poll,formevent
		String timeStr=methods.timeDate();
		methods.navigation(driver, "Segmentation", By.className("btn"));
		
		try{
			driver.findElement(By.className("btn")).click();
			while(true)
			 {
				if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
					break;
			 }
			AssertJUnit.assertEquals("Create a Segment",driver.findElement(By.className("page_title")).getText());
			System.out.println("Open create segmentation page successfully!");
		}catch(Exception e){
			 System.out.println("Fail to open create segmentation page!");
			 logger.error("Fail to open create segmentation page!");
		}
		    
		    //FollowDate
		   
		    driver.findElement(By.id("dynamicRules_name")).clear();
		    driver.findElement(By.id("dynamicRules_name")).sendKeys("FollowerDate"+timeStr);
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"));
		    
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");	   
		    methods.Loading(driver, By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]/div/select"));
		  
		    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]/div/select"))).selectByVisibleText("Follows");
		    methods.Loading(driver, By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]/div/select[2]"));
		  
		    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]/div/select[2]"))).selectByVisibleText("Date");
		    methods.Loading(driver, By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]/div/select[3]"));
	
		    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[1]/div[2]/div/select[3]"))).selectByVisibleText("equals");
		 
		   driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).click();
		   methods.Loading(driver, By.className("datepicker"));
		   driver.findElement(By.className("today")).click();

            Thread.sleep(500);
		    driver.findElement(By.linkText("Preview")).click();
		    while(true){
		    	Thread.sleep(500);
		    	if(methods.isElementPresent(driver, By.className("summary")))
		    		break;
		    	System.out.println("Preview...");
		    }
		    
			String summary=driver.findElement(By.className("summary")).getText();
			String summary_total=methods.Getnum(summary)[2];
			System.out.println(summary+"   "+summary_total+"=================================================================");	
			
		    driver.findElement(By.name("yt1")).click();
		    while(true)
		    {		    	
		    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
		    	{
		    	    System.out.println("Segmentation 创建成功!");
		    	     break;
		    	}
		    }
	//待解放	
			
	/*		//通过mysql拿到tag的id
			Connection conns; // 获取连接
			PreparedStatement pst; // 执行Sql语句(Statement)
			ResultSet rs; // 获取返回结果
			String followerCount = null;

					
			long l = System.currentTimeMillis();
			Date d = new Date(l);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fulltime = sdf.format(d);
			String sqlStr="SELECT count(*) as followerCount FROM `wechat_customer` where mid="+methods.mid+" and subscribe=1 and subscribe_intdate='"+fulltime+"'";	
			try {
					conns =  MySQLConnection.devconn();
					pst = conns.prepareStatement(sqlStr);
					rs = pst.executeQuery(sqlStr);// 执行sql语句
					System.out.println("---------------------------------------");		
					while (rs.next()) {
					   System.out.println(rs.getString("followerCount"));
					   followerCount=rs.getString("followerCount");
					     }		  
					followerCount="1";
					conns.close();
					pst.close();
					rs.close();
					 } catch (SQLException e) {
					     System.out.println("数据查询失败");
					     e.printStackTrace();
					 }
			try{
				AssertJUnit.assertEquals(summary_total, followerCount);
			}catch(AssertionError e){
				logger.error("FollowDate=today segmentation 查询人数不一致，mysql result:"+followerCount+"  Segmentation result:"+summary_total);
				e.printStackTrace();
			}
			*/
	}

}
