package com.example.kolanchik;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class GreetingActivity extends AppCompatActivity {


    /////////////////////////////////////*text view*////////////////////////////////////////////////
    TextView nameRace;
    TextView balance;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////*buttons*///////////////////////////////////////////////////
    Button shop;
    Button balance_but;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////*img*/////////////////////////////////////////////////////
    ImageView avatar;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////*for DB*///////////////////////////////////////////////////
    private FirebaseDatabase db;
    private DatabaseReference reference;
    private StorageReference mStorageRef;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Integer need_race_in_blood = 1;
    public static Integer need_race_in_alchemy = 1;
    public static Integer need_race_in_bijouterie = 1;
    public static Integer need_race_in_magic_items = 1;
    public static Integer need_race_in_medicine = 1;
    public static Integer need_race_in_slaves = 1;
    public static Integer need_race_in_technologies = 1;
    public static Integer need_race_in_weapon = 1;

    @SuppressLint("SetTextI18n")
    //при создании активити
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);



        ////////////////////////////////////*text initialization*///////////////////////////////////
        nameRace = findViewById(R.id.nameRace);
        balance = findViewById(R.id.balance);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////*but initialization*////////////////////////////////////
        shop = findViewById(R.id.shop);
        balance_but = findViewById(R.id.balance_but);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////*img initialization*////////////////////////////////////
        avatar = findViewById(R.id.avatar);
        ////////////////////////////////////////////////////////////////////////////////////////////




        ///////////////////////////////////*DB initialization*//////////////////////////////////////
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("race");
        mStorageRef = FirebaseStorage.getInstance().getReference("race");
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////*to shop*///////////////////////////////////////////
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////



        //////////////////////////////////////////*DB query*////////////////////////////////////////
        Query qGetRace = reference.orderByChild("name").equalTo(InitializationActivity.race);

        qGetRace.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Race race = dataSnapshot.getValue(Race.class);

                if (race != null){
                    balance.setText("Balance credits:  " + race.getCredits());
                    nameRace.setText("King of " + race.getName());
                    String url=race.getImg();
                    Glide.with(getApplicationContext()).load(url).into(avatar);

                    need_race_in_blood = race.getNeedInBlood();
                    need_race_in_alchemy = race.getNeedInAlchemy();
                    need_race_in_bijouterie = race.getNeedInBijouterie();
                    need_race_in_magic_items = race.getNeedInMagicItems();
                    need_race_in_medicine = race.getNeedInMedicine();
                    need_race_in_slaves = race.getNeedInSlaves();
                    need_race_in_technologies = race.getNeedInTechnologies();
                    need_race_in_weapon = race.getNeedInWeapon();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////*button to account replenishment*///////////////////////////////
        balance_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingActivity.this, BalanceActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
    }


    //при завершении
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
