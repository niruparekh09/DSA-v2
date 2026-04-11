import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NMeetingsInOneRoom {
    /*
     * Approach: Greedy Algorithm (Activity Selection Problem)
     * Pattern: Interval Scheduling
     * Time Complexity: O(N log N) - Due to sorting the meetings by their end times.
     * Space Complexity: O(N) - To store the meeting details in a list.
     */
    public int maxMeetings(int[] start, int[] end) {
        List<int[]> meeetingList = new ArrayList<>();

        // Step 1: Combine start and end times to keep track of individual meetings.
        for (int i = 0; i < start.length; i++) {
            meeetingList.add(new int[]{start[i], end[i], i});
        }

        /* * Step 2: Sort meetings based on their end times (Greedy Choice).
         * Why? Ending a meeting as early as possible leaves the most time
         * available for subsequent meetings.
         */

        meeetingList.sort(Comparator.comparingInt(a -> a[1]));

        int count = 0;
        int freeTime = 0; // Represents the time the room becomes available

        // Step 3: Iterate through sorted meetings and pick those that start after the room is free.
        for (int[] meeting : meeetingList) {
            int startTime = meeting[0];
            int endTime = meeting[1];

            if (startTime > freeTime) {
                // Meeting can be scheduled
                count++;
                // Update freeTime to the end time of the current meeting
                freeTime = endTime;
            }
        }

        return count;
    }
}