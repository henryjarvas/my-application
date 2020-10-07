package com.example.menue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.menue.Adapters.SavingAdapter;
import com.example.menue.Models.SavingModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class check_balance extends AppCompatActivity {
    ImageView arr;
    Button balance,bonusbtn;
    EditText etpassword;
    TextView information, actual_balance, actual_bonus, teeeeesssssstttttt, bonus_text;
    SessionManager sessionManager;
    String contact;
    private static final String BALANCE_LIST_URL = "http://192.168.1.178:8080/mosaver/get_balance.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
//        fullname = user.get(SessionManager.FULLNAME);
        contact = user.get(SessionManager.CONTACT);
        actual_bonus = findViewById(R.id.actual_bonus);
        information = findViewById(R.id.information);
        actual_balance = findViewById(R.id.actual_balance);
        balance = findViewById(R.id.balancebtn);
        bonusbtn = findViewById(R.id.bonusbtn);
        etpassword = findViewById(R.id.etPassword);
        bonus_text = findViewById(R.id.bonus_text);
        teeeeesssssstttttt = findViewById(R.id.teeeeesssssstttttt);
        arr = findViewById(R.id.back);
        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(check_balance.this, my_wallet.class);
                startActivity(intent);
            }
        });


        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                String password = etpassword.getText().toString().trim();
                if (password.isEmpty()) {
                    Toast.makeText(check_balance.this, "password is required", Toast.LENGTH_SHORT).show();
                } else {

                    loadBalance();
                    bonusbtn.setVisibility(View.VISIBLE);
                }

            }
        });

        bonusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus_text.setVisibility(View.VISIBLE);
                String mybalancesssss = actual_balance.getText().toString();

                double nnn = 0.1*Integer.parseInt(mybalancesssss) ;
//        String bbbbbbbb = String.valueOf(nnn);
//        teeeeesssssstttttt.setText(bbbbbbbb);

                /* get the  bonus from balance*/

                //display the bonus
                String getBonus = String.valueOf(nnn);
                actual_bonus.setText(getBonus);
            }
        });


        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(home);
                        break;

                    case R.id.ic_help:
                        Intent help = new Intent(getApplicationContext(), HelpActivity.class);
                        startActivity(help);
                        break;

                    case R.id.ic_settings:
                        Intent settings = new Intent(getApplicationContext(), Settings_Activity.class);
                        startActivity(settings);
                        break;

                    case R.id.ic_exit:
                        Intent logout = new Intent(getApplicationContext(), join.class);
                        startActivity(logout);
                        break;
                }


                return false;
            }
        });

        //// load bonuses


    }


    private void loadBalance() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BALANCE_LIST_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONArray users = new JSONArray(response);
                            for (int i = 0; i < users.length(); i++) {
                                JSONObject object = users.getJSONObject(i);

//                                String id = object.getString("id");
                                String my_balance = object.getString("balance");


                                information.setVisibility(View.VISIBLE);
                                actual_balance.setVisibility(View.VISIBLE);
                                actual_balance.setText(my_balance);

                            }
//                            adapter = new SavingAdapter(SavingsActivity.this, mData);
//                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
//                            loading.dismiss();
//                            login.setVisibility(View.VISIBLE);
                            Toast.makeText(check_balance.this, "failed to save " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//
                        Toast.makeText(check_balance.this, "Saving continues to  fail" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("contact", contact);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loadBonus() {
        /*first get the balance
        * get grand amount
        * */

    }
}

