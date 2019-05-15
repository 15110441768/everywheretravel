package com.example.lenovo.everywheretravel.presenter;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.model.MainModel;
import com.example.lenovo.everywheretravel.bean.PersonalInfoBean;
import com.example.lenovo.everywheretravel.view.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
        mModels.add(mainModel);
    }

    public void getVerifyCode() {

    }

    public void getPersonalDetails(String token) {
        mainModel.getPersonalDetails(token, new CallBack<PersonalInfoBean>() {
            @Override
            public void onSuccess(PersonalInfoBean personalInfoBean) {
                if (personalInfoBean.getResult()!=null){
                    if (view!=null){
                        view.onSuccess(personalInfoBean);
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
