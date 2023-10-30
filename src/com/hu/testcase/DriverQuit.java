package com.hu.testcase;

import org.openqa.selenium.WebDriver;

import com.mysql.jdbc.Driver;

public class DriverQuit {
	static WebDriver driver;
	public static void main(String[] args) {
		driver.quit();
	}

}
