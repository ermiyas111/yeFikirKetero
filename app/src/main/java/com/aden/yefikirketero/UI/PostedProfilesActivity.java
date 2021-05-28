package com.aden.yefikirketero.UI;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.tabFragments.postedTab.PostsAdapter;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.aden.yefikirketero.retrofit.model.Post;

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

public class PostedProfilesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PostsAdapter myAdapter;
    YeFikirKeteroApi api;

    Context context = this;

    private final int oneRoundLoadItems = 6;
    int maxRoundNumber = 3;

    //String[] s1 = new String[9];
    //String[] s2 = new String[9];
    //use array list instead of String[]
    List<String> s1 = new ArrayList<>();
    List<Long> s2 = new ArrayList<>();
    List<String> s3 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_profiles_tab);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        myAdapter = new PostsAdapter(recyclerView, context, s1, s2, s3);
        recyclerView.setAdapter(myAdapter);

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
                List<Post> posts = response.body();

                int i =0;

                for(Post p: posts){
                    s1.add(p.getName());
                    s2.add(p.getPhoneNumber());
//                    Log.d("phoneNumber", p.getPhoneNumber());
                    Log.d("name", p.getName());
//                    Log.d("age", p.getAge());
                    i++;
                }
                myAdapter.notifyDataSetChanged();
                myAdapter.setLoaded();

                loadMoreListen();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMoreListen(){

        //built in methods
        //myAdapter.notifyDataSetChanged();

        myAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (s1.size() <= 28) {
                    s1.add(null);
                    s2.add(null);
                    myAdapter.notifyItemInserted(s1.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            s1.remove(s1.size() - 1);
                            s2.remove(s2.size() - 1);
                            myAdapter.notifyItemRemoved(s1.size());

                            //Generating more data
                            fetchFromRetrofit();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(PostedProfilesActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
