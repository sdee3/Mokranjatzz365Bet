package rs.stefandjokic.mokranjatzz365bet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class UsageRights extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_rights);

        TextView textView = (TextView) (findViewById(R.id.usage_rights_txt));
        textView.setMovementMethod(new ScrollingMovementMethod());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
