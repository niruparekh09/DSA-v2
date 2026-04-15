import java.util.Arrays;

public class ShortestJobFirst {
    /*
     * Approach: Greedy Algorithm
     * Pattern: Minimizing Average Waiting Time
     * Time Complexity: O(N log N) - Due to sorting the burst times.
     * Space Complexity: O(1) - Sorting is done in-place (or O(log N) stack space).
     */
    public long solve(int[] bt) {
        /* * Step 1: Sort the burst times in ascending order.
         * Greedy Choice: Always execute the shortest job available first.
         * Why? Shortening the wait for subsequent jobs by finishing small tasks
         * quickly reduces the "accumulation" of waiting time for the entire set.
         */
        Arrays.sort(bt);



        long totalWaitTime = 0; // Cumulative wait time of all processes
        long curWaitTime = 0;   // Wait time for the current process

        // Step 2: Calculate waiting time for each process
        for (int b : bt) {
            /* * The current process's wait time is added to the total.
             * Note: The first process waits 0, the second waits for the first's burst, etc.
             */
            totalWaitTime += curWaitTime;

            // The next process will have to wait for the current one to finish.
            curWaitTime += b;
        }

        // Return the average waiting time.
        return totalWaitTime / bt.length;
    }
}