package com.example.lenovo.everywheretravel.view.login;

import android.app.Activity;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.ui.login.bean.LoginInfo;

public interface LoginFraView extends BaseView{

    Activity getAct();

    void toastShort(String string);

    void goToMainActivity();
}
