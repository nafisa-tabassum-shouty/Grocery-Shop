package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordChecker1 {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{12,}$";

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
public class ResetPassword extends AppCompatActivity {

    EditText NewPassword, confirmPassword;
    TextView LOG;
    Button confirm;
    DatabaseReference reference;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        NewPassword = findViewById(R.id.NewPassword);
        confirmPassword = findViewById(R.id.ConfirmPassword);
        confirm = findViewById(R.id.ResetPass);
        username = getIntent().getStringExtra("username");
        LOG=findViewById(R.id.log);

        LOG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResetPassword.this,LoginActivity.class));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = NewPassword.getText().toString().trim();
                String confirmPass = confirmPassword.getText().toString().trim();
                if(newPassword.equals(confirmPass))
                {
                    if(confirmPass.length()==0 || newPassword.length()==0)
                    {
                        Toast.makeText(ResetPassword.this, "Fill up all information", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        if(PasswordChecker.isValidPassword(newPassword)){
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(username);
                            updateData(newPassword);
                            startActivity(new Intent(ResetPassword.this,LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "The password requirements were not met. Please check the password rules.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(ResetPassword.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    private void updateData(String toString) {
        HashMap User = new HashMap();
        User.put("password",toString);
        reference = FirebaseDatabase.getInstance().getReference("Users").child(username);
        reference.updateChildren(User);
        Toast.makeText(ResetPassword.this,"SuccessfullyUpdated",Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(ResetPassword.this,LoginActivity.class));
    }
}
