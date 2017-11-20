package getwreckt.cs2340.rattrack;

import org.junit.Test;

import getwreckt.cs2340.rattrack.model.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Fitsum Seyoum on 11/19/17.
 *
 * This Class tests setHour(int hour) method in Date class
 * it only tests if those accepted regular hours have been changed correctly to their
 * respective military hour to support date filtering
 *
 */
public class TestSetHourMethod {
    private Date dateUsedToFilter;
    private int hour;
    private boolean isPm;

    /**
     * This method tests the setHour(int) method in Date class, the method purpose
     * is changing a Regular time input of Rat Sighting App user to Military time.
     *
     *  Here 11:00:00 PM regular time corresponds to  military time of 23.
     *
     * The setHour(int hour) converts the hour input to military time
     *
     * First branch coverage where the first if statement is true
     *
     */
    @Test
    public void testSetHour11Pm() {

        dateUsedToFilter = new Date("11/01/2017 11:00:00 PM");
        hour = dateUsedToFilter.getHour();

        // This is the method I am currently testing: setHour(int)
        dateUsedToFilter.setHour(hour);

        // the expected is in military time
        assertEquals(23, dateUsedToFilter.getHour());
    }
    /**
     * Tests the setHour(int hour) method
     * Second branch coverage where the second else if statement is true and the others are false
     *
     * Here regular 12:00:00 AM time will be changed to 00 military time
     */
    @Test
    public void testSetHour12Am() {
        dateUsedToFilter = new Date("11/01/2017 12:00:00 AM");
        hour = dateUsedToFilter.getHour();

        // This is the method I am currently testing: setHour(int)
        dateUsedToFilter.setHour(hour);

        assertEquals(0, dateUsedToFilter.getHour());
    }

    /**
     * Tests the setHour(int hour) method
     *
     *
     * Third branch coverage where the third else statement is true and the others are false
     *
     * Here regular 07:00:00 AM time corresponds to military time of 07
     *
     */
    @Test
    public void testSetHour7Am() {

        dateUsedToFilter = new Date("01/01/1000 07:00:00 AM");
        hour = dateUsedToFilter.getHour();

        dateUsedToFilter.setHour(hour);

        assertEquals(7, dateUsedToFilter.getHour());
    }

    /**
     * Tests the setHour(int hour) method
     *
     *
     * supplements first branch coverage where the first if statement is true and
     * the others are false
     *
     * Here regular 06:00:00 PM time corresponds to military time of 18
     *
     */
    @Test
    public void testSetHour6PM() {

        dateUsedToFilter = new Date("11/01/2017 06:00:00 PM");
        hour = dateUsedToFilter.getHour();

        dateUsedToFilter.setHour(hour);

        assertEquals(18, dateUsedToFilter.getHour());
    }
    /**
     * Tests the setHour(int hour) method
     *
     *
     * supplements the third branch coverage where the else statement is true and
     * the others are false, this is just to see the edge case
     *
     * Here regular 12:00:00 PM time corresponds to military time of 12
     *
     */
    @Test
    public void testSetHour12PM() {

        dateUsedToFilter = new Date("11/01/2017 12:00:00 PM");
        hour = dateUsedToFilter.getHour();

        dateUsedToFilter.setHour(hour);

        assertEquals(12, dateUsedToFilter.getHour());
    }

}