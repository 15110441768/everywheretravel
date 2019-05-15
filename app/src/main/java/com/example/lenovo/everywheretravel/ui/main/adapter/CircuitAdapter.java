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
import com.example.lenovo.everywheretravel.bean.CircuitBean;

import java.util.ArrayList;

public class CircuitAdapter extends RecyclerView.Adapter<CircuitAdapter.ViewHolder> {
    private ArrayList<CircuitBean.ResultBean.RoutesBean> mRecyclerviewList;
    private Context mContext;

    public CircuitAdapter(ArrayList<CircuitBean.ResultBean.RoutesBean> recyclerviewList, Context context) {
        mRecyclerviewList = recyclerviewList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_content_one, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CircuitBean.ResultBean.RoutesBean routesBean = mRecyclerviewList.get(i);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.zhanweitu_home_kapian);
        Glide.with(mContext).load(routesBean.getCardURL()).apply(requestOptions).into(viewHolder.cardURL);
        viewHolder.city.setText(routesBean.getCity());
        viewHolder.priceInCents.setText("¥"+routesBean.getPrice());
        viewHolder.title.setText(routesBean.getTitle());
        viewHolder.people_buy.setText(routesBean.getPriceInCents()+"人感兴趣");


    }

    @Override
    public int getItemCount() {
        return mRecyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardURL;
        private TextView city;
        private Button priceInCents;
        private TextView title;
        private TextView people_buy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardURL=itemView.findViewById(R.id.cardURL);
            city=itemView.findViewById(R.id.city);
            priceInCents=itemView.findViewById(R.id.priceInCents);
            title=itemView.findViewById(R.id.title);
            people_buy=itemView.findViewById(R.id.people_buy);
        }
    }
}
