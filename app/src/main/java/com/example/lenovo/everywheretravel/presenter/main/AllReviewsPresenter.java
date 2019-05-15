package com.example.lenovo.everywheretravel.presenter.main;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.AllReviewsBean;
import com.example.lenovo.everywheretravel.model.main.AllReviewsModel;
import com.example.lenovo.everywheretravel.view.main.AllReviewsView;

public class AllReviewsPresenter extends BasePresenter<AllReviewsView> {

    private AllReviewsModel mAllReviewsModel;

    @Override
    protected void initModel() {
        mAllReviewsModel = new AllReviewsModel();
        mModels.add(mAllReviewsModel);
    }

    public void getAllReviewsData(int id, int page, String token) {
        mAllReviewsModel.getAllReviewsData(id, page, token, new CallBack<AllReviewsBean>() {
            @Override
            public void onSuccess(AllReviewsBean allReviewsBean) {
                if (allReviewsBean!=null&&allReviewsBean.getResult().getReviews().size()>0){
                    if (view!=null){
                        view.onSuccess(allReviewsBean);
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
