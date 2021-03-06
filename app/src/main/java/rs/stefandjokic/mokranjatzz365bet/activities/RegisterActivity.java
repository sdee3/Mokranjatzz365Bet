package rs.stefandjokic.mokranjatzz365bet.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.BroadcastReceiver;

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
import rs.stefandjokic.mokranjatzz365bet.models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextEmail, editTextFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inicijalizacija banner-a !!kojeg nema!!
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
        /*TODO: Provera karaktera, dužine stringova, jačine lozinke, mejla*/

        //Progress dialog:
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registracija...");
        progressDialog.show();

        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String full_name = editTextFullName.getText().toString();

        //Retrofit Object
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL.API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        //Defining Retrofit API service
        APIService service = retrofit.create(APIService.class);

        //New User object:
        User user = new User(username, password, email, full_name);

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

                //Ako nema greške, ide se na Home Page!
                if(!response.body().getError()){

                    finish();
                    SharedPreferencesManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                    sendBroadcast(new Intent(MainActivity.FINISH_ALERT));

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                /* TODO: Ako tekst sadržzi "Unable to connect", prevesti na naš jezik :D */
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
