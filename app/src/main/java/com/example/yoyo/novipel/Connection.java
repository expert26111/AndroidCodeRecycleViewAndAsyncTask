package com.example.yoyo.novipel;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by simon on 12/19/16.
 */


//totally useless class
public class Connection {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public Connection(Context context) {
        sharedPref = context.getSharedPreferences(context.getString(R.string.preferece_file_key), Context.MODE_PRIVATE);
    }

    // stub log in
    public void connect() {
        editor = sharedPref.edit();
        editor.putBoolean("isConnected", true);
        editor.commit();
    }

    // stub log out
    public void disconnect() {
        editor = sharedPref.edit();
        editor.putBoolean("isConnected", false);
        editor.commit();
    }

    public boolean isConnected() {
        boolean result = false;
        return sharedPref.getBoolean("isConnected", result);
    }
}
