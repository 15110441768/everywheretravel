package com.example.lenovo.everywheretravel.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseApp;
import com.example.lenovo.everywheretravel.ui.main.bean.MainContentBean;
import com.youth.banner.Banner;
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
        if (viewHolder instanceof ViewHolderBanner) {
            final ViewHolderBanner viewHolderBanner = (ViewHolderBanner) viewHolder;
            viewHolderBanner.banner.setImages(bannerList);
            viewHolderBanner.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    MainContentBean.ResultBean.BannersBean path1 = (MainContentBean.ResultBean.BannersBean) path;
                    Glide.with(context).load(path1.getImageURL()).into(imageView);
                }
            });
            viewHolderBanner.banner.start();
        }
        if (viewHolder instanceof ViewHolderContentOne) {
            ViewHolderContentOne viewHolderContentOne = (ViewHolderContentOne) viewHolder;
            int newPosition = i;
            if (bannerList.size() > 0) {
                newPosition = i - 1;
            }
            MainContentBean.ResultBean.RoutesBean routesBean = contentList.get(newPosition);
            Glide.with(context).load(routesBean.getCardURL()).into(viewHolderContentOne.cardURL);
            viewHolderContentOne.city.setText(routesBean.getCity());
            viewHolderContentOne.priceInCents.setText(routesBean.getPriceInCents() + "");
            viewHolderContentOne.title.setText(routesBean.getTitle());
        }
        if (viewHolder instanceof ViewHolderContentTwo) {
            ViewHolderContentTwo viewHolderContentTwo = (ViewHolderContentTwo) viewHolder;
            int newPosition = i;
            if (bannerList.size() > 0) {
                newPosition = i - 1;
            }
            MainContentBean.ResultBean.RoutesBean routesBean = contentList.get(newPosition);
            Glide.with(context).load(routesBean.getCardURL()).into(viewHolderContentTwo.cardURL);
            viewHolderContentTwo.title.setText(routesBean.getTitle());

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
            String title = routesBean.getTitle();
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

        private TextView title;
        private ImageView cardURL;

        public ViewHolderContentTwo(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            cardURL = itemView.findViewById(R.id.cardURL);
        }
    }
}
