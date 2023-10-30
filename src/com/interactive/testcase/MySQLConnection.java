package com.interactive.testcase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MySQLConnection {
	 public static Connection devconn = null,stageconn=null;
	    public static String driver = "com.mysql.jdbc.Driver";//"jdbc:mysql://drds4173avo0e3u3public.drds.aliyuncs.com/dev_jingsocial"  29pldds32fasaSWS2c /
	    public static String[] url = {"jdbc:mysql://drdsu7tc9c2e505epublic.drds.aliyuncs.com/jgs001_dev2",	    	                          
	                                  "jdbc:mysql://rm-bp1f3sh5w87kn9x6y.mysql.rds.aliyuncs.com/stage_jingsocial"};
	    public static String[] user = {"jgs001_dev2","stage_jingsocial"};
	    public static String[] password = {"29pldds32fasaSWS2c","AKECyTW5zH9a"};
	    
	 public static Connection devconn() {
	        try {
	            Class.forName(driver);
	            System.out.println("dev驱动加载成功");
	        } catch (ClassNotFoundException e) {
	            System.out.println("dev驱动加载错误");
	            e.printStackTrace();
	        }
	        try {
	            devconn = DriverManager.getConnection(url[0], user[0], password[0]);
	            System.out.println("dev数据库链接成功");
	        } catch (SQLException e) {
	            System.out.println("dev数据库链接错误");
	            e.printStackTrace();
	        }
	        return devconn;
}
	 public static Connection stageconn() {
	        try {
	            Class.forName(driver);
	            System.out.println("stage驱动加载成功");
	        } catch (ClassNotFoundException e) {
	            System.out.println("stage驱动加载错误");
	            e.printStackTrace();
	        }
	        try {
	        	stageconn = DriverManager.getConnection(url[1], user[1], password[1]);
	            System.out.println("stage数据库链接成功");
	        } catch (SQLException e) {
	            System.out.println("stage数据库链接错误");
	            e.printStackTrace();
	        }
	        return stageconn;
}


public static void main(String[] args) throws SQLException {
	 MySQLConnection conne= new MySQLConnection();
		//通过mysql拿到tag的id
	 Connection conns; // 获取连接
	 PreparedStatement pst; // 执行Sql语句(Statement)
	 ResultSet rs; // 获取返回结果
	 String tagID = null;
	 String tagFollower;
	 String sqlTagId="select id from tags where name='Any Action Trigger' and mid=33";
	 
	 try {
	     conns = conne.devconn();
	     pst = conns.prepareStatement(sqlTagId);
	     rs = pst.executeQuery(sqlTagId);// 执行sql语句
	     System.out.println("---------------------------------------");		
	     while (rs.next()) {
	         System.out.println(rs.getString("id"));
	         tagID=rs.getString("id");
	     }		  
	     String sqlTagFollower="SELECT count(DISTINCT(openid)) as TagFollower FROM `wechat_customer_tag` "
	 	 		+ "where mid=33 AND tag_id="+tagID+" AND openid in (SELECT openid FROM wechat_customer WHERE mid=33 and subscribe=1);";
	 	
	     pst = conns.prepareStatement(sqlTagFollower);
	     rs = pst.executeQuery(sqlTagFollower);// 执行sql语句
	     System.out.println("---------------------------------------");		
	     while (rs.next()) {
	         System.out.println(rs.getString("TagFollower"));
	         tagFollower=rs.getString("TagFollower");
	     }	
	     conns.close();
	     pst.close();
	     rs.close();
	 } catch (SQLException e) {
	     System.out.println("数据查询失败");
	     e.printStackTrace();
	 }
/*	 Connection conns; // 获取连接
	 PreparedStatement pst; // 执行Sql语句(Statement)
	 ResultSet rs; // 获取返回结果
	 String tagID = null;
	 String sql = "select art.rid, gmr.mid, gmr.response, gmr.message_type, gmr.content, r.id, r.con, r.name, gmr.create_time from group_message_record gmr" 
            +"left join res r on r.id=gmr.rid"
           +"left join article_total art on art.rid=r.id where r.wid = 33 and art.rid is not null and gmr.response='success' and gmr.message_type!='preview_message'"
           +"and gmr.message_type!='' and gmr.create_time >= '2015-01-10'  and gmr.create_time>='2016-11-12' and gmr.create_time<='2016-12-12' group by content desc";
	 String sql="select * from wechat_customer where mid=33 and subscribe=1";
	  sql="select id from tags where name='Any Action Trigger' and mid=33";
	 try {
	     conns = conne.devconn();
	     pst = conns.prepareStatement(sql);
	     rs = pst.executeQuery(sql);// 执行sql语句
	     System.out.println("---------------------------------------");
	     System.out.println("openid                              |        nickname");
	     while (rs.next()) {
	         System.out.println(rs.getString("openid") + "        |        " + rs.getString("nickname"));
	     }
	     System.out.println("---------------------------------------");
	     while (rs.next()) {
	         System.out.println(rs.getString("id"));
	         tagID=rs.getString("id");
	     }
	    
	     System.out.println(tagID);
	     conns.close();
	     pst.close();
	     rs.close();
	 } catch (SQLException e) {
	     System.out.println("数据查询失败");
	     e.printStackTrace();
	 }*/
	}
	}
	
	
