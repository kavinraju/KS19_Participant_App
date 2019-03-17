package dpi.ks19.participantapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import dpi.ks19.participantapp.Adapter.EventListAdapter;
import dpi.ks19.participantapp.R;

public class EventListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String clusterNames[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        clusterNames = getResources().getStringArray(R.array.cluster_list);
        recyclerView = findViewById(R.id.event_list);

        Intent intentFromCards = getIntent();
        if(intentFromCards != null && intentFromCards.getAction()!=null){
            String clusterName = intentFromCards.getAction();

            EventListAdapter adapter = new EventListAdapter(chooseEventList(clusterName),
                    chooseEventDesc(clusterName),
                    chooseEventRules(clusterName));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

    }

    private ArrayList<String> chooseEventList(String cluster){

        if(cluster.equals(clusterNames[0])){
            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.kuruk_event_list)));

        }else if(cluster.equals(clusterNames[1])){//for arts cluster

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arts_list)));
        }else if(cluster.equals(clusterNames[2])){//for dramatics

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dramatic_list)));
        }else if(cluster.equals(clusterNames[3])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.english_lits_list)));
        }else if(cluster.equals(clusterNames[4])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hindi_lits_list)));
        }else if(cluster.equals(clusterNames[5])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.eastern_dance_list)));
        }else if(cluster.equals(clusterNames[6])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.western_dance_list)));
        }else if(cluster.equals(clusterNames[7])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.telugu_lits_list)));
        }else if(cluster.equals(clusterNames[8])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.fotohub_list)));
        }else if(cluster.equals(clusterNames[9])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.tamil_sangam_list)));
        }else if(cluster.equals(clusterNames[10])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.music_team_list)));
        }else{

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dpi_list)));
        }

    }

    private ArrayList<String> chooseEventDesc(String cluster){

        if(cluster.equals(clusterNames[0])){
            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.kuruk_desc_list)));

        }else if(cluster.equals(clusterNames[1])){//for arts cluster

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arts_desc_list)));
        }else if(cluster.equals(clusterNames[2])){//for dramatics

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dramatics_desc_list)));
        }else if(cluster.equals(clusterNames[3])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.english_lits_desc_list)));
        }else if(cluster.equals(clusterNames[4])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hindi_lits_desc_list)));
        }else if(cluster.equals(clusterNames[5])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.eastern_dance_desc_list)));
        }else if(cluster.equals(clusterNames[6])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.western_dance_desc_list)));
        }else if(cluster.equals(clusterNames[7])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.telugu_lits_desc_list)));
        }else if(cluster.equals(clusterNames[8])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.fotohub_desc_list)));
        }else if(cluster.equals(clusterNames[9])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.tamil_sangam_desc_list)));
        }else if(cluster.equals(clusterNames[10])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.music_team_desc_list)));
        }else{

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dpi_desc_list)));
        }
    }

    private ArrayList<String> chooseEventRules(String cluster){
        if(cluster.equals(clusterNames[0])){
            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.kuruk_rules_list)));

        }else if(cluster.equals(clusterNames[1])){//for arts cluster

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arts_rules_list)));
        }else if(cluster.equals(clusterNames[2])){//for dramatics

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dramatics_rules_list)));
        }else if(cluster.equals(clusterNames[3])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.english_lits_rules_list)));
        }else if(cluster.equals(clusterNames[4])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hindi_lits_rules_list)));
        }else if(cluster.equals(clusterNames[5])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.eastern_dance_rules_list)));
        }else if(cluster.equals(clusterNames[6])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.telugu_lits_rules_list)));
        }else if(cluster.equals(clusterNames[7])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.fotohub_rules_list)));
        }else if(cluster.equals(clusterNames[8])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.tamil_sangam_rules_list)));
        }else if(cluster.equals(clusterNames[9])){

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.music_team_rules_list)));
        }else{

            return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dpi_rules_list)));
        }
    }
}
