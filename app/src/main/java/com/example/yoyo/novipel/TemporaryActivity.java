package com.example.yoyo.novipel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yoyo.novipel.visualisation.VisualisationHandler;

public class TemporaryActivity extends AppCompatActivity {

    private VisualisationHandler visualisationHandler;

    // ui
    private Button deviceInfoButton;
    private Button startDeviceButton;
    private Button stopDeviceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary);

        (visualisationHandler = new VisualisationHandler(this)).init();


        startDeviceButton = (Button) findViewById(R.id.start);
        startDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visualisationHandler.initUSB();
            }
        });

        stopDeviceButton = (Button) findViewById(R.id.stop);
        stopDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visualisationHandler.stopUsbReaderThread();

            }
        });

        deviceInfoButton = (Button) findViewById(R.id.info);
        deviceInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visualisationHandler.getDeviceInfo();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        visualisationHandler.onDestroy();
    }

    public void addText(String text) {
        LinearLayout container = (LinearLayout) findViewById(R.id.textContainer);

        TextView tv = new TextView(TemporaryActivity.this);
        tv.setText(text);
        tv.setTextSize(10);
        container.addView(tv);
    }
}
