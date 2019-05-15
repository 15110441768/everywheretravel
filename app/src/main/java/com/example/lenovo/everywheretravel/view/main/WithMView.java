package com.example.lenovo.everywheretravel.view.main;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.WithMBean;

public interface WithMView extends BaseView {
    void onSuccess(WithMBean withMBean);
    void onFail(String string);

    void onFollowSuccess(String string);
    void onFollowFail(String string);

    void onUnfollowSuccess(String string);
    void onUnfollowFail(String string);


}
