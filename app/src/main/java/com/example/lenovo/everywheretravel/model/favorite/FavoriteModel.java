package com.example.lenovo.everywheretravel.model.favorite;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.MyCollectBean;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class FavoriteModel extends BaseModel {
    public void getData(int page, String token, final CallBack<MyCollectBean> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.getCollectData(page,token).compose(RxUtils.<MyCollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyCollectBean>() {
                    @Override
                    public void onNext(MyCollectBean myCollectBean) {
                        callBack.onSuccess(myCollectBean);
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
