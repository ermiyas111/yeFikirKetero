package com.aden.yefikirketero.accessibilityService;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import com.aden.yefikirketero.UI.getPhone.DescribePaymentOptions;
import com.aden.yefikirketero.UI.getPhone.PaymentTabsFragment.MakePayment;

import java.util.List;


public class ResponseAccessibilityService extends AccessibilityService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private String USSDText;

    public void onAccessibilityEvent(AccessibilityEvent event) {

        List<CharSequence> response = event.getText();
        USSDText = processUSSDText(response);

        if (event.getPackageName().toString().contains("com.android.phone")
                && event.getClassName().toString().toLowerCase()
                .contains("alert")) {//make sure same message isn't sent twice and the e
            if (USSDText.contains("Birr will be transferred to 0944286051")) {
                AccessibilityNodeInfo source = event.getSource();
                if (source != null) {
                    //capture the EditText simply by using FOCUS_INPUT (since the EditText has the focus), you can probably find it with the viewId input_field
                    AccessibilityNodeInfo inputNode = source.findFocus(AccessibilityNodeInfo.FOCUS_INPUT);
                    if (inputNode != null) {//prepare you text then fill it using ACTION_SET_TEXT
                        Bundle arguments = new Bundle();
                        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,"1");
                        inputNode.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
                    }
                    //"Click" the Send button
                    List<AccessibilityNodeInfo> list = source.findAccessibilityNodeInfosByText("Send");
                    for (AccessibilityNodeInfo node : list) {
                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
            } else if(USSDText.contains("Birr to the number 944286051 successfully.The transfer fee is")){
                AccessibilityNodeInfo source = event.getSource();
                if (source != null) {
                    //turn on show dialog
                    MakePayment makePayment = new MakePayment();
                    makePayment.setShowPhoneDialog(true);

                    //"Click" the Send button
                    List<AccessibilityNodeInfo> list = source.findAccessibilityNodeInfosByText("OK");
                    for (AccessibilityNodeInfo node : list) {
                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
            }
        }
    }


    @Override
    public void onInterrupt() {
    }

    @Override
    protected void onServiceConnected() {
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
