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
    public static List<RatSighting> ratSightings = new ArrayList<>();

    public static Date startMapDate = new Date("01/01/1000 12:00:00 AM");
    public static Date endMapDate = new Date("12/31/3999 11:59:00 PM");

    public static Date startGraphDate = new Date("01/01/1000 12:00:00 AM");
    public static Date endGraphDate = new Date("12/31/3999 11:59:00 PM");

    /**
     * Filters and returns sightings which fall within the given date range
     * @param numTimeUnitsAgo the username to check
     * @param timeUnit the password to check
     * @return list of ratSightings which are inside of the dates range
     */
    public static List<RatSighting> dateFilter(int numTimeUnitsAgo, String timeUnit) {
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
        switch (timeUnit) {
            case "Months":
                //the user can view sightings since 30 months ago at maximum

                if (numTimeUnitsAgo >= curMonth) {
                    monthSince = 12 - ((numTimeUnitsAgo - curMonth) % 12);
                } else {
                    monthSince = curMonth - numTimeUnitsAgo;
                }

                int currentMonth = curMonth;
                int newMonth = 0;
                int currentYear = curYear;
                while (numTimeUnitsAgo > 0) {
                    if (currentMonth == 1) {
                        newMonth = 12;
                        currentYear = currentYear - 1;
                    } else {
                        newMonth = currentMonth - 1;
                    }
                    currentMonth = newMonth;
                    numTimeUnitsAgo--;
                }
                yearSince = currentYear;

                break;
            case "Years":
                //the user can view sightings since 3-4 years ago at maximum
                yearSince = curYear - numTimeUnitsAgo;
                break;
            case "Days": {
                //the user can view sightings since 50 days ago at maximum
                boolean monthIs31Days = false;
                if ((curMonth == 5) || (curMonth == 1) || (curMonth == 3) || (curMonth == 7)
                        || (curMonth == 8) || (curMonth == 10) || (curMonth == 12)) {
                    monthIs31Days = true;
                }
                int currentHour = curHours;
                int currentDay = curDay;
                int numTimeUnitsAgoInHours = numTimeUnitsAgo * 24;
                while (numTimeUnitsAgoInHours > 0) {
                    if (currentHour == 1) {
                        currentHour = 24;
                        if (currentDay == 1) {
                            if (monthIs31Days) {
                                if (curMonth == 8) {
                                    currentDay = 31;
                                    monthIs31Days = true;
                                    curMonth = 7;
                                } else if (curMonth == 1) {
                                    currentDay = 31;
                                    monthIs31Days = true;
                                    curMonth = 12;
                                    curYear = curYear - 1;
                                } else if (curMonth == 3) {
                                    currentDay = 28;
                                    monthIs31Days = false;
                                    curMonth = 2;
                                } else {
                                    currentDay = 30;
                                    monthIs31Days = false;
                                    curMonth = curMonth - 1;
                                }
                            } else {
                                currentDay = 31;
                                monthIs31Days = true;
                                curMonth = curMonth - 1;
                            }
                        } else {
                            currentDay = currentDay - 1;
                        }
                    } else {
                        currentHour--;
                    }
                    numTimeUnitsAgoInHours--;
                }
                monthSince = curMonth;
                daySince = currentDay;
                yearSince = curYear;
                break;
            }
            case "Hours": {
                //the user can view sightings since 24 hours ago at maximum
                boolean monthIs31Days = false;
                if ((curMonth == 5) || (curMonth == 1) || (curMonth == 3) || (curMonth == 7)
                        || (curMonth == 8) || (curMonth == 10) || (curMonth == 12)) {
                    monthIs31Days = true;
                }
                while (numTimeUnitsAgo > 0) {
                    numTimeUnitsAgo--;
                }
                monthSince = curMonth;
                daySince = curDay;
                yearSince = curYear;
                hourSince = curHours - numTimeUnitsAgo;
                break;
            }
        }

        List<RatSighting> dateFilteredRatSightings = new ArrayList<>();
        for (RatSighting r: ratSightings) {
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
            switch (timeUnit) {
                case "Months":
                    if (year > yearSince) {
                        dateFilteredRatSightings.add(r);
                    } else if ((year == yearSince) && (month >= monthSince)) {
                        dateFilteredRatSightings.add(r);
                    }
                    break;
                case "Years":
                    if (year > yearSince) {
                        dateFilteredRatSightings.add(r);
                    } else if ((year == yearSince) && (month >= monthSince)) {
                        dateFilteredRatSightings.add(r);
                    }
                    break;
                case "Days":
                    if (year > yearSince) {
                        dateFilteredRatSightings.add(r);
                    } else if (year == yearSince) {
                        if (month >= monthSince) {
                            if (day <= daySince) {
                                dateFilteredRatSightings.add(r);
                            }
                        }
                    }
                    break;
                case "Hours":

                    break;
            }

        }
        return dateFilteredRatSightings;
    }

    public static void addSighting(RatSighting sighting) {
        ratSightings.add(sighting);
    }

    public List<RatSighting> getRatSightings() {
        return this.ratSightings;
    }


    //
}
