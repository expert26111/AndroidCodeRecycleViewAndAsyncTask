package com.example.yoyo.novipel.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yoyo.novipel.R;

/**
 * Created by yoyo on 7/14/2017.
 */

public class Instructions extends Fragment {


    public static Instructions newInstance()
    {
        Instructions fragment = new Instructions();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.instructions, container, false);

        return v;
    }
}