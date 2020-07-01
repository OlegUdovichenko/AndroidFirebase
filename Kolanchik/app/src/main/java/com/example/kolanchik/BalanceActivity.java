package com.example.kolanchik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BalanceActivity extends AppCompatActivity {

    /////////////////////////////////////*text view*////////////////////////////////////////////////
    TextView balance;
    TextView replenish_line;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////*buttons*///////////////////////////////////////////////////
    Button replenish;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////*for DB*///////////////////////////////////////////////////
    private FirebaseDatabase db;
    private DatabaseReference reference;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        ////////////////////////////////////*text initialization*///////////////////////////////////
        balance = findViewById(R.id.balance);
        replenish_line = findViewById(R.id.replenish_line);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////*but initialization*////////////////////////////////////
        replenish = findViewById(R.id.replenish);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////*DB initialization*//////////////////////////////////////
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("race");
        ////////////////////////////////////////////////////////////////////////////////////////////



        //////////////////////////////////////////*DB query*////////////////////////////////////////
        Query qGetRace = reference.orderByChild("name").equalTo(InitializationActivity.race);

        qGetRace.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                final Race race = dataSnapshot.getValue(Race.class);
                if (race != null){
                    balance.setText("Your balance credits:  " + race.getCredits());
                }
                ////////////////////////////*button to account replenishment*///////////////////////////////
                replenish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatabaseReference numMesasReference = dataSnapshot.getRef().child("credits");
                        try {
                            String temp = replenish_line.getText().toString();
                            Integer credits = race.getCredits() + Integer.parseInt(temp);
                            numMesasReference.setValue(credits);
                            backToActivity();
                        }
                        catch (NumberFormatException e) {}

                    }
                });
                ////////////////////////////////////////////////////////////////////////////////////////////


            }

            @Override
            public void onChildChanged(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    ///////////////////////////////////*back to greeting activity*//////////////////////////////////
    public void backToActivity(){
        Intent intent = new Intent(BalanceActivity.this, GreetingActivity.class);
        startActivity(intent);
        finish();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
