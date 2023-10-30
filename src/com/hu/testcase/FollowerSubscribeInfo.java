package com.hu.testcase;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.interactive.testcase.HttpClient;

public class FollowerSubscribeInfo {
	private static Logger logger = Logger.getLogger(FollowerSubscribeInfo.class);
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws Exception {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		System.out.println(HttpClient.openidArr[p][q]+" "+methods.mid+" "+methods.baseUrl);
		boolean flag=methods.DeleteFollower(HttpClient.openidArr[p][q], methods.mid, methods.baseUrl);
		if(flag)
		{
			System.out.println(HttpClient.openidArr[p][q]+"删除成功");
			
		}else
		{
			System.out.println(HttpClient.openidArr[p][q]+"删除失败");
			logger.error(HttpClient.openidArr[p][q]+"删除失败");
		}
	}

}
