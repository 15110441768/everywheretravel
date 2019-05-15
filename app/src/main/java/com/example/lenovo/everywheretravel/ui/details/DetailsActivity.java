package com.example.lenovo.everywheretravel.ui.details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.presenter.details.DetailsPresenter;
import com.example.lenovo.everywheretravel.ui.details.adapter.DetailsRecyclerViewAdapter;
import com.example.lenovo.everywheretravel.bean.DetailsBean;
import com.example.lenovo.everywheretravel.view.details.DetailsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity<DetailsView, DetailsPresenter> implements DetailsView {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<DetailsBean> recyclerviewList;
    private DetailsRecyclerViewAdapter detailsRecyclerViewAdapter;

    @Override
    protected DetailsPresenter initPresenter() {
        return new DetailsPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        super.initView();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        recyclerview.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        recyclerviewList = new ArrayList<>();
        recyclerviewList.add(new DetailsBean("2017/10/21", "请问日本的西瓜卡怎么办理？", 1));
        recyclerviewList.add(new DetailsBean("2017/10/21", "在个地铁口都可以买到，有一个西瓜卡的自动售卖机", 2));
        detailsRecyclerViewAdapter = new DetailsRecyclerViewAdapter(recyclerviewList, DetailsActivity.this);
        recyclerview.setAdapter(detailsRecyclerViewAdapter);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
