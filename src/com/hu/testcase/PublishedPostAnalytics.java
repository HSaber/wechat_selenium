package com.hu.testcase;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.interactive.testcase.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublishedPostAnalytics {
	WebDriver driver;
	public String FieldTitle;
	public String path;
	private static Logger logger = Logger
			.getLogger(PublishedPostAnalytics.class);

	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		driver = new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test
	public void test() throws Exception {
		methods.navigation(driver, "Published Posts",By.id("postanalyticsGraphic"));
		// 获取时间控件时间
		String StartEnd = driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[1]/div/div[1]/select/option[6]")).getText();
		System.out.println(StartEnd);
		String[] StartEndArr = methods.Getnum(StartEnd);
		for (int i = 0; i < 5; i++) {
			if (StartEndArr[i].length() == 1)
				StartEndArr[i] = "0" + StartEndArr[i];
		}
		String Start = StartEndArr[2] + "-" + StartEndArr[0] + "-"+ StartEndArr[1];
		String End = StartEndArr[5] + "-" + StartEndArr[3] + "-"+ StartEndArr[4];
		System.out.println(Start + " " + End);
		String Start1=StartEndArr[2]+StartEndArr[0]+StartEndArr[1];
		String End1 = StartEndArr[5] + StartEndArr[3]+ StartEndArr[4];
		
		String Sent=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[2]")).getText();
		String[] Sentvs=methods.vsNewOld(Sent);
		System.out.println(Sentvs[0]+" "+Sentvs[1]);
		String SentRate=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[1]/span")).getText();
		
/*		String sqlSent="SELECT count(*) as Sentcount FROM `group_message_record` "
				+ "WHERE mid=33 AND content_type='mpnews' AND message_type IN('tag_messages','segmentation_messages','all_users_messages') "
				+ "AND response='success' AND   created_intdate >='"+Start1+"' AND created_intdate <='"+End1+"'";
		Connection conns=MySQLConnection.devconn();
		 PreparedStatement pst; // 执行Sql语句(Statement)
		 ResultSet rs; // 获取返回结果
		 try {
		     pst = conns.prepareStatement(sqlSent);
		     rs = pst.executeQuery(sqlSent);// 执行sql语句
		     System.out.println("---------------------------------------");
		     System.out.println("Post Sent 个数");
		     while (rs.next()) {
		         System.out.println(rs.getString("Sentcount"));
		     }
		     System.out.println("---------------------------------------");
		     conns.close();
		     pst.close();
		     rs.close();
		 } catch (SQLException e) {
		     System.out.println("数据查询失败");
		     e.printStackTrace();
		 }*/
		
		
	}

}
