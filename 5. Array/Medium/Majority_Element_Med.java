import java.util.HashMap;
import java.util.Map;

public class Majority_Element_Med {
    public static void main(String[] args) {
        System.out.println(getMajorElementUsingMoores(new int[]{2, 2, 3, 3, 1, 2, 2}));
    }

    /*
     * Approach: Moore's Voting Algorithm (Optimal)
     * Pattern: Boyer-Moore Voting
     * Time Complexity: O(N) - Two passes (one to find candidate, one to verify).
     * Space Complexity: O(1) - Uses only constant extra variables.
     */
    private static int getMajorElementUsingMoores(int[] arr) {
        int el = 0;
        int count = 0;

        // Pass 1: Find a potential candidate
        // Intuition: Majority elements will "cancel out" minority elements, leaving the majority standing.
        for (int i : arr) {
            if (count == 0) {
                // Reset candidate when count hits 0
                count = 1;
                el = i;
            } else if (el == i) {
                count++; // Reinforce current candidate
            } else {
                count--; // Cancel out current candidate
            }
        }

        // Pass 2: Verify the candidate
        // Key Logic: Necessary because Moore's algorithm might return a candidate even if no majority exists.
        int count1 = 0;
        for (int i : arr) {
            if (i == el) {
                count1++;
            }
        }

        if (count1 > arr.length / 2) return el;
        return -1;
    }

    /*
     * Approach: Frequency Map
     * Pattern: Hashing
     * Time Complexity: O(N) - Iterate array once and map once.
     * Space Complexity: O(N) - Worst case, map stores N/2 unique elements.
     */
    private static int getMajorElement(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();

        // Count frequencies of all elements
        for (int element : arr) {
            int value = map.getOrDefault(element, 0);
            map.put(element, value + 1);
        }

        System.out.println(map.toString());

        // Iterate map to find the element exceeding the N/2 threshold
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 2) return entry.getKey();
        }
        return -1;
    }
}