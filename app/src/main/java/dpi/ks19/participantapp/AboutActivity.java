package dpi.ks19.participantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import static dpi.ks19.participantapp.R.id.about_uphar;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ImageButton sastra = findViewById(R.id.about_sastra);
        ImageButton ks = findViewById(R.id.about_kuruksastra);
        ImageButton uphar = findViewById(about_uphar);
        ImageButton us = findViewById(R.id.about_us);


        ks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AboutKS.class);
                i.putExtra("heading", "KURUKSASTRA");
//                i.putExtra("content",getText(R.string.about_ks));
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(this, (View)ivProfile, "profile");
                getBaseContext().startActivity(i);

            }
        });
        sastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AboutKS.class);
                i.putExtra("heading", "SASTRA");
//                i.putExtra("content",getText(R.string.about_sastra));
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(this, v, "profile");
                getBaseContext().startActivity(i);
            }
        });
        uphar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AboutKS.class);
                i.putExtra("heading", "UPHAAR");
//                i.putExtra("content",getText(R.string.about_ks));
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(this, v, "profile");
                getBaseContext().startActivity(i);

            }
        });
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AboutUs.class);
//                i.putExtra("content",getText(R.string.about_ks));
                final View androidRobotView = findViewById(R.id.about_us);
                getBaseContext().startActivity(i);
            }
        });
    }
}