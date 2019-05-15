package com.example.lenovo.everywheretravel.model.main;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.bean.DetailsPageBean;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class DetailsPageModel extends BaseModel {
    public void getDetailsPageData(int id, String token, final CallBack<DetailsPageBean> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.getDetailsPageData(id,token).compose(RxUtils.<DetailsPageBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DetailsPageBean>() {
                    @Override
                    public void onNext(DetailsPageBean detailsPageBean) {
                        callBack.onSuccess(detailsPageBean);
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

    public void cancelCollection(int id, String token, final CallBack<ResponseBody> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.cancelCollection(id,token).compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        callBack.onSuccess(responseBody);
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

    public void collect(int id, String token, final CallBack<ResponseBody> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.collect(id,token).compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        callBack.onSuccess(responseBody);
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
