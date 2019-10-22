package com.whyteCreations.beautysalon.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.whyteCreations.beautysalon.Model.Salon;
import com.whyteCreations.beautysalon.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchAdapters extends RecyclerView.Adapter<SearchAdapters.MyViewHolder> {
    private Unbinder unbinder;
    private Context mContext;
    private LayoutInflater inflater;
    ArrayList<Salon> searchBusinessList = new ArrayList<>();
    Salon salon;
    RecyclerView recyclerView;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    public boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public SearchAdapters(Context mContext, LayoutInflater inflater, ArrayList<Salon> searchBusinessList, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.inflater = inflater;
        this.searchBusinessList = searchBusinessList;
        this.recyclerView = recyclerView;
        inflater = LayoutInflater.from(mContext);
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                totalItemCount = linearLayoutManager.getItemCount();
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                    if (mOnLoadMoreListener != null) {
//                        mOnLoadMoreListener.onLoadMore();
//                    }
//                    isLoading = true;
//                }
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            holder.businessName.setText(searchBusinessList.get(position).getBusinessName());
            holder.location.setText(searchBusinessList.get(position).getName());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_placeholder_sq);
            Glide.with(mContext).load(searchBusinessList.get(position).getPhoto())
                    .apply(requestOptions).into(holder.imageView);
        } else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);

            if (!isLoading)
                ((LoadingViewHolder) holder).progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return searchBusinessList.size();
    }

    public void setLoaded() {
        isLoading=false;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.businessName)
        TextView businessName;
        @BindView(R.id.location)
        TextView location;
        Unbinder unbinder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            imageView = itemView.findViewById(R.id.image);
            businessName = itemView.findViewById(R.id.businessName);
            location = itemView.findViewById(R.id.location);
        }
    }

    private class LoadingViewHolder extends MyViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
}
