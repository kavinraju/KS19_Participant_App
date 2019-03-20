package dpi.ks19.participantapp.CallbackInterface;

import java.util.ArrayList;

import dpi.ks19.participantapp.Adapter.ClusterAdapter;
import dpi.ks19.participantapp.Model.Schedule;

public interface ScheduleInterface {
    void callback(String queryGame);

    void setScheduleData(ArrayList<Schedule> data, ClusterAdapter.ClusterHolder gameHolder, boolean isEmpty);
}
