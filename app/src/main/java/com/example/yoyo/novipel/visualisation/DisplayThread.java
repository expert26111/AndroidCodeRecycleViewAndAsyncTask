package com.example.yoyo.novipel.visualisation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Responsible for screen painting.
 */
public class DisplayThread extends Thread {
    SurfaceHolder mSurfaceHolder;
    Paint mBackgroundPaint;
    Paint mForegroundPaint;
    Paint mForegroundPaintRed;

    private Paint[] paintValues = new Paint[25];

    private int width;
    private int height;
    private int rectHeight;
    private Random random = new Random();

    private VisualisationHandler visualisationHandler;

    //Delay amount between screen refreshes
    final long DELAY = 0;

    boolean isRunning;
    int counter;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
        this.rectHeight = (height) / 25;
    }

    public DisplayThread(SurfaceHolder surfaceHolder, VisualisationHandler visualisationHandler) {
        mSurfaceHolder = surfaceHolder;

        this.visualisationHandler = visualisationHandler;

        //black painter below to clear the screen before the game is rendered
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setARGB(255, 0, 0, 0);

        mForegroundPaint = new Paint();
        mForegroundPaint.setARGB(255, 255, 255, 255);
        mForegroundPaint.setStyle(Paint.Style.STROKE);
        mForegroundPaint.setStrokeCap(Paint.Cap.ROUND);
        mForegroundPaint.setStrokeWidth(3.0f);
        mForegroundPaint.setAntiAlias(true);

        mForegroundPaintRed = new Paint();
        mForegroundPaintRed.setARGB(255, 255, 0, 0);
        isRunning = true;

        counter = 0;


        for (int v = 0; v < 25; v++) {
            //main.leftValues[v] = (byte) random.nextInt(100);
            //main.rightValues[v] = (byte) random.nextInt(100);
            paintValues[v] = new Paint();
            paintValues[v].setARGB(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            paintValues[v].setStyle(Paint.Style.STROKE);
            paintValues[v].setStrokeCap(Paint.Cap.ROUND);
            paintValues[v].setStrokeWidth(3.0f);
            paintValues[v].setAntiAlias(true);
        }

    }

    /**
     * This is the main nucleus of our program.
     * From here will be called all the method that are associated with the display in GameEngine object
     */


    private void performUSBDraw() {
        //Looping until the boolean is false
        while (isRunning) {
            counter += 3;
            //Log.i("WH", width + ", " + height);
            //Updates the game objects buisiness logic

            //locking the canvas
            Canvas canvas = mSurfaceHolder.lockCanvas(null);

            if (canvas != null) {
                //Clears the screen with black paint and draws object on the canvas
                synchronized (mSurfaceHolder) {

                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mBackgroundPaint);
                    //AppConstants.GetEngine().Draw(canvas);

                    //canvas.drawRect(0, 0 * rectHeight, leftValues[0], rectHeight, paintValues[0]);
                    //canvas.drawRect(0, 1 * rectHeight, leftValues[1], rectHeight + 1 * rectHeight, paintValues[1]);

                    float x1, x2, x3, y1, y2, y3;
                    Path path;

                    for (int y = 0; y < 24; y++) {



                        path = new Path();
                        x1 = visualisationHandler.leftValues[y];
                        y1 = rectHeight * y;
                        Log.i("x1", x1 + "");
                        Log.i("y1", y1 + "");

                        x3 = visualisationHandler.leftValues[y + 1];
                        y3 = rectHeight * (y + 1);

                        x2 = (x3 + x1) / 2;
                        y2 = (y3 + y1) / 2;
                        path.moveTo(x1, y1);
                        path.quadTo(x2, y2, x3, y3);
                        canvas.drawPath(path, mForegroundPaint);

                        path = new Path();
                        x1 = width - visualisationHandler.rightValues[y];
                        y1 = rectHeight * y;

                        x3 = width - visualisationHandler.rightValues[y + 1];
                        y3 = rectHeight * (y + 1);

                        x2 = (x3 + x1) / 2;
                        y2 = (y3 + y1) / 2;
                        path.moveTo(x1, y1);
                        path.quadTo(x2, y2, x3, y3);
                        canvas.drawPath(path, mForegroundPaint);


                        //canvas.drawLine(leftValues[y] * 4, rectHeight * y + rectHeight / 2, leftValues[y+1] * 4, rectHeight * (y + 1) + rectHeight / 2, paintValues[y]);
                        //canvas.drawRect(0, rectHeight * y, leftValues[y] * 4, rectHeight + rectHeight * y, paintValues[y]);
                        //canvas.drawRect(width - rightValues[y] * 4 , rectHeight * y, width, rectHeight + rectHeight * y, paintValues[y]);
                    }


                }

                //unlocking the Canvas
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }

            //delay time
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                //TODO: Log
            }
        }
    }

    private void performTestDraw() {
        int count = 0;
        while (isRunning) {
            //locking the canvas
            Canvas canvas = mSurfaceHolder.lockCanvas(null);

            synchronized (mSurfaceHolder) {

                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mBackgroundPaint);

                canvas.drawRect(50 + (count++ * 2), 50, 100, 100, mForegroundPaint);


            }

            //unlocking the Canvas
            mSurfaceHolder.unlockCanvasAndPost(canvas);

        }

    }

    @Override
    public void run() {
        //performTestDraw();
        performUSBDraw();


    }

    /**
     * @return whether the thread is running
     */
    public boolean IsRunning() {
        return isRunning;
    }

    /**
     * Sets the thread state, false = stoped, true = running
     **/
    public void SetIsRunning(boolean state) {
        isRunning = state;
    }
}