package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fruits extends AppCompatActivity {
    // Declare your variables
    Button add,delete,addgala,deletegala;
    TextView quantity,quantitygala;
    ImageView back;
    int clickCount;
    Button Cart, AppleFuji, Apple_Gala, redgraps, sweetorange, orangeindian, strawberry, applegolden, shagorkiola, shobrikola, chompakola, stripewatermelon, blackwatermelon, pomegranate, pears;
    DatabaseReference fruitReference;
    Users users;
        public void toastshow(){
            Toast.makeText(getApplicationContext(), "Item has been added to Cart", Toast.LENGTH_SHORT).show();
        }
    public void toastshowdelete(){
        Toast.makeText(getApplicationContext(), "Item has been removed from Cart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruits);


        String username = getIntent().getStringExtra("username");

        // Initialize your variables
        back = findViewById(R.id.backto);
        Cart = findViewById(R.id.Cart);


        add=findViewById(R.id.ADD);
        delete=findViewById(R.id.DELETE);
        addgala=findViewById(R.id.ADDgala);
        deletegala=findViewById(R.id.DELETEgala);
        quantity=findViewById(R.id.quantity);
        quantitygala=findViewById(R.id.quantitygala);

        AppleFuji = findViewById(R.id.applefuzi);
        Apple_Gala = findViewById(R.id.applegala);
        redgraps = findViewById(R.id.redgraps);
        sweetorange = findViewById(R.id.sweetorange);
        orangeindian = findViewById(R.id.orangeindian);
        strawberry = findViewById(R.id.strawberry);
        applegolden = findViewById(R.id.applegolden);
        shagorkiola = findViewById(R.id.sagorkola);
        shobrikola = findViewById(R.id.shobrikola);
        chompakola = findViewById(R.id.chompakola);
        stripewatermelon = findViewById(R.id.stripewatermelon);
        blackwatermelon = findViewById(R.id.blackwatermelon);
        pomegranate = findViewById(R.id.pomegranate);
        pears = findViewById(R.id.pears);

        System.out.println(username);

        // Initialize the DatabaseReference
        String userId = getCurrentUserId(); // Implement a method to get the current user's ID
        fruitReference = FirebaseDatabase.getInstance().getReference(username).child("fruits");

        fruitReference.child("AppleFuji").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                quantity.setText(String.valueOf((clickCount)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
        fruitReference.child("Apple_Gala").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                quantitygala.setText(String.valueOf((clickCount)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });


        // Initialize the DatabaseReference
        //fruitReference = FirebaseDatabase.getInstance().getReference("Fruits");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("AppleFuji");
                fruitReference.child("AppleFuji").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        clickCount = 0;
                        if (dataSnapshot.exists()) {
                            clickCount = dataSnapshot.getValue(Integer.class);
                        }
                        quantity.setText(String.valueOf((clickCount+1)));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshowdelete();
                deleteFruitClickCount("AppleFuji");
                fruitReference.child("AppleFuji").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        clickCount = 0;
                        if (dataSnapshot.exists()) {
                            clickCount = dataSnapshot.getValue(Integer.class);
                        }
                        quantity.setText(String.valueOf((clickCount-1)));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });
            }
        });
        addgala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("Apple_Gala");
                fruitReference.child("Apple_Gala").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        clickCount = 0;
                        if (dataSnapshot.exists()) {
                            clickCount = dataSnapshot.getValue(Integer.class);
                        }
                        quantitygala.setText(String.valueOf((clickCount+1)));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });
            }
        });
        deletegala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshowdelete();
                deleteFruitClickCount("Apple_Gala");
                fruitReference.child("Apple_Gala").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        clickCount = 0;
                        if (dataSnapshot.exists()) {
                            clickCount = dataSnapshot.getValue(Integer.class);
                        }
                        quantitygala.setText(String.valueOf((clickCount-1)));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });
            }
        });

        // Set the click listeners for the fruit buttons
        AppleFuji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("AppleFuji");
                fruitReference.child("AppleFuji").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        clickCount = 0;
                        if (dataSnapshot.exists()) {
                            clickCount = dataSnapshot.getValue(Integer.class);
                        }
                        quantity.setText(String.valueOf((clickCount+1)));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });

            }
        });

        Apple_Gala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("Apple_Gala");
                fruitReference.child("Apple_Gala").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        clickCount = 0;
                        if (dataSnapshot.exists()) {
                            clickCount = dataSnapshot.getValue(Integer.class);
                        }
                        quantitygala.setText(String.valueOf((clickCount+1)));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });
            }
        });

        redgraps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("redgraps");
            }
        });

        sweetorange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("sweetorange");
            }
        });

        orangeindian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toastshow();
                updateFruitClickCount("orangeindian");
            }
        });

        strawberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toastshow();
                updateFruitClickCount("strawberry");
            }
        });

        applegolden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();

                updateFruitClickCount("applegolden");
            }
        });

        shagorkiola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("shagorkiola");
            }
        });

        shobrikola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("shobrikola");
            }
        });

        chompakola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("chompakola");
            }
        });

        stripewatermelon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("stripewatermelon");
            }
        });

        blackwatermelon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("blackwatermelon");
            }
        });

        pears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("pears");
            }
        });

        pomegranate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastshow();
                updateFruitClickCount("pomegranate");
            }
        });

        // Set click listener for the Cart button
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 =new Intent(Fruits.this, Cart.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        // Set click listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Fruits.this, Catagories.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
    }

    // Helper method to update the click count of a fruit
    private void updateFruitClickCount(String fruitName) {
        fruitReference.child(fruitName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                clickCount++;
                fruitReference.child(fruitName).setValue(clickCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    private void deleteFruitClickCount(String fruitName) {
        fruitReference.child(fruitName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clickCount = 0;
                if (dataSnapshot.exists()) {
                    clickCount = dataSnapshot.getValue(Integer.class);
                }
                clickCount--;
                fruitReference.child(fruitName).setValue(clickCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    // Create a helper method in Fruits.java to get the current user's ID
    private String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }

}
