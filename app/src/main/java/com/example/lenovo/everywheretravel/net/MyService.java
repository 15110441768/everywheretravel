package com.example.lenovo.everywheretravel.net;

import com.example.lenovo.everywheretravel.bean.VerifyCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 获取验证码
     * @return
     */
    @GET("verify")
    Observable<VerifyCodeBean> getVerifyCode();
}
