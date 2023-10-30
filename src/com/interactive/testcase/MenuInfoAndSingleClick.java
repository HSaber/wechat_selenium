package com.interactive.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hu.testcase.methods;

public class MenuInfoAndSingleClick {

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
	}
	@Parameters({"openIdNum","openIdMax"})
	@Test
	public void test(int openIdNum,int openIdMax) throws JSONException, InterruptedException, NoSuchAlgorithmException {
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		int r=methods.getAccountOrder(methods.baseUrl)[2];
		int t=openIdNum;
		int k=openIdMax;
		String access_token = HttpClient.getAccessToken(HttpClient.appidArr[p][q].toString(),HttpClient.secretArr[p][q].toString());
		System.out.println(access_token);
		String menuGet = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+ access_token;
		String result = "[" + HttpClient.sendGet(menuGet) + "]";
		JSONArray resultArray = new JSONArray(result);
        String[][] Menuarr=new String[20][2];
        
		JSONObject resultObj = resultArray.optJSONObject(0);
		JSONObject Menu = resultObj.getJSONObject("menu");
		String button = Menu.getString("button");
		String view1 = "view";
		JSONArray jsonArray = new JSONArray(button);
		for (int i = 0,m=-1; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);	
			String str= obj.getString("sub_button");
			m++;
			Menuarr[m][0]=obj.getString("name");
			if (str.equals("[]")) {
				if(obj.getString("type").equals(view1)){
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
					if (type1.equals(view1))
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
		
/*		for (int i = 0; i < Menuarr.length; i++) {      
            for (int j = 0; j < Menuarr[i].length; j++) {   
            //循环遍历数组中的每个元素  
            	//Menuarr[i][j]="*";                      
                //初始化数组内容  
            	if(!(Menuarr[i][j] == null || Menuarr[i][j].length() <= 0))
                System.out.print("Menuarr["+i+"]["+j+"]="+Menuarr[i][j]+"  ");   

                //将数组中的元素输出  
            }  
            System.out.println("   ");   
	}*/
		


		String[] arr=HttpClient.getSignature(methods.mid,HttpClient.appidArr[p][q],HttpClient.secretArr[p][q]);
		while(t<=k){
			String testStr= "<xml>"
					+ "<ToUserName><![CDATA["+HttpClient.WechatIDArr[p][q]+"]]></ToUserName>"
					+ "<FromUserName><![CDATA["+HttpClient.openidSArr[r][t]+"]]></FromUserName>"
					+ "<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>"
					+ "<MsgType><![CDATA[event]]></MsgType>"
					+ "<Event><![CDATA[VIEW]]></Event>"
					+ "<EventKey><![CDATA["+Menuarr[2][1]+"]]></EventKey>"
					+ "</xml>";
			System.out.println(Menuarr[2][1]);

		String requesturl = HttpClient.InterurlArr[p][q]+"/index/id/"+methods.mid+"?signature="+arr[2]+"&timestamp="+arr[0]+"&nonce="+arr[1];
		HttpClient test=new HttpClient();
		String[] resultArr=test.Post(requesturl,testStr);
        String str=resultArr[0];
        System.out.println(str);
        Thread.sleep(3000);
        
         testStr= "<xml>"
				+ "<ToUserName><![CDATA["+HttpClient.WechatIDArr[p][q]+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+HttpClient.openidSArr[r][t]+"]]></FromUserName>"
				+ "<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[VIEW]]></Event>"
				+ "<EventKey><![CDATA["+Menuarr[3][1]+"]]></EventKey>"
				+ "</xml>";
		System.out.println(Menuarr[3][1]);

	 resultArr=test.Post(requesturl,testStr);
     str=resultArr[0];
    System.out.println(str);
    Thread.sleep(3000);
    
    
     	//解析xml 
/*		Document doc = DocumentHelper.parseText(str);
		Element root = doc.getRootElement();
		boolean flag=root.elementText("Content").contains("test");
		assertTrue(flag);
		System.out.println(HttpClient.openidSArr[p][q]+"发送message是test成功!");*/
		t++;
		}
		
		
	}

}
