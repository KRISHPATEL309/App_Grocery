package com.krish.practices.app_grocery;

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

public class SignupPage extends AppCompatActivity {
    EditText Name,Mobile_num,EmailRegister,PasswordRegister,Cpassword;
    Button next_btnR;
    TextView Already;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        Mobile_num = findViewById(R.id.Mobile_num);
        EmailRegister = findViewById(R.id.EmailRegister);
        Name = findViewById(R.id.Name);
        PasswordRegister = findViewById(R.id.PasswordRegister);
        Cpassword = findViewById(R.id.Cpassword);
        next_btnR=findViewById(R.id.next_btnR);
        Already=findViewById(R.id.Already);
        fAuth = FirebaseAuth.getInstance();

        Already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginPage.class));
            }
        });

        next_btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String mobilenumber = Mobile_num.getText().toString();
                String email = EmailRegister.getText().toString();
                String password = PasswordRegister.getText().toString();
                String confirmpasword = Cpassword.getText().toString();


                if(name.isEmpty())
                {
                    Name.setError("Name is required");
                    return;
                }

                if(mobilenumber.isEmpty())
                {
                    Mobile_num.setError("Mobile is required");
                    return;
                }
                if(email.isEmpty())
                {
                    EmailRegister.setError("Email is required");
                    return;
                }
                if(password.isEmpty())
                {
                    PasswordRegister.setError("Password is required");
                    return;
                }
                if(confirmpasword.isEmpty())
                {
                    Cpassword.setError("Confirm Password is required");
                    return;
                }
                if(!password.equals(confirmpasword))
                {
                    Cpassword.setError("Password do not match");
                    return;
                }
                // data is validated
                Toast.makeText(SignupPage.this, "Data Validated", Toast.LENGTH_SHORT).show();
                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),SignupPage.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupPage.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}