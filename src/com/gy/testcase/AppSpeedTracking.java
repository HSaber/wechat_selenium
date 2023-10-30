package com.gy.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class AppSpeedTracking extends TestBase{
	
	 private static Cookie PHPSESSID = null;
	  private static String getEnv(String key, String def) {
	             Map<String, String> env = System.getenv();
	             String val = env.get(key);
	             return val == null ? def : val;
	  }
	  
	 
	  @BeforeMethod
		public  void setUp() throws Exception {
			 
			driver= new ChromeDriver();
	    	
		    driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 设置页面加载超时的最大时长
			login();
		  }
	
	  @AfterMethod
		public  void tearDown() throws Exception {
			
		    driver.close();
		 
		   
		  
		  }
	  
	@Test
	  public void appSpeedTracking() throws Exception {
		//java创建excel
		/*try {
	            // 打开文件
	            WritableWorkbook book = Workbook.createWorkbook(new File("app.xls"));
	            // 生成名为“dev speed tracking”的工作表，参数0表示这是第一页
	            WritableSheet sheet = book.createSheet("dev speed tracking", 0);
	            // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
	            // 以及单元格内容为test
	            Label label = new Label(0, 0, "页面名称");
				// 将定义好的单元格添加到工作表中
	            sheet.addCell(label);
				jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
	            sheet.addCell(number);

	            // 写入数据并关闭文件
	            book.write();
	            book.close();

	        } catch (Exception e) {
	            System.out.println(e);
	        }*/
		//读并更改已经存在的excel
		String master[][] = {{"DevMaster","DevMaster123"},{"StageMaster","StageMaster123"},{"cokemaster","123456"}};
		String superadmin[][] = {{"gysuperadmin","guoyan123"},{"gysuperadmin2","guoyan123"},{"guoyan","guoyan123"}};
		String masteruser = "";
		String masterpassword = "";
		String superadminuser="";
		String superadminpassword="";
		try {
			 String filename="";
			if(baseUrl=="https://dev.jingsocial.com/"){
	            filename = "dev.xls";
	            masteruser=master[0][0];
	            masterpassword=master[0][1];
	            superadminuser=superadmin[0][0];
	            superadminpassword=superadmin[0][1];
	            	
	        }else if(baseUrl=="https://staging.jingsocial.com/"){
	        	filename = "staging.xls";
	        	masteruser=master[1][0];
	            masterpassword=master[1][1];
	            superadminuser=superadmin[1][0];
	            superadminpassword=superadmin[1][1];
	            
	        }else if(baseUrl=="https://app.jingsocial.com/"){
	        	filename = "app.xls";
	        	masteruser=master[2][0];
	            masterpassword=master[2][1];
	            superadminuser=superadmin[2][0];
	            superadminpassword=superadmin[2][1];
	        }
			
				Workbook book = Workbook.getWorkbook(new File(filename));
	            WritableWorkbook book1 = Workbook.createWorkbook(new File(filename),book);
	            Sheet[] sheets = book.getSheets();
	            for (int x = 0; x < sheets.length; x++) {   
	            	   JavascriptExecutor js = (JavascriptExecutor)driver;
	            	  
	            	   WritableSheet sheetw = book1.getSheet(x);
	   	               Sheet sheet = book.getSheet(x);
	            	   //System.out.println(sheets.length);
	   	               String sheetname = sheet.getName();
	   	               System.out.println(sheetname);
	   	               int row=0 ,column= 0; 
	            	   row = sheet.getRows();
			           column = sheet.getColumns();
			           System.out.println(sheetname+"有效行数是："+ row);
			           System.out.println(sheetname+"有效列数是："+ column);
			           
			           	//设置标题字体
		   	            WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);    
		   	            WritableCellFormat headerFormat = new WritableCellFormat (headerFont);
		   	            //标红时间过长的loadtime
		   	            WritableFont font1 = new WritableFont(WritableFont.ARIAL,11,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED);  
		   	            WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
			           
		   	            String time = currentTime();
		   	            sheetw.addCell(new Label(column, 0, "Load Time(" + time +")",headerFormat));
		   	            sheetw.setColumnView(column, 29);
		   	            
		   	           driver.manage().deleteAllCookies();
			           String osName = System.getProperty("os.name");
	   	               
			           if(x==0){
			        	   if(osName.contains("Windows")){
			        		   
			        		   	driver.get(baseUrl);

				    			Cookie cookie = new Cookie("session", "unrudgbif7n3ggvshf0j95qlg2");

				    			driver.manage().addCookie(cookie);
				    			
			        	   }else{
			        		   	
			        		    baseUrl = getEnv("WECHAT_BASEURL", baseUrl);
			    		        driver.get(baseUrl);
			    		        if (PHPSESSID != null)
			    		        {
			    		            driver.manage().addCookie(PHPSESSID);
			    		            driver.get(baseUrl);
			    		        }else{
				    		            driver.get(baseUrl + "Admin/default/login");
				    		            
				    		            driver.findElement(By.name("LoginForm[username]")).sendKeys(getEnv("WECHAT_USER", masteruser));
				    		            driver.findElement(By.name("LoginForm[password]")).sendKeys(getEnv("WECHAT_PASSWORD", masterpassword));
				    		           
				    		            driver.findElement(By.name("LoginForm[verifyCode]")).sendKeys("anything");
				    		            driver.findElement(By.name("yt0")).click();
				    		            //driver.fluentWait(By.id("adf"));
				    		            //Thread.sleep(3000);
				    		            //driver.get(baseUrl);
				    		            for (Iterator<Cookie> i = driver.manage().getCookies().iterator(); i.hasNext(); )
				    		            {
				    		                Cookie c = i.next();
				    		                if (c.getName() == "session")
				    		                {
				    		                    PHPSESSID = c;
				    		                    break;
				    		                }
				    		            }
			    		        	}	
			        	   }
			        	   
			        	   
			        	   long endtimem= (long)js.executeScript("return window.performance.timing.loadEventEnd");
			   	    		long starttimem= (long)js.executeScript("return window.performance.timing.navigationStart");
			   	    		long loadtimem=endtimem-starttimem;
			   	    		float loadtimesm = (float)(Math.round(loadtimem/10))/100;
			   	    		System.out.println(sheetname + "darshboard的加载时间是：" + loadtimesm +"s");
			   	            
			   	    		int h=1;
			   	            if(loadtimesm<5){
			     				sheetw.addCell(new Label(column, h, loadtimesm +"s"));
			   	            }else{
			     				sheetw.addCell(new Label(column, h, loadtimesm +"s",cellFormat1));
			   	            }
	   	            	   
			   	            
			   	         for(h =2;h<row;h++){
			   		            Cell cell1 = sheet.getCell(1, h);
			   		            Cell cell2 = sheet.getCell(0, h);
			   		            String url = cell1.getContents();
			   		            String timeload = cell2.getContents();
			   		            System.out.println(url);
			   		           
			   		            if(url!=""){
			   		            	System.out.println(h);
			   			    		driver.get(url);
			   			    		long endtimem1= (long)js.executeScript("return window.performance.timing.loadEventEnd");
			   			    		long starttimem1= (long)js.executeScript("return window.performance.timing.navigationStart");
			   			    		long loadtimem1=endtimem1-starttimem1;
			   			    		float loadtimesm1 = (float)(Math.round(loadtimem1/10))/100;
			   			    		
			   			    		System.out.println(sheetname + "账号的" + timeload + "的加载时间是：" + loadtimesm1 +"s");
			   			    		if(loadtimesm1<5){
			   			    				sheetw.addCell(new Label(column, h, loadtimesm1 +"s"));
			   			    		}else{
			   			    				sheetw.addCell(new Label(column, h, loadtimesm1 +"s",cellFormat1));
			   			    		}
			   			    		//jxl.write.Number number = new jxl.write.Number(column, i, loadtime);
			   			            //sheetw.addCell(number);
			   			    		Thread.sleep(3000);
			   		            }
			   	            }
			   	         
	   	               }else{
		            	   
		            	   int mid = catchNum("<",">",sheetname);
		            	   System.out.println(mid);
		            	   if(mid==0)Assert.fail("没获取到mid");
		            	   driver.manage().deleteAllCookies();
		            	   // 得到第一列第一行的单元格
		           		  // driver.manage().deleteAllCookies();
		           		   //login(driver);
		           		   
		           		   /*driver.get(baseUrl);
		    			   Cookie cookie = new Cookie("session", "b9542k37d90ms8vi82f8rpl5t6");
		    			   driver.manage().addCookie(cookie);
		    			   driver.get(baseUrl + "Manager/default/index/mid/"+mid);*/
			           		//String osName = System.getProperty("os.name");
			    			if(osName.contains("Windows")){
		
				    			driver.get(baseUrl);

				    			Cookie cookie = new Cookie("session", "thtg5a6k8710bqdiuih7e290p0");

			
				    			driver.manage().addCookie(cookie);
				    			driver.get(baseUrl + "Manager/default/index/mid/"+mid);
			    			}
			    			else{
			    			
			    				baseUrl = getEnv("WECHAT_BASEURL", baseUrl);
			    		        driver.get(baseUrl);
			    		        if (PHPSESSID != null)
			    		        {
			    		            driver.manage().addCookie(PHPSESSID);
			    		            driver.get(baseUrl + "Manager/default/index/mid/"+mid);
			    		        }else{
				    		            driver.get(baseUrl + "Admin/default/login");
				    		            
				    		            
				    		            driver.findElement(By.name("LoginForm[username]")).sendKeys(getEnv("WECHAT_USER", superadminuser));
						    		    driver.findElement(By.name("LoginForm[password]")).sendKeys(getEnv("WECHAT_PASSWORD", superadminpassword));
					    		            
						    		    driver.findElement(By.name("LoginForm[verifyCode]")).sendKeys("anything");
						    		    driver.findElement(By.name("yt0")).click();
				    		            //driver.fluentWait(By.id("adf"));
				    		            Thread.sleep(3000);
				    		            driver.get(baseUrl + "Manager/default/index/mid/"+mid);
				    		            for (Iterator<Cookie> i = driver.manage().getCookies().iterator(); i.hasNext(); )
				    		            {
				    		                Cookie c = i.next();
				    		                if (c.getName() == "session")
				    		                {
				    		                    PHPSESSID = c;
				    		                    break;
				    		                }
				    		            }
			    		        	}	
			    			
			    			}
		           		   
		           		   
		           		   
			   	            long endtime0= (long)js.executeScript("return window.performance.timing.loadEventEnd");
			   	    		long starttime0= (long)js.executeScript("return window.performance.timing.navigationStart");
			   	    		long loadtime0=endtime0-starttime0;
			   	    		float loadtimes0 = (float)(Math.round(loadtime0/10))/100;
			   	    		System.out.println(sheetname + "darshboard的加载时间是：" + loadtimes0 +"s");
			   	            
			   	    		int i=1;
			   	            if(loadtimes0<5){
			     				sheetw.addCell(new Label(column, i, loadtimes0 +"s"));
			   	            }else{
			     				sheetw.addCell(new Label(column, i, loadtimes0 +"s",cellFormat1));
			   	            }
			   	            
			   	            for(i =2;i<row;i++){
			   		            Cell cell1 = sheet.getCell(1, i);
			   		            Cell cell2 = sheet.getCell(0, i);
			   		            String url = cell1.getContents();
			   		            String timeload = cell2.getContents();
			   		            System.out.println(url);
			   		           
			   		            if(url!=""){
			   		            	System.out.println(i);
			   			    		driver.get(url);
			   			    		long endtime= (long)js.executeScript("return window.performance.timing.loadEventEnd");
			   			    		long starttime= (long)js.executeScript("return window.performance.timing.navigationStart");
			   			    		long loadtime=endtime-starttime;
			   			    		float loadtimes = (float)(Math.round(loadtime/10))/100;
			   			    		if(i==2){
			   			    			System.out.println("segmentation preview加载完毕之前页面加载的时间是： " + loadtimes);
			   			    			Date start=new Date();
			   			    			String preview="";
			   			    			while(true){
				   					    	boolean b = true;
				   					    	try{
				   					    		preview=driver.findElement(By.cssSelector("div.summary")).getText();
				   					    	}catch(org.openqa.selenium.NoSuchElementException e){
				   					    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.NoSuchElementException e");
				   					    		 b=false;
				   					    	}
				   					    	if(b)
				   					    		break;
			   			    			}
			   			    		
			   			    			
			   			    		while(preview.equals("")){
			   			    				preview=driver.findElement(By.cssSelector("div.summary")).getText();
			   			    				System.out.println("segmentation preview没加载完成,继续等待中！");
			   			    		}
			   			    			
			   			    			Date end=new Date();
			   			    			long extra = end.getTime() - start.getTime();
			   			    			float extratime = (float)(Math.round(extra/10))/100;
			   			    			System.out.println("segmentation preivew加载的extratime是： " + extratime);
			   			    			loadtimes = loadtimes + extratime;
			   			    		}
			   			    		System.out.println(sheetname + "账号的" + timeload + "的加载时间是：" + loadtimes +"s");
			   			    		if(loadtimes<5){
			   			    				sheetw.addCell(new Label(column, i, loadtimes +"s"));
			   			    		}else{
			   			    				sheetw.addCell(new Label(column, i, loadtimes +"s",cellFormat1));
			   			    		}
			   			    		//jxl.write.Number number = new jxl.write.Number(column, i, loadtime);
			   			            //sheetw.addCell(number);
			   			    		Thread.sleep(3000);
			   		            }
			   	            }
	   	         
	   	               }
	            }           
	            book1.write();
	            book.close();
   	            book1.close();
	        } catch (Exception e) {
	        	
	            System.out.println(e);
	            Assert.fail("speed traking失败");
	        }
	 }
}
