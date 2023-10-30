package com.gy.testcase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardAvgAction extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardAvgAction.class);
	public String getLineInfo(){
		
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
	/* @BeforeMethod
	public  void setUp() throws Exception {
		 
		
	  }

  @AfterMethod
	public  void tearDown() throws Exception {
		
	  
 }*/
	
	 @Test
	 public void dashboardAvgAction() throws Exception {
		long[] create = createTime();
		 
		String avgActions=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[4]/div[2]")).getText();
		double avgActionsLeft=0;
		double avgActionsRight=0;
		 try{
			 
			 String regex = String.format("%s.*%s", ""," vs");
			  Pattern pattern = Pattern.compile(regex);
			  Matcher matcher = pattern.matcher(avgActions);
			    if (matcher.find()){
			    	avgActionsLeft = Double.valueOf(matcher.group().replace("", "").replace(" vs", ""));
			    	
					System.out.println("获取的数字是："+avgActionsLeft);
					
				}else{

					System.out.println("not found avgActionsLeft");
					
				} 
		 	}catch(java.lang.NumberFormatException e){
		 		avgActionsLeft=0;
		 	}
		
		 try{
			 
			 String regex = String.format("%s.*%s", "vs ","");
			  Pattern pattern = Pattern.compile(regex);
			  Matcher matcher = pattern.matcher(avgActions);
			    if (matcher.find()){
			    	avgActionsRight = Double.valueOf(matcher.group().replace("vs ", "").replace("", ""));
			    	
					System.out.println("获取的数字是："+avgActionsRight);
					
				}else{

					System.out.println("not found avgActionsRight");
					
				} 
		 	}catch(java.lang.NumberFormatException e){
		 		avgActionsRight=0;
		 	}
		 
		 
		double a =avgActionsLeft;
		double b =avgActionsRight;
		double g=Math.round((a-b)/b*1000)*0.1;
		DecimalFormat df = new DecimalFormat("#.#");
		String growth ="";
		if(g<0){
			growth=df.format(Math.abs(g))+"";
		}else{
			growth=df.format(g)+"";
		}
		
		logger.info("计算出百分比为"+g);
		
		
		String zero=growth.substring(growth.length()-1);
		logger.info("百分比小数点后一位的值是： "+zero);
		if(zero.equals("0")){
			
			growth=growth.substring(0,growth.length()-2);
			logger.info("zero是0，得到的百分比值是： "+growth);
		}
		
		logger.info("growth的值是： "+growth);
		
		int error =0;
		String[] errorinfo=new String[3];
		String percent=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[4]/div[1]/span")).getText();
		try{
			
				Assert.assertEquals(percent, growth+"%");
			
		}catch(Error e){
			
			logger.error("Dashboard Avg. Actions的增长百分比计算错误，请检查！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard Avg. Actions的增长百分比计算错误，请检查！！！";
			
		} 
		
		
		//es没有user_actions_index这个表，需要去mysql查询
		 String[] mid=info();
		 //连接mysql
		 Connection mysqlConnection= mysqlConnect();
		 String sql="SELECT count(*),sum(total_number) FROM user_actions_index where mid="+mid[2]+" and type='avg_actions' and created_intdate BETWEEN '"+create[0]+"' and '"+create[1]+"'";
		 logger.info(sql);
		 //执行sql，返回结果
		 PreparedStatement pst=mysqlConnection.prepareStatement(sql);
		 ResultSet result = pst.executeQuery(sql);
		 logger.info("count(*) | sum(total_number)");
		 
		 double sum=0,count=0;
		 while (result.next()) {
			 System.out.println(result.getString("count(*)")+" | "+result.getString("sum(total_number)"));
			 sum=Double.valueOf(result.getString("sum(total_number)"));
			 count=Double.valueOf(result.getString("count(*)"));
			 
	     }
		 
		 
		String avg="";
 		
	    double n=Math.round(sum/count*1000)*0.1;
	    avg=df.format(n);
	    		
	    try{
		 	Assert.assertEquals(avgActionsLeft+"", avg);
	    }catch(Error e){
	    	logger.error("Dashboard avg.action环比数据左边值显示"+avgActionsLeft+"，但是数据库查询的值是"+avg+"请检查！！！");
			error++;
			errorinfo[1]=getLineInfo()+",  Dashboard avg.action环比数据左边值显示"+avgActionsLeft+"，但是数据库查询的值是"+avg+"请检查！！！";
	    }
 		
	    String sql1="SELECT count(*),sum(total_number) FROM user_actions_index where mid="+mid[2]+" and type='avg_actions' and created_intdate BETWEEN '"+create[2]+"' and '"+create[3]+"'";
		 logger.info(sql1);
		 //执行sql，返回结果
		 PreparedStatement pst1=mysqlConnection.prepareStatement(sql1);
		 ResultSet result1 = pst1.executeQuery(sql1);
		 logger.info("count(*) | sum(total_number)");
		 
		 double sum1=0,count1=0;
		 while (result1.next()) {
			 System.out.println(result1.getString("count(*)")+" | "+result1.getString("sum(total_number)"));
			 sum1=Double.valueOf(result1.getString("sum(total_number)"));
			 count1=Double.valueOf(result1.getString("count(*)"));
			 
	     }
		 
		 
		String avg1="";
		
	    double n1=Math.round(sum1/count1*1000)*0.1;
	    avg1=df.format(n1);
	    		
	    try{
		 	Assert.assertEquals(avgActionsRight+"", avg1);
	    }catch(Error e){
	    	logger.error("Dashboard avg.action环比数据右边值显示"+avgActionsRight+"，但是数据库查询的值是"+avg1+"请检查！！！");
			error++;
			errorinfo[2]=getLineInfo()+",  Dashboard avg.action环比数据右边值显示"+avgActionsRight+"，但是数据库查询的值是"+avg1+"请检查！！！";
	    }
 		
 		
		
		 
		 if(error>0){
			 Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n");
		 }
		        
	}

}
