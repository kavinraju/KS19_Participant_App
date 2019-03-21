package dpi.ks19.participantapp.Model;

public class EventClass {

    public String eventName, startTimeHours, startTimeMin, endTimeHours, endTimeMin, venue;

    public String getEventTime() {
        int sHours = Integer.parseInt(startTimeHours);
        int sMin = Integer.parseInt(startTimeMin);

        int eHours = Integer.parseInt(endTimeHours);
        int eMin = Integer.parseInt(endTimeMin);

        return convertTime(sHours, sMin) + "\t\t" + convertTime(eHours, eMin);
    }

    private String convertTime(int hours, int mins) {

        if (hours == 0) {
            if (mins == 0) {
                return "12" + ":" + mins + "0" + " PM";

            }
            return "12" + ":" + mins + " PM";

        } else if (hours < 12) {
            if (mins == 0) {
                return hours + ":" + mins + "0" + " AM";
            }
            return hours + ":" + mins + " AM";

        } else {
            hours = hours - 12;
            if (mins == 0) {
                return hours + ":" + mins + "0" + " PM";
            }
            return hours + ":" + mins + " PM";

        }

    }


}
