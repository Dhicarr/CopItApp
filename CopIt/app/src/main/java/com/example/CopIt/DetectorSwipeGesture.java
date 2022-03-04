package com.example.CopIt;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DetectorSwipeGesture extends GestureDetector.SimpleOnGestureListener {
    private static int MIN_DISTANCE = 100;
    private static int MAX_DISTANCE = 1000;
    ListingViewPresenter context = new ListingViewPresenter();
    public DetectorSwipeGesture(ListingViewPresenter context){
        this.context = context;
    }
    private ListingViewPresenter list = null;
    private MainActivity activity = null;

    public MainActivity getActivity(){
        return activity;
    }

    public void setActivity(MainActivity activity){
        this.activity = activity;
    }

    public ListingViewPresenter getList() {
        return list;
    }

    public void setList(ListingViewPresenter list) {
        this.list = list;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float deltaX = e1.getX() - e2.getX();

        float deltaXAbs = Math.abs(deltaX);

        if (deltaXAbs >= MIN_DISTANCE && deltaXAbs <= MAX_DISTANCE) {
            if (deltaX > 0) {
                //Action for left swipe
                this.context.nextItem();
            } else {
                //Action for right swipe
                this.context.likeItem();

            }
        }
        return true;
    }
}