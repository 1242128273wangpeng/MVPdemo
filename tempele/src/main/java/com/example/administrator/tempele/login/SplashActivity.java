package com.example.administrator.tempele.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
        alpha.setDuration(3000);
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
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            },3000);
        } else {
            //睡两秒直接进入主界面
            System.out.println("SafePreference.getBool(SplashActivity.this, Const.IsUpdate)"+SafePreference.getBool(SplashActivity.this, Const.IsUpdate));
        }

    }

    @Override
    protected void onDestroy() {
        LoginHelper.getInstance(this).destory();
        super.onDestroy();
    }

}
