package com.example.oluwaseun.ajo.activities.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oluwaseun.ajo.activities.AbstractActivity;
import com.example.oluwaseun.ajo.R;

public class MainActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register (View v){
        Intent in = new Intent(MainActivity.this, Register1Activity.class);
        startActivity(in);
    }

    public void login (View v){
        Intent in = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(in);
    }
}
