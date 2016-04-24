package com.example.administrator.tempele.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/*
 * 对SharePreferences操作
 */
public class SafePreference {
	/*
	 * 往SharePreferences存储数据 可以存储boolean，和String
	 * 写成Context不只是Activity可以调用，广播接收器等都能能调用，只要传上下文就行
	 */
	public static void save(Context context, String key, Object value) {
		// 拿到SharePreferences,getSharedPreferences参数为命名和模式
		// Mode=0 默认的模式 Context.MODE_PRIVATE：指定该SharedPreferences数据只能被本应用程序读、写。
		// Context.MODE_WORLD_READABLE：指定该SharedPreferences数据能被其他应用程序读，但不能写。
		// Context.MODE_WORLD_WRITEABLE：指定该SharedPreferences数据能被其他应用程序读写。
		SharedPreferences sp = context.getSharedPreferences(Const.PNAME, 0);
		Editor editor = sp.edit();
		// getPreferences和getSharedPreferences的区别:针对getPreferences是Activity，
		// 操作时别的Activity拿不到,也就是说preferences不是全局的，getSharedPreferences是属于全局的。
		if (value instanceof String) {// 判断保存的 数据是什么数据类型
			editor.putString(key, (String) value).commit();
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value).commit();
		}
	}

	public static String getStr(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(Const.PNAME, 0);
		return sp.getString(key, "");
	}
	public static Boolean getBool(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(Const.PNAME, 0);
		return sp.getBoolean(key, false);
	}

}
