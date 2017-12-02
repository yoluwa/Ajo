package com.example.oluwaseun.ajo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oluwaseun.ajo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JoinGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JoinGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JoinGroupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public JoinGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JoinGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JoinGroupFragment newInstance(String param1, String param2) {
        JoinGroupFragment fragment = new JoinGroupFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_join_group, container, false);

        // NOTE : We are calling the onFragmentInteraction() declared in the MainActivity
        // ie we are sending "Fragment 1" as title parameter when fragment1 is activated
        if (mListener != null) {
            mListener.onFragmentInteraction("Join Contribution Group");
        }

        // Here we will can create click listners etc for all the gui elements on the fragment.
        // For eg: Button btn1= (Button) view.findViewById(R.id.frag1_btn1);
        // btn1.setOnclickListener(...

        CardView group1 = view.findViewById(R.id.group1);
        CardView group2 =  view.findViewById(R.id.group2);
        CardView group3 = view.findViewById(R.id.group3);

//        group1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        group2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        group3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        Button group1Accept = view.findViewById(R.id.group1Accept);
        Button group2Accept = view.findViewById(R.id.group2Accept);
        Button group3Accept = view.findViewById(R.id.group3Accept);
        Button group1Decline = view.findViewById(R.id.group1Decline);
        Button group2Decline = view.findViewById(R.id.group2Decline);
        Button group3Decline = view.findViewById(R.id.group3Decline);

//        group1Accept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        group2Accept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        group3Accept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        group1Decline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        group2Decline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        group3Decline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return view;
    }
//
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
