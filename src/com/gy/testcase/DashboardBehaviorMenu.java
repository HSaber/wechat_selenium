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

public class DashboardBehaviorMenu extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardBehaviorMenu.class);
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
	 public void dashboardBehaviorMenu() throws Exception {
		long[] create = createTime();
		 
		driver.findElement(By.linkText("Show More")).click(); 
		String menu=driver.findElement(By.xpath("//div[@rel='Menu']/div[2]")).getText();
		int menuLeft=0;
		int menuRight=0;
		 try{
			 menuLeft=catchNum(""," vs",menu);
		 	}catch(java.lang.NumberFormatException e){
		 		menuLeft=0;
		 	}
		
		 try{
			 menuRight=catchNum("vs ","",menu);
		 	}catch(java.lang.NumberFormatException e){
		 		menuRight=0;
		 	}
		 
		double a =(double)menuLeft;
		double b =(double)menuRight;
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
		String percent=driver.findElement(By.xpath("//div[@rel='Menu']/div[1]/span")).getText();
		try{
			
				Assert.assertEquals(percent, growth+"%");
			
		}catch(Error e){
			
			logger.error("Dashboard menu的增长百分比计算错误，请检查！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard menu的增长百分比计算错误，请检查！！！";
			
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
			            "type": "menu"
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
		SearchResponse menuresponse1=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("menu", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[0]).lte(create[1])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits menuhits1 = menuresponse1.getHits();
		String esmenu1=menuhits1.getTotalHits()+"";
		logger.info("es中获取到Dashboard左边menu查询到记录数=" + esmenu1);
		
		try{
			 Assert.assertEquals(menuLeft+"", esmenu1);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard左边 menu查询到记录数=" + esmenu1+"但是页面上显示的是："+menuLeft);
			 error++;
			errorinfo[1]=getLineInfo()+" ,es中获取到Dashboard左边 menu查询到记录数=" + esmenu1+"但是页面上显示的是："+menuLeft;
			
		 }
		 for (int i = 0; i < menuhits1.getHits().length; i++) {
		            logger.info("输出"+menuhits1.getHits()[i].getSourceAsString());
		 }
		 //右边
		 SearchResponse menuresponse2=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("menu", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[2]).lte(create[3])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits menuhits2 = menuresponse2.getHits();
		String esmenu2=menuhits2.getTotalHits()+"";
		logger.info("es中获取到Dashboard右边 menu查询到记录数=" + esmenu2);
		
		try{
			 Assert.assertEquals(menuRight+"", esmenu2);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard右边 menu查询到记录数=" + esmenu2+"但是页面上显示的是："+menuRight);
			 error++;
			errorinfo[2]=getLineInfo()+" , es中获取到Dashboard右边 menu查询到记录数=" + esmenu2+"但是页面上显示的是："+menuRight;
			
		 }
		 for (int i = 0; i < menuhits2.getHits().length; i++) {
		            logger.info("输出"+menuhits2.getHits()[i].getSourceAsString());
		 }
		 
		 if(error>0){
			 Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n");
		 }
	}
}
