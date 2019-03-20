package dpi.ks19.participantapp.Model;

public class Schedule {     //Modal class for ScheduleAdapter.
    String eventName, eventVenue, eventTime;

    public Schedule(String name, String venue, String time) {
        eventName = name;
        eventVenue = venue;
        eventTime = time;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventVenue() {
        return eventVenue;
    }
}
