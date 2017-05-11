package rs.stefandjokic.mokranjatzz365bet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import rs.stefandjokic.mokranjatzz365bet.R;
import rs.stefandjokic.mokranjatzz365bet.helper.SharedPreferencesManager;
import rs.stefandjokic.mokranjatzz365bet.models.User;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView username, email, credit;
    User currentUser;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initializeNavigationView();

        initializeCurrentUser();
        initializeNavTextViews();
        setNavTextViews();

        Toast.makeText(this, "Ulogovani ste kao " + getUsername() + ".", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //Go to početna
        } else if (id == R.id.nav_tiketi) {

        } else if (id == R.id.nav_sportovi) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_logout) {

            SharedPreferencesManager.getInstance(this).logOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initializeCurrentUser() {
        currentUser = SharedPreferencesManager.getInstance(this).getUser();
    }

    private void initializeNavTextViews() {
        username = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_username);
        email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_email);
        credit = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_credit);
    }

    private void setNavTextViews(){
        username.setText(getUsername());
        email.setText(getUserEmail());
        credit.setText(String.format("%s: %.2f", this.getResources().getString(R.string.current_cash), getUserCredit()));
    }

    public float getUserCredit(){
        return currentUser.getCredit();
    }

    public String getUserEmail(){
        return currentUser.getEmail();
    }

    public String getUsername(){
        return currentUser.getUsername();
    }
}
