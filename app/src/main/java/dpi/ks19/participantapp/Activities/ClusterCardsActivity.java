package dpi.ks19.participantapp.Activities;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.Arrays;

import dpi.ks19.participantapp.Adapter.ClusterCardAdapter;
import dpi.ks19.participantapp.R;

public class ClusterCardsActivity extends AppCompatActivity {

    SwipeDeck clusterCard;
    ArrayList<String>clusterList;
    ArrayList<Integer>icons;
    ArrayList<Color>backgroundColorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster_cards);

        clusterCard = findViewById(R.id.clutser_swipe_deck);

        //cluster name list
        clusterList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cluster_list)));

        //icons for clusters
        icons = new ArrayList<>();
        icons.add(R.drawable.logo_ks);
        icons.add(R.drawable.logo_ks);
        icons.add(R.drawable.logo_arts);
        icons.add(R.drawable.logo_studio);
        icons.add(R.drawable.logo_english_lits);
        icons.add(R.drawable.logo_hindi_lits);
        icons.add(R.drawable.logo_thandav);
        icons.add(R.drawable.logo_insiders);
        icons.add(R.drawable.logo_telugu_lits);
        icons.add(R.drawable.logo_sfh);
        icons.add(R.drawable.logo_tamil_sangam);
        icons.add(R.drawable.logo_smt);
        icons.add(R.drawable.logo_dpi);

        //background data
        backgroundColorList = new ArrayList<>();
        //backgroundColorList.add(getColor(R.color.))


        final ClusterCardAdapter adapter = new ClusterCardAdapter(clusterList, icons, backgroundColorList, this);
        clusterCard.setAdapter(adapter);
        clusterCard.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

            }

            @Override
            public void cardSwipedRight(int position) {

            }

            @Override
            public void cardsDepleted() {
                //to reset the card stack
                clusterCard.setAdapter(adapter);
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });
    }

}
