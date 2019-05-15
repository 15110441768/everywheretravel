package com.example.lenovo.everywheretravel.net;

import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.bean.AllReviewsBean;
import com.example.lenovo.everywheretravel.bean.BundlesContentBean;
import com.example.lenovo.everywheretravel.bean.CircuitBean;
import com.example.lenovo.everywheretravel.bean.MyCollectBean;
import com.example.lenovo.everywheretravel.bean.VerifyCodeBean;
import com.example.lenovo.everywheretravel.bean.LoginInfo;
import com.example.lenovo.everywheretravel.bean.DetailsPageBean;
import com.example.lenovo.everywheretravel.bean.MainContentBean;
import com.example.lenovo.everywheretravel.bean.PersonalInfoBean;
import com.example.lenovo.everywheretravel.bean.WithMBean;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;
import com.example.lenovo.everywheretravel.utils.SpUtil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyService {

    String token= (String) SpUtil.getParam(Constants.TOKEN,"");

    int VERIFY_CODE =200;
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
    Observable<MainContentBean> getHomeData(@Query("page") int page, @Header("banmi-app-token") String token);

    @GET("banmi?")
    Observable<WithMBean> getWithMData(@Query("page") int page, @Header("banmi-app-token") String token);

    @POST("account/updateInfo")
    @FormUrlEncoded
    Observable<ResponseBody> upDateInfo(@Field("userName") String userName,
                                        @Field("description") String description,
                                        @Field("gender") String gender,
                                        @Field("photo") String photo,
                                        @Header("banmi-app-token") String token);

    @GET("account/info")
    Observable<PersonalInfoBean> getPersonalInfo(@Header("banmi-app-token") String token);

    // 首页的详情页
    @GET("content/routes/{id}")
    Observable<DetailsPageBean> getDetailsPageData(@Path("id") int id, @Header("banmi-app-token") String token);

    // 首页详情页 取消收藏
    @POST("content/routes/{id}/dislike")
    Observable<ResponseBody> cancelCollection(@Path("id") int id, @Header("banmi-app-token") String token);

    // 首页详情页 收藏
    @POST("content/routes/{id}/like")
    Observable<ResponseBody> collect(@Path("id") int id, @Header("banmi-app-token") String token);

    // 伴米 关注
    @POST("banmi/{id}/follow")
    Observable<ResponseBody> follow(@Path("id") int id, @Header("banmi-app-token") String token);

    // 伴米 取消关注
    @POST("banmi/{id}/unfollow")
    Observable<ResponseBody> unfollow(@Path("id") int id, @Header("banmi-app-token") String token);

    // 我的关注
    @GET("account/followedBanmi?")
    Observable<WithMBean> getFollowData(@Query("page") int page, @Header("banmi-app-token") String token);

    // 我的收藏
    @GET("account/collectedRoutes?")
    Observable<MyCollectBean> getCollectData(@Query("page") int page, @Header("banmi-app-token") String token);

    // 伴米动态
    @GET("banmi/{id}?")
    Observable<WithMDetailsBean> getWithMDetailsData(@Path("id") int id,@Query("page") int page,@Header("banmi-app-token") String token);

    // 伴米详情线路
    @GET("banmi/{id}/routes?page=1")
    Observable<CircuitBean> getCircuitData(@Path("id") int id, @Query("page") int page, @Header("banmi-app-token") String token);

    // 线路详情--全部评价
    @GET("content/routes/{id}/reviews?")
    Observable<AllReviewsBean> getAllReviewsData(@Path("id") int id, @Query("page") int page, @Header("banmi-app-token") String token);

    // 获取专题列表
    @GET("content/bundles")
    Observable<BundlesContentBean> getBundlesData(@Header("banmi-app-token") String token);
}
