package com.aden.yefikirketero.accessibilityService;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import com.aden.yefikirketero.UI.getPhone.DescribePaymentOptions;

import java.util.List;


public class ResponseAccessibilityService extends AccessibilityService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private String USSDText;

    public void onAccessibilityEvent(AccessibilityEvent event) {

        List<CharSequence> response = event.getText();
        USSDText = processUSSDText(response);

        Log.d("onAccessibilityEvent: ", event.getPackageName().toString() + " " + event.getClassName().toString().toLowerCase());
        Log.d("onAccessibilityEvent: ", USSDText);

        if (event.getPackageName().toString().contains("com.android.phone")
                && event.getClassName().toString().toLowerCase()
                .contains("alert")
                && DescribePaymentOptions.getAccessibilityPortalOpen()) {//make sure same message isn't sent twice and the e

            if (USSDText.contains("Dear customer") && USSDText.contains("your operation was successful")) {
                Log.d("0nd step: ", "first step");
                Toast.makeText(ResponseAccessibilityService.this, "first step", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onInterrupt() {
    }

    @Override
    protected void onServiceConnected() {
        Log.d("onAccessibilityEvent: ", "Just checking");
        super.onServiceConnected();
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.packageNames = new String[]{"com.android.phone"};
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);
    }

    private String processUSSDText(List<CharSequence> eventText){
        for(CharSequence s: eventText){
            String text = String.valueOf(s);
            return text;
        }
        return null;
    }
}
