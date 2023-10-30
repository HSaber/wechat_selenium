package com.hu.testcase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class DashboardComparison {
	private static Logger logger = Logger.getLogger(DashboardComparison.class);
	WebDriver driver;
	public boolean flag = true;
	private static Cookie PHPSESSID = null;

	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		driver = new ChromeDriver();
	}

	@AfterClass
	public void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test
	public void test() throws Exception {
		String filename;
		String baseUrl = methods.baseUrl;
		if (baseUrl.contains("dev"))
			filename = "Dev-DashboardComparison.xls";
		else if (baseUrl.contains("staging"))
			filename = "Stage-DashboardComparison.xls";
		else
			filename = "App-DashboardComparison.xls";

		Workbook book = Workbook.getWorkbook(new File(filename));
		WritableWorkbook book1 = Workbook.createWorkbook(new File(filename),book);
		Sheet[] sheets = book.getSheets();
		for (int x = 0; x < sheets.length; x++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WritableSheet sheetw = book1.getSheet(x);
			Sheet sheet = book.getSheet(x);
			 System.out.println("sheets.length"+sheets.length);
			String sheetname = sheet.getName();
			System.out.println("sheetname"+sheetname);
			int row = 0, column = 0;
			row = sheet.getRows();
			column = sheet.getColumns();
			System.out.println(sheetname + "有效行数是：" + row);
			System.out.println(sheetname + "有效列数是：" + column);

			// 设置标题字体
			WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 11,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
			WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
			// 标红时间过长的loadtime
			WritableFont font1 = new WritableFont(WritableFont.ARIAL, 11,WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);

			String osName = System.getProperty("os.name");

			int mid = methods.catchNum("<", ">", sheetname);
			System.out.println(mid);
			if (mid == 0)
				Assert.fail("没获取到mid");
			if (osName.contains("Windows")) {
				driver.manage().window().maximize();
				driver.get(methods.baseUrl);
				Cookie cookie = new Cookie("session","kj01kbnvmhlfd48itg25ng9dr3");
				driver.manage().addCookie(cookie);
				driver.get(methods.baseUrl + "Manager/default/index/mid/" + mid);
			} else {
				baseUrl = methods.getEnv("WECHAT_BASEURL", baseUrl);
				driver.manage().window().maximize();
				driver.get(baseUrl);
				if (PHPSESSID != null) {
					driver.manage().addCookie(PHPSESSID);
					driver.get(baseUrl + "Manager/default/index/mid/" + mid);
				} else {
					driver.get(baseUrl + "Admin/default/login");
					driver.findElement(By.name("LoginForm[username]")).sendKeys(methods.getEnv("WECHAT_USER", "admin"));
					driver.findElement(By.name("LoginForm[password]")).sendKeys(methods.getEnv("WECHAT_PASSWORD","WFlYna85"));
					driver.findElement(By.name("LoginForm[verifyCode]")).sendKeys("anything");
					driver.findElement(By.name("yt0")).click();
					Thread.sleep(3000);
					driver.get(baseUrl + "Manager/default/index/mid/" + mid);
					for (Iterator<Cookie> k = driver.manage().getCookies()
							.iterator(); k.hasNext();) {
						Cookie c = k.next();
						if (c.getName() == "session") {
							PHPSESSID = c;
							break;
						}
					}
				}

			}
			try{
				while(true){
					if(methods.isElementPresent(driver,By.id("followerEngagementChart")))
						break;
				}
				System.out.println("Open Dashboard page successfully!");
			}
			catch (Exception e){
				System.out.println("fail to open Dashboard page!");
				logger.error("fail to open Dashboard page!");
			}
			
			 //获取格式化对象
			   NumberFormat nt = NumberFormat.getPercentInstance();			   
			   NumberFormat nf = NumberFormat.getPercentInstance();
			   NumberFormat no = NumberFormat.getPercentInstance();
			   //设置百分数精确度2即保留两位小数
			   no.setMinimumFractionDigits(1);
			   nt.setMinimumFractionDigits(2);
			   nf.setMinimumFractionDigits(3);
			   String summary;
			// 获取时间控件时间
			String StartEnd = driver.findElement(By.xpath(".//*[@id='dateContainer']/div/select/option[6]")).getText();
			System.out.println(StartEnd);
			String[] StartEndArr=methods.Getnum(StartEnd);
			for(int i=0;i<5;i++){
			if(StartEndArr[i].length()==1)
				StartEndArr[i]="0"+StartEndArr[i];
			}
			String Start=StartEndArr[2]+"-"+StartEndArr[0]+"-"+StartEndArr[1];
			String End=StartEndArr[5]+"-"+StartEndArr[3]+"-"+StartEndArr[4];
			System.out.println(Start+" "+End);
			//设置表头日期
			sheetw.addCell(new Label(column, 0, "Diff TimeZones(" + StartEnd + ")",headerFormat));
			sheetw.setColumnView(column, 25);
			//dashboard follow growth
			DashboardFollowDataCompare followData=new DashboardFollowDataCompare();
			String[] followDiff=followData.FollowDataComparison(mid+"",driver, Start, End);
			for(int i=0;i<followDiff.length;i++)
				sheetw.addCell(new Label(column, i+1,followDiff[i]));

			//dashboard followdata 采集-------------------------------------------------------------------------------

			DashboardBehaviorCompare behaviorDiff=new DashboardBehaviorCompare();
			sheetw.addCell(new Label(column, 4, behaviorDiff.BehaviorComparison(driver, Start, End))); 
			//**************************************************填充excel****************************************************************
			
			//Dashboard Top Menu Link--------------------------------------------------------------------------------------------------------
		
			int menuExcel=5;
			DashboardMenuCompare menuCompare=new DashboardMenuCompare();
			String[][] MenuDiff=menuCompare.MenuCompare(driver, Start, End);
			if(MenuDiff[0][0].equals("NoTopMenu"))
				sheetw.addCell(new Label(column, menuExcel, "NoTopMenu"));
			else
			{
         for (int i = 0; i < MenuDiff.length; i++) {      				
	            for (int j = 0; j < MenuDiff[i].length; j++) {   
	            //循环遍历数组中的每个元素                      
	                //初始化数组内容       
	            	
	            	if(!(MenuDiff[i][j] == null || MenuDiff[i][j].length() <= 0))	            	
	                System.out.print("MenuDiff["+i+"]["+j+"]="+MenuDiff[i][j]+"  ");	  
	            	else
	            		MenuDiff[i][j]="NoData";
	            	sheetw.addCell(new Label(column, menuExcel++, MenuDiff[i][j]));
	            	//**************************************************填充excel****************************************************************
	                //将数组中的元素输出  
	            }  
	            System.out.println("   ");                	            	            
	            }
			}
			
			
			
			//Dashboard Top KOL --------------------------------------------------------------------------------
			
		/*	DashboardKOLCompare kolCompare=new DashboardKOLCompare();
			String[][] KOLDiff=kolCompare.KOLCompare(driver, Start, End);
		int KolExcel=15;
		for (int i = 0; i < KOLDiff.length; i++) {      
            for (int j = 0; j < KOLDiff[i].length; j++) {   
            //循环遍历数组中的每个元素                      
                //初始化数组内容       
            	if(!(KOLDiff[i][j] == null || KOLDiff[i][j].length() <= 0))	            	
                System.out.print("KOLDiff["+i+"]["+j+"]="+KOLDiff[i][j]+"  ");	 
            	else
            		KOLDiff[i][j]="NoData";
             	sheetw.addCell(new Label(column, KolExcel++, KOLDiff[i][j]));
            	//**************************************************填充excel****************************************************************
                //将数组中的元素输出  
            }  
            System.out.println("   ");                	            	            
            }*/
		
		
		
		//Dashboard follower source 数据采集---------------------------------------------------------------------------
			
		DashboardFollowerSourceCompare followersourceCompare = new DashboardFollowerSourceCompare();
		String[][] FollowSourceDiff=followersourceCompare.FollowerSourceCompare(driver, Start, End,StartEnd);
			int SourceExcel=45;
			for (int i = 0; i < FollowSourceDiff.length; i++) {      
	            for (int j = 0; j < FollowSourceDiff[i].length; j++) {   
	            //循环遍历数组中的每个元素                      
	                //初始化数组内容             	
	            	if(!(FollowSourceDiff[i][j] == null || FollowSourceDiff[i][j].length() <= 0))
	                System.out.print("FollowSourceDiff["+i+"]["+j+"]="+FollowSourceDiff[i][j]+"  ");    
	            	else
	            		FollowSourceDiff[i][j]="NoData";
	            	sheetw.addCell(new Label(column, SourceExcel++, FollowSourceDiff[i][j]));
	            	//**************************************************填充excel****************************************************************
	                //将数组中的元素输出  
	            }  
	            System.out.println("   ");                	            	            
		}	
	
		
		//dashboard post分析 采集数据-------------------------------------------------------------------------
			int PostExcel=63;	
			DashboardPostCompare postCompare=new DashboardPostCompare();
			String[] postDiff=postCompare.PostComparison(driver, Start, End);
			for(int i=0;i<postDiff.length;i++)
		sheetw.addCell(new Label(column, PostExcel++, postDiff[i]));

    	//**************************************************填充excel****************************************************************
			


			/*for (i = 2; i < row; i++) {
				Cell cell1 = sheet.getCell(1, i);
				Cell cell2 = sheet.getCell(0, i);
				String url = cell1.getContents();
				String timeload = cell2.getContents();
				System.out.println(url);

			}*/

		}
		book1.write();
		book.close();
		book1.close();
	}
}
