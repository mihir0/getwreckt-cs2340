package getwreckt.cs2340.rattrack.model;


import java.util.Comparator;

/**
 * Created by maya v on 10/20/2017.
 */

public class Date implements Comparable<Date> {
    private int month;
    private int date;
    private int year;

    private int hour; //in military time, for compareTo methods
    private int minute;
    private int second;

    private boolean isPM; //for changing to military time

    private String systemString; //for natural ordering

    //constructor for NYC database date string
    public Date(String data) {
        //data string is orginally of the form "month/date/year hour:minute:second AM/PM"
        //example: "9/5/2012 12:00:00 AM"
        String dataInput = data;


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
        return this.year = year;
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

    public boolean getIsPM() {
        return this.isPM;
    }

    public void setIsPM(boolean value) {
        this.isPM = value;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    private String generateSystemString(int month, int date, int year, int hour, boolean isPM,
                                        int minute, int second) {
        String monthStr;
        String dateStr;
        String hourStr;
        String minuteStr;
        String secondStr;

        if (month < 10) {monthStr = "0" + month; } else { monthStr = "" + month; }

        if (date < 10) { dateStr = "0" + date; } else { dateStr = "" + date; }

        //military time conversion to facilitate date comparison
        if (isPM) { hour += 12; } else if (hour == 12) { hour = 0; }

        if (hour < 10) { hourStr = "0" + hour; } else { hourStr = "" + hour; }

        if (minute < 10) { minuteStr = "0" + minute; } else { minuteStr = "" + minute; }

        if (second < 10) { secondStr = "0" + second; } else { secondStr = "" + second; }

        String result = "" + year + "-" + monthStr + "-" + dateStr + " " + hourStr + ":" +
                minuteStr + ":" + secondStr;

        return result;
    }

    @Override
    public int compareTo(Date other) {
        return this.systemString.compareTo(other.systemString);
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

    public String toString() {
        String meridiem; //the M in AM and PM

        if (isPM) { meridiem = "PM"; } else { meridiem = "AM"; }

        return "" + month + "/" + date + "/" + year + " " + hour + ":" + minute + ":" + second + " "
                + meridiem;
    }
}
