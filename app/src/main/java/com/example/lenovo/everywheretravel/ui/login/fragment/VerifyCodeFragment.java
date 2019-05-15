package com.example.lenovo.everywheretravel.ui.login.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseFragment;
import com.example.lenovo.everywheretravel.presenter.login.VerifyCodePresenter;
import com.example.lenovo.everywheretravel.view.login.VerifyCodeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyCodeFragment extends BaseFragment<VerifyCodeView, VerifyCodePresenter> implements VerifyCodeView {


    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.count_down)
    TextView countDown;
    @BindView(R.id.edit_first)
    EditText editFirst;
    @BindView(R.id.edit_second)
    EditText editSecond;
    @BindView(R.id.edit_third)
    EditText editThird;
    @BindView(R.id.edit_fourth)
    EditText editFourth;
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_code)
    EditText et_code;
    private List<String> codes = new ArrayList<>();
    private InputMethodManager imm;

    public VerifyCodeFragment() {
        // Required empty public constructor
    }

    @Override
    protected VerifyCodePresenter initPresenter() {
        return new VerifyCodePresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_verify_code;
    }

    @Override
    protected void initView() {
        super.initView();
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        initEvent();
    }

    private void initEvent() {
        //验证码输入
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    et_code.setText("");
                    if (codes.size() < 4) {
                        codes.add(editable.toString());
                        showCode();
                    }
                }
            }
        });
        // 监听验证码删除按键
        et_code.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN && codes.size() > 0) {
                    codes.remove(codes.size() - 1);
                    showCode();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 显示输入的验证码
     */
    private void showCode() {
        String code1 = "";
        String code2 = "";
        String code3 = "";
        String code4 = "";
        if (codes.size() >= 1) {
            code1 = codes.get(0);
        }
        if (codes.size() >= 2) {
            code2 = codes.get(1);
        }
        if (codes.size() >= 3) {
            code3 = codes.get(2);
        }
        if (codes.size() >= 4) {
            code4 = codes.get(3);
            showLoading();
        }
        editFirst.setText(code1);
        editSecond.setText(code2);
        editThird.setText(code3);
        editFourth.setText(code4);
    }

    /**
     * 显示键盘
     */
    public void showSoftInput() {
        //显示软键盘
        if (imm != null && et_code != null) {
            et_code.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imm.showSoftInput(et_code, 0);
                }
            }, 200);
        }
    }

    /**
     * 获得手机号验证码
     *
     * @return 验证码
     */
    public String getPhoneCode() {
        StringBuilder sb = new StringBuilder();
        for (String code : codes) {
            sb.append(code);
        }
        return sb.toString();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.back, R.id.count_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
//                if (sendState!=null){
//                    sendState.send(true);
//                }
                break;
            case R.id.count_down:
                break;
        }
    }

//    SendState sendState;
//
//    // 跳转到 VerifyCodeFragment （输入验证码界面）
//    public void setSendState(SendState sendState) {
//        this.sendState = sendState;
//    }
//
//    public interface SendState {
//        void send(boolean b);
//    }
}
