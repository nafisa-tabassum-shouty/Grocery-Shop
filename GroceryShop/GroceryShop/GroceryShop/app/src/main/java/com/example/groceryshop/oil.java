package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class oil extends AppCompatActivity {
    ImageView back;
    Button Cart;

    ImageView bashundhora, aci, fresh_soyabin, fortune, pushti, kings, rupchada_soyabin, safalo, teer, sunflower;
    DatabaseReference oilReference;

    public void toastshow() {
        Toast.makeText(getApplicationContext(), "Item has been added to Cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oil);
        back = findViewById(R.id.backto);
        Cart = findViewById(R.id.Cart);
        String username = getIntent().getStringExtra("username");

        // Initialize the DatabaseReference
        oilReference = FirebaseDatabase.getInstance().getReference(username).child("Oil");

        // Set click listener for the Cart button
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(oil.this, Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Set click listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(oil.this, Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Initialize your variables
        bashundhora = findViewById(R.id.imageView49);
        aci = findViewById(R.id.imageView48);
        fresh_soyabin = findViewById(R.id.imageView51);
        fortune = findViewById(R.id.imageView50);
        pushti = findViewById(R.id.imageView53);
        kings = findViewById(R.id.imageView52);
        rupchada_soyabin = findViewById(R.id.imageView54);
        safalo = findViewById(R.id.imageView55);
        teer = findViewById(R.id.imageView57);
        sunflower = findViewById(R.id.imageView56);

        // Set the click listeners for the oil buttons
        bashundhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("bashundhora_oil");
            }
        });

        aci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("aci_oil");
            }
        });

        fresh_soyabin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("fresh_soyabin");
            }
        });

        fortune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("fortune_oil");
            }
        });

        pushti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("pushti");
            }
        });

        kings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("kings");
            }
        });

        rupchada_soyabin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("rupchada_soyabin");
            }
        });

        safalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("safalo");
            }
        });

        teer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("teer");
            }
        });

        sunflower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateOilClickCount("sunflower_oil");
            }
        });
    }
// Helper method to update the click count of an oil item
        private void updateOilClickCount (String oilName){
            oilReference.child(oilName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int clickCount = 0;
                    if (dataSnapshot.exists()) {
                        clickCount = dataSnapshot.getValue(Integer.class);
                    }
                    clickCount++;
                    oilReference.child(oilName).setValue(clickCount);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle error
                }
            });
        }

}
