package com.example.menue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class saving_frequency extends AppCompatActivity {

    Button done;
    ImageView image;
    Switch daily, weekly, monthly;
    CheckBox mtn, airtell;
    TextView boxname, boxname2, freq,opSelected,amouuuttt;
    EditText eAmmount,confrimPin;
    SessionManager sessionManager;

    String frequency,peroidSelected,payment_option,optionsSelected,fullname,contact,amoutEntered;
 private static final String URL_SIGNUP= "http://192.168.1.178:8080/mosaver/saving.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_frequency);

        daily = findViewById(R.id.daily);
        weekly = findViewById(R.id.weekly);
        monthly = findViewById(R.id.monthly);
        boxname = findViewById(R.id.boxname);
        boxname2 = findViewById(R.id.boxname2);
        freq = findViewById(R.id.freq);
        mtn = findViewById(R.id.mtn);
        airtell = findViewById(R.id.airtel);
        eAmmount = findViewById(R.id.setAmount);
        confrimPin = findViewById(R.id.confirm_pin);
        opSelected = findViewById(R.id.opSelected);
        amouuuttt = findViewById(R.id.amountenered);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        fullname = user.get(SessionManager.FULLNAME);
        contact = user.get(SessionManager.CONTACT);


        //options selected
        Intent intentsss = getIntent();
        String opSelect = intentsss.getStringExtra("optionSelected");

        opSelected.setText(opSelect);
        optionsSelected = opSelected.getText().toString();

        //amount entered
        Intent aE = getIntent();
        String amontt = aE.getStringExtra("balance");

        amouuuttt.setText(opSelect);
        amoutEntered = amouuuttt.getText().toString();

        //peroid selected
        Intent intent = getIntent();
        String bname = intent.getStringExtra("boxname");

        boxname.setText(bname);
       peroidSelected = boxname.getText().toString();

        daily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weekly.setEnabled(false);
                    monthly.setEnabled(false);
                    frequency = daily.getText().toString();
                    freq.setText(frequency);
                    done.setEnabled(true);//ctivate the button on selecting this item

                } else if (!isChecked) {
                    weekly.setEnabled(true);
                    monthly.setEnabled(true);
                    done.setEnabled(false);//if not selected, then dnt activate the btn

                }
            }
        });


        Intent box2 = getIntent();
        String bname2 = box2.getStringExtra("boxname2");

        boxname2.setText(bname2);
        weekly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    daily.setEnabled(false);
                    monthly.setEnabled(false);
                    frequency = weekly.getText().toString();
                    freq.setText(frequency);
                    done.setEnabled(true);//ctivate the button on selecting this item


                } else if (!isChecked) {
                    daily.setEnabled(true);
                    monthly.setEnabled(true);
                    done.setEnabled(false);//if not selected, then dnt activate the btn


                }
            }
        });

        monthly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    daily.setEnabled(false);
                    weekly.setEnabled(false);
                    frequency = monthly.getText().toString();
                    freq.setText(frequency);
                    done.setEnabled(true);//ctivate the button on selecting this item


                } else if (!isChecked) {
                    daily.setEnabled(true);
                    weekly.setEnabled(true);
                    done.setEnabled(false);//if not selected, then dnt activate the btn


                }
            }
        });

        mtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    airtell.setEnabled(false);
                    payment_option = mtn.getText().toString();
                    freq.setText(payment_option);
                    done.setEnabled(true);//ctivate the button on selecting this item

                } else if (!isChecked) {
                    airtell.setEnabled(true);
                    done.setEnabled(false);//if not selected, then dnt activate the btn


                }
            }
        });

        airtell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mtn.setEnabled(false);
                    payment_option = airtell.getText().toString();
                    freq.setText(payment_option);
                    done.setEnabled(true);//ctivate the button on selecting this item

                } else if (!isChecked) {
                    mtn.setEnabled(true);
                    done.setEnabled(false);//if not selected, then dnt activate the btn

                }
            }
        });


        image = findViewById(R.id.back);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(saving_frequency.this, saving_time.class);
                startActivity(intent);
            }
        });


        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Saving();
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


        //    get the string frequency and string boxSelected and other values to send to the database
    }


    private void Saving(){


//       final String freq = this.frequency.getText().toString() ;

        final String amount= this.eAmmount.getText().toString().trim();
        final String pin= this.confrimPin.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SIGNUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr","["+response+"]"); /*debuging tool*/
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {

                                Toast.makeText(getApplicationContext(), "addedd Successfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(saving_frequency.this, HomeActivity.class);
                                startActivity(intent);
                            }

                            else if (success.equals("2")) {

                                Toast.makeText(getApplicationContext(), "adddddeeedddddd not , check and try again" , Toast.LENGTH_SHORT).show();
//                                loading.setVisibility(View.VISIBLE);
//                                signup_btn.setVisibility(View.VISIBLE);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), " errorsssssss" + e.toString(), Toast.LENGTH_SHORT).show();
//                            loading.setVisibility(View.VISIBLE);
//                            signup_btn.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), " error" + error.toString(), Toast.LENGTH_SHORT).show();
//
                    }
                }
        )
        {
            protected Map<String, String> getParams() {
                Map<String, String>params= new HashMap<>();
                params.put("fullname",fullname);
                params.put("contact",contact);
                params.put("account",optionsSelected);
                params.put("period",peroidSelected);
                params.put("frequency",frequency);
                params.put("balance",amount);
                params.put("payment_option",payment_option);

                return  params;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}
