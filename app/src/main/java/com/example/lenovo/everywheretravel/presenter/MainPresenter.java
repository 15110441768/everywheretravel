package com.example.lenovo.everywheretravel.presenter;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.VerifyCodeBean;
import com.example.lenovo.everywheretravel.model.MainModel;
import com.example.lenovo.everywheretravel.view.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
        mModels.add(mainModel);
    }

    public void getVerifyCode() {
        mainModel.getVerifyCode(new CallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean verifyCodeBean) {
                if (view!=null){

                }
            }

            @Override
            public void onFail(String string) {

            }
        });
    }
}
