package com.gy.testcase;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardBehaviorPost extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardBehaviorPost.class);
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
	 public void dashboardBehaviorPost() throws Exception {
		long[] create = createTime();
		 
		driver.findElement(By.linkText("Show More")).click(); 
		String post=driver.findElement(By.xpath("//div[@rel='post']/div[2]")).getText();
		int postLeft=0;
		int postRight=0;
		 try{
			 postLeft=catchNum(""," vs",post);
		 	}catch(java.lang.NumberFormatException e){
		 		postLeft=0;
		 	}
		
		 try{
			 postRight=catchNum("vs ","",post);
		 	}catch(java.lang.NumberFormatException e){
		 		postRight=0;
		 	}
		 
		double a =(double)postLeft;
		double b =(double)postRight;
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
		String percent=driver.findElement(By.xpath("//div[@rel='post']/div[1]/span")).getText();
		try{
			
				Assert.assertEquals(percent, growth+"%");
			
		}catch(Error e){
			
			logger.error("Dashboard behavior post的增长百分比计算错误，请检查！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard behavior post的增长百分比计算错误，请检查！！！";
			
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
          "range": {
            "created_date": {
              "gte": 20170314,
              "lte": 20170412
            }
          }
        },
        {
          "bool": {
            "should": [
              {
                "term": {
                  "type": "read"
                }
              },
              {
                "term": {
                  "type": "readmore"
                }
              },
              {
                "term": {
                  "type": "share_post"
                }
              },
              {
                "term": {
                  "type": "shares_reads"
                }
              },
              {
                "term": {
                  "type": "post_link_click"
                }
              }
            ]
          }
        }
      ]
    }
  }
}*/
		String[] mid=info();
		SearchRequestBuilder responsebuilder = esConnect("user_action_record");
		//左边
		SearchResponse postresponse1=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.boolQuery()
						 .should(QueryBuilders.multiMatchQuery("read", "type"))
						 .should(QueryBuilders.multiMatchQuery("readmore", "type"))
						 .should(QueryBuilders.multiMatchQuery("share_post", "type"))
						 .should(QueryBuilders.multiMatchQuery("shares_reads", "type"))
						 .should(QueryBuilders.multiMatchQuery("post_link_click", "type")))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[0]).lte(create[1])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits posthits1 = postresponse1.getHits();
		String espost1=posthits1.getTotalHits()+"";
		logger.info("es中获取到Dashboard behavior左边post查询到记录数=" + espost1);
		
		try{
			 Assert.assertEquals(postLeft+"", espost1);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard behavior左边 post查询到记录数=" + espost1+"但是页面上显示的是："+postLeft);
			 error++;
			errorinfo[1]=getLineInfo()+" ,es中获取到Dashboard behavior左边 post查询到记录数=" + espost1+"但是页面上显示的是："+postLeft;
			
		 }
		 for (int i = 0; i < posthits1.getHits().length; i++) {
		            logger.info("输出"+posthits1.getHits()[i].getSourceAsString());
		 }
		 //右边
		 SearchResponse postresponse2=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.boolQuery()
						 .should(QueryBuilders.multiMatchQuery("read", "type"))
						 .should(QueryBuilders.multiMatchQuery("readmore", "type"))
						 .should(QueryBuilders.multiMatchQuery("share_post", "type"))
						 .should(QueryBuilders.multiMatchQuery("shares_reads", "type"))
						 .should(QueryBuilders.multiMatchQuery("post_link_click", "type")))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[2]).lte(create[3])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits posthits2 = postresponse2.getHits();
		String espost2=posthits2.getTotalHits()+"";
		logger.info("es中获取到Dashboard behavior右边 post查询到记录数=" + espost2);
		
		try{
			 Assert.assertEquals(postRight+"", espost2);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard behavior右边 post查询到记录数=" + espost2+"但是页面上显示的是："+postRight);
			 error++;
			errorinfo[2]=getLineInfo()+" , es中获取到Dashboard behavior右边 post查询到记录数=" + espost2+"但是页面上显示的是："+postRight;
			
		 }
		 for (int i = 0; i < posthits2.getHits().length; i++) {
		            logger.info("输出"+posthits2.getHits()[i].getSourceAsString());
		 }
		 
		 if(error>0){
			 Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n");
		 }
	}
}
