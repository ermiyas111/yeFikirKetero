package com.aden.yefikirketero.UI;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.options.AboutUs;
import com.aden.yefikirketero.UI.options.ReceivedPhoneNumbers;
import com.aden.yefikirketero.UI.profile.PrepareMyPost;
import com.aden.yefikirketero.UI.profile.RecordAudio;
import com.aden.yefikirketero.UI.profile.ShowTheWayToProfile;
import com.aden.yefikirketero.UI.tabFragments.postedTab.PostsAdapter;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.aden.yefikirketero.retrofit.model.Post;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LauncherActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PostsAdapter postsAdapter;
    YeFikirKeteroApi api;
    FloatingActionButton myFab;
    Context context = this;
    LinearProgressIndicator progressIndicator;
    MaterialToolbar topAppBar;
    SearchView searchView;
    EditText searchEditText;

    private final int oneRoundLoadItems = 6;
    int maxRoundNumber = 3;

    //String[] s1 = new String[9];
    //String[] s2 = new String[9];
    //use array list instead of String[]
    List<String> s1 = new ArrayList<>();
    List<String> s2 = new ArrayList<>();
    List<String> s3 = new ArrayList<>();

    /*TabLayout tabLayout;
    ViewPager viewPager;

    TabItem postedProfilesTab;
    TextView selectedTabText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        postedProfilesTab = findViewById(R.id.postedProfilesTab);

        tabLayout.setTabTextColors(Color.rgb(180, 180, 195), Color.rgb(255, 255, 255));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final LauncherTabsAdapter adapter = new LauncherTabsAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_posted_profiles_tab);

        progressIndicator = findViewById(R.id.progressIndicator);
        recyclerView = findViewById(R.id.recycler_view_posted_profiles);
        topAppBar = findViewById(R.id.top_app_bar);

        //to make on create options menu called
        setSupportActionBar(topAppBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        postsAdapter = new PostsAdapter(recyclerView, context, s1, s2, s3);
        recyclerView.setAdapter(postsAdapter);

        myFab = findViewById(R.id.fab);

        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, PrepareMyPost.class);
                //Intent intent = new Intent(context, RecordAudio.class);
                startActivity(intent);
            }
        });

        //retrofit method call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YeFikirKeteroApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(YeFikirKeteroApi.class);

        //start the back and forth of two methods
        fetchFromRetrofit();

        //handle options button click
        topAppBar.setOnMenuItemClickListener (new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.received_phone_numbers:
                        Intent intent = new Intent(context, ReceivedPhoneNumbers.class);
                        startActivity(intent);
                        break;
                    case R.id.about_us:
                        Intent intent2 = new Intent(context, AboutUs.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }

    //filter method for search functionality
    void filter(String text){
        List<String> temp1 = new ArrayList<>();
        //List<String> temp2 = new ArrayList<>();
        //List<String> temp3 = new ArrayList<>();
        for(String d: s1){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d != null) {
                if (d.contains(text)) {
                    temp1.add(d);
                }
            }
        }
//        for(String d: s2){
//            //or use .equal(text) with you want equal match
//            //use .toLowerCase() for better matches
//            if(!d.equals(null)) {
//                if (d.contains(text)) {
//                    temp2.add(d);
//                }
//            }
//        }
//        for(String d: s3){
//            //or use .equal(text) with you want equal match
//            //use .toLowerCase() for better matches
//            if(!d.equals(null)) {
//                if (d.contains(text)) {
//                    temp3.add(d);
//                }
//            }
//        }
        //update recyclerview
        updateList(temp1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);

        MenuItem searchViewItem = menu.findItem(R.id.search);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) searchViewItem.getActionView();
        searchEditText = (EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.whiteColor));
        searchEditText.setHintTextColor(getResources().getColor(R.color.whiteColor));
        searchView.setQueryHint("Search for Product,Brands...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setSubmitButtonEnabled(true);

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered
                Toast.makeText(getApplicationContext(),"textChanged :"+newText,Toast.LENGTH_LONG).show();

                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                // **Here you can get the value "query" which is entered in the search box.**

                //Toast.makeText(getApplicationContext(),"searchvalue :"+query,Toast.LENGTH_LONG).show();

                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);


        //when inputting search text
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });

        return true;
    }

    private void fetchFromRetrofit(){
        Call<List<Post>> call = api.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressIndicator.setVisibility(GONE);
                myFab.setVisibility(VISIBLE);

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
                postsAdapter.notifyDataSetChanged();
                postsAdapter.setLoaded();

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

        postsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (s1.size() <= 28) {
                    s1.add(null);
                    postsAdapter.notifyItemInserted(s1.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            s1.remove(s1.size() - 1);
                            postsAdapter.notifyItemRemoved(s1.size());

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

    public void updateList(List<String> list1){
        //clear lists
        s1.clear();
//        s2.clear();
//        s3.clear();

        //replace with new given lists
        s1 = list1;
//        s2 = list2;
//        s3 = list3;

        postsAdapter.notifyDataSetChanged();
    }
}
