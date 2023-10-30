package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class CreateSinglePost extends TestBase{
	private static Logger logger = Logger.getLogger(CreateSinglePost.class);
	@Test(groups="createSinglePost",dependsOnGroups="waterMark")
	public void createSinglePost() throws Exception {
	//进入create页面
	
	oldEditorPage();
	//String timetitle = timeDate();
   //文本框输入标题等
    Actions action=new Actions(driver);
    Thread.sleep(2000);
    WebElement edit1=driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[1]"));
    action.moveToElement(driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]"))).perform();
    edit1.click();
    driver.findElement(By.name("msgName")).sendKeys("single'post单图文“测试” selenium");
    driver.findElement(By.name("msgTitle")).sendKeys("单图文'title“测试” selenium");
    //取消smart
    driver.findElement(By.name("msgIsTeaserLengthLimit")).click();
    //添加tag
    driver.findElement(By.cssSelector("input.tags_add")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).click();
    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("seleniumtag");
    //添加summary
    driver.findElement(By.name("msgDigest")).clear();
    driver.findElement(By.name("msgDigest")).sendKeys("selenium单图文测试\nselenium单图文测试");
    //第一个编辑器上传图片
    driver.findElement(By.xpath("//div[@id='edui141_body']/div[1]")).click();
    driver.switchTo().frame("edui137_iframe");
    driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/1.jpg");
    //Thread.sleep(5000);
    boolean status = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
    while(status==false){
    	status = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
    	System.out.println("第一个编辑器图片上传未完成，请等待！！");
    }
    driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
    //Thread.sleep(6000);
    boolean status1 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
    while(status1==false){
    	status1 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
    	System.out.println("第一个编辑器图片upload未完成，请等待！！");
    }
    //Upimage.upload(driver);
    driver.switchTo().defaultContent();
    driver.findElement(By.xpath("//div[@id='edui139_body']")).click();
    //处理link弹框
    driver.findElement(By.xpath("//div[@id='edui136_body']/div[1]")).click();
    driver.switchTo().frame("edui132_iframe");
    driver.findElement(By.id("href")).sendKeys("www.baidu.com");
    driver.findElement(By.id("text")).sendKeys("百度");
    driver.switchTo().defaultContent();
    driver.findElement(By.xpath("//div[@id='edui134_body']")).click();
    //上传视屏
    driver.findElement(By.xpath("//div[@id='edui191_body']/div")).click();
    driver.findElement(By.id("J_vedioUrl")).clear();
    driver.findElement(By.id("J_vedioUrl")).sendKeys("http://v.qq.com/cover/z/z2ke4zzcqkpki7s.html?vid=c0018btb3ga");
    Thread.sleep(2000);
    driver.findElement(By.linkText("Save")).click();
    
    driver.findElement(By.name("linkUrl")).clear();
    driver.findElement(By.name("linkUrl")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA4MzI2NTMzOA==&mid=2652212192&idx=1&sn=c484b75bebcee9c9f6f51cc2042ce18e&chksm=841839b5b36fb0a3b6225eadd4285c51af4beca105275a53b2451e8d5b894563129d0582491e&scene=21#wechat_redirect");
    //上传封面图片
    driver.findElement(By.id("addPic_avatar")).sendKeys(file+"/com/material/2.jpg");
    //Thread.sleep(1000);
    boolean status2 = isElementPresent(By.xpath("//div[5]/div/div[3]/a[1]"));
    while(status2==false){
    	status2 = isElementPresent(By.xpath("//div[5]/div/div[3]/a[1]"));
    	System.out.println("封面图片上传未完成，请等待！！");
    }
    driver.findElement(By.xpath("//div[5]/div/div[3]/a[1]")).click();
    // Upcover.upload(driver);
    Thread.sleep(3000);
    
   driver.findElement(By.id("J_addNews")).click();
    //Thread.sleep(10000);
   boolean status3 = isElementPresent(By.xpath("//a[@id='create-more-posts']/button"));
   while(status3==false){
   	status3 = isElementPresent(By.xpath("//a[@id='create-more-posts']/button"));
   	System.out.println("single post创建保存未完成，请等待！！");
   }
   
    try {
      AssertJUnit.assertEquals("Manage Posts", driver.findElement(By.cssSelector("h1.page_title")).getText());
    } catch (Error e) {
     
    	logger.error("normal单图文创建失败");
      Assert.fail("normal单图文创建失败");
    }
   }
 }