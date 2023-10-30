package com.interactive.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
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
import org.junit.rules.Verifier;
import org.testng.asserts.SoftAssert;

import com.google.gson.stream.JsonReader;
import com.hu.testcase.methods;

public class MenuPersonalInfoQuery {
	 private SoftAssert softAssert = new SoftAssert();
	String Content;
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}

	@Test(dependsOnGroups="MenuPublish")
	public void test() throws JSONException, NoSuchAlgorithmException, InterruptedException, DocumentException {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		int r=0,k=0;
		if(methods.baseUrl.contains("dev")){
			if(methods.mid.equals("33"))
				r=0;
			else if(methods.mid.equals("51"))
				r=1;
		}
		if(methods.baseUrl.contains("staging")){
		    if(methods.mid.equals("59"))
		    	r=3;
		    	else if(methods.mid.equals("68"))
		    		r=4;
		    	else if(methods.mid.equals("73"))
		    		r=5;
		}
		if(methods.baseUrl.contains("app")){
			if(methods.mid.equals("42"))
				r=6;
			else if(methods.mid.equals("61"))
				r=7;
		}
		String[] Nickname=HttpClient.Nickname;
		String[][] MenuOpenArr=HttpClient.MenuOpenArr;
		for(int t=0;t<MenuOpenArr[0].length;t++){
			System.out.println("=============================="+MenuOpenArr[r][t]+"  "+Nickname[t]+"的menu============================================\n");
			String[][] Menuarr=InteractiveMethods.PersonalMenuGet(HttpClient.appidArr[p][q], HttpClient.secretArr[p][q], MenuOpenArr[r][t]);

			for (int i = 0; i < Menuarr.length; i++) {      
	            for (int j = 0; j < Menuarr[i].length; j++) {   
	            //循环遍历数组中的每个元素  
	            	//Menuarr[i][j]="*";                      
	                //初始化数组内容  
	            	if(!(Menuarr[i][j] == null || Menuarr[i][j].length() <= 0))
	                  System.out.print("Menuarr["+i+"]["+j+"]="+Menuarr[i][j]+"  ");   
	                //将数组中的元素输出  
	            }  
	            System.out.println("   ");   
		}
		
			int j=0;
			if(Nickname[t].equals("H.'")){	
				try{
					AssertJUnit.assertTrue(Menuarr[0][0].contains("Img-"));
					System.out.println(Nickname[t]+"Menu 匹配成功!\n");
				}catch(AssertionError e ){
					System.out.println(Nickname[t]+"Menu 匹配失败!\n");
					e.printStackTrace();
				}
			}
			if(Nickname[t].equals("Candy")){  
				try{
					AssertJUnit.assertTrue(Menuarr[0][0].contains("Img-"));
					System.out.println(Nickname[t]+"Menu 匹配成功!\n");
				}catch(AssertionError e ){		
					System.out.println(Nickname[t]+"Menu 匹配失败!\n");
					e.printStackTrace();
				}
			}
			
			if(Nickname[t].equals("rainbow")){
				try{
					AssertJUnit.assertTrue(Menuarr[0][0].contains("PersonalMenu"));
					System.out.println(Nickname[t]+"Menu 匹配成功!\n");
				}catch(AssertionError e ){		
					System.out.println(Nickname[t]+"Menu 匹配失败!\n");
					e.printStackTrace();
				}
			}
			if(Nickname[t].equals("rainbowgy")){
				try{
					AssertJUnit.assertTrue(Menuarr[0][0].contains("PersonalMenu"));
					System.out.println(Nickname[t]+"Menu 匹配成功!\n");
				}catch(AssertionError e ){		
					System.out.println(Nickname[t]+"Menu 匹配失败!\n");
					e.printStackTrace();
				}
			}
			if(Nickname[t].equals("T_T")){
					try{
						AssertJUnit.assertTrue(Menuarr[0][0].contains("PFormevent"));
						System.out.println(Nickname[t]+"Menu 匹配成功!\n");
					}catch(AssertionError e ){		
						System.out.println(Nickname[t]+"Menu 匹配失败!\n");
						e.printStackTrace();
					}
			}
				
			if(Nickname[t].equals("Minglong")){
				try{
					AssertJUnit.assertTrue(Menuarr[0][0].contains("PFormevent"));
					System.out.println(Nickname[t]+"Menu 匹配成功!\n");
				}catch(AssertionError e ){		
					System.out.println(Nickname[t]+"Menu 匹配失败!\n");
					e.printStackTrace();
				}
			}
			
		}
		
	
	}
}








