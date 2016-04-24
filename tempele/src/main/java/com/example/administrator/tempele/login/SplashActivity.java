package com.example.administrator.tempele.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.tempele.R;
import com.example.administrator.tempele.main.widget.MainActivity;
import com.example.administrator.tempele.utils.Const;
import com.example.administrator.tempele.utils.LoginHelper;
import com.example.administrator.tempele.utils.SafePreference;
import com.example.administrator.tempele.utils.ViewHelper;

/**
 * Created by 2014wang on 2016/4/24.
 */
public class SplashActivity extends Activity {
    private TextView tvVersion;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        startTime = System.currentTimeMillis();
        AlphaAnimation alpha = new AlphaAnimation(0f, 1.0f);
        alpha.setDuration(2000);
        RelativeLayout splash_bg = (RelativeLayout) findViewById(R.id.splash_bg);
        splash_bg.setAnimation(alpha);//给该View设置动画效果
        // 版本号
        String versionStr = ViewHelper.getVersion(SplashActivity.this);
        tvVersion = (TextView) findViewById(R.id.tv_version);
        tvVersion.setText("版本号:" + versionStr);
        tvVersion.setTextSize(14);
        SafePreference.save(SplashActivity.this, Const.IsUpdate, true);
        if (SafePreference.getBool(SplashActivity.this, Const.IsUpdate)) {
            // 尽量把activty中的其他操作用类封装并完成这些操作，不要在Activty里面去完成这些操作
            // 登录处理
            System.out.println("cooooooooooooooooo");
            LoginHelper.getInstance(this).loginConnection();
        } else {
            //睡两秒直接进入主界面
            System.out.println("iiiiiiiiiiiiiiiiiiii");
            new Thread(new myRunnable()).start();
        }

    }

    @Override
    protected void onDestroy() {
        LoginHelper.getInstance(this).destory();
        super.onDestroy();
    }

    class myRunnable implements Runnable {
        @Override
        public void run() {
            long endtime = System.currentTimeMillis();
            long sleeptime = endtime - startTime;
            if (sleeptime < 2000) {
                SystemClock.sleep(2000 - sleeptime);
            }
/*			try {
                Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
            SplashActivity.this.runOnUiThread(new Runnable() {
                //可以直接在UI线程中执行的
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            });
        }
        //如果在UI线程里面会阻塞UI,黑屏
    }
}
