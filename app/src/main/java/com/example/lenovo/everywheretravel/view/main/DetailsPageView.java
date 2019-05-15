package com.example.lenovo.everywheretravel.view.main;

import android.app.Activity;
import android.content.Context;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.DetailsPageBean;

public interface DetailsPageView extends BaseView {
    void onSuccess(DetailsPageBean detailsPageBean);
    void onFail(String string);

    void onCancelCollectionSuccess(String string);
    void onCancelCollectionFail(String string);

    void onCollectSuccess(String string);
    void onCollectFail(String string);

    Activity getAct();
}
