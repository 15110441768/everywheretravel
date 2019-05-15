package com.example.lenovo.everywheretravel.model.main;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.bean.MainContentBean;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel {

    public void getHomeData(int page, String token,final CallBack<MainContentBean> callBack) {

            MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
            service.getHomeData(page,token).compose(RxUtils.<MainContentBean>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<MainContentBean>() {
                        @Override
                        public void onNext(MainContentBean mainContentBean) {
                            if (mainContentBean!=null){
                                callBack.onSuccess(mainContentBean);
                            }
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
