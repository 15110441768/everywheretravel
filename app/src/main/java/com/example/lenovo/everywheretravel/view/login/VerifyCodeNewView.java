package com.example.lenovo.everywheretravel.view.login;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.VerifyCodeBean;

public interface VerifyCodeNewView extends BaseView {
    void onSuccess(VerifyCodeBean verifyCodeBean);
    void onFail(String string);
}
