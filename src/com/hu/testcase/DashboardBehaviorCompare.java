package com.hu.testcase;

import java.text.NumberFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardBehaviorCompare {
	private static Logger logger = Logger.getLogger(DashboardBehaviorCompare.class);

	public String BehaviorComparison(WebDriver driver,String Start,String End) throws InterruptedException,Exception {
		 //获取格式化对象
		   NumberFormat nt = NumberFormat.getPercentInstance();			   
		   NumberFormat nf = NumberFormat.getPercentInstance();
		   NumberFormat no = NumberFormat.getPercentInstance();
		   //设置百分数精确度2即保留两位小数
		   no.setMinimumFractionDigits(1);
		   nt.setMinimumFractionDigits(2);
		   nf.setMinimumFractionDigits(3);
		 //dashboard Action Source ----------------------------------------------------------------------------------------------	
		String WechatBehavior=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[1]/div[1]/div/div[2]/table/tbody/tr[2]/td[2]")).getText();
	//Wechat Behavior Breakdown-----------------------------------------------------------------------------------------------
		driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[2]/div[5]/a")).click();
		while(true){
			if(methods.isElementPresent(driver, By.xpath(".//*[@id='post_analytics']/div/div[2]/div[2]/div[2]/div[2]/div[5]/h4")))
				break;
		}
		String[][] ActionsArr=new String[8][2];
		for(int i=0;i<ActionsArr.length;i++){
			ActionsArr[i][0]=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[2]/div[2]/div[2]/div["+(i+1)+"]/h4")).getText();
			ActionsArr[i][1]=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[2]/div[2]/div[2]/div["+(i+1)+"]/div[2]")).getText();
			ActionsArr[i][1]=methods.valueFilter(ActionsArr[i][1])[0];
		}
	
		float ActionsSum=0;
		for (int i = 0; i < ActionsArr.length; i++) {      
            for (int j = 0; j < ActionsArr[i].length; j++) {   
            //循环遍历数组中的每个元素                      
                //初始化数组内容             	
            	
                System.out.print("ActionsArr["+i+"]["+j+"]="+ActionsArr[i][j]+"  ");               	
                //将数组中的元素输出  
            }
            ActionsSum=Float.parseFloat(ActionsArr[i][1])+ActionsSum;
            System.out.println("   ");                	            	            
	}
		float ActionsDiff=(Float.parseFloat(WechatBehavior)-ActionsSum)/Float.parseFloat(WechatBehavior);
		System.out.println("WechatBehavior="+WechatBehavior+"   ActionsSum="+ActionsSum+" 误差:"+nt.format(ActionsDiff));	
		return nt.format(ActionsDiff);
		
	}
	}

