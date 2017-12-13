package com.example.oluwaseun.ajo.activities.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;
import com.example.oluwaseun.ajo.models.User;

public class Register1Activity extends AbstractActivity {

    public String nameString, dateOfBirth, bvnString, phoneNumberString, accountNumberString,
            bankNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
    }

    //Method to get all User Input data
    private void getUserIdentificationData(View view) {

        EditText name = (EditText) findViewById(R.id.name);
        nameString = name.getText().toString().trim();

        EditText dob = (EditText) findViewById(R.id.dob);
        dateOfBirth = dob.getText().toString().trim();

        EditText bvn = (EditText) findViewById(R.id.bvn);
        bvnString = bvn.getText().toString().trim();

        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        phoneNumberString = phoneNumber.getText().toString().trim();

        EditText accountNumber = (EditText) findViewById(R.id.accountNumber);
        accountNumberString = accountNumber.getText().toString().trim();

        EditText bankName = (EditText) findViewById(R.id.bankName);
        bankNameString = bankName.getText().toString().trim();


    }

    public void next(View v) {
        getUserIdentificationData(v);
        User user = new User(nameString, dateOfBirth, bvnString, phoneNumberString,
                accountNumberString, bankNameString);
        Intent in = new Intent(Register1Activity.this, Register2Activity.class);
        in.putExtra("user", user);
        startActivity(in);
    }
}
