package com.example.yoyo.novipel.visualisation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by simon on 3/19/17.
 */

public class UsbReceiver extends BroadcastReceiver {

    VisualisationHandler visualisationHandler;

    public void setActivityHandler(VisualisationHandler visualisationHandler) {
        this.visualisationHandler = visualisationHandler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (VisualisationHandler.ACTION_USB_PERMISSION.equals(action)) {
            synchronized (this) {
                UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    if (device != null) {
                        visualisationHandler.initUSBAcceptedPermission();
                    }
                } else {
                    //main.addText("permission denied for device " + device);
                }
            }
        } else if (VisualisationHandler.ACTION_USB_DETACHED.equals(action)) {

            if (visualisationHandler.isCorrectDevice()) {
                visualisationHandler.readerThreadIsRunning = false;
                visualisationHandler.usbConnection.releaseInterface(visualisationHandler.usbInterface);
                visualisationHandler.usbConnection.close();
                visualisationHandler.getMain().addText("device unplugged");
                Toast.makeText(context, "Plugged out USB!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
