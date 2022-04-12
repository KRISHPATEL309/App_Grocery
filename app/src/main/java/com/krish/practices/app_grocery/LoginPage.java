package com.krish.practices.app_grocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.AuthResult;

public class LoginPage extends AppCompatActivity {

    EditText Emaillogin,Passwordlogin;
    TextView New_here;
    Button next_btnL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Emaillogin = findViewById(R.id.Emaillogin);
        Passwordlogin = findViewById(R.id.Passwordlogin);
        next_btnL = findViewById(R.id.next_btnL);
        New_here=findViewById(R.id.New_here);

        New_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignupPage.class));
            }
        });
        next_btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Emaillogin.getText().toString();
                String password = Passwordlogin.getText().toString();

                if (email.isEmpty() && password.isEmpty()) {
                    if (email.isEmpty()) {
                        Emaillogin.setError("Email is required");
                        return;
                    }

                    if (password.isEmpty()) {
                        Passwordlogin.setError("Password is required");
                        return;
                    }
                }
                startActivity(new Intent(getApplicationContext(),Bottomnavigation.class));
                finish();
            }
        });

    }
}