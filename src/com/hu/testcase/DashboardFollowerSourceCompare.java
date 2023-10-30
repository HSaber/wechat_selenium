package com.hu.testcase;

import java.text.NumberFormat;

import jxl.write.Label;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardFollowerSourceCompare {
	private static Logger logger = Logger.getLogger(DashboardFollowerSourceCompare.class);
	public String[][] FollowerSourceCompare(WebDriver driver,String Start,String End,String StartEnd) throws InterruptedException,Exception {
		 //获取格式化对象
		   NumberFormat nt = NumberFormat.getPercentInstance();			   
		   NumberFormat nf = NumberFormat.getPercentInstance();
		   NumberFormat no = NumberFormat.getPercentInstance();
		   //设置百分数精确度1即保留1位小数
		   no.setMinimumFractionDigits(1);
		  String summary;
		//Dashboard follower source 数据采集---------------------------------------------------------------------------
			methods.navigation(driver, "Dashboard", By.id("followerEngagementChart"));
			int EndNumSource=0;
			String[][] FollowSource=new String[6][3];
			String[][] FollowSourceDiff = null;
			for(int i=0;i<FollowSource.length;i++){
				if(driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[2]/div[3]/div/div[2]/table/tbody/tr["+(i+2)+"]/td[2]")).getText().equals("0"))
					break;
			FollowSource[i][0]=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[2]/div[3]/div/div[2]/table/tbody/tr["+(i+2)+"]/td[1]/span[2]")).getText();
			FollowSource[i][1]=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[2]/div[3]/div/div[2]/table/tbody/tr["+(i+2)+"]/td[2]")).getText();
			FollowSource[i][2]=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[2]/div[3]/div/div[2]/table/tbody/tr["+(i+2)+"]/td[3]")).getText();	
			if(FollowSource[i][2].contains("--"))
				FollowSource[i][2]="0";
			}

			for (int i = 0; i < FollowSource.length; i++) {      
	            for (int j = 0; j < FollowSource[i].length; j++) {   
	            //循环遍历数组中的每个元素  
	            	//Menuarr[i][j]="*";                      
	                //初始化数组内容  
	            	if(!(FollowSource[i][j] == null || FollowSource[i][j].length() <= 0))
	            	{		
	                System.out.print("FollowSource["+i+"]["+j+"]="+FollowSource[i][j]);  
	                EndNumSource=(i+1);
	            	}

	                //将数组中的元素输出  
	            }  
	            System.out.println("   ");                	            	            
		}
			if(EndNumSource<FollowSource.length)
			{
				System.out.println(FollowSource.length-EndNumSource);
				logger.error("缺少"+(FollowSource.length-EndNumSource)+"条数据，");
			}
			
		//follow source 详情页面  数据采集---------------------------------------------------------------------------------
			driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[1]/div[2]/div[1]/a")).click();
			while(true){
				if(methods.isElementPresent(driver, By.id("follower-attribute-grid")))
					break;
				}
			String DetailStartEnd=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/div[1]/select/option[6]")).getText();
			if(DetailStartEnd.equals(StartEnd))
			{	
			 summary=driver.findElement(By.className("summary")).getText();
			String[] result=methods.Getnum(summary);
			int num;
			if(Integer.parseInt(result[2])>20)
				num=20;
			else
				num=Integer.parseInt(result[2]);
			String[][]  DetailFollowSource = new String[num][3];
	//		System.out.println(result[2]);
			for(int i=0;i<DetailFollowSource.length;i++){
				if(driver.findElement(By.xpath(".//*[@id='follower-attribute-grid']/div[1]/table/tbody/tr["+(i+1)+"]/td[2]")).getText().equals("0"))
					break;
				String detailSource=driver.findElement(By.xpath(".//*[@id='follower-attribute-grid']/div[1]/table/tbody/tr["+(i+1)+"]/td[1]")).getText();
				if(detailSource.contains("Qrcode:"))
					detailSource=detailSource.substring(detailSource.indexOf(":")+2);
				DetailFollowSource[i][0]=detailSource;
				DetailFollowSource[i][1]=driver.findElement(By.xpath(".//*[@id='follower-attribute-grid']/div[1]/table/tbody/tr["+(i+1)+"]/td[2]")).getText();
				if(DetailFollowSource[i][1].contains("--"))
					DetailFollowSource[i][1]="0";
				String detailPercent=driver.findElement(By.xpath(".//*[@id='follower-attribute-grid']/div[1]/table/tbody/tr["+(i+1)+"]/td[4]")).getText();
				detailPercent=no.format(Float.parseFloat((detailPercent.substring(0, detailPercent.indexOf("%"))))/100);
				DetailFollowSource[i][2]=detailPercent;				
			}
			
			int detailNum=0;
			for (int i = 0; i < DetailFollowSource.length; i++) {      
	            for (int j = 0; j < DetailFollowSource[i].length; j++) {   
	            //循环遍历数组中的每个元素  
	            	//Menuarr[i][j]="*";                      
	                //初始化数组内容  
	            	if(!(DetailFollowSource[i][j] == null || DetailFollowSource[i][j].length() <= 0))
	            	{		
	                System.out.print("DetailFollowSource["+i+"]["+j+"]="+DetailFollowSource[i][j]);  
	                detailNum=(i+1);
	            	}

	                //将数组中的元素输出  
	            }  
	            System.out.println("   ");                	            	            
		}
			//对比两组数据
			FollowSourceDiff=new String[EndNumSource][3];		
			for(int i=0;i<EndNumSource;i++){
				for(int j=0;j<detailNum;j++)
				{
		//			System.out.println(FollowSource[i][0]+"      "+DetailFollowSource[j][0]);
					if(FollowSource[i][0].equalsIgnoreCase(DetailFollowSource[j][0]) || FollowSource[i][0].equals(DetailFollowSource[j][0]) )
						{
						FollowSourceDiff[i][0]=FollowSource[i][0];
						DetailFollowSource[j][2]=no.format(Float.parseFloat((DetailFollowSource[j][2].substring(0,DetailFollowSource[j][2].indexOf("%"))))/100);
						FollowSourceDiff[i][1]=methods.Comparison(FollowSource[i][1], DetailFollowSource[j][1]);
						FollowSourceDiff[i][2]=methods.Comparison(FollowSource[i][2], DetailFollowSource[j][2]);
		
						break;
						}
					else if(j==detailNum)
					{
						System.out.println(FollowSource[i][0]+"在详情页面中不存在.");
						logger.error(FollowSource[i][0]+"在详情页面中不存在.");
						break;
					}		
				}
			}
		return FollowSourceDiff;
	
	}
			else
			{
				System.out.println("Dashboard 和 follower source的日期不等 请看下是不是功能有问题!");
				logger.error("Dashboard 和 follower source的日期不等 请看下是不是功能有问题!");
				FollowSourceDiff[0][0]="日期有问题";
				return FollowSourceDiff;
			}	
	}

}
