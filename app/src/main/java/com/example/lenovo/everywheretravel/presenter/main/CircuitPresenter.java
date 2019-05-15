package com.example.lenovo.everywheretravel.presenter.main;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.CircuitBean;
import com.example.lenovo.everywheretravel.bean.MainContentBean;
import com.example.lenovo.everywheretravel.model.main.CircuitModel;
import com.example.lenovo.everywheretravel.view.main.CircuitView;

public class CircuitPresenter extends BasePresenter<CircuitView> {

    private CircuitModel mCircuitModel;

    @Override
    protected void initModel() {
        mCircuitModel = new CircuitModel();
        mModels.add(mCircuitModel);
    }

    public void getCircuitData(int id, int page, String token) {
        mCircuitModel.getCircuitData(id, page, token, new CallBack<CircuitBean>() {
            @Override
            public void onSuccess(CircuitBean circuitBean) {
                if (circuitBean.getResult().getRoutes()!=null){
                    if (view!=null){
                        view.onSuccess(circuitBean);
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
