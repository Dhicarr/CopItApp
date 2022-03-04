package com.example.CopIt;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ImageDownloader extends AsyncTask<Object, Void, Drawable> {

    ImageView imageView = null;

    URL url = null;

    @Override
    protected Drawable doInBackground(Object... objects) {
        this.imageView = (ImageView) objects[0];
        System.out.println(objects[1]);
        try {
            InputStream input = (InputStream)  new URL((String) objects[1]).getContent();
            Drawable image = Drawable.createFromStream(input, "google");
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Drawable result) {
        //this.imageView.setImageDrawable(result);
        imageView.setImageDrawable(result);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this.imageView.getContext() , R.anim.fade_in);
        imageView.startAnimation(myFadeInAnimation);
    }
}
