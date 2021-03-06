package rs.stefandjokic.mokranjatzz365bet.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    public static final String FINISH_ALERT = "finish_alert";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeBanner();
        showBanner();

        if(userLoggedIn()){
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }

        this.registerReceiver(this.finishAlert, new IntentFilter(FINISH_ALERT));
    }

    private boolean userLoggedIn() {
        return SharedPreferencesManager.getInstance(this.getApplicationContext()).isLoggedIn();
    }

    private void initializeBanner() {
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
    }

    private void showBanner() {
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void loginActivity(View view) {
        startActivity(new Intent(this, SignInActivity.class));
    }

    public void registerActivity(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void launchMokranjacInfo(View view) {
        String url = "https://sr.wikipedia.org/sr-el/%D0%A1%D1%82%D0%B5%D0%B2%D0%B0%D0%BD_%D0%A1%D1%82%D0%BE%D1%98%D0%B0%D0%BD%D0%BE%D0%B2%D0%B8%D1%9B_%D0%9C%D0%BE%D0%BA%D1%80%D0%B0%D1%9A%D0%B0%D1%86";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    BroadcastReceiver finishAlert = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MainActivity.this.finish();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(finishAlert);
    }

}