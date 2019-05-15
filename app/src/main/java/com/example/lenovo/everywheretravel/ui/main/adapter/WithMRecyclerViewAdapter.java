package com.example.lenovo.everywheretravel.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.bean.BanmiBean;
import com.example.lenovo.everywheretravel.utils.DbUtil;

import java.util.ArrayList;

public class WithMRecyclerViewAdapter extends RecyclerView.Adapter<WithMRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "WithMRecyclerViewAdapte";
    private ArrayList<BanmiBean> recyclerviewList;
    private Context context;
    private boolean mIsFollowed;


    public WithMRecyclerViewAdapter(ArrayList<BanmiBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_with_m_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final BanmiBean banmiBean = recyclerviewList.get(i);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.zhanweitu_xianlu_qipao_small);
        Glide.with(context).load(banmiBean.getPhoto()).apply(requestOptions).into(viewHolder.photo);
        viewHolder.name.setText(banmiBean.getName());
        viewHolder.following.setText(banmiBean.getFollowing()+"人关注");
        viewHolder.location.setText(banmiBean.getLocation());
        viewHolder.occupation.setText(banmiBean.getOccupation());
        boolean isFollowed = banmiBean.getIsFollowed();
        final int id = banmiBean.getId();
        Log.e(TAG, "onBindViewHolder: " + isFollowed+"---------"+i);

        if (isFollowed){
            viewHolder.follow.setBackground(context.getResources().getDrawable(R.mipmap.follow));
        }else {
            viewHolder.follow.setBackground(context.getResources().getDrawable(R.mipmap.follow_unselected));
        }

        viewHolder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsFollowed = banmiBean.getIsFollowed();
                Log.e(TAG, "1: " + mIsFollowed);
                if (mIsFollowed){
//                    Log.e(TAG, "mIsFollowed: " + mIsFollowed);
//                    viewHolder.follow.setBackground(context.getResources().getDrawable(R.mipmap.follow_unselected));
//                    mIsFollowed=false;
                    banmiBean.setIsFollowed(false);
                    if (notifyAdapter!=null){
                        notifyAdapter.unfollow(id,i);
                    }

                }else {

//                    viewHolder.follow.setBackground(context.getResources().getDrawable(R.mipmap.follow));
//                    mIsFollowed=true;
                    banmiBean.setIsFollowed(true);
                    if (notifyAdapter!=null){
                        notifyAdapter.follow(id,i);
                    }
                }
                Log.e(TAG, "onClick: " + mIsFollowed);
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notifyAdapter!=null){
                    notifyAdapter.details(id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
    }

    public void notifyData(int newPosition) {
//        mIsFollowed=recyclerviewList.get(newPosition).getIsFollowed();
        notifyItemChanged(newPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView name;
        private ImageView follow;
        private TextView following;
        private TextView location;
        private TextView occupation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.photo);
            name=itemView.findViewById(R.id.name);
            follow=itemView.findViewById(R.id.follow);
            following=itemView.findViewById(R.id.following);
            location=itemView.findViewById(R.id.location);
            occupation=itemView.findViewById(R.id.occupation);
        }
    }

    NotifyAdapter notifyAdapter;

    public void setNotifyAdapter(NotifyAdapter notifyAdapter) {
        this.notifyAdapter = notifyAdapter;
    }

    public interface NotifyAdapter{
        void follow(int id,int position);
        void unfollow(int id,int position);
        void details(int id);
    }
}
