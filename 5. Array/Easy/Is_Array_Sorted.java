public class Is_Array_Sorted {
    public static void main(String[] args) {
        System.out.println(isSorted(new int[]{1, 3, 4, 5, 4, 7}));
    }

    // Time: O(n), Space: O(1)
    private static boolean isSorted(int[] arr) {
        // Iterate up to the second-to-last element
        for (int i = 0; i < arr.length - 1; i++) {
            // Check if current element is not less than or equal to the next element.
            // This detects out-of-order elements.
            if (!(arr[i] <= arr[i + 1])) {
                return false; // Not sorted
            }
        }
        return true; // Array is sorted after checking all elements.
    }
}
