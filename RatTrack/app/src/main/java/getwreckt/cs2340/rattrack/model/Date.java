package getwreckt.cs2340.rattrack.model;

/**
 * Created by maya v on 10/20/2017.
 */

public class Date {
    private int month;
    private int date;
    private int year;

    private int hour;
    private int minute;
    private int second;

    private boolean isPM; //for changing to military time if needed

    //constructor for NYC database date string
    public Date(String data) {
        //data string is orginally of the form "month/date/year hour:minute:second AM/PM"
        //example: "9/5/2012 12:00:00 AM"

    }

    /** constructor for in app date input
     public Date()
     {

     }     */

    /** constructor for android timestamp
     public Date(Timestamp timestamp) {

     }
     **/


    public String toString() {
        String meridiem; //the M in AM and PM

        if (isPM) { meridiem = "PM"; } else { meridiem = "AM"; }

        return "" + month + "/" + date + "/" + year + " " + hour + ":" + minute + ":" + second + " "
                + meridiem;
    }
}
