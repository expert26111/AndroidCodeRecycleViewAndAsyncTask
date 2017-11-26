//package com.example.yoyo.novipel;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//public class SwipeInformation extends Fragment
//{
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState)
//    {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_swipe_information, container, false);
//    }
//
//}


package com.example.yoyo.novipel.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoyo.novipel.R;

/**
 * Created by yoyo on 11/15/2016.
 */

public class SwipeInformation extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_swipe_information, container, false);

//        Button next = (Button) v.findViewById(R.id.next);
//        next.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                MainActivity activity = (MainActivity) getActivity();
//                activity.nextWindow(view);
//            }
//        });

        return v;
        }
    }