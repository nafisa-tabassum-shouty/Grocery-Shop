package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CashOnDelivery extends AppCompatActivity {
    EditText Contact_Number,Home_adress;
    Button pay;
    ImageView back;
    String username;
    ImageView map;
    ImageView Menu;
    DatabaseReference referencedelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_delivery);
        Contact_Number=findViewById(R.id.Contact_Number);
        Home_adress=findViewById(R.id.HomeAdress);
        pay=findViewById(R.id.button4);
        map=findViewById(R.id.map);
        back=findViewById(R.id.backtopay);
        Menu= findViewById(R.id.Menu);

        username = getIntent().getStringExtra("username");

        DatabaseReference userInfoRef = FirebaseDatabase.getInstance().getReference("Users").child(username);
        //userInfoRef.child("Home Address").setValue(addresss)

        // Check if the username node exists
        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Check if the "home address" child exists under the username
                    if (dataSnapshot.hasChild("Home Address")) {
                        // Read the value of "home address"
                        String homeAddress = dataSnapshot.child("Home Address").getValue(String.class);

                        // Set the value in a text field (replace this with your UI logic)
                        // For now, printing the value to the console
                        Home_adress.setText(homeAddress);
                    } else {
                        Home_adress.setText("");
                        //Toast.makeText(getApplicationContext(), "No 'home address' child under username: " + username, Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Username not found: " + username, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               deleteAllData();
                //startActivity(new Intent(CashOnDelivery.this,Receipt.class));
                //Intent intent1 = new Intent(CashOnDelivery.this,Receipt.class);
                Intent intent1 = new Intent(CashOnDelivery.this,PaymentSuccessful.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CashOnDelivery.this,map.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(CashOnDelivery.this,PaymentMethod.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(CashOnDelivery.this,sidePage.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

    }
    private void deleteAllData() {
        referencedelete = FirebaseDatabase.getInstance().getReference(username);
        referencedelete.removeValue();
    }
}