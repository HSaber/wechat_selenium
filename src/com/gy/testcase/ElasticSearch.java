package com.gy.testcase;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ElasticSearch extends TestBase{
	
	 @BeforeMethod
		public  void setUp() throws Exception {
			 
			
		  }
	
	  @AfterMethod
		public  void tearDown() throws Exception {
			
		  
	 }
	  
	 @Test
	 public void elasticSearch() throws Exception {
	 
	  
		 SearchRequestBuilder responsebuilder = esConnect("wechat_customer");
		 SearchResponse myresponse=responsebuilder.setQuery(QueryBuilders.matchPhraseQuery("openid", "oOuXGt_UcICp1uIt4ZHSS4fIkdpA"))
				 					.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		 
		 SearchHits hits = myresponse.getHits();
		 for (int i = 0; i < hits.getHits().length; i++) {
		            System.out.println("输出"+hits.getHits()[i].getSourceAsString());
		 }
	  
	 }
	 
}
