package com.example.CopIt;

import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class textAnimation {

    public TextView productTitle;
    public TextView productPrice;
    public TextView productDescription;

    protected void onPostExecute(TextView pTitle, TextView pPrice, TextView pDescription) {
        /* this.imageView.setImageDrawable(result); */


        Animation myFadeInAnimation = AnimationUtils.loadAnimation(pTitle.getContext(), R.anim.fade_in);
        Animation my1FadeInAnimation = AnimationUtils.loadAnimation(pPrice.getContext(), R.anim.fade_in);
        Animation my2FadeInAnimation = AnimationUtils.loadAnimation(pDescription.getContext(), R.anim.fade_in);


        pTitle.startAnimation(myFadeInAnimation);
        pPrice.startAnimation(my1FadeInAnimation);
        pDescription.startAnimation(my2FadeInAnimation);


    }
}
