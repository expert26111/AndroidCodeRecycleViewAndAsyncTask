package com.example.yoyo.novipel.utility;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by yoyo on 7/20/2017.
 */

public class Data
{

    private String title;
    private String time;
    private String explanation;
    private ImageView image;
    private String imgLink;
    private String linkStory;

//    public Data(String title, String time, String explanation, ImageView image, String linkStory)
//    {
//        this.title = title;
//        this.time = time;
//        this.explanation = explanation;
//        this.image = image;
//        this.linkStory  = linkStory;
//    }


    public String getLinkStory() {
        return linkStory;
    }

    public String getImgLink()
    {
        return imgLink;
    }

    public void setImageFromDrawable(Drawable res)
    {
        this.image.setImageDrawable(res);
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Data(String title, String time, String explanation, ImageView image)
    {
        this.title = title;
        this.time = time;
        this.explanation = explanation;
        this.image = image;
    }

    public Data(String title, String time, String explanation,String imgLink)
    {
        this.title = title;
        this.time = time;
        this.explanation = explanation;
        this.image = image;
    }

    public Data(String title, String time, String explanation, ImageView image, String imgLink, String linkStory)
    {
        this.title = title;
        this.time = time;
        this.explanation = explanation;
        this.image = image;
        this.imgLink = imgLink;
        this.linkStory  = linkStory;

    }


//        public int getImage() {
//        return image;
//    }
//
//    public void setImage(int image) {
//        this.image = image;
//    }
//
//    public Data(String title, String time, String explanation, int image)
//    {
//        this.title = title;
//        this.time = time;
//        this.explanation = explanation;
//        this.image = image;
//    }

    public Data(String title, String time, String explanation) {
        this.title = title;
        this.time = time;
        this.explanation = explanation;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String name)
    {
        this.title = name;
    }

}
