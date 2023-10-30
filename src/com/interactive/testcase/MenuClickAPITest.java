package com.interactive.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.stream.JsonReader;
import com.hu.testcase.methods;

public class MenuClickAPITest {

	//String Content;
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}

	@Test(dependsOnGroups="MenuSetting",groups="MenuClick")
	public void test() throws JSONException, NoSuchAlgorithmException, InterruptedException, DocumentException {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		// 查询menu
		String[][] Menuarr=InteractiveMethods.MenuGet(HttpClient.appidArr[p][q], HttpClient.secretArr[p][q]);
		int menucount=0;
		for (int i = 0; i < Menuarr.length; i++) {      
            for (int j = 0; j < Menuarr[i].length; j++) {   
            //循环遍历数组中的每个元素  
            	//Menuarr[i][j]="*";                      
                //初始化数组内容  
            	if(!(Menuarr[i][j] == null || Menuarr[i][j].length() <= 0))
            	{
                System.out.print("Menuarr["+i+"]["+j+"]="+Menuarr[i][j]+"  ");   
                menucount++;
            	}

                //将数组中的元素输出  
            }  
            System.out.println("   ");   
	}
		
		methods.Menunum=menucount;
		//点击菜单
		String[][] MenuClickResultArr=InteractiveMethods.MenuClickFunc(HttpClient.InterurlArr[p][q], HttpClient.appidArr[p][q], HttpClient.secretArr[p][q],
				HttpClient.WechatIDArr[p][q], methods.mid, HttpClient.openidArr[p][q], Menuarr);
		
		for (int i = 0; i < MenuClickResultArr.length; i++) {      
            for (int j = 0; j < MenuClickResultArr[i].length; j++) {   
            //循环遍历数组中的每个元素  
            	//Menuarr[i][j]="*";                      
                //初始化数组内容  
            	if(!(MenuClickResultArr[i][j] == null || MenuClickResultArr[i][j].length() <= 0))
                System.out.print("MenuClickResultArr["+i+"]["+j+"]="+MenuClickResultArr[i][j]+"  ");   
                //将数组中的元素输出  
            }  
            System.out.println("   ");   
	}
		

	}
}








