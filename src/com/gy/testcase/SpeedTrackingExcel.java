package com.gy.testcase;

import org.testng.annotations.Test;
import java.io.File;

import org.openqa.selenium.JavascriptExecutor;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class SpeedTrackingExcel extends TestBase{
	@Test
	  public void speedTrackingExcel() throws Exception {
		//java创建excel
		/*try {
	            // 打开文件
	            WritableWorkbook book = Workbook.createWorkbook(new File("jgs005.xls"));
	            // 生成名为“dev speed tracking”的工作表，参数0表示这是第一页
	            WritableSheet sheet = book.createSheet("dev speed tracking", 0);
	            // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
	            // 以及单元格内容为test
	            Label label = new Label(0, 0, "页面名称");
				// 将定义好的单元格添加到工作表中
	            sheet.addCell(label);
				jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
	            sheet.addCell(number);

	            // 写入数据并关闭文件
	            book.write();
	            book.close();

	        } catch (Exception e) {
	            System.out.println(e);
	        }*/
		//读并更改已经存在的excel
		try {
	            Workbook book = Workbook.getWorkbook(new File("speed.xls"));
	            WritableWorkbook book1 = Workbook.createWorkbook(new File("speed.xls"),book);
	    		WritableSheet sheetw = book1.getSheet(0);
	            Sheet sheet = book.getSheet(0);
	            driver.manage().deleteAllCookies();
	            int row=0 ,column= 0; 
	            JavascriptExecutor js = (JavascriptExecutor)driver;
	            // 获得第一个工作表对象
	            if(baseUrl=="https://dev.jingsocial.com/"){
	            	sheet = book.getSheet(0);
	            	sheetw = book1.getSheet(0);
	            	
	            	
	            }else if(baseUrl=="https://staging.jingsocial.com/"){
	            	sheet = book.getSheet(1);
	            	sheetw = book1.getSheet(1);
	            	
	            }else if(baseUrl=="https://app.jingsocial.com/"){
	            	sheet = book.getSheet(2);
	            	sheetw = book1.getSheet(2);
	            	
	            }
	            
	            row = sheet.getRows();
          	column = sheet.getColumns();
          	System.out.println("excel有效行数是："+ row);
          	System.out.println("excel有效列数是："+ column);
	            
	            // 得到第一列第一行的单元格
	            
	            
	            long endtime0= (long)js.executeScript("return window.performance.timing.loadEventEnd");
	    		long starttime0= (long)js.executeScript("return window.performance.timing.navigationStart");
	    		long loadtime0=endtime0-starttime0;
	    		float loadtimes0 = (float)(Math.round(loadtime0/10))/100;
	    		System.out.println("darshboard的加载时间是：" + loadtimes0 +"s");
	            //设置标题字体
	            WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);    
	            WritableCellFormat headerFormat = new WritableCellFormat (headerFont);
	            //标红时间过长的loadtime
	            WritableFont font1 = new WritableFont(WritableFont.ARIAL,11,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED);  
	            WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
	            
	            String time = currentTime();
	            sheetw.addCell(new Label(column, 0, "Load Time(" + time +")",headerFormat));
	            sheetw.setColumnView(column, 29);  
	            
	            int i=1;
	            if(loadtimes0<10){
    				sheetw.addCell(new Label(column, i, loadtimes0 +"s"));
	            }else{
    				sheetw.addCell(new Label(column, i, loadtimes0 +"s",cellFormat1));
	            }
	            
	            for(i =2;i<row;i++){
		            Cell cell1 = sheet.getCell(1, i);
		            Cell cell2 = sheet.getCell(0, i);
		            String url = cell1.getContents();
		            String timeload = cell2.getContents();
		            System.out.println(url);
		           
		            if(url!=""){
		            	System.out.println(i);
			    		driver.get(baseUrl + url);
			    		long endtime= (long)js.executeScript("return window.performance.timing.loadEventEnd");
			    		long starttime= (long)js.executeScript("return window.performance.timing.navigationStart");
			    		long loadtime=endtime-starttime;
			    		float loadtimes = (float)(Math.round(loadtime/10))/100;
			    		System.out.println(timeload + "的加载时间是：" + loadtimes +"s");
			    		if(loadtimes<10){
			    				sheetw.addCell(new Label(column, i, loadtimes +"s"));
			    		}else{
			    				sheetw.addCell(new Label(column, i, loadtimes +"s",cellFormat1));
			    		}
			    		//jxl.write.Number number = new jxl.write.Number(column, i, loadtime);
			            //sheetw.addCell(number);
			    		Thread.sleep(3000);
		            }
	            }
	            book1.write();
	            book.close();
	            book1.close();
	        } catch (Exception e) {
	        	
	            System.out.println(e);
	        }
	 }
}
