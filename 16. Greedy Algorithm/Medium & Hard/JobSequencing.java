import java.util.Arrays;

public class JobSequencing {
    /*
     * Approach: Greedy Algorithm
     * Pattern: Deadline-Based Profit Maximization
     * Time Complexity: O(N log N + N * M) - N is total jobs, M is max deadline.
     * Space Complexity: O(M) - To store the schedule hash table.
     */
    public int[] JobScheduling(int[][] Jobs) {
        /* * Step 1: Sort jobs in descending order of profit.
         * Greedy Choice: Always prioritize the highest-paying jobs first.
         */
        Arrays.sort(Jobs, (a, b) -> b[2] - a[2]);

        int n = Jobs.length;

        // Step 2: Find the maximum deadline to determine the timeline size.
        int maxDeadline = -1;
        for (int[] it : Jobs) {
            maxDeadline = Math.max(maxDeadline, it[1]);
        }

        /* * Step 3: Initialize a schedule (hash table) to track available time slots.
         * Each index represents a 1-unit time slot (0 to maxDeadline-1).
         */
        int[] hash = new int[maxDeadline];
        Arrays.fill(hash, -1);

        int cnt = 0;
        int totalProfit = 0;


        // Step 4: Process each job greedily.
        for (int i = 0; i < n; i++) {
            /* * Greedy Strategy: Attempt to schedule the job as late as possible
             * but before its deadline. This leaves earlier slots free for jobs
             * with tighter (earlier) deadlines.
             */
            for (int j = Jobs[i][1] - 1; j >= 0; j--) {
                // If a slot 'j' is available, schedule the job there.
                if (hash[j] == -1) {
                    cnt++;
                    hash[j] = Jobs[i][0]; // Store Job ID
                    totalProfit += Jobs[i][2];
                    break; // Move to the next highest-profit job
                }
            }
        }

        // Return the total number of jobs scheduled and the max profit achieved.
        return new int[] {cnt, totalProfit};
    }
}