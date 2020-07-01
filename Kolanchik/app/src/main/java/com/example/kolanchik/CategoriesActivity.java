package com.example.kolanchik;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {

    /////////////////////////////////////*for but*//////////////////////////////////////////////////
    LinearLayout liLayout;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////*for DB*///////////////////////////////////////////////////
    private FirebaseDatabase db;
    private DatabaseReference reference;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static String product_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //////////////////////////////////*layout initialization////////////////////////////////////
        liLayout = findViewById(R.id.layout);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////*DB initialization*//////////////////////////////////////
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("products");
        ////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////*DB query*////////////////////////////////////////
        Query qGetProducts = reference.child(ShopActivity.category).orderByChild("name");

        qGetProducts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final Product product = dataSnapshot.getValue(Product.class);

                ////////////////////////////////////*new but*///////////////////////////////////////
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button btnNew = new Button(getApplicationContext());
                btnNew.setText(product.getName());
                liLayout.addView(btnNew, lParams);

                btnNew.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {

                        product_click = product.getName();

                        Intent intent = new Intent(CategoriesActivity.this, ProductActivity.class);
                        startActivity(intent);
                    }
                });
                ////////////////////////////////////////////////////////////////////////////////////
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

    }

    @Override
    public void onClick(View v) {
    }
}
