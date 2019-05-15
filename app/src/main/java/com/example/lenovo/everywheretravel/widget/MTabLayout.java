package com.example.lenovo.everywheretravel.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.utils.SystemUtil;
import com.example.lenovo.everywheretravel.utils.UIUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MTabLayout extends TabLayout {
    public MTabLayout(Context context) {
        super(context);
    }

    public MTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dp10 = SystemUtil.dp2px( 10);
        LinearLayout mTabStrip = (LinearLayout) this.getChildAt(0);
        try {
            Field mTabs = TabLayout.class.getDeclaredField("mTabs");
            mTabs.setAccessible(true);
            ArrayList<Tab> tabs = (ArrayList<Tab>) mTabs.get(this);
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                Tab tab = tabs.get(i);
                Field mView = tab.getClass().getDeclaredField("mView");
                mView.setAccessible(true);
                Object tabView = mView.get(tab);
                Field mTextView = UIUtils.getContext().getClassLoader().loadClass("android.support.design.widget.TabLayout$TabView").getDeclaredField("mTextView");
                mTextView.setAccessible(true);
                TextView textView = (TextView) mTextView.get(tabView);
                float textWidth = textView.getPaint().measureText(textView.getText().toString());
                View child = mTabStrip.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) textWidth, LinearLayout.LayoutParams.MATCH_PARENT);
                params.leftMargin = dp10;
                params.rightMargin = dp10;
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
