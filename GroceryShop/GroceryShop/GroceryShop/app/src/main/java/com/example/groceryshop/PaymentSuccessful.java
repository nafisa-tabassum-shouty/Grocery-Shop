package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentSuccessful extends AppCompatActivity {
    Button b;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_successful);

        b=findViewById(R.id.button5);
        username = getIntent().getStringExtra("username");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PaymentSuccessful.this,Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

    }

}