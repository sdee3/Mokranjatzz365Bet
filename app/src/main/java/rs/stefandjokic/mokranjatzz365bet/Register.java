package rs.stefandjokic.mokranjatzz365bet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

/*        //Inicijalizacija banner-a
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        //Banner-e, pojavi se!
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
*/
    }

    public void usageRights(View view) {

        startActivity(new Intent(this, UsageRights.class));

    }
}
