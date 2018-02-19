package com.example.oluwaseun.ajo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oluwaseun.ajo.R;

/**
 * Created by kibb on 2/18/18.
 */

public class TestFragment extends Fragment {

    public TestFragment(){}


    @Override
    public void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.test_fragment, container, false);

        Button fund = (Button) view.findViewById(R.id.tt_fund);

        fund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("error","test fund ");

            }
        });

        return view;
    }
}
