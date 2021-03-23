package com.aden.yefikirketero.UI.tabFragments.postedTab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.OnLoadMoreListener;
import com.aden.yefikirketero.UI.profile.PhoneVerification;
import com.aden.yefikirketero.UI.profile.ShowTheWayToProfile;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.aden.yefikirketero.retrofit.model.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostedProfilesTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostedProfilesTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    RecyclerView recyclerView;
    PostsAdapter postsAdapter;
    YeFikirKeteroApi api;
    FloatingActionButton myFab;
    LinearProgressIndicator progressIndicator;

    Context context;

    private final int oneRoundLoadItems = 6;
    int maxRoundNumber = 3;

    //String[] s1 = new String[9];
    //String[] s2 = new String[9];
    //use array list instead of String[]
    List<String> s1 = new ArrayList<>();
    List<String> s2 = new ArrayList<>();
    List<String> s3 = new ArrayList<>();

    public PostedProfilesTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Football.
     */
    // TODO: Rename and change types and number of parameters
    public static PostedProfilesTab newInstance(String param1, String param2) {
        PostedProfilesTab fragment = new PostedProfilesTab();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posted_profiles_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressIndicator = view.findViewById(R.id.progressIndicator);
        context = getActivity();
        recyclerView = view.findViewById(R.id.recycler_view_posted_profiles);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        postsAdapter = new PostsAdapter(recyclerView, context, s1, s2, s3);
        recyclerView.setAdapter(postsAdapter);

        myFab = view.findViewById(R.id.fab);
        myFab.setVisibility(GONE);

        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowTheWayToProfile.class);
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
                progressIndicator.setVisibility(GONE);
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
}
