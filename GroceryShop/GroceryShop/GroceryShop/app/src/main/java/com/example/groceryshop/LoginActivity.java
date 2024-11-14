package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;



public class LoginActivity extends AppCompatActivity {

    EditText username, password,email;
    Button BTN;
    TextView ForgetPass, Signup;

    private FirebaseAuth mAuth;
    String foundUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.ForgetPassUserName);
        password = findViewById(R.id.EmailPassword);
        email=findViewById(R.id.Forget_pass_Email);
        BTN = findViewById(R.id.SendPin);
        Signup = findViewById(R.id.Loginsignup);
        ForgetPass = findViewById(R.id.forgetPass);





        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Email=email.getText().toString();
                String Pass = password.getText().toString();
                if (Username.length() == 0 || Pass.length() == 0 || Email.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {

                    //findUsernameOfTheEmail(Email);
                    loginWithEmailAndPassword(Email, Pass);
                }
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foundUsername = username.getText().toString();

                Intent intent1 =new Intent(LoginActivity.this, Registration.class);
                intent1.putExtra("username", foundUsername);
                startActivity(intent1);
            }
        });
        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
            }
        });
    }

    private void loginWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login success, go to the next activity
                        foundUsername = username.getText().toString();
                        Intent intent1 =new Intent(LoginActivity.this, before_home.class);
                        intent1.putExtra("username", foundUsername);
                        startActivity(intent1);
                        finish();
                    } else {
                        // Login failed, display error message
                        Toast.makeText(LoginActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //read username of email
    private void findUsernameOfTheEmail(String email) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        Query query = databaseReference.orderByChild("email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        foundUsername = snapshot.child("username").getValue(String.class);
                        Log.d("TAG", "Username for " + email + ": " + foundUsername);
                        return; // Return the first match only
                    }
                } else {
                    Log.d("TAG", "No user found with the given email.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "Error occurred: " + databaseError.getMessage());
            }
        });
    }
}

