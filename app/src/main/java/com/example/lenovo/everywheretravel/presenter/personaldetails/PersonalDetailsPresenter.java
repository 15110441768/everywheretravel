package com.example.lenovo.everywheretravel.presenter.personaldetails;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.model.personaldetails.PersonalDetailsModel;
import com.example.lenovo.everywheretravel.view.personaldetails.PersonalDetailsView;

public class PersonalDetailsPresenter extends BasePresenter<PersonalDetailsView> {

    private PersonalDetailsModel personalDetailsModel;

    @Override
    protected void initModel() {
        personalDetailsModel = new PersonalDetailsModel();
        mModels.add(personalDetailsModel);
    }

    public void upDateInfo(String name,String personalizedSignature, String gender,  String photo, String token) {
        personalDetailsModel.upDateInfo(name, personalizedSignature,gender, photo, token, new CallBack<String>() {
            @Override
            public void onSuccess(String string) {
                if (view!=null){
                    view.onSuccess(string);
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
