package com.aden.yefikirketero.UI.tabFragments.postedTab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.getPhone.DescribePaymentOptions;
import com.aden.yefikirketero.UI.OnLoadMoreListener;
import com.aden.yefikirketero.UI.getPhone.PaymentActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.VISIBLE;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;

    List<String> data1;
    List<Long> data2;
    List<String> data3;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public PostsAdapter(RecyclerView recyclerView, Context context, List<String> s1, List<Long> s2, List<String> s3) {
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
        return data1.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_each_post, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(ct).inflate(R.layout.item_progress_bar, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.myText1.setText(data1.get(position));
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

        //check what number the position is and call retrofit
    }

    @Override
    public int getItemCount() {
        return  data1 == null ? 0 : data1.size();
    }


    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;
        Button findPhone;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView3);
            findPhone = itemView.findViewById(R.id.findPhone);

            findPhone.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(ct, PaymentActivity.class);
                    long phoneNumber = data2.get(getLayoutPosition());
                    String userId = data3.get(getLayoutPosition());
                    intent.putExtra("currentPhone", phoneNumber);
                    intent.putExtra("currentId", userId);
                    ct.startActivity(intent);

                    //get phone without payment for men
//                    MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ct)
//                            .setMessage(ct.getResources().getString(R.string.connection_error));
//                    materialAlertDialogBuilder.setNeutralButton(ct.getResources().getString(R.string.back), new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which)
//                        {
//
//                        }
//                    });
//                    materialAlertDialogBuilder.setPositiveButton(ct.getResources().getString(R.string.save_button), new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which)
//                        {
//
//                        }
//                    });
//                    materialAlertDialogBuilder.show();
                }
            });
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }
}
