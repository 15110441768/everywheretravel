package com.example.lenovo.everywheretravel.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.main.DetailsPagePresenter;
import com.example.lenovo.everywheretravel.bean.DetailsPageBean;
import com.example.lenovo.everywheretravel.ui.agreement.AgreementActivity;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.main.DetailsPageView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsPageActivity extends BaseActivity<DetailsPageView, DetailsPagePresenter> implements DetailsPageView {

    @BindView(R.id.close)
    ImageButton close;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.intro)
    TextView intro;
    @BindView(R.id.cardURL)
    ImageView cardURL;
    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.occupation)
    TextView occupation;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.introduction)
    TextView introduction;
    @BindView(R.id.userPhoto1)
    ImageView userPhoto1;
    @BindView(R.id.userName1)
    TextView userName1;
    @BindView(R.id.createdAt1)
    TextView createdAt1;
    @BindView(R.id.content1)
    TextView content1;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.userPhoto2)
    ImageView userPhoto2;
    @BindView(R.id.userName2)
    TextView userName2;
    @BindView(R.id.createdAt2)
    TextView createdAt2;
    @BindView(R.id.content2)
    TextView content2;
    @BindView(R.id.recyclerview2)
    RecyclerView recyclerview2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.userPhoto3)
    ImageView userPhoto3;
    @BindView(R.id.userName3)
    TextView userName3;
    @BindView(R.id.createdAt3)
    TextView createdAt3;
    @BindView(R.id.content3)
    TextView content3;
    @BindView(R.id.recyclerview3)
    RecyclerView recyclerview3;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.view_all_reviews)
    TextView viewAllReviews;
    @BindView(R.id.share)
    Button share;
    @BindView(R.id.collect)
    Button collect;
    @BindView(R.id.collected)
    Button collected;
    @BindView(R.id.start_line)
    Button startLine;
    @BindView(R.id.preview_the_line)
    Button previewTheLine;
    @BindView(R.id.price)
    Button price;
    @BindView(R.id.ll)
    LinearLayout ll;
    private int mId;
    private String mToken;
    private boolean mIsCollected;
    private Drawable mCollect;
    private Drawable mCollected;
    private String mCardURL;
    private String mIntro;

    @Override
    protected DetailsPagePresenter initPresenter() {
        return new DetailsPagePresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_details_page;
    }

    public static void startAct(Context context, int id) {
        Intent intent = new Intent(context, DetailsPageActivity.class);
        intent.putExtra(Constants.ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        mId = getIntent().getIntExtra(Constants.ID, 0);
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");

        mCollect = getDrawable(R.mipmap.collect_default);
        mCollect.setBounds(20, 0, 0, 0);
        mCollected = getDrawable(R.mipmap.collect_highlight);
        mCollected.setBounds(20, 0, 0, 0);
    }

    @Override
    protected void initData() {
        super.initData();
        showLoading();
        basePresenter.getDetailsPageData(mId, mToken);
    }

    @OnClick({R.id.close, R.id.view_all_reviews, R.id.share, R.id.collect,R.id.collected, R.id.start_line,R.id.preview_the_line, R.id.price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.view_all_reviews:
                // 查看全部评价
                Intent intent = new Intent(DetailsPageActivity.this, AllReviewsActivity.class);
                intent.putExtra(Constants.ID,mId);
                startActivity(intent);
                break;
            case R.id.share:
                // 分享
                basePresenter.share(mCardURL,mIntro);
                break;
            case R.id.collect:
                // 收藏
                basePresenter.collect(mId,mToken);
                collect.setVisibility(View.GONE);
                collected.setVisibility(View.VISIBLE);
                break;
            case R.id.collected:
                // 取消收藏
                basePresenter.cancelCollection(mId,mToken);
                collect.setVisibility(View.VISIBLE);
                collected.setVisibility(View.GONE);
                break;
            case R.id.start_line:

                break;
            case R.id.preview_the_line:
                break;
            case R.id.price:
                break;
        }
    }

    @Override
    public void onSuccess(DetailsPageBean detailsPageBean) {
        RequestOptions requestOptions = new RequestOptions().circleCrop().placeholder(R.drawable.zhanweitu_xianlu_qipao_small);
        DetailsPageBean.ResultBean.RouteBean route = detailsPageBean.getResult().getRoute();
        city.setText(route.getCity());
        title.setText(route.getTitle());
        mIntro = route.getIntro();
        this.intro.setText(mIntro);
        mCardURL = route.getCardURL();
        Glide.with(this).load(mCardURL).into(this.cardURL);
        boolean isPurchased = route.isIsPurchased();
        if (isPurchased){
            startLine.setVisibility(View.VISIBLE);
            previewTheLine.setVisibility(View.GONE);
            price.setVisibility(View.GONE);
        }else {
            startLine.setVisibility(View.GONE);
            previewTheLine.setVisibility(View.VISIBLE);
            price.setVisibility(View.VISIBLE);
            price.setText("¥"+route.getPrice());
        }
        mIsCollected = route.isIsCollected();
        if (mIsCollected){
            collect.setVisibility(View.GONE);
            collected.setVisibility(View.VISIBLE);
        }else {
            collect.setVisibility(View.VISIBLE);
            collected.setVisibility(View.GONE);
        }

        DetailsPageBean.ResultBean.BanmiBean banmi = detailsPageBean.getResult().getBanmi();
        Glide.with(this).load(banmi.getPhoto()).apply(requestOptions).into(photo);
        name.setText(banmi.getName());
        occupation.setText(banmi.getOccupation());
        location.setText(banmi.getLocation());
        introduction.setText(banmi.getIntroduction());

        List<DetailsPageBean.ResultBean.ReviewsBean> reviews = detailsPageBean.getResult().getReviews();
        Glide.with(this).load(reviews.get(0).getUserPhoto()).apply(requestOptions).into(userPhoto1);
        Glide.with(this).load(reviews.get(1).getUserPhoto()).apply(requestOptions).into(userPhoto2);
        Glide.with(this).load(reviews.get(2).getUserPhoto()).apply(requestOptions).into(userPhoto3);
        userName1.setText(reviews.get(0).getUserName());
        userName2.setText(reviews.get(1).getUserName());
        userName3.setText(reviews.get(2).getUserName());
        createdAt1.setText(reviews.get(0).getCreatedAt());
        createdAt2.setText(reviews.get(1).getCreatedAt());
        createdAt3.setText(reviews.get(2).getCreatedAt());
        content1.setText(reviews.get(0).getContent());
        content2.setText(reviews.get(1).getContent());
        content3.setText(reviews.get(2).getContent());
//        recyclerview1
//        recyclerview2
//        recyclerview3

        hideLoading();
    }

    @Override
    public void onFail(String string) {
        ToastUtil.showShort(string);
    }

    @Override
    public void onCancelCollectionSuccess(String string) {
        ToastUtil.showShort(string);
        collect.setVisibility(View.VISIBLE);
        collected.setVisibility(View.GONE);
    }

    @Override
    public void onCancelCollectionFail(String string) {

    }

    @Override
    public void onCollectSuccess(String string) {
        ToastUtil.showShort(string);
        collect.setVisibility(View.GONE);
        collected.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCollectFail(String string) {

    }

    @Override
    public Activity getAct() {
        return DetailsPageActivity.this;
    }
}
