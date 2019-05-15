package com.example.lenovo.everywheretravel.presenter.main;

import android.widget.Toast;

import com.example.lenovo.everywheretravel.base.BasePresenter;
import com.example.lenovo.everywheretravel.base.CallBack;
import com.example.lenovo.everywheretravel.model.main.DetailsPageModel;
import com.example.lenovo.everywheretravel.bean.DetailsPageBean;
import com.example.lenovo.everywheretravel.ui.MainActivity;
import com.example.lenovo.everywheretravel.view.main.DetailsPageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class DetailsPagePresenter extends BasePresenter<DetailsPageView> {

    private DetailsPageModel mDetailsPageModel;

    @Override
    protected void initModel() {
        mDetailsPageModel = new DetailsPageModel();
        mModels.add(mDetailsPageModel);
    }

    public void getDetailsPageData(int id,String token) {
        mDetailsPageModel.getDetailsPageData(id, token, new CallBack<DetailsPageBean>() {
            @Override
            public void onSuccess(DetailsPageBean detailsPageBean) {
                if (detailsPageBean!=null&&detailsPageBean.getResult()!=null){
                    if (view!=null){
                        view.onSuccess(detailsPageBean);
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

    public void cancelCollection(int id, String token) {
        mDetailsPageModel.cancelCollection(id, token, new CallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    JSONObject jsonObject = new JSONObject(string);
                    String desc = jsonObject.getString("desc");
                    if (desc!=null){
                        if (view!=null){
                            view.onCancelCollectionSuccess(desc);
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
                    view.onCancelCollectionFail(string);
                }
            }
        });
    }

    public void collect(int id, String token) {
        mDetailsPageModel.collect(id, token, new CallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    JSONObject jsonObject = new JSONObject(string);
                    String desc = jsonObject.getString("desc");
                    if (desc!=null){
                        if (view!=null){
                            view.onCollectSuccess(desc);
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
                    view.onCollectFail(string);
                }
            }
        });
    }

    public void share(String mCardURL,String mIntro) {
        UMImage image = new UMImage(view.getAct(), mCardURL);//网络图片
        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
        new ShareAction(view.getAct())
                .withText(mIntro)
                .withMedia(image)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(view.getAct(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(view.getAct(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(view.getAct(), "取消了", Toast.LENGTH_LONG).show();

        }
    };
}

