package rs.stefandjokic.mokranjatzz365bet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void loginActivity(View view) {

        startActivity(new Intent(this, SignIn.class));

    }

    public void registerActivity(View view) {

        startActivity(new Intent(this, Register.class));

    }
}
