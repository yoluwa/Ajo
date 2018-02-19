package com.example.oluwaseun.ajo.activities.english;


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
import com.example.oluwaseun.ajo.fragments.HomeFragment;
import com.example.oluwaseun.ajo.utils.Endpoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;


public class PaymentScreensActivity extends AbstractActivity {
    //variables
    private Card card;
    private Charge charge;

    private EditText emailField;
    private EditText cardNumberField;
    private EditText expiryMonthField;
    private EditText expiryYearField;
    private EditText cvvField;

    private String cardNumber, cvv;
    private int expiryMonth, expiryYear;

    public int amount;
    public String paymentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init paystack sdk
        PaystackSdk.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //init view
        Button payBtn = (Button) findViewById(R.id.pay);

        //emailField = (EditText) findViewById(R.id.edit_email_address);
        cardNumberField = (EditText) findViewById(R.id.cardNumber);
        expiryMonthField = (EditText) findViewById(R.id.month);
        expiryYearField = (EditText) findViewById(R.id.year);
        cvvField = (EditText) findViewById(R.id.cvv);
        EditText amountText = (EditText)findViewById(R.id.amount);
        amount = Integer.parseInt(amountText.getText().toString().trim());



        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateForm()) {
                    return;
                }
                try {
                    //email = emailField.getText().toString().trim();
                    //cardNumber = cardNumberField.getText().toString().trim();
                    //expiryMonth = Integer.parseInt(expiryMonthField.getText().toString().trim());
                    //expiryYear = Integer.parseInt(expiryYearField.getText().toString().trim());
                    //cvv = cvvField.getText().toString().trim();

                    String cardNumber = "4084084084084081";
                    int expiryMonth = 11; //any month in the future
                    int expiryYear = 18; // any year in the future
                    String cvv = "408";
                    card = new Card(cardNumber, expiryMonth, expiryYear, cvv);

                    if (card.isValid()) {
                        Toast.makeText(PaymentScreensActivity.this, "Card is Valid", Toast.LENGTH_LONG).show();
                        performCharge();
                        paymentAPI();
                        Intent in = new Intent(PaymentScreensActivity.this, HomeFragment.class);
                        startActivity(in);
                    } else {
                        Toast.makeText(PaymentScreensActivity.this, "Card not Valid", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });

    }

    /**
     * Method to perform the charging of the card
     */
    private void performCharge() {
        //create a Charge object
        charge = new Charge();

        //set the card to charge
        charge.setCard(card);

        //call this method if you set a plan
        //charge.setPlan("PLN_yourplan");

        //charge.setEmail(email); //dummy email address

        charge.setAmount(amount); //test amount

        PaystackSdk.chargeCard(PaymentScreensActivity.this, charge, new Paystack.TransactionCallback() {
            @Override
            public void onSuccess(Transaction transaction) {
                // This is called only after transaction is deemed successful.
                // Retrieve the transaction, and send its reference to your server
                // for verification.
                paymentReference = transaction.getReference();
                Toast.makeText(PaymentScreensActivity.this, "Transaction Successful! payment reference: "
                        + paymentReference, Toast.LENGTH_LONG).show();
            }

            @Override
            public void beforeValidate(Transaction transaction) {
                // This is called only before requesting OTP.
                // Save reference so you may send to server. If
                // error occurs with OTP, you should still verify on server.
            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                //handle error here
            }
        });
    }

    private boolean validateForm() {
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

        return valid;
    }

    private void paymentAPI(){

        JSONObject refData = new JSONObject();
        //EditText h = (EditText)view.findViewById(R.id.member1);
        try {
            refData.put("reference", paymentReference);
            Log.i("reference",paymentReference);
        }
        catch (JSONException e){

        }
        //Volley Request for the group

        JsonObjectRequest fundRequest = new JsonObjectRequest(Endpoint.FUND_REFERENCE, refData,
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
                                //progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),
                                        "", Toast.LENGTH_LONG).show();
                            }
                            else {
                                // String mem = getMembers(member1String,member2String,member3String,member4String,member5String);
                                Log.i("Status",status);
                                Log.i("Data response:",data.toString() );
                                //progressDialog.dismiss();
                                //Toast.makeText(getApplicationContext(),
                                  //      "Contribution Group Not Created Successfully ", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e) {
                            //progressDialog.dismiss();
//                            Log.e("Error:", e.toString());
                            //Toast.makeText(getApplicationContext(),
                              //      "Sever not Responding", Toast.LENGTH_LONG).show();
                        }

                    }
                },

                //when you cant register the user
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error:", error.toString());
                        //progressDialog.dismiss();
                       // Toast.makeText(getApplicationContext(),
                         //       "Unable to access the Server Try again later.", Toast.LENGTH_LONG).show();
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


    }

}

