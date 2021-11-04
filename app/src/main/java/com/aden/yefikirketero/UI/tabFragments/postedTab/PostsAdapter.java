package com.aden.yefikirketero.UI.tabFragments.postedTab;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;

    List<String> userId = new ArrayList<>();
    List<String> name = new ArrayList<>();
    List<String> age = new ArrayList<>();
    List<String> gender = new ArrayList<>();
    List<String> phone = new ArrayList<>();
    List<String> address = new ArrayList<>();
    List<String> religion = new ArrayList<>();
    List<String> height = new ArrayList<>();
    List<String> job = new ArrayList<>();
    List<String> bio = new ArrayList<>();
    List<String> dateMinAge = new ArrayList<>();
    List<String> dateMaxAge = new ArrayList<>();
    List<String> dateReligion = new ArrayList<>();
    List<String> dateHeight = new ArrayList<>();
    List<String> dateJob = new ArrayList<>();

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public PostsAdapter(RecyclerView recyclerView, Context context,  List<String> userId, List<String> name, List<String> age, List<String> gender, List<String> phone, List<String> address, List<String> religion, List<String> height, List<String> job, List<String> bio, List<String> dateMinAge, List<String> dateMaxAge, List<String> dateReligion, List<String> dateHeight, List<String> dateJob) {
        ct = context;
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.religion = religion;
        this.height = height;
        this.job = job;
        this.bio = bio;
        this.dateMinAge = dateMinAge;
        this.dateMaxAge = dateMaxAge;
        this.dateReligion = dateReligion;
        this.dateHeight = dateHeight;
        this.dateJob = dateJob;

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
        return name.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
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
            String post = ct.getResources().getString(R.string.decoration) + "\n\n";
            String myInfo = "";
            if(name.get(position) != null && !name.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.name_label) + " " + name.get(position);
            }
            if(age.get(position) != null && !age.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.age_label)+ " " + age.get(position);
            }
            if(gender.get(position) != null && !gender.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.gender_label)+ " " + gender.get(position);
            }
            if(phone.get(position) != null && !phone.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.phone_label)+ " " + phone.get(position);
            }
            if(address.get(position) != null && !address.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.address_label) + " " + address.get(position);
            }
            if(religion.get(position) != null && !religion.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.religion_label)+ " " + religion.get(position);
            }
            if(height.get(position) != null && !height.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.height_label)+ " " + height.get(position);
            }
            if(job.get(position) != null && !job.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.job_label)+ " " + job.get(position);
            }
            if(bio.get(position) != null && !bio.get(position).equals("")){
                myInfo = myInfo + "\n" + ct.getResources().getString(R.string.bio_label) + " " + bio.get(position);
            }

            String dateInfo = "";
            if(dateMinAge.get(position) != null && !dateMinAge.get(position).equals("")){
                dateInfo = dateInfo + "\n" + ct.getResources().getString(R.string.date_min_age_label)+ " " + dateMinAge.get(position);
            }
            if(dateMaxAge.get(position) != null && !dateMaxAge.get(position).equals("")){
                dateInfo = dateInfo + "\n" + ct.getResources().getString(R.string.date_max_age_label)+ " " + dateMaxAge.get(position);
            }
            if(dateHeight.get(position) != null && !dateHeight.get(position).equals("")){
                dateInfo = dateInfo + "\n" + ct.getResources().getString(R.string.date_height_label)+ " " + dateHeight.get(position);
            }
            if(dateReligion.get(position) != null && !dateReligion.get(position).equals("")){
                dateInfo = dateInfo + "\n" + ct.getResources().getString(R.string.date_religion_label)+ " " + dateReligion.get(position);
            }
            if(dateJob.get(position) != null && !dateJob.get(position).equals("")){
                dateInfo = dateInfo + "\n" + ct.getResources().getString(R.string.date_job_label)+ " " + dateJob.get(position);
            }
            if(bio.get(position) != null && !bio.get(position).equals("")){
                dateInfo = dateInfo + "\n\n" + bio.get(position);
            }

            if(!dateInfo.equals("")){
                dateInfo = "\n\n" + ct.getResources().getString(R.string.your_date_info_label) + "\n" + dateInfo;
            }

            String endLine = "\n\n" + ct.getResources().getString(R.string.decoration);
            post = post + myInfo + dateInfo + endLine;


            myViewHolder.myText1.setText(post);
            myViewHolder.myText1.setTextSize(18);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

        //check what number the position is and call retrofit
    }

    @Override
    public int getItemCount() {
        return  name == null ? 0 : name.size();
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
                    String phoneNumber = phone.get(getLayoutPosition());
                    String id = userId.get(getLayoutPosition());
                    intent.putExtra("currentPhone", phoneNumber);
                    intent.putExtra("currentId", id);
                    ct.startActivity(intent);
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
