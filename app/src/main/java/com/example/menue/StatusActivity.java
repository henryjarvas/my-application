package com.example.menue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.menue.Adapters.NotificationsAdapter;
import com.example.menue.Adapters.StatusAdapter;
import com.example.menue.Models.NotificationsModel;
import com.example.menue.Models.StatusModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusActivity extends AppCompatActivity {

    private static final String STATUS_LIST_URL= "http://192.168.1.178:8080/mosaver/get_status.php";
    RecyclerView recyclerView;
    List<StatusModel> mData;
    StatusAdapter adapter;
    TextView error_message;
    BottomNavigationView bottomNavigationView;
    SessionManager sessionManager;
    String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.status);

        Toolbar toolbar = findViewById(R.id.toolbar);
        /*setSupportActionBar(toolbar);*/
        recyclerView = findViewById(R.id.recyclerview);
        mData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StatusAdapter(this,mData);
        recyclerView.setAdapter(adapter);

        LoadNotificationsList();

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
//        fullname = user.get(SessionManager.FULLNAME);
        contact = user.get(SessionManager.CONTACT);

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

    private void LoadNotificationsList(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, STATUS_LIST_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONArray users = new JSONArray(response);
                            for (int i = 0; i < users.length(); i++) {
                                JSONObject object = users.getJSONObject(i);

                                String id = object.getString("id");
                                String fullname = object.getString("fullname");
                                String account = object.getString("account");
                                String status = object.getString("status");
                                String amount = object.getString("amount");
                                String saving_date = object.getString("saving_date");
                                String balance = object.getString("balance");


                                StatusModel statusModel = new StatusModel(id, fullname,account, status, amount, saving_date, balance);

                                mData.add(statusModel);
                               /* error_message.setVisibility(View.GONE);*/

                            }
                            adapter = new StatusAdapter(StatusActivity.this, mData);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
//                            loading.dismiss();
//                            login.setVisibility(View.VISIBLE);
                            Toast.makeText(StatusActivity.this, "failed to save........ "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        loading.dismiss();
//                        login.setVisibility(View.VISIBLE);
                        Toast.makeText(StatusActivity.this, "Saving continues to  failllllllllllll"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String,String> getParams()throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("contact",contact);
//                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}