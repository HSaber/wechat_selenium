package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TriggerActionRecord  {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(TriggerActionRecord.class);
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	@Parameters({"TriggerTitle","TriggerNum"})
	@Test
	public void test(String TriggerTitle,int TriggerNum) throws InterruptedException,Exception {
		String timeStr=methods.timeDate();
		String summary;
		methods.navigation(driver, "Triggers", By.linkText("Create a Trigger"));		
		driver.findElement(By.name("Triggers[title]")).sendKeys(TriggerTitle);
		driver.findElement(By.name("Triggers[title]")).sendKeys(Keys.ENTER);
		new Select(driver.findElement(By.name("Triggers[status]"))).selectByValue("1");
		 while(true){
			 if(methods.isElementPresent(driver, By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")))
				 if(methods.isElementPresent(driver, By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[2]/td[3]")))
				 Thread.sleep(500);
				 if(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")).getText().equals(TriggerTitle))
				 {
					  summary=driver.findElement(By.className("summary")).getText();
				     if(summary.equals("Displaying 1-1 of 1 result."))
				    	 break;
				     else
					 if(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[2]/td[3]")).getText().equals(TriggerTitle))
				 break;			
				 }
		 }
		 int TriggerCountNew=0;
		 if(!summary.equals("Displaying 1-1 of 1 result."))			
		  TriggerCountNew=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[2]/td[8]")).getText());		 
		driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[8]/a")).click();
		while(true){
			if(methods.isElementPresent(driver, By.className("data-info")))
				break;
		}
		boolean status1=false,status2=false,status3=false,status4=false;
		int i=1,m=0,n=0,p=0,q=0; //m=H.',n=Candy,p=T_T,q=80
		String results=driver.findElement(By.xpath(".//*[@id='menu-record-grid']/div[2]")).getText();
		results=methods.Getnum(results)[2];
		System.out.println("页面显示的触发次数一共为:"+results);
		int result=Integer.parseInt(results);
		if(result>20)
		   result=20;
		for(;i<=result;i++){
            String nickname=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+i+"]/td[1]")).getText();
			status1=nickname.contains(methods.nicknameArr[0]);
			status2=nickname.contains(methods.nicknameArr[1]);
			status3=nickname.contains("T_T");
		    status4=nickname.contains("80");
			String triggerTime=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/table/tbody/tr["+i+"]/td[2]")).getText();

			if(status1&&triggerTime.compareTo(timeStr)>=0)			
				m++;
			else if(status2&&triggerTime.compareTo(timeStr)>=0)
			    n++;
		else if(status3&&triggerTime.compareTo(timeStr)>=0)
		    p++;
	else if(status4&&triggerTime.compareTo(timeStr)>=0)
	    q++;
		}
		if(m>0 || n>0 || p>0 || q>0)
		{
		System.out.println("H.' 实际触发"+TriggerTitle+" "+m+"次！");
		System.out.println("Candy 实际触发"+TriggerTitle+" "+n+"次！");
		System.out.println("T_T 实际触发"+TriggerTitle+" "+p+"次！");
		System.out.println("80   实际触发"+TriggerTitle+" "+q+"次！");
		System.out.println(TriggerTitle+" 之前触发次数："+methods.TriggerCountArr[TriggerNum]+"次！");
		System.out.println(TriggerTitle+" 当前触发次数："+TriggerCountNew+"次！");
		}
		else	
		System.out.println(TriggerTitle+"未触发成功！");
		
		if(TriggerTitle.equals("AnyActCon")){
			System.out.println("Candy 应该触发"+TriggerTitle+" "+4+"次！");
			AssertJUnit.assertEquals(4,n);
			AssertJUnit.assertTrue(TriggerCountNew>methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("AnyActOnce")){
			System.out.println("T_T 应该触发"+TriggerTitle+" "+1+"次！");
			System.out.println("80   应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,p);
			AssertJUnit.assertEquals(1,q);
			AssertJUnit.assertEquals(TriggerCountNew,methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("AnyAct-ConOnce")){
			System.out.println("H.'  应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,m);
			AssertJUnit.assertEquals(TriggerCountNew,methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("AnyMenuCon")){
			System.out.println("H.'  应该触发"+TriggerTitle+" "+2+"次！");
			AssertJUnit.assertEquals(2,m);
			AssertJUnit.assertTrue(TriggerCountNew>methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("AnyMenuOnce")){
			System.out.println("80   应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,q);
			AssertJUnit.assertEquals(TriggerCountNew,methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("AnyQrExCon")){
			System.out.println("Candy 应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,n);
			AssertJUnit.assertTrue(TriggerCountNew>methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("AnyQrExNew")){
			System.out.println("T_T 应该触发"+TriggerTitle+" "+2+"次！");
			System.out.println("80   应该触发"+TriggerTitle+" "+2+"次！");
			AssertJUnit.assertEquals(2,p);
			AssertJUnit.assertEquals(2,q);
			AssertJUnit.assertTrue(TriggerCountNew>methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("AnyQrNewCon")){
			System.out.println("H.'  应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,m);
			AssertJUnit.assertTrue(TriggerCountNew>methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("SpeMenuCon")){
			System.out.println("Candy 应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,n);
			AssertJUnit.assertTrue(TriggerCountNew>methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("SpeMenuOnce")){
			System.out.println("80   应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,q);
			AssertJUnit.assertEquals(TriggerCountNew,methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("SpecQrExOnce")){
			System.out.println("T_T 应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,p);
			AssertJUnit.assertEquals(TriggerCountNew,methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("SpecQrExNewCon")){
			System.out.println("H.'  应该触发"+TriggerTitle+" "+2+"次！");
			System.out.println("Candy 应该触发"+TriggerTitle+" "+2+"次！");
			AssertJUnit.assertEquals(2,m);
			AssertJUnit.assertEquals(2,n);
			AssertJUnit.assertTrue(TriggerCountNew>methods.TriggerCountArr[TriggerNum]);
		}
		if(TriggerTitle.equals("SpecQrNew")){
			System.out.println("80   应该触发"+TriggerTitle+" "+1+"次！");
			AssertJUnit.assertEquals(1,q);
			AssertJUnit.assertTrue(TriggerCountNew>methods.TriggerCountArr[TriggerNum]);
		}
	}

}
