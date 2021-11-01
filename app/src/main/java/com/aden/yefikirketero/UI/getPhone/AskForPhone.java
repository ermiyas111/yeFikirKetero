package com.aden.yefikirketero.UI.getPhone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aden.yefikirketero.R;
import com.aden.yefikirketero.UI.profile.ShowTheWayToProfile;
import com.aden.yefikirketero.retrofit.YeFikirKeteroApi;
import com.aden.yefikirketero.retrofit.model.PaymentUpload;
import com.aden.yefikirketero.retrofit.model.PostUpload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AskForPhone extends AppCompatActivity {

    Button continueButton;
    EditText hiddenNumber;

    Context context = this;

    YeFikirKeteroApi api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_phone);

        continueButton = findViewById(R.id.button_continue_phone);
        hiddenNumber = findViewById(R.id.phoneEditText);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            Toast.makeText(getApplicationContext(), b.getString("currentPhone"), Toast.LENGTH_LONG).show();
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //retrofit method call
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(YeFikirKeteroApi.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    api = retrofit.create(YeFikirKeteroApi.class);

                    long parsedPhone = Long.valueOf(b.getString("currentPhone"));
                    String parsedId = b.getString("currentId");
                    long parsedCard= Long.parseLong(hiddenNumber.getText().toString());

//                    PaymentUpload paymentUpload = new PaymentUpload(parsedPhone, parsedCard, "no", "get_phone_no", parsedId);
//                    Call<PaymentUpload> call = api.uploadPayment(paymentUpload);
//                    call.enqueue(new Callback<PaymentUpload>() {
//                        @Override
//                        public void onResponse(Call<PaymentUpload> call, Response<PaymentUpload> response) {
//                            Log.d("samuela", "success");
//                            //t1.setText("Success");
//                            //createPerson();
//                        }
//
//                        @Override
//                        public void onFailure(Call<PaymentUpload> call, Throwable t) {
//                            Log.d("samuela", "fail");
//                            //t1.setText("fail");
//                        }
//                    });

                    //call the next activity
                    Intent intent = new Intent(context, AcknowledgePhoneInput.class);
                    startActivity(intent);
                }
            });
        }
    }
}
