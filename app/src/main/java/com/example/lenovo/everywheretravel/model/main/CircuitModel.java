package com.example.lenovo.everywheretravel.model.main;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.CircuitBean;
import com.example.lenovo.everywheretravel.bean.MainContentBean;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class CircuitModel extends BaseModel {
    public void getCircuitData(int id, int page, String token, final CallBack<CircuitBean> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.getCircuitData(id,page,token).compose(RxUtils.<CircuitBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CircuitBean>() {
                    @Override
                    public void onNext(CircuitBean circuitBean) {
                        callBack.onSuccess(circuitBean);
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
