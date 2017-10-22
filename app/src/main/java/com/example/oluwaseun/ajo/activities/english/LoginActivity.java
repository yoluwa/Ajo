package com.example.oluwaseun.ajo.activities.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;

public class LoginActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //Authentication Required --
    public void login (View v){
        Intent in = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(in);
    }
}
