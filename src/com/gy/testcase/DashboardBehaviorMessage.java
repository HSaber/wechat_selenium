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

public class DashboardBehaviorMessage extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardBehaviorMessage.class);
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
	 public void dashboardBehaviorMessage() throws Exception {
		long[] create = createTime();
		 
		driver.findElement(By.linkText("Show More")).click();
		String message=driver.findElement(By.xpath("//div[@rel='Message']/div[2]")).getText();
		int messageLeft=0;
		int messageRight=0;
		 try{
			 messageLeft=catchNum(""," vs",message);
		 	}catch(java.lang.NumberFormatException e){
		 		messageLeft=0;
		 	}
		
		 try{
			 messageRight=catchNum("vs ","",message);
		 	}catch(java.lang.NumberFormatException e){
		 		messageRight=0;
		 	}
		 
		double a =(double)messageLeft;
		double b =(double)messageRight;
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
		String percent=driver.findElement(By.xpath("//div[@rel='msg']/div[1]/span")).getText();
		try{
			
				Assert.assertEquals(percent, growth+"%");
			
		}catch(Error e){
			
			logger.error("Dashboard message的增长百分比计算错误，请检查！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard message的增长百分比计算错误，请检查！！！";
			
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
			            "type": "message"
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
		//左边
		SearchResponse messageresponse1=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("message", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[0]).lte(create[1])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits messagehits1 = messageresponse1.getHits();
		String esmessage1=messagehits1.getTotalHits()+"";
		logger.info("es中获取到Dashboard左边message查询到记录数=" + esmessage1);
		
		try{
			 Assert.assertEquals(messageLeft+"", esmessage1);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard左边 message查询到记录数=" + esmessage1+"但是页面上显示的是："+messageLeft);
			 error++;
			errorinfo[1]=getLineInfo()+" ,es中获取到Dashboard左边 message查询到记录数=" + esmessage1+"但是页面上显示的是："+messageLeft;
			
		 }
		 for (int i = 0; i < messagehits1.getHits().length; i++) {
		            logger.info("输出"+messagehits1.getHits()[i].getSourceAsString());
		 }
		 //右边
		 SearchResponse messageresponse2=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("message", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[2]).lte(create[3])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits messagehits2 = messageresponse2.getHits();
		String esmessage2=messagehits2.getTotalHits()+"";
		logger.info("es中获取到Dashboard右边 message查询到记录数=" + esmessage2);
		
		try{
			 Assert.assertEquals(messageRight+"", esmessage2);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard右边 message查询到记录数=" + esmessage2+"但是页面上显示的是："+messageRight);
			 error++;
			errorinfo[2]=getLineInfo()+" , es中获取到Dashboard右边 message查询到记录数=" + esmessage2+"但是页面上显示的是："+messageRight;
			
		 }
		 for (int i = 0; i < messagehits2.getHits().length; i++) {
		            logger.info("输出"+messagehits2.getHits()[i].getSourceAsString());
		 }
		 
		 if(error>0){
			 Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n");
		 }
	}
}
