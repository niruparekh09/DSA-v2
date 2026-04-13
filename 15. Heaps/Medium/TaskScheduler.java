import java.util.*;

public class TaskScheduler {
    /*
     * Approach: Greedy with Max-Heap and Cooling Queue
     * Pattern: CPU Scheduling / Cooling Period Management
     * Time Complexity: O(T) - T is the total time intervals (can be up to tasks.length * n).
     * Space Complexity: O(1) - The map and heap only ever store 26 characters (constant space).
     */
    public int leastInterval(char[] tasks, int n) {
        // Step 1: Count frequency of each task.
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        // Step 2: Use a Max-Heap to always pick the most frequent task available.
        // Greedy Choice: Executing the most frequent task first minimizes total idle time.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int count : map.values()) {
            maxHeap.add(count);
        }

        /* * Step 3: Use a Queue to manage the "Cooling Period."
         * The queue stores tasks that have been executed but are waiting for n intervals to pass.
         * Format: [remaining_count, available_at_time]
         */

        Queue<int[]> queue = new ArrayDeque<>();
        int time = 0;

        // Loop until all tasks are processed and no tasks are left in the cooling queue.
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            time++;

            // If a task in the cooling queue has finished its cooldown, move it back to the heap.
            if (!queue.isEmpty() && queue.peek()[1] == time) {
                maxHeap.add(queue.poll()[0]);
            }

            // If tasks are available to be scheduled, pick the one with the highest remaining count.
            if (!maxHeap.isEmpty()) {
                int remainingCount = maxHeap.poll() - 1;

                // If the task still has instances left, calculate when it can be run again (time + n + 1).
                if (remainingCount > 0) {
                    queue.add(new int[]{remainingCount, time + n + 1});
                }
            }
            // If maxHeap is empty but queue isn't, the CPU is effectively "Idle" at this interval.
        }

        return time;
    }
}