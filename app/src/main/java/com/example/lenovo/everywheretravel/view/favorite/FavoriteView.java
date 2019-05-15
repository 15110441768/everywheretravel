package com.example.lenovo.everywheretravel.view.favorite;

import com.example.lenovo.everywheretravel.base.BaseView;
import com.example.lenovo.everywheretravel.bean.MyCollectBean;

public interface FavoriteView extends BaseView {
    void onSuccess(MyCollectBean myCollectBean);
    void onFail(String string);
}
