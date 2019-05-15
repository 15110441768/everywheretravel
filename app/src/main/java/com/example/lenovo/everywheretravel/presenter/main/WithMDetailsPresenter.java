package com.example.lenovo.everywheretravel.presenter.main;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;
import com.example.lenovo.everywheretravel.model.main.WithMDetailsModel;
import com.example.lenovo.everywheretravel.model.main.WithMModel;
import com.example.lenovo.everywheretravel.view.main.WithMDetailsView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class WithMDetailsPresenter extends BasePresenter<WithMDetailsView> {

    private WithMDetailsModel mWithMDetailsModel;
    private WithMModel mWithMModel;

    @Override
    protected void initModel() {
        mWithMDetailsModel = new WithMDetailsModel();
        mWithMModel = new WithMModel();
        mModels.add(mWithMDetailsModel);
        mModels.add(mWithMModel);
    }

    public void getWithMDetailsData(int id, int page,String token) {
        mWithMDetailsModel.getWithMDetailsData(id, page, token, new CallBack<WithMDetailsBean>() {
            @Override
            public void onSuccess(WithMDetailsBean withMDetailsBean) {
                if (withMDetailsBean.getResult().getBanmi()!=null){
                    if (view!=null){
                        view.onSuccess(withMDetailsBean);
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
        mWithMModel.follow(id, token, new CallBack<ResponseBody>() {
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
        mWithMModel.unfollow(id, token, new CallBack<ResponseBody>() {
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
}
