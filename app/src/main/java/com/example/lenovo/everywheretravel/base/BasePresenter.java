package com.example.lenovo.everywheretravel.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    protected V view;
    protected ArrayList<BaseModel> mModels = new ArrayList<>();

    public void bind(V view) {
        this.view=view;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void onDestory() {
        //打断P层和V层的联系,
        view = null;
        //掐断网络请求
        if (mModels.size()>0){
            for (BaseModel model :mModels) {
                model.onDestory();
            }
            mModels.clear();
        }
    }
}
