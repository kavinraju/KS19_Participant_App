package dpi.ks19.participantapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import dpi.ks19.participantapp.Adapter.ClusterAdapter;
import dpi.ks19.participantapp.R;

public class DayTwoFrag extends Fragment{

    private RecyclerView clusterRecyclerView;
    private ClusterAdapter clusterAdapter;
    private View view;
    private Context context;



    public DayTwoFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_day_two, container, false);
        // Inflate the layout for this fragment

        this.context = view.getContext();

        clusterRecyclerView = view.findViewById(R.id.d2ClusterRecyclerView);        //Creates the recycler view and displays the clusters
        clusterAdapter = new ClusterAdapter(context, 23);
        clusterRecyclerView.setAdapter(clusterAdapter);
        clusterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;

    }
}

