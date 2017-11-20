package getwreckt.cs2340.rattrack.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Manages rat sightings: filters, sorts, creates, deletes.
 * all methods should be static
 * Created by maya v on 10/28/2017.
 */

public class SightingManager {
    public static List<RatSighting> ratSightings = new ArrayList<>();

    public static Date startMapDate = new Date("01/01/1000 12:00:00 AM");
    public static Date endMapDate = new Date("12/31/3999 11:59:00 PM");

    public static Date startGraphDate = new Date("01/01/1000 12:00:00 AM");
    public static Date endGraphDate = new Date("12/31/3999 11:59:00 PM");

    public static void addSighting(RatSighting sighting) {
        ratSightings.add(sighting);
    }
}
