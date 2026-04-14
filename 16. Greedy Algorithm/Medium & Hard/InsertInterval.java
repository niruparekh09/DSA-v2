import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    /*
     * Approach: Three-Pass Greedy (Linear Scan)
     * Pattern: Interval Merging / Sweep Line
     * Time Complexity: O(N) - Single pass through the intervals array.
     * Space Complexity: O(N) - To store the result list.
     */
    public int[][] insertNewInterval(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();

        int i = 0;


        // Step 1: Add all intervals that end before the newInterval starts.
        // These are strictly to the left and have no overlap.
        while (i < n && intervals[i][1] < newInterval[0]) {
            ans.add(intervals[i]);
            i++;
        }

        /* * Step 2: Merge all overlapping intervals.
         * An overlap exists as long as the current interval starts before
         * or at the time the newInterval ends.
         * Greedy Choice: Update the newInterval boundaries to cover the entire overlapping range.
         */
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // Add the merged (or single) newInterval to the results.
        ans.add(newInterval);

        // Step 3: Add all remaining intervals that start after the newInterval ends.
        // These are strictly to the right and have no overlap.
        while (i < n) {
            ans.add(intervals[i]);
            i++;
        }

        return ans.toArray(new int[ans.size()][]);
    }
}