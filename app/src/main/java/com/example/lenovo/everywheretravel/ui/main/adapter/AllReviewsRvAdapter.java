package com.example.lenovo.everywheretravel.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.bean.AllReviewsBean;

import java.util.ArrayList;

public class AllReviewsRvAdapter extends RecyclerView.Adapter<AllReviewsRvAdapter.ViewHolder> {
    private ArrayList<AllReviewsBean.ResultBean.ReviewsBean> mRecyclerviewList;
    Context mContext;

    public AllReviewsRvAdapter(ArrayList<AllReviewsBean.ResultBean.ReviewsBean> recyclerviewList, Context context) {
        mRecyclerviewList = recyclerviewList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_allreviews_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AllReviewsBean.ResultBean.ReviewsBean reviewsBean = mRecyclerviewList.get(i);
        Glide.with(mContext).load(reviewsBean.getUserPhoto()).apply(new RequestOptions().circleCrop().placeholder(R.drawable.zhanweitu_touxiang)).into(viewHolder.userPhoto);
        viewHolder.userName.setText(reviewsBean.getUserName());
        viewHolder.createdAt.setText(reviewsBean.getCreatedAt());
        viewHolder.content.setText(reviewsBean.getContent());
    }

    @Override
    public int getItemCount() {
        return mRecyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView userPhoto;
        private TextView userName;
        private TextView createdAt;
        private TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userPhoto=itemView.findViewById(R.id.userPhoto);
            userName=itemView.findViewById(R.id.userName);
            createdAt=itemView.findViewById(R.id.createdAt);
            content=itemView.findViewById(R.id.content);
        }
    }
}
