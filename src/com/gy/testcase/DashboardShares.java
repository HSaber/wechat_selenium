package com.gy.testcase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardShares extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardShares.class);
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
	 public void dashboardShares() throws Exception {
		 long[] create = createTime();
		 
		String shares=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[5]/div[2]")).getText();
		int sharesLeft=0;
		int sharesRight=0;
		 try{
			 sharesLeft=catchNum(""," vs",shares);
		 	}catch(java.lang.NumberFormatException e){
		 		sharesLeft=0;
		 	}
		
		 try{
			 sharesRight=catchNum("vs ","",shares);
		 	}catch(java.lang.NumberFormatException e){
		 		sharesRight=0;
		 	}
		 
		double a =(double)sharesLeft;
		double b =(double)sharesRight;
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
		String percent=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[5]/div[1]/span")).getText();
		try{
			
				Assert.assertEquals(percent, growth+"%");
			
		}catch(Error e){
			
			logger.error("Dashboard shares的增长百分比计算错误，请检查！！！");
			error++;
			errorinfo[0]=getLineInfo()+",  Dashboard shares的增长百分比计算错误，请检查！！！";
			
		} 
		
		
		//包含我们自己系统的和微信官方的，自己系统的可以去action log查，分开校验，确保我们系统是正确的，'share_post','share_from_api'  from user_actions_index
		 
		 String[] mid=info();
		 //连接mysql
		 Connection mysqlConnection= mysqlConnect();
		 String sql="SELECT count(*) FROM user_actions_index where mid="+mid[2]+" and type='share_from_api' and created_intdate BETWEEN '"+create[0]+"' and '"+create[1]+"'";
		 logger.info(sql);
		 //执行sql，返回结果
		 PreparedStatement pst=mysqlConnection.prepareStatement(sql);
		 ResultSet result = pst.executeQuery(sql);
		 logger.info("count(*)");
		 
		 int api=0;
		 while (result.next()) {
			 System.out.println(result.getString("count(*)"));
			 api=Integer.parseInt(result.getString("count(*)"));
	     }
		 
		 String sql1="SELECT count(*) FROM user_action_record where wid="+mid[2]+" and type='share_post' and created_date BETWEEN '"+create[0]+"' and '"+create[1]+"'";
		 logger.info(sql1);
		 //执行sql，返回结果
		 PreparedStatement pst1=mysqlConnection.prepareStatement(sql1);
		 ResultSet result1 = pst1.executeQuery(sql1);
		 logger.info("count(*)");
		 
		 int local=0;
		 while (result1.next()) {
			 System.out.println(result1.getString("count(*)"));
			 local=Integer.parseInt(result1.getString("count(*)"));
	     }
		 
		 int share=api+local;
		 try{
			 	Assert.assertEquals(sharesLeft, share);
		 }catch(Error e){
			 logger.error("Dashboard shares环比数据左边值显示"+sharesLeft+"，但是数据库查询的值是"+share+"请检查！！！");
				error++;
				errorinfo[1]=getLineInfo()+",  Dashboard shares环比数据左边值显示"+sharesLeft+"，但是数据库查询的值是"+share+"请检查！！！";
		 }
		 
		 String sql2="SELECT count(*) FROM user_actions_index where mid="+mid[2]+" and type='share_from_api' and created_intdate BETWEEN '"+create[2]+"' and '"+create[3]+"'";
		 logger.info(sql2);
		 //执行sql，返回结果
		 PreparedStatement pst2=mysqlConnection.prepareStatement(sql2);
		 ResultSet result2 = pst2.executeQuery(sql2);
		 logger.info("count(*)");
		 
		 int api1=0;
		 while (result2.next()) {
			 System.out.println(result2.getString("count(*)"));
			 api1=Integer.parseInt(result2.getString("count(*)"));
	     }
		 
		 String sql3="SELECT count(*) FROM user_action_record where wid="+mid[2]+" and type='share_post' and created_date BETWEEN '"+create[2]+"' and '"+create[3]+"'";
		 logger.info(sql3);
		 //执行sql，返回结果
		 PreparedStatement pst3=mysqlConnection.prepareStatement(sql3);
		 ResultSet result3 = pst3.executeQuery(sql3);
		 logger.info("count(*)");
		 
		 int local1=0;
		 while (result3.next()) {
			 System.out.println(result3.getString("count(*)"));
			 local1=Integer.parseInt(result3.getString("count(*)"));
	     }
		 
		 int share1=api1+local1;
		 try{
			 	Assert.assertEquals(sharesRight, share1);
		 }catch(Error e){
			 logger.error("Dashboard shares环比数据右边值显示"+sharesRight+"，但是数据库查询的值是"+share1+"请检查！！！");
				error++;
				errorinfo[2]=getLineInfo()+",  Dashboard shares环比数据右边值显示"+sharesRight+"，但是数据库查询的值是"+share1+"请检查！！！";
		 }
		 
		 if(error>0){
			 Assert.fail(errorinfo[0]+"\n"+errorinfo[1]+"\n"+errorinfo[2]+"\n");
		 }
		 
	}

	
	
	
}
