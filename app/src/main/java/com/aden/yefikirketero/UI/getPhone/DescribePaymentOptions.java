package com.aden.yefikirketero.UI.getPhone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.LauncherActivity;
import com.aden.yefikirketero.accessibilityService.ResponseAccessibilityService;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DescribePaymentOptions extends AppCompatActivity {

    Button insideCountryButton, outsideCountryButton;
    Context context = this;
    private static boolean accessibilityPortalOpen=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe_payment_options);

        insideCountryButton = findViewById(R.id.buttonInsideCountry);
        insideCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessibilityPortalOpen = true;
                Intent intent = new Intent("android.intent.action.CALL",
                        Uri.parse("tel:" + "*806*0904132755*25" + Uri.encode("#")));
                startActivity(intent);
            }
        });

        outsideCountryButton = findViewById(R.id.buttonOutsideCountry);
        outsideCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LauncherActivity.class);
                startActivity(intent);
            }
        });
    }

    // To check if service is enabled
    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        final String service = getPackageName() + "/" + ResponseAccessibilityService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Log.v("dfs", "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e("gb", "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Log.v("dgv", "***ACCESSIBILITY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    Log.v("fdv", "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        Log.v("klk", "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Log.v("yui", "***ACCESSIBILITY IS DISABLED***");
        }

        return false;
    }

    public static boolean getAccessibilityPortalOpen(){
        return accessibilityPortalOpen;
    }
    public static void setAccessibilityPortalOpen(boolean accessibilityState){
        accessibilityPortalOpen=accessibilityState;
    }
}