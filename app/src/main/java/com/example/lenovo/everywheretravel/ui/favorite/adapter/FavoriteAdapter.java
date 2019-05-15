package com.example.lenovo.everywheretravel.ui.favorite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.bean.MyCollectBean;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private ArrayList<MyCollectBean.ResultBean.CollectedRoutesBean> recyclerviewList;
    private Context context;

    public FavoriteAdapter(ArrayList<MyCollectBean.ResultBean.CollectedRoutesBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MyCollectBean.ResultBean.CollectedRoutesBean collectedRoutesBean = recyclerviewList.get(i);
        RoundedCorners roundedCorners = new RoundedCorners(5);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(collectedRoutesBean.getCardURL()).apply(requestOptions).into(viewHolder.cardURL);
        viewHolder.intro.setText(collectedRoutesBean.getIntro());
        viewHolder.title.setText(collectedRoutesBean.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTransferData!=null){
                    mTransferData.details(collectedRoutesBean.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardURL;
        private TextView title;
        private TextView intro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardURL=itemView.findViewById(R.id.cardURL);
            title=itemView.findViewById(R.id.title);
            intro=itemView.findViewById(R.id.intro);
        }
    }

    TransferData mTransferData;

    public void setTransferData(TransferData transferData) {
        mTransferData = transferData;
    }

    public interface TransferData{
        void details(int id);
    }
}
