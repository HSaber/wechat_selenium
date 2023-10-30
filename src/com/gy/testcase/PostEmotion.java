package com.gy.testcase;

import org.testng.annotations.Test;
import org.junit.*;
import org.openqa.selenium.*;

public class PostEmotion extends TestBase{
 
	@Test
	public void postEmotion() throws Exception {
	
	  //进入create页面
		
		oldEditorPage();
		driver.findElement(By.xpath("//div[@id='edui142_button_body']/div")).click();
	    Thread.sleep(3000);
	    driver.switchTo().frame("edui142_iframe");
	    driver.findElement(By.cssSelector("img[title=\"鲁拉\"]")).click();
	    driver.switchTo().defaultContent();
	    Thread.sleep(3000);
  }
}
