package dpi.ks19.participantapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import dpi.ks19.participantapp.Activities.ClusterCardsActivity;
import dpi.ks19.participantapp.Activities.ProfileActivity;

public class MainScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    boolean guestUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            if(intent.getExtras().getBoolean(getString(R.string.login_register_action))){
                //user through login
                guestUser = false;
                sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.login_or_not), true);
                editor.apply();
                editor.commit();
            }else{
                //guest user
                guestUser = true;
            }
        }

    }

    public void eventsclicked(View v){
        startActivity(new Intent(this, ClusterCardsActivity.class));
    }

    public void profileclicked(View v){
        if(guestUser) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You are a guest user . Please Login to view profile",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    public void proshowsclicked(View view) {
        startActivity(new Intent(this, proshows.class));
    }

    public void aboutusclicked(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void sponsorsclicked(View view) {

        String url = "http://www.kuruksastra.in/sponsors.php/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
