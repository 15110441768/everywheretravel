package com.example.lenovo.everywheretravel.ui.main.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.bean.WithMDetailsBean;

import java.util.ArrayList;

public class DynamicStateAdapter extends RecyclerView.Adapter<DynamicStateAdapter.ViewHolder> {

    private static final String TAG = "DynamicStateAdapter";

    private ArrayList<WithMDetailsBean.ResultBean.ActivitiesBean> mRecyclerviewList;
    Context mContext;
    public MediaPlayer mMediaPlayer;


    public DynamicStateAdapter(ArrayList<WithMDetailsBean.ResultBean.ActivitiesBean> recyclerviewList, Context context) {
        mRecyclerviewList = recyclerviewList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dynamicstate, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.photoview.enable();

        final WithMDetailsBean.ResultBean.ActivitiesBean activitiesBean = mRecyclerviewList.get(i);
        // 评论发布的时间
        viewHolder.date.setText(activitiesBean.getDate());
        // 判断有没有语音
        String audioURL = activitiesBean.getAudioURL();
        // 有 显示语音的cardview    没有 显示文字的cardview
        if (audioURL.isEmpty()) {
            viewHolder.cardview_text.setVisibility(View.VISIBLE);
            viewHolder.cardview.setVisibility(View.GONE);
            viewHolder.background_text.setVisibility(View.VISIBLE);
            viewHolder.background_audio.setVisibility(View.GONE);
            viewHolder.explain.setText("发表了一个动态");
//            if (activitiesBean.isIsLiked()){
////                viewHolder.praise.setImageResource(R.mipmap.praise);
//                viewHolder.praise_text.setImageResource(R.mipmap.praise);
//            }else {
////                viewHolder.praise.setImageResource(R.mipmap.praise_unselected);
//                viewHolder.praise_text.setImageResource(R.mipmap.praise_unselected);
//            }
        } else {
            viewHolder.cardview_text.setVisibility(View.GONE);
            viewHolder.cardview.setVisibility(View.VISIBLE);
            viewHolder.background_audio.setVisibility(View.VISIBLE);
            viewHolder.background_text.setVisibility(View.GONE);
            viewHolder.explain.setText("更新了一条语音");
//            if (activitiesBean.isIsLiked()){
//                viewHolder.praise.setImageResource(R.mipmap.praise);
////                viewHolder.praise_text.setImageResource(R.mipmap.praise);
//            }else {
//                viewHolder.praise.setImageResource(R.mipmap.praise_unselected);
////                viewHolder.praise_text.setImageResource(R.mipmap.praise_unselected);
//            }
        }
        if (i > 1) {
            viewHolder.background_text.setVisibility(View.GONE);
            viewHolder.background_audio.setVisibility(View.GONE);
        }
        viewHolder.time.setText(activitiesBean.getAudioLength() + "");
        if (activitiesBean.isIsLiked()) {
            viewHolder.praise.setImageResource(R.mipmap.praise);
            viewHolder.praise_text.setImageResource(R.mipmap.praise);

        } else {
            viewHolder.praise.setImageResource(R.mipmap.praise_unselected);
            viewHolder.praise_text.setImageResource(R.mipmap.praise_unselected);
            viewHolder.likeCount.setTextColor(mContext.getResources().getColor(R.color.c_999999));
            viewHolder.likeCount_text.setTextColor(mContext.getResources().getColor(R.color.c_999999));
            viewHolder.replyCount.setTextColor(mContext.getResources().getColor(R.color.c_999999));
            viewHolder.replyCount_text.setTextColor(mContext.getResources().getColor(R.color.c_999999));
        }
        viewHolder.likeCount.setText(activitiesBean.getLikeCount() + "");
        viewHolder.likeCount_text.setText(activitiesBean.getLikeCount() + "");
        viewHolder.replyCount.setText(activitiesBean.getReplyCount() + "");
        viewHolder.replyCount_text.setText(activitiesBean.getReplyCount() + "");
        viewHolder.content.setText(activitiesBean.getContent());
        if (activitiesBean.getImages().size() > 0) {
            Glide.with(mContext).load(activitiesBean.getImages().get(0))
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(5)).placeholder(R.drawable.zhanweitu_xianlu_jingdian))
                    .into(viewHolder.image);
        } else {
            viewHolder.image.setVisibility(View.GONE);
//            int height = viewHolder.content.getHeight();
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.background_text.getLayoutParams();
//
//            layoutParams.height = layoutParams.height + 30 ;
//            Log.e(TAG, "onBindViewHolder: " + layoutParams.height);
//            viewHolder.background_text.setLayoutParams(layoutParams);
        }

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.photoview.setVisibility(View.VISIBLE);

                Glide.with(mContext).load(activitiesBean.getImages().get(0)).into(viewHolder.photoview);
            }
        });


        viewHolder.play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    mMediaPlayer = MediaPlayer.create(mContext, Uri.parse(activitiesBean.getAudioURL()));
                    mMediaPlayer.start();
                    viewHolder.play.setVisibility(View.INVISIBLE);
                    viewHolder.stop.setVisibility(View.VISIBLE);
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        viewHolder.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.play.setVisibility(View.VISIBLE);
                viewHolder.stop.setVisibility(View.INVISIBLE);
                mMediaPlayer.stop();
                notifyItemChanged(i);
            }
        });
    }

    private void initPop(int position){

    }

    @Override
    public int getItemCount() {
        return mRecyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private TextView explain;
        private ImageView background_audio;
        private ImageView background_text;
        private ImageView play;
        private ImageView stop;
        private TextView time;
        private ImageView praise;
        private TextView likeCount;
        private ImageView comment;
        private TextView replyCount;
        private CardView cardview;
        private TextView title;
        private TextView content;
        private ImageView image;
        private ImageView praise_text;
        private TextView likeCount_text;
        private ImageView comment_text;
        private TextView replyCount_text;
        private CardView cardview_text;
        private PhotoView photoview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            explain = itemView.findViewById(R.id.explain);
            background_audio = itemView.findViewById(R.id.background_audio);
            background_text = itemView.findViewById(R.id.background_text);
            play = itemView.findViewById(R.id.play);
            stop = itemView.findViewById(R.id.stop);
            time = itemView.findViewById(R.id.time);
            praise = itemView.findViewById(R.id.praise);
            likeCount = itemView.findViewById(R.id.likeCount);
            comment = itemView.findViewById(R.id.comment);
            replyCount = itemView.findViewById(R.id.replyCount);
            cardview = itemView.findViewById(R.id.cardview);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.image);
            praise_text = itemView.findViewById(R.id.praise_text);
            likeCount_text = itemView.findViewById(R.id.likeCount_text);
            comment_text = itemView.findViewById(R.id.comment_text);
            replyCount_text = itemView.findViewById(R.id.replyCount_text);
            cardview_text = itemView.findViewById(R.id.cardview_text);
            photoview = itemView.findViewById(R.id.photoview);
        }
    }
}
