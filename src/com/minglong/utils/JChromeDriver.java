package com.minglong.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JChromeDriver extends ChromeDriver{
	public WebElement findElement(By by)
	{
		WebElement element = super.findElement(by);
		if(element != null){
			System.out.println("获取成功:::");
			System.out.println(element.toString());
		}else{
			System.out.println("无法获取到Element:::");
			System.out.println(by.toString());
		}
		return element;
	}
}
