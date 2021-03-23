package com.aden.yefikirketero.UI.profile.preparePostTabFragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.tabFragments.byChoiceTab.ForYouAdapter;
import com.aden.yefikirketero.UI.tabFragments.byChoiceTab.ForYouTab;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class AboutYouForm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextInputLayout genderTextInput;
    AutoCompleteTextView genderAutocomplete;

    TextInputLayout locationTextInput;
    AutoCompleteTextView locationAutocomplete;

    TextInputLayout religionTextInput;
    AutoCompleteTextView religionAutocomplete;

    TextInputLayout heightTextInput;
    AutoCompleteTextView heightAutocomplete;

    ArrayList<String> genderList;
    ArrayAdapter<String> genderAdapter;

    ArrayList<String> locationList;
    ArrayAdapter<String> locationAdapter;

    ArrayList<String> religionList;
    ArrayAdapter<String> religionAdapter;

    ArrayList<String> heightList;
    ArrayAdapter<String> heightAdapter;

    MaterialButton cancelButton, nextButton;
    TextInputEditText nameEditText, ageEditText, phoneEditText, jobEditText;


    int oneRoundItems = 0;

    Context context;


    public AboutYouForm() {
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
    public static AboutYouForm newInstance(String param1, String param2) {
        AboutYouForm fragment = new AboutYouForm();
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
        return inflater.inflate(R.layout.fragment_about_you, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cancelButton = view.findViewById(R.id.canceling_button);
        nextButton = view.findViewById(R.id.next_button);

        nameEditText= view.findViewById(R.id.name_edit_text);
        ageEditText= view.findViewById(R.id.age_edit_text);
        phoneEditText= view.findViewById(R.id.phone_edit_text);
        jobEditText= view.findViewById(R.id.job_edit_text);

        genderTextInput = view.findViewById(R.id.gender_text_input);
        genderAutocomplete = view.findViewById(R.id.gender_autocomplete);

        locationTextInput = view.findViewById(R.id.location_text_input);
        locationAutocomplete = view.findViewById(R.id.location_autocomplete);

        religionTextInput = view.findViewById(R.id.religion_text_input);
        religionAutocomplete = view.findViewById(R.id.religion_autocomplete);

        heightTextInput = view.findViewById(R.id.height_text_input);
        heightAutocomplete = view.findViewById(R.id.height_autocomplete);

        genderList = new ArrayList<>();
        genderList.add(getResources().getString(R.string.male));
        genderList.add(getResources().getString(R.string.female));

        locationList = new ArrayList<>();
        locationList.add(getResources().getString(R.string.male));
        locationList.add(getResources().getString(R.string.female));

        religionList = new ArrayList<>();
        religionList.add(getResources().getString(R.string.orthodox));
        religionList.add(getResources().getString(R.string.muslim));
        religionList.add(getResources().getString(R.string.protestant));

        heightList = new ArrayList<>();
        heightList.add(getResources().getString(R.string.shortish));
        heightList.add(getResources().getString(R.string.medium));
        heightList.add(getResources().getString(R.string.tall));

        genderAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, genderList);
        genderAutocomplete.setAdapter(genderAdapter);

        locationAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, locationList);
        locationAutocomplete.setAdapter(locationAdapter);

        religionAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, religionList);
        religionAutocomplete.setAdapter(religionAdapter);

        heightAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, heightList);
        heightAutocomplete.setAdapter(heightAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isValid = true;
                if(nameEditText.getText().toString().isEmpty()) {
                    nameEditText.setError(getResources().getString(R.string.name_validation_error));
                    isValid = false;
                } else {
                    nameEditText.setError(null); // Clear the error
                }

                if(ageEditText.getText().toString().isEmpty()) {
                    ageEditText.setError(getResources().getString(R.string.age_validation_error));
                    isValid = false;
                } else {
                    ageEditText.setError(null); // Clear the error
                }

                if(genderAutocomplete.getText().toString().isEmpty()) {
                    genderAutocomplete.setError(getResources().getString(R.string.gender_validation_error));
                    isValid = false;
                } else {
                    genderAutocomplete.setError(null); // Clear the error
                }

                if(phoneEditText.getText().toString().isEmpty()) {
                    phoneEditText.setError(getResources().getString(R.string.phone_validation_error));
                    isValid = false;
                } else {
                    phoneEditText.setError(null); // Clear the error
                }

                if(isValid){
                    TabLayout tabs = getActivity().findViewById(R.id.tabLayout);
                    tabs.getTabAt(1).select();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
}
