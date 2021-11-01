package com.aden.yefikirketero.UI.options;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.OnLoadMoreListener;
import com.aden.yefikirketero.UI.getPhone.PaymentActivity;
import com.aden.yefikirketero.UI.tabFragments.postedTab.PostsAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;

    List<String> data1;
    List<String> data2;
    List<String> data3;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public PhoneListAdapter(RecyclerView recyclerView, Context context, List<String> s1, List<String> s2, List<String> s3) {
        ct = context;
        data1 = s1;
        data2 = s2;
        data3 = s3;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_each_received, parent, false);
            return new MyViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.phoneNumberReceived.setText(data1.get(position));
        }

        //check what number the position is and call retrofit
    }

    @Override
    public int getItemCount() {
        return  data1 == null ? 0 : data1.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView phoneNumberReceived;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            phoneNumberReceived = itemView.findViewById(R.id.phone_number_received);
        }
    }

}
