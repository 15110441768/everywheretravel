package com.example.lenovo.everywheretravel.ui.login.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseApp;
import com.example.lenovo.everywheretravel.base.BaseFragment;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.ui.MainActivity;
import com.example.lenovo.everywheretravel.ui.login.LoginActivity;
import com.example.lenovo.everywheretravel.bean.VerifyCodeBean;
import com.example.lenovo.everywheretravel.presenter.login.VerifyCodeNewPresenter;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.login.VerifyCodeNewView;
import com.example.lenovo.everywheretravel.widget.IdentifyingCodeView;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyCodeNewFragment extends BaseFragment<VerifyCodeNewView, VerifyCodeNewPresenter> implements VerifyCodeNewView {

    private static final String TAG = "VerifyCodeNewFragment";

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.count_down)
    TextView countDown;
    @BindView(R.id.icv)
    IdentifyingCodeView icv;
    @BindView(R.id.tv)
    TextView tv;
    int mTime;
    private String mCode;
    private LoginFragment loginFragment;
    //    int time=20;
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what==1){
//                if (time<=0){
//                    countDown.setText("重新发送");
//                    countDown.setClickable(true);
//                    countDown.setTextColor(BaseApp.getRes().getColor(R.color.c_fa6a13));
//                }else {
//                    time--;
//                    countDown.setText("重新发送（"+time+"s）");
//                    countDown.setClickable(false);
//                    countDown.setTextColor(BaseApp.getRes().getColor(R.color.c_999999));
//                    handler.sendEmptyMessageDelayed(1,1000);
//                }
//            }
//        }
//    };

    public VerifyCodeNewFragment() {
        // Required empty public constructor
    }

    public static VerifyCodeNewFragment newInstance(String code){
        VerifyCodeNewFragment verifyCodeNewFragment = new VerifyCodeNewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE,code);
        verifyCodeNewFragment.setArguments(bundle);
        return verifyCodeNewFragment;
    }

    @Override
    protected VerifyCodeNewPresenter initPresenter() {
        return new VerifyCodeNewPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_verify_code_new;
    }

    @Override
    protected void initView() {
        super.initView();

        loginFragment = (LoginFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);

        Bundle bundle = getArguments();
        mCode = bundle.getString(Constants.VERIFY_CODE, "");
        setData(mCode);
    }

    @Override
    protected void initData() {
        super.initData();
//        basePresenter.getVerifyCode();
    }

    @Override
    protected void initListener() {
        super.initListener();
        icv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
    }

    private void autoLogin() {
        if (icv.getTextContent().length()>=4){
            String textContent = icv.getTextContent();
            Log.e(TAG, "autoLogin: " + textContent+"--------"+mCode);
            if (textContent.equals(mCode)){
                //自动登录
                icv.setBackgroundEnter(false);
                tv.setText(BaseApp.getRes().getString(R.string.wait_please));
                ToastUtil.showShort(getResources().getString(R.string.please_login_weibo));

//                MainActivity.starAct(getActivity());
//                getActivity().finish();
//                SpUtil.setParam(Constants.AVATAR_HD,"");
//                SpUtil.setParam(Constants.USER_NAME,getString(R.string.tourist));
//                SpUtil.setParam(Constants.PERSONALIZED_SIGNATURE,"");
//                SpUtil.setParam(Constants.GENDER,"");
//                SpUtil.setParam(Constants.TOKEN,"UUuZfXrdIalicHX5NCNBPzCE1nDICM8JOGQYfUAcGnRs6DiI8WQEsrLUqQyoH8IErxnCmAfBeCaUDnSXODQVJZtEvRrR4BBo859g4DJRdfe9sT89KkCZ7S72IKrbNP6OxlA");
            }else {
                ToastUtil.showShort(getString(R.string.code_error));
            }

        }
    }

    @OnClick({R.id.back, R.id.count_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                pop();
                break;
            case R.id.count_down:
                if (mTime<=0){
                    countDown.setClickable(true);
                    basePresenter.getVerifyCode();

                    loginFragment.startCountDown();

                }
                break;
        }
    }

    /**
     * 碎片手动弹栈
     */
    private void pop() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        //获取回退栈中碎片的数量
        /*int backStackEntryCount = manager.getBackStackEntryCount();
        Logger.println("回退栈Fragmnet数量:"+backStackEntryCount);*/
        //弹栈
        manager.popBackStack();
    }

    @Override
    public void onSuccess(VerifyCodeBean verifyCodeBean) {
        String data = verifyCodeBean.getData();
        tv.setText(BaseApp.getRes().getString(R.string.verify_code)+data);
        loginFragment.setCode(data);
    }

    @Override
    public void onFail(String string) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    public void setCountDownTime(int time) {
        mTime=time;
        if (countDown!=null){
            if (time<=0){
                countDown.setClickable(true);
                countDown.setTextColor(BaseApp.getRes().getColor(R.color.c_fa6a13));
                countDown.setText(getResources().getString(R.string.count_down));
            }else {
                countDown.setClickable(false);
                countDown.setText(getResources().getString(R.string.count_down)+"("+time+"s)");
                countDown.setTextColor(BaseApp.getRes().getColor(R.color.c_999999));
            }
        }
    }

    public void setData(String code) {
        if (!TextUtils.isEmpty(code)&&tv!=null){
            tv.setText(getResources().getString(R.string.verify_code)+code);
            mCode=code;
        }
    }
}
