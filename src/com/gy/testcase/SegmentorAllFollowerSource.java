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

public class SegmentorAllFollowerSource extends TestBase{
	private static Logger logger = Logger.getLogger(SegmentorAllFollowerSource.class);
	
	 @Test
	 public void segmentorAllFollowerSource() throws Exception {
		 navigation("Segmentor");
		 
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/label")).click();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div/div[1]")).click();
		 boolean status=false;
		 status=isElementPresent(By.xpath(".//*[@id='charts']/div/div[1]/div/div[1]/div[2]/div/div[2]/table/tbody/tr[2]/td[1]/span[2]"));
		 while(!status){
			 status=isElementPresent(By.xpath(".//*[@id='charts']/div/div[1]/div/div[1]/div[2]/div/div[2]/table/tbody/tr[2]/td[1]/span[2]"));
		 }
		 int i=0;
		 
	 }
}
