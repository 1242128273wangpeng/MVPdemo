package com.example.administrator.tempele.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String method(String str) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] data = digest.digest(str.getBytes());
			for (int i = 0; i < data.length; i++) {
				String result = Integer.toHexString(data[i] & 0xff);// 十六进制的与运算，可以查看后八位
				if (result.length() == 1) {
					result = "0" + result;
				}
				sb.append(result);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}
	public static void main(String[] args) {
		System.out.println(MD5.method("1"));
	}
}
