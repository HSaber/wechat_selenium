package com.hu.testcase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Testcase {

	public static void main(String[] args) {
		/*  DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			WebDriver driver = new FirefoxDriver(capabilities);*/
			WebDriver driver = new FirefoxDriver();
	      driver.get("http://www.baidu.com");
	}

}
