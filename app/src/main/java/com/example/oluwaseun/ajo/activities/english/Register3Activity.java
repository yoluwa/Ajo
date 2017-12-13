package com.example.oluwaseun.ajo.activities.english;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;
import com.example.oluwaseun.ajo.utils.Endpoint;

import org.json.JSONException;
import org.json.JSONObject;

public class Register3Activity extends AbstractActivity {

    public String emailString, passwordString;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
    }
//    String email = getIntent().getExtras().getCharSequence("email");

    private void getLoginData(View v) {
        EditText email = (EditText) findViewById(R.id.email);
        emailString = email.getText().toString().trim();

        EditText password = (EditText) findViewById(R.id.password);
        passwordString = password.getText().toString().trim();
    }


    public void login(View v) {

        getLoginData(v);

        JSONObject login = new JSONObject();
        try {
            login.put("email", emailString);
            login.put("password", passwordString);
        } catch (JSONException e) {

        }

        JsonObjectRequest loginRequest = new JsonObjectRequest(Endpoint.LOGIN, login,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            JSONObject data = response.getJSONObject("data");
                            if (status.equals("success")) {
                                progressDialog.dismiss();
                                Toast.makeText(Register3Activity.this,
                                        "Login is Successful", Toast.LENGTH_LONG).show();
//                                String userToken = data.token.toString();
                                Intent in = new Intent(Register3Activity.this, DashboardActivity.class);
                                startActivity(in);

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Register3Activity.this,
                                        "Login is not Succesful", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            Toast.makeText(Register3Activity.this,
                                    "Invalid Email address and Password", Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Register3Activity.this, "Server not Responding"
                                , Toast.LENGTH_LONG).show();
                    }
                }
        );


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging In");
        progressDialog.show();
    }
}
