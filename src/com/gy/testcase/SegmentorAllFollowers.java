package com.gy.testcase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

public class SegmentorAllFollowers extends TestBase{
	private static Logger logger = Logger.getLogger(SegmentorAllFollowers.class);
	
	 @Test
	 public void segmentorAllFollowers() throws Exception {
		 navigation("Segmentor");
		 
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/label")).click();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div/div[1]")).click();
		 boolean status=false;
		 status=isElementPresent(By.xpath(".//*[@id='count_block']/div/div/div/div[1]/div"));
		 while(!status){
			 status=isElementPresent(By.xpath(".//*[@id='count_block']/div/div/div/div[1]/div"));
		 }
		 
		 String show = driver.findElement(By.xpath(".//*[@id='count_block']/div/div/div/div[1]/div")).getText();
		 
		 String[] mid=info();
		 SearchRequestBuilder responsebuilder = esConnect("wechat_customer");
		 
		/* {
			  "query": {
			    "filtered": {
			      "filter": {
			        "bool": {
			          "must": [
			            {
			              "term": {
			                "mid": "59"
			              }
			            }
			          ]
			        }
			      }
			    }
			  }
			}*/
		 
		 SearchResponse followerresponse=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "mid")))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		 
		 SearchHits followerhits = followerresponse.getHits();
		 String esfollower=followerhits.getTotalHits()+"";
		 logger.info("es中获取到all follower的人数是" + esfollower);
		 try{
			 Assert.assertEquals(show, "Found "+esfollower+" Matches 100.00% of all Followers");
		 }catch(Error e){
			 logger.info("es中获取到all follower的人数是" + esfollower+"但是页面上显示的是："+show);
			 Assert.fail("es中获取到all follower的人数是" + esfollower+"但是页面上显示的是："+show);
		 }
		
	 }
}
