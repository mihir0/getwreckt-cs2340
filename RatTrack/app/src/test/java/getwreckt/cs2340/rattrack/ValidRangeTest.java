package getwreckt.cs2340.rattrack;

import android.support.annotation.Nullable;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import getwreckt.cs2340.rattrack.controller.DateRangeActivity;
import getwreckt.cs2340.rattrack.model.Date;

/**
 * Tests isValidRange in DateRangeActivity
 * author: Maya Viust
 */

public class ValidRangeTest {
    @Nullable
    private static Date null1;
    @Nullable
    private static Date null2;
    private static Date d1;
    private static Date d2;
    private static Date d3;
    private static final DateRangeActivity dra = new DateRangeActivity();

    /**
     * sets up all the dates used for testing
     */
    @BeforeClass
    public static void setUp() {
        null1 = null;
        null2 = null;
        d1 = new Date(8,17,2001,10,48,true);
        d2 = new Date(11,19,2017,5,55,false);
        d3 = new Date(11,19,2017,5,56,false);
    }

    /**
     * null dates cannot be compared. checks if right exception was thrown
     */
    @Test
    public void nullDates() {
        boolean result = false;
        try {
            dra.isValidRange(null1, null2);
        } catch (java.lang.IllegalArgumentException iae) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    /**
     * Same dates should be a valid range
     */
    @Test
    public void sameDates() {
        Assert.assertTrue(dra.isValidRange(d1, d1));
        Assert.assertTrue(dra.isValidRange(d2, d2));
    }

    /**
     * invalid ranges should have the method return false
     * end should not come before start
     */
    @Test
    public void invalidRange() {
        Assert.assertFalse(dra.isValidRange(d2, d1));
        Assert.assertFalse(dra.isValidRange(d3, d2));
    }

    /**
     * valid ranges should return true
     * start should come before end
     */
    @Test
    public void validRange() {
        Assert.assertTrue(dra.isValidRange(d1, d2));
        Assert.assertTrue(dra.isValidRange(d2, d3));
    }
}
