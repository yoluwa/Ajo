package com.example.oluwaseun.ajo.activities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Oluwaseun on 14/12/2017.
 */

public class SessionManager {

    //Shared Preference mode
    int Private_Mode =0;
    //SharePref filename;
    private static final String PREF_NAME = "ajoo";
    //All Shared Preference keys
    private static final String IS_LOGIN = "isLoggedIn";

    //User token / name
    public static final String TOEKN =  "_token";

    public static final String NAME = "name";
    //SharedPreference Editore
    public SharedPreferences.Editor editor;
    //Activity Context
    public Context _context;

    //SharedPreferences
    public SharedPreferences preferences;
    public SessionManager(Context context){
        this._context = context;

        preferences = _context.getSharedPreferences(PREF_NAME,Private_Mode);
        editor = preferences.edit();

    }

    //create a login session
    public void createLoginSession(String token, String name){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(TOEKN,token);
        editor.putString(NAME,name);
        editor.commit();
    }


    //get user session out
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> userLogin = new HashMap<String, String>();
        //user token
        userLogin.put(TOEKN,preferences.getString(TOEKN,null));
        //get other param you saved eaerlier
        userLogin.put(NAME, preferences.getString(NAME,null));
        //return user
        return userLogin;
    }
    //
    public void checkLogin(){
        if (!this.isLoggedIn()){
            //goto login screen
        }
    }

    public void logoutUser(){
        //clear all session and data from pref
        editor.clear();
        editor.commit();

        //after log out rediredct to login or home page
        //_context.startActivities(i );
    }

    public boolean isLoggedIn(){
        return this.preferences.getBoolean(IS_LOGIN,false);
    }


}
