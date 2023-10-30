package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class NewEditorSinglePost extends TestBase{
	private static Logger logger = Logger.getLogger(NewEditorSinglePost.class);
	//@Test
	@Test(groups="newEditorSinglePost",dependsOnGroups="pollCreate")
	  public void newEditorSinglePost() throws Exception {
		 
		 navigation("Manage Posts");
		 
		 driver.findElement(By.id("create-more-posts")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).click();
		 driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).sendKeys("new editor单图文'selenium");
		 driver.findElement(By.linkText("Submit")).click();
		 
		 driver.findElement(By.name("msgTitle")).sendKeys("new'editor selenium单图文");
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/18.gif");
		 //Thread.sleep(3000);
		 boolean status = isElementPresent(By.linkText("Cancel"));
		 while(status==false){
		    	status = isElementPresent(By.linkText("Cancel"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 
		 
		 driver.findElement(By.linkText("Cancel")).click();
		 //Thread.sleep(1000);
		 String time = currentTime();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 WebElement target = driver.findElement(By.cssSelector(".tags_add"));
		 js.executeScript("arguments[0].scrollIntoView();", target);
		 driver.findElement(By.cssSelector(".tags_add")).click();
		 driver.findElement(By.cssSelector(".tagInputField.ui-autocomplete-input")).sendKeys("neweditor单图文"+ time);
		
		 driver.findElement(By.id("Summary")).sendKeys("new editor单图文summary：\n创建图文时添加\n注意检查换行===============");
		 
		 js.executeScript("jQuery('.postOperation').css({'opacity':'1','-webkit-transform':'translate3d(0,2px,0)'})");
		
		 Thread.sleep(2000);
		 //Actions action = new Actions(driver);
		 //action.moveToElement(driver.findElement(By.xpath("//div[@id='postSetting']/div[2]"))).perform();
		 
		 WebElement delete2 = driver.findElement(By.xpath("//div[@id='postSetting']/div[2]/nav/*[name()='svg'][3]"));
		 Actions action=new Actions(driver);
		 action.click(delete2).perform();
		 
		 //Thread.sleep(2000);
		 boolean status1 = isElementPresent(By.linkText("Cancel"));
		 while(status1==false){
		    	status1 = isElementPresent(By.linkText("Cancel"));
		    	System.out.println("delete item弹框未弹出，请等待！！");
		 }
		 driver.findElement(By.linkText("Delete")).click();
		 WebElement target0 = driver.findElement(By.id("J_next_teaser"));
		 js.executeScript("arguments[0].scrollIntoView();", target0);
		 driver.findElement(By.id("J_next_teaser")).click();
		 
		 boolean status2 = isElementPresent(By.id("js_appmsg_editor"));
		 while(status2==false){
		    	status2 = isElementPresent(By.id("js_appmsg_editor"));
		    	System.out.println("还未跳到teaser page编辑页面，请等待！！");
		 }
		 //上传teaser page图片
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[2]/a")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("js_editor_insertimage")).click();
		 
		 boolean status01 = isElementPresent(By.id("edui18_iframe"));
		 while(status01==false){
		    	status01 = isElementPresent(By.id("edui18_iframe"));
		    	System.out.println("iframe未加载出来，请等待！！");
		 }
		 driver.switchTo().frame("edui18_iframe");
		 driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/17.gif");
		 boolean status3 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		 while(status3==false){
		    	status3 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		    	System.out.println("第一个编辑器图片上传未完成，请等待！！");
		 }
		 driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
		    
		 boolean status4 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		 while(status4==false){
		    	status4 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		    	System.out.println("第一个编辑器图片upload未完成，请等待！！");
		 }
		    
		 driver.switchTo().defaultContent();
		 driver.findElement(By.xpath("//div[@id='edui20_body']")).click();
		 Thread.sleep(1000);
		 //teaser page上传视频
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[3]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("js_editor_insertvideo")).click();
		 driver.findElement(By.id("J_vedioUrl")).sendKeys("http://v.qq.com/x/cover/3b5paho2mh6wsls.html?vid=w0021h59ljc");
		 Thread.sleep(2000);
		 driver.findElement(By.linkText("Save")).click();
		 Thread.sleep(1000);
		 //teaser page浮动框post link
		 js.executeScript("jQuery('.editorBox.textEditBox.clear').css({'z-index': '5', 'visibility': 'visible'})");
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/span")).click();
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/input")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA4MzI2NTMzOA==&mid=2652212192&idx=1&sn=c484b75bebcee9c9f6f51cc2042ce18e&chksm=841839b5b36fb0a3b6225eadd4285c51af4beca105275a53b2451e8d5b894563129d0582491e&scene=21#wechat_redirect");
		 driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("NST-"+time+Keys.ENTER);
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/input")).click();
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/span")).click();
		 //teaser page上传text模板
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[1]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(".//*[@id='tabText']/section[28]")).click();
		 
		 driver.findElement(By.id("J_next")).click();
		 
		 boolean status5 = isElementPresent(By.id("J_done"));
		 while(status5==false){
		    	status5 = isElementPresent(By.id("J_done"));
		    	System.out.println("还未跳到detail  page编辑页面，请等待！！");
		 }
		 //detail page添加poll
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[3]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(".//*[@id='js_editor_insertpoll']")).click();
		 driver.findElement(By.xpath("//div[13]/div[2]/table/tbody/tr[1]/td[1]/input")).click();
		 driver.findElement(By.id("add_poll")).click();
		 Thread.sleep(2000);
		//detail page上传png图片
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[2]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("js_editor_insertimage")).click();
		 Thread.sleep(1000);
		 driver.switchTo().frame("edui18_iframe");
		 driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/3.png");
		 boolean status6 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		 while(status6==false){
		    	status6 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		    	System.out.println("第2个编辑器图片上传未完成，请等待！！");
		 }
		 driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
		    
		 boolean status7 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		 while(status7==false){
		    	status7 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		    	System.out.println("第2个编辑器图片upload未完成，请等待！！");
		 }
		    
		 driver.switchTo().defaultContent();
		 driver.findElement(By.xpath("//div[@id='edui20_body']")).click();
		 Thread.sleep(1000);
		 
		 driver.findElement(By.id("J_done")).click();
		 Thread.sleep(3000);
		 try{
			  AssertJUnit.assertEquals("Preview",driver.findElement(By.id("J_prevNews")).getText());
		 }catch(Error e){
			 logger.error("new editor single post创建失败!!!");
			 Assert.fail("new editor single post创建失败!!!");
		 }
	 }
}
