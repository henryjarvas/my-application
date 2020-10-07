package com.example.menue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class join extends AppCompatActivity {
    TextView welcoming;
    LinearLayout newac,log;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        welcoming = findViewById(R.id.welcome);
        newac = findViewById(R.id.newac);
        log= findViewById(R.id.log);
        pic = findViewById(R.id.back);


       newac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(join.this,sign_up.class);
                startActivity(intent);
            }
        });


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(join.this,login.class);
                startActivity(intent);
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(join.this,Settings_Activity.class);
                startActivity(intent);
            }
        });

    }
}
