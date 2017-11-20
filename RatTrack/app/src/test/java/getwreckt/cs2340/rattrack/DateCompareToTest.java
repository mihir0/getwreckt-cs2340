package getwreckt.cs2340.rattrack;

import org.junit.Assert;
import org.junit.Test;

import getwreckt.cs2340.rattrack.model.Date;

/**
 * Created by aguy on 11/18/17.
 */

public class DateCompareToTest {

    /**
     * Tests whether one date two months before another date is less than that date.
     */
    @Test
    public void testDateTwoMonthsLessThan() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("7/2/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be < 0", d2.compareTo(d1)), d2.compareTo(d1) < 0);
    }

    /**
     * Tests whether one date two months after another date is greater than that date.
     */
    @Test
    public void testDateTwoMonthsGreaterThan() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("7/2/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be > 0", d1.compareTo(d2)), d1.compareTo(d2) > 0);
    }

    /**
     * Tests whether one date two days after another date is greater than that date.
     */
    @Test
    public void testDateTwoDaysGreaterThan() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("9/4/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be > 0", d2.compareTo(d1)), d2.compareTo(d1) > 0);
    }

    /**
     * Tests whether one date two years before another date is less than that date.
     */
    @Test
    public void testDateTwoYearsLessThan() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("9/2/2015 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be < 0", d2.compareTo(d1)), d2.compareTo(d1) < 0);
    }

    /**
     * Tests whether one date two years after another date is greater than that date.
     */
    @Test
    public void testDateTwoYearsLGreaterThan() {
        Date d1 = new Date("9/2/2015 12:01:00 PM");
        Date d2 = new Date("9/2/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be > 0", d2.compareTo(d1)), d2.compareTo(d1) > 0);
    }

    /**
     * Tests whether one date three days before another date over a month is less than that date.
     */
    @Test
    public void testDateThreeDaysLessThanOverMonth() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("8/31/2017 12:01:00 PM");
        Assert.assertTrue(String.format("%d, should be < 0", d2.compareTo(d1)), d2.compareTo(d1) < 0);
    }

    /**
     * Tests whether one date two minutes before another date over noon is less than that date.
     */
    @Test
    public void testDateTwoMinutesLessThanOverPM() {
        Date d1 = new Date("9/2/2017 12:01:00 PM");
        Date d2 = new Date("9/2/2017 11:59:00 AM");
        Assert.assertTrue(String.format("%d, should be < 0", d2.compareTo(d1)), d2.compareTo(d1) < 0);
    }

    /**
     * Tests whether one date two seconds before another date over a minute is less than that date.
     */
    @Test
    public void testDateTwoSecondsLessThanOverMinute() {
        Date d1 = new Date("9/2/2017 12:01:01 PM");
        Date d2 = new Date("9/2/2017 12:00:59 PM");
        Assert.assertTrue(String.format("%d, should be < 0", d2.compareTo(d1)), d2.compareTo(d1) < 0);
    }

    /**
     * Tests whether one date fifteen months before another date is less than that date.
     */
    @Test
    public void testDateFifteenMonthsLessThan() {
        Date d1 = new Date("9/2/2017 12:01:01 PM");
        Date d2 = new Date("6/2/2016 12:01:01 PM");
        Assert.assertTrue(String.format("%d, should be < 0", d2.compareTo(d1)), d2.compareTo(d1) < 0);
    }

    /**
     * Tests whether one date four hours before another date is less than that date.
     */
    @Test
    public void testDateFourHoursLessThanOverDay() {
        Date d1 = new Date("9/2/2017 12:01:01 AM");
        Date d2 = new Date("9/1/2017 8:00:59 PM");
        Assert.assertTrue(String.format("%d, should be < 0", d2.compareTo(d1)), d2.compareTo(d1) < 0);
    }

    /**
     * Tests whether two dates that are the same date are equal to each other.
     */
    @Test
    public void testDateSameDate() {
        Date d1 = new Date("9/2/2017 12:01:01 PM");
        Date d2 = new Date("9/2/2017 12:01:01 PM");
        Assert.assertTrue(String.format("%d, should be 0", d2.compareTo(d1)), d2.compareTo(d1) == 0);
    }
}
