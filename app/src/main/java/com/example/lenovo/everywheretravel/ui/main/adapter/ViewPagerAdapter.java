package com.example.lenovo.everywheretravel.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> viewpagerList;
    public ViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> viewpagerList) {
        super(fm);
        this.viewpagerList=viewpagerList;
    }

    @Override
    public Fragment getItem(int i) {
        return viewpagerList.get(i);
    }

    @Override
    public int getCount() {
        return viewpagerList.size();
    }
}
