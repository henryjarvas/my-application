package com.example.menue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.menue.Models.UsersModel;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

  public class login extends AppCompatActivity {

    TextView heading;
    EditText email;
    TextInputLayout password;
    Button login, register;
    SessionManager sessionManager;
/*
    private ProgressBar wait;
    private String email_val, password_val ;*/
    private static String URL_LOGIN= "http://192.168.1.178:8080/mosaver/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        heading = findViewById(R.id.textView);
        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        login= findViewById(R.id.button);
        register= findViewById(R.id.reg);

        sessionManager = new SessionManager(this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,sign_up.class);
                startActivity(intent);
            }
        });

     login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String user = email.getText().toString().trim();
             String pass = password.getEditText().getText().toString().trim();

             if(!user.isEmpty() || !pass.isEmpty()) {
                 Login(user,pass);
             }

             else{
                 email.setError("Enter a valid Email");
                 password.setError("Enter  Passord");
             }
         }
     });


    }

    private void Login(final String email, final String password){

        final ProgressDialog loading = new ProgressDialog(login.this);
        loading.setMessage("loging in please wait...");
        loading.show();
        login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("tagconvertstr","["+response+"]");
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (success.equals("1")) {
                        Toast.makeText(login.this, "login success", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id").trim();
                            String email = object.getString("email").trim();
                            String contact = object.getString("contact").trim();
                            String fullname = object.getString("fullname").trim();
//                            String name = object.getString("name").trim();

                            sessionManager.createSession(email,contact,fullname,id);

                            Intent intent = new Intent(login.this, HomeActivity.class);
                            intent.putExtra("fullname",fullname);
                            startActivity(intent);
                            loading.dismiss();
                            login.setVisibility(View.GONE);

                        }
                    } else if (success.equals("0")) {
                        loading.dismiss();
                        login.setVisibility(View.VISIBLE);
                        Toast.makeText(login.this, "login error.. account not found", Toast.LENGTH_SHORT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loading.dismiss();
                    login.setVisibility(View.VISIBLE);
                    Toast.makeText(login.this, "failed to login "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        login.setVisibility(View.VISIBLE);
                        Toast.makeText(login.this, "login error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String,String> getParams()throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
