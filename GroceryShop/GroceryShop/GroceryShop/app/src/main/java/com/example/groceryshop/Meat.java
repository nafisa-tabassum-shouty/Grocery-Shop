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

public class Meat extends AppCompatActivity {
    ImageView back;
    Button Cart;

    ImageView beef, drumstick, leg, thigh, withskin, withoutskin, koliza, khashi, koyel, keema, liver, pigeon, roast;
    DatabaseReference meatReference;
    public void toastshow(){
        Toast.makeText(getApplicationContext(), "Item has been added to Cart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meat);
        back = findViewById(R.id.backto);
        Cart = findViewById(R.id.Cart);
        String username = getIntent().getStringExtra("username");

        // Initialize the DatabaseReference
        meatReference = FirebaseDatabase.getInstance().getReference(username).child("Meat");

        // Set click listener for the Cart button
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Meat.this, Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Set click listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Meat.this, Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Initialize your variables
        beef = findViewById(R.id.imageView4);
        khashi = findViewById(R.id.imageView5);
        drumstick = findViewById(R.id.imageView6);
        leg = findViewById(R.id.imageView7);
        thigh = findViewById(R.id.imageView8);
        withskin = findViewById(R.id.imageView9);
        koyel = findViewById(R.id.imageView16);
        roast = findViewById(R.id.imageView18);

        // Set the click listeners for the meat buttons
        beef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMeatClickCount("beef");
            }
        });

        drumstick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMeatClickCount("drumstick");
            }
        });

        leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMeatClickCount("leg");
            }
        });

        thigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMeatClickCount("thigh");
            }
        });

        withskin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMeatClickCount("withskin");
            }
        });


        khashi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMeatClickCount("khashi");
            }
        });

        koyel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMeatClickCount("koyel");
            }
        });


        roast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateMeatClickCount("roast");
            }
        });
    }

    // Helper method to update the click count of a meat
    private void updateMeatClickCount(String meatName) {
        meatReference.child(meatName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                clickCount++;
                meatReference.child(meatName).setValue(clickCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
