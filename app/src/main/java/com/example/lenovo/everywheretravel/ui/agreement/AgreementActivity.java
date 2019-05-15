package com.example.lenovo.everywheretravel.ui.agreement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.EmptyPresenter;
import com.example.lenovo.everywheretravel.view.EmptyView;
import com.example.lenovo.everywheretravel.widget.AndroidJs;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgreementActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    private static final String TAG = "AgreementActivity";

    @BindView(R.id.title)
    TextView mtitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.webview)
    WebView webview;
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

        String url = getIntent().getStringExtra(Constants.URL);
        String title = getIntent().getStringExtra(Constants.TITLE);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!agentWeb.back()) {
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
                .go(url);

        // webview 获取网页标题的方法
        /*new WebView(this).setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取网页标题
                super.onReceivedTitle(view, title);
            }
        });*/

        if (title.equals("")) {
            agentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    if (!TextUtils.isEmpty(title)) {
                        mtitle.setText(title);
                    }
                    super.onReceivedTitle(view, title);
                }
            });
            webview.setVisibility(View.GONE);
        } else {
            mtitle.setText(title);
            webview.setVisibility(View.VISIBLE);
        }

        WebSettings settings = webview.getSettings();
        //支持js交互
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url+"?os=android");

        webview.addJavascriptInterface(new AndroidJs(AgreementActivity.this,AgreementActivity.this), "android");
    }

    public static void startAct(Context context, String url, String title) {
        Intent intent = new Intent(context, AgreementActivity.class);
        intent.putExtra(Constants.URL, url);
        intent.putExtra(Constants.TITLE, title);
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
