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

public class Demo {

	String Content;
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws JSONException, NoSuchAlgorithmException, InterruptedException, DocumentException {

		String access_token = HttpClient.getAccessToken("wx6dfc3052df5fe2f2","c758d9fd64090ff0478d2d2714dc9ec9");
		String menuGet = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+ access_token;
		String result = "[" + HttpClient.sendGet(menuGet) + "]";
		JSONArray resultArray = new JSONArray(result);
        String[][] Menuarr=new String[20][2];
        System.out.println(result);
		JSONObject resultObj = resultArray.optJSONObject(0);
		JSONObject Menu = resultObj.getJSONObject("menu");
		String button = Menu.getString("button");
        String vartime=methods.timeDate();
		JSONArray jsonArray = new JSONArray(button);
		for (int i = 0,m=-1; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);	
			String str= obj.getString("sub_button");
			m++;
			Menuarr[m][0]=obj.getString("name");
			if (str.equals("[]")) {
				if(obj.getString("type").equals("view")){
					Menuarr[m][0]=obj.getString("name");
					Menuarr[m][1]=obj.getString("url");
				}else{
				Menuarr[m][0]=obj.getString("name");
				Menuarr[m][1]=obj.getString("key");}
			} else {
				String sub_button = obj.getString("sub_button");
				JSONArray buttonArray = new JSONArray(sub_button);
				for (int j = 0; j < buttonArray.length(); j++) {
					
					JSONObject obj1 = buttonArray.getJSONObject(j);
					Menuarr[m][0]=obj1.getString("name");
					String type1 = obj1.getString("type");
					if (type1.equals("view"))
					{
					    Menuarr[m][1]=obj1.getString("url");
					}
					else
					{
						Menuarr[m][1]=obj1.getString("key");
					}
					m++;
				}
				
				
			}
		}

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
		
	
	}
}








