package com.example.menue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class my_wallet extends AppCompatActivity {
  LinearLayout balance,transaction,bonus, notifications ,withdraw;
  TextView username,email;
  ImageView look;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);

        look = findViewById(R.id.back);
        withdraw = findViewById(R.id.withdraw);
        balance = findViewById(R.id.profile);
        transaction = findViewById(R.id.tra);
        bonus = findViewById(R.id.bns);
       notifications= findViewById(R.id.bn);

       username = findViewById(R.id.username);
       email = findViewById(R.id.email);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String fullnames = user.get(SessionManager.FULLNAME);
        String emailaddress = user.get(SessionManager.EMAIL);

        username.setText("" + fullnames);
        email.setText("" + emailaddress);



        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(my_wallet.this,HomeActivity.class);
                startActivity(intent);
            }
        });

       withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(my_wallet.this,withdraw.class);
                startActivity(intent);
            }
        });

        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(my_wallet.this,BalanceActivity.class);
                startActivity(intent);
            }
        });


        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(my_wallet.this,StatusActivity.class);
               startActivity(intent);
            }
        });



        bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bns= new Intent(my_wallet.this,bonus.class);
                startActivity(bns);
            }
        });


        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(my_wallet.this,NotificationsActivity.class);
                startActivity(intent);
            }
        });


        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent home = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(home);
                        break;

                    case R.id.ic_help:
                        Intent help = new Intent(getApplicationContext(),HelpActivity.class);
                        startActivity(help);
                        break;

                    case R.id.ic_settings:
                        Intent settings = new Intent(getApplicationContext(),Settings_Activity.class);
                        startActivity(settings);
                        break;

                    case R.id.ic_exit:
                        Intent logout = new Intent(getApplicationContext(),join.class);
                        startActivity(logout);
                        break;
                }


                return false;
            }
        });


    }
}
