import java.util.ArrayList;
import java.util.Collections;

public class Leaders_In_An_Array {
    public static void main(String[] args) {
        ArrayList<Integer> ans = find(new int[]{10, 22, 12, 5, 0, 6});
        System.out.println(ans);
    }

    // Pattern: Right-to-Left Scan (Suffix Maximum)
    // Time: O(N log N) - The finding logic is O(N), but the final sort dominates.
    // Space: O(N) - Worst case (descending sorted array), all elements are stored.
    private static ArrayList<Integer> find(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = arr.length;
        // Initialize with MIN_VALUE to ensure the rightmost element is always processed as a leader
        int maxi = Integer.MIN_VALUE;

        // Logic: Iterate backwards. This allows tracking the maximum element
        // to the right of the current index in O(1) time without nested loops.
        for (int i = n - 1; i >= 0; i--) {
            // If current element is strictly greater than the max seen so far on the right
            // (Use '>=' if the problem considers equal elements as leaders)
            if (arr[i] > maxi) {
                ans.add(arr[i]);
            }
            // Update the maximum for the next iteration (moving left)
            maxi = Math.max(arr[i], maxi);
        }
        // Leaders are collected in reverse topological order; sort is applied for final output requirement
        Collections.sort(ans);
        return ans;
    }
}