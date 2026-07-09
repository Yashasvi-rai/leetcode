import java.util.*;

class MyCalendarTwo {

    private List<int[]> bookings;
    private List<int[]> overlaps;

    public MyCalendarTwo() {
        bookings = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {

        // Check for triple booking
        for (int[] interval : overlaps) {
            if (Math.max(start, interval[0]) < Math.min(end, interval[1])) {
                return false;
            }
        }

        // Record new double-booked intervals
        for (int[] interval : bookings) {

            int left = Math.max(start, interval[0]);
            int right = Math.min(end, interval[1]);

            if (left < right) {
                overlaps.add(new int[]{left, right});
            }
        }

        bookings.add(new int[]{start, end});

        return true;
    }
}