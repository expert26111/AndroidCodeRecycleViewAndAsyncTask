package com.example.yoyo.novipel.visualisation;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.example.yoyo.novipel.MainActivity;
import com.example.yoyo.novipel.R;
import com.example.yoyo.novipel.TemporaryActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by simon on 6/22/17.
 */

public class VisualisationHandler implements SurfaceHolder.Callback {

    /*************
     *  DISPLAY  *
     ************/
    private DisplayThread displayThread;
    private SurfaceView visualisationView;
    private SurfaceHolder surfaceHolder;

    /*******
     * USB *
    *******/
    private final static int DATA_LENGTH = 50;
    protected final static int DEVICE_VENDOR_ID = 1155;
    protected final static int DEVICE_PRODUCT_ID = 22336;
    public static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    public static final String ACTION_USB_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
    private PendingIntent permissionIntent;

    private UsbEndpoint usbDeviceToHost, usbHostToDevice;

    // the global device which will be initialized via a loop
    // when the device has been found, it will be stored here
    public UsbDevice usbDevice;

    private UsbManager usbManager;
    private Thread usbReaderThread;

    // protected since accessed from UsbReceiver
    public volatile boolean readerThreadIsRunning;
    public UsbInterface usbInterface;
    public UsbDeviceConnection usbConnection;
    // variables for storing the bytes read
    private byte[] readBytes = new byte[106];
    public volatile byte[] leftValues = new byte[25];
    public volatile byte[] rightValues = new byte[25];



    /*********
     * OTHER *
     ********/
    private AppCompatActivity appCompatActivity;
    private Context context;
    private TemporaryActivity main;

    public TemporaryActivity getMain() {
        return main;
    }

    public VisualisationHandler(AppCompatActivity appCompatActivity) {
        this.context = appCompatActivity;
        this.appCompatActivity = appCompatActivity;
        this.main = (TemporaryActivity) appCompatActivity;
    }

    public void init() {
        visualisationView = (SurfaceView) appCompatActivity.findViewById(R.id.surfaceView);
        surfaceHolder = visualisationView.getHolder();

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        surfaceHolder.addCallback(this);

        displayThread = new DisplayThread(surfaceHolder, this);
        visualisationView.setFocusable(true);

        usbManager = (UsbManager) appCompatActivity.getSystemService(Context.USB_SERVICE);

        UsbReceiver usbReceiver = new UsbReceiver();
        usbReceiver.setActivityHandler(this);

        // set up the USB permission here
        permissionIntent = PendingIntent.getBroadcast(main, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        main.registerReceiver(usbReceiver, filter);

        // set up when you unplug the USB here
        IntentFilter usbIntentFilter = new IntentFilter("android.hardware.usb.action.USB_DEVICE_DETACHED");
        main.registerReceiver(usbReceiver, usbIntentFilter);
    }

    // set up the usb connection to the device and start the permission for adding the USB
    public void initUSB() {
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();

        for (Map.Entry<String, UsbDevice> entry : deviceList.entrySet()) {
            UsbDevice ud = entry.getValue();
            main.addText("DEVICE VENDOR ID: " + ud.getVendorId());
            main.addText("DEVICE PRODUCT ID: " + ud.getProductId());

            if (ud.getVendorId() == DEVICE_VENDOR_ID && ud.getProductId() == DEVICE_PRODUCT_ID) {
                usbDevice = ud;
                break;
            }
        }

        if (usbDevice != null) {

            usbInterface = usbDevice.getInterface(1);
            if (usbInterface == null) {
                main.addText("usbInterface is null");
            }
            usbHostToDevice = usbInterface.getEndpoint(0);
            usbDeviceToHost = usbInterface.getEndpoint(1);

            // this will ask the permisiion to the user
            usbManager.requestPermission(usbDevice, permissionIntent);
        }
    }


    public boolean isCorrectDevice() {
        return usbDevice.getVendorId() == DEVICE_VENDOR_ID &&
                usbDevice.getProductId() == DEVICE_PRODUCT_ID;
    }

    public void initUSBAcceptedPermission() {

        usbConnection = usbManager.openDevice(usbDevice);
        if (usbConnection == null) {
            //addText("usbConnection is null");
        } else if (isCorrectDevice()) {
            main.addText("initUSBAcceptedPermission()...");
            //addText("found device");
            usbConnection.claimInterface(usbInterface, true);
            startReaderThread();
            sendStartMessage();
        }
    }


    public void stopUsbReaderThread() {
        byte[] stopMessage = new byte[]{(byte) 0x82, (byte) 0x80, 0x00, 0x00, 0x01, 0x00};
        usbConnection.bulkTransfer(usbHostToDevice, stopMessage, stopMessage.length, 0);
        readerThreadIsRunning = false;
    }
    /*
     * This method is used for debugging purposes to find out about the device
     * connected and to display info about it via a Handle
     */
    public void getDeviceInfo() {

        UsbManager manager = (UsbManager) main.getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();

        for (Map.Entry<String, UsbDevice> entry : deviceList.entrySet()) {
            //addText(entry.getKey() + " --> " + entry.getValue());
            UsbDevice ud = entry.getValue();
            main.addText("DEVICE VENDOR ID: " + ud.getVendorId());
            main.addText("DEVICE PRODUCT ID: " + ud.getProductId());
        }
    }

    // this assumes we have a working connection
    private void sendStartMessage() {
        byte[] startMessage = new byte[]{(byte) 0x81, (byte) 0x80, 0x00, 0x00, 0x01, 0x00};
        usbConnection.bulkTransfer(usbHostToDevice, startMessage, startMessage.length, 0);
    }

    private void parseChunk(byte[] message) {
        //StringBuilder sb = new StringBuilder();
        if (message.length == 6 && message[0] == (byte) 0x80 && message[1] == (byte) 0x81) {
            //sb.append("This message is an ACKNOWLEDGE message...\n");
            int numberOfDatas = calculateNumberFromLittleEndianBytes(message[2], message[3]);
            //sb.append("There are " + numberOfDatas + " datas...\n");
        }

        if (message.length == 106 && message[0] == (byte) 0x80 && message[1] == (byte) 0x82) {
            int r = 0;
            for (int i = 0; i < DATA_LENGTH; i += 2) {
                short dataValue = calculateNumberFromLittleEndianBytes(message[4 + i], message[4 + i + 1]);

                if (i < 25) {
                    leftValues[i] = ((byte) (dataValue & 0x7F));

                } else if (i > 24) {

                    rightValues[r++] = ((byte) (dataValue & 0x7F));
                }
            }
        }
    }

    static short calculateNumberFromLittleEndianBytes(byte byte1, byte byte2) {
        short result = 0;
        result |= byte2;
        result <<= 8;
        short rbyte1 = (short) (byte1 & 0x00ff);
        result |= rbyte1;

        return result;
    }

    private void startReaderThread() {
        if (!readerThreadIsRunning) {
            readerThreadIsRunning = true;
            usbReaderThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (readerThreadIsRunning) {

                        // reinitialize read value byte array
                        Arrays.fill(readBytes, (byte) 0);
                        // get some data
                        final int recvBytes = usbConnection.bulkTransfer(usbDeviceToHost, readBytes, readBytes.length, 3000);

                        if (recvBytes > 0) {
                            parseChunk(readBytes);

                        }
                    }
                }
            });
            usbReaderThread.start();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        displayThread.setWidth(visualisationView.getWidth());
        displayThread.setHeight(visualisationView.getHeight());

        main.addText("visualisationView: width: " + visualisationView.getWidth());
        main.addText("visualisationView: height: " + visualisationView.getHeight());


        if (!displayThread.IsRunning()) {

            displayThread = new DisplayThread(surfaceHolder, this);
            displayThread.start();
        } else {
            displayThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // not used at the momeent
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // not used at the momeent
    }

    public void onDestroy() {
        readerThreadIsRunning = false;
    }
}
