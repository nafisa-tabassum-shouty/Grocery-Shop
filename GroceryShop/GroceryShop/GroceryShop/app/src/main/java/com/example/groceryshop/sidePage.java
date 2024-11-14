package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sidePage extends AppCompatActivity {
    TextView home,catagory,logout,aboutus,cart,u,email;
    ImageView cancel;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_page);

        home=findViewById(R.id.home);
        catagory=findViewById(R.id.catagory);
        logout=findViewById(R.id.logout);
        aboutus=findViewById(R.id.aboutus);
        cart=findViewById(R.id.cart);
        cancel=findViewById(R.id.cancel);
        username = getIntent().getStringExtra("username");
        u=findViewById(R.id.username);
        email=findViewById(R.id.EMAIL);
        u.setText(username);
        DatabaseReference userInfoRef = FirebaseDatabase.getInstance().getReference("Users").child(username);

        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("email")) {
                        String homeAddress = dataSnapshot.child("email").getValue(String.class);

                        email.setText(homeAddress);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(sidePage.this,Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        catagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(sidePage.this,Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 =new Intent(sidePage.this,LoginActivity.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(sidePage.this,AboutUs.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(sidePage.this,Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent1 =new Intent(sidePage.this,la);
                intent1.putExtra("username", username);
                startActivity(intent1);*/
                Intent intent1 =new Intent(sidePage.this,Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
                        // Finish the current activity to go back to the previous screen
                        //finish();


            }
        });
    }
}