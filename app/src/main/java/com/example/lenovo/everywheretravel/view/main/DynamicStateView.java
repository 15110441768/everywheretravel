package com.example.lenovo.everywheretravel.view.main;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;

public interface DynamicStateView extends BaseView {
    void onSuccess(WithMDetailsBean withMDetailsBean);
    void onFail(String string);
}
