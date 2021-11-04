package com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.profile.preparePostTabFragments.AboutYouForm;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MakePayment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MaterialButton sendMoneyButton;
    TextView telegramContact, phoneContact;
    static boolean showPhoneDialog = false;
    static boolean accessibilityPortalOpen = false;

    private ClipboardManager myClipboard;
    private ClipData myClip;



    int oneRoundItems = 0;

    Context context;


    public MakePayment() {
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
    public static MakePayment newInstance(String param1, String param2) {
        MakePayment fragment = new MakePayment();
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
        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();

        if (showPhoneDialog) {
            AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity())
                    .setTitle(getResources().getString(R.string.payment_completed))
                    .setMessage(getResources().getString(R.string.the_phone_number) + " " + b.getString("currentPhone"));

            builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.copy_phone), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    myClipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                    String text = b.getString("currentPhone");

                    myClip = ClipData.newPlainText("text", text);
                    myClipboard.setPrimaryClip(myClip);

                    Toast.makeText(getActivity(), "Phone Copied",Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

            ArrayList<String> phoneList = getArrayList("phoneList");

            if(phoneList == null){
                phoneList = new ArrayList<>();
            }
            phoneList.add(b.getString("currentPhone"));
            saveArrayList(phoneList, "phoneList");
            showPhoneDialog = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sendMoneyButton = view.findViewById(R.id.send_money_button);
        telegramContact= view.findViewById(R.id.telegram_contact);
        phoneContact= view.findViewById(R.id.phone_contact);

        sendMoneyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int accessibilityEnabled = 0;
                try {
                    accessibilityEnabled = Settings.Secure.getInt(getActivity().getContentResolver(),android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
                    if(accessibilityEnabled == 0) {
                        //navigate to next fragment
                        TabLayout tabs = getActivity().findViewById(R.id.tabLayout);
                        tabs.getTabAt(0).select();
                    } else{
                        if (checkCallingPermission()) {
                            accessibilityPortalOpen = true;
                            startActivity(new Intent("android.intent.action.CALL",
                                    Uri.parse("tel:" + getResources().getString(R.string.transfer_command) + Uri.encode("#"))));
                        }
                    }
                } catch (Settings.SettingNotFoundException e) {
                    Log.d("LOGTAG", "Error finding setting, default accessibility to not found: " + e.getMessage());
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

    //check if permission is granted or not
    public boolean checkCallingPermission(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 5);
            return false;
        }else{
            return true;
        }
    }


    public static void setShowPhoneDialog(boolean showPhoneDialogParam) {
        showPhoneDialog = showPhoneDialogParam;
    }

    public static boolean isAccessibilityPortalOpen() {
        return accessibilityPortalOpen;
    }

    public static void setAccessibilityPortalOpen(boolean accessibilityPortalOpen) {
        MakePayment.accessibilityPortalOpen = accessibilityPortalOpen;
    }

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
