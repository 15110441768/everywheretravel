package com.example.lenovo.everywheretravel.view;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.PersonalInfoBean;

public interface MainView extends BaseView {
    void onSuccess(PersonalInfoBean personalInfoBean);
    void onFail(String string);
}
