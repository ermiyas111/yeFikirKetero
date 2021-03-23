package com.aden.yefikirketero.UI.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutSelf.GenderQuestion;
import com.aden.yefikirketero.UI.profile.OrganizedInfo.AboutSelf.ReligionQuestion;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneVerification extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        callVerification();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                SharedPreferences sharedPreferences2 = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences2.edit();
                myEdit2.putString("LoggedInWithPhoneNumber", user.getPhoneNumber());
                myEdit2.commit();

                Intent intent = new Intent(this, BioQuestion.class);
                startActivity(intent);
            } else {
                callVerification();
                Toast.makeText(this, "Phone verification process was cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void callVerification(){
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers;
        providers = Arrays.asList(
                        new AuthUI.IdpConfig.PhoneBuilder().setDefaultCountryIso("ET").build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

}
