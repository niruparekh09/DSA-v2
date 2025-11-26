import java.util.ArrayList;

public class Union_Of_Two_Sorted_Arrays {
    public static void main(String[] args) {
        ArrayList<Integer> union = findUnion(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 6, 7});

        System.out.println(union);
    }

    // Pattern: Two Pointers (Merge Logic)
    // Time: O(N + M) - Single pass through both arrays (N and M are array lengths).
    // Space: O(N + M) - Used to store the output Union list.
    public static ArrayList<Integer> findUnion(int[] a, int[] b) {

        ArrayList<Integer> unionList = new ArrayList<>();
        int n1 = a.length;
        int n2 = b.length;
        int i = 0;
        int j = 0;

        // Traverse both sorted arrays simultaneously
        while (i < n1 && j < n2) {
            // Compare current elements to maintain sorted order in union
            if (a[i] < b[j]) {
                // Logic: Add only if list is empty or the last element is different (De-duplication)
                if (unionList.isEmpty() || unionList.getLast() != a[i]) {
                    unionList.add(a[i]);
                }
                i++;
            } else {
                // Handles cases where b[j] < a[i] OR b[j] == a[i]
                // Note: If values are equal, b[j] is added here; a[i] will be skipped in the next iteration via the de-dupe check
                if (unionList.isEmpty() || unionList.getLast() != b[j]) {
                    unionList.add(b[j]);
                }
                j++;
            }
        }

        // Edge Case: If array 'a' still has elements remaining
        while (i < n1) {
            if (unionList.isEmpty() || unionList.getLast() != a[i]) {
                unionList.add(a[i]);
            }
            i++;
        }

        // Edge Case: If array 'b' still has elements remaining
        while (j < n2) {
            if (unionList.isEmpty() || unionList.getLast() != b[j]) {
                unionList.add(b[j]);
            }
            j++;
        }

        return unionList;
    }
}
