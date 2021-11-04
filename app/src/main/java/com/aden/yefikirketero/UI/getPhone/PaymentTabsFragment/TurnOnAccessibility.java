package com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.getPhone.PaymentActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class TurnOnAccessibility extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MaterialButton nextButton;
    SwitchMaterial accessibilityToggle;
    TextView showSteps, telegramContact, phoneContact;

    PaymentActivity paymentActivity = new PaymentActivity();


    int oneRoundItems = 0;

    Context context;


    public TurnOnAccessibility() {
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
    public static TurnOnAccessibility newInstance(String param1, String param2) {
        TurnOnAccessibility fragment = new TurnOnAccessibility();
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
    public void onResume() {
        super.onResume();
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(getActivity().getContentResolver(),android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            accessibilityToggle.setChecked((accessibilityEnabled == 0)? false : true);
        } catch (Settings.SettingNotFoundException e) {
            Log.d("LOGTAG", "Error finding setting, default accessibility to not found: " + e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accessibility_turn_on, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nextButton = view.findViewById(R.id.next_button);
        accessibilityToggle = view.findViewById(R.id.accesibility_toggle);
        showSteps= view.findViewById(R.id.accessibility_steps);
        telegramContact= view.findViewById(R.id.telegram_contact);
        phoneContact= view.findViewById(R.id.phone_contact);

        accessibilityToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });

        showSteps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity())
                    .setTitle("How to turn on accessibility service?")
                    .setMessage("1. Go to settings>accessiblity.\n" +
                            "2. Click on 'installed services'.\n" +
                            "3. From the list of apps click on 'የፍቅር ቀጠሮ'\n" +
                            "4. Turn on the switch."
                    );
                builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton(getResources().getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        startActivity(intent);
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isValid = true;

                if(isValid){
                    int accessibilityEnabled = 0;
                    try {
                        accessibilityEnabled = Settings.Secure.getInt(getActivity().getContentResolver(),android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
                        if(accessibilityEnabled == 0) {
                            AlertDialog alertDialog = new MaterialAlertDialogBuilder(getActivity())
                                    .setMessage(getResources().getString(R.string.turn_on_accessibility))
                                    .setPositiveButton("Ok", null)
                                    .show();
                        } else{
                            //navigate to next fragment
                            TabLayout tabs = getActivity().findViewById(R.id.tabLayout);
                            tabs.getTabAt(1).select();
                        }
                    } catch (Settings.SettingNotFoundException e) {
                        Log.d("LOGTAG", "Error finding setting, default accessibility to not found: " + e.getMessage());
                    }
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
