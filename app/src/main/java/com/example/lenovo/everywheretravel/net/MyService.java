package com.example.lenovo.everywheretravel.net;

import com.example.lenovo.everywheretravel.bean.VerifyCodeBean;
import com.example.lenovo.everywheretravel.ui.login.bean.LoginInfo;
import com.example.lenovo.everywheretravel.ui.main.bean.MainContentBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 获取验证码
     * @return
     */
    @GET("verify")
    Observable<VerifyCodeBean> getVerifyCode();

    public static final int SUCCESS_CODE = 0;
    public static final int VERIFY_CODE_ERROR = 200502;
    public static final int WECHAT_HAVE_BINDED = 20171102;
    //token 失效
    public static final int TOKEN_EXPIRE = 20170707;
    //余额不足
    public static final int MONEY_NOT_ENOUGH = 200607;
    String BASE_URL = "http://api.banmi.com/";


    /**
     * 微信登录
     *
     * @param unionID
     * @return
     */
    @FormUrlEncoded
    @POST("api/3.0/account/login/wechat")
    Observable<LoginInfo> postWechatLogin(@Field("unionID") String unionID);

    /**
     * 微博登录
     *
     * @param oAuthToken 就是三方里面的uid
     * @return
     */
    @FormUrlEncoded
    @POST("api/3.0/account/login/oauth")
    Observable<LoginInfo> postWeiboLogin(@Field("oAuthToken") String oAuthToken);

    String homeUrl="http://api.banmi.com/api/3.0/";
    @GET("content/routesbundles?")
    @Headers("banmi-app-token:JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g")
    Observable<MainContentBean> getHomeData(@Query("page") int page);
}
