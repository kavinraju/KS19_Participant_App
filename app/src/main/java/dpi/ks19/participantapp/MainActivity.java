package dpi.ks19.participantapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.CallbackInterface.GenerateOTPInterface;

public class MainActivity extends AppCompatActivity implements GenerateOTPInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ApiHelper.getInstance(MainActivity.this).generateOTP("sibinehru99@gmail.com",MainActivity.this);
                ApiHelper.getInstance(MainActivity.this).verifyOTP("4727",MainActivity.this);
                //startActivity(new Intent(MainActivity.this,ClusterCardsActivity.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void isEmailVerified(boolean isVerified) {
        if(isVerified){
            Log.d("EMAIL","VALID");
        }else{
            Log.d("EMAIL","INVALID");
        }
    }

    @Override
    public void isOTPVerified(boolean isVerified) {
        if(isVerified){
            Log.d("OTP","VALID");
        }else{
            Log.d("OTP","INVALID");
        }
    }
}
