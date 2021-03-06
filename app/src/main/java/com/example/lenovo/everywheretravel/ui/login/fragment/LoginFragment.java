package com.example.lenovo.everywheretravel.ui.login.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseFragment;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.login.LoginFraPresenter;
import com.example.lenovo.everywheretravel.ui.MainActivity;
import com.example.lenovo.everywheretravel.ui.agreement.AgreementActivity;
import com.example.lenovo.everywheretravel.ui.login.LoginActivity;
import com.example.lenovo.everywheretravel.bean.VerifyCodeBean;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.utils.Tools;
import com.example.lenovo.everywheretravel.view.login.LoginFraView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<LoginFraView, LoginFraPresenter> implements LoginFraView {

    // 5cca9b3b3fc1955c150003b6
    // 微博签名 aaeb46bbf9c52b43962510827f18627e

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
    @BindView(R.id.user_agreement)
    TextView userAgreement;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.ll_three)
    LinearLayout llThree;
    private EditText phone_number;
    private int type;
    private static int TIME = 20;
    private int time = TIME;
    String mCode;
    private Handler mHandler;
    private VerifyCodeNewFragment verifyCodeNewFragment;

    public LoginFragment() {
        // Required empty public constructor
    }

    public void setCode(String code) {
        mCode = code;
    }

    public static LoginFragment newInstance(int type) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE, type);
        loginFragment.setArguments(bundle);
        return loginFragment;
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
        // 设置 EditText 中 hint 的字体大小
        SpannableString spannableString = new SpannableString("请输入手机号");
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(16, true);
        spannableString.setSpan(sizeSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        phoneNumber.setHint(spannableString);

        getArgumentsData();

        setAgreement();

        // 登录和绑定手机号用同一个fragmnet
        // 所以显示或隐藏一些view
        showOrHideView();

    }

    private void showOrHideView() {
        if (type == LoginActivity.TYPE_LOGIN) {
            // 登录
            back.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.VISIBLE);
            llThree.setVisibility(View.VISIBLE);
        } else {
            // 绑定
            back.setVisibility(View.VISIBLE);
            ll.setVisibility(View.INVISIBLE);
            llThree.setVisibility(View.INVISIBLE);
        }
    }

    private void getArgumentsData() {
        Bundle arguments = getArguments();
        type = arguments.getInt(Constants.TYPE);
    }

    @Override
    protected void initListener() {
        super.initListener();
        // 监听EditText中的内容，符合手机号就改变“发送验证码”的背景图片
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = s.toString();
                if (string.matches("[1][34578][0-9]{9}")) {
                    sendCode.setBackgroundResource(R.drawable.shape_send_code_bright);
                } else {
                    sendCode.setBackgroundResource(R.drawable.shape_send_code_dark);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setAgreement() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.useragreement));
        //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //跳转页面,webview展示协议
                //webView有很多坑,所以我们不直接用webView
                AgreementActivity.startAct(getActivity(), "https://api.banmi.com/app2017/agreement.html", "");
            }
        };
        spannableStringBuilder.setSpan(clickableSpan, 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableStringBuilder.setSpan(underlineSpan, 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //前景色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        spannableStringBuilder.setSpan(foregroundColorSpan, 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //需要设置这个ClickableSpan才会有效果
        userAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        userAgreement.setText(spannableStringBuilder);
    }

    @OnClick({R.id.phone_number, R.id.send_code, R.id.wechat, R.id.qq, R.id.sina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                getVerifyCode();
                submit();
                break;
            case R.id.wechat:
                basePresenter.login(SHARE_MEDIA.WEIXIN);
                showLoading();
                break;
            case R.id.qq:
                basePresenter.login(SHARE_MEDIA.QQ);
                break;
            case R.id.sina:
                basePresenter.login(SHARE_MEDIA.SINA);
                break;
        }
    }

    private void getVerifyCode() {
        if (time > 0 && time < TIME) {
            return;
        }
        mCode = "";
        basePresenter.getVerifyCode();
    }

    private void submit() {
        String phonenumber = phoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            ToastUtil.showShort("不能为空");
            return;
        }
        if (phonenumber.matches("[1][34578][0-9]{9}")) {
//            if (sendState != null) {
//                sendState.send(true);
//            }
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
//            VerifyCodeFragment verifyCodeFragment = new VerifyCodeFragment();
//            transaction.add(R.id.frame,verifyCodeFragment);
            verifyCodeNewFragment = VerifyCodeNewFragment.newInstance(mCode);
            if (time > 0 && time < TIME) {

            } else {
                startCountDown();
            }
            transaction.add(R.id.frame, verifyCodeNewFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            // 关闭软键盘
            Tools.closeKeyBoard(getActivity());


        } else {
            ToastUtil.showShort("手机号不正确");
        }
    }

    public void startCountDown() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 当倒计时成负数时停止倒计时并把时间初始化
                if (time <= 0) {
                    time = TIME;
                    return;
                }
                time--;
                if (verifyCodeNewFragment != null) {
                    verifyCodeNewFragment.setCountDownTime(time);
                }
                startCountDown();
            }
        }, 1000);
    }


    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void toastShort(String string) {

    }

    @Override
    public void goToMainActivity() {
        MainActivity.starAct(getActivity());
        getActivity().finish();
        hideLoading();
    }

    @Override
    public void onSuccess(VerifyCodeBean verifyCodeBean) {
        String code = verifyCodeBean.getData();
        mCode = code;
        if (verifyCodeNewFragment != null) {
            verifyCodeNewFragment.setData(code);
        }
    }

    @Override
    public void onFail(String string) {

    }

//    SendState sendState;
//
//    // 跳转到 VerifyCodeFragment （输入验证码界面）
//    public void setSendState(SendState sendState) {
//        this.sendState = sendState;
//    }

//    public interface SendState {
//        void send(boolean b);
//    }
}
