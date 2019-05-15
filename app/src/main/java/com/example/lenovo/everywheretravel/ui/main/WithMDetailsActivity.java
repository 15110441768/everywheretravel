package com.example.lenovo.everywheretravel.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;
import com.example.lenovo.everywheretravel.presenter.main.WithMDetailsPresenter;
import com.example.lenovo.everywheretravel.ui.MainActivity;
import com.example.lenovo.everywheretravel.ui.main.adapter.ViewPagerAdapter;
import com.example.lenovo.everywheretravel.ui.main.fragment.CircuitFragment;
import com.example.lenovo.everywheretravel.ui.main.fragment.DynamicStateFragment;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.view.main.WithMDetailsView;
import com.example.lenovo.everywheretravel.widget.MTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithMDetailsActivity extends BaseActivity<WithMDetailsView, WithMDetailsPresenter> implements WithMDetailsView {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.share)
    ImageButton share;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.follow)
    ImageView follow;
    @BindView(R.id.isFollowed)
    TextView isFollowed;
    @BindView(R.id.following)
    TextView following;
    @BindView(R.id.icon_location)
    ImageView iconLocation;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.icon_occupation)
    ImageView iconOccupation;
    @BindView(R.id.occupation)
    TextView occupation;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.introduction)
    TextView introduction;
    private int mId;
    int page = 1;
    private String mToken;
    private ArrayList<Fragment> mFragments;
    private ViewPagerAdapter mViewPagerAdapter;
    private boolean mIsFollowed;
    private ArrayList<String> titleList;
    private DynamicStateFragment mDynamicStateFragment;

    @Override
    protected WithMDetailsPresenter initPresenter() {
        return new WithMDetailsPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_with_mdetails;
    }

    public static void starAct(Context context,int id) {
        Intent intent = new Intent(context, WithMDetailsActivity.class);
        intent.putExtra(Constants.ID,id);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mId = getIntent().getIntExtra(Constants.ID, 0);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        tablayout.addTab(tablayout.newTab().setText(R.string.dynamic_state));
        tablayout.addTab(tablayout.newTab().setText(R.string.circuit));

//        titleList = new ArrayList<>();
//        titleList.add(getResources().getString(R.string.dynamic_state));
//        titleList.add(getResources().getString(R.string.circuit));

        mFragments = new ArrayList<>();
        mDynamicStateFragment = new DynamicStateFragment(mId);
        mFragments.add(mDynamicStateFragment);
        mFragments.add(new CircuitFragment(mId));
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        viewpager.setAdapter(mViewPagerAdapter);

//        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public int getCount() {
//                return titleList.size();
//            }
//
//            @Override
//            public Fragment getItem(int i) {
//                return mFragments.get(i);
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return position;
//            }
//
//            @Nullable
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return titleList.get(position);
//            }
//        };
//        viewpager.setOffscreenPageLimit(titleList.size());
//        viewpager.setAdapter(adapter);
//        tablayout.setupWithViewPager(viewpager);

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

    @Override
    protected void initData() {
        super.initData();
        showLoading();
        basePresenter.getWithMDetailsData(mId, page, mToken);
    }

    @OnClick({R.id.back, R.id.share, R.id.follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                break;
            case R.id.follow:
                if (mIsFollowed){
                    basePresenter.unfollow(mId,mToken);
                }else {
                    basePresenter.follow(mId,mToken);
                }
                break;
        }
    }

    @Override
    public void onSuccess(WithMDetailsBean withMDetailsBean) {
        WithMDetailsBean.ResultBean.BanmiBean banmi = withMDetailsBean.getResult().getBanmi();
        RoundedCorners roundedCorners = new RoundedCorners(20);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners).placeholder(R.drawable.zhanweitu_touxiang);
        Glide.with(WithMDetailsActivity.this).load(banmi.getPhoto()).apply(requestOptions).into(photo);
        name.setText(banmi.getName());
        mIsFollowed = banmi.isIsFollowed();
        if (mIsFollowed) {
            follow.setImageResource(R.mipmap.follow);
            this.isFollowed.setText(R.string.followed);
        } else {
            follow.setImageResource(R.mipmap.follow_unselected);
            this.isFollowed.setText(R.string.follow);
        }
        following.setText(banmi.getFollowing() + "人关注");
        location.setText(banmi.getLocation());
        occupation.setText(banmi.getOccupation());
        introduction.setText(banmi.getIntroduction());

        hideLoading();
    }

    @Override
    public void onFail(String string) {

    }

    @Override
    public void onFollowSuccess(String string) {
        toastShort(string);
        initData();
    }

    @Override
    public void onFollowFail(String string) {

    }

    @Override
    public void onUnfollowSuccess(String string) {
        toastShort(string);
        initData();
    }

    @Override
    public void onUnfollowFail(String string) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDynamicStateFragment.mDynamicStateAdapter.mMediaPlayer!=null){
            mDynamicStateFragment.mDynamicStateAdapter.mMediaPlayer.release();
        }
    }
}
