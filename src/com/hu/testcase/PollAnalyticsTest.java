package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PollAnalyticsTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(PollAnalyticsTest.class);
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
		String PollTitle = methods.timeDate();
		methods.navigation(driver, "Poll",By.cssSelector("a.btn > button"));
		driver.findElement(By.name("Poll[poll_title]")).sendKeys("中学生上网情况问卷调查" +PollTitle);
		driver.findElement(By.name("Poll[poll_title]")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);		
			String parti=driver.findElement(By.xpath(".//*[@id='poll-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText();
			System.out.println("Poll List 页面  中学生上网情况问卷调查" +PollTitle+"当前的投票总人数是 "+parti);
			int partinum=Integer.parseInt(parti);
			AssertJUnit.assertTrue((partinum>=1));
			Thread.sleep(1000);
			try{
				driver.findElement(By.className("analytics")).click();
				Thread.sleep(3000);
				System.out.println("Open  poll analytics page successfully!");	
			}catch(Exception e){
				System.out.println("Fail to open poll analytics page!");	
			}

			driver.findElement(By.id("export")).click();
			String Follower=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[2]/table/tbody/tr/td[2]/span[1]")).getText();
			System.out.println("中学生上网情况问卷调查" +PollTitle+" 当前的关注者投票人数是 "+Follower);
			int followernum=Integer.parseInt(Follower);
		//	assertTrue((followernum>=1));
			String unFollower=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[2]/table/tbody/tr/td[3]/span[1]")).getText();
			System.out.println("中学生上网情况问卷调查" +PollTitle+"当前的未关注者投票人数是 "+unFollower);
			int unfollowernum=Integer.parseInt(unFollower);
			AssertJUnit.assertTrue((unfollowernum>=1));
			
			String[] num= new String[1];
			String[] NumArr=new String[13];
			int i=0;
			//初二
			String result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[1]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[i]=num[0];
			//女
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[2]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//否
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[3]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//电脑上网
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[4]/div/div[2]/p[1]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//手机上网
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[4]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//有时逃课
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[5]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//1-2小时
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[6]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//没有
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[8]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//无聊的消遣
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[9]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//有独特的神秘感
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[9]/div/div[2]/p[3]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//能使自己畅所欲言
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[9]/div/div[2]/p[4]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//不支持
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[10]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			NumArr[++i]=num[0];
			//无影响
			result=driver.findElement(By.xpath(".//*[@id='poll-form']/div[2]/div[3]/div[11]/div/div[2]/p[2]")).getText();
			num=methods.Getnum(result);
			System.out.println(i+"  "+NumArr[i]);
			NumArr[++i]=num[0];
			System.out.println(i+"  "+NumArr[i]);
			
			for(i=0;i<NumArr.length;i++){
				System.out.println(i+"  "+NumArr[i]);
				if(NumArr[i].equals("0")){
					System.out.println("投票结果数据有问题 结果却是"+NumArr[i]);
					break;
				}
			}
			System.out.println("投票结果正确");

			String title=methods.FileDate();
	        methods.snapshot((TakesScreenshot)driver, title+"PollAnalytics.jpg");
	        Thread.sleep(3000);
			
	}

}
