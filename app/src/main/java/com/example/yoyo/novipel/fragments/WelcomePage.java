package com.example.yoyo.novipel.fragments;

//import android.app.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoyo.novipel.R;

/**
 * Created by yoyo on 11/14/2016.
 */
//the fragment with the circular button in front
public class WelcomePage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.welcoming_window, container, false);
//        Button next = (Button) v.findViewById(R.id.next);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MainActivity activity = (MainActivity) getActivity();
//                activity.nextWindow(view);
//            }
//        });
        // get the actual pixel dimensions
        DisplayMetrics metrics = new DisplayMetrics();

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.i("WIDTH: ", metrics.widthPixels + "");
        return v;
    }

}




































































































