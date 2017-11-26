package com.example.yoyo.novipel.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yoyo.novipel.R;
import com.example.yoyo.novipel.TemporaryActivity;

/**
 * Created by yoyo on 11/22/2016.
 */

public class ThisIsSensPage extends Fragment {
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.this_is_sense, container, false);

        FloatingActionButton simonsBut = (FloatingActionButton) v.findViewById(R.id.connectSens);
        simonsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TemporaryActivity.class);
                startActivity(intent);
            }
        });


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


        float density = getActivity().getResources().getDisplayMetrics().density;
        Log.i("DENSITY", density + "");
        if (density == 0.75) {
            Log.i("THISISSENSPAGE", "Density is LDPI");
        } else if (density == 1.0) {
            Log.i("THISISSENSPAGE", "Density is MDPI");
        } else if (density == 1.5) {
            Log.i("THISISSENSPAGE", "Density is HDPI");
        } else if (density == 2.0) {
            Log.i("THISISSENSPAGE", "Density is XHDPI");
        } else if (density == 3.0) {
            Log.i("THISISSENSPAGE", "Density is XXHDPI");
        } else if (density == 4.0) {
            Log.i("THISISSENSPAGE", "Density is XXXHDPI");
        }




// return 0.75 if it's LDPI
// return 1.0 if it's MDPI
// return 1.5 if it's HDPI
// return 2.0 if it's XHDPI
// return 3.0 if it's XXHDPI
// return 4.0 if it's XXXHDPI


        return v;
    }


    public static ThisIsSensPage newInstance()
    {
        ThisIsSensPage fragment = new ThisIsSensPage();
        return fragment;
    }



}
