package com.example.lenovo.everywheretravel.view.main;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;

public interface WithMDetailsView extends BaseView {
    void onSuccess(WithMDetailsBean withMDetailsBean);
    void onFail(String string);

    void onFollowSuccess(String string);
    void onFollowFail(String string);

    void onUnfollowSuccess(String string);
    void onUnfollowFail(String string);
}
