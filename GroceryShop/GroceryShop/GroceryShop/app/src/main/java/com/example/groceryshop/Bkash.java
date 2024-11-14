package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bkash extends AppCompatActivity {
    EditText phone,pin;
    ImageView back;ImageView Menu;


    Button pay;
    String username;
    DatabaseReference referencedelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bkash);
        phone=findViewById(R.id.HomeAdress);
        pin=findViewById(R.id.editTextText2);
        pay=findViewById(R.id.button4);
        Menu= findViewById(R.id.Menu);
        back=findViewById(R.id.back);

        String phoneNumber=phone.getText().toString();
        String pinNumber=pin.getText().toString();
        username = getIntent().getStringExtra("username");
        pay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                deleteAllData();

                Intent intent1 = new Intent(Bkash.this,PaymentSuccessful.class);
                intent1.putExtra("username", username);
                startActivity(intent1);

                   // Toast.makeText(Bkash.this, "Fill up details", Toast.LENGTH_SHORT).show();


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Bkash.this,sidePage.class);
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