package com.interactive.testcase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hu.testcase.methods;

//testpost主要是实现发送消息给我们平台的接口 然后触发得到我们平台对应的数据 目前就是匹配了keyword
public class HttpClient {
        

	public static String[][] InterurlArr={{"http://dev.callback.jingsocial.com/index/index/index","http://dev.callback.jingsocial.com/index/index/index","https://dev.jingsocial.com/wechat"},//dev 33 ,51,68
		{"http://staging.callback.jingsocial.com/index/index/index","http://staging.callback.jingsocial.com/index/index/index","http://staging.callback.jingsocial.com/index/index"},//staging 59,68
		{"http://callback.jingsocial.com/index/index/index","http://callback.jingsocial.com/index/index/index"},//app 42,61
		{"http://shareinstance1.callback.jingsocial.com/index/index/index"}};

	public static String[][] appidArr = {{"wx3310f2aaf181c636","wx2cf6927bd5f0f924","wxf8c2129423239996"},//dev 33,51,68
	                                    {"wx1bacd2eeeb49808e","wxe824fbe92f79fc04","wxad2eba52a554d2bb"},//staging 59,68,73
		                                {"wx8469538cd2314ba4","wxdc48692cfc614ee7"},//app 42,61
		                                {"wx317f5a52371c5ba2"}};//shareinstance
	
	public static String[][] secretArr= {{"1b7bea7a641739af5b7f89e6acad84e4","976b5567bd8c010690dfbbcfdd395826","c996da06ad18a149d7b1248de48b55b1"},//dev 33,51,68
	                                   	{"19c17864ade9b035c991cf8d6860c229","61c7abf87076fef55d67facaeab778f8","22ffdaef0ef302408ecd7c74f7aad41b"},//staging 59,68,73
                                        {"6eed6f9d25f5899f02d9b41913b1075e","3d7427a78002ace28953c703fc6982ee"},//app 42,61
	                                   	{"747fb72ff92c0c290047f24b5c8ecf3f"}};	
	
	public static String[][] WechatIDArr={{"JING_digital","gh_80abcc022dbc","weixindyhao"},//dev 33,51,68
	                                     {"agent01_jgs009","JGS008_JGS","weixindyhao2"},//staging 59,68
		                                 {"jingsocial_test","JGSAchang006"},//app 42,61
		                                 {"gh_4f19d7bd44b9"}};
	
	public static String[][] openidArr={{"oOuXGtxg1eOzjkfhwpoPP1JA-YGs","oKNc1w93DpFAYKxJb3jPYx0KthX8","o9rovs_Ff_ZHZ-RaGSL-VjMDKtl0"},//dev 33,51,68
		                               {"ohaIcwFFrbnMf2aGyJJKTDdi8clA","oh_Dsw3xs7jYDMb4c9wRYxrKmQKc","oleSFv5S2ilDxnl-tg3gwBdV3Z0M"},//staging 59,68,73
		                               {"oqKzat3N16C1b4QDnXnsGswvGG1s","oNrb6voHvP08GliX03tdpMQXxDFI"},//app 42,61
		                               {"oNrb6voHvP08GliX03tdpMQXxDFI"}};
	
	public static String[][] AccountArr={{"weixin@achang.com","jgs005@achang.com","weixindy@achang.com"},//dev 33,51,68
		                                {"jgs009@achang.com","jgs008@achang.com","weixindy2@achang.com"},//staging 59,68,73
		                                {"weixin5@achang.com","jgs006@achang.com"},//app 42,61
		                                {"JGS017"}};
	
	public static  String[][] PassArr={{"Admin123","jgs005Admin!@#","Admin123"},//dev 33,51,68
		                              {"jgs009Admin!@#","jgs008Admin!@#","Admin123"},//staging 59,68,73
		                              {"Admin123","jgs006Admin!@#"},	//app 42,61
		                              {"jgs017Admin!@#"}};
	
    public static String[][] openidSArr={{"oOuXGtxg1eOzjkfhwpoPP1JA-YGs","oOuXGt2jpzTICKIy0diWk4tEV3Hs","oOuXGt0enB6HixxXP7rfl0_sS_hk","oOuXGt-Th4QIiezt4Zap2RkhvGOM"},//dev33 H.',Candy,T_T,80
    	                                {"oKNc1w93DpFAYKxJb3jPYx0KthX8","oKNc1w0U-rnPA-UehvwUUocv5r1Q","oKNc1w7cD5DbqIHLopUpxtuslgn8","oKNc1w1l3Nhli46VvI6PQKfGKzlY"},//dev 51 H.',Candy
    	                                {"o9rovs_Ff_ZHZ-RaGSL-VjMDKtl0","o9rovs00fekkiLowO5AaMqTAw8IM"},//dev 68 H.',Candy
    	                                {"ohaIcwFFrbnMf2aGyJJKTDdi8clA","ohaIcwErMezrhPd7Rj241W5UwMbw","ohaIcwLhMtWsnsKPlGFlAukXYspM","ohaIcwJKSxmbuCiFFIZVzYPGhkw4"},//staging 59 H.'/Candy
    	                                {"oh_Dsw3xs7jYDMb4c9wRYxrKmQKc","oh_Dsw53x3jfiVugSE-otzo1kkRI","oh_Dsw_3G1PG0g2osoUPe6Y2qWBg","oh_Dsw_JBfkoLpNmAGqscLPslIWA"},//staging 68 H.'/Candy
    	                                {"oleSFv5S2ilDxnl-tg3gwBdV3Z0M","oleSFv_TQfBWb7Lu7FpYfXJg0ssU","oleSFv30CAmYulQ9tOw2hrojlIDw","oleSFv7qJ5dEHhFp98Wz9Qh162Fw"},//staging 73 H.'/Candy
    	                                {"oqKzat3N16C1b4QDnXnsGswvGG1s","oqKzat5VhT24MInIF7JwTU2hz2m8","oqKzat27bNiYGPqgHfP7HguqWPyc","oqKzat27bNiYGPqgHfP7HguqWPyc"},//app 42 H.'/Candy
    	                                {"oNrb6voHvP08GliX03tdpMQXxDFI","oNrb6vp2EBq4XWya3S7r-h1sOiks","oNrb6vtnoYrNVU39eQnEyVnsCJik","oNrb6vgAFIp7a683Q524SWCqCFd0"},//app 61 H.'/Candy
                                        {"oL-M5wcIK8ehA2m8_MoydM8GvveE","oL-M5wRaPDto3qeYFOL9ylcWNnRE","oL-M5we3zvHZSbXcwlRzk04yeQMI","oL-M5wSIKcEe5iYKWNojzdp-WC1A"}};
			  
	  static int counter=0;
	  
		
	public static String[][] MenuOpenArr={{"oOuXGtxg1eOzjkfhwpoPP1JA-YGs","oOuXGt2jpzTICKIy0diWk4tEV3Hs","oOuXGt_UcICp1uIt4ZHSS4fIkdpA",
			                     "oOuXGt-vHeq6QOnXXKFLWxDmMfPE","oOuXGt0enB6HixxXP7rfl0_sS_hk","oOuXGt9Imn3-p4pgL9R3VYeuHyyM"},
				                 //dev33 H.',Candy,rainbow,rainbowgy,T_T,minglong
                             {"oKNc1w93DpFAYKxJb3jPYx0KthX8","oKNc1w0U-rnPA-UehvwUUocv5r1Q","oKNc1w5HLjThVNE1B9bDJLw1KW2Q",
			                    "oKNc1w-oY6jxGzvTIo5cK1iE3nsQ","oKNc1w7cD5DbqIHLopUpxtuslgn8","oKNc1w1IN9MzeA0R3xO5xKLWdvTY"},
			                    //dev 51 H.',Candy,rainbow,rainbowgy,T_T,minglong
                             {"o9rovs_Ff_ZHZ-RaGSL-VjMDKtl0","o9rovs00fekkiLowO5AaMqTAw8IM","o9rovs9k-VV3sRRPkiw9WjfrgxOM",
			                    	},//dev 68 H.',Candy,rainbow---------------
                             {"ohaIcwFFrbnMf2aGyJJKTDdi8clA","ohaIcwErMezrhPd7Rj241W5UwMbw","ohaIcwGQFJXHzH7QVm9nt2Pfpfg0",
			                    "ohaIcwCD3J8bn3140HGQwkXNv8pY","ohaIcwLhMtWsnsKPlGFlAukXYspM","ohaIcwI3sm32i1BgYGz3Mk5xUIbU"},
			                    //staging 59 H.',Candy,rainbow,rainbowgy,T_T,minglong
                             {"oh_Dsw3xs7jYDMb4c9wRYxrKmQKc","oh_Dsw53x3jfiVugSE-otzo1kkRI","oh_DswyGc1jf9h8QmPnctXS8pR-c",
			                    "oh_DswwBVNGD5TBN-jEqGzjqQCVk","oh_Dsw_3G1PG0g2osoUPe6Y2qWBg","oh_Dsw7K8qr3_MFRZyhu2NRwH6xs"},
			                    //staging 68 H.',Candy,rainbow,rainbowgy,T_T,minglong
                             {"oleSFv5S2ilDxnl-tg3gwBdV3Z0M","oleSFv_TQfBWb7Lu7FpYfXJg0ssU","oleSFv9PK_nSAp8YUNvEu74Cyssw",
			                     "oleSFv110FVELZ2j4WkomP4NY-Eg","oleSFv30CAmYulQ9tOw2hrojlIDw","oleSFv6LCgINCoicB5pFh4wbb1-4"},
			                     //staging 73 H.',Candy,rainbow,rainbowgy,T_T,minglong----------
                             {"oqKzat3N16C1b4QDnXnsGswvGG1s","oqKzat5VhT24MInIF7JwTU2hz2m8","oqKzat61tixVci2ihxkOOj46bKqU",
			                    "oqKzat8lGOAUkRwpVxUxVFi_R3JM","oqKzat27bNiYGPqgHfP7HguqWPyc","oqKzatx0_KHnUI7HV_jLG-l6ofOw"},
			                    //app 42 H.',Candy,rainbow,rainbowgy,T_T,minglong
                             {"oNrb6voHvP08GliX03tdpMQXxDFI","oNrb6vp2EBq4XWya3S7r-h1sOiks","oNrb6vo-4Y54YeWmS_J5tvSd9JRo",
			                         "oNrb6vvhjvvaTGpHaabXQkyMkHX0","oNrb6vtnoYrNVU39eQnEyVnsCJik","oNrb6vlfHMD62uxLEALtO3eaoDCY"},
			                       //app 61 H.',Candy,rainbow,rainbowgy,T_T,minglong---------
			                         {"oL-M5wcIK8ehA2m8_MoydM8GvveE","oL-M5wRaPDto3qeYFOL9ylcWNnRE","oL-M5wcrt8ktorMyrrBWnrKrQV9s",
			                        	"oL-M5wUq11pOYgKA0IDNozI52s_A","oL-M5we3zvHZSbXcwlRzk04yeQMI","oL-M5wfHbC5GeZCv7Yes4GmE_akA"}};
		                        
	
	public static String[] Nickname={"H.'","Candy","rainbow","rainbowgy","T_T","Minglong"};
		public static int openIdNum=0;
		public static int openIdMax=1;
	
	  //获取请求url上面的验证的值      MD5加密的方法放在TestEncrypt.java
	  public static String[]  getSignature(String mid,String appid,String secret) throws InterruptedException, NoSuchAlgorithmException{
	    	String noncester=TestEncrypt.Encrypt(mid,"MD5");
	    	long time=System.currentTimeMillis()/1000L;
	    	String timestamp=time+"";
	    	String[] param={timestamp,noncester,TestEncrypt.Encrypt(mid,"MD5")};
	    	java.util.Arrays.sort(param);
	    	String str=param[0]+param[1]+param[2];
	    	String signature=TestEncrypt.Encrypt(str,"SHA-1");
	    	String[] arra={timestamp,noncester,signature};
	    	return arra;
	    }
	    
	  //post 
	  public static String[] Post(String urlStr,String Info) {  
		  String line = ""; 
		  String[] outData= new String[10];
		  int i,j;
	        try {  
	            URL url = new URL(urlStr);  
	            URLConnection con = url.openConnection();  
	            con.setDoOutput(true);  
	            //设置请求头部类型    
	         //   con.setRequestProperty("Pragma:", "no-cache");  //这句话在家里的电脑执行不成功 
	            con.setRequestProperty("Cache-Control", "no-cache");  
	            con.setRequestProperty("Content-Type", "text/xml");  
	            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());       
	            out.write(new String(Info.getBytes("UTF-8")));  
	            out.flush();  
	            out.close();  
	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));  
	            for (i=0,j=0,line = br.readLine(); line != null; line = br.readLine(),j++) {             
	            	if(line.contains("</xml><xml>")){
	            	    outData[i]=outData[i]+"</xml>"+"\n";
	            	    ++i;
	            	    outData[i]="<xml>"+"\n";
	            	}else{
	            		if(outData[i]==null)
	            		{
	            		 outData[i]=line+"\n";
	            		}else{
	                    outData[i]=outData[i]+line+"\n";
	            		}
	            	}
	            } 
	            
/*	            for(i=0;i<outData.length;i++){
	            	if(outData[i].isEmpty()){
	            		break;
	            	}else{
	            	String[] outData[i]
	            	}
	            }*/
	        } catch (Exception e) {  
	        	System.out.println("Post:服务器连接失败!");
	            e.printStackTrace();  
	        }  
	        return outData;
	    }  
	  
	  
	    /** 
	     * 向指定URL发送GET方法的请求 
	     * 
	     * @param url 
	     *            发送请求的URL 
	     * @param param 
	     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。 
	     * @return URL所代表远程资源的响应 
	     */  
	   
		public static String sendGet(String requestUrl) {
	        StringBuffer buffer = null;

	        try {
	            // 建立连接
	            URL url = new URL(requestUrl);
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
	            httpUrlConn.setDoInput(true);
	            httpUrlConn.setRequestMethod("GET");

	            // 获取输入流
	            InputStream inputStream = httpUrlConn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	            // 读取返回结果
	            buffer = new StringBuffer();
	            String str = null;
	            while ((str = bufferedReader.readLine()) != null) {
	                buffer.append(str);
	            }

	            // 释放资源
	            bufferedReader.close();
	            inputStreamReader.close();
	            inputStream.close();
	            httpUrlConn.disconnect();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return buffer.toString();
	    }
	    
	    public static String getAccessToken(String appid, String secret) throws JSONException {  
	    	String api_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
			String result="["+sendGet(api_url)+"]";
			JSONArray resultArray=new JSONArray(result);
			JSONObject resultObj=resultArray.optJSONObject(0);	
			String access_token =resultObj.getString("access_token");
			return access_token;

	    }
	    
	    public static int stringNumbers(String str)  
	    {  
	    	
	        if (str.indexOf("xml")==-1)  
	        {  
	            return 0;  
	        }  
	        else if(str.indexOf("xml") != -1)  
	        {  
	            counter++;  
	            stringNumbers(str.substring(str.indexOf("xml")+3));  
	            return counter;  
	        }  
	        return 0;  
	    }  
        /**
	 * 从指定节点开始,递归遍历所有子节点
	 * @author chenleixing
	 */
	public static void getNodes(Element node){
		System.out.println("--------------------");
		
		//当前节点的名称、文本内容和属性
		System.out.println("当前节点名称："+node.getName());//当前节点名称
		System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
		List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
		for(Attribute attr:listAttr){//遍历当前节点的所有属性
			String name=attr.getName();//属性名称
			String value=attr.getValue();//属性的值
			System.out.println("属性名称："+name+"属性值："+value);
		}
		
		//递归遍历当前节点所有的子节点
		List<Element> listElement=node.elements();//所有一级子节点的list
		for(Element e:listElement){//遍历所有一级子节点
			getNodes(e);//递归
		}
	}
//	HttpClient.InterurlArr[p][q]+"/index/id/"+methods.mid+"?signature="+arr[2]+"&timestamp="+arr[0]+"&nonce="+arr[1];
	public static String RequestUrl(String baseurl,String mid,String signature,String timestamp,String nonce){
		String requestUrl;
		requestUrl=baseurl+"/id/"+mid+"?signature="+signature+"&timestamp="+timestamp+"&nonce="+nonce;
		return requestUrl;
	}
	public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException, JSONException, DocumentException {
		// TODO Auto-generated method stub
		//获取token
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		String token=getAccessToken(appidArr[p][q],secretArr[p][q]);
		String[] arr=getSignature(methods.mid,appidArr[p][q],secretArr[p][q]);
		String msg="test";
		String testStr= "<xml>" +
				"<ToUserName><![CDATA["+WechatIDArr[p][q]+"]]></ToUserName>" +
				"<FromUserName><![CDATA[oqKzat70WCCQas2lev8JYN8DosEI]]></FromUserName>"+
				"<CreateTime>"+arr[0]+"</CreateTime>" +
				"<MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA["+msg+"]]></Content>"+
				"<MsgId>6278109033050373709</MsgId>" +
				"</xml>";

		String requesturl = InterurlArr[p][q]+"/index/id/"+methods.mid+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
		 HttpClient test=new HttpClient();
		 String[] resultArr=test.Post(requesturl,testStr);
		 System.out.println(resultArr.length+"  "+resultArr[0]);

/*         String str=resultArr[0];
         StringBuffer str1=null;
		for(int i=0;i<str.length();i++){
			char b=str.charAt(i);
			if(b!='a'){
				 str1.append(b);
			}
				System.out.println("解析后："+str1);
		}*/
/*		 for (int i=0;i<resultArr.length;i++){
			 if(resultArr[i]!=null){
			 System.out.println(resultArr[i]);
			 Document doc = DocumentHelper.parseText(resultArr[i]);
			 Element root = doc.getRootElement();
			 String Content= root.elementText("Content");
			 System.out.println(Content+"\n");
			 }
		 }	*/		
	}

}
