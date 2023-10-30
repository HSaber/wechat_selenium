package com.hu.testcase;
 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.w3c.dom.Document;

import io.appium.java_client.android.AndroidDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
 
public class AppDemo {
    private  AndroidDriver  driver;
 
    @BeforeMethod
	public void setUp() throws Exception {
        // set up appium
/*        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "weixin_780.apk");*/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
 //       capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "4.4.2");
//        capabilities.setCapability("app", app.getAbsolutePath());
        //F8UDU16325010256  HC38XMR02133  6O5609A53038
        capabilities.setCapability("deviceName","6O5609A53038");
        capabilities.setCapability("appPackage", "com.tencent.mm");
        capabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("autoWebviewTimeout", "3000");
        capabilities.setCapability("autoWebview", "True");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4722/wd/hub"), capabilities);

    }
 
    @AfterMethod
	public void tearDown() throws Exception {
        driver.quit();
    }
 
    @Test
    public void addContact() throws InterruptedException,Exception{
    	Thread.sleep(10000);
    	
    	//登录微信
    /*	driver.findElement(By.id("com.tencent.mm:id/ama")).click();
    	driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
    	driver.findElement(By.id("com.tencent.mm:id/all")).click();
    	driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
    	driver.findElement(By.id("com.tencent.mm:id/a6p")).sendKeys("1486156732");
		   //模拟键盘enter键 
	    try {
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
    	driver.findElement(By.id("com.tencent.mm:id/a6p")).sendKeys("HH8011**");
    	driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
    	driver.findElement(By.id("com.tencent.mm:id/alz")).click();
    	driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);*/
    	

        int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;
       //点击公众号
        boolean status=driver.findElement(By.id("com.tencent.mm:id/aa")).getText().contains("JGS008");
        for(int i=0;status;i++)
        {
        	driver.swipe(width/2,height*3/4, width/2,height/4, 100);
        	status=driver.findElement(By.id("com.tencent.mm:id/aa")).getText().contains("JGS008");
        }
        driver.findElement(By.name("JGS008")).click();    
    	Thread.sleep(5000);
  
        //-----------------点击第一item 
    	
    	driver.findElement(By.name("标题title'1“测试” selenium")).click();
    	Thread.sleep(8000);
       //点击link 微信带参链接正文页
    	int width2=width/18;
//      int height2=height-height*5/16;
    	int height2=height*50/81;
        driver.tap(1, width2, height2,500);
        Thread.sleep(15000);
        //关闭post
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='返回']")).click();
        Thread.sleep(2000);

        //-----------------点击第一item 分享
    	driver.findElement(By.name("标题title'1“测试” selenium")).click();
    	Thread.sleep(10000);

    	//滑动到底部
        driver.swipe(width/2,height*4/5, width/2,height/4, 100);
        Thread.sleep(3000);
        driver.swipe(width/2,height*4/5, width/2,height/4, 100);
        Thread.sleep(3000);
        driver.swipe(width/2,height*4/5, width/2,height/4, 100);
        Thread.sleep(1000);
        //点击阅读原文
    	int width1=width/10+width/27;
        int height1=height-height/18;
        driver.tap(1, width1, height1,500); 
        Thread.sleep(8000);
        // 插入post投票
    	//发送给朋友
    	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='更多']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.name("发送给朋友")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//android.widget.EditText[@text='搜索']")).sendKeys("Tester");
    	Thread.sleep(1000);
    	driver.findElement(By.name("Tester")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.name("发送")).click();
    	Thread.sleep(1000);
    	
        //关闭post
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='返回']")).click();
        Thread.sleep(2000);
        
        
        //--------------点击第二个item
        driver.findElement(By.name("标题title'2“测试”")).click();
        Thread.sleep(5000);
        //发送给qq
    	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='更多']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.name("分享到手机QQ")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.name("搜索")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.name("搜索")).sendKeys("欢");
    	Thread.sleep(1000);
    	driver.findElement(By.name("(Jormungand)")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.name("发送")).click();
    	Thread.sleep(4000);
    	driver.findElement(By.name("返回微信")).click();
    	Thread.sleep(2000);
    	
        //点击阅读原文
        int width3=width/18;
     	//int width3=width/10+width/18;
       // int height3=height-height/4;
        int height3=height*100/111;
        driver.tap(1, width3, height3,500);
        Thread.sleep(15000);  	
        //关闭post
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='返回']")).click();
        Thread.sleep(2000);
        
        //-----------点击第三个item
        driver.findElement(By.name("标题title'3“测试”")).click();
        Thread.sleep(10000);
       //发送给朋友圈
    	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='更多']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//android.widget.TextView[@text='分享到朋友圈']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//android.widget.EditText[@text='这一刻的想法...']")).sendKeys("分享到朋友圈");
    	Thread.sleep(2000);
    	driver.findElement(By.name("发送")).click();
    	Thread.sleep(1000);

    	//滑动到底部
    	double dwidth,dheight;
    	dwidth=driver.manage().window().getSize().width;
    	dheight=driver.manage().window().getSize().height;
    	
    	driver.swipe(width/2,height*4/5, width/2,height/4, 100);
    	Thread.sleep(3000);
    	driver.swipe(width/2,height*4/5, width/2,height/4, 100);
    	Thread.sleep(3000);
    	driver.swipe(width/2,height*4/5, width/2,height/4, 100);
    	Thread.sleep(1000);
        //点击阅读原文
        driver.tap(1, width1, height1,500); 
        Thread.sleep(8000);
    	//发送给朋友
    	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='更多']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.name("发送给朋友")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//android.widget.EditText[@text='搜索']")).sendKeys("Tester");
    	Thread.sleep(1000);
    	driver.findElement(By.name("Tester")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.name("发送")).click();
    	Thread.sleep(1000);
     	
        //发送给朋友圈
     	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='更多']")).click();
     	Thread.sleep(1000);
     	driver.findElement(By.xpath("//android.widget.TextView[@text='分享到朋友圈']")).click();
     	Thread.sleep(1000);
     	driver.findElement(By.xpath("//android.widget.EditText[@text='这一刻的想法...']")).sendKeys("分享到朋友圈");
     	Thread.sleep(2000);
     	driver.findElement(By.name("发送")).click();
     	Thread.sleep(1000);
     	
    	//滑动到底部
    	driver.swipe(width/2,height*4/5, width/2,height/4, 100);
    	Thread.sleep(2000);
        
        //点击post link
        int height4=height-height/23;
        driver.tap(1, width2, height4,500);
        Thread.sleep(10000);
        //关闭post
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='返回']")).click();
        Thread.sleep(3000);
       
        //-------点击第四个item
        driver.findElement(By.name("标题title'4“测试”")).click();
        Thread.sleep(6000);
    	//发送给朋友
    	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='更多']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.name("发送给朋友")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//android.widget.EditText[@text='搜索']")).sendKeys("Tester");
    	Thread.sleep(1000);
    	driver.findElement(By.name("Tester")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.name("发送")).click();
    	Thread.sleep(1000);
    	
        //点击post link  	
        int height5=height*10/34;
        driver.tap(1, width2, height5,100);
        Thread.sleep(8000);
        //关闭post
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='返回']")).click();
        Thread.sleep(2000);

    	
    }
}