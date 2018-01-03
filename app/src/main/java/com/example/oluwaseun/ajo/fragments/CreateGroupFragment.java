package com.example.oluwaseun.ajo.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.oluwaseun.ajo.R;
import com.example.oluwaseun.ajo.activities.SessionManager;
import com.example.oluwaseun.ajo.utils.Endpoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateGroupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public String groupNameString, member1String, member2String, member3String,
            member4String, member5String;
   // public String membersString;

    public String reason, frequency;
    private ProgressDialog progressDialog;

    public CreateGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateGroupFragment newInstance(String param1, String param2) {
        CreateGroupFragment fragment = new CreateGroupFragment();
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
        View view= inflater.inflate(R.layout.fragment_create_group, container, false);

        // NOTE : We are calling the onFragmentInteraction() declared in the MainActivity
        // ie we are sending "Fragment 1" as title parameter when fragment1 is activated
        if (mListener != null) {
            mListener.onFragmentInteraction("Create Contribution Group");
        }

        // Here we will can create click listners etc for all the gui elements on the fragment.
        // For eg: Button btn1= (Button) view.findViewById(R.id.frag1_btn1);
        // btn1.setOnclickListener(...


        EditText groupName = (EditText) view.findViewById(R.id.groupName);
        groupNameString = groupName.getText().toString().trim();
        EditText member1 = view.findViewById(R.id.member1);
        member1String = member1.getText().toString().trim();
        EditText member2 = view.findViewById(R.id.member2);
        member2String = member2.getText().toString().trim();
        EditText member3 = view.findViewById(R.id.member3);
        member3String = member3.getText().toString().trim();
        EditText member4 = view.findViewById(R.id.member4);
        member4String = member4.getText().toString().trim();
        EditText member5 = view.findViewById(R.id.member5);
        member5String = member5.getText().toString().trim();

        RadioButton house = (RadioButton) view.findViewById(R.id.house);
        RadioButton family = (RadioButton) view.findViewById(R.id.family);
        RadioButton gift = (RadioButton) view.findViewById(R.id.gift);


        if (house.isChecked()){
            reason = house.getText().toString();
        }
        else if(family.isChecked()){
            reason = family.getText().toString();
        }
        else if(gift.isChecked()){
            reason = gift.getText().toString();
        }
        else{
            reason = "none";
        }

        RadioButton daily = (RadioButton) view.findViewById(R.id.daily);
        RadioButton weekly = (RadioButton) view.findViewById(R.id.weekly);
        RadioButton monthly = (RadioButton) view.findViewById(R.id.monthly);

        if (daily.isChecked()){
            frequency = daily.getText().toString();
        }
        else if(weekly.isChecked()){
            frequency = weekly.getText().toString();
        }
        else if(monthly.isChecked()){
            frequency = monthly.getText().toString();
        }
        else{
            frequency = "none";
        }


        //Code for the Button Method.. I saw it online and modified it
        Button createGroup = (Button) view.findViewById(R.id.createGroup);
        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here you set what you want to do when user clicks your button,

                //expected payload for a group is name, reason, frequency and members which
                // is a string of the emails to be added separated by a comma with no spaces
                // in between

                //validation will be done after the stuff works as done in registration
                //authorization header stuff is not yet done

                JSONObject group = new JSONObject();
                try {
                    group.put("name", groupNameString);
                    group.put ("reason", reason);
                    group.put("frequency", frequency);
                    group.put("members", member1String);
                }
                catch (JSONException e){

                }

                //Volley Request for the group




                JsonObjectRequest createGroupRequest = new JsonObjectRequest(Endpoint.CREATE_GROUP, group,

                        //when you can access the server
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject serverResponse) {
                                try {
                                    String status = serverResponse.getString("status");

                                    //Log.d("Reg2",status);
                                    if (status.equals("success")) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(),
                                                "Contribution Group Created Successfully", Toast.LENGTH_LONG).show();
                                        //getContect() was used because i do not know how to reference the
                                        // activity that this fragemnt extends

                                        //There should be an intent to take to a reusable fragment
                                        // that displays the detail of a contribution group

                                        //Intent in = new Intent(getContext(), Register3Activity.class);
                                        //in.putExtra("email", emailString);
                                        //startActivity(in);
                                    } else {
                                        JSONObject data = serverResponse.getJSONObject("data");
//                                Log.i("Data response:", data.toString());
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(),
                                                "Contribution Group Not Created", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    progressDialog.dismiss();
//                            Log.e("Error:", e.toString());
                                    Toast.makeText(getContext(),
                                            "Sever not Responding", Toast.LENGTH_LONG).show();
                                }

                            }
                        },

                        //when you cant register the user
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

//                        Log.i("Error:", error.toString());

                                progressDialog.dismiss();
                                Toast.makeText(getContext(),
                                        "Unable to access the Server Try again later.", Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError{
                        Map<String, String> headers = new HashMap<>();
                        SessionManager sessionManager = new SessionManager(getContext());
                        //get current user access token
                        String token = sessionManager.getUserDetails().toString();
                        headers.put("Content-Type","application/json");
                        headers.put("Authorization",token);
                        return  headers;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(createGroupRequest);
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Please wait, your contribution group is being created.");
                progressDialog.show();



            }
        });


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
