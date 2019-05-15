package com.example.lenovo.everywheretravel.ui.information.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.bean.InformationBean;

import java.util.ArrayList;

public class InformationRecyclerViewAdapter extends RecyclerView.Adapter<InformationRecyclerViewAdapter.ViewHolder> {
    private ArrayList<InformationBean> recyclerviewList;
    private Context context;

    public InformationRecyclerViewAdapter(ArrayList<InformationBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_information_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        InformationBean informationBean = recyclerviewList.get(i);
        viewHolder.name.setText(informationBean.getName());
        viewHolder.time.setText(informationBean.getTime());
        viewHolder.content.setText(informationBean.getContent());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sendDataToActivity!=null){
                    sendDataToActivity.sendData();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView time;
        private TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            content=itemView.findViewById(R.id.content);
        }
    }

    SendDataToActivity sendDataToActivity;

    public void setSendDataToActivity(SendDataToActivity sendDataToActivity) {
        this.sendDataToActivity = sendDataToActivity;
    }

    public interface SendDataToActivity{
        void sendData();
    }
}
