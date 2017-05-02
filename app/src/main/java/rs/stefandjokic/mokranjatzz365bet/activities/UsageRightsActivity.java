package rs.stefandjokic.mokranjatzz365bet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rs.stefandjokic.mokranjatzz365bet.R;

public class UsageRightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_rights);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
