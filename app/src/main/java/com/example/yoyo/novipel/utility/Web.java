package com.example.yoyo.novipel.utility;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.yoyo.novipel.R;
import com.example.yoyo.novipel.fragments.Folder;

public class Web extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        Intent startingIntent = getIntent();
        String url = startingIntent.getStringExtra("website");


        WebView webView = (WebView) findViewById(R.id.webView);
      //  WebSettings w = webView.getSettings();
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
       // w.setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl(url);
    }

//    @Override
//    public void onBackPressed() {
//         getFragmentManager().popBackStack();
//        Fragment selectedFragment = Folder.newInstance();
//        getFragmentManager().popBackStack();
//        getFragmentManager().popBackStack();
//       // FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.window, selectedFragment);
//        transaction.commit();
//    }

}

