package com.example.lenovo.everywheretravel.ui.details.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.ui.details.bean.DetailsBean;

import java.util.ArrayList;

public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<DetailsBean> recyclerviewList;
    private Context context;

    public DetailsRecyclerViewAdapter(ArrayList<DetailsBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_details_one, null);
            return new ViewHolderA(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_details_two, null);
            return new ViewHolderB(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        DetailsBean detailsBean = recyclerviewList.get(i);
        if (viewHolder instanceof ViewHolderA){
            ViewHolderA viewHolderA= (ViewHolderA) viewHolder;
            viewHolderA.time.setText(detailsBean.getTime());
            viewHolderA.content.setText(detailsBean.getContent());
        }
        if (viewHolder instanceof ViewHolderB){
            ViewHolderB viewHolderB= (ViewHolderB) viewHolder;
            viewHolderB.time.setText(detailsBean.getTime());
            viewHolderB.content.setText(detailsBean.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return recyclerviewList.get(position).getType();
    }

    class ViewHolderA extends RecyclerView.ViewHolder {

        private TextView time;
        private TextView content;

        public ViewHolderA(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.time);
            content=itemView.findViewById(R.id.content);
        }
    }

    class ViewHolderB extends RecyclerView.ViewHolder {

        private TextView time;
        private TextView content;

        public ViewHolderB(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.time);
            content=itemView.findViewById(R.id.content);
        }
    }
}
