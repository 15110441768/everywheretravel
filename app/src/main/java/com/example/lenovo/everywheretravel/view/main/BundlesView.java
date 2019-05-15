package com.example.lenovo.everywheretravel.view.main;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.BundlesContentBean;

public interface BundlesView extends BaseView {
    void onSuccess(BundlesContentBean bundlesContentBean);
    void onFail(String string);
}
