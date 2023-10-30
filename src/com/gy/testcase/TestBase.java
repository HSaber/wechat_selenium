package com.gy.testcase;


import static org.testng.AssertJUnit.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.Date;
import java.util.GregorianCalendar;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Iterator;
import java.util.Calendar;
import java.util.Locale;

import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.interactive.testcase.TestEncrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.commons.io.FileUtils;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;


import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import com.floragunn.searchguard.ssl.SearchGuardSSLPlugin;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

public class TestBase {
	private static Logger logger = Logger.getLogger(TestBase.class);
	 protected WebDriver driver;
	//private  ThreadLocal<WebDriver> dr;
	
	
	//protected  StringBuffer verificationErrors = new StringBuffer();
	  //获取当前bin路径
	  private String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	  protected File file = new File (path);
	  protected boolean acceptNextAlert = true;
	  
	  private static String delaytime, currenttime, tabletime, delaytabletime;
	  private static int i, j ,k, m = 0;
//=================================================登录====================================================================	  
	  protected  String baseUrl = "https://staging.jingsocial.com/";
	  protected  String[] info(){
		  String[] info = new String[12];
		  //String[] info= {"appid","secret","mid","WechatID","openid","sceneId(qrcode用到)","ticket(qrcode用到)","接口连接","rainbowgy的openid","cluster_name","es_host","es_index","mysql_url","mysql_username","mysql_password"};
		  String[] dev= {"wx3310f2aaf181c636","1b7bea7a641739af5b7f89e6acad84e4","33","JING_digital","oOuXGt_UcICp1uIt4ZHSS4fIkdpA","411"
				  ,"gQHB7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzUweEVqaHJsX0VHQmNDeDNpbURfAAIEKA52VwMEAAAAAA=="
				  ,"https://dev.jingsocial.com/wechat/index/id/","oOuXGt-vHeq6QOnXXKFLWxDmMfPE","elasticsearch","121.43.112.184","dev_jingsocial_33_new"
				  ,"jdbc:mysql://drds4173avo0e3u3public.drds.aliyuncs.com/jgs001_dev2","jgs001_dev2","29pldds32fasaSWS2c"};
		  //String[] dev= {"wx2cf6927bd5f0f924","976b5567bd8c010690dfbbcfdd395826","51","gh_80abcc022dbc","oKNc1w5HLjThVNE1B9bDJLw1KW2Q","25","gQHD8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3RUaTVDLVRsaUxMeG1wOHJSUlRzAAIE69SzVwMEAAAAAA==","http://dev.callback.jingsocial.com/index/index/index/id/","oKNc1w-oY6jxGzvTIo5cK1iE3nsQ"};
		 // String[] dev= {"wxf8c2129423239996","c996da06ad18a149d7b1248de48b55b1","68","gh_66563c5038f0","o9rovs9k-VV3sRRPkiw9WjfrgxOM","","","https://dev.jingsocial.com/wechat/index/id/"};
		 String[] staging= {"wx1bacd2eeeb49808e","19c17864ade9b035c991cf8d6860c229","59","agent01_jgs009","ohaIcwGQFJXHzH7QVm9nt2Pfpfg0","185","gQGy7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3VqdGVuZXZsczV2SzdSUzYxeGRCAAIE8eB8VwMEAAAAAA==","http://staging.callback.jingsocial.com/index/index/index/id/","ohaIcwCD3J8bn3140HGQwkXNv8pY","elasticsearch","121.43.112.184","stage_jingsocial_59"};
		 //String[] staging= {"wxe824fbe92f79fc04","61c7abf87076fef55d67facaeab778f8","68","JGS008_JGS","oh_DswyGc1jf9h8QmPnctXS8pR-c","56","gQFr8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0JEakt4ZURrSW10Ym9GaTBFUlJEAAIE5JGIVwMEAAAAAA==","http://staging.callback.jingsocial.com/index/index/index/id/","oh_DswwBVNGD5TBN-jEqGzjqQCVk"};
		  //String[] staging= {"wxad2eba52a554d2bb","22ffdaef0ef302408ecd7c74f7aad41b","73","weixindyhao2","oleSFv9PK_nSAp8YUNvEu74Cyssw","","","http://staging.callback.jingsocial.com/index/index/index/id/","oleSFv110FVELZ2j4WkomP4NY-Eg"};
		  
		 //String[] app= {"wx8469538cd2314ba4","6eed6f9d25f5899f02d9b41913b1075e","42","jingsocial_test","oqKzat61tixVci2ihxkOOj46bKqU","160","gQFW8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1UwekJndnprYTEwUzZSYTJSbUJzAAIEHpOIVwMEAAAAAA==","http://callback.jingsocial.com/index/index/index/id/","oqKzat8lGOAUkRwpVxUxVFi_R3JM"};
		  String[] app= {"wxdc48692cfc614ee7","3d7427a78002ace28953c703fc6982ee","61","gh_c1f504df991c","oNrb6vo-4Y54YeWmS_J5tvSd9JRo","14","gQFo8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0prWDlORmJsR1gxZ0pOM0RZR255AAIEO5aIVwMEAAAAAA==","http://callback.jingsocial.com/index/index/index/id/","oNrb6vvhjvvaTGpHaabXQkyMkHX0"};
		  if(baseUrl.equals("https://dev.jingsocial.com/")){
			   info=dev;
		  }else if(baseUrl.equals("https://staging.jingsocial.com/")){
			  info=staging;
		  }else if(baseUrl.equals("https://app.jingsocial.com/")){
			  info=app;
		  }else{
			  System.out.println("baseUrl地址输入错误");
		  }
		  return info;
	  }
	  
	 
	  private static Cookie PHPSESSID = null;
	  private static String getEnv(String key, String def) {
	             Map<String, String> env = System.getenv();
	             String val = env.get(key);
	             return val == null ? def : val;
	  }
	  
	  public void login() throws InterruptedException{
		  	String[] wid = info();
			String osName = System.getProperty("os.name");
			if(osName.contains("Windows")){

			driver.get(baseUrl);
			Cookie cookie = new Cookie("session", "u06ad7ceesoba9qbm0b27c99n5");

			driver.manage().addCookie(cookie);
			
			driver.get(baseUrl + "Manager/default/index/mid/"+ wid[2]);
		}
		else{
			
			baseUrl = getEnv("WECHAT_BASEURL", baseUrl);
		        driver.get(baseUrl);
		        if (PHPSESSID != null)
		        {
		            System.out.println("session不为空");
		        	driver.manage().addCookie(PHPSESSID);
		            driver.get(baseUrl + "Manager/default/index/mid/"+ wid[2]);
		        }
		        else
		        {
					Cookie cookie = new Cookie("selenium", "selenium_CI");
					driver.manage().addCookie(cookie);
		        	System.out.println("session为空");
		        	driver.get(baseUrl + "Admin/default/login");
		            driver.findElement(By.name("LoginForm[username]")).sendKeys(getEnv("WECHAT_USER", "gysuperadmin"));
		            driver.findElement(By.name("LoginForm[password]")).sendKeys(getEnv("WECHAT_PASSWORD", "guoyan123"));

		            driver.findElement(By.name("LoginForm[verifyCode]")).sendKeys("anything");                                                                    
		            driver.findElement(By.name("yt0")).click();
		            
		            //driver.fluentWait(By.id("adf"));
		            Thread.sleep(3000);
		            System.out.println("登陆页面提交登陆");
		            driver.get(baseUrl + "Manager/default/index/mid/"+ wid[2]);
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
		
	}
	  
	  //============================================================================================================================
	
	  @BeforeMethod
	public  void setUp() throws Exception {
		 //dr = new ThreadLocal<WebDriver>();  
		 driver= new ChromeDriver();
    	//dr.set(driver);
    	//System.out.println("before method"+ getDriver()); 
	    driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	    //DesiredCapabilities dc = DesiredCapabilities.chrome(); // 设置需要驱动的浏览器，其他的浏览器都是以此类推
		//driver = new RemoteWebDriver(new URL("http://10.127.206.130:4444/wd/hub"), dc); // 这个URL10.127.206.130是要remote PC 的IP,Address，需要改为你自己的
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 设置页面加载超时的最大时长
		//PropertyConfigurator.configure("log4j.properties");
		login();
		System.out.println(System.getProperty("user.dir"));
	  }
	  
	  public WebDriver getDriver() {
	        return driver;
	    }
	 
	//  public void setWebDriver(WebDriver driver) {
	  //      dr.set(driver);
	 //   }

	  
	  @AfterMethod
	public  void tearDown() throws Exception {
		
		 driver.close();
		
	   }
	 //=====================================================关闭弹出的alert============================================================================ 
	  public String closeAlertAndGetItsText() {
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
	 //=====================================判断某元素是否出现=========================================================== 
	  public boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }
	  
	 
	//===============================================延时、当前时间，表格里的时间格式======================================================================  
	  public static String delay() throws InterruptedException {
		    
		  if(i==0){
			
			Calendar now=Calendar.getInstance();
	        //Apr 19, 2016 2:18 PM
	        //SimpleDateFormat sdf=new SimpleDateFormat("MMM d, yyyy h:m a",Locale.US);
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
	        now.add(Calendar.MINUTE,10);
	         delaytime=sdf.format(now.getTimeInMillis());
	        ++i;
			return delaytime;
			}
	       else{
	    	   return delaytime;
	       }
	   }
	  
	  public static String currentTime() throws InterruptedException {
		    
		  if(j==0){
			
			    Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd_HH:mm");
				currenttime=sdf.format(d);
	            ++j;
			return currenttime;
			}
	       else{
	    	   return currenttime;
	       }
	   }
	  
	  public static String tableTime() throws InterruptedException {
		    
		  //if(k==0){
			
			    Date d=new Date();
			    //SimpleDateFormat sdf=new SimpleDateFormat("MMM dd, yyyy h:mm ",Locale.US);
			    SimpleDateFormat sdf=new SimpleDateFormat("MMM dd, yyyy h:",Locale.US);
				tabletime=sdf.format(d);
	    //        ++k;
			return tabletime;
		//	}
	     //  else{
	    //	   return tabletime;
	      // }
	   }
	  
	  public static String delayTableTime() throws InterruptedException {
		    
		  if(m==0){
			
			Calendar now=Calendar.getInstance();
	        SimpleDateFormat sdf=new SimpleDateFormat("MMM dd, yyyy h:mm ",Locale.US);
	        now.add(Calendar.MINUTE,10);
	         delaytabletime = sdf.format(now.getTimeInMillis());
	         ++m;
			return delaytabletime;
			}
	       else{
	    	   return delaytabletime;
	       }
	   }
	//====================================新编辑器跳到旧编辑器==============================================================
	  public void oldEditorPage() throws Exception{
		driver.findElement(By.linkText("Smart Posts")).click();
		driver.findElement(By.linkText("Manage Posts")).click();
	    driver.findElement(By.id("create-more-posts")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).click();
	    driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).clear();
	    driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).sendKeys("selenium");
	    driver.findElement(By.linkText("Submit")).click();
	    driver.findElement(By.linkText("Switch to the Older Version")).click();
	    Thread.sleep(3000);
	}
	  
	//==================================获取display人数=====================================  
	  public int catchNum(String start,String end,String s) throws Exception{
			 
			 int i=0;
			 String regex = String.format("%s.*%s", start,end);
			  Pattern pattern = Pattern.compile(regex);
			  Matcher matcher = pattern.matcher(s);
			    if (matcher.find()){
			    	s = matcher.group().replace(start, "").replace(end, "");
			    	i=Integer.parseInt(s);
					System.out.println("获取的数字是："+i);
					
				}else{

					System.out.println("not found");
					
				} 
			 return i;
	  }
	  
	  public void navigation(String s) throws Exception{
		  //=========================Content/Posts=============================================
		  if(s.equals("Manage Posts")){
			  driver.findElement(By.linkText("Content")).click();
			  driver.findElement(By.linkText("Posts")).click();
			  driver.findElement(By.linkText("Manage Posts")).click();
			  
		  }else if(s.equals("Schedule a Post")){
			  driver.findElement(By.linkText("Content")).click();
			  driver.findElement(By.linkText("Posts")).click();
			  driver.findElement(By.linkText("Schedule a Post")).click();
			  
		  }else if(s.equals("Sent Posts List")){
			  driver.findElement(By.linkText("Content")).click();
			  driver.findElement(By.linkText("Posts")).click();
			  driver.findElement(By.linkText("Post Send & Push Log")).click();
			  driver.findElement(By.linkText("Sent Posts List")).click();
		  
		  }else if(s.equals("Scheduled Posts List")){
			  driver.findElement(By.linkText("Content")).click();
			  driver.findElement(By.linkText("Posts")).click();
			  driver.findElement(By.linkText("Post Send & Push Log")).click();
			  driver.findElement(By.linkText("Scheduled Posts List")).click();
		  
		  }else if(s.equals("48 Posts List")){
			  driver.findElement(By.linkText("Content")).click();
			  driver.findElement(By.linkText("Posts")).click();
			  driver.findElement(By.linkText("Post Send & Push Log")).click();
			  driver.findElement(By.linkText("48 Posts List")).click();
		  
		  }else if(s.equals("Post Sync List")){
			  driver.findElement(By.linkText("Content")).click();
			  driver.findElement(By.linkText("Posts")).click();
			  driver.findElement(By.linkText("Post Send & Push Log")).click();
			  driver.findElement(By.linkText("Post Sync List")).click();
		  //=========================================followers=====================================================================
		  }else if(s.equals("Followers")){
			  driver.findElement(By.linkText("Followers")).click();
			  driver.findElement(By.cssSelector("li.second-category.followers > a")).click();
			  
		  }else if(s.equals("Exfollowers")){
			  driver.findElement(By.linkText("Followers")).click();
			  driver.findElement(By.linkText("Exfollowers")).click();
			  
		  }else if(s.equals("The 48 Hour List")){
			  driver.findElement(By.linkText("Followers")).click();
			  driver.findElement(By.linkText("The 48 Hour List")).click();
			  
		  }else if(s.equals("Segmentation")){
			  driver.findElement(By.linkText("Followers")).click();
			  driver.findElement(By.linkText("Segmentation")).click();
			  
		  }else if(s.equals("Segmentor")){
			  driver.findElement(By.linkText("Followers")).click();
			  driver.findElement(By.linkText("Segmentor")).click();
			//===============================================================Engagement==============================================  
		 
		  }else if(s.equals("Default Message")){
			  driver.findElement(By.linkText("Engagement")).click();
			  driver.findElement(By.linkText("Auto Responders")).click();
			  driver.findElement(By.linkText("Default Message")).click();
		  
		  }else if(s.equals("Welcome Message")){
			  driver.findElement(By.linkText("Engagement")).click();
			  driver.findElement(By.linkText("Auto Responders")).click();
			  driver.findElement(By.linkText("Welcome Message")).click();
		  
		  }else if(s.equals("Keyword Auto-Reply")){
			  driver.findElement(By.linkText("Engagement")).click();
			  driver.findElement(By.linkText("Auto Responders")).click();
			  driver.findElement(By.linkText("Keyword Auto-Reply")).click();
		  
		  }else if(s.equals("Triggers")){
			  driver.findElement(By.linkText("Engagement")).click();
			  driver.findElement(By.linkText("Triggers")).click();
			  
		  }else if(s.equals("Poll")){
			  driver.findElement(By.linkText("Engagement")).click();
			  driver.findElement(By.linkText("Modules")).click();
			  driver.findElement(By.linkText("Poll")).click();
		  
		  }else if(s.equals("Message Inbox")){
			  driver.findElement(By.linkText("Engagement")).click();
			  driver.findElement(By.linkText("Message Inbox")).click();
		//=================================================Analytics==========================================================	  
		  }else if(s.equals("Dashboard")){
			  driver.findElement(By.linkText("Analytics")).click();
			  driver.findElement(By.linkText("Dashboard")).click();
			  
		  }else if(s.equals("Follower Growth Analytics")){
			  driver.findElement(By.linkText("Analytics")).click();
			  driver.findElement(By.linkText("Follower Analytics")).click();
			  driver.findElement(By.linkText("Follower Growth Analytics")).click();
			  
		  }else if(s.equals("Account Profile")){
			  driver.findElement(By.linkText("Setup")).click();
			  driver.findElement(By.linkText("Account Profile")).click();
			  
			  
		  }
	  }
	  
	//================================获取请求url上面的验证的值      MD5加密的方法放在TestEncrypt.java=======================================================
	  public static String[]  getSignature(String mid,String appid,String secret) throws InterruptedException, NoSuchAlgorithmException{
	    	String noncester=TestEncrypt.Encrypt(mid,"MD5");
	    	long time=System.currentTimeMillis()/1000L;
	    	String timestamp=time+"";
	    	String[] param={timestamp,noncester,TestEncrypt.Encrypt(mid,"MD5")};
	    	java.util.Arrays.sort(param);
	    	String str=param[0]+param[1]+param[2];
	    	String signature=TestEncrypt.Encrypt(str,"SHA-1");
	    	String[] arra={timestamp,noncester,signature};
	    	return arra;
	    }
	  
	  //==============================post方法============================================================== 
	  public static String[] Post(String urlStr,String Info) {  
		  String line = ""; 
		  String[] outData= new String[10];
		  int i,j;
	        try {  
	            URL url = new URL(urlStr);  
	            URLConnection con = url.openConnection();  
	            con.setDoOutput(true);  
	            //设置请求头部类型    
	         //   con.setRequestProperty("Pragma:", "no-cache");  //这句话在家里的电脑执行不成功 
	            con.setRequestProperty("Cache-Control", "no-cache");  
	            con.setRequestProperty("Content-Type", "text/xml");  
	            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());       
	            out.write(new String(Info.getBytes("UTF-8")));  
	            out.flush();  
	            out.close();
	           // Thread.sleep(10000);
	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));  
	            for (i=0,j=0,line = br.readLine(); line != null; line = br.readLine(),j++) {             
	            	if(line.contains("</xml><xml>")){
	            	    outData[i]=outData[i]+"</xml>"+"\n";
	            	    ++i;
	            	    outData[i]="<xml>"+"\n";
	            	}else{
	            		if(outData[i]==null)
	            		{
	            		 outData[i]=line+"\n";
	            		}else{
	                    outData[i]=outData[i]+line+"\n";
	            		}
	            	}
	            }  
	        } catch (Exception e) {  
	        	System.out.println("Post:服务器连接失败!");
	            e.printStackTrace();  
	        }  
	        return outData;
	    }  
	  
	  /** 
	     * 向指定URL发送GET方法的请求 
	     * 
	     * @param url 
	     *            发送请求的URL 
	     * @param param 
	     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。 
	     * @return URL所代表远程资源的响应 
	     */  
	   
		public static String sendGet(String requestUrl) {
	        StringBuffer buffer = null;

	        try {
	            // 建立连接
	            URL url = new URL(requestUrl);
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
	            httpUrlConn.setDoInput(true);
	            httpUrlConn.setRequestMethod("GET");

	            // 获取输入流
	            InputStream inputStream = httpUrlConn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	            // 读取返回结果
	            buffer = new StringBuffer();
	            String str = null;
	            while ((str = bufferedReader.readLine()) != null) {
	                buffer.append(str);
	            }

	            // 释放资源
	            bufferedReader.close();
	            inputStreamReader.close();
	            inputStream.close();
	            httpUrlConn.disconnect();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return buffer.toString();
	    }
		
		//=======================================获取token======================================================================================
		public static String getAccessToken(String appid, String secret) throws JSONException {  
		    	String api_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
				String result="["+sendGet(api_url)+"]";
				JSONArray resultArray=new JSONArray(result);
				JSONObject resultObj=resultArray.optJSONObject(0);	
				String access_token =resultObj.getString("access_token");
				return access_token;

		    }
		
		//接口链接
		 protected  String requestURL() throws InterruptedException, NoSuchAlgorithmException{
			  
			  //接口部分
			  //String[] info= {"appid","secret","mid","WechatID","openid"};
			  String[] attr=info();
			  String[] arr=getSignature(attr[2],attr[0],attr[1]);
			  String requesturl = attr[7]+attr[2]+"?signature="+arr[2]+"&timestamp="+arr[0]+"&nonce="+arr[1];
			  return requesturl;
		  }
	
		 //====================================  es connect	 ========================================================
		public  SearchRequestBuilder esConnect(String tablename) throws Exception {
			   // client startup
			String es[] = info();
			 Client client = null;   
			 try {
		        	// on startup，设置集群名字
		        	Settings settings = Settings.settingsBuilder()
		        			.put("path.home", ".")
		        	        .put("cluster.name", es[9])
		        	        .put("client.transport.sniff", true)
		        	        .put("searchguard.ssl.transport.enabled", true)
		        	        .put("searchguard.ssl.transport.keystore_type", "JKS")
		        	        .put("searchguard.ssl.transport.keystore_password", "a4Frs9dtgx92119De")
		        	        .put("searchguard.ssl.transport.keystore_filepath", file+"/plugins/search-guard-2/sgconfig/keystore.jks")
		        	        .put("searchguard.ssl.transport.truststore_type", "JKS")
		        	        .put("searchguard.ssl.transport.truststore_password", "a4Frs9dtgx92119De")
		        	        .put("searchguard.ssl.transport.truststore_filepath", file+"/plugins/search-guard-2/sgconfig/truststore.jks")
		        	        .put("searchguard.ssl.transport.enforce_hostname_verification", false)
		        	        .build();
		        	
		        	//初始化连接客户端new InetSocketAddress("121.43.173.46",6999)
		        	client =TransportClient.builder().settings(settings).addPlugin(SearchGuardSSLPlugin.class).build()
		        					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(es[10]), 9300));
		        	
		        	// on shutdown
		        	//client.close();
		     } catch (Exception e){
		    	 
		            e.printStackTrace();
		        }
			 //建立查询请求
			 SearchRequestBuilder responsebuilder = client.prepareSearch(es[11]).setTypes(tablename);
			 return responsebuilder;
		} 
		
		
	//========================================================mysql connect=====================================================
		public Connection mysqlConnect() throws Exception{
			
			String mysql[] = info();
			String driver = "com.mysql.jdbc.Driver";
			Connection mysqlConnect=null;
			try {
	            Class.forName(driver);
	            System.out.println("驱动加载成功");
	        } catch (ClassNotFoundException e) {
	            System.out.println("驱动加载错误");
	            e.printStackTrace();
	        }
	        try {
	        	mysqlConnect = DriverManager.getConnection(mysql[12], mysql[13], mysql[14]);
	            System.out.println("数据库链接成功");
	        } catch (SQLException e) {
	            System.out.println("数据库链接错误");
	            e.printStackTrace();
	        }
	        
			return mysqlConnect;
		}
		
		
	//=================================================操作时间控件，查询历史数据=================================================
		public Date[] datetime() throws Exception{
			 driver.findElement(By.name("select")).click();
			 //控制右边的时间控件，每次基于当前选择时间往前选一位
			//右边的时间控件
			 boolean selected1 = isElementPresent(By.cssSelector("div.drp-calendar.drp-calendar-end > ul.drp-days > li.drp-day.drp-day-selected"));
			 boolean selected2 = isElementPresent(By.cssSelector("div.drp-calendar.drp-calendar-end > ul.drp-days > li.drp-day.drp-day-selected.drp-day-last-in-row"));
			 String day = null;
			 int l =0;
			 if(selected1){
				 day=driver.findElement(By.cssSelector("div.drp-calendar.drp-calendar-end > ul.drp-days > li.drp-day.drp-day-selected")).getText();
				 l=Integer.parseInt(day)-1;
			 }else if(selected2){
				 day=driver.findElement(By.cssSelector("div.drp-calendar.drp-calendar-end > ul.drp-days > li.drp-day.drp-day-selected.drp-day-last-in-row")).getText();
				 l=Integer.parseInt(day)-1;
			 }else{
				  logger.error("日历筛选器有问题，没有时间被选中，请检查！！！");
			 	  Assert.fail("日历筛选器有问题，没有时间被选中，请检查！！！");
			 }
			 int ed=0;
			 if(day.equals("1")){
				 driver.findElement(By.cssSelector("div.drp-calendar.drp-calendar-end > div.drp-month-picker > div.drp-arrow")).click();
				 driver.findElement(By.xpath("(//form[@id='dateContainer']/div/div/div[3]/div[3]/ul[2]/li)[last()]")).click();
				 ed=Integer.parseInt(driver.findElement(By.xpath("(//form[@id='dateContainer']/div/div/div[3]/div[3]/ul[2]/li)[last()]")).getText());
				 
			 }else{
				 driver.findElement(By.xpath("//form[@id='dateContainer']/div/div/div[3]/div[3]/ul[2]/li[text()='"+l+"']")).click();
				 ed=l;
				 
			 }
			 
			 //这里写控制左边的控件，计算出左右2个控件间隔的天数，如果天数小于1需要把左边的控件往前选一天，如果左边的控件已经是第一天了，需要往前选一个月，写法同上；然后通过计算出的间隔天数，算出环比中es需要查询的日期
			 
			 boolean selected3 = isElementPresent(By.cssSelector("li.drp-day.drp-day-selected"));
			 boolean selected4 = isElementPresent(By.cssSelector("li.drp-day.drp-day-selected.drp-day-last-in-row"));
			 
			 int s=0,bg =0,z=0;
			 if(selected3){
				 bg=Integer.parseInt(driver.findElement(By.cssSelector("li.drp-day.drp-day-selected")).getText());
				 
			 }else if(selected4){
				 bg=Integer.parseInt(driver.findElement(By.cssSelector("li.drp-day.drp-day-selected.drp-day-last-in-row")).getText());
				 
			 }else{
				  logger.error("日历筛选器有问题，没有时间被选中，请检查！！！");
			 	  Assert.fail("日历筛选器有问题，没有时间被选中，请检查！！！");
			 }
			 z=Integer.parseInt(driver.findElement(By.xpath("(.//*[@id='dateContainer']/div/div[1]/div[3]/div[1]/ul[2]/li)[last()]")).getText());
			//判断左右2个控件是否在同一个月
			String be= driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[1]/div[1]/div[2]")).getText();
			 String en= driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[3]/div[1]/div[2]")).getText();
			//不同的月时间间隔是z-bg+1+ed，同一个月就是ed-bg+1
			 //February 2017
			 be=be.substring(0,be.length()-5);
			 en=en.substring(0,en.length()-5);
			 if(be.equals(en)){
				 s=ed-bg+1;
			 }else{
				 s=z-bg+1+ed;
			 }
			 if(s<1){
				 if(bg==1){
					 driver.findElement(By.cssSelector("div.drp-arrow.firepath-matching-node")).click();
					 driver.findElement(By.xpath("(//form[@id='dateContainer']/div/div/div[3]/div/ul[2]/li)[last()]")).click(); 
					 s=2;
				 }else{
					 driver.findElement(By.xpath(".//*[@id='dateContainer']/div/div[1]/div[3]/div[1]/ul[2]/li[text()='"+(bg-1)+"']")).click();
					 s=2;
				 }
			 }
			
			 
			 String startdate = driver.findElement(By.cssSelector("div.drp-calendar-date")).getText();
			 String enddate = driver.findElement(By.cssSelector("div.drp-calendar.drp-calendar-end > div.drp-calendar-date")).getText();
			 logger.info("日期开始时间和结束时间分别是："+startdate+"---"+enddate);
			 SimpleDateFormat simpleDateFormat0 =new SimpleDateFormat("M/d/yyyy");
			 Date date3=simpleDateFormat0 .parse(startdate);
		     Date date4=simpleDateFormat0 .parse(enddate);
		     
		     Calendar calendar1 = new GregorianCalendar();
		     calendar1.setTime(date3);
		     calendar1.add(calendar1.DATE,-s);
		     Date date5=calendar1.getTime();
		     
		     Calendar calendar2 = new GregorianCalendar();
		     calendar2.setTime(date4);
		     calendar2.add(calendar2.DATE,-s);
		     Date date6=calendar2.getTime();
		     
		     Date[] datetime={date3,date4,date5,date6};
		     return datetime;
		}
		
		
		
		
		public long[] createTime() throws Exception{
			Date[] datetime=datetime();
		     
		     SimpleDateFormat simpleDateFormat1 =new SimpleDateFormat("yyyyMMdd");
		     int createddategate = Integer.parseInt(simpleDateFormat1.format(datetime[0]));
		     int createddatelte = Integer.parseInt(simpleDateFormat1.format(datetime[1]));
		     int lcreateddategate = Integer.parseInt(simpleDateFormat1.format(datetime[2]));
		     int lcreateddatelte = Integer.parseInt(simpleDateFormat1.format(datetime[3]));
		     System.out.println("当前转换日期格式yyyyMMdd后"+createddategate+"==="+createddatelte);
		     System.out.println("环比日期转换日期格式yyyyMMdd后"+lcreateddategate+"==="+lcreateddatelte);
			 
		     SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		     Date date1=simpleDateFormat .parse(createddategate+" 00:00:00");
		     Date date2=simpleDateFormat .parse(createddatelte+" 23:59:59");
		     Date date7=simpleDateFormat .parse(lcreateddategate+" 00:00:00");
		     Date date8=simpleDateFormat .parse(lcreateddatelte+" 23:59:59");
		     long createtimegate = date1.getTime()/1000;
		     long createtimelte = date2.getTime()/1000;
		     long lcreatetimegate = date7.getTime()/1000;
		     long lcreatetimelte = date8.getTime()/1000;
		     System.out.println("当前转换时间戳后"+createtimegate+"==="+createtimelte);
		     System.out.println("环比转换时间戳后"+lcreatetimegate+"==="+lcreatetimelte);
			
		    
		    
		     //driver.findElement(By.cssSelector("div.switchbutton")).click();
		     driver.findElement(By.cssSelector(".btn.f_l")).click();
		     
		     
		     long[] createTime={createddategate,createddatelte,lcreateddategate,lcreateddatelte
		    		 			,createtimegate,createtimelte,lcreatetimegate,lcreatetimelte};
		     return createTime;
		}
		
		public String[] refDate() throws Exception{
			Date[] datetime=datetime();
			 SimpleDateFormat simpleDateFormat2 =new SimpleDateFormat("yyyy-MM-dd");
		     String refdategate=simpleDateFormat2.format(datetime[0]);
		     String refdatelte=simpleDateFormat2.format(datetime[1]);
		     String lrefdategate=simpleDateFormat2.format(datetime[2]);
		     String lrefdatelte=simpleDateFormat2.format(datetime[3]);
		     System.out.println("当前转换日期格式yyyy-MM-dd后"+refdategate+"==="+refdatelte);
		     System.out.println("环比日期转换日期格式yyyy-MM-dd后"+lrefdategate+"==="+lrefdatelte);
		     
		     String createtimegate=refdategate+" 00:00:00";
		     String createtimelte=refdategate+" 23:59:59";
		     String lcreatetimegate=lrefdategate+" 00:00:00";
		     String lcreatetimelte=lrefdatelte+" 23:59:59";
		     
		     String[] refdate={refdategate,refdatelte,lrefdategate,lrefdatelte
		    		 			,createtimegate,createtimelte,lcreatetimegate,lcreatetimelte};
		     return refdate;
		}
}

