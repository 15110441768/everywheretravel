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
        fragments.add(loginFragment);
        fragments.add(new VerifyCodeFragment());
        addLoginFragment();
        loginFragment.setSendState(new LoginFragment.SendState() {
            @Override
            public void send(boolean b) {
                if (b){
                    Fragment fragment = fragments.get(1);
                    FragmentTransaction transaction = manager.beginTransaction();
                    if (!fragment.isAdded()){
                        transaction.add(R.id.frame,fragment);
                    }
                    transaction.hide(fragments.get(0));
                    transaction.show(fragments.get(1));
                    transaction.commit();
                }
            }
        });
    }

    private void addLoginFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, fragments.get(0));
        transaction.commit();
    }
}
