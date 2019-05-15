package com.example.lenovo.everywheretravel.model.main;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.AllReviewsBean;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class AllReviewsModel extends BaseModel {
    public void getAllReviewsData(int id, int page, String token, final CallBack<AllReviewsBean> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.getAllReviewsData(id,page,token).compose(RxUtils.<AllReviewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AllReviewsBean>() {
                    @Override
                    public void onNext(AllReviewsBean allReviewsBean) {
                        callBack.onSuccess(allReviewsBean);
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
