package com.example.lenovo.everywheretravel.ui.agreement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.presenter.EmptyPresenter;
import com.example.lenovo.everywheretravel.view.EmptyView;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgreementActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.title)
    TextView mtitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout)
    LinearLayout layout;
    private AgentWeb agentWeb;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_agreement;
    }

    @Override
    protected void initView() {
        super.initView();
        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!agentWeb.back()){
                   finish();
                }
            }
        });

        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))
                // 关闭 AgentWeb 上面的加载进度条，把 useDefaultIndicator() 注释掉，使用 closeIndicator()
//                .useDefaultIndicator()
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");

        // webview 获取网页标题的方法
        /*new WebView(this).setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取网页标题
                super.onReceivedTitle(view, title);
            }
        });*/

        agentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)){
                    mtitle.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, AgreementActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
