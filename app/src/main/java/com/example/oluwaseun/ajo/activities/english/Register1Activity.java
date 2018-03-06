package com.example.oluwaseun.ajo.activities.english;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register1Activity extends AbstractActivity {

    public String nameString, dateOfBirth, bvnString, bvnStr, phoneNumberString, accountNumberString,
            bankNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
    }

    //Method to get all User Input data
    private  boolean getUserIdentificationData(View view) {
        boolean correct = false;

        EditText name = (EditText) findViewById(R.id.name);
        nameString = name.getText().toString().trim();
            if (nameString.isEmpty()){
                correct =true;
                name.setError("Incorrect Name");
            }

        EditText dob = (EditText) findViewById(R.id.dob);
        dateOfBirth = dob.getText().toString().trim();
            if (!isValidDob(dateOfBirth)){
                correct = true;
                dob.setError("Wrong date format Use the format '09/09/1998'");
            }

        EditText bvn = (EditText) findViewById(R.id.bvn);
        bvnString = bvn.getText().toString().trim();
            if (bvnString.isEmpty() && bvnString.length() != 14) {
                correct= true;
                bvn.setError("Invalid BVN");
            }

        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        phoneNumberString = phoneNumber.getText().toString().trim();
        if (phoneNumberString.length() != 11 && phoneNumberString.isEmpty()) {
            correct = true;
            phoneNumber.setError("Invalid BVN");
        }

        EditText accountNumber = (EditText) findViewById(R.id.accountNumber);
        accountNumberString = accountNumber.getText().toString().trim();
            if (accountNumberString.length() != 10 ) {
                correct = true;
                accountNumber.setError("Invalid Account Number");
            }

        EditText bankName = (EditText) findViewById(R.id.bankName);
        bankNameString = bankName.getText().toString().trim();

        return  correct;


    }

    public void next(View v) {
        if (getUserIdentificationData(v)){
            Toast.makeText(getApplicationContext(),"Some input Errors",Toast.LENGTH_LONG).show();
            return;
        }else{
//            User user = new User(nameString, dateOfBirth, bvnString, phoneNumberString,
//                    accountNumberString, bankNameString);
            Intent in = new Intent(Register1Activity.this, Register2Activity.class);
            Bundle userDetails = new Bundle();
            userDetails.putString("name", nameString);
            userDetails.putString("DOB", dateOfBirth);
            userDetails.putString("bvn", bvnString);
            userDetails.putString("phone", phoneNumberString);
            Log.i("phone", phoneNumberString);
            userDetails.putString("account", accountNumberString);
            Log.i("account", accountNumberString);
            userDetails.putString("bank", bankNameString);
            in.putExtras(userDetails);
            startActivity(in);
        }

    }

    // validating name id
    private boolean isValidName(String nameString) {
        String NAME_PATTERN = "[A-Za-z]";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(nameString);
        return matcher.matches();
    }

    // validating dob
    private boolean isValidDob(String dateOfBirth) {

        String DOB_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
        Pattern pattern = Pattern.compile(DOB_PATTERN);
        Matcher matcher = pattern.matcher(dateOfBirth);
        return matcher.matches();
    }
    private boolean isValidEmail(String email){
        Pattern ps = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher ms = ps.matcher(email);
        return ms.matches();
    }
}
