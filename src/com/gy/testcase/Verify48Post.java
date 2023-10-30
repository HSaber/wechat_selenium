package com.gy.testcase;

import static org.testng.AssertJUnit.assertEquals;

import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class Verify48Post extends TestBase{
	private static Logger logger = Logger.getLogger(Verify48Post.class);
	//@Test
	@Test(groups="verify48Post",dependsOnGroups="newEditorMultiReplyPost")
	public void verify48Post() throws Exception {
		
		 navigation("48 Posts List");
	      String num = driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[6]/a")).getText();
	      
	      try {
	      assertEquals(num, driver.findElement(By.xpath("//div[@id='post-queue-forty-hours-grid']/div/table/tbody/tr/td[7]/a")).getText());
	    } catch (Error e) {
	    	logger.error("48小时立即发送post发送后48 posts list页面 显示实际发送人数不正确");
	  	AssertJUnit.fail("48小时立即发送post发送后48 posts list页面 显示实际发送人数不正确");
	  	  //verificationErrors.append(e.toString());
	    }
	}
}
