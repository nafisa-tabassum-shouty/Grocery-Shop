package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class PaymentMethod extends AppCompatActivity {
    Button cash_on_delivery,credit_card,bkash;
    ImageView back;
    ImageView Menu;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_method);
        cash_on_delivery=findViewById(R.id.button);
        credit_card=findViewById(R.id.button2);
        bkash=findViewById(R.id.button3);
        back=findViewById(R.id.backtopay);
        Menu= findViewById(R.id.Menu);
        username = getIntent().getStringExtra("username");

        cash_on_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PaymentMethod.this,CashOnDelivery.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PaymentMethod.this,CreditCard.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PaymentMethod.this,Bkash.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(PaymentMethod.this,Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(PaymentMethod.this,sidePage.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
    }
}