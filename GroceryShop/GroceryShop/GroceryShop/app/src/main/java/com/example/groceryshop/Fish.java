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

public class Fish extends AppCompatActivity {
    ImageView back;
    Button Cart;

    ImageView bata, meni, chingri, koral, carpu, golda, koi, hilsha, kachki, katla;
    DatabaseReference fishReference;
    public void toastshow(){
        Toast.makeText(getApplicationContext(), "Item has been added to Cart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish);
        back = findViewById(R.id.backto);
        Cart = findViewById(R.id.Cart);
        String username = getIntent().getStringExtra("username");

        // Initialize the DatabaseReference
        fishReference =FirebaseDatabase.getInstance().getReference(username).child("Fish");

        // Set click listener for the Cart button
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 =new Intent(Fish.this, Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Set click listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Fish.this,Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Initialize your variables
        bata = findViewById(R.id.bata);
        meni = findViewById(R.id.Meni);
        chingri = findViewById(R.id.chingri);
        koral = findViewById(R.id.koral);
        carpu = findViewById(R.id.carpu);
        golda = findViewById(R.id.golda);
        koi = findViewById(R.id.koi);
        hilsha = findViewById(R.id.hilsha);
        kachki = findViewById(R.id.kachki);
        katla = findViewById(R.id.katla);

        // Set the click listeners for the fish buttons
        bata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFishClickCount("bata");
            }
        });

        meni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toastshow();
                updateFishClickCount("meni");
            }
        });

        chingri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateFishClickCount("chingri");
            }
        });

        koral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateFishClickCount("koral");
            }
        });

        carpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateFishClickCount("carpu");
            }
        });

        golda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateFishClickCount("golda");
            }
        });

        koi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateFishClickCount("koi");
            }
        });

        hilsha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateFishClickCount("hilsha");
            }
        });

        kachki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateFishClickCount("kachki");
            }
        });

        katla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateFishClickCount("katla");
            }
        });
    }

    // Helper method to update the click count of a fish
    private void updateFishClickCount(String fishName) {
        fishReference.child(fishName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                clickCount++;
                fishReference.child(fishName).setValue(clickCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
