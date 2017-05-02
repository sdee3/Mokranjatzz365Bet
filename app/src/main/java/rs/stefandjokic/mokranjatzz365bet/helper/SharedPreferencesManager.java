package rs.stefandjokic.mokranjatzz365bet.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import rs.stefandjokic.mokranjatzz365bet.models.User;

public class SharedPreferencesManager {

    private static SharedPreferencesManager instance;
    private static Context context;

    private static final String SHARED_PREF_NAME = "mokranjatzz365betsharedprefretrofit";

    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_USER_USERNAME = "keyuserusername";
    private static final String KEY_USER_EMAIL = "keyuseremail";
    private static final String KEY_USER_FULL_NAME = "keyuserfullname";

    private SharedPreferencesManager(Context context){
        this.context = context;
    }

    public static synchronized SharedPreferencesManager getInstance(Context context){

        if(instance == null){
            instance = new SharedPreferencesManager(context);
        }
        return instance;

    }

    public boolean userLogin(User user){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_USERNAME, user.getUsername());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_USER_FULL_NAME, user.getFullname());

        editor.apply();
        return true;

    }

    public boolean isLoggedIn(){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return (sharedPreferences.getString(KEY_USER_EMAIL, null) != null ) || (sharedPreferences.getString(KEY_USER_USERNAME, null) != null);
    }

    public User getUser(){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getInt(KEY_USER_ID, 0),
                sharedPreferences.getString(KEY_USER_USERNAME, null),
                sharedPreferences.getString(KEY_USER_FULL_NAME, null),
                sharedPreferences.getString(KEY_USER_EMAIL, null)

        );

    }

    public boolean logOut(){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        return true;
    }

}