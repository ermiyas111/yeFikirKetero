package com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.profile.preparePostTabFragments.AboutYouForm;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConfirmPayment extends Fragment {
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


    public ConfirmPayment() {
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
    public static ConfirmPayment newInstance(String param1, String param2) {
        ConfirmPayment fragment = new ConfirmPayment();
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
        return inflater.inflate(R.layout.fragment_confirm_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
