package com.example.lenovo.everywheretravel.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.MainPresenter;
import com.example.lenovo.everywheretravel.ui.favorite.FavoriteActivity;
import com.example.lenovo.everywheretravel.ui.inform.InformActivity;
import com.example.lenovo.everywheretravel.ui.information.InformationActivity;
import com.example.lenovo.everywheretravel.ui.main.FollowActivity;
import com.example.lenovo.everywheretravel.ui.main.adapter.ViewPagerAdapter;
import com.example.lenovo.everywheretravel.bean.PersonalInfoBean;
import com.example.lenovo.everywheretravel.ui.main.fragment.HomeFragment;
import com.example.lenovo.everywheretravel.ui.main.fragment.WithMFragment;
import com.example.lenovo.everywheretravel.ui.personaldetails.PersonalDetailsActivity;
import com.example.lenovo.everywheretravel.utils.Logger;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.MainView;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
// develop分支
public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, View.OnClickListener {
    private static final String TAG = "MainActivity";

    private static MainActivity mainActivity;
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
    @BindView(R.id.dl)
    DrawerLayout dl;
    private ArrayList<Fragment> viewpagerList;
    private ViewPagerAdapter viewPagerAdapter;
    String mtoken;
    private HomeFragment homeFragment;
    private ImageView navHeadPortrait;
    private TextView userName;
    private RelativeLayout headRl;
    private RelativeLayout coupon;
    private RelativeLayout already_bought;
    private RelativeLayout my_favorite;
    private RelativeLayout my_interest;
    private TextView signature;
    private WithMFragment withMFragment;
    private TextView mBalance;
    private boolean isOpen = false;

//    @BindView(R.id.btn)
//    Button btn;
    String a="a";

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

        // 侧滑菜单
        initNavigationView();

        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        initHeadPortrait();

//        tablayout.addTab(tablayout.newTab().setText(R.string.home).setIcon(R.mipmap.home_unselected));
//        tablayout.addTab(tablayout.newTab().setText(R.string.withm).setIcon(R.mipmap.banmi_unselected));
//
//        tablayout.getTabAt(0).setCustomView(getTabView(getResources().getString(R.string.home),R.mipmap.home_unselected));
//        tablayout.getTabAt(1).setCustomView(getTabView(getResources().getString(R.string.withm),R.mipmap.banmi_unselected));

        tablayout.addTab(tablayout.newTab().setText(R.string.home).setIcon(R.drawable.selector_home));
        tablayout.addTab(tablayout.newTab().setText(R.string.withm).setIcon(R.drawable.selector_banmi));

        tablayout.getTabAt(0).setCustomView(getTabView(getResources().getString(R.string.home),R.drawable.selector_home));
        tablayout.getTabAt(1).setCustomView(getTabView(getResources().getString(R.string.withm),R.drawable.selector_banmi));

//        changeTabSelect(tablayout.getTabAt(0));

        viewpagerList = new ArrayList<>();
        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
        Log.e(TAG, "initView: " + token);

        homeFragment = new HomeFragment(token);
        withMFragment = new WithMFragment();
        viewpagerList.add(homeFragment);
        viewpagerList.add(withMFragment);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), viewpagerList);
        viewpager.setAdapter(viewPagerAdapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
//                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }

    // Tab自定义view
    public View getTabView(String title, int image_src) {
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_item_view, null);
        TextView textView = (TextView) v.findViewById(R.id.textview);
        textView.setText(title);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageview);
        imageView.setImageResource(image_src);
        return v;
    }

    // 切换颜色
    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.imageview);
        TextView txt_title = (TextView) view.findViewById(R.id.textview);
        txt_title.setTextColor(getResources().getColor(R.color.c_fa6a13));
        if (txt_title.getText().toString().equals(getResources().getString(R.string.home))) {
            img_title.setImageResource(R.mipmap.home_highlight);
        } else if (txt_title.getText().toString().equals(getResources().getString(R.string.withm))) {
            img_title.setImageResource(R.mipmap.banmi_highlight);
        }
    }

    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.imageview);
        TextView txt_title = (TextView) view.findViewById(R.id.textview);
        txt_title.setTextColor(getResources().getColor(R.color.c_999999));
        if (txt_title.getText().toString().equals(getResources().getString(R.string.home))) {
            img_title.setImageResource(R.mipmap.home_unselected);
        } else if (txt_title.getText().toString().equals(getResources().getString(R.string.withm))) {
            img_title.setImageResource(R.mipmap.banmi_unselected);
        }
    }

    private void initHeadPortrait() {
        String avatar_hd = (String) SpUtil.getParam(Constants.PHOTO, "");
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.zhanweitu_touxiang)
                .circleCrop();
        Glide.with(MainActivity.this).load(avatar_hd).apply(requestOptions).into(headPortrait);
    }

    private void initNavigationView() {
//        View view = navigationview.getHeaderView(0);
        headRl = findViewById(R.id.head_rl);
        navHeadPortrait = findViewById(R.id.nav_head_portrait);
        userName = findViewById(R.id.name);
        signature = findViewById(R.id.signature);
        mBalance = findViewById(R.id.balance);
        coupon = findViewById(R.id.me_kaquan);
        already_bought = findViewById(R.id.me_yigou);
        my_favorite = findViewById(R.id.me_shoucang);
        my_interest = findViewById(R.id.me_guanzhu);

        // 头像
        String avatrhd = (String) SpUtil.getParam(Constants.PHOTO, "");
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.zhanweitu_touxiang)
                .circleCrop();
        Glide.with(MainActivity.this).load(avatrhd).apply(requestOptions).into(navHeadPortrait);
        // 用户名
        String name = (String) SpUtil.getParam(Constants.USERNAME, "");
        userName.setText(name);
        // 个性签名
        String personalizedSignature = (String) SpUtil.getParam(Constants.DESC, "");
        signature.setText(personalizedSignature);

        // 余额
        String balance = (String) SpUtil.getParam(Constants.BALANCE, "");
        mBalance.setText(balance);

        headRl.setOnClickListener(this);
        navHeadPortrait.setOnClickListener(this);
        userName.setOnClickListener(this);
        coupon.setOnClickListener(this);
        already_bought.setOnClickListener(this);
        my_favorite.setOnClickListener(this);
        my_interest.setOnClickListener(this);
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                isOpen=true;
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                isOpen=false;
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
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
//        basePresenter.getVerifyCode();
        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
        basePresenter.getPersonalDetails(token);
        Logger.println(token);
        showLoading();
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
//            case R.id.telephone:
//                Intent intent1 = new Intent(MainActivity.this, InformActivity.class);
//                startActivity(intent1);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void starAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void getPersonalDetails(){
        if (mainActivity == null){
            mainActivity = new MainActivity();
            mainActivity.initData();
        }
    }

    @OnClick(R.id.head_portrait)
    public void onViewClicked() {
        dl.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_rl:
            case R.id.head_portrait:
            case R.id.name:
            case R.id.signature:
                startActivity(new Intent(MainActivity.this, PersonalDetailsActivity.class));
                break;
            case R.id.me_shoucang:
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
                break;
            case R.id.me_guanzhu:
                startActivity(new Intent(MainActivity.this, FollowActivity.class));
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        initView();
//        basePresenter.getPersonalDetails(mtoken);
        initNavigationView();
        initHeadPortrait();

    }

    @Override
    public void onSuccess(PersonalInfoBean personalInfoBean) {
        SpUtil.setParam(Constants.DESC,personalInfoBean.getResult().getDescription());
        SpUtil.setParam(Constants.USERNAME,personalInfoBean.getResult().getUserName());
        SpUtil.setParam(Constants.GENDER,personalInfoBean.getResult().getGender());
        SpUtil.setParam(Constants.EMAIL,personalInfoBean.getResult().getEmail());
        SpUtil.setParam(Constants.PHOTO,personalInfoBean.getResult().getPhoto());
        SpUtil.setParam(Constants.PHONE,personalInfoBean.getResult().getPhone());
        SpUtil.setParam(Constants.BALANCE,personalInfoBean.getResult().getBalance());
        Logger.println(personalInfoBean.getResult().toString());
        initHeadPortrait();
        initNavigationView();
        hideLoading();
    }

    @Override
    public void onFail(String string) {
        hideLoading();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (isOpen){
                dl.closeDrawer(Gravity.LEFT);
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
