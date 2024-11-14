package com.example.groceryshop;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Catagories extends AppCompatActivity {
    ImageView fruits, vegitables, oil, meat, fish, milk, cleaner, ghee, rice, onion;
    Button Cartt;
    ImageView Menu;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);

        fruits = findViewById(R.id.fruits);
        vegitables = findViewById(R.id.vegetable);
        oil = findViewById(R.id.oil);
        meat = findViewById(R.id.meat);
        fish = findViewById(R.id.fish);
        milk = findViewById(R.id.milk);
        cleaner = findViewById(R.id.cleaner);
        ghee = findViewById(R.id.ghee);
        rice = findViewById(R.id.rice);
        onion = findViewById(R.id.onion);
        Cartt = findViewById(R.id.Cartt);
        Menu= findViewById(R.id.Menu);
        String username = getIntent().getStringExtra("username");
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this,sidePage.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this, Fruits.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        Cartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this, Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        vegitables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this, vegetable.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        oil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this,oil.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this,Meat.class);

                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this,Fish.class);

                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this,Milk.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Catagories.this,Rice.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        cleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Home.this,LoginActivity.class));
            }
        });

        ghee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Home.this,LoginActivity.class));
            }
        });


        onion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Home.this,LoginActivity.class));
            }
        });
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
                        Intent fruitIntent = new Intent(Catagories.this, Fruits.class);
                        fruitIntent.putExtra("username",username);
                        startActivity(fruitIntent);
                        break;
                    case "vegetable":
                        Intent vegetableIntent = new Intent(Catagories.this, vegetable.class);
                        vegetableIntent.putExtra("username",username);
                        startActivity(vegetableIntent);
                        break;
                    case "meat":
                        Intent meatIntent = new Intent(Catagories.this, Meat.class);
                        meatIntent.putExtra("username",username);
                        startActivity(meatIntent);
                        break;
                    case "fish":
                        Intent fishIntent = new Intent(Catagories.this, Fish.class);
                        fishIntent.putExtra("username",username);
                        startActivity(fishIntent);
                        break;
                    case "oil":
                        Intent oilIntent = new Intent(Catagories.this, oil.class);
                        oilIntent.putExtra("username",username);
                        startActivity(oilIntent);
                        break;
                    case "rice":
                        Intent riceIntent = new Intent(Catagories.this, Rice.class);
                        riceIntent.putExtra("username",username);
                        startActivity(riceIntent);
                        break;
                    default:
                        Toast.makeText(Catagories.this, "Invalid selection", Toast.LENGTH_SHORT).show();
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


        /*recyclerView=findViewById(R.id.recycleView);

        ArrayList<RecipeModel> list=new ArrayList<>();

        list.add(new RecipeModel(R.drawable.fruits,"Fruits"));


        list.add(new RecipeModel(R.drawable.vegetable,"Vegetables"));

        /*list.add(new RecipeModel(R.drawable.shopping,"HAPPY SHOPPING"));
        list.add(new RecipeModel(R.drawable.mt_shopping_off_platt,"HAPPY SHOPPING"));

        RecipeAdapter adapter=new RecipeAdapter(list,this);
        recyclerView.setAdapter(adapter);

        StaggeredGridLayoutManager staggered=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggered);*/

    }
}