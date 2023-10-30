package com.gy.testcase;

import java.text.DecimalFormat;
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

public class SegmentorAllFollowerStatus extends TestBase{
	private static Logger logger = Logger.getLogger(SegmentorAllFollowerStatus.class);
	
	 @Test
	 public void segmentorAllFollowerStatus() throws Exception {
		 navigation("Segmentor");
		 
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/label")).click();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div/div[1]")).click();
		 boolean status=false;
		 status=isElementPresent(By.xpath(".//*[@id='charts']/div/div[1]/div/div[1]/div[1]/div/div[2]/table/tbody/tr[2]/td[1]/span[2]"));
		 while(!status){
			 status=isElementPresent(By.xpath(".//*[@id='charts']/div/div[1]/div/div[1]/div[1]/div/div[2]/table/tbody/tr[2]/td[1]/span[2]"));
		 }
		 int i=0;
		 String follower = driver.findElement(By.xpath(".//*[@id='charts']/div/div[1]/div/div[1]/div[1]/div/div[2]/table/tbody/tr[2]/td[2]")).getText();
		 try{
			 	assertEquals("0%",driver.findElement(By.xpath(".//*[@id='charts']/div/div[1]/div/div[1]/div[1]/div/div[2]/table/tbody/tr[2]/td[3]/span")).getText());
		 }catch(Error e){
			 logger.error("segmentor选all follower的时候，所有右边的百分比应该均为0，follower status 里follower右边百分比不是，请检查！");
			 i++;
		 }
		 String unFollower = driver.findElement(By.xpath(".//*[@id='charts']/div/div[1]/div/div[1]/div[1]/div/div[2]/table/tbody/tr[3]/td[2]")).getText();
		 try{
			 	assertEquals("0%",driver.findElement(By.xpath(".//*[@id='charts']/div/div[1]/div/div[1]/div[1]/div/div[2]/table/tbody/tr[3]/td[3]/span")).getText());
		 }catch(Error e){
			 logger.error("segmentor选all follower的时候，所有右边的百分比应该均为0，follower status 里unFollowers右边百分比不是，请检查！");
			 i++;
		 }
		 
		 
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
			  },
			  "aggs": {
			    "all_groupby_subscribe": {
			      "terms": {
			        "field": "subscribe"
			      }
			    }
			  }
			}*/
		 TermsBuilder subscribeAgg= AggregationBuilders.terms("all_groupby_subscribe").field("subscribe");  
		 responsebuilder.addAggregation(subscribeAgg);  
		 SearchResponse subscriberesponse=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "mid")))
				 .execute().actionGet();
		 
		 SearchHits followerhits = subscriberesponse.getHits();
		 double esfollower=followerhits.getTotalHits();
		 logger.info(esfollower);
		
		    
		    Map<String, Aggregation> aggMap = subscriberesponse.getAggregations().asMap();  
	        StringTerms subscribeTerms= (StringTerms) aggMap.get("all_groupby_subscribe");  
	        Iterator<Bucket> subscribeBucketIt = subscribeTerms.getBuckets().iterator();  
	        while (subscribeBucketIt .hasNext()) {  
	            Bucket subscribeBucket = subscribeBucketIt .next();  
	            
	            //subscribe类型 
	            String subscribe = (String) subscribeBucket.getKey(); 
	            logger.info(subscribe);
	            //subscribe类型 对应人数
	            double count = subscribeBucket.getDocCount();
	           
	            double a=Math.round((count/esfollower)*1000)*0.1;
	            DecimalFormat df = new DecimalFormat("#.#");
	            
	            String percent=df.format(a)+"%";
	            if(subscribe.equals("1")){
	            	try{
	            			assertEquals(percent,follower);
	            	}catch(Error e){
	            		logger.error("es查询出的follower百分比是"+percent+"，显示的是"+follower);
	            		i++;
	            	}
	            }else if(subscribe.equals("0")){
	            	try{
            			assertEquals(percent,unFollower);
	            	}catch(Error e){
	            		logger.error("es查询出的unfollower百分比是"+percent+"，显示的是"+unFollower);
	            		i++;
	            	}
	            }else{
	            	logger.error("es查询subscribe的状态不是0也不是1，请确认！");
	            	i++;
	            }
	            
	        }
	        
	        if(i>0){
	        	fail("segmentor all follower下follower status跟es中对不上，具体看log");
	        }
	 }

}
