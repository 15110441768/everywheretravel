package com.example.lenovo.everywheretravel.presenter.main;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;
import com.example.lenovo.everywheretravel.model.main.WithMDetailsModel;
import com.example.lenovo.everywheretravel.view.main.DynamicStateView;

public class DynamicStatePresenter extends BasePresenter<DynamicStateView> {

    private WithMDetailsModel mWithMDetailsModel;

    @Override
    protected void initModel() {
        mWithMDetailsModel = new WithMDetailsModel();
        mModels.add(mWithMDetailsModel);
    }

    public void getDynamicStateData(int id, int page, String token) {
        mWithMDetailsModel.getWithMDetailsData(id, page, token, new CallBack<WithMDetailsBean>() {
            @Override
            public void onSuccess(WithMDetailsBean withMDetailsBean) {
                if (withMDetailsBean!=null&&withMDetailsBean.getResult().getActivities()!=null){
                    if (view!=null){
                        view.onSuccess(withMDetailsBean);
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
