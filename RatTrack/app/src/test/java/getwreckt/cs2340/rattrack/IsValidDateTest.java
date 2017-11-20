package getwreckt.cs2340.rattrack;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Patel on 11/18/2017.
 */
public class IsValidDateTest {

    private static final int TIMEOUT = 200;
    private String unselMonth;
    private String unselDay;
    private String unselYear;
    private String unselHour;
    private String unselMin;



    @Before
    public void setUp() {
        unselMonth = "Month";
        unselDay = "Day";
        unselYear = "Year";
        unselHour = "Hour";
        unselMin = "Minute";

    }
    /**
     * These tests are for the isValidDate() method in the DateRangeActivity. The
     * purpose of the method is to check whether or not a given date is
     * a valid date.
     *
     * isValidDate(String selMonth, String selDay, String selYear, String selHour, String selMin)
     * takes in components of a date, and returns true if they are valid, and false if not
     *
     */

        @Test (timeout = TIMEOUT)
        public void testUnselectedDate() throws Exception {

        /* Testing with unselected fields. isValidDate should return false in each case */
            assertFalse("Expected false, returned: " +
                        ((isValidDate(unselMonth, "01", "2017", "12", "00")) ? "true" : "false"),
                    isValidDate(unselMonth, "01", "2017", "12", "00"));

            assertFalse("Expected false, returned: " +
                        ((isValidDate("January", unselDay, "2017", "12", "00")) ? "true" : "false"),
                    isValidDate("January", unselDay, "2017", "12", "00"));

            assertFalse("Expected false, returned: " +
                        ((isValidDate("January", "01", unselYear, "12", "00")) ? "true" : "false"),
                    isValidDate("January", "01", unselYear, "12", "00"));

            assertFalse("Expected false, returned: " +
                        ((isValidDate("January", "01", "2017", unselHour, "00")) ? "true" : "false"),
                    isValidDate("January", "01", "2017", unselHour, "00"));

            assertFalse("Expected false, returned: " +
                        ((isValidDate("January", "01", "2017", "12", unselMin)) ? "true" : "false"),
                    isValidDate("January", "01", "2017", "12", unselMin));
        }
        //===============================================================================================================================
        /* Testing an unrealistic date. isValidDate should return false in each case. */

        @Test (timeout = TIMEOUT)
        public void testUnrealisticYears() throws Exception {
            assertFalse("Expected false, returned: " +
                        ((isValidDate("January", "01", "2019", "12", "00")) ? "true" : "false"),
                    isValidDate("January", "01", "2019", "12", "00"));

            assertFalse("Expected false, returned: " +
                        ((isValidDate("January", "01", "1899", "12", "00")) ? "true" : "false"),
                    isValidDate("January", "01", "1899", "12", "00"));
        }
        //===============================================================================================================================
        /* Testing for invalid dates of February (tested with non-leap years).
           isValidDate should return false in each case. */

        @Test (timeout = TIMEOUT)
        public void testInvalidFebruaryDates() throws Exception {
            assertFalse("Expected false, returned: " +
                            ((isValidDate("February", "31", "2017", "12", "00")) ? "true" : "false"),
                    isValidDate("February", "31", "2017", "12", "00"));

            assertFalse("Expected false, returned: " +
                            ((isValidDate("February", "30", "2017", "12", "00")) ? "true" : "false"),
                    isValidDate("February", "30", "2017", "12", "00"));

            assertFalse("Expected false, returned: " +
                            ((isValidDate("February", "29", "2017", "12", "59")) ? "true" : "false"),
                    isValidDate("February", "29", "2017", "12", "59"));

            assertFalse("Expected false, returned: " +
                            ((isValidDate("February", "29", "2015", "12", "59")) ? "true" : "false"),
                    isValidDate("February", "29", "2015", "12", "59"));
        }
        //===============================================================================================================================
        /* Testing for valid dates of February (tested with leap and non-leap years).
           isValidDate should return true in each case. */

        @Test (timeout = TIMEOUT)
        public void testValidFebruaryDates() throws Exception {
            assertTrue("Expected true, returned: " +
                            ((isValidDate("February", "28", "2017", "12", "00")) ? "true" : "false"),
                    isValidDate("February", "28", "2017", "12", "00"));

            assertTrue("Expected true, returned: " +
                            ((isValidDate("February", "29", "2016", "12", "00")) ? "true" : "false"),
                    isValidDate("February", "29", "2016", "12", "00"));

            assertTrue("Expected true, returned: " +
                            ((isValidDate("February", "29", "2012", "12", "00")) ? "true" : "false"),
                    isValidDate("February", "29", "2016", "12", "00"));
        }
        //===============================================================================================================================
        /* Testing for a date this year that has not arrived.
         isValidDate should return false in each case. */

        @Test (timeout = TIMEOUT)
        public void testFutureDate() throws Exception {
            assertFalse("Expected false, returned: " +
                            ((isValidDate("December", "29", "2017", "12", "00")) ? "true" : "false"),
                    isValidDate("December", "29", "2017", "12", "00"));
            assertFalse("Expected false, returned: " +
                            ((isValidDate("November", "23", "2017", "12", "00")) ? "true" : "false"),
                    isValidDate("November", "23", "2017", "12", "00"));
        }
        //===============================================================================================================================
        /* Testing with selected day greater than it should be for the month. isValidDate should
           return false in each case. */

        @Test (timeout = TIMEOUT)
        public void testInvalidDaysOfMonth() throws Exception {
            assertFalse("Expected false, returned: " +
                            ((isValidDate("November", "31", "2017", "12", "00")) ? "true" : "false"),
                            isValidDate("November", "31", "2017", "12", "00"));

            assertFalse("Expected false, returned: " +
                            ((isValidDate("April", "31", "2017", "12", "00")) ? "true" : "false"),
                            isValidDate("April", "31", "2017", "12", "00"));

            assertFalse("Expected false, returned: " +
                        ((isValidDate("June", "31", "2017", "12", "00")) ? "true" : "false"),
                        isValidDate("June", "31", "2017", "12", "00"));

            assertFalse("Expected false, returned: " +
                        ((isValidDate("September", "31", "2017", "12", "00")) ? "true" : "false"),
                        isValidDate("September", "31", "2017", "12", "00"));
        }
        //===============================================================================================================================
        /* Testing for realistic date that has passed. isValidDate should return true in each case. */

        @Test (timeout = TIMEOUT)
        public void testRealisticDates() throws Exception {
            assertTrue("Expected true, returned: " +
                        ((isValidDate("November", "17", "2017", "12", "00" )) ? "true" : "false"),
                        isValidDate("November", "17", "2017", "12", "00" ));

            assertTrue("Expected true, returned: " +
                        ((isValidDate("August", "29", "2017", "12", "00" )) ? "true" : "false"),
                        isValidDate("August", "29", "2017", "12", "00" ));

            assertTrue("Expected true, returned: " +
                        ((isValidDate("May", "05", "2017", "12", "00" )) ? "true" : "false"),
                        isValidDate("May", "05", "2017", "12", "00" ));

            assertTrue("Expected true, returned: " +
                        ((isValidDate("December", "31", "2016", "12", "00" )) ? "true" : "false"),
                        isValidDate("December", "31", "2016", "12", "00" ));

            assertTrue("Expected true, returned: " +
                        ((isValidDate("March", "17", "2016", "04", "00" )) ? "true" : "false"),
                        isValidDate("March", "17", "2016", "04", "00" ));

            assertTrue("Expected true, returned: " +
                        ((isValidDate("March", "17", "1900", "04", "00" )) ? "true" : "false"),
                        isValidDate("March", "17", "1900", "04", "00" ));
        }

    //=============================================================================================================================
    /* isValidDate() Method from DateRangeActivity */

        private boolean isValidDate(String selMonth, String selDay,
                                    String selYear, String selHour,
                                    String selMin) {
            if ("Month".equals(selMonth)) {
    //            errorMsg.setText("Select a month");
                return false;
            }

            if ("Day".equals(selDay)) {
    //            errorMsg.setText("Select a day");
                return false;
            }

            if ("Hour".equals(selHour)) {
    //            errorMsg.setText("Select an hour");
                return false;
            }

            if ("Minute".equals(selMin)) {
    //            errorMsg.setText("Select a minute");
                return false;
            }

            int minYear = 1900;

            try {
                int year = Integer.parseInt(selYear);
                if (!((year  >= minYear) && (year <= Calendar.getInstance().get(Calendar.YEAR)))) {
    //                errorMsg.setText("Enter a realistic year. You entered: " + year + ".");
                    return false;
                }
            } catch (NumberFormatException nfe) {
    //            errorMsg.setText("Invalid year entry");
                return false;
            }

            int day = Integer.parseInt(selDay);
            int year = Integer.parseInt(selYear);

            int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            HashMap<String, Integer> monthDays = new HashMap<>();
            monthDays.put("January", daysPerMonth[0]);
            monthDays.put("February", daysPerMonth[1]);
            monthDays.put("March", daysPerMonth[2]);
            monthDays.put("April", daysPerMonth[3]);
            monthDays.put("May", daysPerMonth[4]);
            monthDays.put("June", daysPerMonth[5]);
            monthDays.put("July", daysPerMonth[6]);
            monthDays.put("August", daysPerMonth[7]);
            monthDays.put("September", daysPerMonth[8]);
            monthDays.put("October", daysPerMonth[9]);
            monthDays.put("November", daysPerMonth[10]);
            monthDays.put("December", daysPerMonth[11]);

            if ("February".equals(selMonth)) {
                if (day > daysPerMonth[1]) {
                    //                    errorMsg.setText("Invalid day for selected month");
                    return (day == (daysPerMonth[1] + 1)) && ((year % 4) == 0);
                }
            }

            return Integer.parseInt(selDay) <= monthDays.get(selMonth);
        }


}