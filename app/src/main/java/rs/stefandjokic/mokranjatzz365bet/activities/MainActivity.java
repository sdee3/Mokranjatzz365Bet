package rs.stefandjokic.mokranjatzz365bet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;

import rs.stefandjokic.mokranjatzz365bet.R;
import rs.stefandjokic.mokranjatzz365bet.helper.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicijalizacija banner-a
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        //Banner-e, pojavi se!
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Ako je User već ulogovan:
        if(SharedPreferencesManager.getInstance(this.getApplicationContext()).isLoggedIn()){
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }else {
            Toast.makeText(getApplicationContext(), String.valueOf(SharedPreferencesManager.getInstance(getApplicationContext()).getUser()), Toast.LENGTH_LONG).show();
        }
    }


    public void loginActivity(View view) {

        startActivity(new Intent(this, SignInActivity.class));

    }

    public void registerActivity(View view) {

        startActivity(new Intent(this, RegisterActivity.class));

    }
}
