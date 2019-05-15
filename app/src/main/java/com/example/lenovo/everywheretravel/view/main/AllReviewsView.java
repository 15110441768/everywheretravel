package com.example.lenovo.everywheretravel.view.main;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.AllReviewsBean;

public interface AllReviewsView extends BaseView {
    void onSuccess(AllReviewsBean allReviewsBean);
    void onFail(String string);
}
