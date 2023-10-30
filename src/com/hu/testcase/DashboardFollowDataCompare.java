package com.hu.testcase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.write.Label;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DashboardFollowDataCompare {
	private static Logger logger = Logger.getLogger(DashboardFollowDataCompare.class);
	public String[] FollowDataComparison(String mid,WebDriver driver,String Start,String End) throws InterruptedException,Exception {
		
		 //获取格式化对象
		   NumberFormat nt = NumberFormat.getPercentInstance();			   
		   NumberFormat nf = NumberFormat.getPercentInstance();
		   NumberFormat no = NumberFormat.getPercentInstance();
		   //设置百分数精确度2即保留两位小数
		   no.setMinimumFractionDigits(1);
		   nt.setMinimumFractionDigits(2);
		   nf.setMinimumFractionDigits(3);
		   methods.dashboardTime(driver,Start,End);
	//dashboard followdata 采集-------------------------------------------------------------------------------
		int EndNum=0;
	String NewFollows=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]")).getText();
	NewFollows=	methods.valueFilter(NewFollows)[0];
    System.out.println("Dashboard New Follows Data :"+NewFollows);
    String TotalFollows=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[2]")).getText();
    TotalFollows=	methods.valueFilter(TotalFollows)[0];
    System.out.println("Dashboard Total Follows Data :"+TotalFollows);
    String UnFollows=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[3]/div[2]")).getText();
    UnFollows=	methods.valueFilter(UnFollows)[0];
	System.out.println("Dashboard UnFollows Data :"+UnFollows);
	String[] FollowData={NewFollows,TotalFollows,UnFollows};
	
	//follower growth 数据采集----------------------------------------------------------------------------------------------
	driver.get(methods.baseUrl+"Manager/followerAnalyse/index/category/follower_users");
	while(true){
		if(methods.isElementPresent(driver,By.id("wechat-customer-grid")))
			break;
	}
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date StartTime=format.parse(Start);
	Date EndTime=format.parse(End);
	Date today = new Date();
	Date SysYesterday = new Date(today.getTime() - 86400000L);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String SystemToday=sdf.format(today);
	String SystemYesterday = sdf.format(SysYesterday);
	
	if(End.compareTo(SystemToday)==0)			
		End=SystemToday;
	
	int Days=methods.differentDays(StartTime, EndTime);
	System.out.println(methods.differentDays(StartTime, EndTime));
	String[][] GrowthFollowers=new String[Days][4];
	float days=(float)Days;
	//获取最新Date
	String Yesterday=driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText();
	
	int pageEnd,pageStart;
	if(SystemYesterday.compareTo(Yesterday)!=0)
		logger.error(mid+" "+Yesterday+"数据没有采集,请找开发查看原因。");	
	
	EndTime=format.parse(End);
	Date YesterdayTime=format.parse(Yesterday);
	int EndDaysDiff=methods.differentDays(EndTime, YesterdayTime);	
	if(EndDaysDiff<0)//结束日期大于脚本最新的日期 这个时候就将最新的日期赋值给结束日期
	{
		EndDaysDiff=0;
		EndTime=YesterdayTime;
	}
	//计算页数问题
		if((float)EndDaysDiff%10>0)
			pageEnd=EndDaysDiff/10+1;
		else
			pageEnd=EndDaysDiff/10;
	driver.findElement(By.id("pager_link_num")).clear();
	driver.findElement(By.id("pager_link_num")).sendKeys(pageEnd+"");
	driver.findElement(By.id("pager_link_num")).sendKeys(Keys.ENTER);
	if(pageEnd==0)
		pageEnd=pageEnd+1;
	
	while(true){
		if(methods.isElementPresent(driver, By.className("summary")))
		{
			String Summary=driver.findElement(By.className("summary")).getText();
			String[] pages=methods.Getnum(Summary);
			int currentPage=(Integer.parseInt(pages[0])+10)/10;
			if(pageEnd==currentPage)
				break;
		}
		System.out.println("页面跳转ing...");
	}
	int m=1;//循环的时候要等有值才开始给i加数据
	float GrowthNewFollower=0;
	float GrowthUnFollower=0;
	float GrowthTotalFollower=0;
	for(int i=0;i<GrowthFollowers.length;m++){

		if(m>10)
		{
			m=1;
			driver.findElement(By.className("next")).click();
         	Thread.sleep(4000);
		}
		String Timegrowth=driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr["+m+"]/td[1]")).getText();
		if(End.compareTo(Timegrowth)>=0&&Start.compareTo(Timegrowth)<=0)
		{						
			for(int j=0;j<GrowthFollowers[i].length;j++)
			{
				if(j<3)
			GrowthFollowers[i][j]=driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr["+m+"]/td["+(j+1)+"]")).getText();						
				else
			GrowthFollowers[i][j]=driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr["+m+"]/td["+5+"]")).getText();	
			}
			i++;
		}
		else
			if(Start.compareTo(Timegrowth)>0)
				break;
		}
	
	
	
	System.out.println(GrowthFollowers.length);
	for (int i = 0; i < GrowthFollowers.length; i++) {      
        for (int j = 0; j < GrowthFollowers[i].length; j++) {   
        //循环遍历数组中的每个元素  
        	//Menuarr[i][j]="*";                      
            //初始化数组内容  
        	if(!(GrowthFollowers[i][j] == null || GrowthFollowers[i][j].length() <= 0))
        	{		
            System.out.print("GrowthFollowers["+i+"]["+j+"]="+GrowthFollowers[i][j]+"  ");  
            GrowthTotalFollower=Float.parseFloat(GrowthFollowers[0][3]);
            if(j==1)
            	GrowthNewFollower=GrowthNewFollower+Float.parseFloat(GrowthFollowers[i][j]);
            if(j==2)
            	GrowthUnFollower=GrowthUnFollower+Float.parseFloat(GrowthFollowers[i][j]);
        	EndNum=(i+1);
        	}

            //将数组中的元素输出  
        }  
        System.out.println("   ");   
}

	float NewFollowsDiff=(Float.parseFloat(NewFollows)-GrowthNewFollower)/Float.parseFloat(NewFollows);
	System.out.println(NewFollowsDiff);
	float UnFollowsDiff=(Float.parseFloat(UnFollows)-GrowthUnFollower)/Float.parseFloat(UnFollows);
	float TotalFollowsDiff=(Float.parseFloat(TotalFollows)-GrowthTotalFollower)/Float.parseFloat(TotalFollows);  
	if(EndNum<GrowthFollowers.length)	
		logger.error(mid+" 缺少"+(GrowthFollowers.length-EndNum)+"天数据，");
	
	System.out.println("Dashboard New Follows Data :"+NewFollows);
	System.out.println("Dashboard UnFollows Data :"+UnFollows);
	System.out.println("Dashboard Total Follows Data :"+TotalFollows);
	System.out.println("GrowthNewFollower="+GrowthNewFollower+"\nGrowthUnFollower="+GrowthUnFollower+"\nGrowthTotalFollower="+GrowthTotalFollower);
	System.out.println("NewFollowsDiff="+nt.format(NewFollowsDiff)+"\nUnFollowsDiff="+nt.format(UnFollowsDiff)+"\nTotalFollowsDiff="+nt.format(TotalFollowsDiff));
	
	methods.navigation(driver, "Dashboard", By.id("followerEngagementChart"));
	methods.dashboardTimeRenew(driver,Start,End);
/*
* if (loadtimes0 < 5) {
			sheetw.addCell(new Label(column, i, loadtimes0 + "s"));
		} else {
			sheetw.addCell(new Label(column, i, loadtimes0 + "s",cellFormat1));
		}
		
*/
	String[] FollowDataDiff={nt.format(NewFollowsDiff),nt.format(UnFollowsDiff),nt.format(TotalFollowsDiff)};
    return FollowDataDiff;
    	
			 //**************************************************填充excel****************************************************************
}
}
