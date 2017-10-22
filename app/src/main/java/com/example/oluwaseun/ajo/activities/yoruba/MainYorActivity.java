package com.example.oluwaseun.ajo.activities.yoruba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oluwaseun.ajo.activities.AbstractActivity;
import com.example.oluwaseun.ajo.R;

public class MainYorActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yor);
    }

    public void register (View v){
        Intent in = new Intent(MainYorActivity.this, RegisterYor1Activity.class);
        startActivity(in);
    }

    public void login (View v){
        Intent in = new Intent(MainYorActivity.this, LoginYorActivity.class);
        startActivity(in);
    }
}
