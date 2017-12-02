package com.example.oluwaseun.ajo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.oluwaseun.ajo.R;

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

        EditText groupName = (EditText) view.findViewById(R.id.group1Name);
        EditText member1 = view.findViewById(R.id.member1);
        EditText member2 = view.findViewById(R.id.member2);
        EditText member3 = view.findViewById(R.id.member3);
        EditText member4 = view.findViewById(R.id.member4);
        EditText member5 = view.findViewById(R.id.member5);

        RadioButton house = (RadioButton) view.findViewById(R.id.house);
        RadioButton family = (RadioButton) view.findViewById(R.id.family);
        RadioButton gift = (RadioButton) view.findViewById(R.id.gift);

        String reason, frequency;

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
