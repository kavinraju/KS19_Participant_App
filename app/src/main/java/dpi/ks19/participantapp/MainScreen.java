package dpi.ks19.participantapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dpi.ks19.participantapp.Activities.ClusterCardsActivity;

public class MainScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //store flag to denote that already logged in
        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.login_or_not),true);
        editor.apply();
        editor.commit();
    }

    public void eventsclicked(View v){
        startActivity(new Intent(this, ClusterCardsActivity.class));
    }
}
