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

public class ProductActivity  extends AppCompatActivity {


    /////////////////////////////////////*text view*////////////////////////////////////////////////
    TextView nameProduct;
    TextView description;
    TextView date;
    TextView price;
    TextView quentity;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////*button*////////////////////////////////////////////////////
    Button but_Buy;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////*img*/////////////////////////////////////////////////////
    ImageView img_product;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////*for DB*///////////////////////////////////////////////////
    private FirebaseDatabase db;
    private DatabaseReference reference;
    private StorageReference mStorageRef;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    Integer price_for_race;

    @SuppressLint("SetTextI18n")
    //при создании активити
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        ////////////////////////////////////*text initialization*///////////////////////////////////
        nameProduct = findViewById(R.id.nameProduct);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        price = findViewById(R.id.price);
        quentity = findViewById(R.id.quentity);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////*but initialization*////////////////////////////////////
        but_Buy = findViewById(R.id.but_Buy);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////*img initialization*////////////////////////////////////
        img_product = findViewById(R.id.img_product);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////*DB initialization*//////////////////////////////////////
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("products/" + ShopActivity.category);
        mStorageRef = FirebaseStorage.getInstance().getReference("products" + ShopActivity.category);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////*to shop*///////////////////////////////////////////
        /*shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });*/
        ////////////////////////////////////////////////////////////////////////////////////////////



        //////////////////////////////////////////*DB query*////////////////////////////////////////
        Query qGetProduct = reference.orderByChild("name").equalTo(CategoriesActivity.product_click);

        qGetProduct.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                Product product = dataSnapshot.getValue(Product.class);
                if (product != null){
                    //////////////////////////////////*for price*///////////////////////////////////
                    switch (ShopActivity.category){
                        case "blood":
                            price_for_race = product.getPrice() + product.getPrice()/10 +
                                    (product.getPrice() * GreetingActivity.need_race_in_blood) / 100;
                            break;
                        case "magic_items":
                            price_for_race = product.getPrice() + product.getPrice()/10 +
                                    (product.getPrice() * GreetingActivity.need_race_in_magic_items) / 100;
                            break;
                        case "medicine":
                            price_for_race = product.getPrice() + product.getPrice()/10 +
                                    (product.getPrice() * GreetingActivity.need_race_in_medicine) / 100;
                            break;
                        case "technologies":
                            price_for_race = product.getPrice() + product.getPrice()/10 +
                                    (product.getPrice() * GreetingActivity.need_race_in_technologies) / 100;
                            break;
                        case "slaves":
                            price_for_race = product.getPrice() + product.getPrice()/10 +
                                    (product.getPrice() * GreetingActivity.need_race_in_slaves) / 100;
                            break;
                        case "alchemy":
                            price_for_race = product.getPrice() + product.getPrice()/10 +
                                    (product.getPrice() * GreetingActivity.need_race_in_alchemy) / 100;
                            break;
                        case "weapon":
                            price_for_race = product.getPrice() + product.getPrice()/10 +
                                    (product.getPrice() * GreetingActivity.need_race_in_weapon) / 100;
                            break;
                        case "bijouterie":
                            price_for_race = product.getPrice() + product.getPrice()/10 +
                                    (product.getPrice() * GreetingActivity.need_race_in_bijouterie) / 100;
                            break;
                    }
                    ////////////////////////////////////////////////////////////////////////////////

                    nameProduct.setText(product.getName());
                    description.setText(product.getDescription());
                    date.setText(product.getDate());
                    price.setText(price_for_race.toString() + " credits");
                    quentity.setText("On station: " + product.getQuentity());
                    String url=product.getImg();
                    Glide.with(getApplicationContext()).load(url).into(img_product);

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
        /*balance_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });*/
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

}