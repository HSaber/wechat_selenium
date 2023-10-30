package com.hu.testcase;

import java.text.NumberFormat;

public class NumConversion {
	 //获取格式化对象
	   NumberFormat nt = NumberFormat.getPercentInstance();			   
	   NumberFormat nf = NumberFormat.getPercentInstance();
	   static NumberFormat no = NumberFormat.getPercentInstance();
	   //设置百分数精确度2即保留两位小数
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 no.setMinimumFractionDigits(1);
		String num="23.56%";
		float xiaoshu;
		 xiaoshu=Float.parseFloat((num.substring(0, num.indexOf("%"))))/100;

		 System.out.println(no.format(Float.parseFloat((num.substring(0, num.indexOf("%"))))/100));

	}

}
