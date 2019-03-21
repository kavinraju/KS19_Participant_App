package dpi.ks19.participantapp.CallbackInterface;

import java.util.ArrayList;

import dpi.ks19.participantapp.Adapter.ClusterAdapter;
import dpi.ks19.participantapp.Model.EventClass;

public interface EventsByCluster {
    void getEventsByCluster(ArrayList<EventClass> data, ClusterAdapter.ClusterHolder holder, boolean success);
}
