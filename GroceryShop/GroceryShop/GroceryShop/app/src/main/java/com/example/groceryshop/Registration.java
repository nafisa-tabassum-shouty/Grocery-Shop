package com.example.groceryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordChecker {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{12,}$";

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

public class Registration extends AppCompatActivity {
    EditText username, password, email, confirmPassword;
    Button signup;
    TextView login;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    Users users;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        users = new Users();


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        username = findViewById(R.id.Pin);
        password = findViewById(R.id.NewPassword);
        signup = findViewById(R.id.ResetPass);
        login = findViewById(R.id.RegistrationLogin);
        email = findViewById(R.id.Forget_pass_Email);
        confirmPassword = findViewById(R.id.ConfirmPassword);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Pass = password.getText().toString();
                String Email = email.getText().toString();
                String confirmpass = confirmPassword.getText().toString();

                //reference =FirebaseDatabase.getInstance().getReference("users").child(Username);



                if (Pass.equals(confirmpass)) {
                    if (Username.length() == 0 || Pass.length() == 0 || Email.length() == 0 || confirmpass.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                    } else {
                        if (PasswordChecker.isValidPassword(Pass)) {

                           /* users.setUserName(username.getText().toString());
                            users.setPass(password.getText().toString());
                            users.setEmail(email.getText().toString());

                            reference.child("User Name").setValue(Username);
                            reference.child("Email").setValue(Email);
                            reference.child("Password").setValue(Pass);*/
                            saveUserInfoToDatabase(Username, Pass, Email);

                            // Create account with email and password
                            createAccountWithEmailAndPassword(Email, Pass, Username);
                            // Save user info to Firebase Realtime Database
                            //prev:


                            //Toast.makeText(Registration.this,"SuccessfullyInserted",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(Registration.this,before_home.class));


                        } else {
                            Toast.makeText(getApplicationContext(), "The password requirements were not met. Please check the password rules.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Password didn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, LoginActivity.class));
            }
        });
    }

    private void createAccountWithEmailAndPassword(String email, String password, String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            Toast.makeText(Registration.this, "Signup successful", Toast.LENGTH_SHORT).show();

                            // Go to the next activity
                            Intent intent1 =new Intent(Registration.this, before_home.class);
                            intent1.putExtra("username", username);
                            startActivity(intent1);
                            finish();
                        } else {
                            Toast.makeText(Registration.this, "User is null", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registration.this, "Signup failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }

    // Update the saveUserInfoToDatabase method in Registration.java
    private void saveUserInfoToDatabase(String username, String password, String email) {
        //String userId =getCurrentUserId();
        DatabaseReference userInfoRef = FirebaseDatabase.getInstance().getReference("Users").child(username);

        // Create a map for the user's activities
        /*Map<String, Object> userActivities = new HashMap<>();
        userActivities.put("fruits", 0); // You can set the initial value to 0 or any other default value
        userActivities.put("vegetables", 0);*/
        // Add more activities here as needed

        // Create a map for the user's information
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", email);
        userInfo.put("username", username);
        userInfo.put("password", password);
        //userInfo.put("Activities", userActivities);

        userInfoRef.setValue(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Registration.this, "User info saved successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registration.this, "Failed to save user info: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}



