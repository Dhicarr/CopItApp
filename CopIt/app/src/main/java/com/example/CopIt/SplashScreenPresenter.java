package com.example.CopIt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenPresenter extends AppCompatActivity {

    Animation moveAnim;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //To set the splash screen fullscreen
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        moveAnim = AnimationUtils.loadAnimation(this, R.anim.move);
        logo = findViewById(R.id.logo_splashscreen);
        logo.setAnimation(moveAnim);

        new Handler().postDelayed(new Runnable() {
            public void run(){
                Intent toMainPage = new Intent(SplashScreenPresenter.this, MainActivity.class);
                startActivity(toMainPage);
                finish();
            }
        }, 1000);
    }
}
