package getwreckt.cs2340.rattrack.model;



import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Date class creates Date item with attributes month, date, year, hour, minute, second, AM/PM,
 * meridiem, custom system string
 * Comes with comparator
 * author: Maya Viust
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

    private String toString;

    /**
     *  No argument constructor for Firebase
     */
    public Date() { }

    /**
     *  constructor for Firebase
     */
    public Date(int month, int day, int year, int hour, int minute, boolean isPM) {
        this.date = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        meridiem = (isPM) ? "PM" : "AM";
    }

    /**
     *  Parametrized constructor
     * @param data input in the form of "month/date/year hour:minute:second AM/PM"
     */
    public Date(String data) {
        //data string is originally of the form "month/date/year hour:minute:second AM/PM"
        //example: "9/5/2012 12:00:00 AM

        toString = data;

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
        this.systemString = generateSystemString();
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
     * Getter method for date
     * @return date date in int
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
    public final void setHour(int hour) {
        if (isPM) {
            this.hour = hour + 12;
        } else if (hour == 12){
            this.hour = 0;
        } else {
            this.hour = hour;
        }
    }

    /**
     * Getter method for minute
     * @return minute in int
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * Setter method for minute
     * @param minute minute in int
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Getter method for second
     * @return second in int
     */
    public int getSecond() {
        return this.second;
    }

    /**
     * Setter method for second
     * @param second in int
     */
    public void setSecond(int second) {
        this.second = second;
    }

    /**
     * Getter for pm
     * @return boolean value for pm
     */
    public boolean getIsPM() {
        return this.isPM;
    }

    /**
     * Setter method for pm
     * @param value boolean input value
     */
    public final void setIsPM(boolean value) {

        this.isPM = value;
    }

    /**
     * Getter method of meridiem
     * @return string representation of meridiem
     */
    public String getMeridiem() {
        return this.meridiem;
    }

    /**
     * Setter method of meridiem
     */
    private final void setMeridiem() {
        this.meridiem = isPM ? "PM" : "AM";
    }

    /**
     * Digit to string converter
     * @param digit integer value of digit
     * @return string representation of digit
     */
    private String digitToString(int digit) {
        return ((digit < 10 ? "0" : "") + digit);
    }

    /**
     * Getter method for system String
     * @return system string
     */
    public String getSystemString() {
        return this.systemString;
    }

    /**
     * Method which generate system string
     * @return String representation of system string
     */
    private String generateSystemString() {
        return "" + this.year + "-" + digitToString(this.month) + "-" + digitToString(this.date)
                + " " + digitToString(this.hour) + ":" + digitToString(this.minute) + ":"
                + digitToString(this.second);
    }

    //descending order on system generated string
    @Override
    public int compareTo(@NonNull Date other) {
        java.util.Comparator<Date> dateChainedComparator = new DateChainedComparator();
        return dateChainedComparator.compare(other, this);
    }

    private int getMeridiemHour() {
        if (isPM) {
            return hour - 12;
        } else {
            if (hour == 0) { return 12; }
        }
        return hour;
    }

    /**
     * Getter method for time
     * @return String representation of time
     */
    public String getTime() {
        return "" + digitToString(getMeridiemHour()) + ":" + digitToString(this.minute) + ":"
                + digitToString(this.second) + " " + meridiem;
    }

    /**
     * Concatenates month,date and year which are calendar
     * @return string representation of calendar
     */
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

    /**
     * ToString method for Date class
     * @return String representation of date and time
     */
    public String toString() {
        return toString == null ? getCalendarDate() + " " + getTime() : toString;
    }
}
