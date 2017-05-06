package rs.stefandjokic.mokranjatzz365bet.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.stefandjokic.mokranjatzz365bet.R;
import rs.stefandjokic.mokranjatzz365bet.api.APIService;
import rs.stefandjokic.mokranjatzz365bet.api.API_URL;
import rs.stefandjokic.mokranjatzz365bet.helper.SharedPreferencesManager;
import rs.stefandjokic.mokranjatzz365bet.models.Result;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        //Inicijalizacija banner-a
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        //Banner-e, pojavi se!
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void userLogin(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Prijavljivanje na sistem...");
        progressDialog.show();

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.userLogin(email, password);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (!response.body().getError()) {

                    finish();
                    SharedPreferencesManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                    sendBroadcast(new Intent(MainActivity.FINISH_ALERT));

                } else {
                    Toast.makeText(getApplicationContext(), "Neispravni podaci.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
