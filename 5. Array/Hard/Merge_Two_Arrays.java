import java.util.Arrays;

public class Merge_Two_Arrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 7};
        int[] nums2 = {0, 2, 6, 8, 9};
        mergerOptimal1(nums1, nums1.length, nums2, nums2.length);
    }

    // Pattern: Two Pointers (Auxiliary Array)
    // Time: O((M+N) log(M+N)) - The merge is linear, but the Arrays.sort at the end dominates.
    // Space: O(M + N) - Requires a temporary array to hold merged results.
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] mergedArray = new int[m + n];
        int left = 0;
        int right = 0;
        int idx = 0;

        // Standard merge sort logic: Compare head of both arrays and pick smallest
        while (left < m && right < n) {
            if (nums1[left] >= nums2[right]) {
                mergedArray[idx] = nums2[right];
                right++;
            } else {
                mergedArray[idx] = nums1[left];
                left++;
            }
            idx++;
        }
        // Collect remaining elements from nums1
        while (left < m) {
            mergedArray[idx] = nums1[left];
            left++;
            idx++;
        }
        // Collect remaining elements from nums2
        while (right < n) {
            mergedArray[idx] = nums2[right];
            right++;
            idx++;
        }

        // Note: Logic includes a sort here, making the previous manual merge redundant regarding order.
        Arrays.sort(mergedArray);

        // Copy merged data back into original arrays
        if (m >= 0) System.arraycopy(mergedArray, 0, nums1, 0, m);
        if (n >= 0) System.arraycopy(mergedArray, m, nums2, 0, n);
    }

    // Pattern: Swap and Sort (Two Pointers)
    // Time: O(M log M + N log N) - Linear swap loop followed by sorting both arrays.
    // Space: O(1) - In-place modification.
    public static void mergerOptimal1(int[] nums1, int m, int[] nums2, int n) {
        /*for (int i = 0; i < n; i++) {
            int idx = m - i - 1;
            if (nums1[idx] > nums2[i]) {
                int temp = nums2[i];
                nums2[i] = nums1[idx];
                nums1[idx] = temp;
            } else {
                break;
            }
        }*/

        // Pointers: 'left' at end of nums1, 'right' at start of nums2
        int left = m - 1;
        int right = 0;

        // Logic: Swap if element in nums1 is greater than element in nums2.
        // Goal: Push all larger elements to nums2 and smaller elements to nums1.
        while (left >= 0 && right < n) {
            if (nums1[left] > nums2[right]) {
                int temp = nums2[right];
                nums2[right] = nums1[left];
                nums1[left] = temp;
                left--;
                right++;
            } else {
                // Since arrays are sorted, once this condition fails, no further swaps are needed.
                break;
            }
        }
        // Arrays contain correct partition of numbers but are unsorted internally
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        System.out.println(Arrays.toString(nums1) + "----" + Arrays.toString(nums2));
    }

    // Pattern: Gap Method (Shell Sort variation)
    // Time: O((M+N) log(M+N)) - Gap reduces by half each iteration.
    // Space: O(1) - In-place.
    public static void mergerOptimal2(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        // Initial gap is ceiling of len/2
        int gap = len / 2 + len % 2;

        while (gap > 0) {
            int left = 0;
            int right = left + gap;

            while (right < len) {
                // Logic: Treat nums1 and nums2 as a single contiguous virtual array.

                // Case 1: left in nums1, right in nums2
                if (left < m && right >= m) {
                    swapIfGreater(nums1, nums2, left, right - m); // Adjust index for nums2
                }
                // Case 2: Both pointers in nums2
                else if (left >= m) {
                    swapIfGreater(nums2, nums2, left - m, right - m);
                }
                // Case 3: Both pointers in nums1
                else {
                    swapIfGreater(nums1, nums1, left, right);
                }
                left++;
                right++;
            }
            // Break loop to avoid infinite loop when gap is 1 (since 1/2 + 1%2 = 1)
            if (gap == 1) break;
            // Reduce gap
            gap = gap / 2 + gap % 2;
        }
    }

    private static void swapIfGreater(int[] nums1, int[] nums2, int i1, int i2) {
        if (nums1[i1] > nums2[i2]) {
            int temp = nums2[i2];
            nums2[i2] = nums1[i1];
            nums1[i1] = temp;
        }
    }

    // Pattern: Brute Force (Concat + Sort)
    // Time: O((M+N) log(M+N)) - Dominated by sorting.
    // Space: O(1) - Assuming nums1 has capacity, ignores sort stack space.
    public void mergeLeetcode(int[] nums1, int m, int[] nums2, int n) {
        // Edge Case: If one array is empty
        if (m == 0 || n == 0) {
            if (n != 0) {
                System.arraycopy(nums2, 0, nums1, 0, nums2.length);
            }
            return;
        }
        int left = m;
        int right = 0;

        // Append nums2 elements to the end of valid nums1 elements
        while (left < m + n) {
            nums1[left] = nums2[right];
            left++;
            right++;
        }
        // Sort the entire array
        Arrays.sort(nums1);
    }
}