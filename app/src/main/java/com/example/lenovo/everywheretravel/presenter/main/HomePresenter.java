package com.example.lenovo.everywheretravel.presenter.main;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.model.main.HomeModel;
import com.example.lenovo.everywheretravel.ui.main.bean.MainContentBean;
import com.example.lenovo.everywheretravel.view.main.HomeView;

public class HomePresenter extends BasePresenter<HomeView> {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
        mModels.add(homeModel);
    }

    public void getHomeData(int page) {
        homeModel.getHomeData(page, new CallBack<MainContentBean>() {
            @Override
            public void onSuccess(MainContentBean mainContentBean) {
                if (mainContentBean.getResult()!=null){
                    if (view!=null){
                        view.onSuccess(mainContentBean);
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
