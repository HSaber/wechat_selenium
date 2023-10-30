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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DashboardBehavior extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardBehavior.class);
	public String getLineInfo()
    {
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

	 public void dashboardBehavior() throws Exception {
		 //检查排序
		driver.findElement(By.linkText("Show More")).click(); 
		int[] count=new int[8];
		for(int k=1;k<9;k++){ 
			String action=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[2]/div[2]/div[2]/div["+k+"]/div[2]")).getText();
			
			try{
					count[k-1]=catchNum(""," vs",action);
			 	}catch(java.lang.NumberFormatException e){
			 		count[k-1]=0;
			 	}
		}
		
		for (int i = 0; i < 7; i++) {   
	        for (int j = i + 1; j < 8; j++) {   
	            if (count[i] < count[j]) { 
	                Assert.fail("follower behavior所有action 没有 按照从大到小排序！"); 
	            }else{
	            	logger.info("follower behavior所有action是按照从大到小排序！"); 
	            }   
	        }   
	    }   
	}


}
