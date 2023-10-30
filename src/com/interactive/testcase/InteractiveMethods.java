package com.interactive.testcase;

import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hu.testcase.methods;

public class InteractiveMethods {
	

	public static String[][] MenuClickFunc(String InterUrl,String appid,String appSecret,
			String wechatID,String mid,String openid,String[][] Menuarr)
			throws DocumentException, NoSuchAlgorithmException, InterruptedException
	{
		String[][] MenuClickResultArr=new String[20][2];
		String Content;
		String vartime=methods.timeDate();
		String[] arr=HttpClient.getSignature(mid,appid,appSecret);
		String requesturl =HttpClient.RequestUrl(InterUrl, mid, arr[2], arr[0], arr[1]);
		for(int i=0;i<Menuarr.length;i++){
				if(!(Menuarr[i][0] == null || Menuarr[i][0].length() <= 0)){
				MenuClickResultArr[i][0]=Menuarr[i][0];
				if(Menuarr[i][1].substring(0,7).contains("http://")&&(!Menuarr[i][1].contains("@Image"))){
					String testStr= "<xml>"
							+ "<ToUserName><![CDATA["+wechatID+"]]></ToUserName>"
							+ "<FromUserName><![CDATA["+openid+"]]></FromUserName>"
							+ "<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>"
							+ "<MsgType><![CDATA[event]]></MsgType>"
							+ "<Event><![CDATA[VIEW]]></Event>"
							+ "<EventKey><![CDATA["+Menuarr[i][1]+"]]></EventKey>"
							+ "</xml>";
//					System.out.println("You viewed menu "+Menuarr[i][0]);
					String[] StrXML=HttpClient.Post(requesturl,testStr);			
					MenuClickResultArr[i][1]=Menuarr[i][1];
				}
				else{
				   String testStr= "<xml>"
						+ "<ToUserName><![CDATA["+wechatID+"]]></ToUserName>"
						+ "<FromUserName><![CDATA["+openid+"]]></FromUserName>"
						+ "<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>"
						+ "<MsgType><![CDATA[event]]></MsgType>"
						+ "<Event><![CDATA[CLICK]]></Event>"
						+ "<EventKey><![CDATA["+Menuarr[i][1]+"]]></EventKey>"
						+ "</xml>";
				//	System.out.println("You clicked menu "+Menuarr[i][0]+"  "+Menuarr[i][1]);
					String[] StrXML=HttpClient.Post(requesturl,testStr);
				if(StrXML[0]!=null){
						//解析xml 
						StrXML[0]=methods.xmlFormat(StrXML[0]);
						Document doc = DocumentHelper.parseText(StrXML[0]);
						Element root = doc.getRootElement();
					if( root.elementText("MsgType").equals("news")){
					    	 MenuClickResultArr[i][1]=root.elementText("ArticleCount");
							for(Iterator m=root.elementIterator("Articles");m.hasNext();){
							Element foo=(Element)m.next();							
							for(Iterator it1 = foo.elementIterator("item");it1.hasNext();){
							Element title=(Element)it1.next();
							String Title = title.elementText("Title");
							System.out.println("-----post----------");
							System.out.println(Title);
							}  
							}
						}							
					else if( root.elementText("MsgType").equals("transfer_customer_service")){
						String MsgType = root.elementText("MsgType");
						MenuClickResultArr[i][1]=MsgType;
						}	
						//Survey 调动接口
						//vartime//"Survey"+vartime)
					else if(Menuarr[i][0].contains("Survey")){							
							Content = root.elementText("Content");
							if(Content.contains("您已经参加过")){
								System.out.println("您已经参加过Survey!!!");		
							}else{
							assertTrue(Content.contains("您的姓名"));
							String[] msgarr={"huhuan"+vartime,"1830210"+vartime,"江苏"+vartime,"Achang"+vartime,"女"+vartime,vartime,vartime+"00","2","哪里","1","7"+vartime,"3","晚上"+vartime};
						                       	String[] Quearr={"您的手机号","您的家乡是","您的公司名称是什么","您的性别","你平均一个月的花费大约是多少元","你期望一个月的月消费是多少元","你一般每个月花费主要在哪个方面","你日常一般在哪里用餐","你是否有网购经历","你网购的频率是","今天的天气怎么样","平时您午餐时间大概在几点","感谢您参加我们的调研"};
							for(int m=0;m<msgarr.length;m++){
								String msgStr= "<xml>" +
										"<ToUserName><![CDATA["+wechatID+"]]></ToUserName>" +
										"<FromUserName><![CDATA["+openid+"]]></FromUserName>"+
										"<CreateTime>"+System.currentTimeMillis()/1000L+"</CreateTime>" +
										"<MsgType><![CDATA[text]]></MsgType>" +
										"<Content><![CDATA["+msgarr[m]+"]]></Content>"+
										"<MsgId>6278109033050373709</MsgId>" +
										"</xml>";
								String[] StrXML1=HttpClient.Post(requesturl,msgStr);
								StrXML1[0]=methods.xmlFormat(StrXML1[0]);
								Document docmsg = DocumentHelper.parseText(StrXML1[0]);
								Element rootmsg = docmsg.getRootElement();
								Content = rootmsg.elementText("Content");
								assertTrue(Content.contains(Quearr[m]));
							}	
						    }
							MenuClickResultArr[i][1]=Content;
						}
				 else  if(Menuarr[i][0].contains("Image")){
						Iterator m1=root.elementIterator("Image");
						Element foo1=(Element)m1.next();	
						Content = foo1.elementText("MediaId");
						MenuClickResultArr[i][1]=Content;
						}
				else {
						Content = root.elementText("Content");
						MenuClickResultArr[i][1]=Content;
					 }
						}
						else
					    MenuClickResultArr[i][1]="Return Nothing";
					}
				}
				
		}
		return MenuClickResultArr;
	}
		
		
		public static String[][] MenuGet(String appID,String appSecret) throws JSONException{
			
			String access_token = HttpClient.getAccessToken(appID,appSecret);
			String menuGet = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+ access_token;
			String result = "[" + HttpClient.sendGet(menuGet) + "]";
			JSONArray resultArray = new JSONArray(result);
	        String[][] Menuarr=new String[20][2];
	        
			JSONObject resultObj = resultArray.optJSONObject(0);
			JSONObject Menu = resultObj.getJSONObject("menu");
			String button = Menu.getString("button");
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
			
			return Menuarr;
		}
		
		
		
		public static String[][] PersonalMenuGet(String appID,String appSecret,String openid) throws JSONException{
			
			String access_token = HttpClient.getAccessToken(appID,appSecret);
			String menuGet = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token="+ access_token;
			String testStr="{\"user_id\":\""+openid+"\"}";
			String[] resultArr = HttpClient.Post(menuGet,testStr);
			String result="[" +resultArr[0]+ "]";
			JSONArray resultArray = new JSONArray(result);
	        String[][] Menuarr=new String[20][2];
	        
			JSONObject resultObj = resultArray.optJSONObject(0);
			JSONObject Menu = resultObj.getJSONObject("menu");
			String button = Menu.getString("button");
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
			
			return Menuarr;
		}
		
		
	}
	

