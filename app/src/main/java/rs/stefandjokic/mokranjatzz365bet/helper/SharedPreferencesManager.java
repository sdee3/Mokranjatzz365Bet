package rs.stefandjokic.mokranjatzz365bet.helper;

import android.content.Context;

public class SharedPreferencesManager {

    private SharedPreferencesManager instance;
    private Context context;

    private static final String SHARED_PREF_NAME = "mokranjatzz365betsharedprefretrofit";

    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_USER_USERNAME = "keyuserusername";
    private static final String KEY_USER_EMAIL = "keyuseremail";
    private static final String KEY_USER_FULL_NAME = "keyuserfullname";

    private SharedPreferencesManager(Context context){
        this.context = context;
    }

    public static synchronized SharedPreferencesManager getInstance(Context context){

        if(this.instance == null){

        }

    }

}