public class Count_Consecutive_Ones {
    public static void main(String[] args) {
        System.out.println(count(new int[]{1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1}));
    }

    // Time Complexity: O(n), Space Complexity: O(1)
    public static int count(int[] arr) {
        int max = 0, count = 0;
        for (int j : arr) {
            // If current element is 1, increment the count of consecutive 1s
            if (j == 1) {
                count++;
            } else {
                // Reset count if current element is not 1
                count = 0;
            }
            // Update max if current count is greater
            if (max < count) max = count;
        }
        return max;
    }
}
