package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;

public class CreateMultiPost extends TestBase{
	private static Logger logger = Logger.getLogger(CreateMultiPost.class);
  @Test(groups="createMultiPost",dependsOnGroups="waterMark")
  public void createMultiPost() throws Exception {
	//进入create页面

	oldEditorPage();
    //加3个item
    driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
    driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
    driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
    //============item1编辑========================================================================================
    //文本框输入标题等
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("jQuery('div.appmsg-mark').show();");
    //Thread.sleep(4000);
    boolean status = isElementPresent(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[1]"));
    while(status==false){
    	status = isElementPresent(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[1]"));
    	System.out.println("多图文创建浮动框未显示完成，请等待！！");
    }
    WebElement edit1=driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[1]"));
    edit1.click();
    Thread.sleep(3000);
    driver.findElement(By.name("msgName")).sendKeys("multi'post多图文“测试” selenium");
    driver.findElement(By.name("msgTitle")).sendKeys("标题title'1“测试” selenium");
    //--------------处理第一个编辑器----------------------------------   
    //下面的代码将处理iframe中富文本框编辑的问题
    driver.switchTo().frame("ueditor_0");
    WebElement editor11 = driver.findElement(By.tagName("body"));//editor11代表第一个item的第一个富文本框
    js.executeScript("arguments[0].innerHTML = ' <br> <h1>Selenium Test </h1>I love Selenium <br> this article Post By Selenium WebDriver<br><h2>Create By Guoyan</h2> <br>'", editor11);
    driver.switchTo().defaultContent();
    //第一个编辑器上传图片
    driver.findElement(By.xpath("//div[@id='edui141_body']/div[1]")).click();
    driver.switchTo().frame("edui137_iframe");
    driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/10.jpg");
    //Thread.sleep(2000);
    boolean status1 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
    while(status1==false){
    	status1 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
    	System.out.println("第一个编辑器图片上传未完成，请等待！！");
    }
    driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
    //Thread.sleep(8000);
    boolean status2 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
    while(status2==false){
    	status2 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
    	System.out.println("第一个编辑器图片upload未完成，请等待！！");
    }
    driver.switchTo().defaultContent();
    //Thread.sleep(3000);
    driver.findElement(By.xpath("//div[@id='edui139_body']")).click();
    //Thread.sleep(3000);  
    //处理link弹框
    driver.findElement(By.xpath("//div[@id='edui136_body']/div[1]")).click();
    driver.switchTo().frame("edui132_iframe");
    driver.findElement(By.id("href")).sendKeys("http://staging.jingsocial.com/redirect/index/?l=iALcCs1y8A53AaLhobU8yQWJCGVrCennO/ZsWW8Ef7Q=");
    driver.findElement(By.id("text")).sendKeys("微信带参链接正文页");
    driver.switchTo().defaultContent();
    driver.findElement(By.xpath("//div[@id='edui134_body']")).click();
    //处理post style template
    driver.findElement(By.xpath("//div[@id='edui187_body']/div[1]")).click();
    Thread.sleep(1000);
    
    driver.findElement(By.linkText("其他")).click();
    Thread.sleep(1000);
   
    driver.findElement(By.xpath("//div[@id='tab-4']/div/div[26]")).click();
    driver.findElement(By.xpath("//div[@id='tab-4']/div/div[39]")).click();
    driver.findElement(By.cssSelector("button.J_conBtn.btn")).click();
    //Thread.sleep(2000);
    boolean status5 = isElementPresent(By.xpath("//div[@id='edui191_body']/div"));
    while(status5==false){
    	status5 = isElementPresent(By.xpath("//div[@id='edui191_body']/div"));
    	System.out.println("第一个编辑器template框未关闭，请等待！！");
    }
    //上传视屏
    driver.findElement(By.xpath("//div[@id='edui191_body']/div")).click();
    driver.findElement(By.id("J_vedioUrl")).clear();
    driver.findElement(By.id("J_vedioUrl")).sendKeys("http://v.qq.com/cover/z/z2ke4zzcqkpki7s.html?vid=c0018btb3ga");
    Thread.sleep(2000);
    driver.findElement(By.linkText("Save")).click();
    
    //----------------------------------------处理第二个编辑器----------------------------------------------  
    //添加poll
    
    driver.findElement(By.xpath("//div[@id='edui95_body']/div")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[7]/div[2]/table/tbody/tr[1]/td[1]/input")).click();
    driver.findElement(By.id("add_poll")).click();
    Thread.sleep(2000);
   /* boolean status6 = isElementPresent(By.xpath("//div[@id='edui92_body']/div"));
    while(status6==false){
    	status6 = isElementPresent(By.xpath("//div[@id='edui92_body']/div"));
    	System.out.println("poll弹框未关闭，请等待！！");
    }*/
   
    //用模板
    driver.findElement(By.xpath("//div[@id='edui92_body']/div")).click();
    Thread.sleep(1000);
    
    driver.findElement(By.linkText("模板")).click();
    Thread.sleep(1000);
   
    driver.findElement(By.cssSelector("dt")).click();
    driver.findElement(By.cssSelector("button.J_conBtn.btn")).click();
    //Thread.sleep(2000);
    boolean status9 = isElementPresent(By.id("addPic_avatar"));
    while(status9==false){
    	status9 = isElementPresent(By.id("addPic_avatar"));
    	System.out.println("模板未上传完成，请等待！！");
    }
    
    //上传封面图片
    driver.findElement(By.id("addPic_avatar")).sendKeys(file+"/com/material/17.gif");
    //Thread.sleep(4000);
    boolean status10 = isElementPresent(By.cssSelector(".jcrop-tracker"));
    while(status10==false){
    	status10 = isElementPresent(By.cssSelector(".jcrop-tracker"));
    	System.out.println("封面图片未上传完成，请等待！！");
    }
    driver.findElement(By.linkText("Cancel")).click();
    Thread.sleep(4000);
    //================================item1编辑结束==================================================
    
    //============item2编辑========================================================================================
    WebElement itemedit2 =driver.findElement(By.xpath("//div[@id='J_sortable']/div[2]/div[2]/div/a[1]"));
    itemedit2.click();
    Thread.sleep(3000);
    driver.findElement(By.name("msgTitle")).sendKeys("标题title'2“测试” ");
    //取消smart post
    driver.findElement(By.name("msgIsTeaserLengthLimit")).click();
    //表情
   driver.findElement(By.xpath("//div[@id='edui142_button_body']/div")).click();
    Thread.sleep(3000);
    driver.switchTo().frame("edui142_iframe");
    //Thread.sleep(3000);
    boolean status11 = isElementPresent(By.cssSelector("img[title=\"Love\"]"));
    while(status11==false){
    	status11 = isElementPresent(By.cssSelector("img[title=\"Love\"]"));
    	System.out.println("表情未找到，请等待！！");
    }
    driver.findElement(By.cssSelector("img[title=\"Love\"]")).click();
    driver.switchTo().defaultContent();
    //Thread.sleep(3000);
    boolean status12 = isElementPresent(By.xpath("//div[@id='edui141_body']/div[1]"));
    while(status12==false){
    	status12 = isElementPresent(By.xpath("//div[@id='edui141_body']/div[1]"));
    	System.out.println("表情未上传完成，请等待！！");
    }
    //上传图片
    driver.findElement(By.xpath("//div[@id='edui141_body']/div[1]")).click();
    driver.switchTo().frame("edui137_iframe");
    driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/17.gif");
    //Thread.sleep(2000);
    boolean status13 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
    while(status13==false){
    	status13 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
    	System.out.println("编辑器图片上传未完成，请等待！！");
    }
    driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
    //Thread.sleep(5000);
    boolean status14 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
    while(status14==false){
    	status14 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
    	System.out.println("编辑器图片upload未完成，请等待！！");
    }
    driver.switchTo().defaultContent();
    driver.findElement(By.xpath("//div[@id='edui139_body']")).click();
    //Thread.sleep(3000);
    boolean status15 = isElementPresent(By.name("linkUrl"));
    while(status15==false){
    	status15 = isElementPresent(By.name("linkUrl"));
    	System.out.println("上传图片弹框未关闭，请等待！！");
    }
    
    driver.findElement(By.name("linkUrl")).clear();
    driver.findElement(By.name("linkUrl")).sendKeys("http://staging.jingsocial.com/redirect/index/?l=iALcCs1y8A53AaLhobU8yQWJCGVrCennO/ZsWW8Ef7Q=");
    
    driver.findElement(By.id("addPic_avatar")).sendKeys(file+"/com/material/7.jpg");
    //Thread.sleep(3000);
    boolean status16 = isElementPresent(By.cssSelector(".jcrop-tracker"));
    while(status16==false){
    	status16 = isElementPresent(By.cssSelector(".jcrop-tracker"));
    	System.out.println("上传封面图片未完成，请等待！！");
    }
    
    driver.findElement(By.linkText("Cancel")).click();
    Thread.sleep(4000);
    //================================item2编辑结束==================================================
    //==================================item3开始=============================================
    WebElement itemedit3 =driver.findElement(By.xpath("//div[@id='J_sortable']/div[3]/div[2]/div/a[1]"));
    itemedit3.click();
    Thread.sleep(3000);
    driver.findElement(By.name("msgTitle")).sendKeys("标题title'3“测试” ");
    //add a tag
    driver.findElement(By.xpath("(//input[@type='checkbox'])[4]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("selenium");
  
    //第一个编辑器
     driver.findElement(By.xpath("//div[@id='edui187_body']/div")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("模板")).click();
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("dt")).click();
    driver.findElement(By.cssSelector("button.J_conBtn.btn")).click();
    Thread.sleep(2000);
   
    
    //第二个编辑器
    driver.findElement(By.xpath("//div[@id='edui47_button_body']/div")).click();
    Thread.sleep(2000);
    driver.switchTo().frame("edui47_iframe");
   
    driver.findElement(By.cssSelector("img[title=\"Love\"]")).click();
    driver.switchTo().defaultContent();
    //Thread.sleep(3000);
    boolean status18 = isElementPresent(By.xpath("//div[@id='edui46_body']/div[1]"));
    while(status18==false){
    	status18 = isElementPresent(By.xpath("//div[@id='edui46_body']/div[1]"));
    	System.out.println("表情未上传完成，请等待！！");
    }
    
    driver.findElement(By.xpath("//div[@id='edui46_body']/div[1]")).click();
    Thread.sleep(1000);
    driver.switchTo().frame("edui42_iframe");
    driver.findElement(By.xpath("//div[@id='filePickerReady']/div[2]/input")).sendKeys(file+"/com/material/3.png");
    //Thread.sleep(2000);
    boolean status19 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
    while(status19==false){
    	status19 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/p[2]/img"));
    	System.out.println("编辑器图片上传未完成，请等待！！");
    }
    driver.findElement(By.xpath("//div[@id='queueList']/div[1]/div[3]/div[2]")).click();
    //Thread.sleep(10000);
    boolean status20 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
    while(status20==false){
    	status20 = isElementPresent(By.xpath("//li[@id='WU_FILE_0']/span"));
    	System.out.println("编辑器图片upload未完成，请等待！！");
    }
    driver.switchTo().defaultContent();
    driver.findElement(By.xpath("//div[@id='edui44_body']")).click();
    //Thread.sleep(3000);
    boolean status21 = isElementPresent(By.xpath("//div[@id='edui96_body']/div"));
    while(status21==false){
    	status21 = isElementPresent(By.xpath("//div[@id='edui96_body']/div"));
    	System.out.println("上传图片弹框未关闭，请等待！！");
    }
    
    driver.findElement(By.xpath("//div[@id='edui96_body']/div")).click();
    driver.findElement(By.id("J_vedioUrl")).clear();
    driver.findElement(By.id("J_vedioUrl")).sendKeys("http://v.qq.com/cover/z/z2ke4zzcqkpki7s.html?vid=c0018btb3ga");
    Thread.sleep(2000);
    driver.findElement(By.linkText("Save")).click();
    
    driver.findElement(By.xpath("//div[@id='edui41_body']/div[1]")).click();
    Thread.sleep(1000);
    driver.switchTo().frame("edui35_iframe");
    driver.findElement(By.id("href")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA4MzI2NTMzOA==&mid=2652212192&idx=1&sn=c484b75bebcee9c9f6f51cc2042ce18e&chksm=841839b5b36fb0a3b6225eadd4285c51af4beca105275a53b2451e8d5b894563129d0582491e&scene=21#wechat_redirect");
    driver.findElement(By.id("text")).sendKeys("微信带参链接原文页");
    driver.switchTo().defaultContent();
    driver.findElement(By.xpath("//div[@id='edui39_body']")).click();
    driver.findElement(By.id("addPic_avatar")).sendKeys(file+"/com/material/18.gif");
    //Thread.sleep(3000);
    boolean status22 = isElementPresent(By.cssSelector(".jcrop-tracker"));
    while(status22==false){
    	status22 = isElementPresent(By.cssSelector(".jcrop-tracker"));
    	System.out.println("上传封面图片未完成，请等待！！");
    }
    Thread.sleep(1000);
    driver.findElement(By.linkText("Cancel")).click();
    Thread.sleep(3000);
    //=====================================item3结束============================================
   
  //============item4编辑========================================================================================
    WebElement itemedit4 =driver.findElement(By.xpath("//div[@id='J_sortable']/div[4]/div[2]/div/a[1]"));
    itemedit4.click();
    Thread.sleep(3000);
    driver.findElement(By.name("msgTitle")).sendKeys("标题title'4“测试” ");
    //取消smart post
    driver.findElement(By.name("msgIsTeaserLengthLimit")).click();
    //处理link弹框
    driver.findElement(By.xpath("//div[@id='edui136_body']/div[1]")).click();
    driver.switchTo().frame("edui132_iframe");
    driver.findElement(By.id("href")).sendKeys("www.baidu.com");
    driver.findElement(By.id("text")).sendKeys("百度");
    driver.switchTo().defaultContent();
    driver.findElement(By.xpath("//div[@id='edui134_body']")).click();
    //处理post style template
    driver.findElement(By.xpath("//div[@id='edui187_body']/div[1]")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("其他")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='tab-4']/div/div[26]")).click();
    driver.findElement(By.xpath("//div[@id='tab-4']/div/div[39]")).click();
    driver.findElement(By.cssSelector("button.J_conBtn.btn")).click();
    Thread.sleep(2000);
    //上传视屏
    driver.findElement(By.xpath("//div[@id='edui191_body']/div")).click();
    driver.findElement(By.id("J_vedioUrl")).clear();
    driver.findElement(By.id("J_vedioUrl")).sendKeys("http://v.qq.com/cover/z/z2ke4zzcqkpki7s.html?vid=c0018btb3ga");
    Thread.sleep(2000);
    driver.findElement(By.linkText("Save")).click();
    driver.findElement(By.id("addPic_avatar")).sendKeys(file+"/com/material/9.jpg");
    //Thread.sleep(3000);
    boolean status23 = isElementPresent(By.cssSelector(".jcrop-tracker"));
    while(status23==false){
    	status23 = isElementPresent(By.cssSelector(".jcrop-tracker"));
    	System.out.println("上传封面图片未完成，请等待！！");
    }
    driver.findElement(By.linkText("Cancel")).click();
    Thread.sleep(3000);
    //================================item4编辑结束==================================================
    //创建结束，提交并且校验
     driver.findElement(By.id("J_prevNews")).click();
   // Thread.sleep(10000);
     boolean status24 = isElementPresent(By.xpath("//div[@id='page']/div[2]/div[2]/div[1]/button[3]"));
     while(status24==false){
     	status24 = isElementPresent(By.xpath("//div[@id='page']/div[2]/div[2]/div[1]/button[3]"));
     	System.out.println("multi post创建保存未完成，请等待！！");
     }
    try {
        AssertJUnit.assertEquals("WeChat Web Application - Preview Res", driver.getTitle());
      } catch (Error e) {
        
    	  logger.error("normal多图文创建失败");
        Assert.fail("normal多图文创建失败");
      }
  }

 
}
