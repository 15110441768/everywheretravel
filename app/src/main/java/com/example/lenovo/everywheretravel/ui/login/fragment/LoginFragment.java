package com.example.lenovo.everywheretravel.ui.login.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseFragment;
import com.example.lenovo.everywheretravel.presenter.login.LoginFraPresenter;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.login.LoginFraView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<LoginFraView, LoginFraPresenter> implements LoginFraView {

    // 5cca9b3b3fc1955c150003b6

    private static final String TAG = "LoginFragment";

    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.send_code)
    Button sendCode;
    @BindView(R.id.wechat)
    ImageButton wechat;
    @BindView(R.id.qq)
    ImageButton qq;
    @BindView(R.id.sina)
    ImageButton sina;
    private EditText phone_number;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected LoginFraPresenter initPresenter() {
        return new LoginFraPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        super.initView();
        // 监听EditText中的内容，符合手机号就改变“发送验证码”的背景图片
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = s.toString();
                if (string.matches("[1][34578][0-9]{9}")) {
                    sendCode.setBackgroundResource(R.mipmap.button_highlight);
                } else {
                    sendCode.setBackgroundResource(R.mipmap.button_unavailable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.phone_number, R.id.send_code, R.id.wechat,R.id.qq,R.id.sina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                submit();
                break;
            case R.id.wechat:
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.qq:
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.sina:
                login(SHARE_MEDIA.SINA);
                break;
        }
    }

    private void submit() {
        String phonenumber = phoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            ToastUtil.showShort("不能为空");
            return;
        }
        if (phonenumber.matches("[1][34578][0-9]{9}")) {
            if (sendState != null) {
                sendState.send(true);
            }
        } else {
            ToastUtil.showShort("手机号不正确");
        }
    }

    // 登录
    public void login(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(getActivity());
        umShareAPI.getPlatformInfo(getActivity(), type, umAuthListener);
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
            Set<Map.Entry<String, String>> set = data.entrySet();
            for (Map.Entry<String, String> entry : set) {
                String key = entry.getKey();
                String value = entry.getValue();
                Log.e(TAG, "onComplete: " + key + "------" + value);
            }
            ToastUtil.showShort("成功了");
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

    SendState sendState;

    // 跳转到 VerifyCodeFragment （输入验证码界面）
    public void setSendState(SendState sendState) {
        this.sendState = sendState;
    }

    public interface SendState {
        void send(boolean b);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
