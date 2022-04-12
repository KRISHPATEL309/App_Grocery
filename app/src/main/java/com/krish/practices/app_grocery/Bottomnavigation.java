package com.krish.practices.app_grocery;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bottomnavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnavigation);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.Bottom_nev,new HomeFragment()).addToBackStack(null).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;

                switch (item.getItemId()){
                    case R.id.home:
                        fragment=new HomeFragment();
                        break;

                    case R.id.cart:
                        fragment=new CartFragment();
                        break;

                    case R.id.profile:
                        fragment=new ProfileFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.Bottom_nev,fragment).addToBackStack(null).commit();
                return true;
            }
        });
    }
}