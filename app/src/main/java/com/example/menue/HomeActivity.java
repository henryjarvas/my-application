package com.example.menue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

     CardView schoolfess, agri, bodab, others, account, set;
     LinearLayout ii;
     TextView dash, testing;
     String optionSeleted;
     SessionManager sessionManager;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dash= findViewById(R.id.dashboard);

//        Intent intent = getIntent();
//        String names = intent.getStringExtra("fullname");

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
       String fullnames = user.get(SessionManager.FULLNAME);

        dash.setText("welcome" + fullnames);

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


       ii =findViewById(R.id.ll);
       schoolfess = findViewById(R.id.SafeCash);
        schoolfess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSeleted = "School Fees";
                Intent intent=new Intent(HomeActivity.this,saving_time.class);
                intent.putExtra("optionSelected",optionSeleted);
                startActivity(intent);
            }
        });


       agri = findViewById(R.id.safemoney);
        agri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSeleted = "Agric";
                Intent intent=new Intent(HomeActivity.this,saving_time.class);
                intent.putExtra("optionSelected",optionSeleted);
                startActivity(intent);
            }
        });



       bodab = findViewById(R.id.bb);
        bodab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSeleted = "boda boda";
                Intent intent=new Intent(HomeActivity.this,saving_time.class);
                intent.putExtra("optionSelected",optionSeleted);
                startActivity(intent);
            }
        });



        others = findViewById(R.id.Other);
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSeleted = "saving";
                Intent intent=new Intent(HomeActivity.this,saving_time.class);
                intent.putExtra("optionSelected",optionSeleted);
                startActivity(intent);
            }
        });



        account = findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,my_wallet.class);
                startActivity(intent);
            }
        });



        set = findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,Settings_Activity.class);
                startActivity(intent);
            }
        });




    }
}
