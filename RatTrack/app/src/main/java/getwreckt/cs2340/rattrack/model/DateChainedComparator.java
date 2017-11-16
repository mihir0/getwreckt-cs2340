package getwreckt.cs2340.rattrack.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Custom comparator for Date objects. Sorts by year, month, date, hour, minute, second in that
 * order
 * Author: Maya Viust
 */

public class DateChainedComparator implements Comparator<Date> {
        private final java.util.Collection<Comparator<Date>> listComparators = new ArrayList<>();

        //custom comparators
        //date ordering goes: year, month, date, hour, minute, second
        //DEFAULT IS DESCENDING ORDER

        private static final Comparator<Date> YearComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getYear() - d1.getYear();
            }
        };

        private static final Comparator<Date> MonthComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getMonth() - d1.getMonth();
            }
        };

        private static final Comparator<Date> DateComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getDate() - d1.getDate();
            }
        };

        //hour in military time
        private static final Comparator<Date> HourComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getHour() - d1.getHour();
            }
        };

        private static final Comparator<Date> MinuteComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getMinute() - d1.getMinute();
            }
        };

        private static final Comparator<Date> SecondComparator = new Comparator<Date>() {
            @Override
            public int compare(Date d1, Date d2) {
                return d2.getSecond() - d1.getSecond();
            }
        };

    /**
     * constructor for the comparator that sorts by calendar date then time
     */
    public DateChainedComparator() {
            this.listComparators.add(YearComparator);
            this.listComparators.add(MonthComparator);
            this.listComparators.add(DateComparator);
            this.listComparators.add(HourComparator);
            this.listComparators.add(MinuteComparator);
            this.listComparators.add(SecondComparator);
        }

        @Override
        public int compare(Date d1, Date d2) {
            for (Comparator<Date> comparator: listComparators) {
                int result = comparator.compare(d1, d2);
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        }
}
