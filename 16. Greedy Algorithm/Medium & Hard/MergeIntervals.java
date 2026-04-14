import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    /*
     * Approach: Sorting + Linear Scan
     * Pattern: Overlapping Intervals
     * Time Complexity: O(N log N) - Primarily due to sorting the intervals.
     * Space Complexity: O(N) - To store the merged intervals in the result list.
     */
    public int[][] merge(int[][] intervals) {
        // Step 1: Sort intervals by their start times.
        // This ensures that any intervals that could potentially merge are adjacent.
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> ans = new ArrayList<>();
        int i = 0;

        // Step 2: Iterate through the sorted intervals.
        while (i < intervals.length) {
            // If the list is empty, add the first interval to start the process.
            if (ans.isEmpty()) {
                ans.add(intervals[i]);
            } else {
                // Get the last added interval in our result list.
                int[] latest = ans.get(ans.size() - 1);

                /* * Greedy Choice: Check for overlap.
                 * If the end of the 'latest' interval is greater than or equal to
                 * the start of the current interval, they overlap.
                 */
                if (latest[1] >= intervals[i][0]) {
                    // Merge by extending the end time to the maximum of both.
                    latest[1] = Math.max(latest[1], intervals[i][1]);
                } else {
                    // No overlap found; add the current interval as a new entry.
                    ans.add(intervals[i]);
                }
            }
            i++;
        }

        return ans.toArray(new int[ans.size()][]);
    }
}