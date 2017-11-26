package com.example.yoyo.novipel.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.yoyo.novipel.R;
import com.example.yoyo.novipel.Website;

/**
 * Created by yoyo on 11/15/2016.
 */

//thepage with three buttons
public class ButtonsPage extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.buttons_layout, container, false);

        Button but = (Button) v.findViewById(R.id.buy_sens);
       // but.setTypeface(Typeface.DEFAULT);
        Button connect = (Button) v.findViewById(R.id.connect);
        //connect.setTypeface(Typeface.DEFAULT);
        Button learn_more = (Button) v.findViewById(R.id.learn_more);
       // learn_more.setTypeface(Typeface.DEFAULT);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Dubiel.ttf");
        but.setTypeface(font);
        connect.setTypeface(font);
        learn_more.setTypeface(font);


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Website.ImitateConnect.class);
                startActivity(intent);
            }
        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    //Uri uri = Uri.parse("https://novipel.wordpress.com/");
                    Intent intent = new Intent(getActivity(), Website.class);
                    startActivity(intent);
                    //Website web = new Website();


                } else {
                    Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_LONG).show();
                }

            }
        });

        return v;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
