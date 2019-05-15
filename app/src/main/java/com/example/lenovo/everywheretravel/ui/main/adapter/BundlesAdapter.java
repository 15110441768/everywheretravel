package com.example.lenovo.everywheretravel.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.bean.BundlesContentBean;

import java.util.ArrayList;

public class BundlesAdapter extends RecyclerView.Adapter<BundlesAdapter.ViewHolder> {
    private ArrayList<BundlesContentBean.ResultBean.BundlesBean> mRecyclerviewList;
    Context mContext;

    public BundlesAdapter(ArrayList<BundlesContentBean.ResultBean.BundlesBean> recyclerviewList, Context context) {
        mRecyclerviewList = recyclerviewList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_content_two, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final BundlesContentBean.ResultBean.BundlesBean bundlesBean = mRecyclerviewList.get(i);
        Glide.with(mContext).load(bundlesBean.getCardURL()).into(viewHolder.cardURL);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTransferData!=null){
                    mTransferData.details(bundlesBean.getContentURL(),bundlesBean.getTitle());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardURL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardURL=itemView.findViewById(R.id.cardURL);
        }
    }

    TransferData mTransferData;

    public void setTransferData(TransferData transferData) {
        mTransferData = transferData;
    }

    public interface TransferData{
        void details(String contentURL,String title);
    }
}
