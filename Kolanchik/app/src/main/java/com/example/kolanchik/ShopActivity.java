package com.example.kolanchik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class ShopActivity extends AppCompatActivity {

    Button blood_but;
    Button magic_items_but;
    Button medicine_but;
    Button technologies_but;
    Button slaves_but;
    Button alchemy_but;
    Button weapon_but;
    Button bijouterie_but;

    public static String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ////////////////////////////////////*but initialization*////////////////////////////////////
        blood_but = findViewById(R.id.blood_but);
        magic_items_but = findViewById(R.id.magic_items_but);
        medicine_but = findViewById(R.id.medicine_but);
        technologies_but = findViewById(R.id.technologies_but);
        slaves_but = findViewById(R.id.slaves_but);
        alchemy_but = findViewById(R.id.alchemy_but);
        weapon_but = findViewById(R.id.weapon_but);
        bijouterie_but = findViewById(R.id.bijouterie_but);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////*pres but*//////////////////////////////////////////
        blood_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity("blood");
            }
        });
        magic_items_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity("magic_items");
            }
        });
        medicine_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity("medicine");
            }
        });
        technologies_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity("technologies");
            }
        });
        slaves_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity("slaves");
            }
        });
        alchemy_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity("alchemy");
            }
        });
        weapon_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity("weapon");
            }
        });
        bijouterie_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity("bijouterie");
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    //////////////////////////////////////////*new activity*////////////////////////////////////////
    public void newActivity(String category){
        this.category = category;
        Intent intent = new Intent(ShopActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
