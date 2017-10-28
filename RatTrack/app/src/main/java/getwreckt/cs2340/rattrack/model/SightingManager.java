package getwreckt.cs2340.rattrack.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Manages rat sightings: filters, sorts, creates, deletes.
 * all methods should be static
 * Created by maya v on 10/28/2017.
 */

public class SightingManager {
    private static List<RatSighting> ratSightings = new ArrayList<>();
    /**
     * Filters and returns sightings which fall within the given date range
     * @param numTimeUnitsAgo the username to check
     * @param timeUnit the password to check
     * @return list of ratSightings which are inside of the dates range
     */
    public List<RatSighting> dateFilter(int numTimeUnitsAgo, String timeUnit) {
        // assigns the current day and time as a reference point

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
        Calendar cal = Calendar.getInstance();
        String curDateAsStr = dateFormat.format(cal.getTime());
        Date curDate = new Date(curDateAsStr);
        int curYear = curDate.getYear();
        int curMonth = curDate.getMonth();
        int curDay = curDate.getDate();
        int curHours = curDate.getHour();
        int curMinutes = curDate.getMinute();
        int curSeconds = curDate.getSecond();

        // assigns each time component of a date in history from which to show sightings
        int monthSince = curMonth;
        int yearSince = curYear;
        int daySince = curDay;
        int hourSince = curHours;
        if (timeUnit.equals("Months")) {
            if (numTimeUnitsAgo >= curMonth) {
                monthSince = 12 - ((numTimeUnitsAgo - curMonth) % 12);
            } else {
                monthSince = curMonth - numTimeUnitsAgo;
            }

        } else if (timeUnit.equals("Years")) {
            yearSince = curYear - numTimeUnitsAgo;
        } else if (timeUnit.equals("Days")) {
            daySince = curDay - numTimeUnitsAgo;
        } else if (timeUnit.equals("Hours")) {
            hourSince = curHours - numTimeUnitsAgo;
        }

        ArrayList<RatSighting> dateFilteredRatSightings = new ArrayList<>();
        for (RatSighting r: Model.ratSightings) {
            String date = r.getDate().toString();
            String mdy = date.split(" ")[0];
            String hms = date.split(" ")[1];
            String h = hms.split(":")[0];
            String min = hms.split(":")[1];
            String s = hms.split(":")[2];
            String m = mdy.split("/")[0];
            String d = mdy.split("/")[1];
            String y = mdy.split("/")[2];
            int hour = Integer.parseInt(h);
            int minutes = Integer.parseInt(min);
            int seconds = Integer.parseInt(s);
            int year = Integer.parseInt(y);
            int day = Integer.parseInt(d);
            int month = Integer.parseInt(m);

        }


        return new ArrayList<RatSighting>();
    }

    //
}
