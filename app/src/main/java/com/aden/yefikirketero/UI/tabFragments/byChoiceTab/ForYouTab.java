package com.aden.yefikirketero.UI.tabFragments.byChoiceTab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.LauncherActivity;
import com.aden.yefikirketero.UI.OnLoadMoreListener;
import com.aden.yefikirketero.UI.profile.BioQuestion;
import com.aden.yefikirketero.UI.profile.PhoneVerification;
import com.aden.yefikirketero.UI.profile.ShowTheWayToProfile;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.aden.yefikirketero.retrofit.model.Person;
import com.aden.yefikirketero.retrofit.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForYouTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForYouTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ForYouAdapter forYouAdapter;
    YeFikirKeteroApi api;

    LinearLayout layout1,layout2, layout3;
    Button continueButton;

    int oneRoundItems = 0;

    Context context;

    final String notificationString = "called by notification";
    final String matchedCouplesString = "called by matched couples";
    final String receivedRequestsString = "called by received requests";
    final String byChoiceString = "called by byChoice";

    final int oneFullRound = 15;

    //use array list instead of String[]
    List<String> phoneNumberNotifications = new ArrayList<>();
    List<String> matchedCouples = new ArrayList<>();
    List<String> receivedRequests = new ArrayList<>();
    List<String> byChoice = new ArrayList<>();

    private List<String> allItems = new ArrayList<>();

    List<Integer> forYouTabClassificationIndexes = new ArrayList<>();


    public ForYouTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cricket.
     */
    // TODO: Rename and change types and number of parameters
    public static ForYouTab newInstance(String param1, String param2) {
        ForYouTab fragment = new ForYouTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_for_you, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout1 = view.findViewById(R.id.Layout1);
        layout2 = view.findViewById(R.id.Layout2);
        layout3 = view.findViewById(R.id.Layout3);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("LoggedIn")) {
            Boolean isAccountLoggedIn = sharedPreferences.getBoolean("LoggedIn", false);
            if (isAccountLoggedIn) {
                layout3.setVisibility(LinearLayout.GONE);
                layout1.setVisibility(LinearLayout.VISIBLE);
            } else {
                layout1.setVisibility(LinearLayout.GONE);
                layout3.setVisibility(LinearLayout.VISIBLE);
            }
        }else{
            layout1.setVisibility(LinearLayout.GONE);
            layout3.setVisibility(LinearLayout.VISIBLE);
        }

        continueButton = view.findViewById(R.id.button_continue_for_you_tab);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(context, DateLocationQuestion.class);
                Intent intent = new Intent(context, PhoneVerification.class);
                startActivity(intent);
            }
        });

        /*new Handler().po
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                s1.remove(s1.size() - 1);
                postsAdapter.notifyItemRemoved(s1.size());

                //Generating more data
                fetchFromRetrofit();
            }
        }, 5000);*/



        context = getActivity();
        recyclerView = view.findViewById(R.id.recycler_view_for_you);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        forYouAdapter = new ForYouAdapter(recyclerView, context, allItems, phoneNumberNotifications, matchedCouples, receivedRequests, byChoice, forYouTabClassificationIndexes);
        recyclerView.setAdapter(forYouAdapter);

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
        fetchReceivedPhoneNotifications();
    }

    private void fetchReceivedPhoneNotifications(){
//        int calculatedLimit = oneFullRound - oneRoundItems;
//        Call<List<Person>> call = api.getPhoneNotifications(calculatedLimit);
//        call.enqueue(new Callback<List<Person>>() {
//            @Override
//            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
//                List<Person> phoneNotifications = response.body();
//
//                int count =0;
//
//                List<Post> users = phoneNotifications.get(0).getPhoneNotifications();
//                for(Post n: users){
//                    addItemsToLists(n.getName());
//                    //Log.d("phoneNumber", p.getPhoneNumber());
//                    //Log.d("name", p.getName());
//                    //Log.d("age", p.getAge());
//                    count++;
//                    oneRoundItems++;
//                }
//                checkIndexesArrayNotNullAndAddOtherIndexValue(count, 0);
//                forYouAdapter.notifyDataSetChanged();
//                //forYouAdapter.setLoaded();
//
//                if(oneRoundItems >=15){
//                    //loadMoreListen(notificationString);
//                }else{
//                    //fetchMatchedCouples();
//                    fetchReceivedRequests();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Person>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void fetchMatchedCouples(){
//        Call<List<Post>> call = api.getPosts();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                List<Post> posts = response.body();
//
//                int count =0;
//
//                for(Post p: posts){
//                    if(count == 0){
//                        allItems.add("matched_couples_title");
//                    }
//                    matchedCouples.add(p.getBio());
//                    allItems.add(p.getBio());
//                    Log.d("phoneNumber", p.getPhone());
//                    Log.d("name", p.getName());
////                    Log.d("age", p.getAge());
//                    count++;
//                    oneRoundItems++;
//                }
//                forYouAdapter.notifyDataSetChanged();
//                forYouAdapter.setLoaded();
//
//                if(oneRoundItems >=15){
//                    loadMoreListen(matchedCouplesString);
//                }else{
//                    //fetchReceivedRequests();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void fetchReceivedRequests(){
//        int calculatedLimit = oneFullRound - oneRoundItems;
//        Call<List<Person>> call = api.getReceivedRequests(calculatedLimit);
//        call.enqueue(new Callback<List<Person>>() {
//            @Override
//            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
//                List<Person> receivedLikes = response.body();
//
//                int count =0;
//
//                List<Post> users = receivedLikes.get(0).getLikedBy();
//                for(Post r: users){
//                    if(count == 0){
//                        allItems.add("received_requests_title");
//                    }
//                    addItemsToLists(r.getBio());
//                    //Log.d("phoneNumber", p.getPhoneNumber());
//                    //Log.d("name", p.getName());
//                    //Log.d("age", p.getAge());
//                    count++;
//                    oneRoundItems++;
//                }
//                checkIndexesArrayNotNullAndAddOtherIndexValue(count, 1);
//                forYouAdapter.notifyDataSetChanged();
//                forYouAdapter.setLoaded();
//
//                if(oneRoundItems >=15){
//                    //loadMoreListen(receivedRequestsString);
//                }else{
//                    fetchByChoice();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Person>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void fetchByChoice(){
//        Call<List<Post>> call = api.getPosts();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                List<Post> posts = response.body();
//
//                int count =0;
//
//                for(Post p: posts){
//                    if(count == 0){
//                        allItems.add("by_choice_title");
//                    }
//                    byChoice.add(p.getBio());
//                    allItems.add(p.getBio());
//                    Log.d("phoneNumber", p.getPhone());
//                    Log.d("name", p.getName());
////                    Log.d("age", p.getAge());
//                    count++;
//                    oneRoundItems++;
//                }
//                forYouAdapter.notifyDataSetChanged();
//                forYouAdapter.setLoaded();
//
//                loadMoreListen(byChoiceString);
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void loadMoreListen(String calledBy){

        //built in methods
        //postsAdapter.notifyDataSetChanged();

        forYouAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (allItems.size() <= 60) {
                    allItems.add(null);
                    forYouAdapter.notifyItemInserted(allItems.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            allItems.remove(allItems.size() - 1);
                            forYouAdapter.notifyItemRemoved(allItems.size());

                            //Generating more data
                            oneRoundItems = 0;
                            if(calledBy.equals(notificationString)){
                                fetchReceivedPhoneNotifications();
                            }else if(calledBy.equals(matchedCouplesString)){
                                fetchMatchedCouples();
                            }else if(calledBy.equals(receivedRequestsString)){
                                fetchReceivedPhoneNotifications();
                            }else if(calledBy.equals(byChoiceString)){
                                fetchByChoice();
                            }
                        }
                    }, 5000);
                } else {
                    Toast.makeText(context, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addItemsToLists(String itemToAdd){
        phoneNumberNotifications.add(itemToAdd);
        allItems.add(itemToAdd);
    }


    private boolean checkIndexesArrayNotNull(int i){
        try {
            forYouTabClassificationIndexes.get(i);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void checkIndexesArrayNotNullAndAddOtherIndexValue(int newValue, int j){
        int i = j;
        if(!checkIndexesArrayNotNull(i)){
            while(i >= 0 && checkIndexesArrayNotNull(i)) {
                i--;
                newValue = newValue + forYouTabClassificationIndexes.get(i);
            }
        }else{
            newValue = newValue + forYouTabClassificationIndexes.get(i);
        }

        if(checkIndexesArrayNotNull(j)) {
            forYouTabClassificationIndexes.set(j, newValue);
        }else{
            forYouTabClassificationIndexes.add(newValue);
        }
    }

}
