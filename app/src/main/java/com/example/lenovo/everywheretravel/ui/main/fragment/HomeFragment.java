package com.example.lenovo.everywheretravel.ui.main.fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseFragment;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.main.HomePresenter;
import com.example.lenovo.everywheretravel.ui.agreement.AgreementActivity;
import com.example.lenovo.everywheretravel.ui.main.DetailsPageActivity;
import com.example.lenovo.everywheretravel.ui.main.adapter.MainRecyclerViewAdapter;
import com.example.lenovo.everywheretravel.bean.MainContentBean;
import com.example.lenovo.everywheretravel.view.main.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<MainContentBean.ResultBean.BannersBean> bannerList;
    private ArrayList<MainContentBean.ResultBean.RoutesBean> contentList;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    int page=1;

    public HomeFragment() {
        // Required empty public constructor
    }

    String token;

    @SuppressLint("ValidFragment")
    public HomeFragment(String token) {
        this.token = token;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        bannerList = new ArrayList<>();
        contentList = new ArrayList<>();
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(bannerList, contentList, getActivity());
        recyclerview.setAdapter(mainRecyclerViewAdapter);

        mainRecyclerViewAdapter.setTransferData(new MainRecyclerViewAdapter.TransferData() {
            @Override
            public void details(int id) {
                DetailsPageActivity.startAct(getActivity(),id);
            }

            @Override
            public void details(String contentURL,String title) {
                AgreementActivity.startAct(getActivity(),contentURL,title);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getHomeData(page,token);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(MainContentBean mainContentBean) {
        List<MainContentBean.ResultBean.BannersBean> banners = mainContentBean.getResult().getBanners();
        List<MainContentBean.ResultBean.RoutesBean> routes = mainContentBean.getResult().getRoutes();
        bannerList.addAll(banners);
        contentList.addAll(routes);
        mainRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String string) {
        Log.e(TAG, "onFail: " + string);
    }
}
