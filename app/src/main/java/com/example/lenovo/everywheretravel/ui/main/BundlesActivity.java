package com.example.lenovo.everywheretravel.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.bean.BundlesContentBean;
import com.example.lenovo.everywheretravel.presenter.main.BundlesPresenter;
import com.example.lenovo.everywheretravel.ui.agreement.AgreementActivity;
import com.example.lenovo.everywheretravel.ui.main.adapter.BundlesAdapter;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.view.main.BundlesView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BundlesActivity extends BaseActivity<BundlesView, BundlesPresenter> implements BundlesView {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int mId;
    private ArrayList<BundlesContentBean.ResultBean.BundlesBean> mRecyclerviewList;
    private BundlesAdapter mBundlesAdapter;
    private String mToken;

    @Override
    protected BundlesPresenter initPresenter() {
        return new BundlesPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_bundles;
    }

    public static void startAct(Activity activity, int id){
        Intent intent = new Intent(activity, BundlesActivity.class);
        intent.putExtra(Constants.ID,id);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void initView() {
        super.initView();

        mId = getIntent().getIntExtra(Constants.ID, 0);
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        recyclerview.setLayoutManager(new LinearLayoutManager(BundlesActivity.this));
        mRecyclerviewList = new ArrayList<>();
        mBundlesAdapter = new BundlesAdapter(mRecyclerviewList, BundlesActivity.this);
        recyclerview.setAdapter(mBundlesAdapter);

        mBundlesAdapter.setTransferData(new BundlesAdapter.TransferData() {
            @Override
            public void details(String contentURL, String title) {
                AgreementActivity.startAct(BundlesActivity.this,contentURL,title);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        showLoading();
        basePresenter.getBundlesData(mToken);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(BundlesContentBean bundlesContentBean) {
        List<BundlesContentBean.ResultBean.BundlesBean> bundles = bundlesContentBean.getResult().getBundles();
        mRecyclerviewList.addAll(bundles);
        mBundlesAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public void onFail(String string) {

    }
}
