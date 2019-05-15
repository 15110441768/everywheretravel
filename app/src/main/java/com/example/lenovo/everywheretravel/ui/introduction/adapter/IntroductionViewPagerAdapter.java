package com.example.lenovo.everywheretravel.ui.introduction.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.everywheretravel.ui.main.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class IntroductionViewPagerAdapter extends PagerAdapter{
    private ArrayList<View> arrayList;
    Context context;

    public IntroductionViewPagerAdapter(ArrayList<View> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = arrayList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = arrayList.get(position);
        container.removeView(view);
    }
}
