package com.example.lenovo.everywheretravel.presenter.main;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.model.main.WithMModel;
import com.example.lenovo.everywheretravel.bean.WithMBean;
import com.example.lenovo.everywheretravel.view.main.WithMView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class WithMPresenter extends BasePresenter<WithMView> {

    private WithMModel withMModel;

    @Override
    protected void initModel() {
        withMModel = new WithMModel();
        mModels.add(withMModel);
    }

    public void getWithMData(int page,String token) {
        withMModel.getWithMData(page, token, new CallBack<WithMBean>() {
            @Override
            public void onSuccess(WithMBean withMBean) {
                if (withMBean!=null&&withMBean.getResult().getBanmi().size()>0){
                    if (view!=null){
                        view.onSuccess(withMBean);
                    }
                }else {
                    if (view!=null){
                        view.onFail("失败");
                    }
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onFail(string);
                }
            }
        });
    }

    public void follow(int id, String token) {
        withMModel.follow(id, token, new CallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    if (string!=null){
                        JSONObject jsonObject = new JSONObject(string);
                        JSONObject object = jsonObject.getJSONObject("result");
                        String message = object.getString("message");
                        if (view!=null){
                            view.onFollowSuccess(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onFollowFail(string);
                }
            }
        });
    }

    public void unfollow(int id, String token) {
        withMModel.unfollow(id, token, new CallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    if (string!=null){
                        JSONObject jsonObject = new JSONObject(string);
                        JSONObject object = jsonObject.getJSONObject("result");
                        String message = object.getString("message");
                        if (view!=null){
                            view.onUnfollowSuccess(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onUnfollowFail(string);
                }
            }
        });
    }

    // 关注界面的数据
    public void getFollowData(int page,String token) {
        withMModel.getFollowData(page, token, new CallBack<WithMBean>() {
            @Override
            public void onSuccess(WithMBean withMBean) {
                if (withMBean!=null){
                    if (view!=null){
                        view.onSuccess(withMBean);
                    }
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onFail(string);
                }
            }
        });
    }
}
