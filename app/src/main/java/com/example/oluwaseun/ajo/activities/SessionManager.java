package com.example.oluwaseun.ajo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.oluwaseun.ajo.activities.english.LoginActivity;

import java.util.HashMap;

/**
 * Created by Damola on 14/12/2017.
 */

public class SessionManager {
    //Bundle credentials = getIntent().getExtras();

    //Shared Preference mode
    private int Private_Mode =0;
    //SharePref filename;
    private static final String PREF_NAME = "ajoo";
    //All Shared Preference keys
    private static final String IS_LOGIN = "isLoggedIn";

    //User token / name
    private static final String TOKEN =  "_token";

    private static final String NAME = "name";
    //SharedPreference Editore
    private SharedPreferences.Editor editor;
    //Activity Context
    private Context _context;

    //SharedPreferences
    private SharedPreferences preferences;
    public SessionManager(Context context){
        this._context = context;

        preferences = _context.getSharedPreferences(PREF_NAME,Private_Mode);
        editor = preferences.edit();

    }

    //create a login session
    public void createLoginSession(String token, String name){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(TOKEN,token);
        editor.putString(NAME,name);
        editor.commit();
    }


    //get user session out
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> userLogin = new HashMap<String, String>();
        //user token
        userLogin.put(TOKEN,preferences.getString(TOKEN,null));
        //get other param you saved eaerlier
        userLogin.put(NAME, preferences.getString(NAME,null));
        //return user
        return userLogin;
    }
    //
    public void checkLogin(){
        if (!this.isLoggedIn()){
            //goto login screen
            Intent i =  new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void logoutUser(){
        //clear all session and data from pref
        editor.clear();
        editor.commit();

        //after log out rediredct to login or home page
        Intent i = new Intent(_context,WelcomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
        //_context.startActivities(i );
    }

    private boolean isLoggedIn(){
        return this.preferences.getBoolean(IS_LOGIN,false);
    }


}
