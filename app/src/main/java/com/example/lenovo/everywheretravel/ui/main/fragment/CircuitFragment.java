package com.example.lenovo.everywheretravel.ui.main.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseFragment;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.bean.CircuitBean;
import com.example.lenovo.everywheretravel.bean.MainContentBean;
import com.example.lenovo.everywheretravel.presenter.main.CircuitPresenter;
import com.example.lenovo.everywheretravel.ui.main.adapter.CircuitAdapter;
import com.example.lenovo.everywheretravel.ui.main.adapter.MainRecyclerViewAdapter;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.view.main.CircuitView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircuitFragment extends BaseFragment<CircuitView, CircuitPresenter> implements CircuitView {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int page;
    private String mToken;
    private ArrayList<CircuitBean.ResultBean.RoutesBean> mRecyclerviewList;
    private CircuitAdapter mCircuitAdapter;

    public CircuitFragment() {
        // Required empty public constructor
    }

    private int id;

    @SuppressLint("ValidFragment")
    public CircuitFragment(int id) {
        this.id = id;
    }

    @Override
    protected CircuitPresenter initPresenter() {
        return new CircuitPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_circuit;
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
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
//        recyclerview.setHasFixedSize(true);
//        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerviewList = new ArrayList<>();
        mCircuitAdapter = new CircuitAdapter(mRecyclerviewList, getActivity());
        recyclerview.setAdapter(mCircuitAdapter);
    }



    @Override
    protected void initData() {
        super.initData();
        basePresenter.getCircuitData(id,page,mToken);
    }

    @Override
    public void onSuccess(CircuitBean circuitBean) {
        List<CircuitBean.ResultBean.RoutesBean> routes = circuitBean.getResult().getRoutes();
        mRecyclerviewList.addAll(routes);
        mCircuitAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String string) {

    }
}
