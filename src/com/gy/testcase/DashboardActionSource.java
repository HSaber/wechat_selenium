package com.gy.testcase;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;


public class DashboardActionSource extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardActionSource.class);
	@Test
	 public void dashboardActionSource() throws Exception {
		int a=0;
		float sum=0;
		for(int i=2;i<6;i++){
			String percent = driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[1]/div[1]/div/div[2]/table/tbody/tr["+i+"]/td[3]")).getText();
			String display=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[1]/div[1]/div/div[2]/table/tbody/tr["+i+"]")).getAttribute("style");
			logger.info(":"+percent);
			try{
					percent= percent.substring(0, percent.length()-1);
			}catch(java.lang.StringIndexOutOfBoundsException e){
				percent="0";
			}
			logger.info(percent);
			logger.info(Float.parseFloat(percent));
			sum=sum+ Float.parseFloat(percent);
			if(display.equals("display: none;")){
				a++;
			}
		}
		logger.info("没显示的类型是："+a+"，显示的几个类型百分比总和是："+sum);
		if(a==4){
			try{
				Assert.assertEquals("0.0", sum+"");
		}catch(Error e){
			logger.error("Dashboard中的action source所有action的百分比加起来应该是0%，请检查！！！！");
			Assert.fail("Dashboard中的action source所有action的百分比加起来应该是0%，请检查！！！！");
		}
		}else{
			try{
					Assert.assertEquals("100.0", sum+"");
			}catch(Error e){
				logger.error("Dashboard中的action source所有action的百分比加起来不是100%，请检查！！！！");
				Assert.fail("Dashboard中的action source所有action的百分比加起来不是100%，请检查！！！！");
			}
		}
	}

}
