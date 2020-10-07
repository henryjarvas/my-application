package com.example.menue;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class saving_time extends AppCompatActivity {

    LinearLayout onemonth, twomonths, threemonths, ayear;
    Button one, three, six, year, next, enterperiod_btn;
    EditText amount;
    ImageView image, arrow;
    Switch box6,box7,box8,box9;

TextView testing, testing2,testing3,testing4,options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_time);

        arrow = findViewById(R.id.back);
        onemonth = findViewById(R.id.onemonth);
        twomonths = findViewById(R.id.twomonths);
        threemonths = findViewById(R.id.threemonths);
        ayear = findViewById(R.id.oneyear);
       enterperiod_btn = findViewById(R.id.enterperiod);
        options = findViewById(R.id.optionSelected);



        testing = findViewById(R.id.testting);
        testing2 = findViewById(R.id.testting2);
        testing3 = findViewById(R.id.testting3);
        testing4 = findViewById(R.id.testting4);


        //get option selected
        Intent intent = getIntent();
        String bname = intent.getStringExtra("optionSelected");
options.setText(bname);


        image = findViewById(R.id.back);
        amount= findViewById(R.id.enter);

        box6= findViewById(R.id.checkBox6);
        box7= findViewById(R.id.checkBox7);
        box8= findViewById(R.id.checkBox8);
        box9= findViewById(R.id.checkBox9);
        next = findViewById(R.id.next);




box6.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        box6.setEnabled(true);
    }
});

        amount.setFocusable(false);
        amount.setFocusableInTouchMode(true);
        amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                box7.setEnabled(false);
                box8.setEnabled(false);
                box9.setEnabled(false);
                box6.setEnabled(false);
            }
        });



  //if one iz clicked,then disable the rest
        box6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    box7.setEnabled(false);
                    box8.setEnabled(false);
                    box9.setEnabled(false);
                    amount.setEnabled(false);
                    String boxes ;
                    boxes = box6.getText().toString();
                    testing.setText(boxes);
                    next.setEnabled(true);//activate the button on selecting this item

                }else if (!isChecked){
                    box7.setEnabled(true);
                    box8.setEnabled(true);
                    box9.setEnabled(true);
                    amount.setEnabled(true);
                    next.setEnabled(false);//if not selected, then dnt activate the btn
                }
            }
        });


        box7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    box6.setEnabled(false);
                    box8.setEnabled(false);
                    box9.setEnabled(false);
                    amount.setEnabled(false);
                    String boxes ;
                    boxes = box7.getText().toString();
                    testing.setText(boxes);
                    next.setEnabled(true);//activate the button on selecting this item

                }else if (!isChecked){
                    box7.setEnabled(true);
                    box8.setEnabled(true);
                    box9.setEnabled(true);
                    amount.setEnabled(true);
                    next.setEnabled(false);//if not selected, then dnt activate the btn
                }
            }
        });


        box8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    box7.setEnabled(false);
                    box6.setEnabled(false);
                    box9.setEnabled(false);
                    amount.setEnabled(false);
                    String boxes ;
                    boxes = box8.getText().toString();
                    testing.setText(boxes);
                    next.setEnabled(true);//activate the button on selecting this item

                }else if (!isChecked){
                    box7.setEnabled(true);
                    box6.setEnabled(true);
                    box9.setEnabled(true);
                    amount.setEnabled(true);
                    next.setEnabled(false);//if not selected, then dnt activate the btn
                }
            }
        });

        box9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    box7.setEnabled(false);
                    box6.setEnabled(false);
                    box8.setEnabled(false);
                    amount.setEnabled(false);
                    String boxes ;
                    boxes = box9.getText().toString();
                    testing.setText(boxes);
                    next.setEnabled(true);//activate the button on selecting this item

                }else if (!isChecked){
                    box7.setEnabled(true);
                    box6.setEnabled(true);
                    box8.setEnabled(true);
                    amount.setEnabled(true);
                    next.setEnabled(false);//if not selected, then dnt activate the btn
                }
            }
        });





        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(saving_time.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountentered ;
                amountentered = amount.getText().toString();
//                testing2.setText(boxes);

                String testBox = testing.getText().toString();
//                String testBox2 = testing2.getText().toString();
                String optionsSelected = options.getText().toString();

                Intent intent=new Intent(saving_time.this,saving_frequency.class);
                intent.putExtra("boxname",testBox);
                intent.putExtra("amount",amountentered);
                intent.putExtra("optionSelected",optionsSelected);
                startActivity(intent);
            }
        });

     //making the edit text vissible on byn click//
      enterperiod_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              next.setEnabled(true);
              String eamount = amount.getText().toString().trim();
              testing.setText(eamount);

//              Toast.makeText(saving_time.this, "nnnnnnnnnnnn", Toast.LENGTH_SHORT).show();
              if (amount.getVisibility()==View.VISIBLE){
                  amount.setVisibility(View.INVISIBLE);
                  box7.setEnabled(false);
                  box6.setEnabled(false);
                  box9.setEnabled(false);
                  box8.setEnabled(false);



              }else {
                  amount.setVisibility(View.VISIBLE);
                  box7.setEnabled(false);
                  box6.setEnabled(false);
                  box9.setEnabled(false);
                  box8.setEnabled(false);
//                  next.setEnabled(false);//if not selected, then dnt activate the btn

              }

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
