package com.example.oluwaseun.ajo.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.oluwaseun.ajo.activities.english.FundWallet;
import com.example.oluwaseun.ajo.activities.english.WithdrawalScreen;
import com.example.oluwaseun.ajo.utils.Endpoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


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

        final TextView name = (TextView) view.findViewById (R.id.name);
        final TextView email = (TextView) view.findViewById (R.id.email);
        final TextView phoneNumber = (TextView) view.findViewById (R.id.phoneNumber);
        final TextView accountNumber = (TextView) view.findViewById (R.id.accountNumber);
        final TextView walletBalance = (TextView) view.findViewById (R.id.walletBalance);


        JsonObjectRequest userProfileRequest = new JsonObjectRequest
                (Request.Method.GET, Endpoint.USER_PROFILE, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject serverResponse) {
                        try {
                            String status = serverResponse.getString("status");
                            JSONObject data = serverResponse.getJSONObject("data");
                            Log.i("Status",status);
                            Log.i("Data response:",data.toString() );
                            if (status.equals("success")) {

                                name.setText("Name: " + data.getString("name"));
                                email.setText("Email: " + data.getString("email"));
                                phoneNumber.setText("Phone Number: " + data.getString("phone"));
                                accountNumber.setText("Account Number: " + data.getString("account_number"));
                                walletBalance.setText("N" + Integer.parseInt(data.getString("wallet_balance"))/100 );


                                //Retrieve group details to display them either on cardviews or as a group of textboxes...

                                progressDialog.dismiss();

                            }
                            else {
                                Log.i("Status",status);
                                Log.i("Data response:",data.toString() );
                                   progressDialog.dismiss();
                                Toast.makeText(getContext(),
                                       "Unable to get personal details", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e) {
                            progressDialog.dismiss();
                            Log.e("Error:", e.toString());
                            Toast.makeText(getContext(),
                                    "Sever not Responding", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e("Error:", error.toString());
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),
                                "Unable to access the Server Try again later.", Toast.LENGTH_LONG).show();

                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError{
                        Map<String, String> headers = new HashMap<>();
                        SessionManager sessionManager = new SessionManager(getContext());
                        //get current user access token
                        String token = sessionManager.getUserToken().get("token");
                        headers.put("Authorization",token);
                        headers.put("Content-Type","application/json");
                        Log.i("Header", headers.toString());
                        return  headers;
                    }

                };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(userProfileRequest);
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait, your details are being fetched");
            progressDialog.show();


            fundButton = view.findViewById(R.id.fund);
            fundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), FundWallet.class);
                    startActivity(intent);

                }
            });


        withdraw = view.findViewById(R.id.withdraw);
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), WithdrawalScreen.class);
                startActivity(intent);

            }
        });

        return view;
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
