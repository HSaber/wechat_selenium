package com.hu.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class StyleMethods {
	public static void AddTag(WebDriver driver,String tag) throws InterruptedException,Exception{

		if(driver.findElement(By.id("tag_handler")).getText().isEmpty())
		{
		driver.findElement(By.id("tags")).click();
		driver.findElement(By.cssSelector("li.tagInput")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(tag);
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		}
		else
		{
			//获取ul下li的个数
			Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"tag_handler\").getElementsByTagName(\"li\").length;");
			for(int i=1;i<str;i++){
				driver.findElement(By.xpath(".//*[@id='tag_handler']/li[1]")).click();
				Thread.sleep(1000);
			}
			driver.findElement(By.cssSelector("li.tagInput")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
			driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(tag);
			driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
		}
	}
}
