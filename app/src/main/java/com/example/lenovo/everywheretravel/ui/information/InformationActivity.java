package com.example.lenovo.everywheretravel.ui.information;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.presenter.information.InformationPresenter;
import com.example.lenovo.everywheretravel.ui.information.adapter.InformationRecyclerViewAdapter;
import com.example.lenovo.everywheretravel.ui.information.bean.InformationBean;
import com.example.lenovo.everywheretravel.view.information.InformationView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationActivity extends BaseActivity<InformationView, InformationPresenter> implements InformationView {
    @BindView(R.id.recyclerview)
    SwipeMenuRecyclerView recyclerview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<InformationBean> recyclerviewList;
    private InformationRecyclerViewAdapter informationRecyclerViewAdapter;

    @Override
    protected InformationPresenter initPresenter() {
        return new InformationPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_information;
    }

    @Override
    protected void initView() {
        super.initView();

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        recyclerview.setLayoutManager(new LinearLayoutManager(InformationActivity.this));

        recyclerview.addItemDecoration(new DividerItemDecoration(InformationActivity.this, DividerItemDecoration.VERTICAL));
        recyclerview.setSwipeMenuCreator(swipeMenuCreator);
        recyclerview.setSwipeMenuItemClickListener(swipeMenuItemClickListener);

        recyclerviewList = new ArrayList<>();
        recyclerviewList.add(new InformationBean("消息回复", "2017/10/21", "庫索回复了你的留言"));
        recyclerviewList.add(new InformationBean("系统通知", "2017/10/21", "林竹回复了你的留言"));
        recyclerviewList.add(new InformationBean("系统通知", "2017/10/21", "XX回复了你的留言"));
        informationRecyclerViewAdapter = new InformationRecyclerViewAdapter(recyclerviewList, InformationActivity.this);
        recyclerview.setAdapter(informationRecyclerViewAdapter);
    }

    // 设置菜单监听器。
    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() { // 创建菜单：
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_76);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            @SuppressLint("ResourceAsColor") SwipeMenuItem deleteItem = new SwipeMenuItem(InformationActivity.this)
//                    .setBackground(R.drawable.selector_red)
                    .setBackground(R.color.c_fa6a13)
//                    .setImage(R.mipmap.ic_action_delete)
                    .setTextColor(Color.WHITE)
                    .setText("删除")
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };
    // 菜单点击监听。
    SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) { // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            int direction = menuBridge.getDirection();//左边还是右边菜单
            int adapterPosition = menuBridge.getAdapterPosition();//    ecyclerView的Item的position。
            int position = menuBridge.getPosition();// 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                recyclerviewList.remove(adapterPosition);//删除item
                informationRecyclerViewAdapter.notifyDataSetChanged();
//                Toast.makeText(InformationActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + position, Toast.LENGTH_SHORT).show();
            }
        }
    };
}