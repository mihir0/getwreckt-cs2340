package getwreckt.cs2340.rattrack.model;



import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by maya v on 10/20/2017.
 */

public class Date implements Comparable<Date>, Parcelable {
    private int month;
    private int date;
    private int year;

    private int hour; //in military time, for compareTo methods
    private int minute;
    private int second;

    private boolean isPM; //for changing to military time
    private String meridiem; //the M in AM and PM

    private String systemString; //for natural ordering

    //constructor for NYC database date string
    public Date(String data) {
        //data string is orginally of the form "month/date/year hour:minute:second AM/PM"
        //example: "9/5/2012 12:00:00 AM

        //get month
        String dataInput = data.substring(0, data.indexOf("/"));
        this.month = Integer.parseInt(dataInput);

        data = data.substring(data.indexOf("/") + 1);

        //get date
        dataInput = data.substring(0, data.indexOf("/"));
        this.date = Integer.parseInt(dataInput);

        data = data.substring(data.indexOf("/") + 1);

        //get year
        dataInput = data.substring(0, data.indexOf(" "));
        this.year = Integer.parseInt(dataInput);

        data = data.substring(data.indexOf(" ") + 1);

        //get hour
        dataInput = data.substring(0, data.indexOf(":"));
        setHour(Integer.parseInt(dataInput));

        data = data.substring(data.indexOf(":") + 1);

        //get minute
        dataInput = data.substring(0, data.indexOf(":"));
        this.minute = Integer.parseInt(dataInput);

        data = data.substring(data.indexOf(":") + 1);

        //get second
        dataInput = data.substring(0, data.indexOf(" "));
        this.second = Integer.parseInt(dataInput);

        data = data.substring(data.indexOf(" ") + 1);
        setMeridiem(data);
        setIsPM(data);
    }

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
    /** constructor for in app date input
     public Date()
     {

     }     */

    /** constructor for android timestamp
     public Date(Timestamp timestamp) {

     }
     **/

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return this.hour;
    }

    //changes to military time
    public void setHour(int hour) {
        if (isPM) {
            this.hour = hour + 12;
        } else if (hour == 12){
            this.hour = 0;
        } else {
            this.hour = hour;
        }
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public boolean getIsPM() {
        return this.isPM;
    }

    public void setIsPM(boolean value) {
        this.isPM = value;
    }

    public void setIsPM(String meridiem) {
        this.isPM = meridiem.equals("PM");
    }

    public String getMeridiem() {
        return this.meridiem;
    }

    public void setMeridiem(String meridiem) {
        this.meridiem = meridiem;
    }

    private String digitToString(int digit) {
        return (digit < 10) ? ("0" + digit) : ("" + digit);
    }

    public String getSystemString() {
        return this.systemString;
    }

    private String generateSystemString(int month, int date, int year, int hour, boolean isPM,
                                        int minute, int second) {
        String monthStr = digitToString(month);
        String dateStr = digitToString(date);
        String hourStr  = digitToString(hour);
        String minuteStr = digitToString(minute);
        String secondStr = digitToString(second);

        return "" + year + "-" + monthStr + "-" + dateStr + " " + hourStr + ":" + minuteStr + ":"
                + secondStr;
    }

    //descending order on system generated strings
    @Override
    public int compareTo(Date other) {
        return other.getSystemString().compareTo(this.systemString);
    }

    //custom comparators
    //date ordering goes: year, month, date, hour, minute, second
    //DEFAULT IS DESCENDING ORDER

    public static Comparator<Date> YearComparator = new Comparator<Date>() {
        @Override
        public int compare(Date d1, Date d2) {
            return d2.getYear() - d1.getYear();
        }
    };

    public static Comparator<Date> MonthComparator = new Comparator<Date>() {
        @Override
        public int compare(Date d1, Date d2) {
            return d2.getMonth() - d1.getMonth();
        }
    };

    public static Comparator<Date> DateComparator = new Comparator<Date>() {
        @Override
        public int compare(Date d1, Date d2) {
            return d2.getDate() - d1.getDate();
        }
    };

    //hour in military time
    public static Comparator<Date> HourComparator = new Comparator<Date>() {
        @Override
        public int compare(Date d1, Date d2) {
            return d2.getHour() - d1.getHour();
        }
    };

    public static Comparator<Date> MinuteComparator = new Comparator<Date>() {
        @Override
        public int compare(Date d1, Date d2) {
            return d2.getMinute() - d1.getMinute();
        }
    };

    public static Comparator<Date> SecondComparator = new Comparator<Date>() {
        @Override
        public int compare(Date d1, Date d2) {
            return d2.getSecond() - d1.getSecond();
        }
    };

    public class DateChainedComparator implements Comparator<Date> {
        private List<Comparator<Date>> listComparators = new ArrayList<Comparator<Date>>();

        public DateChainedComparator() {
            this.listComparators.add(YearComparator);
            this.listComparators.add(MonthComparator);
            this.listComparators.add(DateComparator);
            this.listComparators.add(HourComparator);
            this.listComparators.add(MinuteComparator);
            this.listComparators.add(SecondComparator);
        }

        public DateChainedComparator(Comparator<Date> ... comparators) {
            for (Comparator<Date> comparator: comparators) {
                listComparators.add(comparator);
            }
        }

        @Override
        public int compare(Date d1, Date d2) {
            for (Comparator<Date> comparator: listComparators) {
                int result = comparator.compare(d1, d2);
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        }
    }

    private int getMeridiemHour() {
        if (isPM) {
            return hour - 12;
        } else {
            if (hour == 0) { return 12; }
        }
        return hour;
    }

    public String getTime() {
        return "" + digitToString(getMeridiemHour()) + ":" + digitToString(this.minute) + ":"
                + digitToString(this.second) + " " + meridiem;
    }

    public String getCalendarDate() {
        return "" + digitToString(this.month) + "-" + digitToString(this.date)
                + digitToString(this.year);
    }

    //Parcelable implementation
    private Date(Parcel in) {
        month = in.readInt();
        date = in.readInt();
        year = in.readInt();
        hour = in.readInt();
        minute = in.readInt();
        second = in.readInt();
        isPM = in.readByte() != 0;
        meridiem = in.readString();
        systemString = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(month);
        dest.writeInt(date);
        dest.writeInt(year);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeInt(second);
        dest.writeInt(isPM ? 1 : 0);
        dest.writeString(meridiem);
        dest.writeString(systemString);
    }

    public static final Parcelable.Creator<Date> CREATOR = new Parcelable.Creator<Date>() {
        public Date createFromParcel (Parcel in) {return new Date(in);}

        public Date[] newArray(int size) {return new Date[size];}
    };

    @Override
    public int describeContents() {return 0;}

    public String toString() {
        return getCalendarDate() + " " + getTime();
    }
}