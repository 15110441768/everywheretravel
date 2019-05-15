package com.example.lenovo.everywheretravel.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.presenter.MainPresenter;
import com.example.lenovo.everywheretravel.ui.inform.InformActivity;
import com.example.lenovo.everywheretravel.ui.information.InformationActivity;
import com.example.lenovo.everywheretravel.ui.login.LoginActivity;
import com.example.lenovo.everywheretravel.ui.main.adapter.ViewPagerAdapter;
import com.example.lenovo.everywheretravel.ui.main.fragment.HomeFragment;
import com.example.lenovo.everywheretravel.ui.main.fragment.WithMFragment;
import com.example.lenovo.everywheretravel.view.MainView;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
// master 主干
public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
    @BindView(R.id.head_portrait)
    ImageButton headPortrait;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.navigationview)
    NavigationView navigationview;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> viewpagerList;
    private ViewPagerAdapter viewPagerAdapter;


//    @BindView(R.id.btn)
//    Button btn;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        // 权限
        initPermission();

        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(MainActivity.this).load("https://ww1.sinaimg.cn/large/0065oQSqgy1ftwcw4f4a5j30sg10j1g9.jpg").apply(requestOptions).into(headPortrait);

        tablayout.addTab(tablayout.newTab().setText("首页").setIcon(R.drawable.selector_home));
        tablayout.addTab(tablayout.newTab().setText("伴米").setIcon(R.drawable.selector_banmi));

        viewpagerList = new ArrayList<>();
        viewpagerList.add(new HomeFragment());
        viewpagerList.add(new WithMFragment());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), viewpagerList);
        viewpager.setAdapter(viewPagerAdapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }

    private void initPermission() {
        String[] per = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(this, per, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void initData() {
        basePresenter.getVerifyCode();
    }

//    @OnClick(R.id.btn)
//    public void onViewClicked() {
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);
////        showLoading();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.information:
                Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(intent);
                break;
            case R.id.telephone:
                Intent intent1 = new Intent(MainActivity.this, InformActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void starAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.head_portrait)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
