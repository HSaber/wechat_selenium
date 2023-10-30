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

public class DashboardNewFollowers extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardNewFollowers.class);
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
	 public void dashboardNewFollowers() throws Exception {
		long[] create = createTime();
		 
		 
		String newFollows=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]")).getText();
		int newFollowsLeft=0;
		int newFollowsRight=0;
		 try{
			 newFollowsLeft=catchNum(""," vs",newFollows);
		 	}catch(java.lang.NumberFormatException e){
		 		newFollowsLeft=0;
		 	}
		
		 try{
			 newFollowsRight=catchNum("vs ","",newFollows);
		 	}catch(java.lang.NumberFormatException e){
		 		newFollowsRight=0;
		 	}
		 
		double a =(double)newFollowsLeft;
		double b =(double)newFollowsRight;
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
		/*StackTraceElement[] trace = new Throwable().getStackTrace();
		StackTraceElement tmp = trace[0];*/
		
		//StackTraceElement ste = new Throwable().getStackTrace()[1];  http://blog.csdn.net/u011004037/article/details/45224155
		String[] errorinfo=new String[3];
		String percent=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[1]/span")).getText();
		try{
			
				Assert.assertEquals(percent, growth+"%");
			
		}catch(Error e){
			
			logger.error("Dashboard new followers的增长百分比计算错误，请检查！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard new followers的增长百分比计算错误，请检查！！！";
			//Assert.fail("Dashboard new followers的增长百分比计算错误，请检查！！！");
		} 
		
		
		/*{
			  "query": {
			    "bool": {
			      "must": [
			        {
			          "term": {
			            "wid": 33
			          }
			        },
			        {
			          "term": {
			            "type": "subscribe"
			          }
			        },
			        {
			          "range": {
			            "created_date": {
			              "gte": 20170221,
			              "lte": 20170323
			            }
			          }
			        }
			      ]
			    }
			  }
			}*/
		String[] mid=info();
		SearchRequestBuilder responsebuilder = esConnect("user_action_record");
		//new followers左边
		SearchResponse subscriberesponse1=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("subscribe", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[0]).lte(create[1])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits subscribehits1 = subscriberesponse1.getHits();
		String essubscribe1=subscribehits1.getTotalHits()+"";
		logger.info("es中获取到Dashboard左边 new follower查询到记录数=" + essubscribe1);
		
		try{
			 Assert.assertEquals(newFollowsLeft+"", essubscribe1);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard左边 new follower查询到记录数=" + essubscribe1+"但是页面上显示的是："+newFollowsLeft);
			 error++;
			errorinfo[1]=getLineInfo()+" ,es中获取到Dashboard左边 new follower查询到记录数=" + essubscribe1+"但是页面上显示的是："+newFollowsLeft;
			 //Assert.fail("es中获取到Dashboard左边 new follower查询到记录数=" + essubscribe1+"但是页面上显示的是："+newFollowsLeft);
		 }
		 for (int i = 0; i < subscribehits1.getHits().length; i++) {
		            logger.info("输出"+subscribehits1.getHits()[i].getSourceAsString());
		 }
		 //new followers右边
		 SearchResponse subscriberesponse2=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("subscribe", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[2]).lte(create[3])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits subscribehits2 = subscriberesponse2.getHits();
		String essubscribe2=subscribehits2.getTotalHits()+"";
		logger.info("es中获取到Dashboard右边 new follower查询到记录数=" + essubscribe2);
		
		try{
			 Assert.assertEquals(newFollowsRight+"", essubscribe2);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard右边 new follower查询到记录数=" + essubscribe2+"但是页面上显示的是："+newFollowsRight);
			 error++;
			errorinfo[2]=getLineInfo()+" , es中获取到Dashboard右边 new follower查询到记录数=" + essubscribe2+"但是页面上显示的是："+newFollowsRight;
			 //Assert.fail("es中获取到Dashboard右边 new follower查询到记录数=" + essubscribe2+"但是页面上显示的是："+newFollowsRight);
		 }
		 for (int i = 0; i < subscribehits2.getHits().length; i++) {
		            logger.info("输出"+subscribehits2.getHits()[i].getSourceAsString());
		 }
		 
		 if(error>0){
			 Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n");
		 }
	}
}
