package com.example.administrator.tempele.utils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.administrator.tempele.R;
import com.example.administrator.tempele.bean.UpdateBean;
import com.example.administrator.tempele.main.widget.MainActivity;


public class LoginHelper {// 单例模式
	private final int UPDATE = 11;// 更新
	private final int ConnectionError = 12;// 连接服务器出错
	private final int ServerError = 13;// 服务器出错
	private final int DownloadError = 14;// 下载出错
	private final int DownloadSuccess = 15;// 下载成功
	private UpdateBean upbean;
	private static LoginHelper login;
	private Activity context;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE:// 更新提示
				updateTipDialog();
				break;
			case ConnectionError:// 服务器连接错误
				Toast.makeText(context, "服务器连接错误", Toast.LENGTH_SHORT).show();
				break;
			case ServerError:// 服务器出错
				Toast.makeText(context, "服务器出错", Toast.LENGTH_SHORT).show();
				break;
			case DownloadError:// 下载出错
				Toast.makeText(context, "下载出错", Toast.LENGTH_SHORT).show();
				break;
			case DownloadSuccess:// 下载成功
				Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
			super.handleMessage(msg);

		}
	};

	private LoginHelper(Activity context) {
		this.context = context;
	}

	public static LoginHelper getInstance(Activity context) {
		if (login == null) {
			synchronized (LoginHelper.class){
				if(login == null){
					login = new LoginHelper(context);
				}
			}
		}
		return login;
	}

	/**
	 * 提示用户升级
	 */
	private void updateTipDialog() {
		Builder builder = new Builder(context);
		builder.setTitle("升级提示");
		builder.setMessage(upbean.getDes());
		builder.setPositiveButton("是", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				updateApk();
			}

		});
		builder.setNegativeButton("否", new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// 也进入主界面
				enterLogin();
			}
		});
		builder.create().show();
	};

	/*
	 * 连接服务器 耗时的操作
	 */
	public void loginConnection() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				connect();
			}
		}).start();
	}

	/*
	 * 下载更新APK
	 */

	private void updateApk() {
		final ProgressDialog pd = new ProgressDialog(context);
		pd.setTitle("正在下载...");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.show();
		new Thread(new Runnable() {
			Message msg = Message.obtain();

			@Override
			public void run() {
				File file = DownloadHelper.getApkFile(upbean.getApkUrl(), pd);
				System.out.println("没消失，你在干嘛？？？？？？？？"
						+ upbean.getApkUrl().toString());
				if (file == null) {
					pd.dismiss();
					// 下载失败
					msg.what = DownloadError;
					handler.sendMessage(msg);
				} else {
					pd.dismiss();
					System.out.println("没安装，你在干嘛" + file.toString());
					// 进行安装
					/**
					 <intent-filter> <action
					 * android:name="android.intent.action.VIEW" /> <category
					 * android:name="android.intent.category.DEFAULT" /> <data
					 * android:scheme="content" /> <data android:scheme="file"
					 * /> <data android:mimeType=
					 * "application/vnd.android.package-archive" />
					 * </intent-filter>
					 */
					Intent intent = new Intent();
					intent.setAction("android.intent.action.VIEW");
					intent.addCategory("android.intent.category.DEFAULT");
					intent.setDataAndType(Uri.fromFile(file),
							"application/vnd.android.package-archive");
					System.out.println("快跳，你在干嘛？？？？？？？？");
					context.startActivity(intent);
					context.finish();
				}
			}
		}).start();
	}

	protected void connect() {
		HttpURLConnection conn = null;
		// 直接在界面上处理UI更新
		String urlstr = Const.APKURL;
		System.out.println("urlstr" + "-->" + urlstr);
		Message message = Message.obtain();
		try {
			URL url = new URL(urlstr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				// 连接成功
				upbean = XmlParserUtil.getUpdateInfo(conn.getInputStream());
				System.out.println("upbean--->" + upbean.getApkUrl());
				if (upbean != null) {
					if (upbean.getVersion().equals(
							ViewHelper.getVersion(context))) {
						// 最新版本无需更新，进入主界面
						enterLogin();
					} else {
						// 旧版本有新版本，弹出提示框
						// 此后台无法更新主界面，发送消息到handler里面去
						message.what = UPDATE;
						handler.sendMessage(message);
					}
				}
			} else {
				// 连接失败
				message.what = ConnectionError;
				handler.sendMessage(message);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			message.what = ServerError;
			handler.sendMessage(message);
		} finally {
			disableConnectionReuseIfNecessary();
			System.out.println("disableConnectionReuseIfNecessary");
		}

	}

	private void disableConnectionReuseIfNecessary() {
		// HTTP connection reuse which was buggy pre-froyo
		if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
			System.setProperty("http.keepAlive", "false");
		}
	}

	/*
	 * 进入主界面
	 */
	private void enterLogin() {
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
		context.finish();
	}

	public void destory() {
		login = null;
	}

}
