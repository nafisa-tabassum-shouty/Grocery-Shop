package com.example.groceryshop;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;

public class Search extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.lv1);
        //String username= getIntent().getStringExtra("username");

        list = new ArrayList<>();
        list.add("fruit");
        list.add("vegetable");
        list.add("meat");
        list.add("fish");
        list.add("oil");
        list.add("rice");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        // Initially hide the ListView
        listView.setVisibility(View.GONE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Start respective activity based on the selected item
                switch (selectedItem) {
                    case "fruit":
                        Intent fruitIntent = new Intent(Search.this, Fruits.class);
                        //fruitIntent.putExtra("username",username);
                        startActivity(fruitIntent);
                        break;
                    case "vegetable":
                        Intent vegetableIntent = new Intent(Search.this, vegetable.class);
                        //vegetableIntent.putExtra("username",username);
                        startActivity(vegetableIntent);
                        break;
                    case "meat":
                        Intent meatIntent = new Intent(Search.this, Meat.class);
                        //meatIntent.putExtra("username",username);
                        startActivity(meatIntent);
                        break;
                    case "fish":
                        Intent fishIntent = new Intent(Search.this, Fish.class);
                        //fishIntent.putExtra("username",username);
                        startActivity(fishIntent);
                        break;
                    case "oil":
                        Intent oilIntent = new Intent(Search.this, oil.class);
                        //oilIntent.putExtra("username",username);
                        startActivity(oilIntent);
                        break;
                    case "rice":
                        Intent riceIntent = new Intent(Search.this, Rice.class);
                        //riceIntent.putExtra("username",username);
                        startActivity(riceIntent);
                        break;
                    default:
                        Toast.makeText(Search.this, "Invalid selection", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Not needed for your case
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    // Hide the ListView when the search bar is empty
                    listView.setVisibility(View.GONE);
                } else {
                    // Show the ListView when user starts typing
                    listView.setVisibility(View.VISIBLE);
                    adapter.getFilter().filter(newText);
                }
                return false;
            }
        });
    }
}



