package com.example.oluwaseun.ajo.activities.yoruba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;
import com.example.oluwaseun.ajo.activities.english.DashboardActivity;
import com.example.oluwaseun.ajo.activities.english.LoginActivity;

public class LoginYorActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_yor);
    }

    //Authentication Required --
    public void login (View v){
        Intent in = new Intent(LoginYorActivity.this, DashboardYorActivity.class);
        startActivity(in);
    }
}
