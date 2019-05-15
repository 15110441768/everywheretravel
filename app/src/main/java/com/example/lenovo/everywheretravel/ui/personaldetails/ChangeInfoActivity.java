package com.example.lenovo.everywheretravel.ui.personaldetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.personaldetails.ChangeInfoPresenter;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.personaldetails.ChangeInfoView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeInfoActivity extends BaseActivity<ChangeInfoView, ChangeInfoPresenter> implements ChangeInfoView {
    private static final String TAG = "ChangeInfoActivity";

    // 用户昵称
    public static int TYPE_NAME = 0;
    // 个性签名
    public static int TYPE_PERSONALIZED_SIGNATURE = 1;

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.complete)
    TextView complete;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.count)
    TextView mCount;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private int type;
    private Intent intent;

    @Override
    protected ChangeInfoPresenter initPresenter() {
        return new ChangeInfoPresenter();
    }

    public static void startAct(Activity activity, int type, String content) {
        Intent intent = new Intent(activity, ChangeInfoActivity.class);
        intent.putExtra(Constants.TYPE, type);
        intent.putExtra(Constants.CONTENT, content);
        if (type == TYPE_NAME) {
            activity.startActivityForResult(intent, 1);
        } else {
            activity.startActivityForResult(intent, 2);
        }
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_change_info;
    }

    @Override
    protected void initView() {
        super.initView();
        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        intent = getIntent();
        type = intent.getIntExtra(Constants.TYPE, 0);
        String content = intent.getStringExtra(Constants.CONTENT);
        if (type == TYPE_NAME) {
            title.setText(getResources().getString(R.string.change_nickname));
        } else {
            title.setText(getResources().getString(R.string.personalized_signature));
        }
        editText.setText(content);
        mCount.setText(27 - content.length() + "/27");
    }

    @Override
    protected void initListener() {
        super.initListener();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() <= 27) {
                    mCount.setText(27 - s.length() + "/27");
                }
            }
        });
    }

    @OnClick({R.id.back, R.id.complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.complete:
                Intent intent = new Intent();
                String content = editText.getText().toString();
                if (content.isEmpty()) {
                    ToastUtil.showShort("不能为空");
                } else {
                    intent.putExtra(Constants.CONTENT, content);
                    setResult(3, intent);
                    finish();
                }
                break;
        }
    }

    @OnClick(R.id.rl)
    public void onViewClicked() {
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        rl.requestFocus();//setFocus方法无效 //addAddressRemarkInfo is EditText
    }
}
