package com.example.lenovo.everywheretravel.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseView,P extends BasePresenter> extends Fragment implements BaseView{

    private Unbinder unbinder;
    protected P basePresenter;
    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayoutId(), null);

        unbinder = ButterKnife.bind(this, view);
        basePresenter = initPresenter();
        if (basePresenter!=null){
            basePresenter.bind((V) this);
        }
        initView();
        initListener();
        initData();

        return view;
    }

    protected void initView() {

    }

    protected void initListener() {

    }

    protected void initData() {

    }

    protected abstract P initPresenter();

    protected abstract int initLayoutId();

    @Override
    public void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(getContext());
        }
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void toastShort(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        basePresenter.onDestory();
        basePresenter = null;
    }
}
