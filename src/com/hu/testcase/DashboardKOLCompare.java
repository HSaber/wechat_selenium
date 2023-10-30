package com.hu.testcase;

import jxl.write.Label;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardKOLCompare {
	private static Logger logger = Logger.getLogger(DashboardKOLCompare.class);
	public String[][] KOLCompare(WebDriver driver,String Start,String End) throws InterruptedException,Exception {
		
		//Dashboard Top KOL --------------------------------------------------------------------------------
		methods.navigation(driver, "Dashboard", By.id("followerEngagementChart"));
		String summary;
		int EndNumKOL=0;
		String[][] TopKOL=new String[5][6];
		for(int i=0;i<5;i++)
			for(int j=0;j<TopKOL[i].length;j++)
			{
				if(j==0)
		       	TopKOL[i][j]=driver.findElement(By.xpath(".//*[@id='top_5kols']/div[1]/div[2]/table/tbody/tr["+(i+1)+"]/td["+(j+1)+"]/span/a/span")).getText();	
				else if(j==5)
					TopKOL[i][j]=driver.findElement(By.xpath(".//*[@id='top_5kols']/div[1]/div[2]/table/tbody/tr["+(i+1)+"]/td["+(j+1)+"]")).getText().substring(1);
				else
					TopKOL[i][j]=driver.findElement(By.xpath(".//*[@id='top_5kols']/div[1]/div[2]/table/tbody/tr["+(i+1)+"]/td["+(j+1)+"]")).getText();
			
			}
		
		for (int i = 0; i < TopKOL.length; i++) {      
            for (int j = 0; j < TopKOL[i].length; j++) {   
            //循环遍历数组中的每个元素                      
                //初始化数组内容       
            	if(!(TopKOL[i][j] == null || TopKOL[i][j].length() <= 0))
            	{
                System.out.print("TopKOL["+i+"]["+j+"]="+TopKOL[i][j]+"  ");
                EndNumKOL=i+1;
            	}
                //将数组中的元素输出  
            }  
            System.out.println("   ");                	            	            
	}	
	
		driver.findElement(By.xpath(".//*[@id='top_5kols']/div[1]/div[1]/a")).click();
		while(true){
			if(methods.isElementPresent(driver, By.id("follower-kol-grid")))
				break;
			}
		int detailKOL=0;
		String[][] KOLDiff=new String[EndNumKOL][6];
		if(driver.findElement(By.className("mobile_table")).getText().contains("No results found."))
		{
			for (int i = 0; i < KOLDiff.length; i++) {      
	            for (int j = 0; j < KOLDiff[i].length; j++) {   
	            	KOLDiff[i][j]="DetailNoData";
	                //将数组中的元素输出  
	            }           	            	            
		}	
		}
		else{
		 summary=driver.findElement(By.className("summary")).getText();
		String[] numKOL=methods.Getnum(summary);
		if(Integer.parseInt(numKOL[2])>10)
			detailKOL=10;
		else
		detailKOL=Integer.parseInt(numKOL[2]);
	String[][] DetailKOL=new String[detailKOL][6];
	for(int i=1;i<DetailKOL.length;i++){
		for(int j=1;j<DetailKOL[i].length;j++)
		{
			DetailKOL[i][j]=driver.findElement(By.xpath(".//*[@id='follower-kol-grid']/div[1]/table/tbody/tr["+i+"]/td["+j+"]")).getText();
			if(j==5)
				DetailKOL[i][j]=driver.findElement(By.xpath(".//*[@id='follower-kol-grid']/div[1]/table/tbody/tr["+i+"]/td["+j+"]")).getText().substring(1);
		}
	}
	for (int i = 0; i < DetailKOL.length; i++) {      
        for (int j = 0; j < DetailKOL[i].length; j++) {   
        //循环遍历数组中的每个元素                      
            //初始化数组内容       
        	if(!(DetailKOL[i][j] == null || DetailKOL[i][j].length() <= 0))	            	
            System.out.print("DetailKOL["+i+"]["+j+"]="+DetailKOL[i][j]+"  ");	            	
            //将数组中的元素输出  
        }  
        System.out.println("   ");                	            	            
        }
	
	if(EndNumKOL>DetailKOL.length)
	{
		System.out.println("KOL detail页面缺少"+(EndNumKOL-DetailKOL.length)+"天数据");
		logger.error("KOL detail页面缺少"+(EndNumKOL-DetailKOL.length)+"天数据");
	//	EndNumKOL=DetailKOL.length;
	}
	System.out.println("TopKOL 长度:"+TopKOL.length+"DetailKOL ="+DetailKOL.length+" KOLDiff ="+KOLDiff.length+" EndNumKOL="+EndNumKOL);
	for(int i=0;i<EndNumKOL;i++){
		for(int j=0;j<DetailKOL.length;j++)
		{
			if(TopKOL[i][0].equals(DetailKOL[j][0]))
			{
				KOLDiff[i][0]=TopKOL[i][0];
				for(int t=1;t<6;t++)
				{
					System.out.println("TopKOL["+i+"]["+t+"]="+TopKOL[i][t]+"  DetailKOL["+j+"]["+t+"]"+DetailKOL[j][t]+"   ");
					KOLDiff[i][t]=methods.Comparison(TopKOL[i][t], DetailKOL[j][t]);
				}
				break;
			}
			else if((j+1)==DetailKOL.length){
				KOLDiff[i][0]=TopKOL[i][0];
				for(int t=1;t<6;t++)
				{
					System.out.println("TopKOL["+i+"]["+t+"]="+TopKOL[i][t]+"  DetailKOL["+j+"]["+t+"]"+DetailKOL[j][t]+"   ");
					KOLDiff[i][t]="NoData";
				}
				System.out.println(TopKOL[i][0]+" 没有匹配到!");
				logger.error(TopKOL[i][0]+" 没有匹配到!");
				break;
			}
		}
	}
	}
		return KOLDiff;
	}

}
