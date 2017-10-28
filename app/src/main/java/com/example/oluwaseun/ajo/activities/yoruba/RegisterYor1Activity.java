package com.example.oluwaseun.ajo.activities.yoruba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;

public class RegisterYor1Activity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_yor1);


    }
    //Method to get all User Input data

    public void getUserIdentificationData(View view) {

        EditText name = (EditText) findViewById(R.id.name);
        String nameString = name.getText().toString();

        EditText dob = (EditText) findViewById(R.id.dob);
        String dateOfBirth = dob.getText().toString();

        EditText bvn = (EditText) findViewById(R.id.bvn);
        String bvnString = bvn.getText().toString();

        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        String phoneNumberString = phoneNumber.getText().toString();

        EditText accountNumber = (EditText) findViewById(R.id.accountNumber);
        String accountNumberString = accountNumber.getText().toString();

        EditText bankName = (EditText) findViewById(R.id.bankName);
        String bankNameString = bankName.getText().toString();
    }

    public void next(View v){
        Intent in = new Intent(RegisterYor1Activity.this, RegisterYor2Activity.class);
        startActivity(in);
    }
}
