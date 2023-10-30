package com.hu.testcase;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.queryparser.xml.FilterBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.index.query.*;  
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;


public class DemoForTesting {

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		//methods.open();
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
		methods.client.close();
	}

	@Test
	public void test() throws Exception {
		
		Date TDate=new Date();
	    SimpleDateFormat matter=new SimpleDateFormat("yyyyMMdd");
		String TodayDate=matter.format(TDate);
		System.out.println(TodayDate);
		SearchRequestBuilder responsebuilder = methods.client.prepareSearch("dev_jingsocial_33").setTypes("wechat_customer");
		SearchResponse response=responsebuilder.setQuery(QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("mid", methods.mid))
				.must(QueryBuilders.matchQuery("subscribe", "1"))
				.filter(QueryBuilders.hasChildQuery("user_action_record", QueryBuilders.boolQuery()
						.must(QueryBuilders.matchQuery("wid", methods.mid))
						.must(QueryBuilders.matchQuery("created_date", TodayDate))
						.must(QueryBuilders.matchQuery("type", "menu")))))
				.execute().actionGet();
		
		//		.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		
		SearchHits hits = response.getHits();
		System.out.println("查询的记录数="+hits.getTotalHits());
		for (int i = 0; i < hits.getHits().length; i++) {
		           System.out.println(hits.getHits()[i].getSourceAsString());}
		
	/*	response=responsebuilder.setQuery(QueryBuilders.filteredQuery("", FilterBuilder)boolQuery()
				.must(QueryBuilders.matchQuery("mid", methods.mid))
				.must(QueryBuilders.matchQuery("subscribe", "1"))
				.should(QueryBuilders.boolQuery()
						.must(QueryBuilders.matchQuery("latest_location.province", "北京"))))
				.execute().actionGet();
		
		hits = response.getHits();*/
		System.out.println("查询的记录数="+hits.getTotalHits());
		for (int i = 0; i < hits.getHits().length; i++) {
		           System.out.println(hits.getHits()[i].getSourceAsString());}
		
	}

}
