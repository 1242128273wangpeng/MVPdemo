package com.example.administrator.tempele.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class  ViewHelper {
	public static String getVersion(Context context){
	  PackageManager pm = context.getPackageManager();//得到包的管理器
	  try {
		PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
		System.out.println("version--->"+info.versionName);
		return info.versionName;
	} catch (NameNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
  }
}
