package com.example.kolanchik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    ImageView logo;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        textView = findViewById(R.id.pilligrim);

        /////////////////////////////////////*animation*////////////////////////////////////////////
        Animation anim_img = AnimationUtils.loadAnimation(this, R.anim.alpha_img);
        logo.startAnimation(anim_img);

        Animation anim_text = AnimationUtils.loadAnimation(this, R.anim.alpha_text);
        textView.startAnimation(anim_text);
        ////////////////////////////////////////////////////////////////////////////////////////////

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, InitializationActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}
