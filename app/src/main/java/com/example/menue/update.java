package com.example.menue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class update extends AppCompatActivity {

    private static final int SELECT_SINGLE_PICTURE = 101;

    public static final String IMAGE_TYPE = "image/*";

    private ImageView selectedImagePreview;

    ImageView photo;

    private static final String TAG =update.class.getSimpleName() ; //getting the information
    private TextView name, email,mynumber;
    SessionManager sessionManager;
    String getId,number,names,emails;

    private static String URL_EDIT = "http://192.168.1.178:8080/mosaver/edit_detail.php";
    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        photo= findViewById(R.id.image_preview);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mynumber = findViewById(R.id.mynumber);


        HashMap<String,String> user= sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        number = user.get(SessionManager.CONTACT);
        names = user.get(SessionManager.FULLNAME);
        emails = user.get(SessionManager.EMAIL);


name.setText(names);
email.setText(emails);



        // no need to cast to button view here since we can add a listener to any view, this
        // is the single image selection
        findViewById(R.id.image_preview).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.select_picture)), SELECT_SINGLE_PICTURE);
            }
        });

        selectedImagePreview = (ImageView)findViewById(R.id.image_preview);

    }


    @Override
    protected void onResume() {
        super.onResume();
//        getUserDetail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menue_action,menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_edit:
                name.setFocusableInTouchMode(true);
                email.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(name,InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

SaveEditDetail();

                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                name.setFocusableInTouchMode(false);
                email.setFocusableInTouchMode(false);
                name.setFocusable(true);
                email.setFocusable(true);



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void SaveEditDetail() {

        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String id = getId;
        final String contact = number;


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr","["+response+"]"); /*debuging tool*/
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(update.this, "Success!",Toast.LENGTH_SHORT).show();
                              sessionManager.createSession(email,contact,name,id);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(update.this, "Error!"+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(update.this, "Error!"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("fullname", name);
                params.put("email", email);
                params.put("contact", contact);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {

        // just some safety built in
        if( uri == null ) {
            // perform some logging or show user feedback
            Toast.makeText(getApplicationContext(), "erono", Toast.LENGTH_LONG).show();
            Log.d(img_change.class.getSimpleName(), "Failed to parse image path from image URI " + uri);
            return null;
        }

        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here, thanks to the answer from @mad indicating this is needed for
        // working code based on images selected using other file managers
        return uri.getPath();
    }

   /* private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);
    }*/
   /* public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);*/
        /*if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTURE) {

                Uri selectedImageUri = data.getData();
                try {
                    selectedImagePreview.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                } catch (IOException e) {
                    Log.e(img_change.class.getSimpleName(), "Failed to load image", e);
                }
                // original code
//                String selectedImagePath = getPath(selectedImageUri);
//                selectedImagePreview.setImageURI(selectedImageUri);
            }  {
                //And in the Result handling check for that parameter:
                if (Intent.ACTION_SEND_MULTIPLE.equals(data.getAction())
                        && data.hasExtra(Intent.EXTRA_STREAM)) {
                    // retrieve a collection of selected images
                    ArrayList<Parcelable> list = data.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                    // iterate over these images
                    if (list != null) {
                        for (Parcelable parcel : list) {
                            Uri uri = (Uri) parcel;
                            // handle the images one by one here
                        }
                    }

                    // for now just show the last picture
                    if (!list.isEmpty()) {
                        Uri imageUri = (Uri) list.get(list.size() - 1);

                        try {
                            selectedImagePreview.setImageBitmap(new UserPicture(imageUri, getContentResolver()).getBitmap());
                        } catch (IOException e) {
                            Log.e(img_change.class.getSimpleName(), "Failed to load image", e);
                        }
                        // original code
//                        String selectedImagePath = getPath(imageUri);
//                        selectedImagePreview.setImageURI(imageUri);
//                        displayPicture(selectedImagePath, selectedImagePreview);
                    }
                }
            }
        } else {
            // report failure
            Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG).show();
            Log.d(img_change.class.getSimpleName(), "Failed to get intent data, result code is " + resultCode);*/
        }

        

