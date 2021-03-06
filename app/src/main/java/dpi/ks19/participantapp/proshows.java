package dpi.ks19.participantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class proshows extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proshows);

        ImageView musicteam = findViewById(R.id.musicteam);
        Picasso.with(this).load(R.drawable.smt_final).into(musicteam);


        ImageView veena = findViewById(R.id.veenasrivani);
        Picasso.with(this).load(R.drawable.veenasrivani).into(veena);


        ImageView crazy = findViewById(R.id.crazymohan);
        Picasso.with(this).load(R.drawable.crazymohan_final).into(crazy);


        ImageView choreo = findViewById(R.id.choreonite);
        Picasso.with(this).load(R.drawable.choreo_final).into(choreo);


        ImageView pattimandram = findViewById(R.id.pattimandram);
        Picasso.with(this).load(R.drawable.p_final).into(pattimandram);

        ImageView navin = findViewById(R.id.navin);
        Picasso.with(this).load(R.drawable.navin_final).into(navin);


    }
}
