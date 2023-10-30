package com.gy.testcase;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardBehaviorSubscribe extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardBehaviorSubscribe.class);
	public String getLineInfo()
    {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
	
	 @Test
	 public void dashboardBehaviorSubscribe() throws Exception {
		 driver.findElement(By.linkText("Show More")).click(); 
		 //dashboardBehaviorSubscribe
		 String unsubscribe=driver.findElement(By.xpath("//div[@rel='unsubscribe']/div[2]")).getText();
		 String unsubPersent=driver.findElement(By.xpath("//div[@rel='unsubscribe']/div[1]/span")).getText();
		 String subscribe=driver.findElement(By.xpath("//div[@rel='subscribe']/div[2]")).getText();
		 String subPersent=driver.findElement(By.xpath("//div[@rel='subscribe']/div[1]/span")).getText();
	 
		 //dashbord new follows数据进行对比
		 String unfololow=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[3]/div[2]")).getText();
		 String unfollowPersent=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[3]/div[1]/span")).getText();
		 String newfollow=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]")).getText();
		 String newfollowPersent=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[1]/span")).getText();
		
		 int error =0;
		 String[] errorinfo=new String[4];
			
		try{
				
				Assert.assertEquals(unsubscribe, unfololow);
				
		}catch(Error e){
				
			logger.error("Dashboard behavior和dashaboard new follows两个模块的 取关次数 数据不一致，请检查！！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard behavior和dashaboard new follows两个模块的 取关次数 数据不一致，请检查！！！！";
				
		} 
		
		try{
			
			Assert.assertEquals(unsubPersent, unfollowPersent);
			
		}catch(Error e){
			
			logger.error("Dashboard behavior和dashaboard new follows两个模块的 取关百分比 数据不一致，请检查！！！！");
			error++;
			errorinfo[1]=getLineInfo()+",  Dashboard behavior和dashaboard new follows两个模块的 取关百分比 数据不一致，请检查！！！！";
			
		} 
		
		try{
			
			Assert.assertEquals(subscribe, newfollow);
			
		}catch(Error e){
			
			logger.error("Dashboard behavior和dashaboard new follows两个模块的 新关注次数数据 不一致，请检查！！！！");
			error++;
			errorinfo[2]=getLineInfo()+",  Dashboard behavior和dashaboard new follows两个模块的 新关注次数数据 不一致，请检查！！！！";
			
		} 
		
		try{
			
			Assert.assertEquals(subPersent, newfollowPersent);
			
		}catch(Error e){
			
			logger.error("Dashboard behavior和dashaboard new follows两个模块的 新关注次数百分比 不一致，请检查！！！！");
			error++;
			errorinfo[3]=getLineInfo()+",  Dashboard behavior和dashaboard new follows两个模块的  新关注次数百分比 不一致，请检查！！！！";
			
		} 
		
		 if(error>0){
			 Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n"+errorinfo[3]+"\n");
		 }
		
			
}
}
