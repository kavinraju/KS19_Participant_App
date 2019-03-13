package dpi.ks19.participantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dpi.ks19.participantapp.Activities.ClusterCardsActivity;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    public void eventsclicked(View v){
        startActivity(new Intent(this, ClusterCardsActivity.class));
    }
}
