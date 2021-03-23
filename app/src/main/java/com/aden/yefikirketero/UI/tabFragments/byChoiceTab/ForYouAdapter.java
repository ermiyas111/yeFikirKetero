package com.aden.yefikirketero.UI.tabFragments.byChoiceTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.OnLoadMoreListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ForYouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;

    List<String> phoneNumberNotifications;
    List<String> matchedCouples;
    List<String> receivedRequests;
    List<String> byChoice;

    List<String> allItems;

    List<Integer> forYouTabClassificationIndexes;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;

    private final int VIEW_TYPE_ITEM_PHONE_NOTIFICATION = 1;
    private final int VIEW_TYPE_ITEM_MATCHED_COUPLES_TITLE = 2;
    private final int VIEW_TYPE_ITEM_MATCHED_COUPLES = 3;
    private final int VIEW_TYPE_ITEM_RECEIVED_REQUESTS_TITLE = 4;
    private final int VIEW_TYPE_ITEM_RECEIVED_REQUESTS = 5;
    private final int VIEW_TYPE_ITEM_BY_CHOICE_TITLE = 6;
    private final int VIEW_TYPE_ITEM_BY_CHOICE = 7;

    private final int VIEW_TYPE_LOADING = 0;

    public ForYouAdapter(RecyclerView recyclerView, Context context, List<String> allItems, List<String> phoneNumberNotifications, List<String> matchedCouples, List<String> receivedRequests, List<String> byChoice, List<Integer> forYouTabClassificationIndexes) {
        ct = context;
        this.allItems = allItems;
        this.phoneNumberNotifications = phoneNumberNotifications;
        this.matchedCouples = matchedCouples;
        this.receivedRequests = receivedRequests;
        this.byChoice = byChoice;
        this.forYouTabClassificationIndexes = forYouTabClassificationIndexes;

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
        if(phoneNumberNotifications.contains(allItems.get(position)) && position <= forYouTabClassificationIndexes.get(0)){
            return VIEW_TYPE_ITEM_PHONE_NOTIFICATION;
        }else if(allItems.get(position) == "matched_couples_title"){
            return VIEW_TYPE_ITEM_MATCHED_COUPLES_TITLE;
        }else if(matchedCouples.contains(allItems.get(position))){
            return VIEW_TYPE_ITEM_MATCHED_COUPLES;
        }else if(allItems.get(position) == "received_requests_title"){
            return VIEW_TYPE_ITEM_RECEIVED_REQUESTS_TITLE;
        }else if(receivedRequests.contains(allItems.get(position)) && position > forYouTabClassificationIndexes.get(0) && position <= forYouTabClassificationIndexes.get(1)){
            return VIEW_TYPE_ITEM_RECEIVED_REQUESTS;
        }else if(allItems.get(position) == "by_choice_title"){
            return VIEW_TYPE_ITEM_BY_CHOICE_TITLE;
        }else if(byChoice.contains(allItems.get(position)) && position<=38){
            return VIEW_TYPE_ITEM_BY_CHOICE;
        }else if(allItems.get(position) == null){
            return VIEW_TYPE_LOADING;
        }
        return VIEW_TYPE_ITEM_BY_CHOICE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM_PHONE_NOTIFICATION) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_received_phone_numbers_notification, parent, false);
            return new ForYouAdapter.PhoneNotificationViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM_MATCHED_COUPLES_TITLE) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_matched_friends_title, parent, false);
            return new ForYouAdapter.MatchedCouplesTitleViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM_MATCHED_COUPLES) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_each_matched_friend, parent, false);
            return new ForYouAdapter.MatchedCouplesViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM_RECEIVED_REQUESTS_TITLE) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_received_request_title, parent, false);
            return new ForYouAdapter.ReceivedRequestsTitleViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM_RECEIVED_REQUESTS) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_each_received_request, parent, false);
            return new ForYouAdapter.ReceivedRequestsViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM_BY_CHOICE_TITLE) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_based_on_your_choice_title, parent, false);
            return new ForYouAdapter.ByChoiceTitleViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM_BY_CHOICE) {
            LayoutInflater inflater = LayoutInflater.from(ct);
            View view = inflater.inflate(R.layout.item_each_registered_user, parent, false);
            return new ForYouAdapter.ByChoiceViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(ct).inflate(R.layout.item_progress_bar, parent, false);
            return new ForYouAdapter.LoadingViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ForYouAdapter.PhoneNotificationViewHolder) {
            ForYouAdapter.PhoneNotificationViewHolder myViewHolder = (ForYouAdapter.PhoneNotificationViewHolder) holder;
            myViewHolder.myText1.setText(allItems.get(position));
        } else if (holder instanceof ForYouAdapter.MatchedCouplesViewHolder) {
            ForYouAdapter.MatchedCouplesViewHolder myViewHolder = (ForYouAdapter.MatchedCouplesViewHolder) holder;
            myViewHolder.myText1.setText(allItems.get(position));
        } else if (holder instanceof ForYouAdapter.ReceivedRequestsViewHolder) {
            ForYouAdapter.ReceivedRequestsViewHolder myViewHolder = (ForYouAdapter.ReceivedRequestsViewHolder) holder;
            myViewHolder.myText1.setText(allItems.get(position));
        } else if (holder instanceof ForYouAdapter.ByChoiceViewHolder) {
            ForYouAdapter.ByChoiceViewHolder myViewHolder = (ForYouAdapter.ByChoiceViewHolder) holder;
            myViewHolder.myText1.setText(allItems.get(position));
        } else if (holder instanceof ForYouAdapter.LoadingViewHolder) {
            ForYouAdapter.LoadingViewHolder loadingViewHolder = (ForYouAdapter.LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

        //check what number the position is and call retrofit
    }

    @Override
    public int getItemCount() {
        return  allItems == null ? 0 : allItems.size();
    }


    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public class PhoneNotificationViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;

        public PhoneNotificationViewHolder(@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView3);
        }
    }

    public class MatchedCouplesTitleViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;

        public MatchedCouplesTitleViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    public class MatchedCouplesViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;

        public MatchedCouplesViewHolder(@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView3);
        }
    }

    public class ReceivedRequestsTitleViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;

        public ReceivedRequestsTitleViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
    
    public class ReceivedRequestsViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;

        public ReceivedRequestsViewHolder(@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView3);
        }
    }

    public class ByChoiceTitleViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;

        public ByChoiceTitleViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    public class ByChoiceViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;

        public ByChoiceViewHolder(@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView3);
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