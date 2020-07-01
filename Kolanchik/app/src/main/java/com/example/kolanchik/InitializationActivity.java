package com.example.kolanchik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InitializationActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener{

    ///////////////////////////////////////*just buttons*///////////////////////////////////////////
    Button bigRedBut;
    Button centerBut;
    ImageButton cardioBut;
    ImageButton bananaBut;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static String race;
    ////////////////////////////////////*for swipes*////////////////////////////////////////////////
    private GestureDetectorCompat gd;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////*for shake*/////////////////////////////////////////////////
    private static final int SHAKE_SENSITIVITY = 10;
    private SensorManager sensorManager;
    private float accel = SensorManager.GRAVITY_EARTH;
    ////////////////////////////////////////////////////////////////////////////////////////////////



    //when creating activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialization);
        //////////////////////////////////*button initialization*///////////////////////////////////
        bigRedBut = findViewById(R.id.bigRedBut);
        centerBut = findViewById(R.id.centerBut);
        cardioBut = findViewById(R.id.cardioBut);
        bananaBut = findViewById(R.id.bananaBut);
        ////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////*swipe initialization*////////////////////////////////////
        gd = new GestureDetectorCompat(this, this);
        ////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////*initialization for shake*/////////////////////////////////////
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////*animation*///////////////////////////////////////
        Animation anim_rot = AnimationUtils.loadAnimation(this, R.anim.rotate);
        bananaBut.startAnimation(anim_rot);
        Animation anim_scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        cardioBut.startAnimation(anim_scale);
        ////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////*pressing buttons and initializing races*/////////////////////////////
        bigRedBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialization("Beholder");
            }
        });

        centerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialization("Elf");
            }
        });

        cardioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialization("Reaper");
            }
        });

        bananaBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialization("Mon");
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////*tracking shake*////////////////////////////////////////
        sensorManager.registerListener(
                sensorListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        );
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    //после onCreate
    @Override
    protected void onStart() {
        super.onStart();
    }

    //после onStart
    @Override
    protected void onResume() {
        super.onResume();
        ////////////////////////////////////*tracking shake*////////////////////////////////////////
        sensorManager.registerListener(
                sensorListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        );
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    //экран не полностью закрыт (смс), после закрытия смс - onResume
    @Override
    protected void onPause() {
        super.onPause();
    }

    //при полном перекрытии экрана (вначале работает onPause)
    @Override
    protected void onStop() {
        //////////////////////////////////*stop tracking shake*/////////////////////////////////////
        sensorManager.unregisterListener(sensorListener);
        ////////////////////////////////////////////////////////////////////////////////////////////
        super.onStop();
    }

    //пасле возвращения с onStop
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    //при завершении
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////*other function*///////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////*for swipe*/////////////////////////////////////////////
    @Override
    public boolean onTouchEvent(MotionEvent event){
        gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////*initialization race and new activity*//////////////////////////
    public void initialization(String race){
        InitializationActivity.race = race;
        Intent intent = new Intent(InitializationActivity.this, GreetingActivity.class);
        startActivity(intent);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////*for import*////////////////////////////////////////////
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e) {}
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////*when swipe*///////////////////////////////////////////
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(velocityX < 0 && velocityX < velocityY) //swipe on right
            initialization("Kwuntazite");
        return false;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////*when shake*////////////////////////////////////////////
    private final SensorEventListener sensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            float accelPrevious = accel;
            accel = (float) Math.sqrt((double) (x * x + y * y + z * z));
            if (accel - accelPrevious > SHAKE_SENSITIVITY) {
                initialization("Jarulde");
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////*pressing the volume button*////////////////////////////////////
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                initialization("Karlokc");
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                initialization("Karlokc");
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////*override the back button*///////////////////////////////////
    @Override
    public void onBackPressed() {
        openQuitDialog();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////*when you press the back button*/////////////////////////////////
    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                InitializationActivity.this);
        quitDialog.setTitle("Now the application closes. Do you really want this?");

        quitDialog.setPositiveButton("Yes, close it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        quitDialog.setNegativeButton("No, I haven't finished", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        quitDialog.show();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
