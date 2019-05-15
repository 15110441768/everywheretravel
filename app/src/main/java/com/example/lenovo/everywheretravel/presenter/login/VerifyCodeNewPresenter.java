package com.example.lenovo.everywheretravel.presenter.login;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseApp;
import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.VerifyCodeBean;
import com.example.lenovo.everywheretravel.model.login.VerifyCodeModel;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.utils.Logger;
import com.example.lenovo.everywheretravel.view.login.VerifyCodeNewView;

public class VerifyCodeNewPresenter extends BasePresenter<VerifyCodeNewView> {

    private VerifyCodeModel verifyCodeModel;

    @Override
    protected void initModel() {
        verifyCodeModel = new VerifyCodeModel();
        mModels.add(verifyCodeModel);
    }

    public void getVerifyCode() {
        verifyCodeModel.getVerifyCode(new CallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean verifyCodeBean) {
                if (verifyCodeBean!=null&&verifyCodeBean.getCode()== MyService.VERIFY_CODE){
                    if (view!=null){
                        view.onSuccess(verifyCodeBean);
                        Logger.println(verifyCodeBean.getData());
                    }
                }else {
                    if (view!=null){
                        view.onFail(BaseApp.getRes().getString(R.string.get_verify_fail));
                    }
                }
            }

            @Override
            public void onFail(String string) {
                Logger.println(string);
            }
        });
    }
}
