package dpi.ks19.participantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class proshows extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proshows);

        ImageView musicteam = (ImageView) findViewById(R.id.musicteam);
        musicteam.setImageResource(R.drawable.smt_final);

        ImageView veena = (ImageView) findViewById(R.id.veenasrivani);
        veena.setImageResource(R.drawable.veenasrivani);

        ImageView crazy = (ImageView) findViewById(R.id.crazymohan);
        crazy.setImageResource(R.drawable.crazymohan_final);

        ImageView choreo = (ImageView) findViewById(R.id.choreonite);
        choreo.setImageResource(R.drawable.choreo_final);

        ImageView pattimandram = (ImageView) findViewById(R.id.pattimandram);
        pattimandram.setImageResource(R.drawable.p_final);

        ImageView navin = (ImageView) findViewById(R.id.navin);
        navin.setImageResource(R.drawable.navin_final);


    }
}
