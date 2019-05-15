package com.example.lenovo.everywheretravel.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.login.LoginPresenter;
import com.example.lenovo.everywheretravel.ui.MainActivity;
import com.example.lenovo.everywheretravel.ui.login.fragment.LoginFragment;
import com.example.lenovo.everywheretravel.ui.login.fragment.VerifyCodeFragment;
import com.example.lenovo.everywheretravel.ui.main.fragment.HomeFragment;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.login.LoginView;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView{
    public static int TYPE_LOGIN = 0;
    public static int TYPE_BIND = 1;

    @BindView(R.id.frame)
    FrameLayout frame;
    private int type;
    public static String TAG = "loginFragment";
    //    private FragmentManager manager;
//    private ArrayList<Fragment> fragments;
//    private LoginFragment loginFragment;
//    private final int TYPE_LOGIN = 0;
//    private final int TYPE_VERIFYCODE = 1;
//    private VerifyCodeFragment verifyCodeFragment;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }

    public static void startAct(Context context,int type){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
//        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
//        if (!TextUtils.isEmpty(token)){
//            MainActivity.starAct(LoginActivity.this);
//            return;
////            MainActivity.getPersonalDetails();
//        }

//        manager = getSupportFragmentManager();
//
//        fragments = new ArrayList<>();
//        loginFragment = new LoginFragment();
//        verifyCodeFragment = new VerifyCodeFragment();
//        fragments.add(loginFragment);
//        fragments.add(verifyCodeFragment);

        // 一开始就显示登录界面
        addLoginFragment();

        // 登录界面的接口回调，显示验证码界面
//        loginFragment.setSendState(new LoginFragment.SendState() {
//            @Override
//            public void send(boolean b) {
//                if (b){
//                    showHide(TYPE_VERIFYCODE,TYPE_LOGIN);
//                }
//            }
//        });
//
//        verifyCodeFragment.setSendState(new VerifyCodeFragment.SendState() {
//            @Override
//            public void send(boolean b) {
//                if (b){
//                    showHide(TYPE_LOGIN,TYPE_VERIFYCODE);
//                }
//            }
//        });
    }

//    private void showHide(int show,int hide) {
//        Fragment fragment = fragments.get(show);
//        FragmentTransaction transaction = manager.beginTransaction();
//        if (show==TYPE_VERIFYCODE){
//            transaction.addToBackStack(null);
//        }
//        if (!fragment.isAdded()){
//            transaction.add(R.id.frame,fragment);
//        }
//        transaction.hide(fragments.get(hide));
//        transaction.show(fragments.get(show));
//        transaction.commit();
//    }

    private void addLoginFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        getIntentData();
        LoginFragment loginFragment=LoginFragment.newInstance(type);
        transaction.add(R.id.frame, loginFragment,TAG);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void getIntentData() {
        type = getIntent().getIntExtra(Constants.TYPE, TYPE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                ToastUtil.showShort("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();
    }
}
