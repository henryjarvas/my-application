package com.example.menue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import static android.provider.Telephony.Carriers.PASSWORD;

public class SessionManager {
    public static final String EMAIL = "EMAIL";
    public static final String CONTACT = "CONTACT";
    public static final String FULLNAME = "FULLNAME";
    public static final String ID = "ID";



    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public SharedPreferences.Editor editor;
    public Context context;
    SharedPreferences sharedPreferences;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String email, String contact, String fullname, String id) {
        editor.putBoolean(LOGIN, true);
        editor.putString(EMAIL, email);
        editor.putString(CONTACT, contact);
        editor.putString(FULLNAME, fullname);
        editor.putString(ID, id);
        editor.apply();

    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLogin()) {
            Intent i = new Intent(context, login.class);
            context.startActivity(i);
            ((HomeActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(CONTACT, sharedPreferences.getString(CONTACT, null));
        user.put(FULLNAME, sharedPreferences.getString(FULLNAME, null));
        user.put(ID, sharedPreferences.getString(ID, null));

        return user;
    }

}
