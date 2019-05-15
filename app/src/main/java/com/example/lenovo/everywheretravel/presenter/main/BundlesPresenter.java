package com.example.lenovo.everywheretravel.presenter.main;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.BundlesContentBean;
import com.example.lenovo.everywheretravel.model.main.BundlesModel;
import com.example.lenovo.everywheretravel.view.main.BundlesView;

public class BundlesPresenter extends BasePresenter<BundlesView> {

    private BundlesModel mBundlesModel;

    @Override
    protected void initModel() {
        mBundlesModel = new BundlesModel();
        mModels.add(mBundlesModel);
    }

    public void getBundlesData(String token) {
        mBundlesModel.getBundlesData(token, new CallBack<BundlesContentBean>() {
            @Override
            public void onSuccess(BundlesContentBean bundlesContentBean) {
                if (bundlesContentBean!=null&&bundlesContentBean.getResult().getBundles().size()>0){
                    if (view!=null){
                        view.onSuccess(bundlesContentBean);
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
