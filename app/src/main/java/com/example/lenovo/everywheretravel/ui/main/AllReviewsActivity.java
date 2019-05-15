package com.example.lenovo.everywheretravel.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.bean.AllReviewsBean;
import com.example.lenovo.everywheretravel.presenter.main.AllReviewsPresenter;
import com.example.lenovo.everywheretravel.ui.main.adapter.AllReviewsRvAdapter;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.view.main.AllReviewsView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllReviewsActivity extends BaseActivity<AllReviewsView, AllReviewsPresenter> implements AllReviewsView {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<AllReviewsBean.ResultBean.ReviewsBean> mRecyclerviewList;
    private AllReviewsRvAdapter mAllReviewsRvAdapter;
    private String mToken;
    private int mId;
    int page=1;

    @Override
    protected AllReviewsPresenter initPresenter() {
        return new AllReviewsPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_all_reviews;
    }

    @Override
    protected void initView() {
        super.initView();
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mId = getIntent().getIntExtra(Constants.ID, 0);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        recyclerview.setLayoutManager(new LinearLayoutManager(AllReviewsActivity.this));
        mRecyclerviewList = new ArrayList<>();
        mAllReviewsRvAdapter = new AllReviewsRvAdapter(mRecyclerviewList, AllReviewsActivity.this);
        recyclerview.setAdapter(mAllReviewsRvAdapter);

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                mRecyclerviewList.clear();
                initData();

            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getAllReviewsData(mId,page,mToken);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(AllReviewsBean allReviewsBean) {
        List<AllReviewsBean.ResultBean.ReviewsBean> reviews = allReviewsBean.getResult().getReviews();
        mRecyclerviewList.addAll(reviews);
        mAllReviewsRvAdapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void onFail(String string) {

    }
}
