package com.example.lenovo.everywheretravel.ui;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.ui.login.LoginActivity;
import com.example.lenovo.everywheretravel.utils.SpUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 停留2s
        SystemClock.sleep(2000);

        String token = (String) SpUtil.getParam(Constants.TOKEN, "");

        // 判断有没有保存过，如果保存过就直接进入登录页面
        Boolean b = (Boolean) SpUtil.getParam(Constants.INTRODUCTION, false);

        // 判断之前有没有登陆过，登陆过直接跳主页面，没有登陆过就再判断有没有进入过引导页，进入过就直接进入登录页面，否则进入引导页
        if (!token.equals("")){
            MainActivity.starAct(SplashActivity.this);
            finish();
        }else if (b){
            LoginActivity.startAct(SplashActivity.this,LoginActivity.TYPE_LOGIN);
            finish();
        }else {
            Intent intent = new Intent(SplashActivity.this, IntroductionActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
