import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
    /*
     * Approach: Greedy (Activity Selection Variation)
     * Pattern: Interval Scheduling - Minimize Removals
     * Time Complexity: O(N log N) - Sorting the intervals by end time.
     * Space Complexity: O(log N) - Space used by the sorting algorithm.
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        /* * Step 1: Sort intervals based on their END times.
         * Greedy Choice: By picking intervals that end earliest, we leave
         * as much room as possible for subsequent intervals.
         */
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 0; // Number of intervals to remove
        int i = 0;

        // 'k' tracks the end time of the last interval we decided to keep.
        int k = Integer.MIN_VALUE;
        // List<int[]> list = new ArrayList<>(); <--- Do you really need to store all the res? We are only checking [1] of previous element

        // Step 2: Iterate through intervals and decide which to keep.
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            /* * If the current interval starts after or at the end of the
             * previous kept interval, there is no overlap.
             */
            if (k <= start) {
                // Keep this interval and update the 'k' boundary.
                k = end;
            } else {
                /* * If there is an overlap, we "remove" the current interval.
                 * Since we sorted by end time, the interval we already kept (k)
                 * is guaranteed to end before or at the same time as this one.
                 * Removing the current one is greedily optimal to save space.
                 */
                count++;
            }
            i++;
        }

        return count;
    }
}