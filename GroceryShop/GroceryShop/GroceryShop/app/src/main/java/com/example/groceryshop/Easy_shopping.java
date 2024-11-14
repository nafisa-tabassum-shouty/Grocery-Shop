package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Easy_shopping extends AppCompatActivity {
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_shopping);
        next=findViewById(R.id.button2);
        String username = getIntent().getStringExtra("username");
        System.out.println(username);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Easy_shopping.this,Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
    }
}