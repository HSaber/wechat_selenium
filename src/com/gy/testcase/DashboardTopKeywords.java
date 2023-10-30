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

public class DashboardTopKeywords extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardTopKeywords.class);
	
	 @Test
	 public void dashboardTopKeywords() throws Exception {
		 long[] create = createTime();
		 
		 String[][] a=new String[5][2];
		 for(int i=1;i<6;i++){
			 String keyword=null;
			 	try{
			 		keyword=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[1]/div[1]/table/tbody/tr["+i+"]/td[1]/span")).getText();
			 	}catch(Error e){
			 		break;
			 	}
				String keywordTriggered=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[1]/div[1]/table/tbody/tr["+i+"]/td[2]")).getText();
				a[i-1][0]=keyword;
				a[i-1][1]=keywordTriggered;
		}
		 
		 String[] mid=info();
		 SearchRequestBuilder responsebuilder = esConnect("message");
		/* top keyword排序
		 /dev_jingsocial_33/message/_search?search_type=count
			{
	  "query": {
	    "bool": {
	      "must": [
	        {
	          "term": {
	            "mid": 33
	          }
	        },
	        {
	          "term": {
	            "is_keyword": 1
	          }
	        },
	        {
	          "bool": {
	            "must_not": {
	              "term": {
	                "keyword_id": 0
	              }
	            }
	          }
	        },
	        {
	          "range": {
	            "create_time": {
	              "gte": "1489420800",
	              "lte": "1492012799"
	            }
	          }
	        }
	      ]
	    }
	  },
	  "aggs": {
	    "group_by_keyword": {
	      "terms": {
	        "field": "keyword_id",
	        "order": {
	          "_count": "desc"
	        }
	      }
	    }
	  }
	}*/
		
		 TermsBuilder keywordAgg= AggregationBuilders.terms("group_by_keyword").field("keyword_id").size(5);  
		    responsebuilder.addAggregation(keywordAgg);  
		    SearchResponse keywordresponse=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "mid"))
				 .must(QueryBuilders.multiMatchQuery("1", "is_keyword"))
				 .must(QueryBuilders.boolQuery()
						 .mustNot(QueryBuilders.multiMatchQuery("0", "keyword_id")))
				 .must(QueryBuilders.rangeQuery("create_time").gte(create[4]).lte(create[5])))
				 .execute().actionGet();
		    
		   /* SearchHits messagehits = keywordresponse.getHits();
		    for (int i = 0; i < messagehits.getHits().length; i++) {
	            logger.info("输出"+messagehits.getHits()[i].getSourceAsString());
		    }*/
		    
		    int i=1,j=0;
		    String[][] aes=new String[2][5];
		    Map<String, Aggregation> aggMap = keywordresponse.getAggregations().asMap();  
	        StringTerms keywordTerms= (StringTerms) aggMap.get("group_by_keyword");  
	        Iterator<Bucket> keywordBucketIt = keywordTerms.getBuckets().iterator();  
	        while (keywordBucketIt .hasNext()) {  
	            Bucket keywordBucket = keywordBucketIt .next();  
	            
	            //keyword名  
	            String keyword = (String) keywordBucket.getKey();  
	            //记录数  
	            String count = keywordBucket.getDocCount()+"";
	            
	            System.out.println("es中keyword是"+keyword + "的有" + count +"个人。");
	            aes[0][i-1]=keyword;
				aes[1][i-1]=count;
	           /* //String dk=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[1]/table/tbody/tr["+i+"]/td[1]/span")).getText();
	            //String dc=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[1]/table/tbody/tr["+i+"]/td[2]")).getText();
	            try{
	            	assertEquals(dk,keyword);
	            	assertEquals(dc,count);
	            }catch(Error e){
	            	j++;
	            	logger.error("darshboard页面上的top keyword前5名中"+dk+" : "+dc+"有问题，跟es对不上，请检查！！");
	            }*/
	            i++;
	        }
	        
	        SearchRequestBuilder responsebuilder1 = esConnect("message");
	        
	        if(j>0){
	        	fail("darshboard页面上的top keyword前5名跟es中对不上，具体看log");
	        }
	 }
}
