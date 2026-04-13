import java.util.Arrays;

public class MinimumNumberOfPlatforms {
    /*
     * Approach: Brute Force (Interval Overlap Check)
     * Time Complexity: O(N^2) - Nested loops comparing every train with every other train.
     * Space Complexity: O(1) - No extra data structures used.
     */
    public int findPlatformBrute(int[] Arrival, int[] Departure) {
        int n = Arrival.length;
        int maxCnt = 1;

        for (int i = 0; i < n; i++) {
            int cnt = 1; // Count platforms for train 'i'
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    /* * Check if train 'j' is present at the station when train 'i' arrives.
                     * If the arrival of 'i' falls between arrival and departure of 'j',
                     * they need different platforms.
                     */
                    if ((Arrival[i] >= Arrival[j] && Departure[j] >= Arrival[i])) {
                        cnt++;
                    }
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
        }

        return maxCnt;
    }

    /*
     * Approach: Greedy (Two Pointers on Sorted Events)
     * Pattern: Sweep Line / Concurrent Intervals
     * Time Complexity: O(N log N) - Due to sorting the arrival and departure arrays.
     * Space Complexity: O(1) - Sorting is done in-place or uses O(log N) stack space.
     */
    public int findPlatform(int[] Arrival, int[] Departure) {
        int n = Arrival.length;

        // Step 1: Sort arrivals and departures independently.
        // We don't care which train departs, only THAT a train departs to free a platform.
        Arrays.sort(Arrival);
        Arrays.sort(Departure);


        // i points to next arrival, j points to next departure.
        int i = 1, j = 0;
        int ans = 1, platformCount = 1;

        // Step 2: Sweep through time using two pointers.
        while (i < n && j < n) {
            // If a train arrives before (or at the same time) the earliest departure:
            if (Arrival[i] <= Departure[j]) {
                platformCount++; // Need a new platform
                i++;
            }
            // If a train departs before the next arrival:
            else {
                platformCount--; // A platform becomes free
                j++;
            }

            // Track the maximum number of platforms used at any point in time.
            ans = Math.max(platformCount, ans);
        }

        return ans;
    }
}