package com.example.oluwaseun.ajo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.english.MainActivity;
import com.example.oluwaseun.ajo.activities.yoruba.MainYorActivity;

public class WelcomeActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void englishLanguageOption (View v){
        Intent in = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(in);
    }

    public void yorubaLanguageOption(View v){
        Intent in = new Intent(WelcomeActivity.this, MainYorActivity.class);
        startActivity(in);
    }
}
