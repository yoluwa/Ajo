package com.example.oluwaseun.ajo.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.SessionManager;
import com.example.oluwaseun.ajo.activities.english.ContributionGroupActivity;
import com.example.oluwaseun.ajo.activities.english.DashboardActivity;
import com.example.oluwaseun.ajo.activities.english.LoginActivity;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ProgressDialog progressDialog;

    private Button fundButton;
    private Button withdraw;

    public String dataString;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializing pastack

        PaystackSdk.initialize(getContext());


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_home, container, false);

        // NOTE : We are calling the onFragmentInteraction() declared in the MainActivity
        // ie we are sending "Fragment 1" as title parameter when fragment1 is activated
        if (mListener != null) {
            mListener.onFragmentInteraction("Home");
        }

        CardView group1 = view.findViewById(R.id.group1);
        CardView group2 =  view.findViewById(R.id.group2);
        CardView group3 = view.findViewById(R.id.group3);

        group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        group3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        TextView name = (TextView) view.findViewById (R.id.name);
        TextView email = (TextView) view.findViewById (R.id.email);
        TextView phoneNumber = (TextView) view.findViewById (R.id.phoneNumber);
        TextView accountNumber = (TextView) view.findViewById (R.id.accountNumber);
        TextView walletBalance = (TextView) view.findViewById (R.id.walletBalance);


        fundButton = view.findViewById(R.id.fund);
        fundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cardNumber = "4084084084084081";
                int expiryMonth = 11; //any month in the future
                int expiryYear = 18; // any year in the future. '2018' would work also!
                String cvv = "408";  // cvv of the test card

                Card card = new Card(cardNumber, expiryMonth, expiryYear, cvv);
                if (card.isValid()) {
                    // charge card

                    Toast.makeText(getContext(), "Card Valid",Toast.LENGTH_LONG).show();
                    Charge charge = new Charge();
                    charge.setCard(card);
                    PaystackSdk.chargeCard(getActivity(), charge, new Paystack.TransactionCallback() {
                        @Override
                        public void onSuccess(Transaction transaction) {
                            Toast.makeText(getContext()," charged",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void beforeValidate(Transaction transaction) {
                            Toast.makeText(getContext(),"charging", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable error, Transaction transaction) {
                            Log.e("error", transaction.toString());
                        }
                    });
                } else {
                    //do something
                    Toast.makeText(getContext(),"error charging", Toast.LENGTH_LONG).show();
                    Log.e("card error",card.toString());
                }


                Toast.makeText(getContext(), "Charging Card",Toast.LENGTH_LONG).show();

                JSONObject details = new JSONObject();
                try {
                    details.put("reference", "23456789056");
                    details.put ("amount", "5000");
                    details.put("email", "damaris@gmail.com");
                }
                catch (JSONException e){

                }


                //volley request to charge card

                JsonObjectRequest chargeCardRequest = new JsonObjectRequest(Endpoint.PAYSTACK_INITIALIZE, details,
                        //when you can access the server
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject serverResponse) {
                                try {
                                    String status = serverResponse.getString("status");
                                    JSONObject data = serverResponse.getJSONObject("data");
                                    Log.i("Status",status);
                                    Log.i("Data response:",data.toString() );
                                    dataString = data.toString();
                                    if (status.equals("success")) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(),
                                                "Card Charged Successfully", Toast.LENGTH_LONG).show();
                                        //Intent in = new Intent(Register2Activity.this, Register3Activity.class);
                                        //startActivity(in)
                                    }
                                    else {
                                        // String mem = getMembers(member1String,member2String,member3String,member4String,member5String);
                                        Log.i("Status",status);
                                        Log.i("Data response:",data.toString() );
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(),
                                                "Card Charge Not Successful ", Toast.LENGTH_LONG).show();
                                    }
                                }
                                catch (JSONException e) {
                                    progressDialog.dismiss();
                                    Log.i("Error:", e.toString());
                                    Toast.makeText(getContext(),
                                            "Sever not Responding", Toast.LENGTH_LONG).show();
                                }

                            }
                        },

                        //when you cant register the user
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error:", error.toString());
                                progressDialog.dismiss();
                               // Toast.makeText(getContext(),
                                 //       "Unable to access the Server Try again later.", Toast.LENGTH_LONG).show();

                                Toast.makeText(getContext(),
                                        error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization","sk_test_a11fbc3187c55f7046bc513de6307bb7ae0ee7a2");
                        headers.put("Content-Type","application/json");
                        Log.i("Header", headers.toString());
                        return  headers;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(chargeCardRequest);
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Please wait, your fund request is being processed.");
                progressDialog.show();







            }

        });


        withdraw = view.findViewById(R.id.withdraw);
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hey hi ", Toast.LENGTH_SHORT).show();
                Log.e("FUND BtN", "clicked");
            }
        });





        return view;
    }

    public void performCharge(){
        Charge charge = new Charge();
        //charge.setCard(card); //sets the card to charge

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // NOTE : We changed the Uri to String.
        void onFragmentInteraction(String title);
    }
}
