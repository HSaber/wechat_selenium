package com.hu.testcase;

import java.util.List;

import jxl.write.Label;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardMenuCompare {
	private static Logger logger = Logger.getLogger(DashboardMenuCompare.class);
	public String[][] MenuCompare(WebDriver driver,String Start,String End) throws InterruptedException,Exception {
	
	//Dashboard Top Menu Link--------------------------------------------------------------------------------------------------------
	int EndNumMenu=0;
	String[][] TopMenu=new String[5][2];
	String[][] MenuDiff = null;
	int menuExcel=5;
	
	if(!methods.isElementPresent(driver, By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[1]")))
	{
		MenuDiff = new String[1][1];
		System.out.println("No Top Menu");
		MenuDiff[0][0]="NoTopMenu";
		return MenuDiff;
    	//**************************************************填充excel****************************************************************
	}
	else{
		List<WebElement> e_row=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody")).findElements(By.tagName("tr"));
		
	for(int i=0;i<e_row.size();i++)
	{
		TopMenu[i][0]=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody/tr["+(i+1)+"]/td[1]")).getText();
		TopMenu[i][1]=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody/tr["+(i+1)+"]/td[2]")).getText();
	}
	for (int i = 0; i < TopMenu.length; i++) {      
        for (int j = 0; j < TopMenu[i].length; j++) {   
        //循环遍历数组中的每个元素                      
            //初始化数组内容       
        	if(!(TopMenu[i][j] == null || TopMenu[i][j].length() <= 0))
        	{
            System.out.print("TopMenu["+i+"]["+j+"]="+TopMenu[i][j]+"  ");
            EndNumMenu=i+1;
        	}
            //将数组中的元素输出  
        }  
        System.out.println("   ");                	            	            
}	
//Menu Analytics----------------------------------------------------------------------------------------------------------------------
	driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[1]/a")).click();
	while(true){
		if(methods.isElementPresent(driver, By.id("follower-attribute-grid")))
			break;
	}
	String summary=driver.findElement(By.className("summary")).getText();
	String[] result=methods.Getnum(summary);
	int num;
	if(Integer.parseInt(result[2])>20)
		num=20;
	else
		num=Integer.parseInt(result[2]);
	String[][] DetailMenu=new String[num][2];
	for(int i=0;i<DetailMenu.length;i++){
		DetailMenu[i][0]=driver.findElement(By.xpath(".//*[@id='follower-attribute-grid']/div[1]/table/tbody/tr["+(i+1)+"]/td[1]")).getText();
		DetailMenu[i][1]=driver.findElement(By.xpath(".//*[@id='follower-attribute-grid']/div[1]/table/tbody/tr["+(i+1)+"]/td[2]")).getText();
	}
	for (int i = 0; i < DetailMenu.length; i++) {      
        for (int j = 0; j < DetailMenu[i].length; j++) {   
        //循环遍历数组中的每个元素                      
            //初始化数组内容       
        	if(!(DetailMenu[i][j] == null || DetailMenu[i][j].length() <= 0))	            	
            System.out.print("DetailMenu["+i+"]["+j+"]="+DetailMenu[i][j]+"  ");	            	
            //将数组中的元素输出  
        }  
        System.out.println("   ");                	            	            
        }
	MenuDiff=new String[EndNumMenu][2];
	for(int i=0;i<EndNumMenu;i++){
		for(int j=0;j<DetailMenu.length;j++)
		{
			if(TopMenu[i][0].equals(DetailMenu[j][0]))
			{
				MenuDiff[i][0]=TopMenu[i][0];
				MenuDiff[i][1]=methods.Comparison(TopMenu[i][1], DetailMenu[j][1]);
				//nt.format((Float.parseFloat(TopMenu[i][1])-Float.parseFloat(DetailMenu[j][1]))/Float.parseFloat(TopMenu[i][1]));
				break;
			}
			else if(j==DetailMenu.length){
				System.out.println(TopMenu[i][0]+" 没有匹配到!");
				logger.error(TopMenu[i][0]+" 没有匹配到!");
				break;
			}
		}
	}
	for (int i = 0; i < MenuDiff.length; i++) {      
        for (int j = 0; j < MenuDiff[i].length; j++) {   
        //循环遍历数组中的每个元素                      
            //初始化数组内容       
        	if(!(MenuDiff[i][j] == null || MenuDiff[i][j].length() <= 0))	            	
            System.out.print("MenuDiff["+i+"]["+j+"]="+MenuDiff[i][j]+"  ");	            	
            //将数组中的元素输出  
        }  
        System.out.println("   ");                	            	            
        }
	
	return MenuDiff;	
	
	}
	}
}
