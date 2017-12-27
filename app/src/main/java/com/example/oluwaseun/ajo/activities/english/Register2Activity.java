package com.example.oluwaseun.ajo.activities.english;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.oluwaseun.ajo.models.User;
import com.example.oluwaseun.ajo.utils.Endpoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register2Activity extends AbstractActivity {

    private ProgressDialog progressDialog;
    public String nameString, dateOfBirth, bvnString, phoneNumberString, accountNumberString,
            bankNameString;
    public String emailString, passwordString, passwordStr, confirmPasswordString, securityQuestionString,
            answerToSecurityQuestionString;
    public int phone, account_number, bvn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

    //Method to get all User Input data

    private boolean getUserRegistrationData(View view) {
        boolean correct = false;

        EditText email = (EditText) findViewById(R.id.email);
        emailString = email.getText().toString().trim();
         if (!isValidEmail(emailString)) {
             correct = true;
            email.setError("Invalid Email (Email must be of the form janedoe@gmail.com)");
            }

        EditText password = (EditText) findViewById(R.id.password);
        passwordStr = password.getText().toString().trim();
            if (!isValidPassword(passwordStr)) {
                password.setError("Invalid Password (Password must be greater than 6 characters)");
            }

        EditText confirmPassword = (EditText) findViewById(R.id.confirm_password);
        confirmPasswordString = confirmPassword.getText().toString().trim();

        if (passwordStr.equals(confirmPasswordString)) {
            passwordString = passwordStr;
        }
        else {
            password.setError("Passwords do not match");
            }

        EditText securityQuestion = (EditText) findViewById(R.id.security_question);
        securityQuestionString = securityQuestion.getText().toString().trim();

        EditText answerToSecurityQuestion = (EditText) findViewById(R.id.answer_security_question);
        answerToSecurityQuestionString = answerToSecurityQuestion.getText().toString().trim();

        return  correct;

    }

    private void getUserData(View view) {

        User user = (User) getIntent().getSerializableExtra("user");
        nameString = user.getName();
        dateOfBirth = user.getDateOfBirth();
        bvnString = user.getBvn();
        //bvn = Integer.valueOf(bvnString);
        phoneNumberString = user.getPhoneNumber();
//        phone = Integer.valueOf(phoneNumberString);
        accountNumberString = user.getAccountNumber();
//        account_number = Integer.valueOf(accountNumberString);
        bankNameString = user.getBankName();
    }

    public void createAccount(View v) {

        getUserData(v);

        if (getUserRegistrationData(v)) {
            Toast.makeText(getApplicationContext(),"Some input Errors",Toast.LENGTH_LONG).show();
            return;
        }
        else{
            //JSON Object
            JSONObject user = new JSONObject();

            try {
                user.put("name", nameString); //1
                user.put("date_of_birth", dateOfBirth); //2
                user.put("bvn", bvn); //3
                user.put("phone", phone); //4
                user.put("account_number", account_number); //5
                user.put("bank_name", bankNameString); //6
                user.put("email", emailString); //7
                user.put("password", passwordString); //8
//            user.put("confirm_password", confirmPasswordString);
                user.put("security_question", securityQuestionString); //9
                user.put("answer_to_security", answerToSecurityQuestionString); //10
            } catch (JSONException e) {

            }

            //Volley Request

            JsonObjectRequest stringRequest = new JsonObjectRequest(Endpoint.REGISTER, user,

                    //when you can access the server
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject serverResponse) {
                            try {
                                String status = serverResponse.getString("status");

                                Log.d("Reg2",status);
                                if (status.equals("success")) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Register2Activity.this,
                                            "Registration is successful", Toast.LENGTH_LONG).show();
                                    Intent in = new Intent(Register2Activity.this, Register3Activity.class);
                                    in.putExtra("email", emailString);
                                    startActivity(in);
                                } else {
                                    JSONObject data = serverResponse.getJSONObject("data");
//                                Log.i("Data response:", data.toString());
                                    progressDialog.dismiss();
                                    Toast.makeText(Register2Activity.this,
                                            "Registration not successful", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                progressDialog.dismiss();
//                            Log.e("Error:", e.toString());
                                Toast.makeText(Register2Activity.this,
                                        "Unable to access the Server Try again later.", Toast.LENGTH_LONG).show();
                            }

                        }
                    },

                    //when you cant register the user
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

//                        Log.i("Error:", error.toString());

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





    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
}
