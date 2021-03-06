package com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.getPhone.PaymentActivity;
import com.aden.yefikirketero.UI.profile.preparePostTabFragments.AboutYouForm;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InputPhone extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MaterialButton nextButton;
    TextInputEditText phoneEditText;
    TextView telegramContact, phoneContact;

    PaymentActivity paymentActivity = new PaymentActivity();


    int oneRoundItems = 0;

    Context context;


    public InputPhone() {
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
    public static InputPhone newInstance(String param1, String param2) {
        InputPhone fragment = new InputPhone();
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
        return inflater.inflate(R.layout.fragment_input_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nextButton = view.findViewById(R.id.next_button);
        phoneEditText= view.findViewById(R.id.phone_edit_text);
        telegramContact= view.findViewById(R.id.telegram_contact);
        phoneContact= view.findViewById(R.id.phone_contact);

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isValid = true;
                if(phoneEditText.getText().toString().isEmpty()) {
                    phoneEditText.setError(getResources().getString(R.string.name_validation_error));
                    isValid = false;
                } else {
                    phoneEditText.setError(null); // Clear the error
                }

                if(isValid){
                    //set phone number
                    paymentActivity.setUserPhoneNumber(phoneEditText.getText().toString());

                    //navigate to next fragment
                    TabLayout tabs = getActivity().findViewById(R.id.tabLayout);
                    tabs.getTabAt(1).select();
                }
            }
        });

        telegramContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://telegram.me/yefikerketerohelperbot"));
                startActivity(telegram);
            }
        });

        phoneContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.phone_contact)));
                startActivity(intent);
            }
        });

    }

}
