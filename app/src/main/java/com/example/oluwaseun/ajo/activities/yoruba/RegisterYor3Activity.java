package com.example.oluwaseun.ajo.activities.yoruba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;
import com.example.oluwaseun.ajo.activities.english.DashboardActivity;

public class RegisterYor3Activity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_yor3);
    }

    //Authentication Required --
    public void login (View v){
        Intent in = new Intent(RegisterYor3Activity.this, DashboardYorActivity.class);
        startActivity(in);
    }
}
