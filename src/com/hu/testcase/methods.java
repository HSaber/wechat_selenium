package com.hu.testcase;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.floragunn.searchguard.ssl.SearchGuardSSLPlugin;
import com.interactive.testcase.MySQLConnection;

public class methods {
	private static Cookie session = null;
	public static String Osname;
	public static String TitleDate, TitleDate1, TitleDate2, TitleDate3,
			TitleDate4, Calendarday,fulltime;
	public static int i = 0, j = 0, q = 0, s = 0, f = 0,h=0;
	public static boolean acceptNextAlert = true;
	public static String baseUrl= getEnv("WECHAT_BASEURL", "https://app.jingsocial.com/");//shareinstance1
	public static String mid="59";//1000000
	public static Logger logger = Logger.getLogger("sgg");
	public static String[] UrlLink=new String[2];
	public static String[] CData=new String[3];
	public static String[][] TagNumArr=new String[18][2];
	public static int[] TriggerCountArr=new int[13];
	public static String[] nicknameArr={"H.'","Candy预定"};
	public   String triggerStr;
    public static int Menunum;
    public static  Client client=null;
    public static String[] pollopenid={"oOuXGt2jpzTICKIy0diWk4tEV3Hs", //33 dev all candy account
    	                               "oKNc1w0U-rnPA-UehvwUUocv5r1Q",//51 dev
    	                               "ohaIcwErMezrhPd7Rj241W5UwMbw",//59 staging 
                                       "oh_Dsw53x3jfiVugSE-otzo1kkRI",//68 staging
                                       "oleSFv_TQfBWb7Lu7FpYfXJg0ssU",//73 staging
                                       "oqKzat5VhT24MInIF7JwTU2hz2m8",//42 app
                                       "oNrb6vp2EBq4XWya3S7r-h1sOiks"};//61 app
	 //获取格式化对象
       static NumberFormat nt = NumberFormat.getPercentInstance();			   
	   static NumberFormat nf = NumberFormat.getPercentInstance();
	   static NumberFormat no = NumberFormat.getPercentInstance();
	/*   //设置百分数精确度2即保留两位小数
	   no.setMinimumFractionDigits(1);
	   nt.setMinimumFractionDigits(2);
	   nf.setMinimumFractionDigits(3);*/
	   
	public static String getEnv(String key, String def) {
		Map<String, String> env = System.getenv();
		String val = env.get(key);
		return val == null ? def : val;
	}
	


	// login function
	public static void account_login(WebDriver driver) throws Exception {
	
		// DesiredCapabilities dc = DesiredCapabilities.chrome(); //
		// 设置需要驱动的浏览器，其他的浏览器都是以此类推
		// driver = new RemoteWebDriver(new
		// URL("http://101.200.159.97:4446/wd/hub"), dc);

		driver.manage().window().maximize();
		driver.get(baseUrl);

		System.out.println("运行系统为：" + System.getProperty("os.name"));
		Osname = System.getProperty("os.name");
		if (Osname.contains("Windows")) {
			//dev----------------
			if(baseUrl.contains("dev")){
			Cookie cookie = new Cookie("session", "dibvq436fcaoppvc6af033pnc0");
			driver.manage().addCookie(cookie);
			driver.get(baseUrl + "Manager/default/index/mid/"+mid);
			}
			else if(baseUrl.contains("staging")){
			//staging---------
				Cookie cookie = new Cookie("session", "ljo4u89q7no6045imf23gdvm96");
				driver.manage().addCookie(cookie);
				driver.get(baseUrl + "Manager/default/index/mid/"+mid);
			}else{
			//app----------------
			Cookie cookie = new Cookie("session", "nkbld1fhrtgd1rreocjh3go600");
			driver.manage().addCookie(cookie);
			driver.get(baseUrl + "Manager/default/index/mid/"+mid);
			}
		} else if (session != null) {
			driver.manage().addCookie(session);
			driver.get(baseUrl + "Manager/default/index/category/dashboard");
			methods.Loading(driver, By.className("dashboard"));
		} else {
			driver.findElement(By.id("LoginForm_username")).clear();
			driver.findElement(By.id("LoginForm_username")).sendKeys("admin");
			driver.findElement(By.id("LoginForm_password")).clear();
			driver.findElement(By.id("LoginForm_password")).sendKeys("WFlYna85");
			driver.findElement(By.id("LoginForm_verifyCode")).sendKeys("test");
			driver.findElement(By.xpath(".//*[@id='login-form']/table/tbody/tr[10]/td[2]/div/input")).click();
			Thread.sleep(2000);
			driver.get(baseUrl + "/Manager/default/index/mid/59");
			for (Iterator<Cookie> i = driver.manage().getCookies().iterator(); i.hasNext();) {
				Cookie c = i.next();
				if (c.getName() == "session") {
					session = c;
					break;
				}
			}
		}

	}
	 //====================================  es connect	 ========================================================
	public   SearchRequestBuilder esConnect(String tablename) throws Exception {
		   // client startup
		String parentPath = getClass().getResource("../../../").getFile().toString();
	//	String parentPath1 = parentPath + "/material/32.JPG";
		//String es[] = info();
		File f= new File(parentPath);
		System.out.println(f);
		 Client client = null;   
		 try {
	        	// on startup，设置集群名字
	        	Settings settings = Settings.settingsBuilder()
	        			.put("path.home", ".")
	        	        .put("cluster.name","elasticsearch")
	        	        .put("client.transport.sniff", true)
	        	        .put("searchguard.ssl.transport.enabled", true)
	        	        .put("searchguard.ssl.transport.keystore_type", "JKS")
	        	        .put("searchguard.ssl.transport.keystore_password", "a4Frs9dtgx92119De")
	        	        .put("searchguard.ssl.transport.keystore_filepath", f.toString()+"/plugins/search-guard-2/sgconfig/keystore.jks")
	        	        .put("searchguard.ssl.transport.truststore_type", "JKS")
	        	        .put("searchguard.ssl.transport.truststore_password", "a4Frs9dtgx92119De")
	        	        .put("searchguard.ssl.transport.truststore_filepath", f.toString()+"/plugins/search-guard-2/sgconfig/truststore.jks")
	        	        .put("searchguard.ssl.transport.enforce_hostname_verification", false)
	        	        .build();
	        	
	        	//初始化连接客户端new InetSocketAddress("121.43.173.46",6999)http://121.43.112.184
	        	client =TransportClient.builder().settings(settings).addPlugin(SearchGuardSSLPlugin.class).build()
	        					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("121.43.112.184"), 9300));
	        	
	        	// on shutdown
	        	//client.close();
	     } catch (Exception e){
	    	 
	            e.printStackTrace();
	        }
		 //建立查询请求
		 SearchRequestBuilder responsebuilder = client.prepareSearch("dev_jingsocial_33_2017_5_10").setTypes(tablename);
		 return responsebuilder;
	} 
	
	//==================================================================================
	public static void Loading(WebDriver driver,By by) throws Exception{
		while(true)
		{
			Thread.sleep(500);
			if(isElementPresent(driver, by))
				break;
			System.out.println("元素加载ing");
		}
	}
	
	public static void navigation(WebDriver driver,String navigation,By verify) throws Exception{
		String First="" , Second="" ,Third="",Forth="";
		if(navigation.equals("Dashboard"))
		{
			First="Analytics";
		}
		if(navigation.equals("Poll"))
		{
			First="Engagement";
			Second="Modules";
		}
		if(navigation.equals("Segmentation"))
		{
			First="Followers";
		}
		if(navigation.equals("Custom Data Call Back") || navigation.equals("Custom Data Report"))
		{
			First="Analytics";
			Second="Behavior Analytics";
			Third="Custom Data";
		}
		if(navigation.equals("Custom Field"))
		{
			First="Setup";
			Second="Follower Custom Field";
		}
		if(navigation.equals("Followers"))
		{
			First="Followers";
		}
		if(navigation.equals("Tags"))
		{
			First="Setup";
			Second="Tags";
		}
		if(navigation.equals("Form Events"))
		{
			First="Engagement";
			Second="Modules";
			Third="Events";
		}
		if(navigation.equals("HTML5 Pages"))
		{
			First="Engagement";
			Second="Modules";
		}
		if(navigation.equals("Menu"))
		{
			First="Engagement";
			Second="Menu";
		}
		if(navigation.equals("Menus")){
			First="Engagement";
			Second="Menu";
			Third="All Menus";
		}
		if(navigation.equals("Published Posts"))
		{
			First="Analytics";
			Second="Post Analytics";
		}
		if(navigation.equals("QR Codes"))
		{
			First="Engagement";
		}
		if(navigation.equals("Surveys"))
		{
			First="Engagement";
			Second="Modules";
		}
		if(navigation.equals("Triggers"))
		{
			First="Engagement";
		}
		if(navigation.equals("Published Posts"))
		{
			First="Analytics";
			Second="Post Analytics";
		}
		driver.findElement(By.linkText(First)).click();
		 if(!Second.isEmpty())
			 driver.findElement(By.linkText(Second)).click();
		 if(!Third.isEmpty())
			 driver.findElement(By.linkText(Third)).click();
		 if(!Forth.isEmpty())
			 driver.findElement(By.linkText(Forth)).click();

		 try{
		 if(navigation.equals("Tags"))
			 driver.findElement(By.xpath(".//*[@id='dada']/div[2]/div/div/div[2]/ul[6]/li/ul/li[7]/ul/li[2]/a")).click();			
		 else if(navigation.equals("Menu"))	
		     driver.findElement(By.xpath(".//*[@id='dada']/div[2]/div/div/div[2]/ul[4]/li/ul/li[3]/ul/li[1]/a")).click();
		 else if(navigation.equals("Followers"))
			 driver.findElement(By.xpath(".//*[@id='dada']/div[2]/div/div/div[2]/ul[2]/li/ul/li[1]/a")).click();
		 else
			driver.findElement(By.linkText(navigation)).click();
		 Thread.sleep(500);
			while(true){
				Thread.sleep(500);
				if(methods.isElementPresent(driver,verify))
					break;
			}
			System.out.println("Open "+navigation+" page successfully!");
		} catch (Exception e) {
			System.out.println("Fail to open "+navigation+" page");
		
	}
	}
	
	// vs 前后数据取值
	public static String[] vsNewOld(String Content){
		 no.setMinimumFractionDigits(1);
		String[] NewOldArr=new String[2];
		if(Content.contains("%"))
		{
			if(Content.contains("-- vs"))
				NewOldArr[0]="0";
			else
				NewOldArr[0]=Content.substring(0,Content.indexOf("v"));
			
			if(Content.contains("vs --"))
				NewOldArr[1]="0";
			else
				NewOldArr[1]=Content.substring(Content.indexOf("s")+1);
			
			NewOldArr[0]= NewOldArr[0].replaceAll(" ", "");
			if(!NewOldArr[0].equals("0"))
				NewOldArr[0]=no.format(Float.parseFloat((NewOldArr[0].substring(0,NewOldArr[0].indexOf("%"))))/100);
						
			NewOldArr[1]=NewOldArr[1].replaceAll(" ", "");
			if(!NewOldArr[1].equals("0"))		
				NewOldArr[1]=no.format(Float.parseFloat((NewOldArr[1].substring(0,NewOldArr[1].indexOf("%"))))/100);
			return NewOldArr;
		}
		else
		{
		if(Content.contains("vs --"))
			NewOldArr[1]="0";
		else
			NewOldArr[1]=Content.substring(Content.indexOf("s")+1);

		if(Content.contains("-- vs"))
			NewOldArr[0]="0";
		else
			NewOldArr[0]=Content.substring(0,Content.indexOf("v"));
	
		NewOldArr[0]= NewOldArr[0].replaceAll(" ", "");
		NewOldArr[1]=NewOldArr[1].replaceAll(" ", "");
		return NewOldArr;
		}
	}
	
// vs 
	//compare
	public static String Comparison(String current,String old){
		float a = 0,b = 0;	
		nt.setMinimumFractionDigits(2);
		if(current.equals("0") || current.equals("0%"))
			return ("current="+current+" old="+old);		
		else
			{
			if(current.contains("%"))			
			 a=(Float.parseFloat((current.substring(0,current.indexOf("%"))))/100);
			else
				a=Float.parseFloat(current);
		  if(old.contains("%"))
			 b=(Float.parseFloat((old.substring(0,old.indexOf("%"))))/100);
		  else
			  b=Float.parseFloat(old);	
		       return(nt.format((a-b)/a));
			}
		}
			
	
	//dashboard时间控件问题
	
	public static void dashboardTime(WebDriver driver,String Start,String End) throws Exception{
		//处理dashboard时间控件
		String[][] MonthTranslate={{"January","1"},{"February","2"},{"March","3"},{"April","4"},{"May","5"},{"June","6"},{"July","7"},
				{"August","8"},{"September","9"},{"October","10"},{"November","11"},{"December","12"}};
	        	driver.findElement(By.className("custom-date")).click();
		//		System.out.println(driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[2]")).getText());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date StartTime=format.parse(Start);
				Date EndTime=format.parse(End);
				Date today = new Date();
      //          System.out.println(format.format(today)+"  "+EndTime);
                
                SimpleDateFormat month=new SimpleDateFormat("MM");
                SimpleDateFormat day=new SimpleDateFormat("dd");
                SimpleDateFormat year=new SimpleDateFormat("yyyy");
                String EndYear=year.format(format.parse(End));
                String EndMonth=month.format(format.parse(End));
                String EndMonth1=EndMonth;
                String EndDay=day.format(format.parse(End));
                
                String todayYear=year.format(today);
                String todayMonth=month.format(today);
                String todayMonth1=todayMonth;
                String todayday=day.format(today);
                String MonthSwitchNum,YearSwitchNum=null,YearSwitch=null,MonthSwitch;
                //获取结束日期控件的ul的number
        		int flag=0,linum=0;
        		while(true)
        		{
        		if(((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].className;").equals("drp-days"))
        			flag++;
        		else
        			linum++;
        	//	System.out.println(((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].className;"));
        		Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].getElementsByTagName(\"li\").length;");
        	//	System.out.println(linum+" "+str);
        		if(flag==1)
        			break;
        		}	
        		
                if(Integer.parseInt(EndYear)>Integer.parseInt(todayYear))
                {
                	System.out.println("控件结束的年份比今天大!");
                	YearSwitchNum=EndYear;
                	while((Integer.parseInt(YearSwitchNum)>Integer.parseInt(todayYear))){
                	driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[1]")).click();
                	
                	 YearSwitch=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[2]")).getText();
                	 YearSwitchNum=YearSwitch.substring(YearSwitch.length()-4,YearSwitch.length());                     	 
 //               	 System.out.println(YearSwitch);                		                                       	
                	}
                	String NewMonth=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[2]")).getText();
                	NewMonth=NewMonth.substring(0, NewMonth.indexOf(" "));
                	for(int i=0;i<MonthTranslate.length;i++)
                		if(NewMonth.compareToIgnoreCase(MonthTranslate[i][0])==0)
                			NewMonth=MonthTranslate[i][1];
                    if(Integer.parseInt(NewMonth)>Integer.parseInt(todayMonth))
                    {
                    	System.out.println("年份大的同时 控件结束的月份比今天大!");
                    	MonthSwitchNum=NewMonth;
                    	while((Integer.parseInt(MonthSwitchNum)>Integer.parseInt(todayMonth))){
                    	driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[1]")).click();                	
                    	MonthSwitch=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[2]")).getText();                	
                    	MonthSwitch=MonthSwitch.substring(0, MonthSwitch.indexOf(" "));
                    	for(int i=0;i<MonthTranslate.length;i++)
                    		if(MonthSwitch.compareToIgnoreCase(MonthTranslate[i][0])==0)
                    			MonthSwitchNum=MonthTranslate[i][1];
//                    	 System.out.println(MonthSwitch);
                    		}           
                    }                    	
						Long str1=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].getElementsByTagName(\"li\").length;");
						String newEnd=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+str1+"]")).getText();						
						int dayDiff=(Integer.parseInt(newEnd)-Integer.parseInt(todayday));
							   driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+(str1-dayDiff-1)+"]")).click();
							   driver.findElement(By.className("switchbutton")).click();
							   Thread.sleep(3000);                              	
                }
                
                 if(Integer.parseInt(EndYear)<=Integer.parseInt(todayYear)&&Integer.parseInt(EndMonth)>Integer.parseInt(todayMonth))
                {
                	System.out.println("控件结束的月份比今天大!");
                	MonthSwitchNum=EndMonth;
                	while((Integer.parseInt(MonthSwitchNum)>Integer.parseInt(todayMonth))){
                	driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[1]")).click();                	
                	MonthSwitch=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[2]")).getText();                	
                	MonthSwitch=MonthSwitch.substring(0, MonthSwitch.indexOf(" "));
                	for(int i=0;i<MonthTranslate.length;i++)
                		if(MonthSwitch.compareToIgnoreCase(MonthTranslate[i][0])==0)
                			MonthSwitchNum=MonthTranslate[i][1];
//                	 System.out.println(MonthSwitch);
                		}           
                }
                
                 if(format.format(today).compareTo((format.format(EndTime)))<=0)
				{
					System.out.println("控件结束日期大于等于今天");
					Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].getElementsByTagName(\"li\").length;");

                    if(Integer.parseInt(EndMonth1)>Integer.parseInt(todayMonth1)){                        	
							Long str1=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].getElementsByTagName(\"li\").length;");
							String newEnd=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+str1+"]")).getText();							
							int dayDiff=(Integer.parseInt(newEnd)-Integer.parseInt(todayday));
							driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+(str1-dayDiff-1)+"]")).click();
							driver.findElement(By.className("switchbutton")).click();
							Thread.sleep(3000);
                    }
                    else{
                    	int days=daysBetween(today,EndTime);
                    	System.out.println("dashboardTime    "+today+" "+EndTime+" 相差 :"+days+"天.");
				   	for(int i=1;i<=str;i++){						
						String liAttribute=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+i+"]")).getAttribute("class");
	//					System.out.println(str+" "+liAttribute);
						if(liAttribute.equals("drp-day drp-day-selected") || liAttribute.equals("drp-day drp-day-selected drp-day-last-in-row"))
						{
						   String liupAttribute=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+(i-1)+"]")).getAttribute("class");													
						   driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+(i-days-1)+"]")).click();
						   driver.findElement(By.className("switchbutton")).click();
						   Thread.sleep(3000);							
						   break;
						}
					}
                    }                   
					}				
			
	}

	public static void dashboardTimeRenew(WebDriver driver,String Start,String End) throws Exception{
		  SimpleDateFormat month=new SimpleDateFormat("MM");
          SimpleDateFormat day=new SimpleDateFormat("dd");
          SimpleDateFormat year=new SimpleDateFormat("yyyy");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date StartTime=format.parse(Start);
		Date EndTime=format.parse(End);
		Date today = new Date();
	    String EndYear=year.format(format.parse(End));
        String EndMonth=month.format(format.parse(End));
        String EndMonth1=EndMonth;
        String EndDay=day.format(format.parse(End));
        
        String todayYear=year.format(today);
        String todayMonth=month.format(today);
        String todayMonth1=todayMonth;
        String todayday=day.format(today);
    	int flag=0,linum=0;
		while(true)
		{
		if(((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].className;").equals("drp-days"))
			flag++;
		else
			linum++;
//		System.out.println(((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].className;"));
		Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].getElementsByTagName(\"li\").length;");
//		System.out.println(linum+" "+str);
		if(flag==1)
			break;
		}
		
		driver.findElement(By.className("custom-date")).click();
		Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].getElementsByTagName(\"li\").length;");
		int days=daysBetween(today,EndTime);
		System.out.println("dashboardTimeRenew    "+today+" "+EndTime+" 相差 :"+days+"天.");
		 if(Integer.parseInt(EndYear)>Integer.parseInt(todayYear) || Integer.parseInt(EndMonth)>Integer.parseInt(todayMonth))
		 {
			   Long str1=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].getElementsByTagName(\"li\").length;");
				String newEnd=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+str1+"]")).getText();							
				int dayDiff=(Integer.parseInt(newEnd)-Integer.parseInt(todayday));
				if(Integer.parseInt(todayday)==1)
				{
					driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[1]")).click();
					driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+str1+"]")).click();
				}
				else
				driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+(str1-dayDiff)+"]")).click();
				driver.findElement(By.className("switchbutton")).click();
				Thread.sleep(3000);
				
		 }
		 else
		 {
		if(days>=0)
		{
		for(int i=1;i<=str;i++){
			String liAttribute=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+i+"]")).getAttribute("class");

			if(liAttribute.equals("drp-day drp-day-selected"))
			{
				if(i==str)
				{
					driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[3]")).click();
				Long str1=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"ul\")["+linum+"].getElementsByTagName(\"li\").length;");
				for(int j=1;j<=str1;j++)
				{
					String li1Attribute=driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+j+"]")).getAttribute("class");
					if(li1Attribute.equals("drp-day drp-day-in-range"))
					{
					   driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+(j+days+1)+"]")).click();
					   driver.findElement(By.className("switchbutton")).click();
					   Thread.sleep(3000);
					   break;					  
					}																						
				}
				}
				else
				{
				driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/ul[2]/li["+(i+days+1)+"]")).click();
			    driver.findElement(By.className("switchbutton")).click();
				Thread.sleep(3000);
				}
				break;
			}
		}
			
		}
	}
		
	}
	
	public static String[] valueFilter(String str)
	{
		String[] value=new String[2];
		if(str.contains("-- vs --"))
		{
			value[0]="0";
		    value[1]="0";
		}
		else if(str.contains("-- vs"))
		{
			value[0]="0";
			value[1]=methods.Getnum(str)[0];	
		}
		else if(str.contains("vs --"))
		{
			value[0]=methods.Getnum(str)[0];
			value[1]="0";
		}
		else
		{
			value[0]=methods.Getnum(str)[0];
			value[1]=methods.Getnum(str)[1];
		}
		return value;
	}
	
	  public static int daysBetween(Date smdate,Date bdate) throws ParseException    
	    {    
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));           
	    }    
	  
	
	
	
	//==================================获取display人数=====================================  
	  public static int catchNum(String start,String end,String s) throws Exception{
			 
			 int i=0;
			 String regex = String.format("%s.*%s", start,end);
			  Pattern pattern = Pattern.compile(regex);
			  Matcher matcher = pattern.matcher(s);
			    if (matcher.find()){
			    	s = matcher.group().replace(start, "").replace(end, "");
			    	i=Integer.parseInt(s);
					System.out.println("获取的数字是："+i);
					return i;
				}else{

					System.out.println("not found");
					return i;
				} 
	  }


		// 日期方法
	public static String timeDate() throws InterruptedException {
			if (i == 0) {
				long l = System.currentTimeMillis();
				Date d = new Date(l);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				TitleDate = sdf.format(d);
				++i;
				return TitleDate;
			} else {
				return TitleDate;
			}
		}
		
	// 日期方法
	public static String timeDate1() throws InterruptedException {
		if (j == 0) {
			long l = System.currentTimeMillis();
			Date d = new Date(l);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TitleDate1 = sdf.format(d);
			++j;
			return TitleDate1;
		} else {
			return TitleDate1;
		}
	}

	// 日期方法
	public static String timeDate2() throws InterruptedException {
		if (q == 0) {
			long l = System.currentTimeMillis();
			Date d = new Date(l);
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			TitleDate2 = sdf.format(d);
			++q;
			return TitleDate2;
		} else {
			return TitleDate2;
		}
	}

	// 日期方法 --dd/MM/yy
	public static String timeDate3() throws InterruptedException {
		if (s == 0) {
			long l = System.currentTimeMillis();
			Date d = new Date(l);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
			TitleDate3 = sdf.format(d);
			++s;
			return TitleDate3;
		} else {
			return TitleDate3;
		}
	}
	
	// 日期方法
	public static String timeDate4() throws InterruptedException {
		if (j == 0) {
			long l = System.currentTimeMillis();
			Date d = new Date(l);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
			TitleDate1 = sdf.format(d);
			++j;
			return TitleDate1;
		} else {
			return TitleDate1;
		}
	}

	// 日期方法 --dd/MM/yy
	public static String fulldate() throws InterruptedException {
		if (h == 0) {
			long l = System.currentTimeMillis();
			Date d = new Date(l);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fulltime = sdf.format(d);
			++h;
			return fulltime;
		} else {
			return fulltime;
		}
	}
	
	// 根据天数加或减 日期方法
	public static String funcday(int daycount, int minutecount)
			throws InterruptedException {
		long l = System.currentTimeMillis();
		Date d = new Date(l);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, daycount);
		c.add(Calendar.MINUTE, minutecount);
		Calendarday = sdf.format(c.getTime());
		return Calendarday;
	}
	
	/** 
	* 获得指定日期的前一天 
	* @param specifiedDay 
	* @return 
	* @throws Exception 
	*/ 
	public static String getSpecifiedDayBefore(String specifiedDay){ 
	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	Calendar c = Calendar.getInstance(); 
	Date date=null; 
	try { 
	date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
	} catch (ParseException e) { 
	e.printStackTrace(); 
	} 
	c.setTime(date); 
	int day=c.get(Calendar.DATE); 
	c.set(Calendar.DATE,day-1); 

	String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
	return dayBefore; 
	} 
	/** 
	* 获得指定日期的后一天 
	* @param specifiedDay 
	* @return 
	*/ 
	public static String getSpecifiedDayAfter(String specifiedDay){ 
	Calendar c = Calendar.getInstance(); 
	Date date=null; 
	try { 
	date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
	} catch (ParseException e) { 
	e.printStackTrace(); 
	} 
	c.setTime(date); 
	int day=c.get(Calendar.DATE); 
	c.set(Calendar.DATE,day+1); 

	String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
	return dayAfter; 
	}
	
	//随机生成几位包含数字和字母的字符串
	  public static String getStringRandom(int length) {  
          
	        String val = "";  
	        Random random = new Random();  
	          
	        //参数length，表示生成几位随机数  
	        for(int i = 0; i < length; i++) {  
	              
	            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
	            //输出字母还是数字  
	            if( "char".equalsIgnoreCase(charOrNum) ) {  
	                //输出是大写字母还是小写字母  
	                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
	                val += (char)(random.nextInt(26) + temp);  
	            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
	                val += String.valueOf(random.nextInt(10));  
	            }  
	        }  
	        return val;  
	    }  
	  

	// 文件名
	public static String FileDate() throws InterruptedException {
		if (f == 0) {
			long l = System.currentTimeMillis();
			Date d = new Date(l);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			TitleDate4 = sdf.format(d);
			++f;
			return TitleDate4;
		} else {
			return TitleDate4;
		}
	}

	// 只取字符中数字
	public static String[] Getnum(String StrText) {

		String regex = "\\d*";
		String[] num = new String[10];
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(StrText);
		int i=0;
		while (m.find()) {
			if (!"".equals(m.group()))
			{
				num[i] = m.group();
			    i++;
			}
		}
		return num;
	}

	//日期相隔时间
	public static int differentDaysByMillisecond(Date date1,Date date2){
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		return days;
		}
	
	 public static int differentDays(Date date1,Date date2) {
		 Calendar cal1 = Calendar.getInstance(); 
		 cal1.setTime(date1);   
		 Calendar cal2 = Calendar.getInstance(); 
		 cal2.setTime(date2); 
		 int day1= cal1.get(Calendar.DAY_OF_YEAR); 
		 int day2 = cal2.get(Calendar.DAY_OF_YEAR);   
		 int year1 = cal1.get(Calendar.YEAR); 
		 int year2 = cal2.get(Calendar.YEAR); 
		 if(year1 != year2) //同一年 
			 {  int timeDistance = 0 ;  
			 for(int i = year1 ; i < year2 ; i ++)  
			 {  
				 if(i%4==0 && i%100!=0 || i%400==0) //闰年    
					 {  
					 timeDistance += 366;  
					 }  
				 else //不是闰年  
						 {  
					 timeDistance += 365;  
						 }  
				 }     
			 return timeDistance + (day2-day1) ; } 
		 else //不同年
			 {  
			 System.out.println(date1+"和"+date2+"相差:" + (day2-day1));  
			 return day2-day1; 
			 }
		 }			 			 		 
	 
	//截图
	public static void snapshot(TakesScreenshot drivername, String filename) {
		// 调用这个方法的时候强制转换driver的类型 snapshot((TakesScreenshot)driver,filename)
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is picname
		String currentPath = methods.class.getClass().getResource("/").getFile().toString();
		currentPath = currentPath + "com/screenshots";
		File path = new File(currentPath);
		System.out.println(path);
		File scrFile = (drivername).getScreenshotAs(OutputType.FILE);

		try {
			System.out.println("save snapshot path is:" + path + "/" + filename);
			FileUtils.copyFile(scrFile, new File(path + "/" + filename));
		} catch (IOException e) {
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {
			System.out.println("screen shot finished");
		}
	}

	//日志待完善
	public static void setLogingProperties(Logger logger) {

		try {
			String path = methods.class.getClass().getResource("/").getFile().toString();
			path = path + "com/hu/MyLogFile.log";
			File logPath = new File(path);
			FileHandler fh = new FileHandler(logPath.toString(), true);
			logger.addHandler(fh);// 日志输出文件
			// logger.setLevel(level);
			fh = new FileHandler(logPath.toString(), true);
			fh.setFormatter(new SimpleFormatter());// 输出格式
			// logger.addHandler(new ConsoleHandler());//输出到控制台
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, "安全性错误", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "读取文件日志错误", e);
		}
	}
	
	//account judgement
	public static int[] getAccountOrder(String Url){
		
		int[] p= new int[3];
		if(methods.baseUrl.contains("dev.")){
			p[0]=0;
			if(methods.mid.equals("33"))
			{
			p[1]=0;
			p[2]=0;
			}
			else if(methods.mid.equals("51"))
			{
			p[1]=1;
			p[2]=1;
			}
			else
			{
			p[1]=2;
			p[2]=2;	
			}
		}else if(methods.baseUrl.contains("staging.")){
			p[0]=1;
			if(methods.mid.equals("59"))
			{
			p[1]=0;
			p[2]=3;
			}
			if(methods.mid.equals("68"))
			{
			p[1]=1;
			p[2]=4;
			}
			if(methods.mid.equals("73"))
			{
			p[1]=2;
			p[2]=5;
			}
			
		}else if(methods.baseUrl.contains("app.")){
	
			p[0]=2;
			if(methods.mid.equals("61")){
			p[1]=1;
			p[2]=7;
			}else{
			p[1]=0;
			p[2]=6;
			}
			
		}else{
			
			p[0]=3;
			p[1]=0;
			p[2]=8;
			}		
		return p;		
	}
	
	
	//删除用户
	public static boolean DeleteFollower(String openid,String mid,String url)
	{

	        Connection conns; // 获取连接
	        PreparedStatement pst; // 执行Sql语句(Statement)
	        boolean status = false;
	        String sql = "delete from wechat_customer where openid ='"+openid +"' and mid='"+mid+"'";
	        System.out.println(sql);
	        try {
	        	if(url.contains("dev")){
	            conns = MySQLConnection.devconn();
	        	}else
	        	conns = MySQLConnection.stageconn();
	            pst = conns.prepareStatement(sql);
	            pst.executeUpdate();
	            pst.close();
	            conns.close();
	            status = true;
	        } catch (SQLException e) {
	            System.out.println("数据删除失败");
	            e.printStackTrace();
	        }
	        return status;
	}

	public static void CheckTagAndTagNum(WebDriver driver,String tagfront,String timeStr,String num)
	{
		int i=1;
		String tag=null,tagnum=null;
		while(i<100)
		{

			tag=driver.findElement(By.xpath(".//*[@id='tags_value']/li["+i+"]/span[1]")).getText();
			tagnum=driver.findElement(By.xpath(".//*[@id='tags_value']/li["+i+"]/span[2]")).getText();
			 if(tag.equals(tagfront+timeStr))
			 {
				 if(timeStr.isEmpty()){
					 if(tagnum.contains(num)){
						 System.out.println(tagfront+timeStr+" tag 添加成功并且数量正确！");
				     }
				   else
					   System.out.println(tagfront+timeStr+" 数量不对 ");
				   break;
			   }else{
				 if(num.compareTo(tagnum)>0){
					 System.out.println(tagfront+timeStr+" tag 添加成功并且数量正确！");
			     }
			   else
				   System.out.println(tagfront+timeStr+" 数量不对 ");
			   break;
			 }
				 }
				  
				 i++;
		}
	}
	
	public static String TagNum(WebDriver driver,String tagfront)
	{
		int i=1;
		String tag=null,tagnum=null,num=null;
		Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"tag_handler\").getElementsByTagName(\"li\").length;");
		while(i<str)
		{

			if(driver.findElement(By.xpath(".//*[@id='tags_value']/li["+i+"]/span[1]")).getAttribute("class").equals("tag_category"))
				System.out.println("");
			else{
			tag=driver.findElement(By.xpath(".//*[@id='tags_value']/li["+i+"]/span[1]")).getText();
			tagnum=driver.findElement(By.xpath(".//*[@id='tags_value']/li["+i+"]/span[2]")).getText();
			 if(tag.equals(tagfront))
			 {
				   num=tagnum.substring(1, tagnum.length()-1);
				   break;
			 }
			}
				 i++;
		}
		return num;
	}
	
	  public static boolean isElementPresent(WebDriver driver,By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      System.out.println("加载ing");
		      return false;
		    }
		  }
	  
		
	  public static boolean isAlertPresent(WebDriver driver) {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  public static String closeAlertAndGetItsText(WebDriver driver) {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	  
	// 判断一个字符串是否都为数字  
	  public boolean isDigit(String strNum) {  
	      Pattern pattern = Pattern.compile("[0-9]{1,}");  
	      Matcher matcher = pattern.matcher((CharSequence) strNum);  
	      return matcher.matches();  
	  }
	  
	// 判断一个字符串是否含有数字
	  public static boolean hasDigit(String content) {
	  boolean flag = false;
	  Pattern p = Pattern.compile(".*\\d+.*");
	  Matcher m = p.matcher(content);
	  if (m.matches())
	  flag = true;
	  return flag;
	  }
	  
	  public static String xmlFormat (String xmlStr){
			if(null != xmlStr && !"".equals(xmlStr)){
				  if(xmlStr.indexOf("<") != -1 && xmlStr.lastIndexOf(">") != -1 && xmlStr.lastIndexOf(">") > xmlStr.indexOf("<"))
					  xmlStr = xmlStr.substring(xmlStr.indexOf("<"), xmlStr.lastIndexOf(">") + 1);
				}
			
			return (xmlStr);
	  }
	 
	  public static String optionStr(WebDriver driver,String pathStr,String SpecStr){
		   List<WebElement> element=new Select(driver.findElement(By.xpath(pathStr))).getOptions();
		   String[] optionArr = new String[element.size()];
	    	int i=0,j=0;
		for (WebElement e : element){
			 i++;
			  System.out.println(element.size()+"  "+e.getAttribute("value")+" "+i);
			 if(i>1&&e.getAttribute("value").contains(SpecStr))
			    optionArr[j++]=e.getAttribute("value");
		}
		String optionStr=optionArr[j-1];
		return optionStr;
	  }
	  
	/*  public static String ExcelFile(WebDriver driver,String filename){
		
	  }*/
	  
	public static void main(String[] args) {

	}

}
