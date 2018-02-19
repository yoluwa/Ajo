package com.example.oluwaseun.ajo.activities.english;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;

import co.paystack.android.PaystackSdk;

public class PaymentScreen extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);

        PaystackSdk.initialize(getApplicationContext());

        //Sample Test Details From Paystack
        String cardNumber = "4084084084084081";
        int expiryMonth = 11; //any month in the future
        int expiryYear = 2018; // any year in the future
        String cvv = "408";

        EditText amount = (EditText)findViewById(R.id.amount);

        Button pay = (Button)findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
