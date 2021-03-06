package com.example.oluwaseun.ajo.activities.english;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;
import com.example.oluwaseun.ajo.activities.SessionManager;
import com.example.oluwaseun.ajo.utils.Endpoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WithdrawalScreen extends AbstractActivity {

    public int amount1, amount;
    private EditText amountText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal_screen);


        Button pay = (Button) findViewById(R.id.pay);
        amountText = (EditText)findViewById(R.id.amount);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amountTexts = amountText.getText().toString();
                if (TextUtils.isEmpty(amountTexts)) {
                    amountText.setError("Required.");
                } else {
                    amountText.setError(null);
                    amount1 = Integer.parseInt(amountText.getText().toString().trim());
                    amount = amount1 * 100;
                }

                JSONObject amountData = new JSONObject();
                try {
                    amountData.put("amount", amount);
                }
                catch (JSONException e){

                }
                //Volley Request for the group

                JsonObjectRequest fundRequest = new JsonObjectRequest(Endpoint.WITHDRAW, amountData,
                        //when you can access the server
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject serverResponse) {
                                try {
                                    String status = serverResponse.getString("status");
                                    JSONObject data = serverResponse.getJSONObject("data");
                                    Log.i("Status",status);
                                    Log.i("Data response:",data.toString() );
                                    if (status.equals("success")) {
//                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(),
                                                "Transaction Successful", Toast.LENGTH_LONG).show();
                                        Intent in = new Intent(WithdrawalScreen.this, DashboardActivity.class);
                                        startActivity(in);
                                    }
                                    else {
                                        // String mem = getMembers(member1String,member2String,member3String,member4String,member5String);
                                        Log.i("Status",status);
                                        Log.i("Data response:",data.toString() );
//                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(),
                                                "Transaction Not Successful", Toast.LENGTH_LONG).show();
                                    }
                                }
                                catch (JSONException e) {
//                                    progressDialog.dismiss();
//                            Log.e("Error:", e.toString());
                                    Toast.makeText(getApplicationContext(),
                                            "Sever not Responding", Toast.LENGTH_LONG).show();
                                }

                            }
                        },

                        //when you cant register the user
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error:", error.toString());
//                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),
                                        "Unable to access the Server Try again later.", Toast.LENGTH_LONG).show();
                            }
                        }
                )
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        //get current user access token
                        String token = sessionManager.getUserToken().get("token");
                        headers.put("Authorization",token);
                        headers.put("Content-Type","application/json");
                        Log.i("Header", headers.toString());
                        return  headers;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(fundRequest);
//                progressDialog = new ProgressDialog(getApplicationContext());
//                progressDialog.setMessage("Please wait, your wallet is being funded");
//                progressDialog.show();

            }
        });
    }
}
