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

public class vegetable extends AppCompatActivity {
    ImageView back;
    Button Cart;
    Button alovera,cabage,begun,capsicumred,cepsicumyellow,carrot,chichinga,dhundul,fulcopy,papaya,pumpkin,tomatogreen,kachakola,kolmishak,lalshak,lemon,mistikumra,mula,newalu,palongshak,potol,sheem,tomatolong;


    DatabaseReference vegetableReference;
    public void toastshow(){
        Toast.makeText(getApplicationContext(), "Item has been added to Cart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vegetable);

        back = findViewById(R.id.backto);
        Cart = findViewById(R.id.Cart);
        String username = getIntent().getStringExtra("username");

        // Initialize the DatabaseReference for vegetables
        vegetableReference =FirebaseDatabase.getInstance().getReference(username).child("Vegetables");

        // Set click listener for the Cart button
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(vegetable.this, Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Set click listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(vegetable.this, Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        alovera=findViewById(R.id.alovera);
        cabage=findViewById(R.id.badhakopi);
        begun=findViewById(R.id.Beguun);
        capsicumred=findViewById(R.id.redCapsicum);
        cepsicumyellow=findViewById(R.id.yellowCapsicum);
        carrot=findViewById(R.id.carrot);
        chichinga=findViewById(R.id.chichinga);
        dhundul=findViewById(R.id.dhundul);
        fulcopy=findViewById(R.id.Fulcopy);
        papaya=findViewById(R.id.papaya);
        pumpkin=findViewById(R.id.pumpkin);
        tomatogreen=findViewById(R.id.greenTomato);
        kachakola=findViewById(R.id.kachakola);
        kolmishak=findViewById(R.id.kolmishak);
        lalshak=findViewById(R.id.lalshak);
        lemon=findViewById(R.id.lemon);
        mistikumra=findViewById(R.id.mishtikumra);
        mula=findViewById(R.id.radish);
        newalu=findViewById(R.id.newAlu);
        palongshak=findViewById(R.id.palongshak);
        potol=findViewById(R.id.potol);
        sheem=findViewById(R.id.sheem);
        tomatolong=findViewById(R.id.tomatolong);

        // Set the click listeners for the vegetable buttons
        alovera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("alovera");
            }
        });

        cabage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("cabage");
            }
        });

        begun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("begun");
            }
        });

        capsicumred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("capsicumred");
            }
        });

        cepsicumyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("cepsicumyellow");
            }
        });

        carrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("carrot");
            }
        });

        chichinga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("chichinga");
            }
        });

        dhundul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("dhundul");
            }
        });

        fulcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("fulcopy");
            }
        });

        papaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("papaya");
            }
        });

        pumpkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("pumpkin");
            }
        });

        tomatogreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("tomatogreen");
            }
        });

        kachakola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("kachakola");
            }
        });

        kolmishak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("kolmishak");
            }
        });

        lalshak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("lalshak");
            }
        });

        lemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("lemon");
            }
        });

        mistikumra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("mistikumra");
            }
        });

        mula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("mula");
            }
        });

        newalu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("newalu");
            }
        });

        palongshak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("palongshak");
            }
        });

        potol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("potol");
            }
        });

        sheem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("sheem");
            }
        });

        tomatolong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                incrementClickCount("tomatolong");
            }
        });
    }

    // Helper method to increment the click count of a vegetable
    private void incrementClickCount(String vegetableName) {
        vegetableReference.child(vegetableName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                clickCount++;
                vegetableReference.child(vegetableName).setValue(clickCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
