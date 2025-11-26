import java.util.List;

public class Find_How_Many_Times_Is_Array_Rotated {
    public static void main(String[] args) {
        System.out.println(findRotation(List.of(4, 5, 1, 2, 3)));
    }

    /*
     * Approach: Binary Search (Find Minimum Index)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Standard binary search splitting.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int findRotation(List<Integer> arr) {
        // Key Insight: The number of rotations is exactly equal to the INDEX of the minimum element.
        // Example: [4, 5, 1, 2, 3] -> Min is 1 at index 2. Rotations = 2.

        int ans = Integer.MAX_VALUE;
        int index = 0;
        int low = 0, high = arr.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Identify which half is sorted

            // Case 1: Left half is sorted [low...mid]
            if (arr.get(low) <= arr.get(mid)) {
                // If sorted, the smallest element in this range is at 'low'.
                // Update global minimum if this is smaller than what we've seen.
                if (ans > arr.get(low)) {
                    index = low;
                    ans = arr.get(low);
                }
                // Optimization: We processed the left half (took its min), so now search the Right half.
                low = mid + 1;
            }
            // Case 2: Right half is sorted [mid...high]
            else {
                // If sorted, the smallest element in this range is at 'mid'.
                if (ans > arr.get(mid)) {
                    index = mid;
                    ans = arr.get(mid);
                }
                // Processed right half, now search the Left half.
                high = mid - 1;
            }
        }
        return index;
    }
}