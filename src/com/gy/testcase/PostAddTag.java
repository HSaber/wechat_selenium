package com.gy.testcase;

import org.testng.annotations.Test;
import org.junit.*;
import org.openqa.selenium.*;

public class PostAddTag extends TestBase{
  @Test
  public void postAddTag() throws Exception {
	
	  //进入create页面
	
	oldEditorPage();
	String time = currentTime();
    driver.findElement(By.cssSelector("input.tags_add")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).click();
    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("test add a tag"+time+"");
   Thread.sleep(2000);
  }
}
