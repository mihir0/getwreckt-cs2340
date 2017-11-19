package getwreckt.cs2340.rattrack;

import org.junit.Assert;
import org.junit.Test;

import getwreckt.cs2340.rattrack.model.Date;

/**
 * Created by aguy on 11/18/17.
 */

public class DateCompareToTest {

    @Test
    public void testDateTwoMonthsLessThan() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("7/2/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be -2", d2.compareTo(d1)), d2.compareTo(d1) == -2);
    }

    @Test
    public void testDateTwoMonthsGreaterThan() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("7/2/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be 2", d1.compareTo(d2)), d1.compareTo(d2) == 2);
    }

    @Test
    public void testDateTwoDaysGreaterThan() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("9/4/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be 2", d2.compareTo(d1)), d2.compareTo(d1) == 2);
    }

    @Test
    public void testDateTwoYearsLessThan() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("9/2/2015 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be -2", d2.compareTo(d1)), d2.compareTo(d1) == -2);
    }

    @Test
    public void testDateThreeDaysLessThanOverMonth() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("8/31/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be -3", d2.compareTo(d1)), d2.compareTo(d1) == -3);
    }

    @Test
    public void testDateTwoMinutesLessThanOverPM() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("9/2/2017 11:59:00 AM");
        Assert.assertTrue(String.format("%d, should be -2", d2.compareTo(d1)), d2.compareTo(d1) == -2);
    }

    @Test
    public void testDateTwoSecondsLessThanOverMinute() {
        Date d1 = new Date("9/2/2017 12:01:01 PM");
        Date d2 = new Date("9/2/2017 12:00:59 PM");
        Assert.assertTrue(String.format("%d, should be -2", d2.compareTo(d1)), d2.compareTo(d1) == -2);
    }
}
