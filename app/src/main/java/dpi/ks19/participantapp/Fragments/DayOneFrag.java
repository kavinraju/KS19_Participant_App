package dpi.ks19.participantapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dpi.ks19.participantapp.Adapter.ClusterAdapter;
import dpi.ks19.participantapp.CallbackInterface.EventsByCluster;
import dpi.ks19.participantapp.CallbackInterface.ScheduleInterface;
import dpi.ks19.participantapp.Model.EventClass;
import dpi.ks19.participantapp.Model.Schedule;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class DayOneFrag extends Fragment implements EventsByCluster {

    public static DayOneFrag instance;
    public ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    private RecyclerView clusterRecyclerView;
    private ClusterAdapter clusterAdapter;
    private View view;
    boolean success;
    private ScheduleInterface callbackInterface;
    private ClusterAdapter.ClusterHolder clusterHolder;

    public static DayOneFrag getInstance() {  //Gets the instance of this fragment.
        if (instance == null) {
            instance = new DayOneFrag();
        }
        return instance;
    }

    public DayOneFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_day_one, container, false);
        // Inflate the layout for this fragment
        //sample call to end point
        ApiHelper.getInstance(getActivity()).getEventsForCluster(23, "a", this);

        clusterRecyclerView = view.findViewById(R.id.d1ClusterRecyclerView);        //Creates the rrecycler view and displays the clusters
        clusterAdapter = new ClusterAdapter(getActivity(), 1);
        clusterRecyclerView.setAdapter(clusterAdapter);
        clusterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;

    }

    @Override
    public void getEventsByCluster(ArrayList<EventClass> data, boolean success) {

    }

    public void getSchedule(String query, final ScheduleInterface callbackInterface, final ClusterAdapter.ClusterHolder clusterHolder) {
        //Schedule data is set here.

        //TODO: Implement API here.
        ApiHelper.getInstance(getActivity()).getEventsForCluster(22, query, this);

        //API is to be called here.
        //API returns JSONArray.
        //Parse it here and return it in scheduleArrayList.
        //It is to be queried with the 'query', which has the query word, and day.
        //Name, venue in the format: "Venue: ________" and time in the format: "08.30 AM - 12.30 PM" is the data that is required.

        scheduleArrayList.clear();          //Clears the previous data.

        this.callbackInterface = callbackInterface;   //Saves data to
        this.clusterHolder = clusterHolder;
    }
}
