package com.example.lenovo.everywheretravel.model;

import com.example.lenovo.everywheretravel.base.BaseModel;
import com.example.lenovo.everywheretravel.base.BaseObserver;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.net.MyService;
import com.example.lenovo.everywheretravel.bean.PersonalInfoBean;
import com.example.lenovo.everywheretravel.utils.HttpUtils;
import com.example.lenovo.everywheretravel.utils.RxUtils;
import com.example.lenovo.everywheretravel.utils.ToastUtil;

import io.reactivex.disposables.Disposable;

public class MainModel extends BaseModel {


    public void getPersonalDetails(String token, final CallBack<PersonalInfoBean> callBack) {
        if (!token.isEmpty()){
            MyService service = HttpUtils.getInstance().getApiserver(MyService.homeUrl, MyService.class);
            service.getPersonalInfo(token).compose(RxUtils.<PersonalInfoBean>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<PersonalInfoBean>() {
                        @Override
                        public void onNext(PersonalInfoBean personalInfoBean) {
                            callBack.onSuccess(personalInfoBean);
                        }

                        @Override
                        public void error(String msg) {
                            callBack.onFail(msg);
                        }

                        @Override
                        protected void subscribe(Disposable d) {

                        }
                    });
        }else {
            ToastUtil.showShort("暂无数据");
        }

    }
}
