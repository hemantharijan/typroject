package com.hemantax.musica.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sabrish on 17/01/18.
 */

public class PrefManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    //Shared pref mode
    int Private_mode=0;

    // Shared preferenes file name

    private static final String pref_name="welcome_login";

    private static final String is_first_time_launch="IsFirstTimeLaunch";

    private static final String is_Logged_in="IsLoggedIn";

    private static  final String userame = "Username";


    private static  final String userid = "Userid";


    public PrefManager(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(pref_name,Private_mode);
        editor=sharedPreferences.edit();

    }
    public void setFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(is_first_time_launch,isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch(){
        return sharedPreferences.getBoolean(is_first_time_launch,true);
    }

    public void setLoggedIn(boolean islogin){
        editor.putBoolean(is_Logged_in,islogin);
        editor.commit();
    }
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(is_Logged_in,false);
    }

    public void setUserame(String Username){
        editor.putString(userame,Username);
        editor.commit();

    }
    public String getUsername(){
        return sharedPreferences.getString(userame,"");
    }

    public void setUserid(String userid){
        editor.putString(userid,userid);
    }
    public String getUserid()
    {
        return sharedPreferences.getString(userid,"");
    }
}
