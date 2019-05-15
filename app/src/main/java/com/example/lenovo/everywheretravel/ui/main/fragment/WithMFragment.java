package com.example.lenovo.everywheretravel.ui.main.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseFragment;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.main.WithMPresenter;
import com.example.lenovo.everywheretravel.ui.main.WithMDetailsActivity;
import com.example.lenovo.everywheretravel.ui.main.adapter.WithMRecyclerViewAdapter;
import com.example.lenovo.everywheretravel.bean.BanmiBean;
import com.example.lenovo.everywheretravel.bean.WithMBean;
import com.example.lenovo.everywheretravel.utils.Logger;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.view.main.WithMView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WithMFragment extends BaseFragment<WithMView, WithMPresenter> implements WithMView {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<BanmiBean> recyclerviewList;
    private WithMRecyclerViewAdapter withMRecyclerViewAdapter;
    int page=1;
    private static WithMFragment withMFragment;
    private String mToken;
    private int newPosition;

    public WithMFragment() {
        // Required empty public constructor
    }



    @Override
    protected WithMPresenter initPresenter() {
        return new WithMPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_with_m;
    }

    @Override
    protected void initView() {
        super.initView();
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerviewList = new ArrayList<>();
        withMRecyclerViewAdapter = new WithMRecyclerViewAdapter(recyclerviewList, getActivity());
        recyclerview.setAdapter(withMRecyclerViewAdapter);

        withMRecyclerViewAdapter.setNotifyAdapter(new WithMRecyclerViewAdapter.NotifyAdapter() {
            @Override
            public void follow(int id,int position) {
                basePresenter.follow(id,mToken);
                newPosition=position;
//                recyclerviewList.clear();

            }

            @Override
            public void unfollow(int id,int position) {
                basePresenter.unfollow(id,mToken);
                newPosition=position;
//                recyclerviewList.clear();

            }

            @Override
            public void details(int id) {
                WithMDetailsActivity.starAct(getActivity(),id);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        showLoading();
        basePresenter.getWithMData(page, mToken);
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
        Logger.println(string);
    }

    @Override
    public void onFollowSuccess(String string) {
        toastShort(string);
        withMRecyclerViewAdapter.notifyData(newPosition);
//        initData();
    }

    @Override
    public void onFollowFail(String string) {

    }

    @Override
    public void onUnfollowSuccess(String string) {
        toastShort(string);
        withMRecyclerViewAdapter.notifyData(newPosition);
//        initData();
    }

    @Override
    public void onUnfollowFail(String string) {

    }

    @Override
    public void onStart() {
        super.onStart();
//        recyclerviewList.clear();
//        initData();
    }
}
