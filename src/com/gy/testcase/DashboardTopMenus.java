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

public class DashboardTopMenus extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardTopMenus.class);
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
	 public void dashboardTopMenus() throws Exception {
		 long[] create = createTime();
		 
		 /*String[][] a=new String[2][5];
		 for(int i=1;i<6;i++){
			 String menutitle=null;
			 	try{
			 			 menutitle=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[1]/table/tbody/tr["+i+"]/td[1]/span")).getText();
			 	}catch(Error e){
			 		break;
			 	}
				String menuclicks=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[1]/table/tbody/tr["+i+"]/td[2]")).getText();
				a[0][i-1]=menutitle;
				a[1][i-1]=menuclicks;
		}*/
		
		 
		 String[] mid=info();
		 SearchRequestBuilder responsebuilder = esConnect("user_action_record");
		/* top menu排序
		 /dev_jingsocial_33/user_action_record/_search?search_type=count
				 {
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
			            "type": "menu"
			          }
			        },
			        {
			          "range": {
			            "created_date": {
			              "gte": 20161207,
			              "lte": 20161208
			            }
			          }
			        }
			      ]
			    }
			  },
			  "aggs": {
			    "group_by_keyword": {
			      "terms": {
			        "field": "keyword",
			        "order": {
			          "_count": "desc"
			        }
			      }
			    }
			  }
			}*/
		    TermsBuilder keywordAgg= AggregationBuilders.terms("group_by_keyword").field("keyword").size(5);  
		    responsebuilder.addAggregation(keywordAgg);  
		    SearchResponse menuresponse=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("menu", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[0]).lte(create[1])))
				 .execute().actionGet();
		    
		   /* SearchHits messagehits = menuresponse.getHits();
		    for (int i = 0; i < messagehits.getHits().length; i++) {
	            logger.info("输出"+messagehits.getHits()[i].getSourceAsString());
		    }*/
		    
		    int i=1,j=0;
		    String[] errorinfo=new String[5];
		    String[] menuname= new String[5];
		    long[] menucount=new long[5];
		    Map<String, Aggregation> aggMap = menuresponse.getAggregations().asMap();  
	        StringTerms keywordTerms= (StringTerms) aggMap.get("group_by_keyword");  
	        Iterator<Bucket> keywordBucketIt = keywordTerms.getBuckets().iterator();  
	        while (keywordBucketIt .hasNext()) {  
	            Bucket keywordBucket = keywordBucketIt .next();  
	            
	            //keyword名  
	            String keyword = (String) keywordBucket.getKey();  
	            //记录数  
	            long count = keywordBucket.getDocCount();
	            
	            System.out.println("es中第"+i+"名menu是"+keyword + "，点击" + count +"次。");
	            menuname[i-1]=keyword;
	            menucount[i-1]=count;
	            String dk=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody/tr["+i+"]/td[1]/span")).getText();
	            String dc=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody/tr["+i+"]/td[2]")).getText();
	            try{
	            	assertEquals(dk,keyword);
	            	assertEquals(dc,count+"");
	            }catch(Error e){
	            	j++;
	            	logger.error("darshboard页面上的top menu第"+i+"名中"+dk+" : "+dc+"有问题，跟es中"+keyword+" : "+count+"对不上，请检查！！");
	            	errorinfo[i-1]=getLineInfo()+"darshboard页面上的top menu第"+i+"名中"+dk+" : "+dc+"有问题，跟es中"+keyword+" : "+count+"对不上，请检查！！";
	            }
	            i++;
	        }
	        
	        if(j>0){
	        	Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n"+errorinfo[3]+"\n"+errorinfo[4]+"\n");
	        }else{
	        	//es获取环比值
	        	for(int m=0;m<menuname.length;m++){
		        	SearchResponse menuresponse1=responsebuilder.setQuery(QueryBuilders.boolQuery()
		   				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
		   				 .must(QueryBuilders.multiMatchQuery("menu", "type"))
		   				 .must(QueryBuilders.multiMatchQuery(menuname[m], "keyword"))
		   				 .must(QueryBuilders.rangeQuery("created_date").gte(create[2]).lte(create[3])))
		   				 .execute().actionGet();
		   		    
		   		   /* SearchHits messagehits = menuresponse.getHits();
		   		    for (int i = 0; i < messagehits.getHits().length; i++) {
		   	            logger.info("输出"+messagehits.getHits()[i].getSourceAsString());
		   		    }*/
		   		    
		        	SearchHits menuhits1 = menuresponse1.getHits();
		    		long esmenu1=menuhits1.getTotalHits();
		    		logger.info("es中获取到Dashboard top menu第"+ m+1 +"名"+menuname[m]+"环比点击数是" + esmenu1);
		    		
		    		double a =(double)menucount[m];
		    		double b =(double)esmenu1;
		    		String growth ="";
		    		if(b==0){
		    			growth="--";
		    		}else if(a==0){
		    			growth="0%";
		    		}else{
			    		double g=Math.round((a-b)/b*100);
			    		DecimalFormat df = new DecimalFormat("#");
			    		
			    		if(g<0){
			    			growth=df.format(Math.abs(g))+"%";
			    		}else{
			    			growth=df.format(g)+"%";
			    		}
		    		}
		    		
		    		logger.info("计算出百分比为"+growth);
		    		
		    		String percent=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody/tr["+m+1+"]/td[3]/span")).getText();
		    		try{
		    			Assert.assertEquals(percent, growth);
		    		}catch(Error e){
		    			Assert.fail("es中获取到Dashboard top menu第"+ m+1 +"名"+menuname[m]+"环比百分比是" + growth +"但是页面上是"+percent);
		    		}
		    		
		    		
	    		
	    		}
	    		
	        	
	        }
		 
	 }
}
