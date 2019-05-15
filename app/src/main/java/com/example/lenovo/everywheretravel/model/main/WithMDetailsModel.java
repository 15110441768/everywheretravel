package com.example.lenovo.everywheretravel.model.main;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class WithMDetailsModel extends BaseModel{

    public void getWithMDetailsData(int id, int page, String token, final CallBack<WithMDetailsBean> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.getWithMDetailsData(id,page,token).compose(RxUtils.<WithMDetailsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WithMDetailsBean>() {
                    @Override
                    public void onNext(WithMDetailsBean withMDetailsBean) {
                        callBack.onSuccess(withMDetailsBean);
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
