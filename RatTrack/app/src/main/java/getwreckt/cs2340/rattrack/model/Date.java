package getwreckt.cs2340.rattrack.model;



import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    /**
     *  No argument constructor for Firebase
     */
    public Date() { }

    /**
     *  Parametrized constructor
     * @param data input in the form of "month/date/year hour:minute:second AM/PM"
     */
    public Date(String data) {
        //data string is originally of the form "month/date/year hour:minute:second AM/PM"
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
        setIsPM("PM".equals(data));
        setMeridiem();
        generateSystemString();
    }

    /**
     * Getter method for month
     * @return month in int
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Setter method for month
     * @param month month in int
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     *
     * @return
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Setter method for date
     * @param date input date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Getter method for year
     * @return year in int
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Setter method for year
     * @param year year in int
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Getter method for hour
     * @return hour in int
     */
    public int getHour() {
        return this.hour;
    }
    /**
     *  Sets an hour in military time
     * @param hour hour in int
     */
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

    public String getMeridiem() {
        return this.meridiem;
    }

    public void setMeridiem() {
        this.meridiem = isPM ? "PM" : "AM";
    }

    private String digitToString(int digit) {
        return ((digit < 10 ? "0" : "") + digit);
    }

    private String generateSystemString() {
        String monthStr = digitToString(month);
        String dateStr = digitToString(date);
        String hourStr  = digitToString(hour);
        String minuteStr = digitToString(minute);
        String secondStr = digitToString(second);

        String sysID = "" + year + "-" + monthStr + "-" + dateStr + " " + hourStr + ":" + minuteStr + ":"
                + secondStr;
        this.systemString = sysID;
        return sysID;
    }

    //descending order on system generated strings
    @Override
    public int compareTo(@NonNull Date other) {
        java.util.Comparator<Date> dateChainedComparator = new DateChainedComparator();
        return dateChainedComparator.compare(other, this);
    }

    public static class DateChainedComparator implements Comparator<Date> {
        private java.util.Collection<Comparator<Date>> listComparators = new ArrayList<>();

        //custom comparators
        //date ordering goes: year, month, date, hour, minute, second
        //DEFAULT IS DESCENDING ORDER

        private static Comparator<Date> YearComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getYear() - d1.getYear();
            }
        };

        private static Comparator<Date> MonthComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getMonth() - d1.getMonth();
            }
        };

        private static Comparator<Date> DateComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getDate() - d1.getDate();
            }
        };

        //hour in military time
        private static Comparator<Date> HourComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getHour() - d1.getHour();
            }
        };

        private static Comparator<Date> MinuteComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getMinute() - d1.getMinute();
            }
        };

        private static Comparator<Date> SecondComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getSecond() - d1.getSecond();
            }
        };

        public DateChainedComparator() {
            this.listComparators.add(YearComparator);
            this.listComparators.add(MonthComparator);
            this.listComparators.add(DateComparator);
            this.listComparators.add(HourComparator);
            this.listComparators.add(MinuteComparator);
            this.listComparators.add(SecondComparator);
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
        @Override
        public Date createFromParcel (Parcel in) {return new Date(in);}

        @Override
        public Date[] newArray(int size) {return new Date[size];}
    };

    @Override
    public int describeContents() {return 0;}

    public String toString() {
        return getCalendarDate() + " " + getTime();
    }
}