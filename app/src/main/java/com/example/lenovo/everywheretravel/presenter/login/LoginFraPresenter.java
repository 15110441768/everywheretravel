package com.example.lenovo.everywheretravel.presenter.login;

import android.util.Log;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseApp;
import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.model.login.LoginFraModel;
import com.example.lenovo.everywheretravel.ui.login.bean.LoginInfo;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.login.LoginFraView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

public class LoginFraPresenter extends BasePresenter<LoginFraView> {

    private static final String TAG = "LoginFraPresenter";
    private LoginFraModel loginFraModel;

    @Override
    protected void initModel() {
        loginFraModel = new LoginFraModel();
        mModels.add(loginFraModel);
    }

    // 登录
    public void login(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(view.getAct());
        // share_media,三方平台
        // umAuthListener,登录回调
        umShareAPI.getPlatformInfo(view.getAct(), type, umAuthListener);
    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            if (platform==SHARE_MEDIA.SINA){
                loginSina(data.get("uid"));
                Set<Map.Entry<String, String>> set = data.entrySet();
                for (Map.Entry<String, String> entry : set) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Log.e(TAG, "onComplete: " + key + "------" + value);
                }
//                ToastUtil.showShort("成功了");
            }

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtil.showShort("失败：" + t.getMessage());
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtil.showShort("取消了");
        }
    };

    private void loginSina(String uid) {
        loginFraModel.loginSina(uid, new CallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {
                saveUserInfo(loginInfo.getResult());
                if (loginInfo.getResult()!=null){
                    if (view!=null){
                        view.toastShort(BaseApp.getRes().getString(R.string.login_success));
                        view.goToMainActivity();
                    }
                }else {
                    if (view!=null){
                        view.toastShort(BaseApp.getRes().getString(R.string.login_fail));
                    }
                }

            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.toastShort(string);
                }
            }
        });
    }

    /**
     * 保存用户信息
     * @param result
     */
    private void saveUserInfo(LoginInfo.ResultBean result) {
        SpUtil.setParam(Constants.TOKEN,result.getToken());
        SpUtil.setParam(Constants.DESC,result.getDescription());
        SpUtil.setParam(Constants.USERNAME,result.getUserName());
        SpUtil.setParam(Constants.GENDER,result.getGender());
        SpUtil.setParam(Constants.EMAIL,result.getEmail());
        SpUtil.setParam(Constants.PHOTO,result.getPhoto());
        SpUtil.setParam(Constants.PHONE,result.getPhone());
    }
}
