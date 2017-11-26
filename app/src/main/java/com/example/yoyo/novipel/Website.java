package com.example.yoyo.novipel;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

//the fragment that shows the wordpress website
public class Website extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://novipel.wordpress.com/");
        setContentView(webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    //the page which on press connects show fading in and out animation
    public static class ImitateConnect extends AppCompatActivity {
        private static final int PROGRESS = 0x1;
        private long fileSize = 0;
        private ProgressBar mProgress;
        private boolean mProgressStatus = false;

        private Handler mHandler = new Handler();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bluetooth);

            mProgress = (ProgressBar) findViewById(R.id.progress_bar);

            //  mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                public void run()
                {
                    while (!mProgressStatus)
                    {
                        mProgressStatus = doSomeTasks();
                        if (mProgressStatus) {
                        mHandler.post(new Runnable()
                        {
                            public void run()
                            {
                                mProgress.setVisibility(View.INVISIBLE);

                            }
                        });
                    }
                    }
                }
            }).start();

        }


        // file download simulator... a really simple
        public boolean doSomeTasks() {

            while (fileSize <= 1000000000) {

                fileSize++;
                if(fileSize==1000000000)
                {
                    return true;
                }

            }
            return true;

            //end of doSomeTasks() method
        }

        //end of Android class
    }
}
