package com.interactive.testcase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestEncrypt {
	//md5,sha-1  加密
	public static String Encrypt(String strSrc,String encName) {
		//parameter strSrc is a string will be encrypted,
		//parameter encName is the algorithm name will be used.
		//encName dafault to "MD5"
		MessageDigest md=null;
		String strDes=null;

		byte[] bt=strSrc.getBytes();
		try {
		if (encName==null||encName.equals("")) {
		encName="MD5";
		}
		md=MessageDigest.getInstance(encName);
		md.update(bt);
		strDes=bytes2Hex(md.digest()); //to HexString
		}
		catch (NoSuchAlgorithmException e) {
		System.out.println("Invalid algorithm.");
		return null;
		}
		return strDes;
		}

		public static String bytes2Hex(byte[]bts) {
		String des="";
		String tmp=null;
		for (int i=0;i<bts.length;i++) {
		tmp=(Integer.toHexString(bts[i] & 0xFF));
		if (tmp.length()==1) {
		des+="0";
		}
		des+=tmp;
		}
		return des;
		}

		
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
