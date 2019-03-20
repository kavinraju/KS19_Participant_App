package dpi.ks19.participantapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dpi.ks19.participantapp.CallbackInterface.EventsByCluster;
import dpi.ks19.participantapp.Model.EventClass;
import dpi.ks19.participantapp.Networking.ApiHelper;
import dpi.ks19.participantapp.R;

public class DayOneFrag extends Fragment implements EventsByCluster {

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
        // Inflate the layout for this fragment
        //sample call to end point
        ApiHelper.getInstance(getActivity()).getEventsForCluster(23, "a", this);
        return inflater.inflate(R.layout.fragment_day_one, container, false);

    }

    @Override
    public void getEventsByCluster(ArrayList<EventClass> data, boolean success) {
        //data receiced from endpoint check sucess flag for errors

    }
}
