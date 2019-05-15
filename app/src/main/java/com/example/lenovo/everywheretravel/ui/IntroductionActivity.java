package com.example.lenovo.everywheretravel.ui;

import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.EmptyPresenter;
import com.example.lenovo.everywheretravel.ui.introduction.adapter.IntroductionViewPagerAdapter;
import com.example.lenovo.everywheretravel.ui.login.LoginActivity;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.view.EmptyView;
import com.example.lenovo.everywheretravel.widget.PreviewIndicator;

import java.util.ArrayList;

import butterknife.BindView;

public class IntroductionActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.previewIndicator)
    PreviewIndicator previewIndicator;
    @BindView(R.id.experience)
    Button experience;
    private ArrayList<View> arrayList;
    private IntroductionViewPagerAdapter introductionViewPagerAdapter;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_introduction;
    }

    @Override
    protected void initView() {
        super.initView();


        arrayList = new ArrayList<>();

        initViewOne();

        initViewTwo();

        initViewThree();

        previewIndicator.setNumbers(arrayList.size());



        introductionViewPagerAdapter = new IntroductionViewPagerAdapter(arrayList, IntroductionActivity.this);
        viewpager.setAdapter(introductionViewPagerAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        // viewpager滑动监听
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                previewIndicator.setSelected(i);
                switch (i) {
                    case 2:
                        // 隐藏指示器，显示“立即体验”按钮
                        previewIndicator.setVisibility(View.GONE);
                        experience.setVisibility(View.VISIBLE);
                        break;
                    case 0:
                    case 1:
                        // 隐藏“立即体验”按钮，显示指示器
                        previewIndicator.setVisibility(View.VISIBLE);
                        experience.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // 点击立即体验保存一个状态，当应用下次启动时就不进入引导页面了
        experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.setParam(Constants.INTRODUCTION,true);
                LoginActivity.startAct(IntroductionActivity.this,LoginActivity.TYPE_LOGIN);
                finish();
            }
        });
    }

    private void initViewOne() {
        View view1 = LayoutInflater.from(this).inflate(R.layout.item_introduction_one, null);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.introduction_text1));
        //前景色
        ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(getResources().getColor(R.color.c_78d9ff));
        spannableStringBuilder.setSpan(foregroundColorSpan1, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        spannableStringBuilder.setSpan(foregroundColorSpan2, 6, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView tv1 = view1.findViewById(R.id.tv1);
        tv1.setText(spannableStringBuilder);
        arrayList.add(view1);
    }

    private void initViewTwo() {
        View view2 = LayoutInflater.from(this).inflate(R.layout.item_introduction_two, null);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.introduction_text2));
        //前景色
        ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(getResources().getColor(R.color.c_78d9ff));
        spannableStringBuilder.setSpan(foregroundColorSpan1, 4, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        spannableStringBuilder.setSpan(foregroundColorSpan2, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView tv2 = view2.findViewById(R.id.tv2);
        tv2.setText(spannableStringBuilder);
        arrayList.add(view2);
    }

    private void initViewThree() {
        View view3 = LayoutInflater.from(this).inflate(R.layout.item_introduction_three, null);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.introduction_text3));
        //前景色
        ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(getResources().getColor(R.color.c_78d9ff));
        spannableStringBuilder.setSpan(foregroundColorSpan1, 4, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        spannableStringBuilder.setSpan(foregroundColorSpan2, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView tv3 = view3.findViewById(R.id.tv3);
        tv3.setText(spannableStringBuilder);
        arrayList.add(view3);
    }
}