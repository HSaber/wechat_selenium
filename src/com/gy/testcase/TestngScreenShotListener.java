package com.gy.testcase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class TestngScreenShotListener extends TestListenerAdapter{
	private static Logger logger = Logger.getLogger(TestngScreenShotListener.class);
	public static final String CONFIG = "config.properties";

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.error(tr.getName() + " Failure");
		takeScreenShot(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.warn(tr.getName() + " Skipped");
                takeScreenShot(tr);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(tr.getName() + " Success");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getName() + " Start");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);

	}
	
	private void takeScreenShot(ITestResult tr) {
		 WebDriver driver=null;
		try {
            TestBase tb = (TestBase) tr.getInstance();
            driver = tb.getDriver();         
            System.out.println(driver.getTitle());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {         
            e.printStackTrace();
        }
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String mDateTime = formatter.format(new Date());
		String fileName = mDateTime + "_" + tr.getName();
		String currentPath = this.getClass().getResource("/").getFile().toString();
		currentPath = currentPath + "com/screenshots";
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile,new File(currentPath+fileName));
			
		} catch (Exception e) {
			System.out.println("没截图成功！！！");
			e.printStackTrace();
		}
		
		Reporter.setCurrentTestResult(tr);
		Reporter.log(currentPath+fileName);

               //这里实现把图片链接直接输出到结果文件中，通过邮件发送结果则可以直接显示图片
		Reporter.log("<img src=\"../" + currentPath+fileName + "\"/>");
}
}
