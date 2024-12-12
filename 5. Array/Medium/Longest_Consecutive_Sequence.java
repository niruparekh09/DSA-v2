package Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Longest_Consecutive_Sequence {
    public static void main(String[] args) {
        int longestSeq = getLongestSeqOptimal(new int[]{100, 102, 100, 101, 101, 4, 3, 2, 3, 2, 1, 1, 1, 2});
        System.out.println(longestSeq);
    }

    private static int getLongestSeqOptimal(int[] arr) {
        if (arr.length == 0) return 0;
        int longest = 1;
        Set<Integer> set = new HashSet<>();
        for (int el : arr) {
            set.add(el);
        }
        for (int it : set) {
            // If it is the starting element, like in our arr 100 is starting because 100-1=99 doesn't exist.
            if (!set.contains(it - 1)) {
                int cnt = 1;
                int x = it; // x = 100 suppose
                // This while loop will search for 101,102....
                while (set.contains(x + 1)) { // Rather than LS, set will take O(1) to find an element
                    x = x + 1;
                    cnt = cnt + 1;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }

    private static int getLongestSeq(int[] arr) {
        int cnt = 1;
        int lastSmallest = Integer.MIN_VALUE;
        int longest = 0;
        Arrays.sort(arr);
        for (int el : arr) {
            if (el - 1 == lastSmallest) {
                cnt = cnt + 1;
                lastSmallest = el;
            } else if (el != lastSmallest) {
                lastSmallest = el;
                cnt = 1;
            }
            longest = Math.max(longest, cnt);
        }
        return longest;
    }
}
