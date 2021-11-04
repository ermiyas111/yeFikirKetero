package com.aden.yefikirketero.UI.profile.preparePostTabFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.LauncherActivity;
import com.aden.yefikirketero.UI.profile.PrepareMyPost;
import com.aden.yefikirketero.UI.tabFragments.byChoiceTab.ForYouAdapter;
import com.aden.yefikirketero.UI.tabFragments.byChoiceTab.ForYouTab;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.aden.yefikirketero.retrofit.model.Post;
import com.aden.yefikirketero.retrofit.model.PostUpload;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AboutDateForm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    YeFikirKeteroApi api;

    TextInputLayout religionTextInput;
    AutoCompleteTextView religionAutocomplete;

    TextInputLayout heightTextInput;
    AutoCompleteTextView heightAutocomplete;

    ArrayList<String> religionList;
    ArrayAdapter<String> religionAdapter;

    ArrayList<String> heightList;
    ArrayAdapter<String> heightAdapter;

    MaterialButton cancelButton, nextButton;
    TextInputEditText ageEditTextFrom, ageEditTextTo, jobEditText;


    int oneRoundItems = 0;

    Context context;


    public AboutDateForm() {
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
    public static AboutDateForm newInstance(String param1, String param2) {
        AboutDateForm fragment = new AboutDateForm();
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
        return inflater.inflate(R.layout.fragment_about_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cancelButton = view.findViewById(R.id.canceling_button);
        nextButton = view.findViewById(R.id.next_button);

        ageEditTextFrom= view.findViewById(R.id.age_edit_text_from);
        ageEditTextTo= view.findViewById(R.id.age_edit_text_to);
        jobEditText= view.findViewById(R.id.job_edit_text);

        religionTextInput = view.findViewById(R.id.religion_text_input);
        religionAutocomplete = view.findViewById(R.id.religion_autocomplete);

        heightTextInput = view.findViewById(R.id.height_text_input);
        heightAutocomplete = view.findViewById(R.id.height_autocomplete);

        religionList = new ArrayList<>();
        religionList.add(getResources().getString(R.string.orthodox));
        religionList.add(getResources().getString(R.string.muslim));
        religionList.add(getResources().getString(R.string.protestant));
        religionList.add(getResources().getString(R.string.no_problem));

        heightList = new ArrayList<>();
        heightList.add(getResources().getString(R.string.shortish));
        heightList.add(getResources().getString(R.string.medium));
        heightList.add(getResources().getString(R.string.tall));
        heightList.add(getResources().getString(R.string.no_problem));

        religionAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, religionList);
        religionAutocomplete.setAdapter(religionAdapter);

        heightAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, heightList);
        heightAutocomplete.setAdapter(heightAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isValid = true;
                if(ageEditTextFrom.getText().toString().isEmpty()) {
                    ageEditTextFrom.setError(getResources().getString(R.string.age_validation_error));
                    isValid = false;
                } else {
                    ageEditTextFrom.setError(null); // Clear the error
                }

                if(ageEditTextTo.getText().toString().isEmpty()) {
                    ageEditTextTo.setError(getResources().getString(R.string.age_validation_error));
                    isValid = false;
                } else {
                    ageEditTextTo.setError(null); // Clear the error
                }

                if(isValid){
                    //handle web request here
                    //retrofit method call
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(YeFikirKeteroApi.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    api = retrofit.create(YeFikirKeteroApi.class);

                    postUserData();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TabLayout tabs = getActivity().findViewById(R.id.tabLayout);
                tabs.getTabAt(0).select();
            }
        });
    }

    private void postUserData(){
        Intent intent = new Intent(getActivity(), LauncherActivity.class);
        //Intent intent = new Intent(context, RecordAudio.class);
        ArrayList<String> aboutYou = new ArrayList<>();
        aboutYou = getArrayList("aboutYou");
        startActivity(intent);
        Post post = new Post(
                aboutYou.get(0),
                Integer.valueOf(aboutYou.get(1)),
                aboutYou.get(2),
                aboutYou.get(3),
                aboutYou.get(4),
                aboutYou.get(5),
                aboutYou.get(6),
                aboutYou.get(7),
                aboutYou.get(8),
                Integer.valueOf(ageEditTextFrom.getText().toString()),
                Integer.valueOf(ageEditTextTo.getText().toString()),
                religionAutocomplete.getText().toString(),
                heightAutocomplete.getText().toString(),
                jobEditText.getText().toString(),
                null
        );
        Call<Post> call = api.uploadPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(getActivity(),"You have successfully posted your profile",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getActivity(),"Posting profile unsuccessful",Toast.LENGTH_LONG).show();
            }
        });
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
