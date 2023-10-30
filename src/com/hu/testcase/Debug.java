package com.hu.testcase;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;



import java.util.Date;
import java.util.List;

import jxl.write.Label;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.interactive.testcase.MySQLConnection;


public class Debug {
	WebDriver driver;
	private static Logger logger = Logger.getLogger(Debug.class);

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
		//new tag Count >0-------------------------------------------------------------------------------------------------
	
		

	
		List<WebElement> e_row=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody")).findElements(By.tagName("tr"));
		System.out.println(e_row.size());
	}
}
