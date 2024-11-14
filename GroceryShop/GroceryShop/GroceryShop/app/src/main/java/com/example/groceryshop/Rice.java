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

public class Rice extends AppCompatActivity {
    ImageView back;
    Button Cart;
    ImageView aci, bashmoti, fresh, chashi, fortune, dawat, nazershail, miniket, rupchada, pran;
    DatabaseReference riceReference;
    public void toastshow(){
        Toast.makeText(getApplicationContext(), "Item has been added to Cart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rice);
        back = findViewById(R.id.backto);
        Cart = findViewById(R.id.Cart);

        String username = getIntent().getStringExtra("username");

        // Initialize the DatabaseReference
        riceReference =FirebaseDatabase.getInstance().getReference(username).child("Rice");

        // Set click listeners for the rice buttons
        aci = findViewById(R.id.aci);
        bashmoti = findViewById(R.id.bashmoti);
        fresh = findViewById(R.id.fresh);
        chashi = findViewById(R.id.chashi);
        fortune = findViewById(R.id.fortune);
        dawat = findViewById(R.id.dawat);
        nazershail = findViewById(R.id.nazershail);
        miniket = findViewById(R.id.miniket);
        rupchada = findViewById(R.id.rupchada);
        pran = findViewById(R.id.pran);

        aci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("aci");
            }
        });

        bashmoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("bashmoti");
            }
        });

        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("fresh");
            }
        });

        chashi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("chashi");
            }
        });

        fortune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("fortune");
            }
        });

        dawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("dawat");
            }
        });

        nazershail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("nazershail");
            }
        });

        miniket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("miniket");
            }
        });

        rupchada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("rupchada");
            }
        });

        pran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();updateRiceClickCount("pran");
            }
        });

        // Set click listener for the Cart button
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Rice.this, Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Set click listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Rice.this, Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);

            }
        });
    }

    // Helper method to update the click count of a rice item
    private void updateRiceClickCount(String riceName) {
        riceReference.child(riceName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                clickCount++;
                riceReference.child(riceName).setValue(clickCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
