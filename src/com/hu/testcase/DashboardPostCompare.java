package com.hu.testcase;

import java.text.NumberFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPostCompare {
	private static Logger logger = Logger.getLogger(DashboardPostCompare.class);
	public String[] PostComparison(WebDriver driver,String Start,String End) throws InterruptedException,Exception {
		  NumberFormat no = NumberFormat.getPercentInstance();
		   no.setMinimumFractionDigits(1);
		//dashboard post分析 采集数据-------------------------------------------------------------------------
		methods.navigation(driver, "Dashboard", By.id("followerEngagementChart"));
		String[] postNum = null;
		String ViewsOld,ViewRateOld,ShareRateOld,LinkClicksOld;
		String Views=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]")).getText();
		String ViewRateStr=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[1]/div[3]/div[2]/div[2]/div[2]")).getText();
		String ViewRate;
		postNum= methods.valueFilter(Views);
		Views=postNum[0];
		ViewsOld=postNum[1];	
		ViewRate=methods.vsNewOld(ViewRateStr)[0];
		ViewRateOld=methods.vsNewOld(ViewRateStr)[1];

		String ShareRateStr=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[1]/div[3]/div[2]/div[3]/div[2]")).getText();
		String ShareRate;
		ShareRate=methods.vsNewOld(ShareRateStr)[0];
		ShareRateOld=methods.vsNewOld(ShareRateStr)[1];

		String LinkClicks=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[1]/div[3]/div[2]/div[4]/div[2]")).getText();
		postNum= methods.valueFilter(LinkClicks);
		LinkClicks=postNum[0];
		LinkClicksOld=postNum[1];	

		//published post analytics 数据采集--------------------------------------------------------------------------
		driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[1]/div[1]/a")).click();
		while(true){
			if(methods.isElementPresent(driver, By.id("postanalyticsGraphic")))
				break;
		}

	
		String DetailSent=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[2]")).getText();
		String DetailSentOld,DetailViewsOld,DetailLinkClicksOld,DetailShareRateOld;
		postNum= methods.valueFilter(DetailSent);
		DetailSent=postNum[0];
		DetailSentOld=postNum[1];					
		
		String DetailViews=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[2]/div[1]/div/div[2]/div[2]")).getText();
		postNum= methods.valueFilter(DetailViews);
		DetailViews=postNum[0];
		DetailViewsOld=postNum[1];	
		
		float detailRate,detailRateOld;
		if(DetailSent.equals("0"))
			detailRate=0;
		else
		{
		detailRate=Float.parseFloat(DetailViews)/Float.parseFloat(DetailSent);
		detailRate   =  (float)(Math.round(detailRate*1000))/1000;
		}
		if(DetailSentOld.equals("0"))
			detailRateOld=0;
		else
		{
		detailRateOld=Float.parseFloat(DetailViewsOld)/Float.parseFloat(DetailSentOld);
        detailRateOld=(float)(Math.round(detailRateOld*1000))/1000;
		}

		String DetailLinkClicks=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]")).getText();
		postNum= methods.valueFilter(DetailLinkClicks);
		DetailLinkClicks=postNum[0];
		DetailLinkClicksOld=postNum[1];	
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[2]/div[1]/a")).click();
		while(true){
			if(methods.isElementPresent(driver, By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[2]/div[2]/div/div[4]/div[2]")))
				break;
		}
		String DetailShareRateStr=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[2]/div[2]/div/div[4]/div[2]")).getText();
		String DetailShareRate;
		DetailShareRate=methods.vsNewOld(DetailShareRateStr)[0];
		DetailShareRateOld=methods.vsNewOld(DetailShareRateStr)[1];
	
		//作对比
		String ViewsDiff=methods.Comparison(Views, DetailViews);
		String ViewsOldDiff=methods.Comparison(ViewsOld, DetailViewsOld);
		String ViewRateDiff,ViewRateOldDiff;
		ViewRateDiff=methods.Comparison(ViewRate, detailRate+"");
		ViewRateOldDiff=methods.Comparison(ViewRateOld, detailRateOld+"");
		String ShareRateDiff=methods.Comparison(ShareRate, DetailShareRate);
		String ShareRateOldDiff=methods.Comparison(ShareRateOld, DetailShareRateOld);
		String LinkClicksDiff=methods.Comparison(LinkClicks, DetailLinkClicks);
		String LinkClicksOldDiff=methods.Comparison(LinkClicksOld, DetailLinkClicksOld);
		System.out.println(ShareRate+"  "+DetailShareRate+"  "+ShareRateOld+"  "+ DetailShareRateOld);
		System.out.println("ViewsDiff="+ViewsDiff+"   "+"ViewsOldDiff="+ViewsOldDiff+"   "+"ViewRateDiff="+ViewRateDiff+"  "
		+"ShareRateDiff="+ShareRateDiff+"    "+"ShareRateOldDiff="+ShareRateOldDiff+"  "
				+"LinkClicksDiff="+LinkClicksDiff+"    "+"LinkClicksOldDiff="+LinkClicksOldDiff);					
		String[] PostDiff={ViewsDiff,ViewsOldDiff,ViewRateDiff,ViewRateOldDiff,ShareRateDiff,ShareRateOldDiff,LinkClicksDiff,LinkClicksOldDiff};
		return PostDiff;
	}
}
