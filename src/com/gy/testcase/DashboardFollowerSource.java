package com.gy.testcase;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;


public class DashboardFollowerSource extends TestBase{
	private static Logger logger = Logger.getLogger(DashboardFollowerSource.class);
	public String getLineInfo()
    {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
 /*@BeforeMethod
	public  void setUp() throws Exception {
		 
		
	  }

  @AfterMethod
	public  void tearDown() throws Exception {
		
	  
 }*/
	 @Test
	 public void dashboardFollowerSource() throws Exception {
		 String[] refdate=refDate();
		 String[] mid=info();
		 //连接mysql
		 Connection mysqlConnection= mysqlConnect();
		 String sql="SELECT user_source,sum(new_user),sum(cancel_user) FROM follower_source_analytics where mid="+mid[2]+" and ref_date BETWEEN '"+refdate[0]+"' and '"+refdate[1]+"' group by user_source ORDER BY sum(new_user) DESC ";
		 logger.info(sql);
		 //执行sql，返回结果
		 PreparedStatement pst=mysqlConnection.prepareStatement(sql);
		 ResultSet result = pst.executeQuery(sql);
		 logger.info("user_source | sum(new_user) | sum(cancel_user)");
		 int[][] source=null;
		 int i=0;
		 while (result.next()) {
			 System.out.println(result.getString("user_source") + " | " + result.getString("sum(new_user)")+ " | " + result.getString("sum(cancel_user)"));
			 source[i][0]=Integer.parseInt(result.getString("user_source"));
			 source[i][1]=Integer.parseInt(result.getString("sum(new_user)"));
			 source[i][2]=Integer.parseInt(result.getString("sum(cancel_user"));
			 i++;
	     }
		 
		 String sql1="SELECT source_value,count(*) FROM wechat_customer where mid="+mid[2]+" AND subscribe=1 and source_type=1 and subscribe_time BETWEEN '"+refdate[4]+"' and '"+refdate[5]+"' group by source_value ORDER BY count(*) DESC";
		 logger.info(sql1);
		 PreparedStatement pst1=mysqlConnection.prepareStatement(sql1);
		 ResultSet result1 = pst1.executeQuery(sql1);
		 logger.info("source_value | count(*)");
		 int t=source.length;
		 int r=t;
		 while (result1.next()) {
			 System.out.println(result1.getString("source_value") + " | " + result1.getString("count(*)"));
			 source[t][0]=Integer.parseInt(result1.getString("source_value"));
			 source[t][1]=Integer.parseInt(result1.getString("count(*)"));
			 t++;
	     }
		 int sum=0;
		 if(t>r){
			 for(int k=r;k<source.length;k++){
				 String sql2="SELECT count(*) FROM wechat_customer where mid="+mid[2]+" AND subscribe=0 and source_value="+source[k][0]+" and unsubscribe_time BETWEEN '"+refdate[4]+"' and '"+refdate[5]+"'";
				 logger.info(sql2);
				 PreparedStatement pst2=mysqlConnection.prepareStatement(sql2);
				 ResultSet result2 = pst2.executeQuery(sql2);
				 logger.info("count(*)");
				 while (result2.next()) {
					 System.out.println(result2.getString("count(*)"));
					 source[k][2]=Integer.parseInt(result2.getString("count(*)"));
					 
			     }
				 sum=sum+source[k][1];
			 }
			 for(int f=0;f<r;f++){
				 if(source[f][0]==30){
					 if(source[f][0]>sum){
						 source[f][0]=source[f][0]-sum;
					}else{
						source[f][0]=0;
					}
					 break;
				 }else{
					 logger.info("正在遍历微信获取的follower source数据第"+f+1+"，还没检测到扫码来源的数据！！！");
				 }
			 }
			 
		 }else{
			 logger.info("选择时间段内没有用户扫二维码关注！！");
		 }
		 int[] temp=null;
		 for(int s=0;s<source.length;s++){
			 if(source[s][1]<source[s+1][1]){
				 for(int c=0;c<3;c++){
					 temp[c]=source[s+1][c];
					 source[s+1][c]=source[s][c];
					 source[s][c]=temp[c];
				 }
			 }
		 }
		 //OTHER = 0;SEARCH = 1;SHARE_CARD = 17;SCAN_QRCODE = 30;POST_SHARE = 43;PAYMENT = 51;POST_IN_SHARE = 57;POST_ADS = 75;MOMENTS = 78;
		 int[] wechat={0,1,17,30,43,51,57,75,78};
		 int[] olduser=new int[source.length];
		 String[] grow=new String[source.length];
		 int status=0;
		 for(int m=0;m<source.length;m++){
			 for(int w=0;w<10;w++){
				 if(source[m][0]==wechat[w]){
					 String sql3="SELECT sum(new_user) FROM follower_source_analytics where mid="+mid[2]+" and user_source="+source[m][0]+" and ref_date BETWEEN '"+refdate[2]+"' and '"+refdate[3]+"'";
					 logger.info(sql3);
					 //执行sql，返回结果
					 PreparedStatement pst3=mysqlConnection.prepareStatement(sql3);
					 ResultSet result3 = pst3.executeQuery(sql3);
					 logger.info("sum(new_user)");
					
					 while (result3.next()) {
						 System.out.println(result3.getString("sum(new_user)"));
						 olduser[m]=Integer.parseInt(result3.getString("sum(new_user)"));
				     }
					 status=1;
					 break;
				 }
			 }
			 if(status==0){
				 String sql4="SELECT count(*) FROM wechat_customer where mid="+mid[2]+" AND subscribe=1 and source_type=1 and source_value="+source[m][0]+" and subscribe_time BETWEEN '"+refdate[6]+"' and '"+refdate[7]+"'";
				 logger.info(sql4);
				 //执行sql，返回结果
				 PreparedStatement pst4=mysqlConnection.prepareStatement(sql4);
				 ResultSet result4 = pst4.executeQuery(sql4);
				 logger.info("count(*)");
				
				 while (result4.next()) {
					 System.out.println(result4.getString("count(*)"));
					 olduser[m]=Integer.parseInt(result4.getString("count(*)"));
			     }
			 }
		

	    		double a =(double)source[m][1];
	    		double b =(double)olduser[m];
	    		String growth ="";
	    		if(b==0){
	    			growth="--";
	    		}else if(a==0){
	    			growth="0%";
	    		}else{
		    		double g=Math.round((a-b)/b*1000)*0.1;
		    		DecimalFormat df = new DecimalFormat("#.#");
		    		
		    		if(g<0){
		    			growth=df.format(Math.abs(g))+"%";
		    		}else{
		    			growth=df.format(g)+"%";
		    		}
	    		}
	    		grow[m]=growth;
	    		logger.info("计算出百分比为"+growth);
	    		
	    		/*String percent=driver.findElement(By.xpath(".//*[@id='follower_source']/div/div[1]/div[2]/div[2]/table/tbody/tr["+m+1+"]/td[3]/span")).getText();
	    		try{
	    			Assert.assertEquals(percent, growth);
	    		}catch(Error e){
	    			Assert.fail();
	    		}	*/ 
			 
		}
		
		
		 
		 
	 }
}
