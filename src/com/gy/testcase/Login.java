package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.junit.*;
import org.openqa.selenium.*;

public class Login extends TestBase{
  @Test
  public void loginVerify() throws Exception {
	
	Thread.sleep(3000);
	try {
	      AssertJUnit.assertEquals("Dashboard", driver.findElement(By.cssSelector("h1.page_title")).getText());
	      System.out.println("登录成功");
	    } catch (Error e) {
	      
	      System.out.println("登录失败");
	      Assert.fail("登录失败");
	    }
	 driver.findElement(By.xpath("//ul[7]/li[2]/a")).click();
	    driver.findElement(By.xpath("(//a[contains(text(),'Logout')])[2]")).click();
	    Thread.sleep(3000);
	    try {
	      AssertJUnit.assertEquals("WeChat Web Application - Login Default", driver.getTitle());
	      System.out.println("退出成功");
	    } catch (Error e) {
	      
	      System.out.println("退出失败");
	      Assert.fail("退出失败");
	    }
  }
}