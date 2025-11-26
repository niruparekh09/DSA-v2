public class Remove_Duplicate_From_Sorted_Array {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1, 1, 1, 2, 2, 3, 3}));
    }

    // Coding Pattern: Two Pointers
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int removeDuplicates(int[] nums) {
        // i: slow pointer, j: fast pointer
        int i = 0;
        // Iterate from the second element
        for (int j = 1; j < nums.length; j++) {
            // Why: Find a different element
            if (nums[i] != nums[j]) {
                // Why: Update the next unique element
                nums[i + 1] = nums[j];
                // Why: Advance the slow pointer to track the last unique element
                i++;
            }
        }
        // Why: Return the length of the unique array (index + 1)
        return i + 1;
    }
}
