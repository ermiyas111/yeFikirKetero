package com.aden.yefikirketero.UI.options;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.OnLoadMoreListener;
import com.aden.yefikirketero.UI.tabFragments.postedTab.PostsAdapter;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.aden.yefikirketero.retrofit.model.Post;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ReceivedPhoneNumbers extends AppCompatActivity {

    RecyclerView recyclerView;
    PhoneListAdapter phoneListAdapter;
    YeFikirKeteroApi api;
    Context context = this;
    LinearProgressIndicator progressIndicator;

    //use array list instead of String[]
    List<String> s1 = new ArrayList<>();
    List<String> s2 = new ArrayList<>();
    List<String> s3 = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_phone_numbers);

        progressIndicator = findViewById(R.id.progressIndicator);
        recyclerView = findViewById(R.id.recycler_view_phone_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        phoneListAdapter = new PhoneListAdapter(recyclerView, context, s1, s2, s3);
        recyclerView.setAdapter(phoneListAdapter);

        //retrofit method call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YeFikirKeteroApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(YeFikirKeteroApi.class);

        //start the back and forth of two methods
        fetchFromRetrofit();
    }

    private void fetchFromRetrofit(){
        Call<List<Post>> call = api.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressIndicator.setVisibility(GONE);

                List<Post> posts = response.body();

                int i =0;

                for(Post p: posts){
                    s1.add(p.getBio());
                    s2.add(p.getPhoneNumber());
                    s3.add(p.getUserId());
                    Log.d("phoneNumber", p.getPhoneNumber());
                    Log.d("name", p.getName());
                    Log.d("age", p.getAge());
                    i++;
                }
                phoneListAdapter.notifyDataSetChanged();
                phoneListAdapter.setLoaded();

                loadMoreListen();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressIndicator.setVisibility(GONE);
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context)
                        .setMessage(getResources().getString(R.string.connection_error));
                materialAlertDialogBuilder.setNeutralButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                });
                materialAlertDialogBuilder.setPositiveButton(getResources().getString(R.string.retry), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        progressIndicator.setVisibility(VISIBLE);
                        fetchFromRetrofit();
                    }
                });
                materialAlertDialogBuilder.setCancelable(false);
                materialAlertDialogBuilder.show();
            }
        });
    }

    private void loadMoreListen(){

        //built in methods
        //postsAdapter.notifyDataSetChanged();

        phoneListAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (s1.size() <= 28) {
                    s1.add(null);
                    phoneListAdapter.notifyItemInserted(s1.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            s1.remove(s1.size() - 1);
                            phoneListAdapter.notifyItemRemoved(s1.size());

                            //Generating more data
                            fetchFromRetrofit();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(context, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
