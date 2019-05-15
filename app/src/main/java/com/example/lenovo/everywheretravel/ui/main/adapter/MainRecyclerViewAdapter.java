package com.example.lenovo.everywheretravel.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.bean.MainContentBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MainRecyclerViewAdapter";

    private ArrayList<MainContentBean.ResultBean.BannersBean> bannerList;
    private ArrayList<MainContentBean.ResultBean.RoutesBean> contentList;
    private Context context;

    public MainRecyclerViewAdapter(ArrayList<MainContentBean.ResultBean.BannersBean> bannerList, ArrayList<MainContentBean.ResultBean.RoutesBean> contentList, Context context) {
        this.bannerList = bannerList;
        this.contentList = contentList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_main_banner, null);
            return new ViewHolderBanner(view);
        }
        if (i == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_main_content_one, null);
            return new ViewHolderContentOne(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_main_content_two, null);
            return new ViewHolderContentTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.zhanweitu_home_kapian);
        if (viewHolder instanceof ViewHolderBanner) {
            final ViewHolderBanner viewHolderBanner = (ViewHolderBanner) viewHolder;
            // 去掉banner的指示器
            viewHolderBanner.banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
            viewHolderBanner.banner.setImages(bannerList);
            viewHolderBanner.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    MainContentBean.ResultBean.BannersBean path1 = (MainContentBean.ResultBean.BannersBean) path;
                    Glide.with(context).load(path1.getImageURL()).apply(requestOptions).into(imageView);
                }
            });
            viewHolderBanner.banner.start();
        }else {
            int newPosition = i;
            if (bannerList.size() > 0) {
                newPosition = i - 1;
            }
            final MainContentBean.ResultBean.RoutesBean routesBean = contentList.get(newPosition);
            if (viewHolder instanceof ViewHolderContentOne) {
                ViewHolderContentOne viewHolderContentOne = (ViewHolderContentOne) viewHolder;

                Glide.with(context).load(routesBean.getCardURL()).apply(requestOptions).into(viewHolderContentOne.cardURL);
                viewHolderContentOne.city.setText(routesBean.getCity());
                viewHolderContentOne.priceInCents.setText("¥"+routesBean.getPrice());
                viewHolderContentOne.title.setText(routesBean.getTitle());
                viewHolderContentOne.people_buy.setText(routesBean.getPurchasedTimes()+"人感兴趣");

                viewHolderContentOne.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTransferData!=null){
                            mTransferData.details(routesBean.getId());
                        }
                    }
                });
            }
            if (viewHolder instanceof ViewHolderContentTwo) {
                ViewHolderContentTwo viewHolderContentTwo = (ViewHolderContentTwo) viewHolder;

                Glide.with(context).load(routesBean.getCardURL()).apply(requestOptions).into(viewHolderContentTwo.cardURL);

                viewHolderContentTwo.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTransferData!=null){
                            mTransferData.details(routesBean.getContentURL(),routesBean.getTitle());
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if (bannerList.size() > 0) {
            return contentList.size() + 1;
        } else {
            return contentList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 0;
        }else {
            MainContentBean.ResultBean.RoutesBean routesBean = contentList.get(position-1);
            String type = routesBean.getType();
//            String title = routesBean.getTitle();
//            Log.e(TAG, "getItemViewType: " + title+"-----------"+ type);
            if (type.equals("route")) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    class ViewHolderBanner extends RecyclerView.ViewHolder {

        private Banner banner;

        public ViewHolderBanner(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    class ViewHolderContentOne extends RecyclerView.ViewHolder {

        private TextView city;
        private ImageView positioning;
        private TextView provinces;
        private Button priceInCents;
        private TextView title;
        private TextView people_buy;
        private ImageView cardURL;

        public ViewHolderContentOne(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city);
            positioning = itemView.findViewById(R.id.positioning);
            provinces = itemView.findViewById(R.id.provinces);
            priceInCents = itemView.findViewById(R.id.priceInCents);
            title = itemView.findViewById(R.id.title);
            people_buy = itemView.findViewById(R.id.people_buy);
            cardURL = itemView.findViewById(R.id.cardURL);
        }
    }

    class ViewHolderContentTwo extends RecyclerView.ViewHolder {

        private ImageView cardURL;

        public ViewHolderContentTwo(@NonNull View itemView) {
            super(itemView);
            cardURL = itemView.findViewById(R.id.cardURL);
        }
    }

    TransferData mTransferData;

    public void setTransferData(TransferData transferData) {
        mTransferData = transferData;
    }

    public interface TransferData{
        void details(int id);
        void details(String contentURL,String title);
    }
}
