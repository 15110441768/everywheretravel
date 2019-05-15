package com.example.lenovo.everywheretravel.view.main;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.CircuitBean;
import com.example.lenovo.everywheretravel.bean.MainContentBean;

public interface CircuitView extends BaseView {

    void onSuccess(CircuitBean circuitBean);
    void onFail(String string);
}
