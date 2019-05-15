package com.example.lenovo.everywheretravel.ui.favorite;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.bean.MyCollectBean;
import com.example.lenovo.everywheretravel.presenter.favorite.FavoritePresenter;
import com.example.lenovo.everywheretravel.ui.favorite.adapter.FavoriteAdapter;
import com.example.lenovo.everywheretravel.ui.main.DetailsPageActivity;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.view.favorite.FavoriteView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FavoriteActivity extends BaseActivity<FavoriteView, FavoritePresenter> implements FavoriteView {

    public static String TYPE_FAVORITE="favorite";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    int page=1;
    private ArrayList<MyCollectBean.ResultBean.CollectedRoutesBean> recyclerviewList;
    private String mToken;
    private FavoriteAdapter mFavoriteAdapter;

    @Override
    protected FavoritePresenter initPresenter() {
        return new FavoritePresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void initView() {
        super.initView();

        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");

        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewList = new ArrayList<>();
        mFavoriteAdapter = new FavoriteAdapter(recyclerviewList, this);
        recyclerview.setAdapter(mFavoriteAdapter);

        mFavoriteAdapter.setTransferData(new FavoriteAdapter.TransferData() {
            @Override
            public void details(int id) {
                Intent intent = new Intent(FavoriteActivity.this, DetailsPageActivity.class);
                intent.putExtra(Constants.ID,id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData(page,mToken);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(MyCollectBean myCollectBean) {
        List<MyCollectBean.ResultBean.CollectedRoutesBean> collectedRoutes = myCollectBean.getResult().getCollectedRoutes();

        recyclerviewList.addAll(collectedRoutes);
        mFavoriteAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String string) {

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recyclerviewList.clear();
        initData();
    }
}
