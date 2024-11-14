package com.example.groceryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgetPassword extends AppCompatActivity {
    EditText Username, Email;
    Button next;
    ImageView back;
    DatabaseReference reference;
    Users users;
    String username, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Username = findViewById(R.id.ForgetPassUserName);
        Email = findViewById(R.id.Forget_pass_Email);
        next = findViewById(R.id.Next);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(ForgetPassword.this,LoginActivity.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference("Users").child(Username.getText().toString());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        username = (snapshot.child("username").getValue().toString());
                        email = (snapshot.child("email").getValue().toString());
                        if (username.equals(Username.getText().toString()) && (email.equals(Email.getText().toString()))) {
                            Toast.makeText(ForgetPassword.this, "Email & Username matched", Toast.LENGTH_SHORT).show();
                            Intent intent1 =new Intent(ForgetPassword.this, ResetPassword.class);
                            intent1.putExtra("username", username);
                            startActivity(intent1);
                        } else {
                            if (!(username.equals(Username.getText().toString()))) {
                                Toast.makeText(ForgetPassword.this, "Wrong Username", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgetPassword.this, "Wrong Email", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ForgetPassword.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}

/*
*  UserInfoReference = FirebaseDatabase.getInstance().getReference("UserInfo");
final String username = Username.getText().toString().trim(); // Use trim() to remove whitespaces
                final String email = Email.getText().toString().trim(); // Use trim() to remove whitespaces

                // Query the Firebase database to check for matching username and email
                UserInfoReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isMatched = false;

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // Retrieve user data from the snapshot
                            String dbEmail = snapshot.child("email").getValue(String.class);

                            // Debugging: Log the values to check if they match
                            Log.d("Debug", "Provided username: " + username);
                            Log.d("Debug", "Provided email: " + email);
                            Log.d("Debug", "Firebase email: " + dbEmail);

                            // Check if the provided email matches the email in the database
                            if (email.equalsIgnoreCase(dbEmail)) {
                                isMatched = true;
                                break;
                            }
                        }

                        if (isMatched) {
                            // Match found, navigate to ResetPassword activity
                            startActivity(new Intent(ForgetPassword.this, ResetPassword.class));
                        } else {
                            // No match found, show error message
                            Toast.makeText(ForgetPassword.this, "Username and Email didn't match", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle any errors or interruptions here
                        Toast.makeText(ForgetPassword.this, "Error occurred: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

 *
* */