package com.example.lenovo.everywheretravel.model.login;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.ui.login.bean.LoginInfo;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class LoginFraModel extends BaseModel {

    public void loginSina(String uid, final CallBack<LoginInfo> callBack){
        MyService service = HttpUtils.getInstance().getApiserver(MyService.BASE_URL, MyService.class);
        service.postWeiboLogin(uid).compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo!=null){
                            if (loginInfo.getCode() == MyService.SUCCESS_CODE){
                                callBack.onSuccess(loginInfo);
                            }else {
                                callBack.onFail(loginInfo.getDesc());
                            }
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
