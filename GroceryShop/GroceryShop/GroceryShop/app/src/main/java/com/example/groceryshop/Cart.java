package com.example.groceryshop;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceryshop.Adapter.cartAdapter;
import com.example.groceryshop.Model.CartModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    Button nextPage;
    Button clearCart;
    ImageView back;
    TextView totalPrice;
    int price = 0;
    String username;
    cartAdapter adapter;
    DatabaseReference fruitReference;
    DatabaseReference meatReference;
    DatabaseReference fishReference;
    DatabaseReference vegetableReference;
    DatabaseReference milkReference;
    DatabaseReference riceReference;
    DatabaseReference referencedelete;
    DatabaseReference oilReference;
    ArrayList<CartModel> cartItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        back= findViewById(R.id.backtos);
        username = getIntent().getStringExtra("username");
        clearCart= findViewById(R.id.Clear);
        nextPage = findViewById(R.id.Pay);
        totalPrice = findViewById(R.id.totalPrice);
        recyclerView = findViewById(R.id.recyclarView);
        // Initialize the DatabaseReference
        String userId = getCurrentUserId(); // Implement a method to get the current user's ID
        fruitReference = FirebaseDatabase.getInstance().getReference(username).child("fruits");


        // Get reference to Firebase "Fruits" and "Meat" nodes
        //fruitReference = FirebaseDatabase.getInstance().getReference("Fruits");
        meatReference =  FirebaseDatabase.getInstance().getReference(username).child("Meat");
        fishReference =  FirebaseDatabase.getInstance().getReference(username).child("Fish");
        milkReference = FirebaseDatabase.getInstance().getReference(username).child("Milk"); // Add reference for "Milk" node
        vegetableReference =FirebaseDatabase.getInstance().getReference(username).child("Vegetables"); // Add reference for "Vegetable" node
        riceReference = FirebaseDatabase.getInstance().getReference(username).child("Rice");
        oilReference = FirebaseDatabase.getInstance().getReference(username).child("Oil");


        // Initialize HashMaps for fruit and meat click counts
        HashMap<String, Integer> fruitClickCounts = new HashMap<>();
        HashMap<String, Integer> meatClickCounts = new HashMap<>();
        HashMap<String, Integer> fishClickCounts = new HashMap<>();
        HashMap<String, Integer> milkClickCounts = new HashMap<>(); // Add HashMap for milk click counts
        HashMap<String, Integer> vegetableClickCounts = new HashMap<>();
        HashMap<String, Integer> riceClickCounts = new HashMap<>();
        HashMap<String, Integer> oilClickCounts = new HashMap<>();


        // Initialize ArrayList for the cart items


        adapter = new cartAdapter(cartItems, Cart.this);

        // Retrieve data for fruit items from Firebase
        fruitReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    fruitClickCounts.clear();
                    for (DataSnapshot fruitSnapshot : dataSnapshot.getChildren()) {
                        String fruitName = fruitSnapshot.getKey();
                        int clickCount = fruitSnapshot.getValue(Integer.class);
                        fruitClickCounts.put(fruitName, clickCount);
                    }

                    // Process and add fruit items to the cartItems ArrayList
                    processFruitItems(cartItems, fruitClickCounts);
                    totalPrice.setText(String.valueOf(price) + "tk");

                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        // Retrieve data for meat items from Firebase
        meatReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    meatClickCounts.clear();
                    for (DataSnapshot meatSnapshot : dataSnapshot.getChildren()) {
                        String meatName = meatSnapshot.getKey();
                        int clickCount = meatSnapshot.getValue(Integer.class);
                        meatClickCounts.put(meatName, clickCount);
                    }

                    // Process and add meat items to the cartItems ArrayList
                    processMeatItems(cartItems, meatClickCounts);
                    totalPrice.setText(String.valueOf(price) + "tk");

                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        // Retrieve data for milk items from Firebase
        milkReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    milkClickCounts.clear();
                    for (DataSnapshot milkSnapshot : dataSnapshot.getChildren()) {
                        String milkName = milkSnapshot.getKey();
                        int clickCount = milkSnapshot.getValue(Integer.class);
                        milkClickCounts.put(milkName, clickCount);
                    }

                    // Process and add milk items to the cartItems ArrayList
                    processMilkItems(cartItems, milkClickCounts);

                    totalPrice.setText(String.valueOf(price) + "tk");
                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        // Retrieve data for fish items from Firebase
        fishReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    fishClickCounts.clear();
                    for (DataSnapshot fishSnapshot : dataSnapshot.getChildren()) {
                        String fishName = fishSnapshot.getKey();
                        int clickCount = fishSnapshot.getValue(Integer.class);
                        fishClickCounts.put(fishName, clickCount);
                    }

                    // Process and add fish items to the cartItems ArrayList
                    processFishItems(cartItems, fishClickCounts);

                    totalPrice.setText(String.valueOf(price) + "tk");
                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
        // Retrieve data from Firebase using addListenerForSingleValueEvent

        // Retrieve data for vegetable items from Firebase
        vegetableReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    vegetableClickCounts.clear();
                    for (DataSnapshot vegetableSnapshot : dataSnapshot.getChildren()) {
                        String vegetableName = vegetableSnapshot.getKey();
                        int clickCount = vegetableSnapshot.getValue(Integer.class);
                        vegetableClickCounts.put(vegetableName, clickCount);
                    }

                    // Process and add fish items to the cartItems ArrayList
                    processVegetableItems(cartItems, vegetableClickCounts);

                    totalPrice.setText(String.valueOf(price) + "tk");
                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
        // Retrieve data for rice items from Firebase
        riceReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    riceClickCounts.clear();
                    for (DataSnapshot riceSnapshot : dataSnapshot.getChildren()) {
                        String riceName = riceSnapshot.getKey();
                        int clickCount = riceSnapshot.getValue(Integer.class);
                        riceClickCounts.put(riceName, clickCount);
                    }

                    // Process and add fruit items to the cartItems ArrayList
                    processRiceItems(cartItems, riceClickCounts);


                    // Pricing before notifying the adapter
                    totalPrice.setText(String.valueOf(price) + "tk");

                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });


        // Retrieve data for oil items from Firebase
        oilReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    oilClickCounts.clear();
                    for (DataSnapshot oilSnapshot : dataSnapshot.getChildren()) {
                        String oilName = oilSnapshot.getKey();
                        int clickCount = oilSnapshot.getValue(Integer.class);
                        oilClickCounts.put(oilName, clickCount);
                    }

                    // Process and add oil items to the cartItems ArrayList
                    processOilItems(cartItems, oilClickCounts);

                    // Pricing before notifying the adapter
                    totalPrice.setText(String.valueOf(price) + "tk");

                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });




        // ... (Your existing code for other buttons and actions)
        // Set up the RecyclerView and Adapter for displaying cart items
        // cartAdapter adapter = new cartAdapter(cartItems, Cart.this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Cart.this, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);


        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllData();
                adapter.notifyDataSetChanged();
                totalPrice.setText("0");
                Toast.makeText(Cart.this, "Successfully cleared All Cart Data", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Cart.this, Cart.class);
                intent1.putExtra("value", String.valueOf(price));
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Cart.this, TotalPrice.class);

                intent1.putExtra("value", String.valueOf(price));
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Cart.this,Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

    }


    // Process fruit items
    private void processFruitItems(ArrayList<CartModel> list, HashMap<String, Integer> fruitClickCounts) {
        if (fruitClickCounts.containsKey("AppleFuji")) {
            int AppleFuji_clicked = fruitClickCounts.get("AppleFuji");

            if (AppleFuji_clicked > 0) {
                list.add(new CartModel(R.drawable.applefujikg, "Apple Fuji", "385tk", String.valueOf(AppleFuji_clicked)));
                price += 385 * AppleFuji_clicked;
            }
        }

        if (fruitClickCounts.containsKey("Apple_Gala")) {
            int Apple_Gala_clicked = fruitClickCounts.get("Apple_Gala");

            if (Apple_Gala_clicked > 0) {
                list.add(new CartModel(R.drawable.applegalakg, "Apple Gala", "382tk", String.valueOf(Apple_Gala_clicked)));
                price += 382 * Apple_Gala_clicked;
            }
        }

        if (fruitClickCounts.containsKey("redgraps")) {
            int redgraps_clicked = fruitClickCounts.get("redgraps");

            if (redgraps_clicked > 0) {
                list.add(new CartModel(R.drawable.grapes_red, "Red Graps", "382tk", String.valueOf(redgraps_clicked)));
                price += 382 * redgraps_clicked;
            }
        }

        if (fruitClickCounts.containsKey("sweetorange")) {
            int sweetorange_clicked = fruitClickCounts.get("sweetorange");

            if (sweetorange_clicked > 0) {
                list.add(new CartModel(R.drawable.sweet_orange, "Sweet Orange", "450tk", String.valueOf(sweetorange_clicked)));
                price += 450 * sweetorange_clicked;
            }
        }

        if (fruitClickCounts.containsKey("orangeindian")) {
            int orangeindian_clicked = fruitClickCounts.get("orangeindian");

            if (orangeindian_clicked > 0) {
                list.add(new CartModel(R.drawable.orange_komla_indian, "Orange Komla Indian", "328tk", String.valueOf(orangeindian_clicked)));
                price += 328 * orangeindian_clicked;
            }
        }

        if (fruitClickCounts.containsKey("strawberry")) {
            int strawberry_clicked = fruitClickCounts.get("strawberry");

            if (strawberry_clicked > 0) {
                list.add(new CartModel(R.drawable.strawberry, "Strawberry", "650tk", String.valueOf(strawberry_clicked)));
                price += 650 * strawberry_clicked;
            }
        }

        if (fruitClickCounts.containsKey("applegolden")) {
            int applegolden_clicked = fruitClickCounts.get("applegolden");

            if (applegolden_clicked > 0) {
                list.add(new CartModel(R.drawable.apple_golden_delicious, "Apple Golden Deliious", "308tk", String.valueOf(applegolden_clicked)));
                price += 308 * applegolden_clicked;
            }
        }

        if (fruitClickCounts.containsKey("shagorkiola")) {
            int shagorkiola_clicked = fruitClickCounts.get("shagorkiola");

            if (shagorkiola_clicked > 0) {
                list.add(new CartModel(R.drawable.banana_meher_shagor_kola_pc, "Banana Meher Shagor Kola", "14tk", String.valueOf(shagorkiola_clicked)));
                price += 14 * shagorkiola_clicked;
            }
        }

        if (fruitClickCounts.containsKey("shobrikola")) {
            int shobrikola_clicked = fruitClickCounts.get("shobrikola");

            if (shobrikola_clicked > 0) {
                list.add(new CartModel(R.drawable.banana_shobri_pcs, "Banana Shobri kola", "12tk", String.valueOf(shobrikola_clicked)));
                price += 12 * shobrikola_clicked;
            }
        }

        if (fruitClickCounts.containsKey("chompakola")) {
            int chompakola_clicked = fruitClickCounts.get("chompakola");

            if (chompakola_clicked > 0) {
                list.add(new CartModel(R.drawable.banana_chini_chompa, "Chompa Kola", "6tk", String.valueOf(chompakola_clicked)));
                price += 6 * chompakola_clicked;
            }
        }

        if (fruitClickCounts.containsKey("stripewatermelon")) {
            int stripewatermelon_clicked = fruitClickCounts.get("stripewatermelon");

            if (stripewatermelon_clicked > 0) {
                list.add(new CartModel(R.drawable.watermelon_stripe_tormuz, "Watermelon Stripe Tormuz", "88tk", String.valueOf(stripewatermelon_clicked)));
                price += 88 * stripewatermelon_clicked;
            }
        }

        if (fruitClickCounts.containsKey("blackwatermelon")) {
            int blackwatermelon_clicked = fruitClickCounts.get("blackwatermelon");

            if (blackwatermelon_clicked > 0) {
                list.add(new CartModel(R.drawable.water_melon_black, "Black Watermelon", "88tk", String.valueOf(blackwatermelon_clicked)));
                price += 88 * blackwatermelon_clicked;
            }
        }

        if (fruitClickCounts.containsKey("pomegranate")) {
            int pomegranate_clicked = fruitClickCounts.get("pomegranate");

            if (pomegranate_clicked > 0) {
                list.add(new CartModel(R.drawable.pomegranate, "Pomegranate", "520tk", String.valueOf(pomegranate_clicked)));
                price += 520 * pomegranate_clicked;
            }
        }

        if (fruitClickCounts.containsKey("pears")) {
            int pears_clicked = fruitClickCounts.get("pears");

            if (pears_clicked > 0) {
                list.add(new CartModel(R.drawable.pear, "Pears", "385tk", String.valueOf(pears_clicked)));
                price += 385 * pears_clicked;
            }
        }
    }

    // Process meat items
    private void processMeatItems(ArrayList<CartModel> list, HashMap<String, Integer> meatClickCounts) {
        if (meatClickCounts.containsKey("beef")) {
            int beef_clicked = meatClickCounts.get("beef");

            if (beef_clicked > 0) {
                list.add(new CartModel(R.drawable.beef_premiumcube, "Beef Premium Cube", "750tk", String.valueOf(beef_clicked)));
                price += 750 * beef_clicked;
            }
        }

        if (meatClickCounts.containsKey("drumstick")) {
            int drumstick_clicked = meatClickCounts.get("drumstick");

            if (drumstick_clicked > 0) {
                list.add(new CartModel(R.drawable.broilerchickendrumstick, "Broiler Chicken Drumstick", "487tk", String.valueOf(drumstick_clicked)));
                price += 487 * drumstick_clicked;
            }
        }

        if (meatClickCounts.containsKey("leg")) {
            int leg_clicked = meatClickCounts.get("leg");

            if (leg_clicked > 0) {
                list.add(new CartModel(R.drawable.broilerchickenleg, "Broiler Chicken Leg", "448tk", String.valueOf(leg_clicked)));
                price += 448 * leg_clicked;
            }
        }

        if (meatClickCounts.containsKey("thigh")) {
            int thigh_clicked = meatClickCounts.get("thigh");

            if (thigh_clicked > 0) {
                list.add(new CartModel(R.drawable.broilerchickenthigh, "Broiler Chicken Thigh", "375tk", String.valueOf(thigh_clicked)));
                price += 375 * thigh_clicked;
            }
        }

        if (meatClickCounts.containsKey("withskin")) {
            int withskin_clicked = meatClickCounts.get("withskin");

            if (withskin_clicked > 0) {
                list.add(new CartModel(R.drawable.broilerchickenwithskin, "Broiler Chicken with Skin", "298tk", String.valueOf(withskin_clicked)));
                price += 298 * withskin_clicked;
            }
        }



        if (meatClickCounts.containsKey("khashi")) {
            int khashi_clicked = meatClickCounts.get("khashi");

            if (khashi_clicked > 0) {
                list.add(new CartModel(R.drawable.khashirkolijaregular, "Khashi Regular", "620tk", String.valueOf(khashi_clicked)));
                price += 620 * khashi_clicked;
            }
        }

        if (meatClickCounts.containsKey("koyel")) {
            int koyel_clicked = meatClickCounts.get("koyel");

            if (koyel_clicked > 0) {
                list.add(new CartModel(R.drawable.koyelermangshodressed, "Koyeler Mangsho (Dressed)", "68tk", String.valueOf(koyel_clicked)));
                price += 68 * koyel_clicked;
            }
        }



        if (meatClickCounts.containsKey("roast")) {
            int roast_clicked = meatClickCounts.get("roast");

            if (roast_clicked > 0) {
                list.add(new CartModel(R.drawable.roastchicken, "Roast Chicken", "192tk", String.valueOf(roast_clicked)));
                price += 192 * roast_clicked;
            }
        }
    }
    private void processFishItems(ArrayList<CartModel> list, HashMap<String, Integer> fishClickCounts) {
        if (fishClickCounts.containsKey("bata")) {
            int bata_clicked = fishClickCounts.get("bata");

            if (bata_clicked > 0) {
                list.add(new CartModel(R.drawable.bata, "Bata", "490tk", String.valueOf(bata_clicked)));
                price += 490 * bata_clicked;
            }
        }

        if (fishClickCounts.containsKey("meni")) {
            int meni_clicked = fishClickCounts.get("meni");

            if (meni_clicked > 0) {
                list.add(new CartModel(R.drawable.meni, "Meni", "650tk", String.valueOf(meni_clicked)));
                price += 650 * meni_clicked;
            }
        }

        if (fishClickCounts.containsKey("chingri")) {
            int chingri_clicked = fishClickCounts.get("chingri");

            if (chingri_clicked > 0) {
                list.add(new CartModel(R.drawable.chingrismall, "Chingri", "725tk", String.valueOf(chingri_clicked)));
                price += 725 * chingri_clicked;
            }
        }

        if (fishClickCounts.containsKey("koral")) {
            int koral_clicked = fishClickCounts.get("koral");

            if (koral_clicked > 0) {
                list.add(new CartModel(R.drawable.koral, "Koral", "1050tk", String.valueOf(koral_clicked)));
                price += 1050 * koral_clicked;
            }
        }

        if (fishClickCounts.containsKey("carpu")) {
            int carpu_clicked = fishClickCounts.get("carpu");

            if (carpu_clicked > 0) {
                list.add(new CartModel(R.drawable.carpu, "Carpu", "290tk", String.valueOf(carpu_clicked)));
                price += 290 * carpu_clicked;
            }
        }

        if (fishClickCounts.containsKey("golda")) {
            int golda_clicked = fishClickCounts.get("golda");

            if (golda_clicked > 0) {
                list.add(new CartModel(R.drawable.galda, "Golda", "1550tk", String.valueOf(golda_clicked)));
                price += 1550 * golda_clicked;
            }
        }

        if (fishClickCounts.containsKey("koi")) {
            int koi_clicked = fishClickCounts.get("koi");

            if (koi_clicked > 0) {
                list.add(new CartModel(R.drawable.koi, "Koi", "430tk", String.valueOf(koi_clicked)));
                price += 430 * koi_clicked;
            }
        }

        if (fishClickCounts.containsKey("hilsha")) {
            int hilsha_clicked = fishClickCounts.get("hilsha");

            if (hilsha_clicked > 0) {
                list.add(new CartModel(R.drawable.hilshalarge, "Hilsha", "1370tk", String.valueOf(hilsha_clicked)));
                price += 1370 * hilsha_clicked;
            }
        }

        if (fishClickCounts.containsKey("kachki")) {
            int kachki_clicked = fishClickCounts.get("kachki");

            if (kachki_clicked > 0) {
                list.add(new CartModel(R.drawable.kachki, "Kachki", "690tk", String.valueOf(kachki_clicked)));
                price += 690 * kachki_clicked;
            }
        }

        if (fishClickCounts.containsKey("katla")) {
            int katla_clicked = fishClickCounts.get("katla");

            if (katla_clicked > 0) {
                list.add(new CartModel(R.drawable.katla, "Katla", "375tk", String.valueOf(katla_clicked)));
                price += 375 * katla_clicked;
            }
        }

        // ... (Add the rest of the fish items)

    }

    // Process milk items
    private void processMilkItems(ArrayList<CartModel> list, HashMap<String, Integer> milkClickCounts) {
        if (milkClickCounts.containsKey("danish")) {
            int danish_clicked = milkClickCounts.get("danish");

            if (danish_clicked > 0) {
                list.add(new CartModel(R.drawable.danish, "Danish", "425tk", String.valueOf(danish_clicked)));
                price += 425 * danish_clicked;
            }
        }

        if (milkClickCounts.containsKey("arong")) {
            int arong_clicked = milkClickCounts.get("arong");

            if (arong_clicked > 0) {
                list.add(new CartModel(R.drawable.arong, "Arong", "400tk", String.valueOf(arong_clicked)));
                price += 400 * arong_clicked;
            }
        }

        if (milkClickCounts.containsKey("cowhead")) {
            int cowhead_clicked = milkClickCounts.get("cowhead");

            if (cowhead_clicked > 0) {
                list.add(new CartModel(R.drawable.cowhead, "Cowhead", "330tk", String.valueOf(cowhead_clicked)));
                price += 330 * cowhead_clicked;
            }
        }

        if (milkClickCounts.containsKey("dano")) {
            int dano_clicked = milkClickCounts.get("dano");

            if (dano_clicked > 0) {
                list.add(new CartModel(R.drawable.dano, "Dano", "799tk", String.valueOf(dano_clicked)));
                price += 799 * dano_clicked;
            }
        }

        if (milkClickCounts.containsKey("chocolatemilk")) {
            int chocolatemilk_clicked = milkClickCounts.get("chocolatemilk");

            if (chocolatemilk_clicked > 0) {
                list.add(new CartModel(R.drawable.choco, "Chocolate Milk", "30tk", String.valueOf(chocolatemilk_clicked)));
                price += 30 * chocolatemilk_clicked;
            }
        }

        if (milkClickCounts.containsKey("fresh")) {
            int fresh_clicked = milkClickCounts.get("fresh");

            if (fresh_clicked > 0) {
                list.add(new CartModel(R.drawable.fresh, "Fresh", "815tk", String.valueOf(fresh_clicked)));
                price += 815 * fresh_clicked;
            }
        }

        if (milkClickCounts.containsKey("mangomilk")) {
            int mangomilk_clicked = milkClickCounts.get("mangomilk");

            if (mangomilk_clicked > 0) {
                list.add(new CartModel(R.drawable.mango, "Mango Milk", "25tk", String.valueOf(mangomilk_clicked)));
                price += 25 * mangomilk_clicked;
            }
        }

        if (milkClickCounts.containsKey("marks")) {
            int marks_clicked = milkClickCounts.get("marks");

            if (marks_clicked > 0) {
                list.add(new CartModel(R.drawable.marks, "Marks", "400tk", String.valueOf(marks_clicked)));
                price += 400 * marks_clicked;
            }
        }

        if (milkClickCounts.containsKey("pranmilk")) {
            int pranmilk_clicked = milkClickCounts.get("pranmilk");

            if (pranmilk_clicked > 0) {
                list.add(new CartModel(R.drawable.pran, "Pran Milk", "400tk", String.valueOf(pranmilk_clicked)));
                price += 400 * pranmilk_clicked;
            }
        }

        if (milkClickCounts.containsKey("milkman")) {
            int milkman_clicked = milkClickCounts.get("milkman");

            if (milkman_clicked > 0) {
                list.add(new CartModel(R.drawable.milkman, "Milkman", "150tk", String.valueOf(milkman_clicked)));
                price += 150 * milkman_clicked;
            }
        }

        if (milkClickCounts.containsKey("redcow")) {
            int redcow_clicked = milkClickCounts.get("redcow");

            if (redcow_clicked > 0) {
                list.add(new CartModel(R.drawable.redcow, "Redcow", "405tk", String.valueOf(redcow_clicked)));
                price += 405 * redcow_clicked;
            }
        }

        if (milkClickCounts.containsKey("strawberrymilk")) {
            int strawberrymilk_clicked = milkClickCounts.get("strawberrymilk");

            if (strawberrymilk_clicked > 0) {
                list.add(new CartModel(R.drawable.strawberrymilk, "Strawberry Milk", "25tk", String.valueOf(strawberrymilk_clicked)));
                price += 25 * strawberrymilk_clicked;
            }
        }
    }

    // Process vegetable items
    private void processVegetableItems(ArrayList<CartModel> list, HashMap<String, Integer> vegetableClickCounts) {
        if (vegetableClickCounts.containsKey("alovera")) {
            int alovera_clicked = vegetableClickCounts.get("alovera");

            if (alovera_clicked > 0) {
                list.add(new CartModel(R.drawable.alovera, "Alovera", "60tk", String.valueOf(alovera_clicked)));
                price += 60 * alovera_clicked;
            }
        }
        if (vegetableClickCounts.containsKey("cabage")) {
            int cabage_clicked = vegetableClickCounts.get("cabage");

            if (cabage_clicked > 0) {
                list.add(new CartModel(R.drawable.badhakopi, "cabage", "55tk", String.valueOf(cabage_clicked)));
                price += 55 * cabage_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("badhakopi")) {
            int badhakopi_clicked = vegetableClickCounts.get("badhakopi");

            if (badhakopi_clicked > 0) {
                list.add(new CartModel(R.drawable.badhakopi, "Badhakopi", "55tk", String.valueOf(badhakopi_clicked)));
                price += 55 * badhakopi_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("begun")) {
            int begun_clicked = vegetableClickCounts.get("begun");

            if (begun_clicked > 0) {
                list.add(new CartModel(R.drawable.begun, "Begun", "75tk", String.valueOf(begun_clicked)));
                price += 75 * begun_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("capsicum")) {
            int capsicum_clicked = vegetableClickCounts.get("capsicum");

            if (capsicum_clicked > 0) {
                list.add(new CartModel(R.drawable.capsicum, "Capsicum", "480tk", String.valueOf(capsicum_clicked)));
                price += 480 * capsicum_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("capsicumyellow")) {
            int capsicumyellow_clicked = vegetableClickCounts.get("capsicumyellow");

            if (capsicumyellow_clicked > 0) {
                list.add(new CartModel(R.drawable.capsicumyellow, "Capsicum Yellow", "500tk", String.valueOf(capsicumyellow_clicked)));
                price += 500 * capsicumyellow_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("carrot")) {
            int carrot_clicked = vegetableClickCounts.get("carrot");

            if (carrot_clicked > 0) {
                list.add(new CartModel(R.drawable.carrot, "Carrot", "100tk", String.valueOf(carrot_clicked)));
                price += 100 * carrot_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("chichinga")) {
            int chichinga_clicked = vegetableClickCounts.get("chichinga");

            if (chichinga_clicked > 0) {
                list.add(new CartModel(R.drawable.chichinga, "Chichinga", "66tk", String.valueOf(chichinga_clicked)));
                price += 66 * chichinga_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("dhundul")) {
            int dhundul_clicked = vegetableClickCounts.get("dhundul");

            if (dhundul_clicked > 0) {
                list.add(new CartModel(R.drawable.dhundul, "Dhundul", "80tk", String.valueOf(dhundul_clicked)));
                price += 80 * dhundul_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("fulcopy")) {
            int fulcopy_clicked = vegetableClickCounts.get("fulcopy");

            if (fulcopy_clicked > 0) {
                list.add(new CartModel(R.drawable.fulcopy, "Fulcopy", "70tk", String.valueOf(fulcopy_clicked)));
                price += 70 * fulcopy_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("greenpapaya")) {
            int greenpapaya_clicked = vegetableClickCounts.get("greenpapaya");

            if (greenpapaya_clicked > 0) {
                list.add(new CartModel(R.drawable.greenpapaya, "Green Papaya", "70tk", String.valueOf(greenpapaya_clicked)));
                price += 70 * greenpapaya_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("greenpumpkin")) {
            int greenpumpkin_clicked = vegetableClickCounts.get("greenpumpkin");

            if (greenpumpkin_clicked > 0) {
                list.add(new CartModel(R.drawable.greenpumpkin, "Green Pumpkin", "65tk", String.valueOf(greenpumpkin_clicked)));
                price += 65 * greenpumpkin_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("greentomato")) {
            int greentomato_clicked = vegetableClickCounts.get("greentomato");

            if (greentomato_clicked > 0) {
                list.add(new CartModel(R.drawable.greentomato, "Green Tomato", "45tk", String.valueOf(greentomato_clicked)));
                price += 45 * greentomato_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("kachakola")) {
            int kachakola_clicked = vegetableClickCounts.get("kachakola");

            if (kachakola_clicked > 0) {
                list.add(new CartModel(R.drawable.kachakola, "Kachakola", "12tk", String.valueOf(kachakola_clicked)));
                price += 12 * kachakola_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("kagozilemon")) {
            int kagozilemon_clicked = vegetableClickCounts.get("kagozilemon");

            if (kagozilemon_clicked > 0) {
                list.add(new CartModel(R.drawable.kagozilemon, "Kagozi Lemon", "10tk", String.valueOf(kagozilemon_clicked)));
                price += 10 * kagozilemon_clicked;
            }
        }
        if (vegetableClickCounts.containsKey("kolmishak")) {
            int kolmishak_clicked = vegetableClickCounts.get("kolmishak");

            if (kolmishak_clicked > 0) {
                list.add(new CartModel(R.drawable.kolmishak, "Kolmishak", "5tk", String.valueOf(kolmishak_clicked)));
                price += 5 * kolmishak_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("lalshak")) {
            int lalshak_clicked = vegetableClickCounts.get("lalshak");

            if (lalshak_clicked > 0) {
                list.add(new CartModel(R.drawable.lalshak, "Lalshak", "16tk", String.valueOf(lalshak_clicked)));
                price += 16 * lalshak_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("mishtikumra")) {
            int mishtikumra_clicked = vegetableClickCounts.get("mishtikumra");

            if (mishtikumra_clicked > 0) {
                list.add(new CartModel(R.drawable.mishtikumra, "Mishti Kumra", "45tk", String.valueOf(mishtikumra_clicked)));
                price += 45 * mishtikumra_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("mula")) {
            int mula_clicked = vegetableClickCounts.get("mula");

            if (mula_clicked > 0) {
                list.add(new CartModel(R.drawable.mula, "Mula", "82tk", String.valueOf(mula_clicked)));
                price += 82 * mula_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("newalu")) {
            int newalu_clicked = vegetableClickCounts.get("newalu");

            if (newalu_clicked > 0) {
                list.add(new CartModel(R.drawable.newalu, "New Alu", "35tk", String.valueOf(newalu_clicked)));
                price += 35 * newalu_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("palokshak")) {
            int palokshak_clicked = vegetableClickCounts.get("palokshak");

            if (palokshak_clicked > 0) {
                list.add(new CartModel(R.drawable.palokshak, "Palok Shak", "16tk", String.valueOf(palokshak_clicked)));
                price += 16 * palokshak_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("potol")) {
            int potol_clicked = vegetableClickCounts.get("potol");

            if (potol_clicked > 0) {
                list.add(new CartModel(R.drawable.potol, "Potol", "35tk", String.valueOf(potol_clicked)));
                price += 35 * potol_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("sheem")) {
            int sheem_clicked = vegetableClickCounts.get("sheem");

            if (sheem_clicked > 0) {
                list.add(new CartModel(R.drawable.sheem, "Sheem", "60tk", String.valueOf(sheem_clicked)));
                price += 60 * sheem_clicked;
            }
        }

        if (vegetableClickCounts.containsKey("tomato")) {
            int tomato_clicked = vegetableClickCounts.get("tomato");

            if (tomato_clicked > 0) {
                list.add(new CartModel(R.drawable.tomato, "Tomato", "28tk", String.valueOf(tomato_clicked)));
                price += 28 * tomato_clicked;
            }
        }
    }
    // Process rice items
    private void processRiceItems(ArrayList<CartModel> list, HashMap<String, Integer> riceClickCounts) {
        if (riceClickCounts.containsKey("aci")) {
            int aci_clicked = riceClickCounts.get("aci");

            if (aci_clicked > 0) {
                list.add(new CartModel(R.drawable.aci, "Aci Rice", "350tk", String.valueOf(aci_clicked)));
                price += 350 * aci_clicked;
            }
        }

        if (riceClickCounts.containsKey("bashmoti")) {
            int bashmoti_clicked = riceClickCounts.get("bashmoti");

            if (bashmoti_clicked > 0) {
                list.add(new CartModel(R.drawable.bashmoti, "Bashmoti Rice", "290tk", String.valueOf(bashmoti_clicked)));
                price += 290 * bashmoti_clicked;
            }
        }

        if (riceClickCounts.containsKey("chinigura_rice")) {
            int chinigura_rice_clicked = riceClickCounts.get("chinigura_rice");

            if (chinigura_rice_clicked > 0) {
                list.add(new CartModel(R.drawable.chinigura_rice, "Chinigura Rice", "170tk", String.valueOf(chinigura_rice_clicked)));
                price += 170 * chinigura_rice_clicked;
            }
        }

        if (riceClickCounts.containsKey("chashi")) {
            int chashi_clicked = riceClickCounts.get("chashi");

            if (chashi_clicked > 0) {
                list.add(new CartModel(R.drawable.chashi, "Chashi Rice", "180tk", String.valueOf(chashi_clicked)));
                price += 180 * chashi_clicked;
            }
        }

        if (riceClickCounts.containsKey("fortune")) {
            int fortune_clicked = riceClickCounts.get("fortune");

            if (fortune_clicked > 0) {
                list.add(new CartModel(R.drawable.fortune, "Fortune Rice", "350tk", String.valueOf(fortune_clicked)));
                price += 350 * fortune_clicked;
            }
        }

        if (riceClickCounts.containsKey("dawat")) {
            int dawat_clicked = riceClickCounts.get("dawat");

            if (dawat_clicked > 0) {
                list.add(new CartModel(R.drawable.dawat, "Dawat Rice", "2239tk", String.valueOf(dawat_clicked)));
                price += 2239 * dawat_clicked;
            }
        }

        if (riceClickCounts.containsKey("nazirshail")) {
            int nazirshail_clicked = riceClickCounts.get("nazirshail");

            if (nazirshail_clicked > 0) {
                list.add(new CartModel(R.drawable.nazirshail, "Nazirshail Rice", "60tk", String.valueOf(nazirshail_clicked)));
                price += 60 * nazirshail_clicked;
            }
        }

        if (riceClickCounts.containsKey("miniket")) {
            int miniket_clicked = riceClickCounts.get("miniket");

            if (miniket_clicked > 0) {
                list.add(new CartModel(R.drawable.miniket, "Miniket Rice", "71tk", String.valueOf(miniket_clicked)));
                price += 71 * miniket_clicked;
            }
        }

        if (riceClickCounts.containsKey("rupchada")) {
            int rupchada_clicked = riceClickCounts.get("rupchada");

            if (rupchada_clicked > 0) {
                list.add(new CartModel(R.drawable.rupchada, "Rupchada Rice", "180tk", String.valueOf(rupchada_clicked)));
                price += 180 * rupchada_clicked;
            }
        }

        if (riceClickCounts.containsKey("pran_rice")) {
            int pran_rice_clicked = riceClickCounts.get("pran_rice");

            if (pran_rice_clicked > 0) {
                list.add(new CartModel(R.drawable.pran_rice, "Pran Rice", "350tk", String.valueOf(pran_rice_clicked)));
                price += 350 * pran_rice_clicked;
            }
        }

    }
    private void processOilItems(ArrayList<CartModel> list, HashMap<String, Integer> oilClickCounts) {
        if (oilClickCounts.containsKey("bashundhora_oil")) {
            int bashundhora_oil_clicked = oilClickCounts.get("bashundhora_oil");

            if (bashundhora_oil_clicked > 0) {
                list.add(new CartModel(R.drawable.bashundhora_oil, "Bashundhora Oil", "324tk", String.valueOf(bashundhora_oil_clicked)));
                price += 324 * bashundhora_oil_clicked;
            }
        }

        if (oilClickCounts.containsKey("aci_oil")) {
            int aci_oil_clicked = oilClickCounts.get("aci_oil");

            if (aci_oil_clicked > 0) {
                list.add(new CartModel(R.drawable.aci_oil, "Aci Oil", "975tk", String.valueOf(aci_oil_clicked)));
                price += 975 * aci_oil_clicked;
            }
        }

        if (oilClickCounts.containsKey("fresh_soyabin")) {
            int fresh_soyabin_clicked = oilClickCounts.get("fresh_soyabin");

            if (fresh_soyabin_clicked > 0) {
                list.add(new CartModel(R.drawable.fresh_soyabin, "Fresh Soyabin Oil", "890tk", String.valueOf(fresh_soyabin_clicked)));
                price += 890 * fresh_soyabin_clicked;
            }
        }

        if (oilClickCounts.containsKey("fortune_oil")) {
            int fortune_oil_clicked = oilClickCounts.get("fortune_oil");

            if (fortune_oil_clicked > 0) {
                list.add(new CartModel(R.drawable.fortune_oil, "Fortune Oil", "320tk", String.valueOf(fortune_oil_clicked)));
                price += 320 * fortune_oil_clicked;
            }
        }

        if (oilClickCounts.containsKey("pushti")) {
            int pushti_clicked = oilClickCounts.get("pushti");

            if (pushti_clicked > 0) {
                list.add(new CartModel(R.drawable.pushti, "Pushti Oil", "180tk", String.valueOf(pushti_clicked)));
                price += 180 * pushti_clicked;
            }
        }

        if (oilClickCounts.containsKey("kings")) {
            int kings_clicked = oilClickCounts.get("kings");

            if (kings_clicked > 0) {
                list.add(new CartModel(R.drawable.kings, "Kings Oil", "1900tk", String.valueOf(kings_clicked)));
                price += 1900 * kings_clicked;
            }
        }

        if (oilClickCounts.containsKey("rupchada_soyabin")) {
            int rupchada_soyabin_clicked = oilClickCounts.get("rupchada_soyabin");

            if (rupchada_soyabin_clicked > 0) {
                list.add(new CartModel(R.drawable.rupchada_soyabin, "Rupchada Soyabin Oil", "870tk", String.valueOf(rupchada_soyabin_clicked)));
                price += 870 * rupchada_soyabin_clicked;
            }
        }

        if (oilClickCounts.containsKey("safalo")) {
            int safalo_clicked = oilClickCounts.get("safalo");

            if (safalo_clicked > 0) {
                list.add(new CartModel(R.drawable.safalo, "Safalo Oil", "1500tk", String.valueOf(safalo_clicked)));
                price += 1500 * safalo_clicked;
            }
        }

        if (oilClickCounts.containsKey("teer")) {
            int teer_clicked = oilClickCounts.get("teer");

            if (teer_clicked > 0) {
                list.add(new CartModel(R.drawable.teer, "Teer Oil", "920tk", String.valueOf(teer_clicked)));
                price += 920 * teer_clicked;
            }
        }

        if (oilClickCounts.containsKey("sunflower_oil")) {
            int sunflower_oil_clicked = oilClickCounts.get("sunflower_oil");

            if (sunflower_oil_clicked > 0) {
                list.add(new CartModel(R.drawable.sunflower_oil, "Sunflower Oil", "2200tk", String.valueOf(sunflower_oil_clicked)));
                price += 2200 * sunflower_oil_clicked;
            }
        }
    }


    // Create a helper method in Fruits.java to get the current user's ID
    private String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }
    private void deleteAllData() {
        referencedelete = FirebaseDatabase.getInstance().getReference(username);
        referencedelete.removeValue();

        // Clear the cartItems list
        cartItems.clear();

        // Notify the adapter that data has changed
        adapter.notifyDataSetChanged();

    }

}