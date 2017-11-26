package com.example.yoyo.novipel.utility;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by yoyo on 6/1/2017.
 */

public class ProgressBarAnimation extends Animation
{

    private ImageView progressBar;
    private float from;
    private float  to;

    public ProgressBarAnimation(ImageView progressBar, float from, float to) {
        super();
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
       // progressBar.setProgress((int) value);
        progressBar.setTranslationY(value);
      //  progressBar.set
    }


}
