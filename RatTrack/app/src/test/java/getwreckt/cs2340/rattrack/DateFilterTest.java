package getwreckt.cs2340.rattrack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import getwreckt.cs2340.rattrack.model.Date;
import getwreckt.cs2340.rattrack.model.RatSighting;
import getwreckt.cs2340.rattrack.model.SightingManager;

/**
 * Created by aguy on 11/18/17.
 */

public class DateFilterTest {
    private List<RatSighting> listToFilter;

    private void setup() {
        listToFilter = new ArrayList<>();
        listToFilter.add(new RatSighting("1111111", "9/3/2017 12:30:56 PM", "Unknown", "10101", "123 Brook Rd", "Brooklyn", "Brooklyn", "47.567", "-74.568"));
        listToFilter.add(new RatSighting("1111112","9/2/2017 12:30:56 PM", "Unknown", "10101", "234 Brook Rd", "Brooklyn", "Brooklyn", "47.567", "-74.568"));
        listToFilter.add(new RatSighting("1111113","8/4/2017 12:30:56 PM", "Unknown", "10101", "345 Brook Rd", "Brooklyn", "Brooklyn", "47.567", "-74.568"));
        listToFilter.add(new RatSighting("1111114","9/4/2016 12:30:56 PM", "Unknown", "10101", "456 Brook Rd", "Brooklyn", "Brooklyn", "47.567", "-74.568"));
        listToFilter.add(new RatSighting("1111115","9/4/2015 12:30:56 PM", "Unknown", "10101", "567 Brook Rd", "Brooklyn", "Brooklyn", "47.567", "-74.568"));
        listToFilter.add(new RatSighting("1111116","9/3/2017 12:10:56 PM", "Unknown", "10101", "678 Brook Rd", "Brooklyn", "Brooklyn", "47.567", "-74.568"));
        listToFilter.add(new RatSighting("1111117","9/3/2017 12:30:56 AM", "Unknown", "10101", "789 Brook Rd", "Brooklyn", "Brooklyn", "47.567", "-74.568"));
        listToFilter.add(new RatSighting("1111118","9/1/2017 12:30:56 PM", "Unknown", "10101", "890 Brook Rd", "Brooklyn", "Brooklyn", "47.567", "-74.568"));
    }

    @Test
    public void testPastMonth() {
        setup();
        List<RatSighting> newList = SightingManager.dateFilter(1, "Months", listToFilter, new Date("9/3/2017 12:30:56 PM"));
        Set<String> addresses = new HashSet<>();
        addresses.add("123 Brook Rd");
        addresses.add("234 Brook Rd");
        addresses.add("345 Brook Rd");
        addresses.add("678 Brook Rd");
        addresses.add("789 Brook Rd");
        addresses.add("890 Brook Rd");
        Set<String> addressesToCheck = new HashSet<>();
        for (RatSighting ratSighting : newList) {
            addressesToCheck.add(ratSighting.getLocation().getAddress());
        }
        Assert.assertTrue(addresses.containsAll(addressesToCheck) && addressesToCheck.containsAll(addresses));
    }
}
