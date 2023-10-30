package com.gy.testcase;

import org.testng.annotations.Test;
import org.junit.*;
import org.openqa.selenium.*;

public class PostVideo extends TestBase{
 
	@Test
	public void postVideo() throws Exception {
	
	oldEditorPage();
    
    driver.findElement(By.xpath("//div[@id='edui191_body']/div")).click();
    WebElement input =driver.findElement(By.id("J_vedioUrl"));
    input.clear();
    input.sendKeys("http://v.qq.com/cover/z/z2ke4zzcqkpki7s.html?vid=c0018btb3ga");
    Thread.sleep(3000);
    driver.findElement(By.linkText("Save")).click();
    Thread.sleep(3000);
  }
}
