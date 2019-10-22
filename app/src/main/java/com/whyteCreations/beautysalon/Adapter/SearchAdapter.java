package com.whyteCreations.beautysalon.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.whyteCreations.beautysalon.Interface.IRecyclerViewOnClick;
import com.whyteCreations.beautysalon.Model.Salon;
import com.whyteCreations.beautysalon.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private Context context;
    private List<Salon> searchBusinessList;


    public SearchAdapter(Context context, List<Salon> searchBusinessList) {
        this.context = context;
        this.searchBusinessList = searchBusinessList;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.businessName.setText(searchBusinessList.get(position).getBusinessName());
        holder.location.setText(searchBusinessList.get(position).getName());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_placeholder_sq);
        Glide.with(context).load(searchBusinessList.get(position).getPhoto())
                .apply(requestOptions).into(holder.imageView);
        holder.setiRecyclerViewOnClick(new IRecyclerViewOnClick() {

            @Override
            public void onClick(View view, int position) {

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e("ITEM", String.valueOf(searchBusinessList.size()));
        return searchBusinessList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.businessName)
        TextView businessName;
        @BindView(R.id.location)
        TextView location;
        IRecyclerViewOnClick iRecyclerViewOnClick;
        Unbinder unbinder;

        public void setiRecyclerViewOnClick(IRecyclerViewOnClick iRecyclerViewOnClick) {
            this.iRecyclerViewOnClick = iRecyclerViewOnClick;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            imageView = itemView.findViewById(R.id.image);
            businessName = itemView.findViewById(R.id.businessName);
            location = itemView.findViewById(R.id.location);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecyclerViewOnClick.onClick(v, getAdapterPosition());
        }
    }
}
