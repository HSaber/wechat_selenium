package com.gy.testcase;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardBehaviorLBS extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardBehaviorLBS.class);
	/*@BeforeMethod
	public  void setUp() throws Exception {
		 
		
	  }

	@AfterMethod
	public  void tearDown() throws Exception {
		
	  
	}*/
	
	public String getLineInfo()
    {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
	 
	
	
	 @Test
	 public void dashboardBehaviorLBS() throws Exception {
		long[] create = createTime();
		driver.findElement(By.linkText("Show More")).click();
		//System.out.println("=============="+driver.findElement(By.xpath("//div[@rel='msg']/div[2]")).getText()); 
		String lBS=driver.findElement(By.xpath("//div[@rel='lbs']/div[2]")).getText();
		int lBSLeft=0;
		int lBSRight=0;
		 try{
			 lBSLeft=catchNum(""," vs",lBS);
		 	}catch(java.lang.NumberFormatException e){
		 		lBSLeft=0;
		 	}
		
		 try{
			 lBSRight=catchNum("vs ","",lBS);
		 	}catch(java.lang.NumberFormatException e){
		 		lBSRight=0;
		 	}
		 
		double a =(double)lBSLeft;
		double b =(double)lBSRight;
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
		String percent=driver.findElement(By.xpath("//div[@rel='lbs']/div[1]/span")).getText();
		try{
			
				Assert.assertEquals(percent, growth+"%");
			
		}catch(Error e){
			
			logger.error("Dashboard LBS的增长百分比计算错误，请检查！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard LBS的增长百分比计算错误，请检查！！！";
			
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
			            "type": "lbs"
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
		SearchResponse lbsresponse1=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("lbs", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[0]).lte(create[1])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits lbshits1 = lbsresponse1.getHits();
		String eslbs1=lbshits1.getTotalHits()+"";
		logger.info("es中获取到Dashboard左边lbs查询到记录数=" + eslbs1);
		
		try{
			 Assert.assertEquals(lBSLeft+"", eslbs1);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard左边 lbs查询到记录数=" + eslbs1+"但是页面上显示的是："+lBSLeft);
			 error++;
			errorinfo[1]=getLineInfo()+" ,es中获取到Dashboard左边 lbs查询到记录数=" + eslbs1+"但是页面上显示的是："+lBSLeft;
			
		 }
		 for (int i = 0; i < lbshits1.getHits().length; i++) {
		            logger.info("输出"+lbshits1.getHits()[i].getSourceAsString());
		 }
		 //右边
		 SearchResponse lbsresponse2=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("lbs", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[2]).lte(create[3])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits lbshits2 = lbsresponse2.getHits();
		String eslbs2=lbshits2.getTotalHits()+"";
		logger.info("es中获取到Dashboard右边 lbs查询到记录数=" + eslbs2);
		
		try{
			 Assert.assertEquals(lBSRight+"", eslbs2);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard右边 lbs查询到记录数=" + eslbs2+"但是页面上显示的是："+lBSRight);
			 error++;
			errorinfo[2]=getLineInfo()+" , es中获取到Dashboard右边 lbs查询到记录数=" + eslbs2+"但是页面上显示的是："+lBSRight;
			
		 }
		 for (int i = 0; i < lbshits2.getHits().length; i++) {
		            logger.info("输出"+lbshits2.getHits()[i].getSourceAsString());
		 }
		 
		 if(error>0){
			 Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n");
		 }
	}
}
