package rs.stefandjokic.mokranjatzz365bet.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        }
    }

    public void loginActivity(View view) {

        startActivity(new Intent(this, SignInActivity.class));

    }

    public void registerActivity(View view) {

        startActivity(new Intent(this, RegisterActivity.class));

    }

    public void launchMokranjacInfo(View view) {
        //Open Wiki page: https://sr.wikipedia.org/sr-el/%D0%A1%D1%82%D0%B5%D0%B2%D0%B0%D0%BD_%D0%A1%D1%82%D0%BE%D1%98%D0%B0%D0%BD%D0%BE%D0%B2%D0%B8%D1%9B_%D0%9C%D0%BE%D0%BA%D1%80%D0%B0%D1%9A%D0%B0%D1%86

        String url = "https://sr.wikipedia.org/sr-el/%D0%A1%D1%82%D0%B5%D0%B2%D0%B0%D0%BD_%D0%A1%D1%82%D0%BE%D1%98%D0%B0%D0%BD%D0%BE%D0%B2%D0%B8%D1%9B_%D0%9C%D0%BE%D0%BA%D1%80%D0%B0%D1%9A%D0%B0%D1%86";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public Context getContext(){
        return getApplicationContext();
    }

}