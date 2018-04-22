package com.example.administrator.tempele.utils;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.example.administrator.tempele.bean.UpdateBean;


public class XmlParserUtil {
		   
		/*
		 * 传入InputStream解析XML，得到updateinfo
		 */
		public static UpdateBean getUpdateInfo(InputStream inputStream) {
	       XmlPullParser parser = Xml.newPullParser();
	       UpdateBean upbean = new UpdateBean();
	       try {
			parser.setInput(inputStream, "UTF-8");
			int type = parser.getEventType();
			while(type!=XmlPullParser.END_DOCUMENT){
				switch (type) {
				case XmlPullParser.START_TAG:
	               if("version".equals(parser.getName())){
	            	   upbean.setVersion(parser.nextText());
	            	   System.out.println("XMLParser--version"+upbean.getVersion());
	               }else if("description".equals(parser.getName())){
	            	   upbean.setDes(parser.nextText());
	            	   System.out.println("XMLParser--description"+upbean.getDes());
	               }else if("apkurl".equals(parser.getName())){
	            	   upbean.setApkUrl(parser.nextText());
	            	   System.out.println("XMLParser--apkurl"+upbean.getApkUrl());
	               }
					break;

				default:
					break;
				}
				type = parser.next(); 
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return upbean;
		}
	  
	}


