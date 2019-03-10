package dpi.ks19.participantapp.Activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster_cards);

        clusterCard = findViewById(R.id.clutser_swipe_deck);

        clusterList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cluster_list)));
        ClusterCardAdapter adapter = new ClusterCardAdapter(clusterList,this);
        clusterCard.setAdapter(adapter);
    }
}
