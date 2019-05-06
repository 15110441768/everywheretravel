package com.example.lenovo.everywheretravel.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.presenter.login.LoginPresenter;
import com.example.lenovo.everywheretravel.ui.login.fragment.LoginFragment;
import com.example.lenovo.everywheretravel.ui.login.fragment.VerifyCodeFragment;
import com.example.lenovo.everywheretravel.view.login.LoginView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> {

    @BindView(R.id.frame)
    FrameLayout frame;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private LoginFragment loginFragment;
    private final int TYPE_LOGIN = 0;
    private final int TYPE_VERIFYCODE = 1;
    private VerifyCodeFragment verifyCodeFragment;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        manager = getSupportFragmentManager();

        fragments = new ArrayList<>();
        loginFragment = new LoginFragment();
        verifyCodeFragment = new VerifyCodeFragment();
        fragments.add(loginFragment);
        fragments.add(verifyCodeFragment);

        // 一开始就显示登录界面
        addLoginFragment();

        // 登录界面的接口回调，显示验证码界面
        loginFragment.setSendState(new LoginFragment.SendState() {
            @Override
            public void send(boolean b) {
                if (b){
                    showHide(TYPE_VERIFYCODE,TYPE_LOGIN);
                }
            }
        });

        verifyCodeFragment.setSendState(new VerifyCodeFragment.SendState() {
            @Override
            public void send(boolean b) {
                if (b){
                    showHide(TYPE_LOGIN,TYPE_VERIFYCODE);
                }
            }
        });
    }

    private void showHide(int show,int hide) {
        Fragment fragment = fragments.get(show);
        FragmentTransaction transaction = manager.beginTransaction();
        if (show==TYPE_VERIFYCODE){
            transaction.addToBackStack(null);
        }
        if (!fragment.isAdded()){
            transaction.add(R.id.frame,fragment);
        }
        transaction.hide(fragments.get(hide));
        transaction.show(fragments.get(show));
        transaction.commit();
    }

    private void addLoginFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, fragments.get(0));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
