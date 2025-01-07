package BSOnAns;

public class Smallest_Divisor {

    public static void main(String[] args) {
        System.out.println(smallestDivisor(new int[]{1, 2, 5, 9}, 6));
    }

    public static int smallestDivisor(int[] nums, int threshold) {
        if (threshold < nums.length) return -1;
        int low = 1, high = getMax(nums);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sumByD(nums, mid) <= threshold) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int sumByD(int[] nums, int mid) {
        int ans = 0;
        for (int num : nums) {
            ans += (int) Math.ceil((double) num / (double) mid);
        }
        return ans;
    }

    private static int getMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int el : nums) {
            max = Math.max(el, max);
        }
        return max;
    }
}
