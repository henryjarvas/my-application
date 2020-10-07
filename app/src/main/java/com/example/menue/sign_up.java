package com.example.menue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {

    TextView heading;
    EditText full_name, email, contact, address, occupation, password, confirm_password;
    Button signup_btn, already;
    private ProgressBar loading;
    private String full_name_val, email_val, contact_val, address_val, occupation_val, password_val ,confirm_password_val;
    private static final String URL_SIGNUP= "http://192.168.1.178:8080/mosaver/signup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        loading= findViewById(R.id.loading);
        heading = findViewById(R.id.textView22);
        full_name = findViewById(R.id.editText5);
        email = findViewById(R.id.editText6);
        contact = findViewById(R.id.contact);
        address =findViewById(R.id.editText7);
        occupation = findViewById(R.id.editText8);
        password = findViewById(R.id.editText9);
        confirm_password = findViewById(R.id.editText10);
        signup_btn = findViewById(R.id.button14);
        already= findViewById(R.id.button17);




        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(sign_up.this,login.class);
                startActivity(intent);
            }
        });


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(sign_up.this,HomeActivity.class);
                startActivity(intent);
            }
        });


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                full_name_val = full_name.getText().toString().trim().toLowerCase();
                email_val = email.getText().toString().trim().toLowerCase();
                contact_val = contact.getText().toString().trim().toLowerCase();
                address_val = address.getText().toString().trim().toLowerCase();
                occupation_val = occupation.getText().toString().trim().toLowerCase();
                password_val =password.getText().toString().trim().toLowerCase();
                confirm_password_val = confirm_password.getText().toString().trim().toLowerCase();

                if (!full_name_val.isEmpty() || !email_val.isEmpty() || !contact_val.isEmpty() || !address_val.isEmpty()|| !occupation_val.isEmpty()||
                        !password_val.isEmpty() || !confirm_password_val.isEmpty() ){

                   /* checking if password and confirm password aint equal*/
                    if (password_val.equals(confirm_password_val)){

                        Register();
                        Intent intent =new Intent(sign_up.this,login.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(sign_up.this, "password feilds do not match", Toast.LENGTH_SHORT).show();
                    }


                    full_name.setText("");
                    email.setText("");
                    contact.setText("");
                    address.setText("");
                   occupation.setText("");
                    password.setText("");
                    confirm_password.setText("");
                }

                else{
                    full_name.setError("Enter full_name");
                    email.setError("Enter email");
                    contact.setError("Enter contact");
                    address.setError("Enter address");
                    occupation.setError("occupation");
                    password.setError("Enter_password");
                    confirm_password.setError("confirm_password");

                }
            }
        });
    }


    private void Register(){

        loading.setVisibility(View.VISIBLE);
        signup_btn.setVisibility(View.GONE);
        final String full_name= this.full_name.getText().toString().trim();
        final String email= this.email.getText().toString().trim();
        final String contact= this.contact.getText().toString().trim();
        final String address= this.address.getText().toString().trim();
        final String occupation= this.occupation.getText().toString().trim();
        final String password= this.password.getText().toString().trim();
        final String confirm_password= this.confirm_password.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SIGNUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr","["+response+"]"); /*debuging tool*/
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {

                            Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            signup_btn.setVisibility(View.VISIBLE);
                            }

                           else if (success.equals("2")) {

                                Toast.makeText(getApplicationContext(), "Either Email address or contact or fullnames already taken, check and try again", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.VISIBLE);
                                signup_btn.setVisibility(View.VISIBLE);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Registration error" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.VISIBLE);
                            signup_btn.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Registration error" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.VISIBLE);
                        signup_btn.setVisibility(View.VISIBLE);

                    }
                }
        )
        {
            protected Map<String, String> getParams() {
               Map<String, String>params= new HashMap<>();
               params.put("fullname",full_name);
               params.put("email",email);
                params.put("contact",contact);
                params.put("address",address);
                params.put("occupation",occupation);
                params.put("password",password);
                params.put("confirmpassword",confirm_password);
                return  params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
