package com.gy.testcase;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestngRetry implements IRetryAnalyzer{
	private static Logger logger = Logger.getLogger(TestngRetry.class);
	private int retryCount = 1;
	private int maxRetryCount=1;

	@Override
	public boolean retry(ITestResult result) {
	if (retryCount <= maxRetryCount) {
		
	String message = "本次用例执行失败，用例 '" + result.getName() + "' 将在 " +this.getClass().getName() + "执行第  " + retryCount + " 次重跑！！";
	logger.info(message);
	Reporter.setCurrentTestResult(result);
	Reporter.log("RunCount=" + (retryCount + 1));
	result.getTestContext().getFailedTests().removeResult(result.getMethod());
	/*result.setStatus(ITestResult.SKIP);
	result.getTestContext().getSkippedTests().removeResult(result.getMethod());*/
	retryCount++;
	return true;
	}
	return false;
	}

	}

	

	


