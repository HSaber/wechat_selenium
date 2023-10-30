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

public class DashboardActionSourceEcommerce extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardActionSourceEcommerce.class);
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
	 public void dashboardActionSourceEcommerce() throws Exception {
		long[] create = createTime();
		 
		String ecommerce=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[1]/div[1]/div/div[2]/table/tbody/tr[4]/td[2]")).getText();
		logger.info("+"+ecommerce+"+");
		

		String[] mid=info();
		SearchRequestBuilder responsebuilder = esConnect("user_action_record");
		//当前日期
		SearchResponse ecommerceresponse1=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("ecommerce", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[0]).lte(create[1])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits ecommercehits1 = ecommerceresponse1.getHits();
		String esecommerce1=ecommercehits1.getTotalHits()+"";
		logger.info("es中获取到Dashboard wechat ecommerce查询到记录数=" + esecommerce1);
		if(esecommerce1.equals("0")){
			String style=driver.findElement(By.xpath(".//*[@id='post_analytics']/div/div[2]/div[1]/div[1]/div/div[2]/table/tbody/tr[4]")).getAttribute("style");
			try{
				 Assert.assertEquals(style, "display: none;");
			}catch(Error e){
				logger.error("es中获取到Dashboard wechat ecommerce查询到记录数是0，页面不能显示!!!!");
				Assert.fail("es中获取到Dashboard wechat ecommerce查询到记录数是0，页面不能显示!!!!");
			}
		}else{
		
		try{
			 Assert.assertEquals(ecommerce+"", esecommerce1);
		 }catch(Error e){
			 logger.error("es中获取到Dashboard wechat ecommerce查询到记录数=" + esecommerce1+"但是页面上显示的是："+ecommerce);
			Assert.fail("es中获取到Dashboard wechat ecommerce查询到记录数=" + esecommerce1+"但是页面上显示的是："+ecommerce);
			
		 }
		 for (int i = 0; i < ecommercehits1.getHits().length; i++) {
		            logger.info("输出"+ecommercehits1.getHits()[i].getSourceAsString());
		 }
		 //环比数
		 SearchResponse ecommerceresponse2=responsebuilder.setQuery(QueryBuilders.boolQuery()
				 .must(QueryBuilders.multiMatchQuery(mid[2], "wid"))
				 .must(QueryBuilders.multiMatchQuery("ecommerce", "type"))
				 .must(QueryBuilders.rangeQuery("created_date").gte(create[2]).lte(create[3])))
				 .setFrom(0).setSize(1).setExplain(true).execute().actionGet();
		
		SearchHits ecommercehits2 = ecommerceresponse2.getHits();
		String esecommerce2=ecommercehits2.getTotalHits()+"";
		logger.info("es中获取到Dashboard wechat ecommerce查询到环比记录数=" + esecommerce2);
		double a =Double.parseDouble(ecommerce);
		double b =Double.parseDouble(esecommerce2);
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
			 logger.error("es中计算出Dashboard wechat ecommerce增长百分比是：" + growth+"但是页面上显示的是："+percent);
			Assert.fail("es中计算出Dashboard wechat ecommerce增长百分比是：" + growth+"但是页面上显示的是："+percent);
			
		 }
		
		}
		 
	}
}
