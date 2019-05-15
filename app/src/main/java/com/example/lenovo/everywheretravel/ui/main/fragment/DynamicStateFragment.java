package com.example.lenovo.everywheretravel.ui.main.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseFragment;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.presenter.main.DynamicStatePresenter;
import com.example.lenovo.everywheretravel.ui.main.adapter.DynamicStateAdapter;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.main.DynamicStateView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicStateFragment extends BaseFragment<DynamicStateView, DynamicStatePresenter> implements DynamicStateView {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.all_state)
    LinearLayout allState;
    private ArrayList<WithMDetailsBean.ResultBean.ActivitiesBean> mRecyclerviewList;
    public DynamicStateAdapter mDynamicStateAdapter;
    int page=1;

    public DynamicStateFragment() {
        // Required empty public constructor
    }

    private int id;

    @SuppressLint("ValidFragment")
    public DynamicStateFragment(int id) {
        this.id = id;
    }

    @Override
    protected DynamicStatePresenter initPresenter() {
        return new DynamicStatePresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_dynamic_state;
    }

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
        @Override
        public boolean canScrollVertically() {
            return false;
        }
    };

    @Override
    protected void initView() {
        super.initView();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerviewList = new ArrayList<>();
        mDynamicStateAdapter = new DynamicStateAdapter(mRecyclerviewList, getActivity());
        recyclerview.setAdapter(mDynamicStateAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        showLoading();
        basePresenter.getDynamicStateData(id,page, MyService.token);
    }

    @OnClick(R.id.all_state)
    public void onViewClicked() {

    }

    @Override
    public void onSuccess(WithMDetailsBean withMDetailsBean) {
        List<WithMDetailsBean.ResultBean.ActivitiesBean> activities = withMDetailsBean.getResult().getActivities();
        mRecyclerviewList.addAll(activities);
        mDynamicStateAdapter.notifyDataSetChanged();
        hideLoading();
        if (mRecyclerviewList.size()==0){
            toastShort("暂时没有动态");
            allState.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFail(String string) {

    }
}
