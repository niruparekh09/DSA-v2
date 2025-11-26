import java.util.ArrayList;

public class Rearrange_Array_Elements_by_Sign {
    public static void main(String[] args) {
        int[] arr = {3, 1, -2, -5, 2, -4};
    }

    /*
     * Approach: Brute Force (Using Auxiliary Lists)
     * Pattern: Array Partitioning
     * Time Complexity: O(N) - Two passes: one to separate, one to merge.
     * Space Complexity: O(N) - Requires extra space for positive and negative lists.
     */
    private static int[] rearrangeUnoptimized(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();

        // Step 1: Segregate elements into separate buckets
        for (int element : arr) {
            if (element > 0) {
                positive.add(element);
            } else {
                negative.add(element);
            }
        }

        // Step 2: Merge back alternately (Assumes Equal Counts)
        for (int i = 0; i < n / 2; i++) {
            arr[2 * i] = positive.get(i);      // Even indices: 0, 2, 4...
            arr[2 * i + 1] = negative.get(i);  // Odd indices: 1, 3, 5...
        }

        return arr;
    }

    /*
     * Approach: Optimal One-Pass (Fixed Pointers)
     * Pattern: Two Pointers
     * Time Complexity: O(N) - Single pass to place elements directly.
     * Space Complexity: O(N) - New array required (cannot easily be done in-place without shifting).
     */
    private static int[] rearrange(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];

        // Key Logic: Pre-define starting positions.
        // Positives go to even indices (0, 2...), Negatives go to odd indices (1, 3...)
        int positiveIdx = 0;
        int negativeIdx = 1;

        for (int j : arr) {
            if (j > 0) {
                ans[positiveIdx] = j;
                positiveIdx += 2; // Jump 2 steps to next even index
            } else {
                ans[negativeIdx] = j;
                negativeIdx += 2; // Jump 2 steps to next odd index
            }
        }
        return ans;
    }

    /*
     * Approach: Follow-up Question (Unequal Counts)
     * Pattern: Array Merge
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    private static int[] rearrangeTwo(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();

        // Segregate elements
        for (int element : arr) {
            if (element > 0) {
                positive.add(element);
            } else {
                negative.add(element);
            }
        }

        // Logic: Fill alternately until the smaller list is exhausted, then append the rest.
        if (positive.size() > negative.size()) {
            // Fill the common part (1:1 ratio)
            for (int i = 0; i < negative.size(); i++) {
                arr[i * 2] = positive.get(i);
                arr[i * 2 + 1] = negative.get(i);
            }
            // Fill remaining positives at the end
            int index = negative.size() * 2;
            for (int i = negative.size(); i < positive.size(); i++) {
                arr[index] = positive.get(i);
                index++;
            }
        } else {
            // Fill the common part
            for (int i = 0; i < positive.size(); i++) {
                arr[i * 2] = positive.get(i);
                arr[i * 2 + 1] = negative.get(i);
            }
            // Fill remaining negatives at the end
            int index = positive.size() * 2;
            for (int i = positive.size(); i < negative.size(); i++) {
                arr[index] = negative.get(i);
                index++;
            }
        }
        return arr;
    }
}