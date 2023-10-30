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

public class DashboardActionSourceBehavior extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardActionSourceBehavior.class);
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
	 public void dashboardActionSourceBehavior() throws Exception {
		long[] create = createTime();
		 
		String behavior=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[1]/div[1]/div/div[2]/table/tbody/tr[2]/td[2]")).getText();
		
		
		String[] mid=info();
		SearchRequestBuilder responsebuilder = esConnect("user_action_record");
		//当前日期
		SearchResponse behaviorresponse1=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.boolQuery()
						 .should(QueryBuilders.multiMatchQuery("qrcode_scan", "type"))
						 .should(QueryBuilders.multiMatchQuery("unsubscribe", "type"))
						 .should(QueryBuilders.multiMatchQuery("subscribe", "type"))
						 .should(QueryBuilders.multiMatchQuery("menu", "type"))
						 .should(QueryBuilders.multiMatchQuery("get_coupon", "type"))
						 .should(QueryBuilders.multiMatchQuery("use_coupon", "type"))
						 .should(QueryBuilders.multiMatchQuery("message", "type"))
						 .should(QueryBuilders.multiMatchQuery("read", "type"))
						 .should(QueryBuilders.multiMatchQuery("lbs", "type"))
						 .should(QueryBuilders.multiMatchQuery("readmore", "type"))
						 .should(QueryBuilders.multiMatchQuery("shares_reads", "type"))
						 .should(QueryBuilders.multiMatchQuery("share_behavior", "type"))
						 .should(QueryBuilders.multiMatchQuery("behavior_link_click", "type"))
						 .should(QueryBuilders.multiMatchQuery("vote", "type")))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[0]).lte(create[1])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits behaviorhits1 = behaviorresponse1.getHits();
		String esbehavior1=behaviorhits1.getTotalHits()+"";
		logger.info("es中获取到Dashboard wechat behavior查询到记录数=" + esbehavior1);
		
		try{
			 Assert.assertEquals(behavior+"", esbehavior1);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard wechat behavior查询到记录数=" + esbehavior1+"但是页面上显示的是："+behavior);
			Assert.fail("es中获取到Dashboard wechat behavior查询到记录数=" + esbehavior1+"但是页面上显示的是："+behavior);
			
		 }
		 for (int i = 0; i < behaviorhits1.getHits().length; i++) {
		            logger.info("输出"+behaviorhits1.getHits()[i].getSourceAsString());
		 }
		 //环比数
		 SearchResponse behaviorresponse2=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.boolQuery()
						 .should(QueryBuilders.multiMatchQuery("qrcode_scan", "type"))
						 .should(QueryBuilders.multiMatchQuery("unsubscribe", "type"))
						 .should(QueryBuilders.multiMatchQuery("subscribe", "type"))
						 .should(QueryBuilders.multiMatchQuery("menu", "type"))
						 .should(QueryBuilders.multiMatchQuery("get_coupon", "type"))
						 .should(QueryBuilders.multiMatchQuery("use_coupon", "type"))
						 .should(QueryBuilders.multiMatchQuery("message", "type"))
						 .should(QueryBuilders.multiMatchQuery("read", "type"))
						 .should(QueryBuilders.multiMatchQuery("lbs", "type"))
						 .should(QueryBuilders.multiMatchQuery("readmore", "type"))
						 .should(QueryBuilders.multiMatchQuery("shares_reads", "type"))
						 .should(QueryBuilders.multiMatchQuery("share_behavior", "type"))
						 .should(QueryBuilders.multiMatchQuery("behavior_link_click", "type"))
						 .should(QueryBuilders.multiMatchQuery("vote", "type")))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[2]).lte(create[3])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits behaviorhits2 = behaviorresponse2.getHits();
		String esbehavior2=behaviorhits2.getTotalHits()+"";
		logger.info("es中获取到Dashboard wechat behavior查询到环比记录数=" + esbehavior2);
		double a =Double.parseDouble(behavior);
		double b =Double.parseDouble(esbehavior2);
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
		String percent=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[1]/div[1]/div/div[2]/table/tbody/tr[2]/td[4]/span")).getText();
		try{
			 Assert.assertEquals(percent, growth+"%");
		 }catch(Error e){
			 logger.error("es中计算出Dashboard wechat behavior增长百分比是：" + growth+"但是页面上显示的是："+percent);
			Assert.fail("es中计算出Dashboard wechat behavior增长百分比是：" + growth+"但是页面上显示的是："+percent);
			
		 }
		 
	}
}
