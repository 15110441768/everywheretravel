package com.example.lenovo.everywheretravel.presenter.favorite;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.MyCollectBean;
import com.example.lenovo.everywheretravel.model.favorite.FavoriteModel;
import com.example.lenovo.everywheretravel.view.favorite.FavoriteView;

public class FavoritePresenter extends BasePresenter<FavoriteView> {

    private FavoriteModel favoriteModel;

    @Override
    protected void initModel() {
        favoriteModel = new FavoriteModel();
        mModels.add(favoriteModel);
    }

    public void getData(int page,String token) {
        favoriteModel.getData(page, token, new CallBack<MyCollectBean>() {
            @Override
            public void onSuccess(MyCollectBean myCollectBean) {
                if (myCollectBean.getResult().getCollectedRoutes()!=null){
                    if (view!=null){
                        view.onSuccess(myCollectBean);
                    }
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onFail(string);
                }
            }
        });
    }
}
