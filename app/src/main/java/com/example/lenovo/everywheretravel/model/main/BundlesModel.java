package com.example.lenovo.everywheretravel.model.main;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.BundlesContentBean;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class BundlesModel extends BaseModel {
    public void getBundlesData(String token, final CallBack<BundlesContentBean> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.getBundlesData(token).compose(RxUtils.<BundlesContentBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BundlesContentBean>() {
                    @Override
                    public void onNext(BundlesContentBean bundlesContentBean) {
                        callBack.onSuccess(bundlesContentBean);
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
