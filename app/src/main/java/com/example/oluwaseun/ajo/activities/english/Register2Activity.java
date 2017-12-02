package com.example.oluwaseun.ajo.activities.english;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.AbstractActivity;
import com.example.oluwaseun.ajo.utils.Endpoint;

import org.json.JSONException;
import org.json.JSONObject;

public class Register2Activity extends AbstractActivity {

    private String emailString, passwordString;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

    //Method to get all User Input data

    private void getUserRegistrationData(View view) {

        EditText email = (EditText) findViewById(R.id.email);
        String emailString = email.getText().toString().trim();

        EditText password = (EditText) findViewById(R.id.password);
        String passwordString = password.getText().toString().trim();

        EditText confirmPassword = (EditText) findViewById(R.id.confirm_password);
        String confirmPasswordString = confirmPassword.getText().toString();

        EditText securityQuestion = (EditText) findViewById(R.id.security_question);
        String securityQuestionString = securityQuestion.getText().toString();

        EditText answerToSecurityQuestion = (EditText) findViewById(R.id.answer_security_question);
        String answerToSecurityQuestionString = answerToSecurityQuestion.getText().toString();

    }

    public void createAccount(View v) {
//        Intent in = new Intent(Register2Activity.this, Register3Activity.class);
//        startActivity(in);

        //JSON Object
        JSONObject user = new JSONObject();

        try {
            user.put("email" , emailString);
            user.put("password", passwordString);
        } catch (JSONException e) {

        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoint.REGISTER,

                //when you can access the server
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String serverResponse) {
                        try {
                            JSONObject response = new JSONObject(serverResponse);
                            Boolean success = response.getBoolean("success");
                            if (success) {
                                progressDialog.dismiss();
                                Toast.makeText(Register2Activity.this,
                                        "Registration is successful", Toast.LENGTH_LONG).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Register2Activity.this,
                                        "Registration not successful", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException ex) {
                            progressDialog.dismiss();
                            Toast.makeText(Register2Activity.this,
                                    "Unable to access the Server Try again later.", Toast.LENGTH_LONG).show();
                        }

                    }
                },

                //when you cant register the user
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Error:", error.toString());

                        progressDialog.dismiss();
                        Toast.makeText(Register2Activity.this,
                                "Sever not Responding", Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait, you're being registered.");
        progressDialog.show();

    }
}
