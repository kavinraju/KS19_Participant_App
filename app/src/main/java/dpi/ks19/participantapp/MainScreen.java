package dpi.ks19.participantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import dpi.ks19.participantapp.Activities.ClusterCardsActivity;


public class MainScreen extends AppCompatActivity {

    boolean YourtransferredData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            YourtransferredData = extras.getBoolean("key");
            //The key argument here must match that used in the other activity
        }


    }

    public void eventsclicked(View v){
        startActivity(new Intent(this, ClusterCardsActivity.class));
    }

    public void profileclicked(View v){
        if(YourtransferredData) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You are a guest user . Please Login to view profile",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void proshowsclicked(View view) {
        startActivity(new Intent(this, proshows.class));
    }

    public void aboutusclicked(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }
}
