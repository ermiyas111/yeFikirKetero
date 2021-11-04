package com.aden.yefikirketero.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostWrapper {
    public PostWrapper(int total, int skip, int limit, List<Post> data ){
        this.total = total;
        this.skip = skip;
        this.limit = limit;
        this.data = data;
    }

    @SerializedName("total")
    private int total;

    @SerializedName("skip")
    private int skip;

    @SerializedName("limit")
    private int limit;

    @SerializedName("data")
    private List<Post> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Post> getData() {
        return data;
    }

    public void setData(List<Post> data) {
        this.data = data;
    }
}
