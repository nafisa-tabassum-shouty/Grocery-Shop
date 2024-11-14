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

public class CreditCard extends AppCompatActivity {
    EditText Card_number,CVV,Expire,Name_on_card;
    Button confirm_order;
    String username;
    ImageView back;
    ImageView Menu;
    DatabaseReference referencedelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card);
        Card_number=findViewById(R.id.CardNumber);
        CVV=findViewById(R.id.CVV);
        Expire=findViewById(R.id.Expire);
        Name_on_card=findViewById(R.id.Name_on_Card);
        confirm_order=findViewById(R.id.button4);
        Menu= findViewById(R.id.Menu);
        back=findViewById(R.id.backtopay);
        username = getIntent().getStringExtra("username");

        confirm_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CardNo=Card_number.getText().toString();
                String CVV_=CVV.getText().toString();
                String Expire_=Expire.getText().toString();
                String Name_on_card_=Name_on_card.getText().toString();
                if(CVV_.length()!=0 && CardNo.length()!=0 && Expire_.length()!=0 && Name_on_card_.length()!=0)
                {
                    deleteAllData();
                    Intent intent1 = new Intent(CreditCard.this,PaymentSuccessful.class);
                    intent1.putExtra("username", username);
                    startActivity(intent1);
                }
                else
                    Toast.makeText(CreditCard.this, "Fill up details", Toast.LENGTH_SHORT).show();
            }
        });
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(CreditCard.this,sidePage.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(CreditCard.this,PaymentMethod.class);
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