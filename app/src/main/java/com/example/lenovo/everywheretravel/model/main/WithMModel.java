package com.example.lenovo.everywheretravel.model.main;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.bean.WithMBean;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class WithMModel extends BaseModel {
    public void getWithMData(int page, String token, final CallBack<WithMBean> callBack) {
        if (!token.isEmpty()){
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.getWithMData(page,token)
                .compose(RxUtils.<WithMBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WithMBean>() {
                    @Override
                    public void onNext(WithMBean withMBean) {
                        callBack.onSuccess(withMBean);
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
        }else {
//            ToastUtil.showShort("暂无数据");
        }
    }

    public void follow(int id, String token, final CallBack<ResponseBody> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.follow(id,token).compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
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

    public void unfollow(int id, String token, final CallBack<ResponseBody> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.unfollow(id,token).compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
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

    // 我的关注界面的数据
    public void getFollowData(int page, String token, final CallBack<WithMBean> callBack) {
        MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
        service.getFollowData(page,token).compose(RxUtils.<WithMBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WithMBean>() {
                    @Override
                    public void onNext(WithMBean withMBean) {
                        callBack.onSuccess(withMBean);
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
