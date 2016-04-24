package com.example.administrator.tempele.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Xml;


/**
 * TODO : Util.java--工具类，号码是否有效，网络是否连接
 */
public class PhoneValidUtil {

	// 判断电话号码是否有效
	public static boolean isPhoneNumberValid(String phoneNumber) {

		boolean isValid = false;
		String expression = "";
		// CharSequence的值是可读可写序列，而String的值是只读序列
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	
	
	

}
