import java.util.Arrays;

public class Move_Zeroes_To_The_End {
    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 3, 0, 2, 0};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    // Pattern: Two Pointers
    // Time: O(N) - Linear scan (two passes in worst case).
    // Space: O(1) - In-place operation.
    public static void moveZeroes(int[] nums) {
        int j = -1;
        // Step 1: Find the index of the first zero to initialize the 'zero' pointer (j)
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = i;
                break;
            }
        }
        // Edge case: If no zeroes are found, the array is already valid
        if (j == -1) return;

        // Step 2: Iterate through the rest of the array with pointer 'i'
        for (int i = j + 1; i < nums.length; i++) {
            // If a non-zero is found, swap it with the zero at 'j'
            if (nums[i] != 0) {
                swap(nums, i, j);
                j++; // Advance the zero pointer to the next zero position
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
