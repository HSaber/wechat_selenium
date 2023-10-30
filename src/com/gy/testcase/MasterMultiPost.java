package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class MasterMultiPost extends TestBase{
	private static Logger logger = Logger.getLogger(MasterMultiPost.class);
	 @Test
	  public void masterMultiPost() throws Exception {
		 
		 driver.get(baseUrl + "Master/dashboard/index/category/dashboard");
		 driver.findElement(By.linkText("Content")).click();
		  
		  driver.findElement(By.linkText("Manage Posts")).click();
		 
		 driver.findElement(By.id("create-more-posts")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[1]/input")).click();
		 driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[1]/input")).sendKeys("master多图文'selenium");
		 driver.findElement(By.linkText("Submit")).click();
		 //items1
		 driver.findElement(By.name("msgTitle")).sendKeys("master'selenium多图文--1");
		 driver.findElement(By.cssSelector(".switch-handle")).click();
		 boolean status0 = isElementPresent(By.cssSelector(".ui-dialog-buttonset>button"));
		 while(status0==false){
		    	status0 = isElementPresent(By.cssSelector(".ui-dialog-buttonset>button"));
		    	System.out.println("smart post设置弹框未弹出，请等待！！");
		 }
		 driver.findElement(By.cssSelector(".ui-dialog-buttonset>button")).click();
		 
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/31.jpg");
		 //Thread.sleep(3000);
		 boolean status = isElementPresent(By.cssSelector(".jcrop-tracker"));
		 while(status==false){
		    	status = isElementPresent(By.cssSelector(".jcrop-tracker"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 
		 
		 driver.findElement(By.linkText("Cancel")).click();
		 
		 String src = null;
		 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
		 System.out.println("src的值是："+ src);
		 while(src==null||src.equals("")){
				 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
				 System.out.println("封面图片未上传完成,继续等待中！");
		 }
		 
		 driver.findElement(By.id("Summary")).sendKeys("master多图文summary--1：\n创建图文时添加\n注意检查换行===============");
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("jQuery('.postOperation').css({'opacity':'1','-webkit-transform':'translate3d(0,2px,0)'})");
		 Thread.sleep(2000);
		 
		 driver.findElement(By.cssSelector(".addMobileCon")).click();
		 driver.findElement(By.cssSelector(".addMobileCon")).click();
		 
		 driver.findElement(By.cssSelector("#J_next_teaser")).click();
		 Thread.sleep(1000);
		 
		 try{
			 	AssertJUnit.assertEquals("Item2 title cannot be empty",driver.findElement(By.cssSelector("div.toast-message")).getText());
		 }catch(Error e){
			 
			 logger.error("item信息未填满，没有给出提示！！");
			 Assert.fail("item信息未填满，没有给出提示！！");
		 }
		 Thread.sleep(1000);
		 try{
			 	AssertJUnit.assertEquals("Post Line 2",driver.findElement(By.cssSelector("h2.post_index")).getText());
		 }catch(Error e){
			
			 logger.error("没有跳转到items2，请检查！！");
			 Assert.fail("没有跳转到items2，请检查！！");
		 }
		 
		 //items2
		 driver.findElement(By.name("msgTitle")).sendKeys("master'selenium多图文--2");
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/17.gif");
		 //Thread.sleep(3000);
		 boolean status1 = isElementPresent(By.linkText("Cancel"));
		 while(status1==false){
		    	status1 = isElementPresent(By.linkText("Cancel"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 Thread.sleep(1000);
		 
		 driver.findElement(By.linkText("Cancel")).click();
		 
		 src = null;
		 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
		 System.out.println("src的值是："+ src);
		 while(src==null||src.equals("")){
				 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
				 System.out.println("封面图片未上传完成,继续等待中！");
		 }
		 
		 String time = currentTime();
		
		 
		 driver.findElement(By.id("Summary")).sendKeys("master多图文summary--2：\n创建图文时添加\n注意检查换行===============");
		 
		 //item3
		 driver.findElement(By.xpath(".//*[@id='postSetting']/section[1]")).click();
		 Thread.sleep(1000);
		 
		 driver.findElement(By.name("msgTitle")).sendKeys("master'selenium多图文--3");
		 
		 driver.findElement(By.cssSelector(".switch-handle")).click();
		 boolean status3 = isElementPresent(By.cssSelector(".ui-dialog-buttonset>button"));
		 while(status3==false){
		    	status3 = isElementPresent(By.cssSelector(".ui-dialog-buttonset>button"));
		    	System.out.println("smart post设置弹框未弹出，请等待！！");
		 }
		 driver.findElement(By.cssSelector(".ui-dialog-buttonset>button")).click();
		 
		 driver.findElement(By.id("originalPage")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA5NDExNDAxMg==&mid=402503006&idx=1&sn=96f7cb154d807671b3dfac20b145d406");
		 
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/26.jpg");
		 //Thread.sleep(3000);
		 boolean status2 = isElementPresent(By.cssSelector(".jcrop-tracker"));
		 while(status2==false){
		    	status2 = isElementPresent(By.cssSelector(".jcrop-tracker"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 
		 
		 driver.findElement(By.linkText("Save")).click();
		 Thread.sleep(1000);
		 src = null;
		 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
		 System.out.println("src的值是："+ src);
		 while(src==null||src.equals("")){
				 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
				 System.out.println("封面图片未上传完成,继续等待中！");
		 }
		 
		 
		 driver.findElement(By.id("Summary")).sendKeys("master多图文summary--3：\n创建图文时添加\n注意检查换行===============");
		 
		 //item4
		 
		 driver.findElement(By.xpath(".//*[@id='postSetting']/section[2]")).click();
		 Thread.sleep(1000);
		 
		 driver.findElement(By.name("msgTitle")).sendKeys("master selenium多图文--4");
		 
		
		 
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/18.gif");
		 //Thread.sleep(3000);
		 boolean status4 = isElementPresent(By.linkText("Save"));
		 while(status4==false){
		    	status4 = isElementPresent(By.linkText("Save"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 
		 Thread.sleep(1000);
		 driver.findElement(By.linkText("Save")).click();
		 
		 src = null;
		 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
		 System.out.println("src的值是："+ src);
		 while(src==null||src.equals("")){
				 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
				 System.out.println("封面图片未上传完成,继续等待中！");
		 }
		 
		 WebElement t = driver.findElement(By.id("J_next_teaser"));
		 js.executeScript("arguments[0].scrollIntoView();", t);
		 driver.findElement(By.id("J_next_teaser")).click();
		 //driver.findElement(By.xpath("//div/span[2]")).click();
		 
		 //==========================================item4 teaser page编辑=============================================================
		 boolean status5 = isElementPresent(By.id("js_appmsg_editor"));
		 while(status5==false){
		    	status5 = isElementPresent(By.id("js_appmsg_editor"));
		    	System.out.println("还未跳到teaser page编辑页面，请等待！！");
		 }
		 //上传图片
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[2]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("js_editor_insertimage")).click();
		 Thread.sleep(1000);
		 driver.switchTo().frame("edui18_iframe");
		 driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/17.gif");
		 boolean status6 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		 while(status6==false){
		    	status6 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		    	System.out.println("第一个编辑器图片上传未完成，请等待！！");
		 }
		 driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
		    
		 boolean status7 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		 while(status7==false){
		    	status7 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
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
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/input")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA5NDExNDAxMg==&mid=402503006&idx=1&sn=96f7cb154d807671b3dfac20b145d406");
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/div/button")).click();
		 //teaser page上传text模板
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[1]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(".//*[@id='tabText']/section[28]")).click();
		 
		 driver.findElement(By.id("J_next")).click();
		 //===============================item4 detail page编辑======================================================================
		 boolean status8 = isElementPresent(By.id("J_done"));
		 while(status8==false){
		    	status8 = isElementPresent(By.id("J_done"));
		    	System.out.println("还未跳到detail  page编辑页面，请等待！！");
		 }
		
		//detail page上传png图片
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[2]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("js_editor_insertimage")).click();
		 Thread.sleep(1000);
		 driver.switchTo().frame("edui18_iframe");
		 driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/3.png");
		 boolean status9 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		 while(status9==false){
		    	status9 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		    	System.out.println("第2个编辑器图片上传未完成，请等待！！");
		 }
		 driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
		    
		 boolean status10 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		 while(status10==false){
		    	status10 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		    	System.out.println("第2个编辑器图片upload未完成，请等待！！");
		 }
		    
		 driver.switchTo().defaultContent();
		 driver.findElement(By.xpath("//div[@id='edui20_body']")).click();
		 Thread.sleep(1000);
		 
		 //detail page插入表情
		 
		 js.executeScript("jQuery('.editorBox.textEditBox.clear').css({'z-index': '5', 'visibility': 'visible'})");
		 driver.findElement(By.cssSelector("#emotion>span")).click();
		 Thread.sleep(1000);
		 driver.switchTo().frame("edui3_iframe");
		 driver.findElement(By.xpath(".//*[@id='tabHeads']/span[8]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(".//*[@id='tab7']/table/tbody/tr[2]/td[6]/span")).click();
		 Thread.sleep(1000);
		 driver.switchTo().defaultContent();
		 //上传link
		 
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/span")).click();
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/input")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA5NDExNDAxMg==&mid=402503006&idx=1&sn=96f7cb154d807671b3dfac20b145d406");
		 
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/div/button")).click();
		 driver.findElement(By.id("J_done")).click();
		 
		 boolean status11 = isElementPresent(By.id("J_prevNews"));
		 while(status11==false){
		    	status11 = isElementPresent(By.id("J_prevNews"));
		    	System.out.println("还未跳回到items编辑页面，请等待！！");
		 }
		 
		 try{
			 	AssertJUnit.assertEquals("Post Line 1",driver.findElement(By.cssSelector("h2.post_index")).getText());
		 }catch(Error e){
			
			 logger.error("没有跳转到items1，请检查！！");
			 Assert.fail("没有跳转到items1，请检查！！");
		 }
		 
		 //====================================item4结束======================================================
		 driver.findElement(By.id("J_next_teaser")).click();
		 boolean status12 = isElementPresent(By.id("js_appmsg_editor"));
		 while(status12==false){
		    	status12 = isElementPresent(By.id("js_appmsg_editor"));
		    	System.out.println("还未跳到item1 teaser page编辑页面，请等待！！");
		 }
		 //=============================item1 teaser page编辑=========================================
		 //上传图片
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[2]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("js_editor_insertimage")).click();
		 Thread.sleep(1000);
		 driver.switchTo().frame("edui18_iframe");
		 driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/17.gif");
		 boolean status13 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		 while(status13==false){
		    	status13 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		    	System.out.println("第一个编辑器图片上传未完成，请等待！！");
		 }
		 driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
		    
		 boolean status14 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		 while(status14==false){
		    	status14 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		    	System.out.println("第一个编辑器图片upload未完成，请等待！！");
		 }
		    
		 driver.switchTo().defaultContent();
		 driver.findElement(By.xpath("//div[@id='edui20_body']")).click();
		 Thread.sleep(1000);
		 //上传表情
		 js.executeScript("jQuery('.editorBox.textEditBox.clear').css({'z-index': '5', 'visibility': 'visible'})");
		 driver.findElement(By.cssSelector("#emotion>span")).click();
		 Thread.sleep(1000);
		 driver.switchTo().frame("edui3_iframe");
		 driver.findElement(By.xpath(".//*[@id='tabHeads']/span[8]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(".//*[@id='tab7']/table/tbody/tr[2]/td[6]/span")).click();
		 Thread.sleep(1000);
		 driver.switchTo().defaultContent();
		 
		 driver.findElement(By.id("J_next")).click();
		 boolean status15 = isElementPresent(By.id("J_prevNews"));
		 while(status15==false){
		    	status15 = isElementPresent(By.id("J_prevNews"));
		    	System.out.println("还未跳回到items编辑页面，请等待！！");
		 }
		 //===============================item1编辑完成======================================================
		 
		 driver.findElement(By.xpath(".//*[@id='postSetting']/div[2]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("J_next_teaser")).click();
		 boolean status16 = isElementPresent(By.id("js_appmsg_editor"));
		 while(status16==false){
		    	status16 = isElementPresent(By.id("js_appmsg_editor"));
		    	System.out.println("还未跳到item2 teaser page编辑页面，请等待！！");
		 }
		 //======================================items2 teaser page编辑=================================================
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[2]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("js_editor_insertimage")).click();
		 Thread.sleep(1000);
		 driver.switchTo().frame("edui18_iframe");
		 driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/3.png");
		 boolean status17 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		 while(status17==false){
		    	status17 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		    	System.out.println("第2个编辑器图片上传未完成，请等待！！");
		 }
		 driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
		    
		 boolean status18 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		 while(status18==false){
		    	status18 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		    	System.out.println("第1个编辑器图片upload未完成，请等待！！");
		 }
		    
		 driver.switchTo().defaultContent();
		 driver.findElement(By.xpath("//div[@id='edui20_body']")).click();
		 Thread.sleep(1000);
		 
		
		 
		 driver.findElement(By.id("J_next")).click();
		 
		 boolean status19 = isElementPresent(By.id("J_done"));
		 while(status19==false){
		    	status19 = isElementPresent(By.id("J_done"));
		    	System.out.println("还未跳到detail  page编辑页面，请等待！！");
		 }
		 //======================items2 detail page编辑========================================================
		
		 //添加模板
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[1]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(".//*[@id='tabText']/section[34]")).click();
		 //添加视屏
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[3]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("js_editor_insertvideo")).click();
		 driver.findElement(By.id("J_vedioUrl")).sendKeys("http://v.qq.com/x/cover/3b5paho2mh6wsls.html?vid=w0021h59ljc");
		 Thread.sleep(2000);
		 driver.findElement(By.linkText("Save")).click();
		 Thread.sleep(1000);
		 
		/* driver.findElement(By.id("J_save")).click();
		 boolean status20 = isElementPresent(By.id("J_done"));
		 while(status20==false){
		    	status20 = isElementPresent(By.id("J_done"));
		    	System.out.println("还未save完成，请等待！！");
		 }*/
		 
		 driver.findElement(By.id("J_done")).click();
		 
		 boolean status21 = isElementPresent(By.id("J_prevNews"));
		 while(status21==false){
		    	status21 = isElementPresent(By.id("J_prevNews"));
		    	System.out.println("还未跳回到items编辑页面，请等待！！");
		 }
		 
		 //=====================items 2编辑完成=================================
		 
		 driver.findElement(By.xpath(".//*[@id='postSetting']/div[3]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("J_next_teaser")).click();
		 boolean status22 = isElementPresent(By.id("js_appmsg_editor"));
		 while(status22==false){
		    	status22 = isElementPresent(By.id("js_appmsg_editor"));
		    	System.out.println("还未跳到item2 teaser page编辑页面，请等待！！");
		 }
		 
		 //==============================item3 teaser page编辑==============================
		 //上传图片
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[2]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("js_editor_insertimage")).click();
		 Thread.sleep(1000);
		 driver.switchTo().frame("edui18_iframe");
		 driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/9.jpg");
		 boolean status23 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		 while(status23==false){
		    	status23 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
		    	System.out.println("第一个编辑器图片上传未完成，请等待！！");
		 }
		 driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
		    
		 boolean status24 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		 while(status24==false){
		    	status24 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
		    	System.out.println("第一个编辑器图片upload未完成，请等待！！");
		 }
		 driver.switchTo().defaultContent();
		 driver.findElement(By.xpath("//div[@id='edui20_body']")).click();
		 Thread.sleep(1000);
		 
		 //teaser page浮动框post link
		 js.executeScript("jQuery('.editorBox.textEditBox.clear').css({'z-index': '5', 'visibility': 'visible'})");
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/span")).click();
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/input")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA5NDExNDAxMg==&mid=402503006&idx=1&sn=96f7cb154d807671b3dfac20b145d406");
		
		 driver.findElement(By.xpath(".//*[@id='js_appmsg_editor']/div[2]/div[2]/menu[1]/section/ul[2]/li[6]/div/div/button")).click();
		 //teaser page上传text模板
		 driver.findElement(By.xpath(".//*[@id='tabNav']/ul/li[1]/a")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(".//*[@id='tabText']/section[28]")).click();
		 Thread.sleep(1000);
		 
		 driver.findElement(By.id("J_next")).click();
		 
		 boolean status25 = isElementPresent(By.id("J_prevNews"));
		 while(status25==false){
		    	status25 = isElementPresent(By.id("J_prevNews"));
		    	System.out.println("还未跳回到items编辑页面，请等待！！");
		 }
		 
		 driver.findElement(By.id("J_prevNews")).click();
		 
		 boolean status26 = isElementPresent(By.xpath(".//*[@id='page']/div[2]/div[2]/div[1]/button"));
		 while(status26==false){
		    	status26 = isElementPresent(By.xpath(".//*[@id='page']/div[2]/div[2]/div[1]/button"));
		    	System.out.println("还未跳回到preview post编辑页面，请等待！！");
		 }
		 
		 try{
			 System.out.println(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[1]/button")).getText()); 
			 AssertJUnit.assertEquals("Back",driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[1]/button")).getText());
		 }catch(Error e){
			 logger.error("master multi post创建失败!!!");
			 Assert.fail("master multi post创建失败!!!");
		 }
	 }
}
