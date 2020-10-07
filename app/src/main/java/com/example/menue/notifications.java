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

public class notifications extends AppCompatActivity {
    ImageView arr;
    TextView notificationsview,amount;
    SessionManager sessionManager;
    String contact;
    private static final String BALANCE_LIST_URL= "http://192.168.1.178:8080/mosaver/get_notifications.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
//        fullname = user.get(SessionManager.FULLNAME);
        contact = user.get(SessionManager.CONTACT);

      notificationsview = findViewById(R.id.notificationsview);
      amount = findViewById(R.id.amount);


        arr= findViewById(R.id.back);
        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(notifications.this,my_wallet.class);
                startActivity(intent);
            }
        });


                loadBalance();





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

    private void loadBalance(){

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
                                String my_balance= object.getString("balance");

                                amount.setText(my_balance);

                            }
//                            adapter = new SavingAdapter(SavingsActivity.this, mData);
//                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
//                            loading.dismiss();
//                            login.setVisibility(View.VISIBLE);
                            Toast.makeText(notifications.this, "failed to save "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//
                        Toast.makeText(notifications.this, "Saving continues to  fail"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("contact",contact);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
