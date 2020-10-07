package com.example.menue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class withdraw extends AppCompatActivity {

    ImageView arr;
    Button confirm_withdraw;
    EditText withdraw_amount, withdraw_password;
    TextView mybalance, dummy, report ,test,fullname;

    SessionManager sessionManager;//fetching
    String contact, getID;
    private static final String BALANCE_LIST_URL = "http://192.168.1.178:8080/mosaver/get_balance.php";
    private static final String WITHDRAW_URL = "http://192.168.1.178:8080/mosaver/get_withdraw.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getID = user.get(SessionManager.ID);
        contact = user.get(SessionManager.CONTACT);


        arr = findViewById(R.id.back);
        test = findViewById(R.id.test);
        dummy = findViewById(R.id.dummy);
        report = findViewById(R.id.report);
        mybalance = findViewById(R.id.mybalance);
        fullname = findViewById(R.id.fullname);
        confirm_withdraw = findViewById(R.id.confirm_withdraw);
        withdraw_amount = findViewById(R.id.withdraw_amount);
        withdraw_password = findViewById(R.id.withdraw_password);

//runs first
        loadBalance();

        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(withdraw.this, my_wallet.class);
                startActivity(intent);
            }
        });

        confirm_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = withdraw_amount.getText().toString().trim();
                String paa = withdraw_password.getText().toString().trim();
                if (money.isEmpty() || paa.isEmpty()) {
                    Toast.makeText(withdraw.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    confirmWithdraw();
                    report.setVisibility(View.VISIBLE);
                    dummy.setVisibility(View.VISIBLE);

                    test.setText(money);
                }
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


                                mybalance.setText(my_balance);




                            }
//                            adapter = new SavingAdapter(SavingsActivity.this, mData);
//                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
//                            loading.dismiss();
//                            login.setVisibility(View.VISIBLE);
                            Toast.makeText(withdraw.this, "failed to save " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//
                        Toast.makeText(withdraw.this, "Saving continues to  fail" + error.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void confirmWithdraw() {
        //amount dedicated
        final String ww = this.withdraw_amount.getText().toString();

//test.setText(ww);
        //get my balance and deduct the amount entered
        String mbalance = mybalance.getText().toString();

        // change amount to int
        int balance = Integer.parseInt(mbalance);

        //get amount entered
        String eamount = withdraw_amount.getText().toString().trim();

        // change amount to int
        int amountEntered = Integer.parseInt(eamount);

        //check if the amount entered is greater or equal to mybalance
        if (amountEntered >= balance) {
            Toast.makeText(this, "you can not withdraw the same amount or more than your balance ", Toast.LENGTH_LONG).show();
        } else {
            //deducting the money entered from the actual balance
            int withdrawbalance = balance - amountEntered;

            //change the above result to string
            final String results = String.valueOf(withdrawbalance);

            //testing dummy
            dummy.setText(results);
            final String dumm = dummy.getText().toString();

            //now send this sh****t to the database

            StringRequest stringRequest = new StringRequest(Request.Method.POST, WITHDRAW_URL,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                Log.i("tagconvertstr","["+response+"]"); /*debuging tool*/
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                if (success.equals("1")) {

                                    Toast.makeText(getApplicationContext(), " Successfull", Toast.LENGTH_SHORT).show();

                                }

                                else if (success.equals("2")) {

                                    Toast.makeText(getApplicationContext(), "Either Email address or contact or fullnames already taken, check and try again", Toast.LENGTH_SHORT).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(withdraw.this, "failed to withdraw " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//
                            Toast.makeText(withdraw.this, "withdraw continues to  fail " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("contact", contact);
                    params.put("balance", dumm);
                    params.put("amount",ww);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }



}