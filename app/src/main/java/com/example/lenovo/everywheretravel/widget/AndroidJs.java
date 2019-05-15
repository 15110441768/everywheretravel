package com.example.lenovo.everywheretravel.widget;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;

import com.example.lenovo.everywheretravel.base.BaseApp;
import com.example.lenovo.everywheretravel.ui.main.BundlesActivity;
import com.example.lenovo.everywheretravel.ui.main.DetailsPageActivity;

/**
 * @author xts
 *         Created by asus on 2019/5/13.
 *         Js调用Android的桥梁类,
 */

public class AndroidJs extends Object {

    Context mContext;
    Activity mActivity;

    public AndroidJs(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void callAndroid(int id) {
        System.out.println(id);
        BundlesActivity.startAct(mActivity,id);

    }

    @JavascriptInterface
    public void callAndroid(String msg,int id) {
        System.out.println(id);
        DetailsPageActivity.startAct(mContext,id);
    }

}
