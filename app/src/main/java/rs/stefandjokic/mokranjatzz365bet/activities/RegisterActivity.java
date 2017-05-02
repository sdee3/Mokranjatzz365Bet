package rs.stefandjokic.mokranjatzz365bet.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.stefandjokic.mokranjatzz365bet.R;
import rs.stefandjokic.mokranjatzz365bet.activities.api.APIService;
import rs.stefandjokic.mokranjatzz365bet.activities.api.API_URL;
import rs.stefandjokic.mokranjatzz365bet.activities.models.Result;
import rs.stefandjokic.mokranjatzz365bet.activities.models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextEmail, editTextFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inicijalizacija banner-a
        /*

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        //Banner-e, pojavi se!
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        */

        editTextUsername = (EditText) findViewById(R.id.regUsername);
        editTextPassword = (EditText) findViewById(R.id.regPassword);
        editTextEmail = (EditText) findViewById(R.id.regEmail);
        editTextFullName = (EditText) findViewById(R.id.regFullName);

    }

    public void usageRights(View view) {

        startActivity(new Intent(this, UsageRightsActivity.class));

    }

    public void userSignUp(View view){

        //Progress dialog:
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registracija...");
        progressDialog.show();

        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String full_name = editTextFullName.getText().toString().trim();

        //Retrofit Object
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);


        //New User object:
        User user = new User(username, email, password, full_name);

        //Defining the Call
        Call<Result> call = service.createUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getFullname());

        //Calling the API
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //Hiding progress dialog
                progressDialog.dismiss();

                //Displaying the message from the response as toast
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
