package dpi.ks19.participantapp.Model;

public class EventClass {

    public String eventName, startTimeHours, startTimeMin, endTimeHours, endTimeMin, venue;

    public String getEventTime() {
        String time = "";
        int sH, sM, eH, eM;
        sH = Integer.parseInt(startTimeHours);
        sM = Integer.parseInt(startTimeMin);
        eH = Integer.parseInt(endTimeHours);
        eM = Integer.parseInt(endTimeMin);
        switch (sH) {
            case 0:
                if (sM >= 1 && sM <= 9)
                    time += "12" + "." + 0 + startTimeMin + " AM : ";
                else
                    time += "12" + "." + startTimeMin + " AM : ";
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if (sM >= 1 && sM <= 9)
                    time += "0" + startTimeHours + "." + "0" + startTimeMin + " AM : ";
                else
                    time += "0" + startTimeHours + "." + startTimeMin + " AM : ";
                break;
            case 10:
            case 11:
                if (sM >= 1 && sM <= 9)
                    time += startTimeHours + "." + 0 + startTimeMin + " AM : ";
                else
                    time += startTimeHours + "." + startTimeMin + " AM : ";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                if (sM >= 1 && sM <= 9)
                    time += startTimeHours + "." + 0 + startTimeMin + " PM : ";
                else
                    time += startTimeHours + "." + startTimeMin + " PM : ";
                break;
        }
        switch (eH) {
            case 0:
                if (eM >= 1 && eM <= 9)
                    time += "12" + "." + 0 + startTimeMin + " AM : ";
                else
                    time += "12" + "." + startTimeMin + " AM : ";
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if (sM >= 1 && sM <= 9)
                    time += "0" + startTimeHours + "." + "0" + startTimeMin + " AM : ";
                else
                    time += "0" + startTimeHours + "." + startTimeMin + " AM : ";
                break;
            case 10:
            case 11:
                if (eM >= 1 && eM <= 9)
                    time += endTimeHours + "." + 0 + endTimeMin + " AM : ";
                else
                    time += endTimeHours + "." + endTimeMin + " AM : ";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                if (eM >= 1 && eM <= 9)
                    time += startTimeHours + "." + 0 + startTimeMin + " PM : ";
                else
                    time += startTimeHours + "." + startTimeMin + " PM : ";
                break;
        }
        return time;
    }
}
