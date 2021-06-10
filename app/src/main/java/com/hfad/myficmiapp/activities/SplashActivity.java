package com.hfad.myficmiapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hfad.myficmiapp.R;

public class SplashActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    TextView name, slogan;
    ImageView ficmi;
    private  static  int SPLASH_SCREEN=5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //setting animation on our text
        name = findViewById(R.id.church);
        slogan = findViewById(R.id.slogan);
        ficmi = findViewById(R.id.ficmi);

        ficmi.setAnimation(topAnim);
        name.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        },SPLASH_SCREEN);
    }
}