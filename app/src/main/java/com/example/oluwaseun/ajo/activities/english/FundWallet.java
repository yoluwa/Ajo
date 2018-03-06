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

import co.paystack.android.PaystackSdk;

public class FundWallet extends AbstractActivity {

    public int amount1, amount;
    private EditText amountText;
    private EditText cardNumberField;
    private EditText expiryMonthField;
    private EditText expiryYearField;
    private EditText cvvField;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_wallet);

        //init paystack sdk
        PaystackSdk.initialize(getApplicationContext());

        Button pay = (Button) findViewById(R.id.pay);
        cardNumberField = (EditText) findViewById(R.id.cardNumber);
        expiryMonthField = (EditText) findViewById(R.id.month);
        expiryYearField = (EditText) findViewById(R.id.year);
        cvvField = (EditText) findViewById(R.id.cvv);
        amountText = (EditText)findViewById(R.id.amount);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateForm()) {
                    Toast.makeText(FundWallet.this, "Input Errors", Toast.LENGTH_LONG).show();
                    return;
                }

                JSONObject amountData = new JSONObject();
                try {
                    amountData.put("amount", amount);
                }
                catch (JSONException e){

                }
                //Volley Request for the group

                JsonObjectRequest fundRequest = new JsonObjectRequest(Endpoint.FUND, amountData,
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
                                        Intent in = new Intent(FundWallet.this, DashboardActivity.class);
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
//                        Log.i("Header", headers.toString());
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


    public boolean validateForm() {
        boolean valid = true;


        String cardNumber = cardNumberField.getText().toString();
        if (TextUtils.isEmpty(cardNumber)) {
            cardNumberField.setError("Required.");
            valid = false;
        } else {
            cardNumberField.setError(null);
        }

        String expiryMonth = expiryMonthField.getText().toString();
        if (TextUtils.isEmpty(expiryMonth)) {
            expiryMonthField.setError("Required.");
            valid = false;
        } else {
            expiryMonthField.setError(null);
        }

        String expiryYear = expiryYearField.getText().toString();
        if (TextUtils.isEmpty(expiryYear)) {
            expiryYearField.setError("Required.");
            valid = false;
        } else {
            expiryYearField.setError(null);
        }

        String cvv = cvvField.getText().toString();
        if (TextUtils.isEmpty(cvv)) {
            cvvField.setError("Required.");
            valid = false;
        } else {
            cvvField.setError(null);
        }

        String amountTexts = amountText.getText().toString();
        if (TextUtils.isEmpty(amountTexts)) {
            amountText.setError("Required.");
            valid = false;
        } else {
            amountText.setError(null);
            amount1 = Integer.parseInt(amountText.getText().toString().trim());
            amount = amount1 * 100;
        }

        return valid;
    }

}
