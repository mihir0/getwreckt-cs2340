package getwreckt.cs2340.rattrack;

/**
 * Created by Mihir on 11/18/17.
 */

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import getwreckt.cs2340.rattrack.model.Date;

public class DateGetMeridiemHourTest {

    /*
    Checks all hours from times 12:00 AM up to 11:59 AM.
    Other private data of the Date class (month, year, and day) are irrelevant to Date.getMeridiemHour()
     */
    @Test
    public void testAMHours() {
        for (int hour = 1; hour <= 12; hour++) {
            Date date = new Date(2000, 10, 10, hour, 10, false);
            Assert.assertEquals(hour, date.getMeridiemHour());
        }
    }
    /*
    Checks all hours from times 12:00 PM up to 11:59 PM.
     */
    @Test
    public void testPMHours() {
        for (int hour = 1; hour <= 12; hour++) {
            Date date = new Date(2000, 10, 10, hour, 10, true);
            Assert.assertEquals(hour, date.getMeridiemHour());
        }
    }
    /*
    Checks all AM hours with all possible minutes (0 - 59 minutes)
     */
    @Test
    public void testAMHoursWithMinutes() {
        for (int hour = 1; hour <= 12; hour++) {
            for (int minute = 0; minute <= 59; minute++) {
                Date date = new Date(2000, 10, 10, hour, minute, false);
                Assert.assertEquals(hour, date.getMeridiemHour());
            }
        }
    }
    /*
    Checks all PM hours with all possible minutes (0 - 59 minutes)
     */
    @Test
    public void testPMHoursWithMinutes() {
        for (int hour = 1; hour <= 12; hour++) {
            for (int minute = 0; minute <= 59; minute++) {
                Date date = new Date(2000, 10, 10, hour, minute, true);
                Assert.assertEquals(hour, date.getMeridiemHour());
            }
        }
    }

    /*
    Checks that getMeridiemHour() still works for AM hours
    after a date object has been created and setHour() has been called.
     */
    @Test
    public void testSetAMHourModifcation() {
        ArrayList<Date> dates = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            dates.add(new Date("10/10/2017 12:00:00 AM"));
        }
        for (int i = 1; i <= 12; i++) {
            dates.get(i - 1).setHour(i);
            Assert.assertEquals(i, dates.get(i - 1).getMeridiemHour());
        }
    }
    /*
    Checks that getMeridiemHour() still works for PM hours
    after a date object has been created and setHour() has been called.
     */
    @Test
    public void testSetPMHourModifcation() {
        ArrayList<Date> dates = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            dates.add(new Date("10/10/2017 12:00:00 PM"));
        }
        for (int i = 1; i <= 12; i++) {
            dates.get(i - 1).setHour(i);
            Assert.assertEquals(i, dates.get(i - 1).getMeridiemHour());
        }
    }
}
