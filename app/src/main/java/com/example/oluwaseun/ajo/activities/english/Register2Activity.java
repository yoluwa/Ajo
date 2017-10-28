package com.example.oluwaseun.ajo.activities.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;

public class Register2Activity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

    //Method to get all User Input data

    private void getUserRegistrationData(View view) {

        EditText email = (EditText) findViewById(R.id.email);
        String emailString = email.getText().toString();

        EditText password = (EditText) findViewById(R.id.password);
        String passwordString = password.getText().toString();

        EditText confirmPassword = (EditText) findViewById(R.id.confirm_password);
        String confirmPasswordString = confirmPassword.getText().toString();

        EditText securityQuestion = (EditText) findViewById(R.id.security_question);
        String securityQuestionString = securityQuestion.getText().toString();

        EditText answerToSecurityQuestion = (EditText) findViewById(R.id.answer_security_question);
        String answerToSecurityQuestionString = answerToSecurityQuestion.getText().toString();

    }

    public void createAccount(View v) {
        Intent in = new Intent(Register2Activity.this, Register3Activity.class);
        startActivity(in);
    }
}
