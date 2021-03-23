package com.aden.yefikirketero.UI.getPhone;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.aden.yefikirketero.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.VISIBLE;

public class PaymentActivity extends AppCompatActivity {

    Context context;

    int checkedItem = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        context = this;

        String[] listItems = getResources().getStringArray(R.array.location_choices);

        //let users choose whether they are from inside the country or not
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context)
                .setTitle(getResources().getString(R.string.from_where));
                materialAlertDialogBuilder.setNeutralButton(getResources().getString(R.string.back), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                });
                materialAlertDialogBuilder.setPositiveButton(getResources().getString(R.string.choose_button), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                // Single-choice items (initialized with checked item)
                materialAlertDialogBuilder.setSingleChoiceItems(listItems, checkedItem, null);
                materialAlertDialogBuilder.setCancelable(false);
                materialAlertDialogBuilder.show();



    }
}
