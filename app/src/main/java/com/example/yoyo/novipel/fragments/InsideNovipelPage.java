package com.example.yoyo.novipel.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoyo.novipel.R;

/**
 * Created by yoyo on 11/26/2016.
 */


//just a fragmrnt with a picture
public class InsideNovipelPage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.inside_novipel, container, false);

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
