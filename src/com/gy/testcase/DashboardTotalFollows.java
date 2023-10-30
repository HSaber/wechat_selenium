package com.gy.testcase;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class DashboardTotalFollows extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardTotalFollows.class);
	public String getLineInfo(){
		
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
	/* @BeforeMethod
	public  void setUp() throws Exception {
		 
		
	  }

  @AfterMethod
	public  void tearDown() throws Exception {
		
	  
 }*/
	
	 @Test
	 public void dashboardTotalFollows() throws Exception {
		 long[] create = createTime();
		 
		String totalFollows=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[2]")).getText();
		int totalFollowsLeft=0;
		int totalFollowsRight=0;
		 try{
			 totalFollowsLeft=catchNum(""," vs",totalFollows);
		 	}catch(java.lang.NumberFormatException e){
		 		totalFollowsLeft=0;
		 	}
		
		 try{
			 totalFollowsRight=catchNum("vs ","",totalFollows);
		 	}catch(java.lang.NumberFormatException e){
		 		totalFollowsRight=0;
		 	}
		 
		double a =(double)totalFollowsLeft;
		double b =(double)totalFollowsRight;
		double g=Math.round((a-b)/b*1000)*0.1;
		DecimalFormat df = new DecimalFormat("#.#");
		String growth ="";
		if(g<0){
			growth=df.format(Math.abs(g))+"";
		}else{
			growth=df.format(g)+"";
		}
		
		logger.info("计算出百分比为"+g);
		
		
		String zero=growth.substring(growth.length()-1);
		logger.info("百分比小数点后一位的值是： "+zero);
		if(zero.equals("0")){
			
			growth=growth.substring(0,growth.length()-2);
			logger.info("zero是0，得到的百分比值是： "+growth);
		}
		
		logger.info("growth的值是： "+growth);
		
		int error =0;
		String[] errorinfo=new String[3];
		String percent=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[1]/span")).getText();
		try{
			
				Assert.assertEquals(percent, growth+"%");
			
		}catch(Error e){
			
			logger.error("Dashboard totalfollowers的增长百分比计算错误，请检查！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard totalfollowers的增长百分比计算错误，请检查！！！";
			
		} 
		
		
		//es没有user_actions_index这个表，需要去mysql查询
	}
}
