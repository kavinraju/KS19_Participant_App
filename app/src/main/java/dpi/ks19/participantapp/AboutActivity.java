package dpi.ks19.participantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import dpi.ks19.participantapp.Activities.ClusterCardsActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ImageButton sastra = findViewById(R.id.about_sastra);
        ImageButton ks = findViewById(R.id.about_ks);
        ImageButton uphar = findViewById(R.id.about_uphar);
        ImageButton us = findViewById(R.id.about_us);

        ;
        ks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AboutKS.class);
                i.putExtra("heading", "KURUKSASTRA");
//                i.putExtra("content",getText(R.string.about_ks));
                getBaseContext().startActivity(i);

            }
        });
        sastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AboutKS.class);
                i.putExtra("heading", "SASTRA");
//                i.putExtra("content",getText(R.string.about_sastra));
                getBaseContext().startActivity(i);
            }
        });
        uphar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AboutKS.class);
                i.putExtra("heading", "UPHAAR");
//                i.putExtra("content",getText(R.string.about_ks));
                getBaseContext().startActivity(i);

            }
        });
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AboutUs.class);
//                i.putExtra("content",getText(R.string.about_ks));
                getBaseContext().startActivity(i);

            }
        });
    }
}
