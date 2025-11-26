public class Number_That_Appears_Once_In_Array {
    public static void main(String[] args) {
        System.out.println(getNumber(new int[]{1, 1, 2, 2, 3, 4, 4}));
    }

    // Coding Pattern: Bit Manipulation
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int getNumber(int[] nums) {
        // Initialize result to 0. XOR with 0 returns the number itself.
        int number = 0;
        // Iterate through the array.
        for (int num : nums) {
            // XOR each number with the current result.
            // XORing a number with itself results in 0, effectively canceling out duplicates.
            number = number ^ num;
        }
        // The final result is the number that appears only once.
        return number;
    }
}
