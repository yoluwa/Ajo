package com.example.oluwaseun.ajo.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.english.ContributionGroupActivity;
import com.example.oluwaseun.ajo.activities.english.DashboardActivity;
import com.example.oluwaseun.ajo.activities.english.LoginActivity;

import co.paystack.android.PaystackSdk;


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

        View view= inflater.inflate(R.layout.fragment_home, container, false);

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


        // Here we will can create click listners etc for all the gui elements on the fragment.
        // For eg: Button btn1= (Button) view.findViewById(R.id.frag1_btn1);
        // btn1.setOnclickListener(...






        //JSONRequest to get details for user profile
//
//        JsonObjectRequest createGroupRequest = new JsonObjectRequest(Endpoint.USER_PROFILE, group,
//                //when you can access the server
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject serverResponse) {
//                        try {
//                            String status = serverResponse.getString("status");
//                            JSONObject data = serverResponse.getJSONObject("data");
//                            String serverData = data.toString();
//                            Log.i("Status",status);
//                            Log.i("Data response:",data.toString() );
//                            if (status.equals("success")) {
//                                progressDialog.dismiss();
//                                //Toast.makeText(getContext(), "Contribution Group Created Successfully", Toast.LENGTH_LONG).show();
//                                //Intent in = new Intent(Register2Activity.this, Register3Activity.class);
//                                //startActivity(in)
//                            }
//                            else {
//                                Log.i("Status",status);
//                                Log.i("Data response:",data.toString() );
//                                progressDialog.dismiss();
//                                //Toast.makeText(getContext(), "Contribution Group Not Created Successfully ", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                        catch (JSONException e) {
//                            progressDialog.dismiss();
////                            Log.e("Error:", e.toString());
//                            //Toast.makeText(getContext(), "Sever not Responding", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                },
//
//                //when you cant register the user
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.i("Error:", error.toString());
//                        progressDialog.dismiss();
//                        Toast.makeText(getContext(),
//                                "Unable to access the Server Try again later.", Toast.LENGTH_LONG).show();
//                    }
//                }
//        ){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                SessionManager sessionManager = new SessionManager(getContext());
//                //get current user access token
//                String token = sessionManager.getUserToken().get("token");
//                headers.put("Authorization",token);
//                headers.put("Content-Type","application/json");
//                Log.i("Header", headers.toString());
//                return  headers;
//            }
//
//        };


        TextView name = (TextView) view.findViewById (R.id.name);
        TextView email = (TextView) view.findViewById (R.id.email);
        TextView phoneNumber = (TextView) view.findViewById (R.id.phoneNumber);
        TextView accountNumber = (TextView) view.findViewById (R.id.accountNumber);
        TextView walletBalance = (TextView) view.findViewById (R.id.walletBalance);
        Button fundWallet = (Button) view.findViewById (R.id.fund);

        fundWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(HomeFragment.this, ContributionGroupActivity.class);
                startActivity(in);


            }
        });



//        name.setText(serverData.name);
//        email.setText();
//        phoneNumber.setText();
//        accountNumber.setText();
//        walletBalance.setText();
//
//





        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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
