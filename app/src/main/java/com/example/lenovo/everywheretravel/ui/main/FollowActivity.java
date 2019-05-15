package com.example.lenovo.everywheretravel.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.main.WithMPresenter;
import com.example.lenovo.everywheretravel.ui.main.adapter.WithMRecyclerViewAdapter;
import com.example.lenovo.everywheretravel.bean.BanmiBean;
import com.example.lenovo.everywheretravel.bean.WithMBean;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.view.main.WithMView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FollowActivity extends BaseActivity<WithMView, WithMPresenter> implements WithMView {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<BanmiBean> recyclerviewList;
    private WithMRecyclerViewAdapter withMRecyclerViewAdapter;
    int page=1;
    private String mToken;

    @Override
    protected WithMPresenter initPresenter() {
        return new WithMPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initView() {
        super.initView();

        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewList = new ArrayList<>();
        withMRecyclerViewAdapter = new WithMRecyclerViewAdapter(recyclerviewList, this);
        recyclerview.setAdapter(withMRecyclerViewAdapter);

        withMRecyclerViewAdapter.setNotifyAdapter(new WithMRecyclerViewAdapter.NotifyAdapter() {
            @Override
            public void follow(int id, int position) {

            }

            @Override
            public void unfollow(int id, int position) {

                basePresenter.unfollow(id,mToken);
            }

            @Override
            public void details(int id) {
                WithMDetailsActivity.starAct(FollowActivity.this,id);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getFollowData(page,mToken);
        showLoading();
    }

    @Override
    public void onSuccess(WithMBean withMBean) {
        List<BanmiBean> banmi = withMBean.getResult().getBanmi();
        recyclerviewList.addAll(banmi);
        withMRecyclerViewAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public void onFail(String string) {
        toastShort(string);
    }

    @Override
    public void onFollowSuccess(String string) {

    }

    @Override
    public void onFollowFail(String string) {

    }

    @Override
    public void onUnfollowSuccess(String string) {
        toastShort(string);
        recyclerviewList.clear();
        initData();
    }

    @Override
    public void onUnfollowFail(String string) {
        toastShort(string);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
