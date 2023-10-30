package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;

public class ImportPost extends TestBase{
	private static Logger logger = Logger.getLogger(ImportPost.class);
	 @Test
	  public void importPost() throws Exception {
	   /* driver.get("https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN");
	    driver.findElement(By.id("account")).clear();
	    driver.findElement(By.id("account")).sendKeys("weixin5@achang.com");
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("Admin123");
	    driver.findElement(By.id("loginBt")).click();
	    
	    driver.findElement(By.linkText("素材管理")).click();
	    driver.findElement(By.linkText("新建图文消息")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("title")).clear();
	    driver.findElement(By.id("title")).sendKeys("测试import post");
	    
	   driver.switchTo().frame("ueditor_0");
	    WebElement editor1 = driver.findElement(By.tagName("body"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].innerHTML = ' import post🍱🍛🍙'", editor1);
	    driver.switchTo().defaultContent();
	    driver.findElement(By.id("js_editor_insertimage")).click();
	    driver.findElement(By.xpath("//li[6]/label/img")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
	    
	    driver.findElement(By.id("js_editor_insertvideo")).click();
	    driver.findElement(By.linkText("视频网址")).click();
	    driver.findElement(By.cssSelector("input.frm_input.js_video_txurl")).clear();
	    driver.findElement(By.cssSelector("input.frm_input.js_video_txurl")).sendKeys("http://v.qq.com/cover/z/z2ke4zzcqkpki7s.html?vid=c0018btb3ga");
	    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
	    
	    driver.findElement(By.xpath("//div[@id='edui22_body']/div")).click();
	    driver.findElement(By.id("txtHref")).clear();
	    driver.findElement(By.id("txtHref")).sendKeys("http://app.jingsocial.com/artview/index/v/1/rid/6169/wid/42");
	    driver.findElement(By.id("txtTitle")).clear();
	    driver.findElement(By.id("txtTitle")).sendKeys("post  link");
	    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
	    
	    driver.findElement(By.id("checkbox2")).click();
	    driver.findElement(By.name("source_url")).clear();
	    driver.findElement(By.name("source_url")).sendKeys("http://app.jingsocial.com/artview/index/v/3/rid/6169/wid/42");
	    
	    driver.findElement(By.id("js_imagedialog")).click();
	    driver.findElement(By.cssSelector("img.pic")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
	    
	    driver.findElement(By.cssSelector("i.icon35_common.add_gray")).click();
	    driver.findElement(By.id("title")).clear();
	    driver.findElement(By.id("title")).sendKeys("import post 2--🍕🌮🌯");
	    
	    driver.findElement(By.name("digest")).clear();
	    driver.findElement(By.name("digest")).sendKeys("import 摘要\n🍱🍛🍙");
	    
	    driver.findElement(By.id("js_imagedialog")).click();
	    driver.findElement(By.xpath("//li[2]/label/img")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
	    
	    driver.findElement(By.id("js_editor_insertimage")).click();
	    driver.findElement(By.xpath("//li[2]/label/img")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
	    
	    driver.findElement(By.cssSelector("#js_submit > button[type=\"button\"]")).click();*/
	    
		 navigation("Manage Posts");
	    driver.findElement(By.cssSelector("#import_post_btn > button")).click();
	    try {
	        System.out.println(driver.findElement(By.cssSelector(".bigT")).getText());
	    	AssertJUnit.assertEquals("Do you want to import the 5 latest posts from native WeChat platform?",driver.findElement(By.cssSelector(".bigT")).getText());
	    } catch (Error e) {
	     
	    	logger.error("import post导入提示错误");
	      Assert.fail("import post导入提示错误");
	    }
	    driver.findElement(By.id("import_confirm_btn")).click();
	  }

}
