package com.example.administrator.tempele.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

public class DownloadHelper {

    public String getSDPath(){  
            File sdDir = null;  
            boolean sdCardExist = Environment.getExternalStorageState()    
                                  .equals(Environment.MEDIA_MOUNTED);  //判断sd卡是否存在
            if  (sdCardExist)    
            {                                   
                sdDir = Environment.getExternalStorageDirectory();//获取跟目录  
              }    
            return sdDir.toString();  
        }

  /*
   * 下载读到SD卡，文件返回
   */
	public static File getApkFile(String url,ProgressDialog pd) {
		System.setProperty("http.keepAlive", "false");
		HttpURLConnection conn=null;
		int last = url.lastIndexOf("/");
		File file = new File(Environment.getExternalStorageDirectory(),
				url.substring(last + 1));
		 System.out.println("file-->path:"+file.toString());
		try {
			URL u = new URL(url);			
			conn = (HttpURLConnection) u.openConnection();
			conn.setReadTimeout(12 * 1000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				int num = 0;
				pd.setMax(conn.getContentLength());
		 		InputStream input = conn.getInputStream();
				FileOutputStream out = new FileOutputStream(file);
				byte buffer[] = new byte[1024];
				int len = 0;
				while ((len = input.read(buffer)) != -1) {
					out.write(buffer, 0, len);
					Thread.sleep(10);
					num+=len;
					pd.setProgress(num);
				}
				System.out.println("下载");
				out.flush();
				out.close();
				input.close();
			} else {
              System.out.println("下载连接出错");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			disableConnectionReuseIfNecessary();
		}
		return file;
	}
	private static void disableConnectionReuseIfNecessary() {
	    // HTTP connection reuse which was buggy pre-froyo
	    if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
	        System.setProperty("http.keepAlive", "false");
	    }
	}
}
