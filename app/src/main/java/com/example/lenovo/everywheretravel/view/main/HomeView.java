package com.example.lenovo.everywheretravel.view.main;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.ui.main.bean.MainContentBean;

public interface HomeView extends BaseView {
    void onSuccess(MainContentBean mainContentBean);
    void onFail(String string);
}
