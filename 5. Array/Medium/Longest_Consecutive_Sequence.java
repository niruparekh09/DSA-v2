import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Longest_Consecutive_Sequence {
    public static void main(String[] args) {
        int longestSeq = getLongestSeqOptimal(new int[]{100, 102, 100, 101, 101, 4, 3, 2, 3, 2, 1, 1, 1, 2});
        System.out.println(longestSeq);
    }

    /*
     * Approach: HashSet Lookup
     * Pattern: Hashing
     * Time Complexity: O(N) - We iterate the array once and the set roughly 2N times.
     * Space Complexity: O(N) - Storing unique elements in the Set.
     */
    private static int getLongestSeqOptimal(int[] arr) {
        // Edge Case: Empty array has sequence length 0
        if (arr.length == 0) return 0;

        int longest = 1;
        Set<Integer> set = new HashSet<>();

        // Add to Set to allow O(1) lookups and remove duplicates
        for (int el : arr) {
            set.add(el);
        }

        for (int it : set) {
            // Key Logic: Only start counting if 'it' is the START of a sequence.
            // If (it - 1) exists, this number is part of a sequence started by someone else.
            if (!set.contains(it - 1)) {
                int cnt = 1;
                int x = it;

                // Expand sequence: Check for x+1, x+2... using O(1) lookup
                while (set.contains(x + 1)) {
                    x = x + 1;
                    cnt = cnt + 1;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }

    /*
     * Approach: Sort and Iterate
     * Pattern: Sorting
     * Time Complexity: O(N log N) - Dominated by the sorting algorithm.
     * Space Complexity: O(1) or O(N) - Depends on sort implementation (ignoring input mod).
     */
    private static int getLongestSeq(int[] arr) {
        if (arr.length == 0) return 0;

        int cnt = 1;
        int lastSmallest = Integer.MIN_VALUE;
        int longest = 0;

        // Group consecutive elements together
        Arrays.sort(arr);

        for (int el : arr) {
            // Logic: If current is exactly 1 greater than previous, extend sequence
            if (el - 1 == lastSmallest) {
                cnt = cnt + 1;
                lastSmallest = el;
            }
            // Logic: If sequence breaks (and not a duplicate), start new count
            else if (el != lastSmallest) {
                lastSmallest = el;
                cnt = 1;
            }
            // Note: Duplicates (el == lastSmallest) are ignored entirely

            longest = Math.max(longest, cnt);
        }
        return longest;
    }
}