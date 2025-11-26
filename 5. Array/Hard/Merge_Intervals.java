import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge_Intervals {
    public static void main(String[] args) {
        int[][] merge = mergeOptimal(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        System.out.println(Arrays.deepToString(merge));
    }

    // Pattern: Sorting + Nested Iteration (Brute Force approach)
    // Time: O(N^2) - Sorting takes NlogN, but nested loops can run O(N^2) in worst case.
    // Space: O(N) - To store the result list.
    public static int[][] merge(int[][] arr) {
        List<int[]> ans = new ArrayList<>();

        // Step 1: Sort intervals by start time to bring overlapping intervals together
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int start = arr[i][0]; // First element of each pair
            int end = arr[i][1]; // Second element of each pair

            // Logic: If the current interval is already covered by the last merged interval, skip it.
            // This handles intervals "consumed" by the inner loop of a previous iteration.
            if (!ans.isEmpty() && end <= ans.getLast()[1]) continue;

            for (int j = i + 1; j < n; j++) {
                // Check for overlap: if next interval starts before current ends
                if (arr[j][0] <= end) {
                    // Logic: Merge intervals by extending the current 'end' to the max of both
                    end = Math.max(end, arr[j][1]);
                } else {
                    break; // Since array is sorted, no further overlaps are possible
                }
            }
            ans.add(new int[]{start, end});
        }
        return ans.toArray(new int[0][]);
    }

    // Pattern: Sorting + Linear Scan (Greedy)
    // Time: O(N log N) - dominated by the sorting step. Single pass is O(N).
    // Space: O(N) - To store the result list.
    public static int[][] mergeOptimal(int[][] arr) {
        List<int[]> ans = new ArrayList<>();

        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // Base Case: Add the first interval to start the list
            if (ans.isEmpty()) {
                ans.add(new int[]{arr[0][0], arr[0][1]});
                continue;
            }

            // Logic: Compare current interval with the last added interval in 'ans'
            int[] lastElement = ans.getLast();

            // Check Overlap: If last interval ends after current starts
            if (lastElement[1] >= arr[i][0]) {
                // Merge: Extend the end time of the last interval if needed
                if (lastElement[1] < arr[i][1]) lastElement[1] = arr[i][1];
            } else {
                // No Overlap: Start a new interval
                ans.add(new int[]{arr[i][0], arr[i][1]});
            }
        }
        return ans.toArray(new int[0][]);
    }
}

