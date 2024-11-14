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

public class Milk extends AppCompatActivity {
    ImageView back;
    Button Cart;

    ImageView danish, arong, cowhead, dano, chocolatemilk, fresh, mangomilk, marks, pranmilk, milkman, redcow, strawberrymilk;
    DatabaseReference milkReference;
    public void toastshow(){
        Toast.makeText(getApplicationContext(), "Item has been added to Cart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milk);
        back = findViewById(R.id.backto);
        Cart = findViewById(R.id.Cart);
        String username = getIntent().getStringExtra("username");

        // Initialize the DatabaseReference
        milkReference = FirebaseDatabase.getInstance().getReference(username).child("Milk");

        // Set click listener for the Cart button
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 =new Intent(Milk.this, Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Set click listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Milk.this,Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Initialize your variables
        danish = findViewById(R.id.imageView28);
        arong = findViewById(R.id.imageView26);
        cowhead = findViewById(R.id.imageView27);
        dano = findViewById(R.id.imageView29);
        chocolatemilk = findViewById(R.id.imageView30);
        fresh = findViewById(R.id.imageView31);
        mangomilk = findViewById(R.id.imageView32);
        marks = findViewById(R.id.imageView33);
        pranmilk = findViewById(R.id.imageView35);
        milkman = findViewById(R.id.imageView34);
        redcow = findViewById(R.id.imageView36);
        strawberrymilk = findViewById(R.id.imageView37);

        // Set the click listeners for the milk buttons
        danish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("danish");
            }
        });

        arong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("arong");
            }
        });

        cowhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("cowhead");
            }
        });

        dano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateMilkClickCount("dano");
            }
        });

        chocolatemilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("chocolatemilk");
            }
        });

        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("fresh");
            }
        });

        mangomilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("mangomilk");
            }
        });

        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("marks");
            }
        });

        pranmilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("pranmilk");
            }
        });

        milkman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("milkman");
            }
        });

        redcow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("redcow");
            }
        });

        strawberrymilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMilkClickCount("strawberrymilk");
            }
        });
    }

    // Helper method to update the click count of a milk item
    private void updateMilkClickCount(String milkName) {
        milkReference.child(milkName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                clickCount++;
                milkReference.child(milkName).setValue(clickCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
