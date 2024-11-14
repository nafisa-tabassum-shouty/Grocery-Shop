package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class before_home extends AppCompatActivity {
    ImageView next_Page;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_home);
        next_Page=findViewById(R.id.next);
        name=findViewById(R.id.name);
        String username = getIntent().getStringExtra("username");
        name.setText(username);

        next_Page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(before_home.this,Easy_shopping.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
    }
}